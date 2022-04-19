package com.hero.spa.mvc;

import com.alibaba.fastjson.JSONObject;
import com.hero.spa.core.util.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yinx
 */
public class JsonReslutResolver implements ResultResolver{
    @Override
    public void resolver(HttpServletResponse response, Object result) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(R.ok(result));
        writer.write(jsonObject.toJSONString());
    }
}
