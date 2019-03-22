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

    @Update({"update t_user set ",
            "<trim prefix=\"set\" suffixOverrides=\",\">",

            "<when test='name != null'> name = #{name}, </when>",
            "<when test='birthday != null'> birthday = #{birthday}, </when>",
            "<when test='sex != null'> sex = #{sex}, </when>",
            "<when test='telephone != null'> telephone = #{telephone}, </when>",
            "<when test='email != null'> email = #{email}, </when>",
            "<when test='state != null'> state = #{state}, </when>",
            "<when test='update_user != null'> update_user = #{update_user}, </when>",
            "<when test='update_time != null'> update_time = #{update_time}, </when>",

            "</trim>",

            "where id =#{id}",
            "</script>"
    })
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
