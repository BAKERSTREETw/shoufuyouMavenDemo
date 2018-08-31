package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//利率查询
@SuppressWarnings("serial")
public class TradeRateQueryServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        TradeRateQueryResponse res = client.sendTradeRateQueryRequest();
        System.out.println(res.getInstallmentRate3());
        System.out.println(res.getInstallmentRate6());
        System.out.println(res.getInstallmentRate12());
        System.out.println(res.getInstallmentDiscount());
    }
}
