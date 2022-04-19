package com.hero.spa.cloud.client.register;

import com.hero.spa.cloud.client.annotation.SPACloudClient;
import com.hero.spa.cloud.utils.HttpUtil;

public class ReginsterCilent {


    public static void connciton(Class mainClass) {

        if (mainClass.getAnnotation(SPACloudClient.class) != null) {
            SPACloudClient spaCloudClient = (SPACloudClient) mainClass.getAnnotation(SPACloudClient.class);
            String serviceName = spaCloudClient.name();
            String serviceUrl = spaCloudClient.url();
            String port = spaCloudClient.port();
            ReginsterCilentContext.service=serviceUrl;
            HttpUtil.httpGet(serviceUrl + "/spa/reginster?name=" + serviceName+"&port="+port);
        }
    }
}
