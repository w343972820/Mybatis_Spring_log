package com.vico.mysqldemo;

import com.vico.utils.DatabaseUtil;
import io.swagger.annotations.Api;
import org.apache.ibatis.session.SqlSession;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("v2")
@Api(value = "v2",description = "自己数据库操作测试用例")
public class VicoMysqlTest {
    private SqlSession sqls;
    public VicoMysqlTest() throws IOException {
        this.sqls = DatabaseUtil.getSqlSession();
    }
    //数据库查询
    @RequestMapping(value="/findCity",method = RequestMethod.GET)
    public String addSms(@RequestParam int id){
        System.out.println("进来..");
        String result=sqls.selectOne("vicos1.getCity",id);
        return result;
    }

}
