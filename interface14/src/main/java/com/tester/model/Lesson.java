package com.tester.model;

import lombok.Data;

@Data
public class Lesson {
    //课时id
    private int lessonId;
    //课时名称
    private String name;
    //默认课次
    private int lessonNum=1;
    //是否测评课程
    private  int isTestCourse=0;
    //时长
    private  int whenLong=30;
    //词汇
    private  String vocabulary="";
    //学季
    private int term=40;
    //等级
    private int level=20;
    //课程类型
    private int courseType=20;
    //备注
    private String remark="代码测试";
    //1直播；2AI课堂；3录播视频
    private int lessonWay=2;
    //'教材类型(10主课 30前途美中 40文化课 50PW教材 60自然拼读)'
    private int materialType=10;
    //学科名称
    private String subjectName="英语";
    //学科编码
    private int subjectCode=10;
    //ai课程id
    private int aiId=243;
    //课时类型1:比邻课时,2:新东方AI课时
    private int lessonType=2;
    //发布状态
    private int state=20;



}
