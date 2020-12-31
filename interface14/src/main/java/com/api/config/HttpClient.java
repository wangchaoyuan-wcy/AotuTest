package com.api.config;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.api.utils.ConfigFile;
import com.api.utils.Controller;
import com.api.utils.Login;
import com.tester.config.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static com.api.utils.InterfaceName.LOGIN;

@SuppressWarnings("all")
public class HttpClient {
    Logger logger= LoggerFactory.getLogger(getClass());

    //有参数
    public String Post(JSONObject jsonObject1,String testConfig,String userName) throws IOException {
        org.apache.http.client.HttpClient httpClient=new DefaultHttpClient();
        //HttpPost httpPost=new HttpPost(TestConfig.getInsert= ConfigFile.getUrl(GETINSERT));
        HttpPost httpPost=new HttpPost(testConfig);
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        Login login1=new Login();
        httpPost.setHeader("token",login1.login(userName));
        /*String result2 = HttpRequest.post(ConfigFile.getUrl(LOGIN))
                .body(Login.getLoginDataForWcy("crmadmin").toJSONString())
                .execute().body();
        token = JSONObject.parseObject(result2)
                .getJSONObject("data")
                .getJSONObject("userInfoVO")
                .getString("token");*/
        if (httpPost.getHeaders("token")!=null) {
        }
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity(jsonObject1.toString(),"utf-8");
        //将参数信息添加到方法中
        httpPost.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        HttpResponse response = null;
        //获取响应头
        response = TestConfig.defaultHttpClient.execute(httpPost);
        logger.info("返回头是"+response);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        logger.info("调用接口result:"+result);
        return result;
    }
    //登录方法
    public String Post1(JSONObject jsonObject1,String testConfig) throws IOException {
        org.apache.http.client.HttpClient httpClient=new DefaultHttpClient();
        //HttpPost httpPost=new HttpPost(TestConfig.getInsert= ConfigFile.getUrl(GETINSERT));
        HttpPost httpPost=new HttpPost(testConfig);
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity(jsonObject1.toString(),"utf-8");
        //将参数信息添加到方法中
        httpPost.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        HttpResponse response = null;
        //获取响应头
        response = TestConfig.defaultHttpClient.execute(httpPost);
        logger.info("返回头是"+response);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        logger.info("调用接口result:"+result);
        return result;
    }
    //无参数
    public String Post(String testConfig,String userName)throws IOException{
        org.apache.http.client.HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(testConfig);
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        Login login1=new Login();
        //httpPost.setHeader("token",login1.login(userName));
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity("utf-8");
        //将参数信息添加到方法中
        httpPost.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        HttpResponse response = null;
        //获取响应头
        response = TestConfig.defaultHttpClient.execute(httpPost);
        logger.info("返回头是"+response);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        logger.info("调用接口result:"+result);
        return result;
    }
}
