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
package com.idsmanager.plugin.demo.service.business;

import com.idsmanager.idp.core.IDPApplicationService;
import com.idsmanager.idp.core.PluginBeanProvider;
import com.idsmanager.idp.core.dto.IDPAccountLinkingDto;
import com.idsmanager.idp.core.dto.IDPApplicationPluginStoreDataDto;
import com.idsmanager.plugin.demo.infrastructure.RC4;
import com.idsmanager.plugin.demo.service.dto.SsoDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

/**
 * 2021/9/29
 *
 * @author ilanyu
 */
public class SsoHandler {

    private final transient IDPApplicationService idpApplicationService = PluginBeanProvider.getBean(IDPApplicationService.class);

    private final String enterpriseApplicationUuid;
    private final String linkedUuid;

    public SsoHandler(String enterpriseApplicationUuid, String linkedUuid) {
        this.enterpriseApplicationUuid = enterpriseApplicationUuid;
        this.linkedUuid = linkedUuid;
    }

    public SsoDto handle() {
        final SsoDto ssoDto = new SsoDto();
        final IDPApplicationPluginStoreDataDto urlDto = idpApplicationService.findStoreDataByApplicationUuid(enterpriseApplicationUuid, "loginUrl");
        if (urlDto == null) {
            ssoDto.setSuccess(false);
            ssoDto.setMessage("管理员登录地址配置错误");
            return ssoDto;
        }

        final IDPApplicationPluginStoreDataDto keyDto = idpApplicationService.findStoreDataByApplicationUuid(enterpriseApplicationUuid, "key");
        if (keyDto == null) {
            ssoDto.setSuccess(false);
            ssoDto.setMessage("管理员登录key配置错误");
            return ssoDto;
        }

        final IDPAccountLinkingDto idpAccountLinkingDto = idpApplicationService.getIdpAccountLinkingDto(enterpriseApplicationUuid, linkedUuid);
        if (idpAccountLinkingDto == null) {
            ssoDto.setSuccess(false);
            ssoDto.setMessage("请先进行账户关联");
            return ssoDto;
        }

        final String applicationUsername = idpAccountLinkingDto.getApplicationUsername();
        final RC4 rc4 = new RC4(keyDto.getValue().getBytes(StandardCharsets.UTF_8));
        final String encryptApplicationUsername = Base64.encodeBase64String(rc4.encrypt(applicationUsername.getBytes(StandardCharsets.UTF_8)));
        final String loginUrl = UriComponentsBuilder.fromUriString(urlDto.getValue()).queryParam("username", encryptApplicationUsername).build().encode().toString();

        ssoDto.setLoginUrl(loginUrl);
        return ssoDto;
    }
}
