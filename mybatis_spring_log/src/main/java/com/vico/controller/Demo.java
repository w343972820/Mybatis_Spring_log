package com.vico.controller;


import com.vico.utils.DatabaseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("v1")
@Api(value = "v1",description = "这是我的第一个版本的demo")
public class Demo {
    @RequestMapping(value = "/getUserPhone",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户电话号码",httpMethod = "GET")
    public String getUserPhone() throws IOException {
        SqlSession sqls = DatabaseUtil.getSqlSession();
        String aaa = sqls.selectOne("vicos1.getUserCount", 70);
        return aaa;
       // return "正常访问……";
    }
}
