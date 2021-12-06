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
package com.idsmanager.plugin.demo.web.controller;

import com.idsmanager.idp.core.utils.STRender;
import com.idsmanager.micro.commons.web.filter.RIDHolder;
import com.idsmanager.plugin.demo.service.DemoV2Service;
import com.idsmanager.plugin.demo.service.dto.SsoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.idsmanager.idp.core.IDPApplicationConstants.API_VERSION;
import static com.idsmanager.plugin.demo.DemoV2Application.APPLICATION_ID;

/**
 * 2021/9/29
 *
 * @author ilanyu
 */
@RestController
@RequestMapping("/api/bff/" + API_VERSION + "/enduser/" + APPLICATION_ID + "/")
public class DemoV2SSOController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoV2SSOController.class);

    @Autowired
    private DemoV2Service demoV2Service;

    @RequestMapping(value = "sso_{enterpriseApplicationUuid}", method = RequestMethod.GET)
    public void startSSO(@PathVariable("enterpriseApplicationUuid") String enterpriseApplicationUuid,
                         String linkedUuid,
                         HttpServletResponse response, HttpServletRequest request) throws Exception {

        LOG.debug("[{}]- Start DemoV2 SSO, enterpriseApplicationUuid: {}, linkedUuid: {}", RIDHolder.id(), enterpriseApplicationUuid, linkedUuid);
        SsoDto ssoDto = demoV2Service.startSSO(enterpriseApplicationUuid, linkedUuid);

        if (!ssoDto.isSuccess()) {
            final Map<String, Object> attributes = ssoDto.attributes();
            attributes.put("contextPath", request.getContextPath());
            STRender stRender = new STRender(APPLICATION_ID + "/template/linking_error.html", attributes);
            response.setContentType("text/html");
            final String responseText = stRender.render();
            response.getWriter().write(responseText);
        } else {
            response.sendRedirect(ssoDto.getLoginUrl());
        }
    }
}
