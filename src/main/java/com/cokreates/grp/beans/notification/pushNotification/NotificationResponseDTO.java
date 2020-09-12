package com.cokreates.grp.beans.notification.pushNotification;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NotificationResponseDTO extends MasterDTO {
    private int status;
    private int totalCount;
    private String message;
    private String errors;
    private String options;
    private Timestamp timestamp;

    DataNotificationResponseDTO data;
}
