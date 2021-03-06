package com.tester.cases;
import com.tester.model.InterfaceName;
import com.tester.config.TestConfig;
import com.tester.model.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
@SuppressWarnings("all")
public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取HttpClient对象")
    public void beforeTest(){
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
    }
    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);
    }
    @Test(description = "用户登陆失败接口")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);
    }
    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        // 创建http POST请求
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        //创建jsonobject的对象用于存放参数
        JSONObject param = new JSONObject();
        //把参数根据key，value的形式放到jsonobject的对象里
        param.put("userName",loginCase.getUserName());
        param.put("",loginCase.getPassword());
        //设置请求头信息 设置headerpassword
        post.setHeader("content-type","application/json");
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        //将参数信息添加到方法中
        post.setEntity(entity);
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        System.out.println("返回值为"+response);
        //获取响应结果
        //声明一个对象来进行响应结果的存储
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result+"...................................");
        //把cookie拿到放到静态属性中用于其他接口的使用
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }
}