package com.hero.spa.cloud.service.register;

import com.hero.spa.mvc.annotation.Controller;
import com.hero.spa.mvc.annotation.GET;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller("/spa/")
public class RegisterService {

    @GET("reginster")
    public int registerClient(HttpServletRequest request,String name,String port){
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setServiceName(name);
        serviceInfo.setServiceUrl("http://"+request.getRemoteAddr()+":"+port);
        ServicesContext.addService(serviceInfo);
        return 1;
    }

    @GET("getUrl")
    public String getUrl(String name){
        return ServicesContext.getSerivce(name).get(0).getServiceUrl();
    }

    @GET("getServices")
    public Map<String, List<ServiceInfo>> getServices(){
        return ServicesContext.getServices();
    }



}
