/*
 * Copyright (c) 2016 BeiJing JZYT Technology Co. Ltd
 * www.idsmanager.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BeiJing JZYT Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with BeiJing JZYT Technology Co. Ltd.
 */
package com.idsmanager.plugin.demo.service.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021/9/29
 *
 * @author ilanyu
 */
public class SsoDto implements Serializable {

    private boolean success = true;

    private String message;

    private String loginUrl;

    private String idpUsername;

    private String applicationName;

    public SsoDto() {
    }

    public Map<String, Object> attributes() {
        Map<String, Object> map = new HashMap<>();
        map.put("idpUsername", idpUsername);
        map.put("applicationName", applicationName);
        return map;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getIdpUsername() {
        return idpUsername;
    }

    public void setIdpUsername(String idpUsername) {
        this.idpUsername = idpUsername;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
