package com.luobin.manage_ssm.service;

import com.luobin.manage_ssm.domain.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface IRoleService {
    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /**
     * 新建/保存角色
     * @param role
     */
    void save(Role role) throws Exception;

    /**
     * 根据用户id查询此用户除此之外的角色信息
     * @param id userId
     * @return
     * @throws Exception
     */
    List<Role> findOtherRole(String id) throws Exception;

    /**
     * 根据roleId查询role
     * @param roleId
     * @return
     */
    Role findById(String roleId) throws Exception;

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
