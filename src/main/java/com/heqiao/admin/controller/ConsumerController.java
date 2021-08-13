package com.heqiao.admin.controller;

import com.heqiao.admin.entity.Status;
import com.heqiao.admin.entity.StatusEnum;
import com.heqiao.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.heqiao.admin.entity.Response;
/**
 * @author heq3
 * @create 2021/8/2 15:04
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static List<Map<String,Object>> logins = new ArrayList(12);
    private static Map<String,Object> person = new HashMap<>();

    private static StatusEnum currentStatus;

    @Autowired
    RedisUtil redisUtil;

    @ResponseBody
    @PostMapping(value="/login")
    public Response login(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String,Object> map2 = new HashMap<>();
        map2.putAll(map);
        map2.put("access_token","123");
        map2.put("token_type","hq");
        map2.put("name","胡歌");
        map2.put("age",35);
        Status status = new Status(StatusEnum.STATUS1.getCode(),StatusEnum.STATUS1.getMessage());
        map2.put("status",status);
        logins.add(map2);
        return new Response("成功登录", 100, map2);
    }

    @ResponseBody
    @GetMapping(value="/logout")
    public Response logout() {
        System.out.println("logout");
        return new Response("退出", 100, null);
    }

    @ResponseBody
    @GetMapping(value="/getUserInfo")
    public Response getUserInfo() {
        person.put("name","胡歌");
        person.put("age",30);
        person.put("tel","18817805101");
        return new Response("成功登录", 100, person);
    }

    @PostMapping(value="/finishApply")
    public Response finishApply(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Status status = new Status(StatusEnum.STATUS2.getCode(),StatusEnum.STATUS2.getMessage());
        redisUtil.set("apply",map);
        return new Response("授信成功登录", 100, status);
    }


    @ResponseBody
    @GetMapping(value="/activeApply")
    public Response activeApply() {
        Status status = new Status(StatusEnum.STATUS2.getCode(),StatusEnum.STATUS2.getMessage());
        redisUtil.set("status",status);
        return new Response("激活额度成功", 100, status);
    }

    @ResponseBody
    @GetMapping(value="/getIndexInfo")
    public Response getIndexInfo() {
        Object status = redisUtil.get("status");
        return new Response("激活额度成功", 100, status);
    }


    @ResponseBody
    @GetMapping(value="/agree")
    public Response agree() {
        Status status = new Status(StatusEnum.STATUS4.getCode(),StatusEnum.STATUS4.getMessage());
        redisUtil.set("status",status);
        return new Response("提交同意书成功", 100, status);
    }


    @ResponseBody
    @GetMapping(value="/quit")
    public Response quit() {
        Status status = new Status(StatusEnum.STATUS1.getCode(),StatusEnum.STATUS1.getMessage());
        redisUtil.set("status",status);
        return new Response("提交同意书成功", 100, status);
    }








}
