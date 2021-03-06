package com.tester.utils;

import com.tester.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    //创建一个能读取properties的文件的静态属性
   private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){
        //获取配置文件中的域名
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;
        //获取配置文件中的url
        if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }
        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }
        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }
        if(name == InterfaceName.ADDUSERINFO){
            uri = bundle.getString("addUser.uri");
        }
        //拼接域名+url
        testUrl = address + uri;
        return testUrl;
    }
}
