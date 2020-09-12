package com.cokreates.grp.beans.employee;

import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.cokreates.grp.util.request.EmptyBodyDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class SwitchService {

    @Autowired
    private HeaderUtilComponent headerUtilComponent;

    private String notificationEnabled ;
    private String emailEnabled ;
    private String tokenValidationEnabled ;

    public SwitchService() {
        this.notificationEnabled = "Yes";
        this.emailEnabled = "No";
        this.tokenValidationEnabled = "Yes";
    }

    public void setTokenValidationEnabled(String status) {

        this.tokenValidationEnabled = status;

    }

    public void setEmailEnabled(String status) {

        this.emailEnabled = status;

    }

    public void setNotificationEnabled(String status) {

        this.notificationEnabled = status;

    }


}