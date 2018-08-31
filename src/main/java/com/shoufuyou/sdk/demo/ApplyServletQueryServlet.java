package com.shoufuyou.sdk.demo;

import com.shoufuyou.sdk.SfyClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

//查询信用额度
public class ApplyServletQueryServlet extends HttpServlet implements javax.servlet.Servlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("applyId",UUID.randomUUID().toString());
        String s = client.buildRequestHtml(hashMap," http://apitest.shoufuyou.com/service");
        System.out.print(s);
    }


}
