package com.winjean.controller;

import com.winjean.common.BaseResponse;
import com.winjean.common.PageResponse;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.request.UserQueryRequest;
import com.winjean.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("用户基本信息")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "insert")
    @ApiOperation("新增用户信息")
    public Object insert(@RequestBody UserEntity user) {
        try {
            log.info("receive value : " + user);
            userService.insert(user);
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping(value = "insertUsers"/*, consumes = "application/json"*/)
    @ApiOperation("批量新增用户信息")
    public Object insert(@RequestBody List<UserEntity> users) {
        try {
            log.info("receive value : " + users);
            userService.insert(users);
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping(value = "update")
    @ApiOperation("更新用户信息")
    public Object updateUser(@RequestBody UserEntity user) {
        try {
            log.info("receive value : " + user);
            userService.update(user);
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping(value = "delete")
    @ApiOperation("删除用户信息")
    public Object deleteUser(@RequestBody UserEntity user) {
        try {
            log.info("receive value : " + user);
            userService.delete(user);
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping(value = "deleteUsers")
    @ApiOperation("批量删除用户信息")
    public Object deleteUsers(@RequestBody List<UserEntity> users) {
        try {
            log.info("receive value : " + users);
            userService.delete(users);
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping(value = {"searchUsers","xxx"})
    @ApiOperation("查询批量用户信息")
    public Object searchUsers(@RequestBody UserQueryRequest req) {
        try {
            log.info("receive value : " + req);
            PageResponse<UserQueryRequest> response = userService.searchUsers(req);
            return BaseResponse.getSuccessResponse(response);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping(value = "searchUser")
    @ApiOperation("查询用户信息")
    public Object searchUser(@RequestBody UserEntity user) {
        try {
            log.info("receive value : " + user);
            UserEntity u = userService.searchUser(user);
            if(u == null){
                return BaseResponse.getSuccessResponse("未找到相应的用户信息");
            }
            return BaseResponse.getSuccessResponse(u);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }
}
