package com.cokreates.grp.util.components;


import com.cokreates.core.RequestModel;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class RequestContextComponent {

    public RequestModel requestDTO;

}
