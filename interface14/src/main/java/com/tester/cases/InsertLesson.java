package com.tester.cases;
import com.tester.config.TestConfig;
import com.tester.model.*;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.tester.model.InterfaceName.*;
import static java.lang.Thread.sleep;
@SuppressWarnings("all")

public class InsertLesson {
    Lesson lesson =new Lesson();
    List<Integer> list1=new ArrayList();
    @Test(description = "创建课时信息")
    public void addLesson() throws IOException, InterruptedException {
        List list=new ArrayList();
        for(int i=0;i<=1;i++){
            //创建接口对象
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(TestConfig.getInsert= ConfigFile.getUrl(GETINSERT));
            JSONObject jsonObject=new JSONObject();
            //jsonObject = JSON.parseObject("{\"name\":\"测试4\",\"lessonNum\":1,\"isTestCourse\":0,\"whenLong\":30,\"vocabulary\":\"\",\"term\":40,\"level\":20,\"courseType\":20,\"remark\":\"测试1\",\"lessonWay\":2,\"materialType\":\"10\",\"subjectName\":\"英语\",\"subjectCode\":10,\"coveUrl\":\"\",\"aiId\":243,\"lessonType\":2}");
            //把课时对象转换为JSONObject对象
            if (i==0){
                lesson.setName("测试9");
            }
            if (i==1){
                lesson.setName("测试10");
            }
            JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(lesson);
            System.out.println(jsonObject1);
            //设置请求头信息 设置header
            httpPost.setHeader("content-type","application/json");
            httpPost.setHeader("token","eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb20ueGRmLmJsaW5nIiwiYXVkIjoiY2xpZW50IiwidXNlcmNvZGUiOiJjcm1hZG1pbiIsImV4cCI6MTYwMTg2ODc2NywiaWF0IjoxNjAxMjYzOTY3fQ.bB9EETrFduxzvrOqjGIJtvN2N_lTPC_twG_au26ELj3_UWcfl4rYxuO3tW52kNJgDYYZIu7DZXpoFsQUMXn_5g");
            //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
            StringEntity entity = new StringEntity(jsonObject1.toString(),"utf-8");
            //将参数信息添加到方法中
            httpPost.setEntity(entity);
            //声明一个对象来进行响应结果的存储
            String result;
            //执行post方法
            TestConfig.defaultHttpClient=new DefaultHttpClient();
            HttpResponse response = null;
            response = TestConfig.defaultHttpClient.execute(httpPost);
            //获取响应结果
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("调用接口result:"+result);
            SqlSession session = DatabaseUtil.getSqlSession();
            int i1=session.update("updateLessonState",lesson);
            session.commit();
            session.close();
            //log.info("执行成功条数"+i);
            System.out.println("执行成功条数"+i);
            SqlSession session1 = DatabaseUtil.getSqlSession();
            int j=session1.selectOne("setLessonId");
            lesson.setLessonId(j);
            System.out.println(lesson.getLessonId());
            list.add(lesson.getLessonId());
            System.out.println(list);
            sleep(3000);
        }
        System.out.println(list);
        list1.addAll(list);
        System.out.println(list1);
    }
    @Test(description = "创建课程信息")
    public void addCourse() throws IOException, InterruptedException {
        addLesson();
        //创建接口对象
        HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(TestConfig.addCourse= ConfigFile.getUrl(ADDCOURSE));
        JSONObject jsonObject=new JSONObject();
        //创建课程对象
        Course course =new Course();
        CourseSon courseSon=new CourseSon();
        courseSon.setLessonName("测试9");
        courseSon.setLessonWay(lesson.getLessonWay());
        courseSon.setLessonId(list1.get(0));
        CourseSon courseSon1=new CourseSon();
        courseSon1.setLessonName("测试10");
        courseSon1.setLessonWay(lesson.getLessonWay());
        courseSon1.setLessonId(list1.get(1));
        List list=new ArrayList();
        list.add(courseSon);
        list.add(courseSon1);
        course.setCourseLessonList(list);
        course.setCourseName("测试11");
        System.out.println(course.toString());
        //把课程对象转化为jsonObject对象
        JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(course);
        //jsonObject = JSON.parseObject("{\"term\":20,\"level\":10,\"courseType\":20,\"courseName\":\"测试5\",\"state\": \"00000000020\",\"subjectCode\": 10,\n" +
        //        "\t\"classWay\": 3,\"courseLessonList\":[{\"lessonName\":\"测试2\",\"lessonId\":3122,\"lessonWay\":2},{\"lessonName\":\"测试1\",\"lessonId\":3121,\"lessonWay\":2},{\"lessonName\":\"测试3\",\"lessonId\":3125,\"lessonWay\":2}],\"subjectName\":\"英语\"}");
        //设置请求头信息 设置header
        System.out.println(jsonObject1.toString());
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("token","eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb20ueGRmLmJsaW5nIiwiYXVkIjoiY2xpZW50IiwidXNlcmNvZGUiOiJjcm1hZG1pbiIsImV4cCI6MTYwMDc2MTg0NCwiaWF0IjoxNjAwMTU3MDQ0fQ.T9YX8RQWeC1ijGXdfu7WpPmEN11QQhJ45Iyb7oHrB59JxPDfXzoYBmHwU19TOxQnPU6r7IOXhhkIH2dItx-d0g");
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity(jsonObject1.toString(),"utf-8");
        System.out.println(entity.toString());
        //将参数信息添加到方法中
        httpPost.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        HttpResponse response = null;
        response = TestConfig.defaultHttpClient.execute(httpPost);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("调用接口result:"+result);
    }
    @Test(description = "添加模板")
    public void addTemplate() throws IOException, InterruptedException {
        addCourse();
        //创建httpClient接口
        HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(TestConfig.addTemplate= ConfigFile.getUrl(ADDTEMPLATE));
        JSONObject jsonObject=new JSONObject();
        Template template=new Template();
        TemplateSon templateSon=new TemplateSon();
        templateSon.setLessonId(list1.get(0));
        templateSon.setName("测试9");
        TemplateSon templateSon1=new TemplateSon();
        templateSon1.setLessonId(list1.get(1));
        templateSon1.setName("测试10");
        List list=new ArrayList();
        list.add(templateSon);
        list.add(templateSon1);
        template.setTemplateLessonList(list);
        SqlSession session = DatabaseUtil.getSqlSession();
        int courseId=session.selectOne("setcoureId");
        template.setCourseId(courseId);
        JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(template);
        /*jsonObject = JSON.parseObject("{\n" +
                "\t\"channelGrade\": \"1\",\n" +
                "\t\"courseChannelCode\": 10,\n" +
                "\t\"syncStatus\": 0,\n" +
                "\t\"stage\": 10,\n" +
                "\t\"openClassTotle\": \"1\",\n" +
                "\t\"sendMaterial\": 0,\n" +
                "\t\"courseId\": 30191,\n" +
                "\t\"templateLessonList\": [{\n" +
                "\t\t\"lessonId\": 3122,\n" +
                "\t\t\"name\": \"测试2\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"showPrice\": 0,\n" +
                "\t\t\"contractPrice\": 0,\n" +
                "\t\t\"teacherRole\": 0\n" +
                "\t}, {\n" +
                "\t\t\"lessonId\": 3121,\n" +
                "\t\t\"name\": \"测试1\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"showPrice\": 0,\n" +
                "\t\t\"contractPrice\": 0,\n" +
                "\t\t\"teacherRole\": 0\n" +
                "\t}, {\n" +
                "\t\t\"lessonId\": 3125,\n" +
                "\t\t\"name\": \"测试3\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"showPrice\": 0,\n" +
                "\t\t\"contractPrice\": 0,\n" +
                "\t\t\"teacherRole\": 0\n" +
                "\t}],\n" +
                "\t\"schoolTimeStatus\": 1,\n" +
                "\t\"liveContain\": 3,\n" +
                "\t\"commentStatus\": 1,\n" +
                "\t\"freeLesson\": 0,\n" +
                "\t\"entryClassTimeList\": {\n" +
                "\t\t\"entryDate\": \"\",\n" +
                "\t\t\"entryHour\": \"\",\n" +
                "\t\t\"entryMinute\": \"\"\n" +
                "\t}\n" +
                "}");*/
        //设置请求头信息 设置header
        System.out.println(jsonObject1.toString());
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("token","eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb20ueGRmLmJsaW5nIiwiYXVkIjoiY2xpZW50IiwidXNlcmNvZGUiOiJjcm1hZG1pbiIsImV4cCI6MTYwMDc2MTg0NCwiaWF0IjoxNjAwMTU3MDQ0fQ.T9YX8RQWeC1ijGXdfu7WpPmEN11QQhJ45Iyb7oHrB59JxPDfXzoYBmHwU19TOxQnPU6r7IOXhhkIH2dItx-d0g");
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity(jsonObject1.toString(),"utf-8");
        System.out.println(entity.toString());
        //将参数信息添加到方法中
        httpPost.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        HttpResponse response = null;
        response = TestConfig.defaultHttpClient.execute(httpPost);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("调用接口result:"+result);
    }
    @Test(description = "发布班级")
    public void classIssue() throws IOException, InterruptedException {
        //addTemplate();
        HttpClient httpClient=new DefaultHttpClient();
        SqlSession session = DatabaseUtil.getSqlSession();
        int classId=session.selectOne("setClassId");
        HttpPost httpPost=new HttpPost(TestConfig.releaseClassInfo= ConfigFile.getUrl(RELEASECLASSINFO)+"?ids="+classId);
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("token","eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb20ueGRmLmJsaW5nIiwiYXVkIjoiY2xpZW50IiwidXNlcmNvZGUiOiJjcm1hZG1pbiIsImV4cCI6MTYwMTQ2ODY4NSwiaWF0IjoxNjAwODYzODg1fQ.SkrB1KR2eHzIHUIvUgZNbMuBR5j-CA-eZJF7MMrCeU0RWW8vPtNfzFHadohA2EBqjTooiGnBLjWHwrw9rUninA");
        //将参数转化为HttpEntity类型的实现类才能把参数放到方法里
        StringEntity entity = new StringEntity("utf-8");
        //将参数信息添加到方法中
        httpPost.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        HttpResponse response = null;
        response = TestConfig.defaultHttpClient.execute(httpPost);
        System.out.println(response);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        session.update("updateClassState",classId);
        session.commit();
        session.close();
        System.out.println("调用接口result:"+result);
    }
}
