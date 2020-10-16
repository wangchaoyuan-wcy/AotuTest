package com.tester.cases;
import com.alibaba.fastjson.JSONObject;
import com.tester.model.*;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import static com.tester.config.TestConfig.*;
import static com.tester.utils.InterfaceName.*;
import static java.lang.Thread.sleep;

@SuppressWarnings("all")
public class InsertLesson1 {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    ClassInfo classInfo = (ClassInfo) context.getBean("classInfo");
    Logger logger=LoggerFactory.getLogger(getClass());
    ArrayList<Integer> list1=new ArrayList();
    JSONObject jsonObject=new JSONObject();
    SqlSession session;
    {
        try {
            session = DatabaseUtil.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void l() {
    }
    @Test(description = "创建课时信息")
    public void addLesson() throws IOException, InterruptedException {
        ArrayList list=new ArrayList();
        for(int i=0;i<=1;i++){
            //把课时对象转换为JSONObject对象
            if (i==0){
                classInfo.getLesson().setName("测试9");
            }
            if (i==1){
                classInfo.getLesson().setName("测试10");
            }
            JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(classInfo.getLesson());
            System.out.println("入参是"+jsonObject1);
            //调用添加课时接口
            classInfo.getHttpClient1().Post(jsonObject1,ConfigFile.getUrl(GETINSERT));
            //发布课时信息
            SqlSession session=DatabaseUtil.getSqlSession();
            int i1=session.update("updateLessonState",classInfo.getLesson());
            session.commit();
            session.close();
            logger.info("执行成功条数"+i);
            SqlSession session1 = DatabaseUtil.getSqlSession();
            int lessonid=session1.selectOne("setLessonId");
            classInfo.getLesson().setLessonId(lessonid);
            list.add(classInfo.getLesson().getLessonId());
            logger.info("集合是"+list);
            sleep(3000);
        }
        list1.addAll(list);
        logger.info("大集合是"+list1);
    }
    @Test(description = "创建课程信息")
    public void addCourse() throws IOException, InterruptedException {
        addLesson();
        ArrayList list=new ArrayList();
        for (int i = 0; i < list1.size(); i++) {
            CourseSon courseSon=new CourseSon();
            courseSon.setLessonName((String) session.selectOne("setLessonName",list1.get(i)));
            courseSon.setLessonWay(classInfo.getLesson().getLessonWay());
            courseSon.setLessonId((Integer) list1.get(i));
            list.add(courseSon);
        }
        classInfo.getCourse().setCourseLessonList(list);
        classInfo.getCourse().setCourseName("测试13");
        //把课程对象转化为jsonObject对象
        JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(classInfo.getCourse());
        logger.info("入参是"+jsonObject1);
        //调用添加课程接口
        classInfo.getHttpClient1().Post(jsonObject1,ConfigFile.getUrl(ADDCOURSE));
    }
    @Test(description = "添加模板")
    public void addTemplate() throws IOException, InterruptedException {
        addCourse();
        ArrayList list=new ArrayList();
        //创建httpClient接口
        for (int i = 0; i < list1.size(); i++) {
            TemplateSon templateSon=new TemplateSon();
            templateSon.setLessonId(list1.get(i));
            templateSon.setName((String) session.selectOne("setLessonName",list1.get(i)));
            list.add(templateSon);
        }
        classInfo.getTemplate().setTemplateLessonList(list);
        //添加课程id
        SqlSession session = DatabaseUtil.getSqlSession();
        int courseId=session.selectOne("setcoureId");
        classInfo.getTemplate().setCourseId(courseId);
        JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(classInfo.getTemplate());
        logger.info("入参是"+jsonObject1);
        //调用添加班级模板接口
        classInfo.getHttpClient1().Post(jsonObject1,ConfigFile.getUrl(ADDTEMPLATE));
    }
    @Test(description = "发布班级")
    public void classIssue() throws IOException, InterruptedException {
        addTemplate();
        //查询班级id
        SqlSession session = DatabaseUtil.getSqlSession();
        int classId=session.selectOne("setClassId");
        //调用发布班级接口
        releaseClassInfo= ConfigFile.getUrl(RELEASECLASSINFO);
        //拼接参数
        classInfo.getHttpClient1().Post(ConfigFile.getUrl(RELEASECLASSINFO)+"?ids="+classId);
        //更改发布平台接口
        session.update("updateClassState",classId);
        session.commit();
        session.close();
    }
}
