package com.shoufuyou.sdk.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import com.shoufuyou.sdk.*;

//支付
@SuppressWarnings("serial")
public class CreditApplyServlet extends HttpServlet implements javax.servlet.Servlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        CreditApplyRequest creditApplyRequest = new CreditApplyRequest();
        //商户申请id
        creditApplyRequest.setApplyId(UUID.randomUUID().toString());
        //身份证号
        creditApplyRequest.setIdCardNumber("450100200007048933");
        //姓名
        creditApplyRequest.setName("王五");
        //手机号
        creditApplyRequest.setMobile("13226677969");
        //端类型
        creditApplyRequest.setSourceType("wap");
        //额外参数 （选填）
        creditApplyRequest.setExtraParam("测试数据");
        //用户ip
//        creditApplyRequest.setApplyId("172.18.163.119");

//        creditApplyRequest.setReturnUrl("http://localhost:8080/shoufuyou-sdk-demo/after-credit-apply-return");
        creditApplyRequest.setReturnUrl("http://qac36k.natappfree.cc/after-credit-apply-return");
//        creditApplyRequest.setNotifyUrl("http://hostname:8080/shoufuyou-sdk-demo/after-credit-apply-notify");
        creditApplyRequest.setNotifyUrl("http://qac36k.natappfree.cc/after-credit-apply-notify");
        String s=client.buildCreditApplyRequestHtml(creditApplyRequest);
        response.getWriter().write(s);
    }
}