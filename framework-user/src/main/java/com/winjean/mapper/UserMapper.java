package com.winjean.mapper;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.winjean.common.exception.DaoException;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.response.UserQueryResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(id,user_name,birthday,email,telephone,sex,password,state,create_user,create_time,update_user,update_time)" +
            "values "+
            "(#{id},#{name},#{birthday},#{email},#{telephone},#{sex},#{password},#{state},#{createUser},#{createTime},#{updateUser},#{updateTime})")
    int insert(JSONObject user) throws DaoException;


    @Insert("<script> " +
                "insert into t_user(id,user_name,birthday,email,telephone,sex,password,state,create_user,create_time,update_user,update_time)" +
                "values "+
                    "<foreach collection=\"users\" item=\"user\" separator=\",\" >" +
                        "(#{id},#{name},#{birthday},#{email},#{telephone},#{sex},#{password},#{state},#{createUser},#{createTime},#{updateUser},#{updateTime})"+
                    "</foreach>" +
            "</script>")
    int insertUsers(@Param("users") List<JSONObject> users);

    @Update("update t_user set " +
                "user_name=#{name},birthday= #{birthday},sex=#{sex}," +
                "telephone=#{telephone},email=#{email},state=#{state}," +
                "update_user=#{updateUser},update_time=#{updateTime} " +
            "where id =#{id}")
    int update(JSONObject user);

    @Delete("delete from t_user where id=#{id}")
    int delete(String id);

    @Delete("<script> " +
            "delete from t_user where id in " +
            "<foreach collection=\"jsons\" item=\"json\" open=\"(\" close=\")\" separator=\",\" >" +
                "#{json.id}"+
            "</foreach>" +
            "</script>")
    int deleteUsers(@Param("jsons") List<JSONObject> jsons);

    @Select("select id,user_name as name,birthday,email,sex,telephone,create_user as createUser,create_time as createTime from t_user")
    Page<UserQueryResponse> searchUsers();

    @Select("select id,user_name as name,birthday,email,sex,telephone,create_user as createUser,create_time as createTime from t_user where id=#{id}")
    JSONObject searchUser(UserEntity user);
}
