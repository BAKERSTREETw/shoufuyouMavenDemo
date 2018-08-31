package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//退款
@SuppressWarnings("serial")
public class TradeRefundServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        TradeRefundRequest trr = new TradeRefundRequest();
        trr.setMerchantOrderId("20160330153535");
        trr.setMerchantRefundId("20160824112811111");
        trr.setRefundAmount(100);
        trr.setRefundReason("{退款理由}");
        TradeRefundResponse res = client.sendTradeRefundRequest(trr);
        System.out.println(res.getMerchantOrderId());
        System.out.println(res.getMerchantRefundId());
        System.out.println(res.getRefundTotalAmount());
        System.out.println(res.getRefundAmount());
    }
}
