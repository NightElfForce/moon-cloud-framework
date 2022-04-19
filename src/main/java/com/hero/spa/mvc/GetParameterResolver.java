package com.hero.spa.mvc;

import com.hero.spa.core.exception.SpaException;
import com.hero.spa.core.ioc.context.ParamContext;
import com.hero.spa.core.ioc.context.Router;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author yinx
 */
public class GetParameterResolver implements ParameterResolver{
    @Override
    public Object[] resolver(HttpServletRequest request, Router router) {
        String[] paramNames = ParamContext.getParamByClass(router.getClazz(), router.getMethod());
        List<Object> value = new ArrayList();
        Map<String, String[]> parameter = request.getParameterMap();

        for (Parameter param : router.getMethod().getParameters()) {

            if(HttpServletRequest.class==param.getType()){
                value.add(request);
            }else {
                if(parameter.get(param.getName())==null){
                    throw new SpaException("参数解析异常，请检查url参数");
                }
                value.add(parameter.get(param.getName())[0]);

            }
        }
        return value.toArray();
    }
}
