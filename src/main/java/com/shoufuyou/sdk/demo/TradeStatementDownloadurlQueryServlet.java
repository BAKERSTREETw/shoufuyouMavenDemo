package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//退款
@SuppressWarnings("serial")
public class TradeStatementDownloadurlQueryServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        TradeStatementDownloadurlQueryRequest tsdqr = new TradeStatementDownloadurlQueryRequest();
        tsdqr.setStatementDate("2016-08-22");
        TradeStatementDownloadurlQueryResponse res = client.sendTradeStatementDownloadurlQueryRequest(tsdqr);
        System.out.println(res.getStatementDownloadUrl());
    }
}
