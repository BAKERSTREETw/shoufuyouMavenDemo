package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//支付异步回调
@SuppressWarnings("serial")
public class AfterCreditApplyNotifyServlet extends HttpServlet implements javax.servlet.Servlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        CreditApplyNotification notification = client.fetchCreditApplyNotification(request.getParameterMap());
        System.out.println(notification.getApplyId());
        System.out.println(notification.getStatus());
        System.out.println(notification.getCreditLineAmount());
        System.out.println(notification.getExtraParam());
        response.getWriter().write("SUCCESS");//返回 SUCCESS 表示异步通知接收成功
    }
}