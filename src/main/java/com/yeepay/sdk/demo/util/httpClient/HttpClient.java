/**
 * 
 */
package com.yeepay.sdk.demo.util.httpClient;

import java.util.List;

import com.yeepay.sdk.demo.util.customSSLSocketFactory.CustomHostnameVerifier;
import com.yeepay.sdk.demo.util.customSSLSocketFactory.CustomSSLSocketFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;  

/**
 * @author Masapay Technology Support Group
 * 
 * 类名：HttpMethod
 * 功能：对httpClient进行简单的封装，实现HTTP协议POST方法
 * 详细：提供HTTP协议POST(TLS/SSL)方法的实现
 * 日期：2017
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究，只是提供一个参考。
 *
 */
public class HttpClient {
	
	public String HttpPost(List <NameValuePair> nvps, String targetURI) throws Exception{
		String responseText = "";
		SSLContext sslcontext = CustomSSLSocketFactory.createIgnoreVerifySSL();
		//DefaultHostnameVerifier hnv = new DefaultHostnameVerifier();
		
        CustomSSLSocketFactory sslsf = new CustomSSLSocketFactory(sslcontext,new String[] { "TLSv1.2" },null,new CustomHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try{
			HttpPost httpPost = new HttpPost(targetURI);
			UrlEncodedFormEntity urlEncodeFormEntity = new UrlEncodedFormEntity(nvps);
			httpPost.setEntity(urlEncodeFormEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			//新增
//			Header[] fileNames = response.getHeaders("fileName");
//			System.out.println("文件名称为:"+fileNames.toString());

			try {
	            HttpEntity entity = response.getEntity();
	            if( null != entity){
	            	responseText = EntityUtils.toString(entity, "UTF-8");
	            }
	            EntityUtils.consume(entity);
	            return responseText;
	        } finally {
	            response.close();
	        }
		} finally {
			httpclient.close();
		}
	}
}
