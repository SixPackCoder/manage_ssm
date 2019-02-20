package com.luobin.manage_ssm.service;

import com.luobin.manage_ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户查询  登录  添加  详情
 */
public interface IUserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 新建用户保存用户
     * @param userInfo
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;

    /**
     *给用户添加角色
     * @param userId 指定用户id
     * @param roleIds 角色id数组  可能不止一个角色
     */
    void addRoleToUSer(String userId, String[] roleIds) throws Exception;
}
