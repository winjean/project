package com.winjean.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winjean.mapper.UserMapper;
import com.winjean.model.po.UserEntity;
import com.winjean.model.vo.SearchUsersRequest;
import com.winjean.model.vo.SearchUsersResponse;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/18 14:30
 * 修改人：Administrator
 * 修改时间：2018/10/18 14:30
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insert(UserEntity user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional
    public void insert(List<UserEntity> users) {
        userMapper.insertUsers(users);
    }

    @Override
    @Transactional
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @Override
    @Transactional
    public void delete(UserEntity user) {
        userMapper.delete(user);
    }

    @Override
    @Transactional
    public void delete(List<UserEntity> users) {
        userMapper.deleteUsers(users);
    }

    /**
     * readOnly=true
     * 一次执行单条查询语句，没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性
     * 一次执行多条查询语句，多条查询SQL必须保证整体的读一致性(在前条SQL查询之后，
     * 后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态)
    **/
    @Override
    @Transactional(readOnly = true)
    public SearchUsersResponse searchUsers(SearchUsersRequest req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Page<UserEntity> users = userMapper.searchUsers();

        SearchUsersResponse response = new SearchUsersResponse();
        response.setPageNum(req.getPageNum());
        response.setPageSize(req.getPageSize());

        response.setTotal(users.getTotal());
        response.setUsers(users);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity searchUser(UserEntity user) {
        return userMapper.searchUser(user);
    }
}
