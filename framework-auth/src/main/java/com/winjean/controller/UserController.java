//package com.winjean.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.winjean.common.BaseResponse;
//import com.winjean.common.PageResponse;
//import com.winjean.model.entity.UserEntity;
//import com.winjean.model.request.UserInsertRequest;
//import com.winjean.model.response.UserQueryResponse;
//import com.winjean.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@Api("用户基本信息")
//@Slf4j
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping({"/","/index"})
//    @ApiOperation("index")
//    public String index() {
//        return "index";
//    }
//
//    @PostMapping("user/login")
//    @ResponseBody
//    @ApiOperation("用户登录")
//    public Object login(@RequestBody JSONObject userLogin) {
//        try {
//            String userName = userLogin.getString("userName");
//            String password = userLogin.getString("password");
//            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//
//            Subject subject = SecurityUtils.getSubject();
//            subject.login(token);
//
//            if(subject.isAuthenticated()){
//                return BaseResponse.getSuccessResponse("认证成功");
//            }else {
//                return BaseResponse.getFailureResponse("认证失败");
//            }
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping({"logout","user/logout"})
//    @ResponseBody
//    @ApiOperation("用户登录")
//    public Object logout() {
//
//        try {
//            // TODO 相关登出操作,如清理redis等
//            return BaseResponse.getSuccessResponse("logout success!");
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping("user/insert")
//    @ResponseBody
//    @ApiOperation("新增用户信息")
//    public Object insert(@Validated @RequestBody UserInsertRequest user) {
//        try {
//            userService.insert(user);
//            return BaseResponse.getSuccessResponse();
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping("user/insertUsers")
//    @ResponseBody
//    @ApiOperation("批量新增用户信息")
//    public Object insert(@RequestBody List<UserInsertRequest> users) {
//        try {
//            userService.insert(users);
//            return BaseResponse.getSuccessResponse();
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping("user/update")
//    @ResponseBody
//    @ApiOperation("更新用户信息")
//    public Object updateUser(@RequestBody UserEntity user) {
//        try {
//            userService.update(user);
//            return BaseResponse.getSuccessResponse();
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @GetMapping("user/delete")
//    @ResponseBody
//    @ApiOperation("删除用户信息")
//    public Object deleteUser(@RequestParam("id") String id ) {
//        try {
//            userService.delete(id);
//            return BaseResponse.getSuccessResponse();
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping("user/deleteUsers")
//    @ResponseBody
//    @ApiOperation("批量删除用户信息")
//    public Object deleteUsers(@RequestBody List<JSONObject> users) {
//        try {
//            userService.delete(users);
//            return BaseResponse.getSuccessResponse();
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping({"user/searchUsers"})
//    @ResponseBody
//    @ApiOperation("查询批量用户信息")
//    public Object searchUsers(@RequestBody JSONObject req) {
//        try {
//            PageResponse<UserQueryResponse> response = userService.searchUsers(req);
//            return BaseResponse.getSuccessResponse(response);
//        }catch (Exception e){
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//
//    @PostMapping("user/query")
//    @ResponseBody
//    @ApiOperation("查询用户信息")
//    public Object query(@RequestBody UserEntity user) {
//        try {
//
//            JSONObject u = userService.searchUser(user);
//            if(u == null){
//                return BaseResponse.getSuccessResponse("未找到相应的用户信息");
//            }
//            return BaseResponse.getSuccessResponse(u);
//        }catch (Exception e){
//            e.printStackTrace();
//            return BaseResponse.getFailureResponse(e.getMessage());
//        }
//    }
//}
