package com.luobin.manage_ssm.service;

import com.luobin.manage_ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;

    /**
     * 新建/保存权限
     * @param permission
     */
    void save(Permission permission) throws Exception;

    /**
     * 根据roleId查询此角色除此之外的其余权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId) throws Exception;
}
