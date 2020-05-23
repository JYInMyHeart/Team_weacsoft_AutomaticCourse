package com.spring.controller;

import com.spring.util.MyUtil;
import com.spring.util.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@CrossOrigin
@Controller
@RequestMapping("/public")
public class PublicController {

    MyUtil myUtil = new MyUtil();

    /**
     * 测试接口是否联通
     * @return
     */
    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity test(){
        ResponseEntity responseData = ResponseEntity.ok();
        responseData.putDataValue("message","能够成功连接");
        responseData.putDataValue("timestamp",myUtil.getTime());
        return responseData;
    }
}
