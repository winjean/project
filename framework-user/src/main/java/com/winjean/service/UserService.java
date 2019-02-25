package com.winjean.service;

import com.winjean.model.po.UserEntity;
import com.winjean.model.vo.SearchUsersRequest;
import com.winjean.model.vo.SearchUsersResponse;

import java.util.List;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/18 14:54
 * 修改人：Administrator
 * 修改时间：2018/10/18 14:54
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public interface UserService {

    void insert(UserEntity user);

    void insert(List<UserEntity> users);

    void update(UserEntity user);

    void delete(UserEntity user);

    void delete(List<UserEntity> users);

    SearchUsersResponse searchUsers(SearchUsersRequest req);

    UserEntity searchUser(UserEntity user);

}
