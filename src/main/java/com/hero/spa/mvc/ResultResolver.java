package com.hero.spa.mvc;

import javax.servlet.http.HttpServletResponse;
/**
 * @author yinx
 */
public interface ResultResolver {

    void resolver(HttpServletResponse response, Object result);

}
