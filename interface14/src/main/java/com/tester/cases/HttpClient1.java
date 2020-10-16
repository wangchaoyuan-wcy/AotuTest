package com.tester.cases;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import com.tester.config.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
@SuppressWarnings("all")
public class HttpClient1 {
    Logger logger= LoggerFactory.getLogger(getClass());
    //有参数
    public void Post(JSONObject jsonObject1,String testConfig) throws IOException {
        HttpClient httpClient=new DefaultHttpClient();
        //HttpPost httpPost=new HttpPost(TestConfig.getInsert= ConfigFile.getUrl(GETINSERT));
        HttpPost httpPost=new HttpPost(testConfig);
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("token","eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb20ueGRmLmJsaW5nIiwiYXVkIjoiY2xpZW50IiwidXNlcmNvZGUiOiJjaGFuZ3BpbiIsImV4cCI6MTYwMzEwNjg1MywiaWF0IjoxNjAyNTAyMDUzfQ.ebW1lQoNRJOdQLo_dd7bZFbuIsE4w9yIIl6rTwlOy0TzhmORvHE2c9Xs9ypm9rZEuGuVNu_rxGAmQjcR1W0zCA");
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
    }
    //无参数
    public void Post(String testConfig)throws IOException{
        HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(testConfig);
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("token","eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb20ueGRmLmJsaW5nIiwiYXVkIjoiY2xpZW50IiwidXNlcmNvZGUiOiJjaGFuZ3BpbiIsImV4cCI6MTYwMzEwNjg1MywiaWF0IjoxNjAyNTAyMDUzfQ.ebW1lQoNRJOdQLo_dd7bZFbuIsE4w9yIIl6rTwlOy0TzhmORvHE2c9Xs9ypm9rZEuGuVNu_rxGAmQjcR1W0zCA");
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
    }
}
