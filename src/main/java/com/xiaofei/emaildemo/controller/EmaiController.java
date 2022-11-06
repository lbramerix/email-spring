package com.xiaofei.emaildemo.controller;

import com.xiaofei.emaildemo.pojo.Email;
import com.xiaofei.emaildemo.util.EmailUtil;
import com.xiaofei.emaildemo.util.Result;
import com.xiaofei.emaildemo.util.StatusCode;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("email")
@CrossOrigin
public class EmaiController {

    @RequestMapping
    public Result tests(){
        return new Result(true, StatusCode.OK,"成功！");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result test(@RequestBody Map<String,String> map){
        try{
            Email mailInfo = new Email();
            mailInfo.setToAddress(map.get("email"));
            mailInfo.setSubject(map.get("title")); //邮件的title
            mailInfo.setContent(map.get("content")); //邮件的内容
            //这个类主要来发送邮件
            EmailUtil.sendHtmlMail(mailInfo);
            return new Result(true, StatusCode.OK,"发送成功！");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"发送失败！");
        }


    }
}
