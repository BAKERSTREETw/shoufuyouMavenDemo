/**
 * 
 */
package com.yeepay.sdk.demo.util.customSSLSocketFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author Technology Support Group
 * 
 * 类名：CustomHostnameVerifier
 * 功能：自定义CustomHostnameVerifier工具类
 * 详细：工具类 不验证hostname
 * 日期：2017
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究，只是提供一个参考。
 *
 */
public class CustomHostnameVerifier implements HostnameVerifier{

	@Override
	public boolean verify(String hostname, SSLSession session) {
		// TODO Auto-generated method stub
		return true;
	}

}
