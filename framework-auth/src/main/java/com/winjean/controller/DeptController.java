package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.DeptEntity;
import com.winjean.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("save")
    public Object save(@RequestBody DeptEntity entity){
        deptService.save(entity);
        return "save success";
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id){
        deptService.delete(id);
        return "delete success";
    }

    @PostMapping("update")
    public Object findModuleById(@RequestBody DeptEntity entity){
        deptService.update(entity);
        return "update success";
    }

    @PostMapping("findById")
    public Object findModuleById(@RequestBody JSONObject json){
        Long id = json.getLong("id");

        DeptEntity entity =  deptService.findById(id);
        return entity;
    }

    @PostMapping("findByName")
    public Object findModuleByName(@RequestBody JSONObject json){
        String name = json.getString("name");
        DeptEntity entity = deptService.findByName(name);
        return entity;
    }

    @PostMapping("findAll")
    public Object findAll(@RequestBody JSONObject json){
        int pageNo = json.getInteger("pageNo");
        int pageSize = json.getInteger("pageSize");
        Page<DeptEntity> entitys = deptService.findAll(pageNo, pageSize);
        return entitys;
    }
}
