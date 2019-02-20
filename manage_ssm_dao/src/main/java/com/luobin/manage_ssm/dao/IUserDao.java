package com.luobin.manage_ssm.dao;

import com.luobin.manage_ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    /**
     * 根据username查找用户
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.luobin.manage_ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;

    /**
     * 根据id查询userInfo
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.luobin.manage_ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 新建/保存用户
     *
     * @param userInfo
     */
    @Insert("insert into users(username,password,email,phoneNum,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据roleId查找用户
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from user where id in (select userId from users_role where roleId = #{roleId})")
    List<UserInfo> findByRoleId(String roleId) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
