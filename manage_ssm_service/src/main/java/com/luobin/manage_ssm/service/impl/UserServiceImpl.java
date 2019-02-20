package com.luobin.manage_ssm.service.impl;

import com.luobin.manage_ssm.dao.IUserDao;
import com.luobin.manage_ssm.domain.Role;
import com.luobin.manage_ssm.domain.UserInfo;
import com.luobin.manage_ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    //密码加密用
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println(username);
        UserInfo userInfo = null;
        try {
             userInfo =userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userInfo);
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);
        //username, password, true, true, true, true, authorities
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,authoritys);
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    /**
     * 新建用户保存用户
     *
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密  重新设置进用户对象中
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 给用户添加角色
     *
     * @param userId  指定用户id
     * @param roleIds 角色id数组  可能不止一个角色
     */
    @Override
    public void addRoleToUSer(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
