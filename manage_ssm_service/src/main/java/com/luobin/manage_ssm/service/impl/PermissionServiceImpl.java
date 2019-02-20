package com.luobin.manage_ssm.service.impl;

import com.luobin.manage_ssm.dao.IPermissionDao;
import com.luobin.manage_ssm.domain.Permission;
import com.luobin.manage_ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    /**
     * 新建/保存权限
     *
     * @param permission
     */
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    /**
     * 根据roleId查询此角色除此之外的其余权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return permissionDao.findOtherPermission(roleId);
    }
}
