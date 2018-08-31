package com.yeepay.sdk.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateOrderReturnServlet extends HttpServlet implements javax.servlet.Servlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
           Map map = request.getParameterMap();
           System.out.println(map);
        }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
            doGet(req,resp);
    }
}
