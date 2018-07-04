//package com.example.demo.controller;
//
//import org.apache.catalina.Host;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
//import static org.springframework.http.HttpHeaders.FROM;
//import static org.springframework.http.HttpHeaders.HOST;
//
//
//@RestController
//@RequestMapping("/iclock")
//public class FingerMsgController {
//
//    //1. 设备启动时，从服务器上读取设备的配置信息
////    @GetMapping("/cdata")
//    @RequestMapping(value = "/cdata", method = { RequestMethod.GET, RequestMethod.POST })
//
//
////    HTTP 请求方法使用:GET 方法
////    URI 使用:/iclock/cdata
////    HTTP 协议版本使用:1.1
////    客户端配置信息:
////      SN:${Required}表示客户端的序列号
////      options:${Required}表示获取服务器配置参数，目前值只有 all
////      pushver:${Optional}表示 push 协议的版本，新开发的客户端必须支持且必须大于等于 2.2.14 版本
////      language:${Optional}表示客户端支持的语言，新开发的客户端最好支持，服务端可通过该参数知道目前设备是什么语言，见“附录 2”
////      pushcommkey:${Optional}表示客户端与服务器绑定的密文信息，软件通过此密文判断设备是否经过授权，不同设备值一般是不一样的，该参数需要服务器支持之后，客户端才需支持
////    Host头域:${Required} 认证是否发给该服务器
////    其他头域:${Optional}
//
//
//    public String getConnect(@RequestParam("SN") String SN,
////                             @RequestParam("options") String options,
////                             @RequestParam("pushver") String pushver,
////                             @RequestParam("language") int language,
////                             @RequestParam("pushcommkey") String pushcommkey,
//                             HttpServletRequest request, HttpServletResponse response) throws IOException {
////        SN = "3485162300120";
////        language = 83;
//
//        System.out.println(SN);
//
//        String rec = "GET OPTION FROM: ";
//        rec += SN+"\n";
//        rec += "ATTLOGStamp=9999"+"\n";
//        rec += "ErrorDelay=60"+"\n";
//        rec += "Delay=30"+"\n";
//        rec += "TransTimes=00:00;14:00"+"\n";
//        rec += "TransInterval=1"+"\n";
//        rec += "TransFlag=1101100000"+"\n";
//        rec += "Realtime=1"+"\n";
//        rec += "Encrypt=0"+"\n";
//        rec += "ServerVer=3.4.1 2010\u00AD06\u00AD07";
//
////        String host=request.getHeader("Host");
////        response.addHeader("Host",host);
//        // 返回时间
//        response.addHeader("Date",new Date().toString());
//        // 设置内容类型
//        response.addHeader("Content-Type","text/plain");
//        // 返回长度
//        response.addHeader("Content-Length","190");
//
////        response.getOutputStream().write(rec.getBytes());
////        GET OPTION FROM: 3485162300120;
////        Stamp=82983982;
////        OpStamp=9238883;
////        PhotoStamp=9238833;
////        //  联网失败后客户端重新联接服务器的间隔时间(秒)
////        ErrorDelay=60;
////        //  正常联网时客户端联接服务器的间隔时间
////        Delay=30;
////        //  客户端定时检查并传送新数据时间
////        TransTimes=00:00;14:00;
////        //  客户端检查并传送新数据间隔时间(分钟)
////        TransInterval=1;
////        //  客户端向服务器自动上传哪些数据的标识
////        TransFlag=1101100000;
////        //  客户端实时传送新记录
////        Realtime=1;
////        //  是否加密传送数据，保留未使用
////        Encrypt=0;
////        //  服务器支持的协议版本号及时间
////        ServerVer=3.4.1 2010­06­07;
//
//
//        return rec;
//    }
//
//}
