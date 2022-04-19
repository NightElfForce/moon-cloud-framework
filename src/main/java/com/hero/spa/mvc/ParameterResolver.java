package com.hero.spa.mvc;

import com.hero.spa.core.ioc.context.Router;

import javax.servlet.http.HttpServletRequest;
/**
 * @author yinx
 */
public interface ParameterResolver {

    Object[] resolver(HttpServletRequest request,Router router);


}
