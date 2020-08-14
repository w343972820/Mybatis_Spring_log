package com.vico.cases;

import com.alibaba.fastjson.JSONObject;
import com.vico.config.TestConfig;
import com.vico.model.InterfaceName;
import com.vico.model.LoginCase;
import com.vico.utils.ConfigFile;
import com.vico.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = {"loginTrue"},description = "测试之前准备工作，如httpclient对象之类的")
    public void beforeTest(){
        //首先给各接口地址赋值
        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        //给httpClient赋值
        TestConfig.client=new DefaultHttpClient();
    }
    @Test(groups = {"loginTrue"},description = "用户成功登录接口")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase=sqlSession.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //拿到用例数据后，访问接口，去拿接口返回结果
        String result = getResult(loginCase);
        //拿到结果后，判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);

    }
    @Test(groups = {"loginTrue"},description = "用户失败登录接口")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase=sqlSession.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //拿到用例数据后，访问接口，去拿接口返回结果
        String result = getResult(loginCase);
        //拿到结果后，判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);

    }

    //用请求参数访问登录请求链接，返回请求结果
    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param=new JSONObject();
        param.put("name",loginCase.getUserName());
        param.put("password",loginCase.getPassWord());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到参数中去
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        String result;
        //执行post请求
        HttpResponse response = TestConfig.client.execute(post);
        //获取响应结果
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //拿到响应cookies
        TestConfig.store=TestConfig.client.getCookieStore();
        return result;
    }
}
