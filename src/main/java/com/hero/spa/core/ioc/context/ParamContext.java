package com.hero.spa.core.ioc.context;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class ParamContext {
    private static final ConcurrentHashMap<Class, ConcurrentHashMap<Method, String[]>> paramMap = new ConcurrentHashMap();

    public static void addParam(Class cs, Method method) throws Exception {
        String[] paramNames = getParamNames(method);
        if (method.getParameterTypes().length == paramNames.length) {
            if (paramMap.containsKey(cs)) {
                ConcurrentHashMap<Method, String[]> concurrentHashMap = paramMap.get(cs);
                concurrentHashMap.put(method, paramNames);
                paramMap.put(cs, concurrentHashMap);
            } else {
                ConcurrentHashMap<Method, String[]> concurrentHashMap = new ConcurrentHashMap<>();
                concurrentHashMap.put(method, paramNames);
                paramMap.put(cs, concurrentHashMap);
            }
        } else {
            throw new Exception("参数异常");
        }
    }


    public static String[] getParamByClass(Class clazz, Method method)  {
       return  paramMap.get(clazz).get(method);
    }

    private static String[] getParamNames(Method method) {
        try {
            //重点
            Class<?> clazz = method.getDeclaringClass();
            ClassPool pool = ClassPool.getDefault();
            CtClass clz = pool.get(clazz.getName());
            clz.freeze();
            clz.defrost();
            CtClass[] params = new CtClass[method.getParameterTypes().length];
            for (int i = 0; i < method.getParameterTypes().length; i++) {
                ClassClassPath classPath = new ClassClassPath(method.getParameterTypes()[i]);
                pool.insertClassPath(classPath);
                params[i] = pool.getCtClass(method.getParameterTypes()[i].getName());
            }
            CtMethod cm = clz.getDeclaredMethod(method.getName(), params);
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                    .getAttribute(LocalVariableAttribute.tag);
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            String[] paramNames = new String[cm.getParameterTypes().length];
            for (int i = 0; i < attr.tableLength(); i++) {
                if (attr.index(i) >= pos && attr.index(i) < paramNames.length + pos) {
                    paramNames[attr.index(i) - pos] = attr.variableName(i);
                }
            }
            return paramNames;
        } catch (NotFoundException e) {
            return new String[]{};
        }
    }


}
