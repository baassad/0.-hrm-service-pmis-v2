package com.cokreates.grp.beans.notification.pushNotification;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.util.List;

@Data
public class DataNotificationResponseDTO extends MasterDTO {
        private String oid;
        private String createdBy;
        private String updatedBy;
        private String rowStatus;

        private String notificationContent;
        private String requestOid;
        private String tag;
        private String token;
        private String url;
        private String callbackApi;

        private List<NotificationRecipientDTO> recipients;
}
