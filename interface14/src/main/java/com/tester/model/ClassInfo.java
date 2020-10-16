package com.tester.model;

import com.tester.cases.HttpClient1;
import org.springframework.beans.factory.annotation.Autowired;
public class ClassInfo {
    @Autowired
    private Lesson lesson;
    public Lesson getLesson() {
        return lesson;
    }
    @Autowired
    private HttpClient1 httpClient1;
    public HttpClient1 getHttpClient1() {
        return httpClient1;
    }
    @Autowired
    private Course course;
    public Course getCourse() {
        return course;
    }

    @Autowired
    private Template template;
    public Template getTemplate() {
        return template;
    }





}
