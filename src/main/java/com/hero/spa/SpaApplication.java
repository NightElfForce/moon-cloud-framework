package com.hero.spa;

import com.hero.spa.cloud.client.register.ReginsterCilent;
import com.hero.spa.core.ioc.IocInit;
import com.hero.spa.core.server.SPAServer;
import com.hero.spa.orm.connectionPool.ConnectionPoolImpl;

/**
 * @author yinx
 */
public class SpaApplication {
    public static void run(Class mainClass, Integer port) throws Exception{
        IocInit.iocInit(mainClass);
//        ConnectionPoolImpl.init();
        ReginsterCilent.connciton(mainClass);
        new SPAServer(port).run();
    }
}
