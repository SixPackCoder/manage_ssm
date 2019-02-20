package com.luobin.manage_ssm.dao;

import com.luobin.manage_ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    /**
     * 根据角色id查询权限
     * @param RoleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in (select permissionid from role_permission where roleId=#{RoleId})")
    List<Permission> findPermissionByRoleId(String RoleId) throws Exception;

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 新建/保存权限
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    /**
     * 根据roleId查询此角色除此之外的其余权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);
}
