package com.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import static com.api.utils.InterfaceName.LOGIN;
public class Login {

    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    Controller controller = (Controller) context.getBean("controller");

    JSONObject jsonObject=new JSONObject();
    public String login(String userName) throws IOException {
        jsonObject=Login.getLoginDataForWcy(userName);
        String response=controller.getHttpClient().Post1(jsonObject,ConfigFile.getUrl(LOGIN));
        JSONObject responseJson = JSONObject.parseObject(response);
        JSONObject jsonObject1=responseJson.getJSONObject("data");
        JSONObject jsonObject2=jsonObject1.getJSONObject("userInfoVO");
        String token=jsonObject2.getString("token");
        return token;
    }
    public static JSONObject getLoginDataForWcy(String userName) {
        if (userName=="crmadmin") {
            JSONObject jsonObjectWCY = JSON.parseObject("{\"username\":\"WJ2tod/VMtdwffjX+ykjPA==\",\"password\":\"GXuFOM+isDtc4Mcv8AVaPA==\",\"platformCode\":\"0007\"}");
            return jsonObjectWCY;
        }
        return null;
    }
    
    /*public static void main(String[] args) throws IOException {
        DataDao dataDao=new DataDao();
        JSONObject jsonObject=JSON.parseObject(dataDao.selectTestDataListById("1").get(0).getRequest());
        Login login =new Login();
        String string=login.login(jsonObject);
        System.out.println(string);
    }*/


}
