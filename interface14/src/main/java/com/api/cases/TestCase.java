package com.api.cases;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.config.HttpClient;
import com.api.db.mysql.daos.DataDao;
import com.api.utils.ConfigFile;
import com.api.utils.Controller;
import com.api.utils.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

import static com.api.utils.InterfaceName.LOGIN;
import static com.api.utils.InterfaceName.STUDYREPORT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TestCase {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    Controller controller = (Controller) context.getBean("controller");
    JSONObject jsonObject=new JSONObject();



    @BeforeTest
    public void getToken() {

    }

    @Test
    public void test() throws IOException{
        DataDao dataDao=new DataDao();
        JSONObject jsonObject=JSON.parseObject(dataDao.selectTestDataListById("2").get(0).getRequest());
        JSONObject responseData=JSON.parseObject(dataDao.selectTestDataListById("2").get(0).getResponse());
        String response =controller.getHttpClient().Post(jsonObject, ConfigFile.getUrl(STUDYREPORT),"crmadmin");
        JSONObject responseJson = JSONObject.parseObject(response);
        String code =responseJson.getString("code");
        String expectedCode=responseData.getString("code");
        String msg = responseJson.getString("msg");
        String expectedMsg=responseData.getString("msg");
        //Assert.assertEquals(code,expectedValue,"正确");
        assertThat("校验返回值code错误，验证code",code,is(expectedCode));
        assertThat("校验返回值msg错误，验证msg",msg,is(expectedMsg));
    }
    @Test
    public void test1() throws IOException {
    }
}
