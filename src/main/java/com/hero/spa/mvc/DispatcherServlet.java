package com.hero.spa.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yinx
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dispatcher dispatcher = new Dispatcher(request,response);
        dispatcher.analysis();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dispatcher dispatcher = new Dispatcher(request,response);
        dispatcher.analysis();
    }

}
