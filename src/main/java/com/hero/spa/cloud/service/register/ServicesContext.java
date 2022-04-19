package com.hero.spa.cloud.service.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class ServicesContext {
    private static Map<String, List<ServiceInfo>> services = new ConcurrentHashMap<>(32);

    public static Map<String, List<ServiceInfo>> addService(ServiceInfo serviceInfo) {
        List<ServiceInfo> list = services.get(serviceInfo.getServiceName());
        if(list==null||list.size()==0){
            List<ServiceInfo> service = new ArrayList();
            service.add(serviceInfo);
            services.put(serviceInfo.getServiceName(),service);
        }else {
            for (ServiceInfo info : list) {
                if (!info.getServiceUrl().equals(serviceInfo.getServiceUrl())) {
                    list.add(serviceInfo);
                    break;
                }
            }
        }
        return services;
    }

    public static Map<String, List<ServiceInfo>> getServices() {
        return services;
    }

    public static List<ServiceInfo> getSerivce(String name) {
        return services.get(name);
    }
}
