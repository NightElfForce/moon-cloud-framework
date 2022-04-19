package com.hero.spa.core.server;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
/**
 * @author yx
 */
public class SPAServer {

    private  Integer port ;

    public SPAServer(Integer port){
        this.port = port;
    }

    public void run() throws Exception{
        System.out.println(getHello());
        Tomcat t=new Tomcat();
        Connector conn=new Connector("HTTP/1.1");
        conn.setPort(port);
        t.getService().addConnector(conn);
        Context ctx=t.addContext("/",null);
        Tomcat.addServlet( ctx,"DispatcherServlet","com.hero.spa.mvc.DispatcherServlet");
        ctx.addServletMappingDecoded("/*", "DispatcherServlet");
        t.start();
        t.getServer().await();
    }

    private String getHello() {
        return " ________           ________        ________     \n" +
                "|\\   ____\\         |\\   __  \\      |\\   __  \\    \n" +
                "\\ \\  \\___|_        \\ \\  \\|\\  \\     \\ \\  \\|\\  \\   \n" +
                " \\ \\_____  \\        \\ \\   ____\\     \\ \\   __  \\  \n" +
                "  \\|____|\\  \\        \\ \\  \\___|      \\ \\  \\ \\  \\ \n" +
                "    ____\\_\\  \\        \\ \\__\\          \\ \\__\\ \\__\\\n" +
                "   |\\_________\\        \\|__|           \\|__|\\|__|\n" +
                "   \\|_________|                                  \n";
    }

}
