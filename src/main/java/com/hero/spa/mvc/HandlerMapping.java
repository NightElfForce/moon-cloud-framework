package com.hero.spa.mvc;

import com.hero.spa.core.exception.SpaException;
import com.hero.spa.core.ioc.context.Router;
import com.hero.spa.core.ioc.context.RouterContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yinx
 */
public class HandlerMapping {
        public Router mapping(HttpServletRequest request){
            String url = request.getRequestURI();
            System.out.print("开始匹配url---{}"+url);
            Map<String, Router> routers = RouterContext.getRouter(request.getMethod());
            Router router = routers.get(url);
            System.out.print("匹配url{}成功"+url);
            return router;
        }

}
