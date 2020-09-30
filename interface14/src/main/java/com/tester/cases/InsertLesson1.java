package com.tester.cases;
import com.alibaba.fastjson.JSONObject;
import com.tester.model.*;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.tester.config.TestConfig.*;
import static com.tester.model.InterfaceName.*;
import static java.lang.Thread.sleep;

@SuppressWarnings("all")
public class InsertLesson1 {
    Logger logger=LoggerFactory.getLogger(getClass());
    HttpClient1 httpClient1=new HttpClient1();
    Lesson lesson =new Lesson();
    List<Integer> list1=new ArrayList();
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
        list1.add(23);
        logger.info("你好"+list1);
        System.out.println("打印"+list1);

    }
    @Test(description = "创建课时信息")
    public void addLesson() throws IOException, InterruptedException {
        List list=new ArrayList();
        for(int i=0;i<=1;i++){
            //把课时对象转换为JSONObject对象
            if (i==0){
                lesson.setName("测试9");
            }
            if (i==1){
                lesson.setName("测试10");
            }
            JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(lesson);
            System.out.println(jsonObject1);
            getInsert= ConfigFile.getUrl(GETINSERT);
            httpClient1.Post(jsonObject1,getInsert);
            //发布课时信息
            SqlSession session=DatabaseUtil.getSqlSession();
            int i1=session.update("updateLessonState",lesson);
            session.commit();
            session.close();
            logger.info("执行成功条数"+i);
            SqlSession session1 = DatabaseUtil.getSqlSession();
            int lessonid=session1.selectOne("setLessonId");
            lesson.setLessonId(lessonid);
            list.add(lesson.getLessonId());
            logger.info("集合是"+list);
            sleep(3000);
        }
        list1.addAll(list);
        logger.info("大集合是"+list1);
    }
    @Test(description = "创建课程信息")
    public void addCourse() throws IOException, InterruptedException {
        addLesson();
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
        //把课程对象转化为jsonObject对象
        JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(course);
        //设置请求头信息 设置header
        addCourse= ConfigFile.getUrl(ADDCOURSE);
        httpClient1.Post(jsonObject1,addCourse);
    }
    @Test(description = "添加模板")
    public void addTemplate() throws IOException, InterruptedException {
        addCourse();
        //创建httpClient接口
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
        //添加课程id
        int courseId=session.selectOne("setcoureId");
        template.setCourseId(courseId);
        JSONObject jsonObject1= (JSONObject) jsonObject.toJSON(template);
        addTemplate= ConfigFile.getUrl(ADDTEMPLATE);
        httpClient1.Post(jsonObject1,addTemplate);

    }
    @Test(description = "发布班级")
    public void classIssue() throws IOException, InterruptedException {
        addTemplate();
        //sleep(7000);
        //查询班级id
        SqlSession session = DatabaseUtil.getSqlSession();
        int classId=session.selectOne("setClassId");
        //调用发布接口方法
        releaseClassInfo= ConfigFile.getUrl(RELEASECLASSINFO);
        httpClient1.Post(releaseClassInfo+"?ids="+classId);
        session.update("updateClassState",classId);
        session.commit();
        session.close();
    }
}
