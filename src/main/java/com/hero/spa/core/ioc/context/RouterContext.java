package com.hero.spa.core.ioc.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class RouterContext {

    private final static Map<String, Map<String, Router>> router = new ConcurrentHashMap<>();

    public static void addRouter(Router routerInfo) {
        if (routerInfo != null) {
            String url = routerInfo.getUrl();
            Map<String, Router> stringRouterInfoMap = router.get(routerInfo.getReqMethodName());
            if (stringRouterInfoMap == null) {
                stringRouterInfoMap = new ConcurrentHashMap<>();
            }
            stringRouterInfoMap.put(url, routerInfo);
            router.put(routerInfo.getReqMethodName(), stringRouterInfoMap);
        }
    }

    public static Map<String, Map<String, Router>> getAll() {
        return router;
    }

    public static Map<String, Router> getRouter(String method) {
        return router.get(method);
    }

}
