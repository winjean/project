package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.common.PageResponse;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.request.UserInsertRequest;
import com.winjean.model.request.UserQueryRequest;
import com.winjean.model.response.UserQueryResponse;
import com.winjean.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("用户基本信息")
//@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","user/index"})
    @ApiOperation("index")
    public Object index() {
        try {
            log.info("index  ");

            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("user/login")
    @ApiOperation("用户登录")
    public Object login(@Validated @RequestBody JSONObject userLogin) {
        try {
            log.info("login  : " + userLogin);
            String userName = userLogin.getString("userName");
            String password = userLogin.getString("password");
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if(subject.isAuthenticated()){
                return BaseResponse.getSuccessResponse("认证成功");
            }else {
                return BaseResponse.getFailureResponse("认证失败");
            }
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("user/logout")
    @ApiOperation("用户登录")
    public Object logout(@Validated @RequestBody JSONObject userLogout) {
        try {
            log.info("login  : " + userLogout);
            // TODO 相关登出操作,如清理redis等
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("user/insert")
    @ApiOperation("新增用户信息")
    public Object insert(@Validated @RequestBody UserInsertRequest user) {
        try {
            log.info("receive value : " + user);
            userService.insert(user);
            return BaseResponse.getSuccessResponse();
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("user/insertUsers"/*, consumes = "application/json"*/)
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

    @PostMapping("user/update")
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

    @PostMapping("user/delete")
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

    @PostMapping("user/deleteUsers")
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

    @PostMapping({"user/searchUsers"})
    @ApiOperation("查询批量用户信息")
    public Object searchUsers(@RequestBody UserQueryRequest req) {
        try {
            log.info("receive value : " + req);
            PageResponse<UserQueryResponse> response = userService.searchUsers(req);
            return BaseResponse.getSuccessResponse(response);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("user/query")
    @ApiOperation("查询用户信息")
    public Object query(@RequestBody UserEntity user) {
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
