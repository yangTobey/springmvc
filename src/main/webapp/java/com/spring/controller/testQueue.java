package com.spring.controller;

import com.spring.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/27.
 */
@Controller
@RequestMapping("/rabbitMQ")
public class testQueue {
    @Autowired
    private Producer producer;
    @Value("#{appConfig['mq.queue']}")
    private String queueId;

    /**
     * @Description: 消息队列
     * @Author:
     * @CreateTime:
     */
    @ResponseBody
    @RequestMapping(value = "/sendQueue", method = RequestMethod.GET)
    public String testQueue() {
        System.out.println("访问进来！！");
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<String> list=null;
            for(int i=0; i<10; i++){
                new Thread("" + i){
                    @Override
                    public void run(){
                        sendMessage();
                    }
                }.start();
            }


            // 注意：第二个属性是 Queue 与 交换机绑定的路由
            //producer.sendQueue(queueId + "_exchange", queueId + "_patt", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    public void sendMessage(){
        for(int i=1;i<30000;i++){
            String a="年轻人，多学习，多看报，不要犹豫，日写代码三百行，月薪上万不是梦!!"+i;
            producer.sendQueue(queueId + "_exchange", queueId + "_patt", a);
        }
    }
}
