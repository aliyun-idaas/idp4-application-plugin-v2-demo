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
package com.idsmanager.plugin.demo;

import com.idsmanager.idp.core.IDPApplicationAdapter;
import com.idsmanager.idp.core.domain.DeviceType;
import com.idsmanager.micro.commons.web.filter.RIDHolder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.idsmanager.idp.core.IDPApplicationConstants.API_VERSION;

/**
 * 2021/9/29
 *
 * @author ilanyu
 */
public class DemoV2Application extends IDPApplicationAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(DemoV2Application.class);

    public static final List<DeviceType> DEVICE_TYPES = Arrays.asList(DeviceType.WEB);

    public static final String NAME = "DemoV2";

    public static final String APPLICATION_ID = "plugin_v2_demo";

    private byte[] logoAsBytes;

    public DemoV2Application() {
    }

    @Override
    public boolean plugin() {
        return true;
    }

    @Override
    public long pluginVersion() {
        return 1;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean custom() {
        return true;
    }

    @Override
    public String applicationId() {
        return APPLICATION_ID;
    }

    @Override
    public List<DeviceType> supportedDeviceTypes() {
        return DEVICE_TYPES;
    }

    @Override
    public String description() {
        return "应用插件V2 Demo示例";
    }

    @Override
    public List<String> tags() {
        List<String> tags = new ArrayList<>();
        tags.add("Demo");
        return Collections.unmodifiableList(tags);
    }

    @Override
    public boolean developing() {
        return false;
    }

    @Override
    public byte[] logoAsBytes() throws IOException {
        if (this.logoAsBytes == null) {
            this.logoAsBytes = IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream(APPLICATION_ID + ".png"));
            LOG.debug("[{}]- Initialed " + this.getClass().getName() + " logo from: " + APPLICATION_ID + ".png", RIDHolder.id());
        }
        return this.logoAsBytes;
    }

    @Override
    public String plusApplicationUrl(String enterpriseId) {
        return "/api/bff/" + API_VERSION + "/application/" + APPLICATION_ID + "/plus?enterpriseId=" + enterpriseId;
    }

    @Override
    public String enableApplicationUrl(String enterpriseApplicationUuid, boolean enable) {
        return "/api/bff/" + API_VERSION + "/application/" + APPLICATION_ID + "/" + (enable ? "enable" : "disable") + "?applicationUuid=" + enterpriseApplicationUuid;
    }

    @Override
    public String detailsUrl(String enterpriseApplicationUuid) {
        return "/api/bff/" + API_VERSION + "/application/" + APPLICATION_ID + "/details?applicationUuid=" + enterpriseApplicationUuid;
    }

    @Override
    public String modifyApplicationUrl(String enterpriseApplicationUuid) {
        return "/api/bff/" + API_VERSION + "/application/" + APPLICATION_ID + "/modify?applicationUuid=" + enterpriseApplicationUuid;
    }

    @Override
    public String startSSOUrl(String enterpriseApplicationUuid) {
        return "/enduser/application/" + APPLICATION_ID + "/sso_" + enterpriseApplicationUuid;
    }

    @Override
    public String archiveApplicationUrl(String enterpriseApplicationUuid, boolean archived) {
        return "/api/bff/" + API_VERSION + "/application/" + APPLICATION_ID + "/" + (archived ? "archived" : "restore") + "?applicationUuid=" + enterpriseApplicationUuid;
    }

    @Override
    public String removeApplicationUrl(String enterpriseApplicationUuid) {
        return "/api/bff/" + API_VERSION + "/application/" + APPLICATION_ID + "/remove?applicationUuid=" + enterpriseApplicationUuid;
    }

    @Override
    public String bffSSOUrl(String enterpriseApplicationUuid) {
        return "/api/bff/" + API_VERSION + "/enduser/" + APPLICATION_ID + "/sso_" + enterpriseApplicationUuid;
    }
}
