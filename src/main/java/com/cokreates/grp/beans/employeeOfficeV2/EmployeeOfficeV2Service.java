package com.cokreates.grp.beans.employeeOfficeV2;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeService;
import com.cokreates.grp.beans.employeeimport.EmployeeImportService;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.components.UtilCharacter;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
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
    
    public EmployeeOfficeV2 createEmployeeOffice(EmployeeOfficeV2 requestedDTO) {
        return repository.save(requestedDTO);
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

	public List<EmployeeOfficeV2DTO> getEmployeeOfficeListByEmployeeOid(String employeeOid) {
		List<EmployeeOfficeV2DTO> result = new ArrayList<>();
		List<EmployeeOfficeV2> officeList = getEmployeeOfficeByEmployeeOid(employeeOid);
		officeList.stream().forEach(createdItem -> result.add(convertEntityToDTO(createdItem)));
		return result;
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByEmployeeOid(String employeeOid) {
		return repository.findAllByEmployeeOidAndRowStatus(employeeOid, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByEmployeeOidAndOfficeOid(String employeeOid, String officeOid) {
		return repository.findAllByEmployeeOidAndOfficeOidAndRowStatus(employeeOid, officeOid, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByOfficeOidAndOfficeUnitOid(String officeOid, String officeUnitOid) {
		return repository.findAllByOfficeOidAndOfficeUnitOidAndRowStatus(officeOid, officeUnitOid, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByOfficeOid(String officeOid) {
		return repository.findAllByOfficeOidAndRowStatus(officeOid, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByOfficeOidList(List<String> officeOidList) {
		return repository.findAllByOfficeOidInAndRowStatus(officeOidList, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByOfficeUnitOidList(List<String> OfficeUnitOidList) {
		return repository.findAllByOfficeUnitOidInAndRowStatus(OfficeUnitOidList, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getEmployeeOfficeByOfficeUnitPostOidList(List<String> OfficeUnitPostOidList) {
		return repository.findAllByOfficeUnitPostOidInAndRowStatus(OfficeUnitPostOidList, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2DTO> getEmployeeOfficeByEmployeeOidAndOfficeUnitPostOid(String employeeOid, String officeUnitPostOid) {
		List<EmployeeOfficeV2DTO> result = new ArrayList<>();
		List<EmployeeOfficeV2> officeList = repository.findAllByEmployeeOidAndOfficeUnitPostOidAndRowStatusAndStatus(employeeOid, officeUnitPostOid, Constant.STATUS_ACTIVE, "Active");
		officeList.stream().forEach(createdItem -> result.add(convertEntityToDTO(createdItem)));
		return result;
	}
	
	public EmployeeOfficeV2 findByEmployeeOidAndEmployeeOfficeOid(String employeeOid, String employeeOfficeOid) {
		return repository.findByEmployeeOidAndEmployeeOfficeOidAndRowStatus(employeeOid, employeeOfficeOid, Constant.STATUS_ACTIVE);
	}
	
	public List<EmployeeOfficeV2> getByEmployeeOidAndResponsibilityType(List<String> oidList) {
		return repository.findAllByEmployeeOidInAndResponsibilityTypeInAndRowStatus(oidList, Arrays.asList("", null), Constant.STATUS_ACTIVE);
	}

	private void prepareEmployeeOfficeV2Data(List<EmployeeOfficeV2> result, JSONObject employee) {
		String employeeOid = employee.getString("oid");
		log.info(">>> Preparing data for employee :: " + employeeOid);
		List<EmployeeOfficeV2> existingEmployee = repository.findAllByEmployeeOid(employeeOid);
		if (existingEmployee.size() == 0) {
			if (employee.has("employeeoffice")) {
				JSONArray employeeOffices = employee.getJSONArray("employeeoffice");
				if (null != employeeOffices && employeeOffices.length() > 0) {
					log.info(employeeOffices.length() + " Employee office found.");
					for (int j = 0; j < employeeOffices.length(); j++) {
						JSONObject employeeOffice = employeeOffices.getJSONObject(j);

						String employeeOfficeOid = (!isNull(employeeOffice, "oid"))?employeeOffice.getString("oid"):null;
						if(null == employeeOfficeOid) continue;
						String officeOid = !isNull(employeeOffice, "officeOid")?employeeOffice.getString("officeOid"):null;
						String officeUnitOid = !isNull(employeeOffice, "officeUnitOid")?employeeOffice.getString("officeUnitOid"):null;
						String officeUnitPostOid = !isNull(employeeOffice, "officeUnitPostOid")?employeeOffice.getString("officeUnitPostOid"):null;

						EmployeeOfficeV2 office = new EmployeeOfficeV2();
						office.setEmployeeOid(employeeOid);
						office.setEmployeeOfficeOid(employeeOfficeOid);
						office.setOfficeOid(officeOid);
						office.setOfficeUnitOid(officeUnitOid);
						office.setOfficeUnitPostOid(officeUnitPostOid);
						office.setEmploymentTypeOid(employeeOffice.getString("employmentTypeOid"));
						office.setJoiningDate(getDateString(employeeOffice, "joiningDate"));
						office.setLastOfficeDate(getDateString(employeeOffice, "lastOfficeDate"));
						office.setStatus(isNull(employeeOffice, "status") ? null : employeeOffice.getString("status"));
						office.setInchargeLabelBn(isNull(employeeOffice, "inchargeLabelBn") ? null : employeeOffice.getString("inchargeLabelBn"));
						office.setInchargeLabelEn(isNull(employeeOffice, "inchargeLabelEn") ? null : employeeOffice.getString("inchargeLabelEn"));
						office.setResponsibilityType(isNull(employeeOffice, "responsibilityType") ? null : employeeOffice.getString("responsibilityType"));
						office.setIsApprover(isNull(employeeOffice, "isApprover") ? Constant.NO : employeeOffice.getString("isApprover"));
						office.setIsReviewer(isNull(employeeOffice, "isReviewer") ? Constant.NO : employeeOffice.getString("isReviewer"));
						office.setIsAwardAdmin(isNull(employeeOffice, "isAwardAdmin") ? Constant.NO : employeeOffice.getString("isAwardAdmin"));
						office.setIsOfficeAdmin(isNull(employeeOffice, "isOfficeAdmin") ? Constant.NO : employeeOffice.getString("isOfficeAdmin"));
						office.setIsAttendanceAdmin(isNull(employeeOffice, "isAttendanceAdmin") ? Constant.NO : employeeOffice.getString("isAttendanceAdmin"));
						office.setIsAttendanceDataEntryOperator(isNull(employeeOffice, "isAttendanceDataEntryOperator") ? Constant.NO : employeeOffice.getString("isAttendanceDataEntryOperator"));
						office.setIsOfficeHead(isNull(employeeOffice, "isOfficeHead") ? Constant.NO : employeeOffice.getString("isOfficeHead"));
						office.setIsOfficeUnitHead(isNull(employeeOffice, "isOfficeUnitHead") ? Constant.NO : employeeOffice.getString("isOfficeUnitHead"));
						office.setDataStatus(isNull(employeeOffice, "dataStatus") ? null : employeeOffice.getString("dataStatus"));
						office.setCreatedBy("System");
						office.setCreatedOn(new Timestamp(System.currentTimeMillis()));
						result.add(office);
						log.info("Preparing data for employee-office :: " + employeeOfficeOid);
					}
				} else log.info("No office found for this employee. So, skip and go to next ...");
			} else log.info("No office found for this employee. So, skip and go to next ...");
		} else log.info("Already imported. Go to next ...");
	}

	
    public List<EmployeeOfficeV2DTO> syncEmployeeOffice() {

		List<EmployeeOfficeV2DTO> createdItemsDTO =new ArrayList<>();
    	List<EmployeeOfficeV2> officeDtoList = new ArrayList<>();

    	JSONArray employeeList = dataEmployeeService.getAllEmployeeList();
    	for (int i = 0; i < employeeList.length(); i++) {
    		JSONObject employee = employeeList.getJSONObject(i);
			prepareEmployeeOfficeV2Data(officeDtoList, employee);
		}

    	if(!officeDtoList.isEmpty()){
			List<EmployeeOfficeV2> createdItems = repository.saveAll(officeDtoList);
			if(!createdItems.isEmpty()) {
				createdItems.stream().forEach(createdItem -> createdItemsDTO.add(convertEntityToDTO(createdItem)));
				log.info("Import done !!");
			} else log.info("No new employee office has imported.");
		} else log.info("No employee office found to be imported.");
    	
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

    public List<EmployeeOfficeDTO> convertPmisEmployeeOfficeListToEmployeeOfficeList(List<EmployeeOfficeV2DTO> dtoList) {
		List<EmployeeOfficeDTO> resultList = new ArrayList<EmployeeOfficeDTO>();
		for (EmployeeOfficeV2DTO nodeDTO : dtoList) {
			EmployeeOfficeDTO officeDTO = convertPmisEmployeeOfficeToEmployeeOffice(nodeDTO);
            resultList.add(officeDTO);
		}
		
		return resultList;
	}
    
    public EmployeeOfficeDTO convertPmisEmployeeOfficeToEmployeeOffice(EmployeeOfficeV2DTO nodeDTO) {
    	EmployeeOfficeDTO officeDTO = new EmployeeOfficeDTO();
    	officeDTO.setOid(nodeDTO.getEmployeeOfficeOid());
    	officeDTO.setEmploymentTypeOid(nodeDTO.getEmploymentTypeOid());
    	officeDTO.setIsApprover(nodeDTO.getIsApprover());
    	officeDTO.setIsOfficeAdmin(nodeDTO.getIsOfficeAdmin());
    	officeDTO.setIsOfficeHead(nodeDTO.getIsOfficeHead());
    	officeDTO.setIsReviewer(nodeDTO.getIsReviewer());
        officeDTO.setJoiningDate(nodeDTO.getJoiningDate());
        officeDTO.setOfficeOid(nodeDTO.getOfficeOid());
        officeDTO.setOfficeUnitOid(nodeDTO.getOfficeUnitOid());
        officeDTO.setOfficeUnitPostOid(nodeDTO.getOfficeUnitPostOid());
        officeDTO.setStatus(nodeDTO.getStatus());
        officeDTO.setIsOfficeUnitHead(nodeDTO.getIsOfficeUnitHead());
        officeDTO.setResponsibilityType(nodeDTO.getResponsibilityType());
        officeDTO.setIsAttendanceDataEntryOperator(nodeDTO.getIsAttendanceDataEntryOperator());
        officeDTO.setIsAttendanceAdmin(nodeDTO.getIsAttendanceAdmin());
        officeDTO.setIsAwardAdmin(nodeDTO.getIsAwardAdmin());
        officeDTO.setNodeOid(nodeDTO.getNodeOid());
        officeDTO.setDataStatus(nodeDTO.getDataStatus());
        officeDTO.setRowStatus(nodeDTO.getRowStatus());
        officeDTO.setCreatedBy(nodeDTO.getCreatedBy());
        officeDTO.setUpdatedBy(nodeDTO.getUpdatedBy());
        officeDTO.setCreatedOn(nodeDTO.getCreatedOn()==null?null:new Timestamp(nodeDTO.getCreatedOn().getTime()));
        officeDTO.setUpdatedOn(nodeDTO.getUpdatedOn()==null?null:new Timestamp(nodeDTO.getUpdatedOn().getTime()));
        officeDTO.setInchargeLabelBn(nodeDTO.getInchargeLabelBn());
        officeDTO.setInchargeLabelEn(nodeDTO.getInchargeLabelEn());
        officeDTO.setLastOfficeDate(nodeDTO.getLastOfficeDate());
		
		return officeDTO;
	}
}



