package com.hero.spa.mvc;

import com.hero.spa.core.ioc.context.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author yinx
 */
public class Dispatcher {
    HttpServletRequest request;
    HttpServletResponse response;

    public Dispatcher(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public void analysis(){
        HandlerMapping mapping = new HandlerMapping();
        Router router = mapping.mapping(request);
        ParameterResolver getParameterResolver = null;
        if("GET".equals(request.getMethod())){
            getParameterResolver = new GetParameterResolver();
        }
        if("POST".equals(request.getMethod())){
            getParameterResolver = new PostParameterResolver();
        }
        Object[] args =  getParameterResolver.resolver(request, router);
        Handler handler = new Handler();
        Object result = handler.start(request,router,args);
        JsonReslutResolver reslutResolver = new JsonReslutResolver();
        reslutResolver.resolver(response,result);
    }

}
