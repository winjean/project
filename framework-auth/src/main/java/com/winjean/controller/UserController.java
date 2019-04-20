package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.entity.UserRoleEntity;
import com.winjean.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 签名key
     */
    public static final String SIGNING_KEY = "JWTSecret";

    @PostMapping("login")
    public Object login(/*@RequestBody JSONObject json,*/ HttpServletResponse response){
//        userService.save(entity);
//        String username = "winjean";
//        ArrayList<Object> roleList = new ArrayList<>();
//        String subject = username /*+ "-" + roleList*/;
//        String token = Jwts.builder()
//                .setSubject(subject)
//                .setExpiration(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)) // 设置过期时间 365 * 24 * 60 * 60秒(这里为了方便测试，所以设置了1年的过期时间，实际项目需要根据自己的情况修改)
//                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY) //采用什么算法是可以自己选择的，不一定非要采用HS512
//                .compact();

        String token = Jwts.builder()
                .setSubject("winjean")
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "JWTSecret")
                .compact();

//        // 登录成功后，返回token到header里面
        response.addHeader("Authorization", "Bearer " + token);
//        response.addHeader("Authorization", "Bearer 123456");
        return "login success " + token;
    }

    @Autowired
    private UserService userService;

    @PostMapping("save")
    public Object save(@RequestBody UserEntity entity){
        userService.save(entity);
        return "save success";
    }

    @PostMapping("delete")
    public Object delete(@RequestParam Long id){
        userService.delete(id);
        return "delete success";
    }

    @PostMapping("update")
    public Object findModuleById(@RequestBody UserEntity entity){

        userService.update(entity);
        return "update success";
    }

    @PostMapping("findById")
    public Object findModuleById(@RequestBody JSONObject json){
        Long id = json.getLong("id");

        UserEntity module =  userService.findById(id);
        return module;
    }

    @PostMapping("findByName")
    public Object findModuleByName(@RequestBody JSONObject json){
        String name = json.getString("name");
        UserEntity module = userService.findByName(name);
        return module;
    }

    @PostMapping("findAll")
    public Object findAll(@RequestBody JSONObject json){
        int pageNo = json.getInteger("pageNo");
        int pageSize = json.getInteger("pageSize");
        Page<UserEntity> modules = userService.findAll(pageNo, pageSize);
        return modules;
    }

    /***** 以下处理 user 和 Role 的关系******/

    @PostMapping("saveUserRole")
    public Object saveUserRole(@RequestBody List<UserRoleEntity> entities){
        userService.save(entities);
        return "save success";
    }

    @PostMapping("deleteUserRole")
    public Object deleteUserRole(@RequestBody List<Long> ids){
        userService.deleteUserRole(ids);
        return "delete success";
    }
}
