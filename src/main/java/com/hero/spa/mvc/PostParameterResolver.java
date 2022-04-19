package com.hero.spa.mvc;

import com.alibaba.fastjson.JSON;
import com.hero.spa.core.exception.SpaException;
import com.hero.spa.core.ioc.context.Router;
import com.hero.spa.mvc.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author yinx
 */
public class PostParameterResolver implements ParameterResolver{
    @Override
    public Object[] resolver(HttpServletRequest request, Router router) {
        List list = new ArrayList();
        Map<String,Object> params = new HashMap<>();
        BufferedReader br;
        try {
            br = request.getReader();
            String str, wholeStr = "";
            while ((str = br.readLine()) != null) {
                wholeStr += str;
            }
            if (StringUtils.isNotEmpty(wholeStr)) {
                params = JSON.parseObject(wholeStr, Map.class);
            }
        } catch (IOException e) {
            System.out.print("post请求中body解析异常！");
        }

        Map<String, String[]> parameter = request.getParameterMap();
        for (Parameter param : router.getMethod().getParameters()) {
            if (param.getAnnotation(RequestBody.class) != null) {
                list.add(params);
            } else {
                if(parameter.get(param.getName())==null){
                    throw new SpaException("参数解析异常，请检查url参数");
                }
                list.add(parameter.get(param.getName())[0]);
            }

        }
        return list.toArray();
    }
}
