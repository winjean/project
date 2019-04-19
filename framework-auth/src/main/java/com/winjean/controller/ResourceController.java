package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.ResourceEntity;
import com.winjean.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("save")
    public Object save(@RequestBody ResourceEntity entity){
        resourceService.save(entity);
        return "save success";
    }

    @PostMapping("delete")
    public Object delete(@RequestParam Long id){
        resourceService.delete(id);
        return "delete success";
    }

    @PostMapping("update")
    public Object findModuleById(@RequestBody ResourceEntity entity){

        resourceService.update(entity);
        return "update success";
    }

    @PostMapping("findById")
    public Object findModuleById(@RequestBody JSONObject json){
        Long id = json.getLong("id");

        ResourceEntity module =  resourceService.findById(id);
        return module;
    }

    @PostMapping("findByName")
    public Object findModuleByName(@RequestBody JSONObject json){
        String name = json.getString("name");
        ResourceEntity module = resourceService.findByName(name);
        return module;
    }

    @PostMapping("findAll")
    public Object findAll(@RequestBody JSONObject json){
        int pageNo = json.getInteger("pageNo");
        int pageSize = json.getInteger("pageSize");
        Page<ResourceEntity> modules = resourceService.findAll(pageNo, pageSize);
        return modules;
    }
}
