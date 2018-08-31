package com.yeepay.sdk.demo;

import com.yeepay.sdk.demo.DTO.AutoRefundDTO;
import com.yeepay.sdk.demo.util.YeepayClient;
import com.yeepay.sdk.demo.util.YeepayClientFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自动还款，绑卡 有问题
 * 本接口为credit.apply和rest.create在返回绑卡时候进行调用  不清楚什么时候调用
 */
//TODO
public class AutoRefundServlet extends HttpServlet implements javax.servlet.Servlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        YeepayClient client = YeepayClientFactory.createSfyClient();
        AutoRefundDTO dto = new AutoRefundDTO();
//        dto.setTradeNumber("");
        dto.setName("");
        dto.setBankCard("");
        dto.setIdCardNumber("");
        dto.setMobile("");
        client.buildAutoRefund(dto);
    }
}
