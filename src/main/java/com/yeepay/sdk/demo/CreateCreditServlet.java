package com.yeepay.sdk.demo;

import com.yeepay.sdk.demo.DTO.CreditDTO;
import com.yeepay.sdk.demo.util.Message;
import com.yeepay.sdk.demo.util.YeepayClient;
import com.yeepay.sdk.demo.util.YeepayClientFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateCreditServlet extends HttpServlet implements javax.servlet.Servlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        YeepayClient client = YeepayClientFactory.createSfyClient();
        CreditDTO dto = new CreditDTO();
        //暂时用时间戳，商户这边自己生成的id
        dto.setApplyId(Long.toString(System.currentTimeMillis()));

        dto.setIdCardNumber(Message.idCard);
        dto.setName(Message.name);
        dto.setMobile(Message.phone);
        dto.setSourceType("wap");
//        dto.setExtraParam("");
        dto.setIp("127.0.0.1");
        Map<String,String> map = client.createCredit(dto);
        System.out.println(map);
    }
}
