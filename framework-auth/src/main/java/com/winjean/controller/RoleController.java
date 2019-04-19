package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.RoleEntity;
import com.winjean.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("save")
    public Object save(@RequestBody RoleEntity entity){
        roleService.save(entity);
        return "save success";
    }

    @PostMapping("delete")
    public Object delete(@RequestParam int id){
        roleService.delete(id);
        return "delete success";
    }

    @PostMapping("update")
    public Object findModuleById(@RequestBody RoleEntity entity){

        roleService.update(entity);
        return "update success";
    }

    @PostMapping("findById")
    public Object findModuleById(@RequestBody JSONObject json){
        int id = json.getInteger("id");

        RoleEntity module =  roleService.findById(id);
        return module;
    }

    @PostMapping("findByName")
    public Object findModuleByName(@RequestBody JSONObject json){
        String name = json.getString("name");
        RoleEntity module = roleService.findByName(name);
        return module;
    }

    @PostMapping("findAll")
    public Object findAll(@RequestBody JSONObject json){
        int pageNo = json.getInteger("pageNo");
        int pageSize = json.getInteger("pageSize");
        Page<RoleEntity> modules = roleService.findAll(pageNo, pageSize);
        return modules;
    }
}
