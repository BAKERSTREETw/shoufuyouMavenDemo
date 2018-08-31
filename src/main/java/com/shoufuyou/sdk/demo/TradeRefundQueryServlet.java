package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//退款查询
@SuppressWarnings("serial")
public class TradeRefundQueryServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        TradeRefundQueryRequest trqr = new TradeRefundQueryRequest();
        trqr.setMerchantRefundId("2016092602322239");
        TradeRefundResponse res = client.sendTradeRefundQueryRequest(trqr);
        System.out.println(res.getMerchantOrderId());
        System.out.println(res.getMerchantRefundId());
        System.out.println(res.getRefundTotalAmount());
        System.out.println(res.getRefundAmount());
    }
}
