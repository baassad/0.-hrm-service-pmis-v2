package com.cokreates.grp.beans.employeeOfficeV2;

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
public class EmployeeOfficeV2Service extends MasterService<EmployeeOfficeV2DTO, EmployeeOfficeV2> {

    @Autowired
    EmployeeOfficeV2Repository repository;

    @Autowired
    EmployeeImportService employeeImportService;
    
    @Autowired
    EmployeeOfficeService employeeOfficeService;

	@Autowired
	UtilCharacter utilCharacter;
    
    @Autowired
    ObjectMapper objectMapper;

    public EmployeeOfficeV2Service(RequestBuildingComponent<EmployeeOfficeV2DTO> requestBuildingComponent, DataServiceRestTemplateClient<EmployeeOfficeV2DTO, EmployeeOfficeV2> dataServiceRestTemplateClient) {
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public EmployeeOfficeV2 update(EmployeeOfficeV2DTO dto) {
    	EmployeeOfficeV2 requestedDTO = convertDTOtoEntity(dto);
    	EmployeeOfficeV2 createdItem = repository.save(requestedDTO);
        return createdItem;
    }
    
    public List<EmployeeOfficeV2DTO> create(List<EmployeeOfficeV2DTO> dtos) {
    	List<EmployeeOfficeV2> requestedDTOs = new ArrayList<EmployeeOfficeV2>();
    	dtos.stream().forEach(dto -> requestedDTOs.add(convertDTOtoEntity(dto)));
    	List<EmployeeOfficeV2> createdItems = repository.saveAll(requestedDTOs);
    	List<EmployeeOfficeV2DTO> createdItemsDTO =new ArrayList<EmployeeOfficeV2DTO>();
    	createdItems.stream().forEach(createdItem -> createdItemsDTO.add(convertEntityToDTO(createdItem)));
        return createdItemsDTO;
    }
    
    public List<EmployeeOfficeV2DTO> updateAll(String pmisOid, List<EmployeeOfficeV2DTO> inputDTOs) {
    	List<EmployeeOfficeV2> existingOfficeList = repository.findAllByPmisOidAndRowStatus(pmisOid, Constant.STATUS_ACTIVE);
    	List<EmployeeOfficeV2> requestedOffices = new ArrayList<EmployeeOfficeV2>();
    	try {
    		inputDTOs.stream().forEach(dto -> requestedOffices.add(convertRequestedDTOtoEntity(dto, existingOfficeList)));
    	
    		existingOfficeList.removeAll(requestedOffices);
    		existingOfficeList.forEach(node -> node.setRowStatus(Constant.STATUS_DELETE));
    		requestedOffices.addAll(existingOfficeList);
	    	List<EmployeeOfficeV2> createdItems = repository.saveAll(requestedOffices);
	    	List<EmployeeOfficeV2DTO> createdItemsDTO =new ArrayList<EmployeeOfficeV2DTO>();
	    	createdItems.stream().forEach(createdItem -> createdItemsDTO.add(convertEntityToDTO(createdItem)));
	        return createdItemsDTO;
    	} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	public List<EmployeeOfficeV2DTO> getPmisEmployeeOfficeNodes(String pmisOid) {

		List<EmployeeOfficeV2> officeList = repository.findAllByPmisOidAndRowStatus(pmisOid, Constant.STATUS_ACTIVE);

		Set<String> employeeOfficeOidSet = new HashSet<>();
		if (!officeList.isEmpty())
			employeeOfficeOidSet = officeList.stream().map(node -> node.getEmployeeOfficeOid()).collect(Collectors.toSet());

		List<EmployeeOffice> employeeOfficeList = new ArrayList<>();
		if (employeeOfficeOidSet.size() > 0)
			employeeOfficeList = employeeImportService.getEmployeeOfficeListByEmployeeOfficeOidSet(employeeOfficeOidSet);

		List<EmployeeOfficeV2DTO> result = new ArrayList<>();
		if (!employeeOfficeList.isEmpty()) {
			result = employeeOfficeList.stream().map(employeeOffice -> {
				EmployeeOfficeV2DTO nodeDTO = getModelMapper().map(employeeOffice, EmployeeOfficeV2DTO.class);
				nodeDTO.setEmploymentTypeOid(employeeOffice.getEmploymentType().getOid());
				return nodeDTO;
			}).collect(Collectors.toList());
		}
		
		for (EmployeeOfficeV2DTO pmisEmployeeOfficeNodeDTO : result) {
			String employeeOfficeOid = pmisEmployeeOfficeNodeDTO.getOid();
			for (EmployeeOfficeV2 office : officeList) {
				String employeeOfficeOidFromNode = office.getEmployeeOfficeOid();
				if (null != employeeOfficeOid && !employeeOfficeOid.isEmpty()
						&& null != employeeOfficeOidFromNode && !employeeOfficeOidFromNode.isEmpty()
						&& employeeOfficeOid.equals(employeeOfficeOidFromNode)) {
					pmisEmployeeOfficeNodeDTO.setIsApprover(office.getIsApprover());
					pmisEmployeeOfficeNodeDTO.setIsReviewer(office.getIsReviewer());
					pmisEmployeeOfficeNodeDTO.setIsOfficeAdmin(office.getIsOfficeAdmin());
					pmisEmployeeOfficeNodeDTO.setIsAwardAdmin(office.getIsAwardAdmin());
					pmisEmployeeOfficeNodeDTO.setIsAttendanceAdmin(office.getIsAttendanceAdmin());
					pmisEmployeeOfficeNodeDTO.setIsAttendanceDataEntryOperator(office.getIsAttendanceDataEntryOperator());
					pmisEmployeeOfficeNodeDTO.setPmisOid(office.getPmisOid());
					pmisEmployeeOfficeNodeDTO.setEmployeeOfficeOid(office.getEmployeeOfficeOid());
					pmisEmployeeOfficeNodeDTO.setOid(office.getOid());
				}
			}
		}
		return result;
	}
    
	public EmployeeOfficeV2 findByPmisOidAndEmployeeOfficeOidAndRowStatus(String pmisOid, String employeeOfficeOid) {
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
    
    public EmployeeOfficeV2 convertRequestedDTOtoEntity(EmployeeOfficeV2DTO dto, List<EmployeeOfficeV2> existingOfficeList) {
    	EmployeeOfficeV2 office = new EmployeeOfficeV2();
		if (dto.getOid() == null) {
			office.setPmisOid(dto.getPmisOid());
			office.setEmployeeOfficeOid(dto.getEmployeeOfficeOid());
			
			office.setCreatedBy("System");
			office.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		} else {
			office = existingOfficeList.stream()
					  .filter(customer -> dto.getOid().equals(customer.getOid()))
					  .findAny()
					  .orElse(null);
		}
		
		return office;
    }
    
    public EmployeeOfficeV2DTO convertEntityToDTO(EmployeeOfficeV2 office) {
    	EmployeeOfficeV2DTO dto = new EmployeeOfficeV2DTO();
    	dto.setOid(office.getOid());
    	dto.setPmisOid(office.getPmisOid());
    	dto.setEmployeeOfficeOid(office.getEmployeeOfficeOid());
    	dto.setCreatedBy(office.getCreatedBy());
		dto.setCreatedOn(new Timestamp(office.getCreatedOn().getTime()));
		dto.setRowStatus(office.getRowStatus());
		return dto;
    }
    
    public EmployeeOfficeV2 convertDTOtoEntity(EmployeeOfficeV2DTO dto) {
    	EmployeeOfficeV2 office = new EmployeeOfficeV2();
    	if (dto.getOid()!=null) {
    		office.setOid(dto.getOid());
		}
    	office.setPmisOid(dto.getPmisOid());
    	office.setEmployeeOfficeOid(dto.getEmployeeOfficeOid());
    	office.setIsAttendanceDataEntryOperator(!utilCharacter.noData(dto.getIsAttendanceDataEntryOperator())?dto.getIsAttendanceDataEntryOperator():Constant.NO);
		office.setIsAttendanceAdmin(!utilCharacter.noData(dto.getIsAttendanceAdmin())?dto.getIsAttendanceAdmin():Constant.NO);
		office.setIsApprover(!utilCharacter.noData(dto.getIsApprover())?dto.getIsApprover():Constant.NO);
		office.setIsReviewer(!utilCharacter.noData(dto.getIsReviewer())?dto.getIsReviewer():Constant.NO);
		office.setIsAwardAdmin(!utilCharacter.noData(dto.getIsAwardAdmin())?dto.getIsAwardAdmin():Constant.NO);
		office.setIsOfficeAdmin(!utilCharacter.noData(dto.getIsOfficeAdmin())?dto.getIsOfficeAdmin():Constant.NO);
		office.setConfig(dto.getConfig());
		office.setDataStatus(dto.getDataStatus());
		office.setCreatedBy("System");
		office.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		return office;
    }






}
