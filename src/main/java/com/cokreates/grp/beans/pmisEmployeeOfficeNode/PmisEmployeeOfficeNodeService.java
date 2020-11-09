package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeService;
import com.cokreates.grp.beans.employeeimport.EmployeeImportService;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOffice;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.components.UtilCharacter;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PmisEmployeeOfficeNodeService extends MasterService<PmisEmployeeOfficeNodeDTO, PmisEmployeeOfficeNode> {

    @Autowired
    PmisEmployeeOfficeNodeRepository repository;

    @Autowired
    EmployeeImportService employeeImportService;
    
    @Autowired
    EmployeeOfficeService employeeOfficeService;

	@Autowired
	UtilCharacter utilCharacter;
    
    @Autowired
    ObjectMapper objectMapper;

    public PmisEmployeeOfficeNodeService(RequestBuildingComponent<PmisEmployeeOfficeNodeDTO> requestBuildingComponent, DataServiceRestTemplateClient<PmisEmployeeOfficeNodeDTO, PmisEmployeeOfficeNode> dataServiceRestTemplateClient) {
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public PmisEmployeeOfficeNode update(PmisEmployeeOfficeNodeDTO node) {
    	PmisEmployeeOfficeNode requestedNode = convertDTOtoEntity(node);
    	PmisEmployeeOfficeNode createdItem = repository.save(requestedNode);
        return createdItem;
    }
    
    public List<PmisEmployeeOfficeNodeDTO> create(List<PmisEmployeeOfficeNodeDTO> nodes) {
    	List<PmisEmployeeOfficeNode> requestedNodes = new ArrayList<PmisEmployeeOfficeNode>();
    	nodes.stream().forEach(dto -> requestedNodes.add(convertDTOtoEntity(dto)));
    	List<PmisEmployeeOfficeNode> createdItems = repository.saveAll(requestedNodes);
    	List<PmisEmployeeOfficeNodeDTO> createdItemsDTO =new ArrayList<PmisEmployeeOfficeNodeDTO>();
    	createdItems.stream().forEach(createdItem -> createdItemsDTO.add(convertEntityToDTO(createdItem)));
        return createdItemsDTO;
    }
    
    public List<PmisEmployeeOfficeNodeDTO> updateAll(String pmisOid, List<PmisEmployeeOfficeNodeDTO> inputNodes) {
    	List<PmisEmployeeOfficeNode> existingNodeList = repository.findAllByPmisOidAndRowStatus(pmisOid, Constant.STATUS_ACTIVE);
    	List<PmisEmployeeOfficeNode> requestedNodes = new ArrayList<PmisEmployeeOfficeNode>();
    	try {
    		inputNodes.stream().forEach(dto -> requestedNodes.add(convertRequestedDTOtoEntity(dto, existingNodeList)));
    	
	    	existingNodeList.removeAll(requestedNodes);
	    	existingNodeList.forEach(node -> node.setRowStatus(Constant.STATUS_DELETE));
	    	requestedNodes.addAll(existingNodeList);
	    	List<PmisEmployeeOfficeNode> createdItems = repository.saveAll(requestedNodes);
	    	List<PmisEmployeeOfficeNodeDTO> createdItemsDTO =new ArrayList<PmisEmployeeOfficeNodeDTO>();
	    	createdItems.stream().forEach(createdItem -> createdItemsDTO.add(convertEntityToDTO(createdItem)));
	        return createdItemsDTO;
    	} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	public List<PmisEmployeeOfficeNodeDTO> getPmisEmployeeOfficeNodes(String pmisOid) {

		List<PmisEmployeeOfficeNode> nodeList = repository.findAllByPmisOidAndRowStatus(pmisOid, Constant.STATUS_ACTIVE);

		Set<String> employeeOfficeOidSet = new HashSet<>();
		if (!nodeList.isEmpty())
			employeeOfficeOidSet = nodeList.stream().map(node -> node.getEmployeeOfficeOid()).collect(Collectors.toSet());

		List<EmployeeOffice> employeeOfficeList = new ArrayList<>();
		if (employeeOfficeOidSet.size() > 0)
			employeeOfficeList = employeeImportService.getEmployeeOfficeListByEmployeeOfficeOidSet(employeeOfficeOidSet);

		List<PmisEmployeeOfficeNodeDTO> result = new ArrayList<>();
		if (!employeeOfficeList.isEmpty())
			result = employeeOfficeList.stream().map(employeeOffice -> getModelMapper().map(employeeOffice, PmisEmployeeOfficeNodeDTO.class)).collect(Collectors.toList());


		for (PmisEmployeeOfficeNodeDTO pmisEmployeeOfficeNodeDTO : result) {
			String employeeOfficeOid = pmisEmployeeOfficeNodeDTO.getOid();
			for (PmisEmployeeOfficeNode node : nodeList) {
				String employeeOfficeOidFromNode = node.getEmployeeOfficeOid();
				if (null != employeeOfficeOid && !employeeOfficeOid.isEmpty()
						&& null != employeeOfficeOidFromNode && !employeeOfficeOidFromNode.isEmpty()
						&& employeeOfficeOid.equals(employeeOfficeOidFromNode)) {
					pmisEmployeeOfficeNodeDTO.setIsApprover(node.getIsApprover());
					pmisEmployeeOfficeNodeDTO.setIsReviewer(node.getIsReviewer());
					pmisEmployeeOfficeNodeDTO.setIsOfficeAdmin(node.getIsOfficeAdmin());
					pmisEmployeeOfficeNodeDTO.setIsAwardAdmin(node.getIsAwardAdmin());
					pmisEmployeeOfficeNodeDTO.setIsAttendanceAdmin(node.getIsAttendanceAdmin());
					pmisEmployeeOfficeNodeDTO.setIsAttendanceDataEntryOperator(node.getIsAttendanceDataEntryOperator());
					pmisEmployeeOfficeNodeDTO.setPmisOid(node.getPmisOid());
					pmisEmployeeOfficeNodeDTO.setEmployeeOfficeOid(node.getEmployeeOfficeOid());
					pmisEmployeeOfficeNodeDTO.setOid(node.getOid());
				}
			}
		}
		return result;
	}
    
	public PmisEmployeeOfficeNode findByPmisOidAndEmployeeOfficeOidAndRowStatus(String pmisOid, String employeeOfficeOid) {
		return repository.findByPmisOidAndEmployeeOfficeOidAndRowStatus(pmisOid, employeeOfficeOid, Constant.STATUS_ACTIVE);
	}
	
    public void parseJsonAndUpdateEmployeeOffice (JSONObject nodeObject) {
    	JSONArray nodes = nodeObject.getJSONArray("nodes");
    	for (int i = 0; i < nodes.length(); i++) {
    		JSONObject node = nodes.getJSONObject(i);
    		try {
    			EmployeeOfficeDTO dto = objectMapper.readValue(node.toString().getBytes(), EmployeeOfficeDTO.class);
    			employeeOfficeService.updateEmployeeOffice(dto, dto.getOid());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    public PmisEmployeeOfficeNode convertRequestedDTOtoEntity(PmisEmployeeOfficeNodeDTO dto, List<PmisEmployeeOfficeNode> existingNodeList) {
    	PmisEmployeeOfficeNode node = new PmisEmployeeOfficeNode();
		if (dto.getOid() == null) {
			node.setPmisOid(dto.getPmisOid());
			node.setEmployeeOfficeOid(dto.getEmployeeOfficeOid());
			
			node.setCreatedBy("System");
    		node.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		} else {
			node = existingNodeList.stream()
					  .filter(customer -> dto.getOid().equals(customer.getOid()))
					  .findAny()
					  .orElse(null);
		}
		
		return node;
    }
    
    public PmisEmployeeOfficeNodeDTO convertEntityToDTO(PmisEmployeeOfficeNode node) {
    	PmisEmployeeOfficeNodeDTO dto = new PmisEmployeeOfficeNodeDTO();
    	dto.setOid(node.getOid());
    	dto.setPmisOid(node.getPmisOid());
    	dto.setEmployeeOfficeOid(node.getEmployeeOfficeOid());
    	dto.setCreatedBy(node.getCreatedBy());
		dto.setCreatedOn(new Timestamp(node.getCreatedOn().getTime()));
		dto.setRowStatus(node.getRowStatus());
		return dto;
    }
    
    public PmisEmployeeOfficeNode convertDTOtoEntity(PmisEmployeeOfficeNodeDTO dto) {
    	PmisEmployeeOfficeNode node = new PmisEmployeeOfficeNode();
    	if (dto.getOid()!=null) {
    		node.setOid(dto.getOid());
		}
    	node.setPmisOid(dto.getPmisOid());
    	node.setEmployeeOfficeOid(dto.getEmployeeOfficeOid());
    	node.setIsAttendanceDataEntryOperator(!utilCharacter.noData(dto.getIsAttendanceDataEntryOperator())?dto.getIsAttendanceDataEntryOperator():Constant.NO);
		node.setIsAttendanceAdmin(!utilCharacter.noData(dto.getIsAttendanceAdmin())?dto.getIsAttendanceAdmin():Constant.NO);
		node.setIsApprover(!utilCharacter.noData(dto.getIsApprover())?dto.getIsApprover():Constant.NO);
		node.setIsReviewer(!utilCharacter.noData(dto.getIsReviewer())?dto.getIsReviewer():Constant.NO);
		node.setIsAwardAdmin(!utilCharacter.noData(dto.getIsAwardAdmin())?dto.getIsAwardAdmin():Constant.NO);
		node.setIsOfficeAdmin(!utilCharacter.noData(dto.getIsOfficeAdmin())?dto.getIsOfficeAdmin():Constant.NO);
		node.setConfig(dto.getConfig());
		node.setDataStatus(dto.getDataStatus());
		node.setCreatedBy("System");
		node.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		return node;
    }






}
