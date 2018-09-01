package com.yeepay.sdk.demo;

import com.yeepay.sdk.demo.DTO.TradeRefundDTO;
import com.yeepay.sdk.demo.util.YeepayClient;
import com.yeepay.sdk.demo.util.YeepayClientFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 交易退款接口
 */
public class TradeRefundServlet extends HttpServlet implements javax.servlet.Servlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        YeepayClient client = YeepayClientFactory.createSfyClient();
        TradeRefundDTO dto = new TradeRefundDTO();
        dto.setMerchantOrderId("5717046980");
        String refundId = Long.toString(System.currentTimeMillis()).substring(3);
        System.out.println("refundId="+refundId);
        dto.setMerchantRefundId(refundId);
        dto.setRefundAmount(1000);
        dto.setRefundReason("用户不想出行");
        Map<String,String> map = client.buildTradeRefund(dto);
        System.out.println(map);
    }
}
