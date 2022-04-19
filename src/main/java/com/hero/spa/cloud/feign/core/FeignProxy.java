package com.hero.spa.cloud.feign.core;

import com.alibaba.fastjson.JSONObject;
import com.hero.spa.cloud.client.register.ReginsterCilentContext;
import com.hero.spa.cloud.feign.annotation.FeignClient;
import com.hero.spa.cloud.utils.HttpUtil;
import com.hero.spa.core.exception.SpaException;
import com.hero.spa.mvc.annotation.GET;
import com.hero.spa.mvc.annotation.POST;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yinx
 */
public class FeignProxy<T> implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        GET get = method.getAnnotation(GET.class);
        POST post = method.getAnnotation(POST.class);
        FeignClient feignClient = method.getAnnotation(FeignClient.class);
        String name = feignClient.value();
        if (get != null) {
            JSONObject o = HttpUtil.httpGet(ReginsterCilentContext.service + "/spa/getUrl?name=" + name);
            String url = o.getString("data");
            JSONObject o1 = HttpUtil.httpGet(url + get.value() + "?name=" + name);
            return o1.getString("data");
        }
        throw new SpaException("远程调用异常！");
    }
}
