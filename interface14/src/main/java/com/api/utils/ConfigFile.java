package com.api.utils;

import java.util.Locale;
import java.util.ResourceBundle;
@SuppressWarnings("all")
public class ConfigFile {
    //创建一个能读取properties的文件的静态属性
   private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){
        //获取配置文件中的域名
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;
        //获取配置文件中的url
        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }
        if (name== InterfaceName.GETINSERT){
            uri = bundle.getString("getInsert.url");
        }
        if (name== InterfaceName.ADDCOURSE){
            uri = bundle.getString("addcourse.url");
        }
        if (name== InterfaceName.ADDTEMPLATE){
            uri = bundle.getString("addtemplate.url");
        }
        if (name== InterfaceName.RELEASECLASSINFO){
            uri = bundle.getString("releaseclassinfo.url");
        }
        if (name== InterfaceName.STUDYREPORT){
            uri = bundle.getString("studyReport.url");
        }
        if (name== InterfaceName.LOGIN){
            uri = bundle.getString("login.url");
        }
        //拼接域名+url
        testUrl = address + uri;
        return testUrl;
    }
}
