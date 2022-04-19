package com.hero.spa.core.ioc.bean;

import com.hero.spa.core.ioc.context.BeansContext;
import com.hero.spa.core.ioc.context.ParamContext;
import com.hero.spa.core.ioc.context.Router;
import com.hero.spa.core.ioc.context.RouterContext;
import com.hero.spa.mvc.annotation.Controller;
import com.hero.spa.mvc.annotation.GET;
import com.hero.spa.mvc.annotation.POST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author yx
 */
public class ControllerInit implements Init {
    @Override
    public void init(List<Class<?>> clazz) {
        if (clazz==null){
            return;
        }
        try {
            for (Class aClass : clazz) {
                Controller controller = (Controller) aClass.getAnnotation(Controller.class);
                String controllerPath = controller.value().trim();
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    Annotation[] annotations = method.getAnnotations();
                    ParamContext.addParam(aClass, method);
                    List<Class> classes = Arrays.asList(new Class[]{GET.class, POST.class});
                    for (Class c : classes) {
                        Annotation annotation = method.getAnnotation(c);
                        if (annotation != null) {
                            Method value = c.getMethod("value");
                            value.setAccessible(true);
                            String path = controllerPath + value.invoke(annotation).toString();
                            method.setAccessible(true);
                            Router router = new Router();
                            router.setMethod(method);
                            router.setClazz(aClass);
                            router.setUrl(path);
                            router.setReqMethodName(c.getSimpleName());
                            RouterContext.addRouter(router);
                        }
                    }
                }
                BeansContext.addBean(aClass.getName(), aClass.newInstance());
            }
        } catch (Exception e) {
            System.out.print("初始化Controller异常");
        }
    }

    @Override
    public void init(Class clazz) {

    }
}
