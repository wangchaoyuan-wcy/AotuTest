package com.tester.model;

import lombok.Data;

import java.util.List;
@Data
public class Template {
    //渠道年级
    private int channelGrade=1;
    //班级渠道码
    private int courseChannelCode=10;
    //同步集团教务(0:不同步 1:同步)
    private int syncStatus=0;
    //类别标签
    private int classLabel;
    //分期
    private int stage=10;
    //非周期性开班数
    private int openClassTotle=1;
    //是否寄送教材0不送，1送
    private int sendMaterial=0;
    //课程id
    private int courseId;
    //课时数组
    private List<TemplateSon> templateLessonList;
    //周期性时间设置(0:周期性 1:非周期性)
    private int schoolTimeStatus=1;
    //直播班容
    private int liveContain=3;
    //是否需要外教评论(0否  1是)
    private int commentStatus=1;
    //免费课时
    private int freeLesson=0;
    //教材价格
    private int materialPrice=0;


}
