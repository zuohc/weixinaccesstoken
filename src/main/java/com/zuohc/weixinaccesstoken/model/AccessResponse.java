package com.zuohc.weixinaccesstoken.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AccessResponse {
    private String access_token;
    private String expires_in;
    private String errcode;
    private String errmsg;

}
