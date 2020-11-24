package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.common.EmployeeAndJsonOidHolderRequestBodyDTO;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class BankService extends MasterService<BankDTO, Bank> {

    @Autowired
    PmisRepository pmisRepository;

    @Autowired
    ObjectMapper objectMapper;

    public BankService(RequestBuildingComponent<BankDTO> requestBuildingComponent,
                       DataServiceRestTemplateClient<BankDTO, Bank> dataServiceRestTemplateClient) {
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "bank"));
        this.setType("List");
    }

    public List<BankDTO> getBankAccountByOid(EmployeeAndJsonOidHolderRequestBodyDTO requestBody) {

        List<String> bankDetailsJson = pmisRepository.getBankAccountDetails(Arrays.asList(requestBody.getEmployeeOid()));

        System.out.println("Bank Details : " + bankDetailsJson.get(0));
        try {
            if (bankDetailsJson.size() > 0) {
                List<BankDTO> bankDTOList = objectMapper.readValue(bankDetailsJson.get(0), new TypeReference<List<BankDTO>>() {
                });

                for (BankDTO bank : bankDTOList) {
                    if (bank.getOid().equals(requestBody.getJsonRowOid())) {
                        return Collections.singletonList(bank);
                    }
                }

            }
        } catch (Exception e) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("ব্যাংক তথ্য বিদ্যমান নেই");
        }

        return new ArrayList<>();

    }

    @Override
    public Class getDtoClass() {
        return BankDTO.class;
    }

    @Override
    public Class getEntityClass() {
        return Bank.class;
    }
}