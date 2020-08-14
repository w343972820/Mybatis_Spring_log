package com.vico.utils;

import com.vico.model.InterfaceName;

import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application");
    public static String getUrl(InterfaceName name){
        String address=bundle.getString("test.url");
        String uri="";
        String testUrl;
        if (name==InterfaceName.GETUSERLIST){
            uri=bundle.getString("test.getUserList.uri");
        }
        if (name==InterfaceName.GETUSERINFO){
            uri=bundle.getString("test.getUserInfo.uri");
        }
        if (name==InterfaceName.LOGIN){
            uri=bundle.getString("test.login.uri");
        }
        if (name==InterfaceName.UPDATEUSERINFO){
            uri=bundle.getString("test.updateUserInfo.uri");
        }
        if (name==InterfaceName.ADDUSER){
            uri=bundle.getString("test.addUserInfo.uri");
        }
        testUrl=address+uri;
        return testUrl;
    }
}
