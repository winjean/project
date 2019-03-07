package com.winjean.mapper;

import com.github.pagehelper.Page;
import com.winjean.model.po.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(user_id,user_name,birthdate,sex,create_user,create_time,update_user,update_time)" +
            "values "+
            "(#{user_id},#{user_name},#{birthdate},#{sex},#{create_user},#{create_time},#{update_user},#{update_time})")
    int insert(UserEntity user);

    @Insert("<script> " +
                "insert into t_user(user_id,user_name,birthdate,sex,create_user,create_time,update_user,update_time)" +
                "values "+
                    "<foreach collection=\"users\" item=\"user\" separator=\",\" >" +
                        "(#{user.user_id},#{user.user_name},#{user.birthdate},#{user.sex},#{user.create_user},#{user.create_time},#{user.update_user},#{user.update_time})"+
                    "</foreach>" +
            "</script>")
    int insertUsers(@Param("users") List<UserEntity> users);

    @Update("update t_user set " +
                "user_name=#{user_name},birthdate= #{birthdate},sex=#{sex}," +
                "create_user=#{create_user},create_time=#{create_time}," +
                "update_user=#{update_user},update_time=#{update_time} " +
            "where user_id =#{user_id}")
    int update(UserEntity user);

    @Delete("delete from t_user where user_id=#{user_id}")
    int delete(UserEntity user);

    @Delete("<script> " +
            "delete from t_user where user_id in " +
            "<foreach collection=\"users\" item=\"user\" open=\"(\" close=\")\" separator=\",\" >" +
            "#{user.user_id}"+
            "</foreach>" +
            "</script>")
    int deleteUsers(@Param("users") List<UserEntity> users);

    @Select("select * from t_user")
    Page<UserEntity> searchUsers();

    @Select("select * from t_user where user_id=#{user_id}")
    UserEntity searchUser(UserEntity user);
}
