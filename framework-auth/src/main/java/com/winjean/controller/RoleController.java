package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.ResourceEntity;
import com.winjean.model.entity.RoleEntity;
import com.winjean.model.entity.RoleResourceEntity;
import com.winjean.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("save")
    public Object save(@RequestBody RoleEntity entity){
        roleService.save(entity);
        return "save success";
    }

    @PostMapping("delete")
    public Object delete(@RequestParam Long id){
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
        Long id = json.getLong("id");

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
        Page<RoleEntity> entities = roleService.findAll(pageNo, pageSize);
        return entities;
    }

    /***** 以下处理 Resources 和 Role 的关系******/

    @PostMapping("saveRoleResource")
    public Object saveRoleResource(@RequestBody List<RoleResourceEntity> entities){
        roleService.save(entities);
        return "save success";
    }

    @PostMapping("deleteRoleResource")
    public Object deleteRoleResource(@RequestBody List<Long> ids){
        roleService.deleteRoleResource(ids);
        return "save success";
    }



}
