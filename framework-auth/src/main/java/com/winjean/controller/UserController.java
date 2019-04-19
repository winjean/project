package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.entity.UserRoleEntity;
import com.winjean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

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

    @PostMapping("deleteRoleResource")
    public Object deleteUserRole(@RequestBody List<Long> ids){
        userService.deleteUserRole(ids);
        return "delete success";
    }
}
