package com.cokreates.grp.beans.employeeOfficeV2;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeService;
import com.cokreates.grp.beans.employeeimport.EmployeeImportService;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.components.UtilCharacter;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    @Autowired
    DataEmployeeService dataEmployeeService;
    
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
    	List<EmployeeOfficeV2> existingOfficeList = repository.findAllByEmployeeOidAndRowStatus(pmisOid, Constant.STATUS_ACTIVE);
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

	public List<EmployeeOfficeV2DTO> getEmployeeOfficeByEmployeeOid(String employeeOid) {
		List<EmployeeOfficeV2DTO> result = new ArrayList<>();
		List<EmployeeOfficeV2> officeList = repository.findAllByEmployeeOidAndRowStatus(employeeOid, Constant.STATUS_ACTIVE);
		officeList.stream().forEach(createdItem -> result.add(convertEntityToDTO(createdItem)));
		return result;
	}
    
	public EmployeeOfficeV2 findByPmisOidAndEmployeeOfficeOidAndRowStatus(String employeeOid, String employeeOfficeOid) {
		return repository.findByEmployeeOidAndEmployeeOfficeOidAndRowStatus(employeeOid, employeeOfficeOid, Constant.STATUS_ACTIVE);
	}
	
    public List<EmployeeOfficeV2DTO> syncEmployeeOffice() {
    	List<EmployeeOfficeV2> officeDtoList = new ArrayList<EmployeeOfficeV2>();
    	JSONArray employeeList = dataEmployeeService.getAllEmployeeList();
    	for (int i = 0; i < employeeList.length(); i++) {
    		JSONObject employee = employeeList.getJSONObject(i);
    		String employeeOid = employee.getString("oid");
			JSONArray employeeOffices = employee.getJSONArray("employeeoffice");
			for (int j = 0; j < employeeOffices.length(); j++) {
				JSONObject employeeOffice = employeeOffices.getJSONObject(j);
				EmployeeOfficeV2 office = new EmployeeOfficeV2();
		    	office.setEmployeeOid(employeeOid);
		    	office.setEmployeeOfficeOid(employeeOffice.getString("oid"));
		    	office.setOfficeOid(employeeOffice.getString("officeOid"));
		    	office.setOfficeUnitOid(employeeOffice.getString("officeUnitOid"));
		    	office.setOfficeUnitPostOid(employeeOffice.getString("officeUnitPostOid"));
		    	office.setEmploymentTypeOid(employeeOffice.getString("employmentTypeOid"));
			    office.setJoiningDate(getDateString(employeeOffice, "joiningDate"));
			    office.setLastOfficeDate(getDateString(employeeOffice, "lastOfficeDate"));
			    office.setStatus(isNull(employeeOffice, "status")?null:employeeOffice.getString("status"));
			    office.setInchargeLabelBn(isNull(employeeOffice, "inchargeLabelBn")?null:employeeOffice.getString("inchargeLabelBn"));
			    office.setInchargeLabelEn(isNull(employeeOffice, "inchargeLabelEn")?null:employeeOffice.getString("inchargeLabelEn"));
			    office.setResponsibilityType(isNull(employeeOffice, "responsibilityType")?null:employeeOffice.getString("responsibilityType"));
				office.setIsApprover(isNull(employeeOffice, "isApprover")?Constant.NO:employeeOffice.getString("isApprover"));
				office.setIsReviewer(isNull(employeeOffice, "isReviewer")?Constant.NO:employeeOffice.getString("isReviewer"));
				office.setIsAwardAdmin(isNull(employeeOffice, "isAwardAdmin")?Constant.NO:employeeOffice.getString("isAwardAdmin"));
				office.setIsOfficeAdmin(isNull(employeeOffice, "isOfficeAdmin")?Constant.NO:employeeOffice.getString("isOfficeAdmin"));
				office.setIsAttendanceAdmin(isNull(employeeOffice, "isAttendanceAdmin")?Constant.NO:employeeOffice.getString("isAttendanceAdmin"));
				office.setIsAttendanceDataEntryOperator(isNull(employeeOffice, "isAttendanceDataEntryOperator")?Constant.NO:employeeOffice.getString("isAttendanceDataEntryOperator"));
				office.setIsOfficeHead(isNull(employeeOffice, "isOfficeHead")?Constant.NO:employeeOffice.getString("isOfficeHead"));
				office.setIsOfficeUnitHead(isNull(employeeOffice, "isOfficeUnitHead")?Constant.NO:employeeOffice.getString("isOfficeUnitHead"));
				office.setDataStatus(isNull(employeeOffice, "dataStatus")?null:employeeOffice.getString("dataStatus"));
				office.setCreatedBy("System");
				office.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				officeDtoList.add(office);
			}
		}
    	
    	List<EmployeeOfficeV2> createdItems = repository.saveAll(officeDtoList);
    	List<EmployeeOfficeV2DTO> createdItemsDTO =new ArrayList<EmployeeOfficeV2DTO>();
    	createdItems.stream().forEach(createdItem -> createdItemsDTO.add(convertEntityToDTO(createdItem)));
        return createdItemsDTO;
    }
    
    public boolean isNull(JSONObject object, String key) {
    	if ((object.has(key) && !object.isNull(key))) {
			return false;
		}
    	return true;
    }
    
    public String getDateString(JSONObject object, String key) {
    	if (object.isNull(key) || object.get(key)==null || object.get(key)=="null") {
		    return null;
		} else {
			try {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				Date date = inputFormat.parse(object.getString(key));
				return String.valueOf(date.getTime());
			} catch (ParseException e) {
				long date = object.getLong(key);
				return String.valueOf(date);
			} catch (Exception e) {
				return null;
			}
		}
    }
    
    public EmployeeOfficeV2 convertRequestedDTOtoEntity(EmployeeOfficeV2DTO dto, List<EmployeeOfficeV2> existingOfficeList) {
    	EmployeeOfficeV2 office = new EmployeeOfficeV2();
		if (dto.getOid() == null) {
			office.setEmployeeOid(dto.getEmployeeOid());
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
    	dto.setEmployeeOid(office.getEmployeeOid());
    	dto.setEmployeeOfficeOid(office.getEmployeeOfficeOid());
    	dto.setCreatedBy(office.getCreatedBy());
		dto.setCreatedOn(new Timestamp(office.getCreatedOn().getTime()));
		dto.setRowStatus(office.getRowStatus());
		dto.setOfficeOid(office.getOfficeOid());
    	dto.setOfficeUnitOid(office.getOfficeUnitOid());
    	dto.setOfficeUnitPostOid(office.getOfficeUnitPostOid());
    	dto.setEmploymentTypeOid(office.getEmploymentTypeOid());
	    dto.setInchargeLabelBn(office.getInchargeLabelBn());
	    dto.setInchargeLabelEn(office.getInchargeLabelEn());
	    dto.setJoiningDate(office.getJoiningDate());
	    dto.setLastOfficeDate(office.getLastOfficeDate());
	    dto.setStatus(office.getStatus());
	    dto.setResponsibilityType(office.getResponsibilityType());
		dto.setIsApprover(office.getIsApprover());
		dto.setIsReviewer(office.getIsReviewer());
		dto.setIsAwardAdmin(office.getIsAwardAdmin());
		dto.setIsOfficeAdmin(office.getIsOfficeAdmin());
		dto.setIsAttendanceAdmin(office.getIsAttendanceAdmin());
		dto.setIsAttendanceDataEntryOperator(office.getIsAttendanceDataEntryOperator());
		dto.setIsOfficeHead(office.getIsOfficeHead());
		dto.setIsOfficeUnitHead(office.getIsOfficeUnitHead());
		return dto;
    }
    
    public EmployeeOfficeV2 convertDTOtoEntity(EmployeeOfficeV2DTO dto) {
    	EmployeeOfficeV2 office = new EmployeeOfficeV2();
    	if (dto.getOid()!=null) {
    		office.setOid(dto.getOid());
		}
    	office.setEmployeeOid(dto.getEmployeeOid());
    	office.setEmployeeOfficeOid(dto.getEmployeeOfficeOid());
    	office.setOfficeOid(dto.getOfficeOid());
    	office.setOfficeUnitOid(dto.getOfficeUnitOid());
    	office.setOfficeUnitPostOid(dto.getOfficeUnitPostOid());
    	office.setEmploymentTypeOid(dto.getEmploymentTypeOid());
	    office.setInchargeLabelBn(dto.getInchargeLabelBn());
	    office.setInchargeLabelEn(dto.getInchargeLabelEn());
	    office.setJoiningDate(dto.getJoiningDate());
	    office.setLastOfficeDate(dto.getLastOfficeDate());
	    office.setStatus(dto.getStatus());
	    office.setResponsibilityType(dto.getResponsibilityType());
		office.setIsApprover(!utilCharacter.noData(dto.getIsApprover())?dto.getIsApprover():Constant.NO);
		office.setIsReviewer(!utilCharacter.noData(dto.getIsReviewer())?dto.getIsReviewer():Constant.NO);
		office.setIsAwardAdmin(!utilCharacter.noData(dto.getIsAwardAdmin())?dto.getIsAwardAdmin():Constant.NO);
		office.setIsOfficeAdmin(!utilCharacter.noData(dto.getIsOfficeAdmin())?dto.getIsOfficeAdmin():Constant.NO);
		office.setIsAttendanceAdmin(!utilCharacter.noData(dto.getIsAttendanceAdmin())?dto.getIsAttendanceAdmin():Constant.NO);
		office.setIsAttendanceDataEntryOperator(!utilCharacter.noData(dto.getIsAttendanceDataEntryOperator())?dto.getIsAttendanceDataEntryOperator():Constant.NO);
		office.setIsOfficeHead(!utilCharacter.noData(dto.getIsOfficeHead())?dto.getIsOfficeHead():Constant.NO);
		office.setIsOfficeUnitHead(!utilCharacter.noData(dto.getIsOfficeUnitHead())?dto.getIsOfficeUnitHead():Constant.NO);
		office.setConfig(dto.getConfig());
		office.setDataStatus(dto.getDataStatus());
		office.setCreatedBy("System");
		office.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		return office;
    }

}



