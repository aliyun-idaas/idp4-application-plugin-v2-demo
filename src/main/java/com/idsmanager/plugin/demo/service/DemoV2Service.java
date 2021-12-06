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
package com.idsmanager.plugin.demo.service;

import com.idsmanager.plugin.demo.service.dto.SsoDto;

/**
 * 2021/9/29
 *
 * @author ilanyu
 */
public interface DemoV2Service {
    SsoDto startSSO(String enterpriseApplicationUuid, String linkedUuid);
}
