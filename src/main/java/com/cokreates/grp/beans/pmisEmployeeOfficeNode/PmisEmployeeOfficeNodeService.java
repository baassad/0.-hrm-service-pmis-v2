package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeService;
import com.cokreates.grp.beans.employeeimport.EmployeeImportService;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOffice;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ObjectMapper objectMapper;

    public PmisEmployeeOfficeNodeService(RequestBuildingComponent<PmisEmployeeOfficeNodeDTO> requestBuildingComponent, DataServiceRestTemplateClient<PmisEmployeeOfficeNodeDTO, PmisEmployeeOfficeNode> dataServiceRestTemplateClient) {
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public List<PmisEmployeeOfficeNode> create(List<PmisEmployeeOfficeNode> nodes) {
        return repository.saveAll(nodes);
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

        return result;
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

}
