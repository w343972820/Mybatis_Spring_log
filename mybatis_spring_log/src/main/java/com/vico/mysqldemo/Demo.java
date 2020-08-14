package com.vico.mysqldemo;
import com.vico.model.UserSms;
import com.vico.utils.DatabaseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("v1")
@Api(value = "v1",description = "这是我的第一个版本的demo")
public class Demo {
    private SqlSession sqls;
    private Logger logger= LoggerFactory.getLogger(Demo.class);
    public Demo() throws IOException {
        this.sqls = DatabaseUtil.getSqlSession();
    }
    //数据库查询
    @RequestMapping(value = "/getUserPhone",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户电话号码",httpMethod = "GET")
    public String getUserPhone() throws IOException {
        String phone = sqls.selectOne("vicos1.getUserCount", 70);
        return phone+" ..正常....";
    }
    //数据库新增
    @RequestMapping(value="/addSms",method = RequestMethod.POST)
    public int addSms(@RequestBody UserSms sms){
        int result=sqls.insert("vicos1.addSms",sms);
        sqls.commit();
        return result;
    }
    //数据库修改
    @RequestMapping(value="/updateSms",method = RequestMethod.POST)
    public int updateSms(@RequestBody UserSms sms){
        int result=sqls.insert("vicos1.updateSam",sms);
        sqls.commit();
        return result;
    }
    //数据库删除
    @RequestMapping(value="/deleteSms",method = RequestMethod.POST)
    public int deleteSms(@RequestParam int id){
        int result=sqls.insert("vicos1.deleteSam",id);
        sqls.commit();
        return result;
    }
    @RequestMapping(value = "lookeLog",method = RequestMethod.GET)
    public void lookLog(){
        logger.info("==================");
    }
}
