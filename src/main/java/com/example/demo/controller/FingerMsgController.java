package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.Date;


@RestController
@RequestMapping("/iclock")
public class FingerMsgController {

    //1. 设备启动时，从服务器上读取设备的配置信息
//    @GetMapping("/cdata")



//    HTTP 请求方法使用:GET 方法
//    URI 使用:/iclock/cdata
//    HTTP 协议版本使用:1.1
//    客户端配置信息:
//      SN:${Required}表示客户端的序列号
//      options:${Required}表示获取服务器配置参数，目前值只有 all
//      pushver:${Optional}表示 push 协议的版本，新开发的客户端必须支持且必须大于等于 2.2.14 版本
//      language:${Optional}表示客户端支持的语言，新开发的客户端最好支持，服务端可通过该参数知道目前设备是什么语言，见“附录 2”
//      pushcommkey:${Optional}表示客户端与服务器绑定的密文信息，软件通过此密文判断设备是否经过授权，不同设备值一般是不一样的，该参数需要服务器支持之后，客户端才需支持
//    Host头域:${Required} 认证是否发给该服务器
//    其他头域:${Optional}


//
//    public String getConnect(@RequestParam("SN") String SN,
//                             @RequestParam("options") String options,
//                             @RequestParam("pushver") String pushver,
//                             @RequestParam("language") int language,
//                             @RequestParam("pushcommkey") String pushcommkey,
//                             HttpServletRequest request, HttpServletResponse response){
//        String host=request.getHeader("Host");
//        // 返回时间
//        response.addHeader("Date",new Date().toString());
//        String SerialNumber = SN + '/';
//        String stamp = ATTLOG+'stamp';
//        Time ErrorDelay = 20;
//        Time Delay = 60;
//        int TransFlag = 1;
//        int TimeZone = 4;
//        int Realtime = 1;
//        return
//    }

}
