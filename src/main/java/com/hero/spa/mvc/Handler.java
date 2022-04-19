package com.hero.spa.mvc;

import com.hero.spa.core.ioc.context.BeansContext;
import com.hero.spa.core.ioc.context.Router;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yinx
 */
public class Handler {

    protected Object start(HttpServletRequest request, Router router, Object[] agrs) {
        Object o = BeansContext.getBean(router.getClazz().getName());
        Object result = null;
        try {
            result = router.getMethod().invoke(o, agrs);
        } catch (Exception e) {
            System.out.print("执行对象{}--方法{}异常，参数信息---{}"+o.getClass().getSimpleName()+router.getMethod().getName()+agrs);
        }
        return result;
    }
}
