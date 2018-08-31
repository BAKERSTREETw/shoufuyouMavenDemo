package com.yeepay.sdk.demo;

import com.yeepay.sdk.demo.DTO.TradeRequestDTO;
import com.yeepay.sdk.demo.util.YeepayClient;
import com.yeepay.sdk.demo.util.YeepayClientFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//白条支付
public class TradeServlet extends HttpServlet implements javax.servlet.Servlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            YeepayClient client = YeepayClientFactory.createSfyClient();
            TradeRequestDTO tradereq = new TradeRequestDTO();
            tradereq.setMerchantOrderId("5628667429");
//            tradereq.setTradeNumber("201808291606057807");
            tradereq.setTradeNumber("201808301851080443");
            tradereq.setPeriods(12);
            tradereq.setIp("127.0.0.1");
            Map<String,String> map = client.crateTrade(tradereq);
            System.out.print(map);
    }
}
