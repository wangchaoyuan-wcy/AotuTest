package com.tester.model;

import lombok.Data;

@Data
public class TemplateSon {
    //课时id
    private int lessonId;
    //课时名称
    private String name;
    //价格
    private int price=0;
    //显示价格
    private int showPrice=0;
    //合同价格
    private int contractPrice=0;
    //授课角色1外教课2中教课
    private int teacherRole=0;

}
