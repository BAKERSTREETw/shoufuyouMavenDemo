package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//交易查询
@SuppressWarnings("serial")
public class TradeQueryServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        TradeQueryRequest tqr = new TradeQueryRequest();
        tqr.setMerchantOrderId("2026031123202013589563");
        tqr.setTradeNumber("201603111804042489");
        TradeQueryResponse res = client.sendTradeQueryRequest(tqr);
        System.out.println(res.getMerchantOrderId());
        System.out.println(res.getTradeNumber());
        System.out.println(res.getTradeStatus());
        System.out.println(res.getOrderAmount());
    }
}
