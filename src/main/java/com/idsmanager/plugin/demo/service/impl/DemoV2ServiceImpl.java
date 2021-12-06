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
package com.idsmanager.plugin.demo.service.impl;

import com.idsmanager.plugin.demo.service.DemoV2Service;
import com.idsmanager.plugin.demo.service.business.SsoHandler;
import com.idsmanager.plugin.demo.service.dto.SsoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 2021/9/29
 *
 * @author ilanyu
 */
@Service
public class DemoV2ServiceImpl implements DemoV2Service {

    @Override
    @Transactional
    public SsoDto startSSO(String enterpriseApplicationUuid, String linkedUuid) {
        SsoHandler handler = new SsoHandler(enterpriseApplicationUuid, linkedUuid);
        return handler.handle();
    }
}
