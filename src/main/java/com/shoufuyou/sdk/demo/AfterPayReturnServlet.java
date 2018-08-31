package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//支付同步回调
@SuppressWarnings("serial")
public class AfterPayReturnServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        PayNotification notification = client.fetchPayNotification(request.getParameterMap());
        System.out.println(notification.getMerchantOrderId());
        System.out.println(notification.getTradeNumber());
        System.out.println(notification.getTradeStatus());
        System.out.println(notification.getOrderAmount());
        System.out.println(notification.getBillUrl());
    }
}
