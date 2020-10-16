package com.tester.model;


import lombok.Data;

import java.util.List;
@Data
public class Course {
    //课程id
    private int id;
    //学季
    private int term=20;
    //等级
    private int level = 10;
    //课程类型
    private int courseType = 20;
    //课程渠道
    private int courseChannel=10;
    //课程名称
    private String courseName;
    //发布状态
    private String state = "00000000020";
    //学科编码
    private int subjectCode = 10;
    //1直播；2AI课堂；3录播视频
    private int classWay=3;
    //学科名称
    private String subjectName="英语";
    //课时列表
    private List<CourseSon> courseLessonList;
    //产品体系
    private int productSystem;
    //产品品类
    private String productLevel;
    //部门
    private int department;
    //所属项目编码
    private int projectCode;
    //教学内容 201=口语
    private int teachingContent=201;
    //学龄标签
    private int experienceType= -1;
    //课程备注
    private String remark="测试";
    //年份
    private int year=2020;
}
