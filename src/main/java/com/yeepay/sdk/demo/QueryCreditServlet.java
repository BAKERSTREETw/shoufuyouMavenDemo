package com.yeepay.sdk.demo;

import com.yeepay.sdk.demo.util.YeepayClient;
import com.yeepay.sdk.demo.util.YeepayClientFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class QueryCreditServlet extends HttpServlet implements javax.servlet.Servlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        YeepayClient client = YeepayClientFactory.createSfyClient();
        //查询接口，apply_id open_id二选一
        Map<String,String> map = client.queryCredit("1535620311820");
        System.out.println(map);
    }
}
