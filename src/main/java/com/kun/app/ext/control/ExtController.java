/**
 * Program  : ExtController.java
 * Author   : songkun
 * Create   : 2016年1月11日 下午3:55:20
 *
 * Copyright 2016 songkun. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of songkun.  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with songkun.
 *
 */

package com.kun.app.ext.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kun.common.web.control.BaseController;

/**
 * 扩展
 *
 * @author songkun
 * @version 1.0.0
 * @date 2016年1月11日 下午3:55:20
 */
@Controller("extController")
@RequestMapping("/ext")
public class ExtController extends BaseController<Object> {

}
