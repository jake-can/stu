package com.jt.common.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

@Service
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired(required=false)
    private CloseableHttpClient httpClient;

    @Autowired(required=false)
    private RequestConfig requestConfig;

    /**
     * 实现httpclientpost方法
     * 1,需要设定url参数
     * 2，map<string,string> 使用map数据结构实现参数封装
     * 3，设置字符集编码utf-8
     * 难点:POST如何传递参数?????
     * 	Post请求将参数转化为二进制字节流信息进行数据传输.
     * 	一般form表单提交试用POST提交
     * 	回顾: get http://addUser?id=1&name=tom

     */
    public String doPost(String url,Map<String,String> params,String charset){
    	
    	String result = null;
    	//1,判断字符集编码是否为null，如果参数为空则默认为utf-8
    	if(StringUtils.isEmpty(charset)){
    		charset="utf-8";
    	}
    	//获取请求对象的实体
    	HttpPost httpPost = new HttpPost(url);
    	httpPost.setConfig(requestConfig);
    	//3,判断用户是否传递参数
    	try {
    		if(params!=null){
    			List<NameValuePair> parameters = new ArrayList<>();
    			for(Map.Entry<String, String> entry:params.entrySet()){
    				BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
    				parameters.add(nameValuePair);
    			}
    			//实现参数封装
    			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charset);
    			httpPost.setEntity(formEntity);
    		}
    		//4,发起post请求
    		CloseableHttpResponse response = httpClient.execute(httpPost);
    		if(response.getStatusLine().getStatusCode()==200){
    			result = EntityUtils.toString(response.getEntity(),charset);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    public String doPost(String url){
    	return doPost(url, null, null);
    }
    
    public String doPost(String url,Map<String,String> params){
    	return doPost(url, params, null);
    }
    
    public String doGet(String url,Map<String,String> params,String charset) {
    	String result = null;
    	//1,判断字符集编码是否为空
    	if(StringUtils.isEmpty(url)){
    		url = "utf-8";
    	}
    	try {
    		if(params !=null){
    			URIBuilder builder = new URIBuilder(url);
    			for (Map.Entry<String, String> param : params.entrySet()) {
    				builder.addParameter(param.getKey(),param.getValue());
    			}
    			url = builder.build().toString();
    		}
			//定义请求类型
    		HttpGet httpGet = new HttpGet(url);
    		httpGet.setConfig(requestConfig);
    		//发起请求
    		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    		if(httpResponse.getStatusLine().getStatusCode()==200){
    			result = EntityUtils.toString(httpResponse.getEntity(),charset);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    public String doGet(String url){
    	return doGet(url, null, null);
    }
    
    public String doGet(String url,Map<String,String> params){
    	return doGet(url, params, null);
    }
}
