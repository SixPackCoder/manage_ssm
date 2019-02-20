package com.luobin.manage_ssm.dao;

import com.luobin.manage_ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    /**
     * 根据userId查询role 牵涉到中间表 user_role
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in( select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class, many = @Many(select = "com.luobin.manage_ssm.dao.IPermissionDao.findPermissionByRoleId"))
            /* @Result(column = "id",property = "users",javaType = java.util.List.class,many = @Many(select = "com.luobin.manage_ssm.dao.IUserDao.findByRoleId"))*/
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 新建/保存角色
     *
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 查询除此用户id对应的角色的其他所有角色
     *
     * @param id
     * @return
     */
    @Select("select * from role where id not in( select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id) throws Exception;

    /**
     * 根据roleId查询role
     * @return
     * @param roleId
     */
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId) throws Exception;

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId")String roleId,@Param(value = "permissionId")String permissionId);
}
