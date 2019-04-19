//package com.winjean.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.winjean.common.BaseService;
//import com.winjean.common.PageResponse;
//import com.winjean.common.exception.ServiceException;
//import com.winjean.mapper.UserMapper;
//import com.winjean.model.entity.UserEntity;
//import com.winjean.model.request.UserInsertRequest;
//import com.winjean.model.response.UserQueryResponse;
//import com.winjean.service.UserService;
//import com.winjean.utils.BeanUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//@Slf4j
//public class UserServiceImpl extends BaseService implements UserService{
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    @Transactional
//    public void insert(UserInsertRequest user) throws ServiceException {
//
//        JSONObject json = BeanUtils.appendBeanInfo(user,"winjean");
//        userMapper.insert(json);
//    }
//
//    @Override
//    @Transactional
//    public void insert(List<UserInsertRequest> users) {
//        List<JSONObject> list = new ArrayList<>();
//        for(UserInsertRequest user: users){
//            JSONObject json = BeanUtils.appendBeanInfo(user,"winjean");
//            list.add(json);
//        }
//
//        userMapper.insertUsers(list);
//    }
//
//    @Override
//    @Transactional
//    public void update(UserEntity user) {
//        JSONObject json = BeanUtils.updateBeanInfo(user,"winjean");
//        userMapper.update(json);
//    }
//
//    @Override
//    @Transactional
//    public void delete(String id) {
//        userMapper.delete(id);
//    }
//
//    @Override
//    @Transactional
//    public void delete(List<JSONObject> users) {
//         userMapper.deleteUsers(users);
//    }
//
//    /**
//     * readOnly=true
//     * 一次执行单条查询语句，没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性
//     * 一次执行多条查询语句，多条查询SQL必须保证整体的读一致性(在前条SQL查询之后，
//     * 后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态)
//    **/
//    @Override
//    @Transactional(readOnly = true)
//    public PageResponse<UserQueryResponse> searchUsers(JSONObject req) {
//        int pageNum =req.getInteger("pageNum");
//        int pageSize =req.getInteger("pageSize");
//
//        PageHelper.startPage(pageNum,pageSize);
//        Page<UserQueryResponse> users = userMapper.searchUsers();
//
//        PageResponse response = new PageResponse<UserQueryResponse>();
//        response.setPageNum(pageNum);
//        response.setPageSize(pageSize);
//
//        response.setTotal(users.getTotal());
//        response.setList(users);
//        return response;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public JSONObject searchUser(UserEntity user) {
//        return userMapper.searchUser(user);
//    }
//}
