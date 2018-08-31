package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//支付同步回调
@SuppressWarnings("serial")
public class AfterCreditApplyReturnServlet extends HttpServlet implements javax.servlet.Servlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        CreditApplyNotification notification = client.fetchCreditApplyNotification(request.getParameterMap());
        System.out.println(notification.getApplyId());
        System.out.println(notification.getStatus());
        System.out.println(notification.getCreditLineAmount());
        System.out.println(notification.getExtraParam());
    }
}
