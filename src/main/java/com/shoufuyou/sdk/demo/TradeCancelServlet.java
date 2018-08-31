package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//交易查询
@SuppressWarnings("serial")
public class TradeCancelServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        TradeCancelRequest tcr = new TradeCancelRequest();
        tcr.setMerchantOrderId("2026031123202013589563");
        TradeCancelResponse res = client.sendTradeCancelRequest(tcr);
        System.out.println(res.getMerchantOrderId());
    }
}
