package com.luobin.manage_ssm.service.impl;

import com.luobin.manage_ssm.dao.IRoleDao;
import com.luobin.manage_ssm.domain.Role;
import com.luobin.manage_ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有角色
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 新建/保存角色
     *
     * @param role
     */
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    /**
     * 根据用户id查询此用户除此之外的角色信息
     *
     * @param id userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRole(String id) throws Exception {

        List<Role> roleList = roleDao.findOtherRole(id);
        return roleList;

    }

    /**
     * 根据roleId查询role
     *
     * @param roleId
     * @return
     */
    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
