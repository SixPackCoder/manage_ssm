package com.luobin.manage_ssm.controller;

import com.luobin.manage_ssm.domain.Role;
import com.luobin.manage_ssm.domain.UserInfo;
import com.luobin.manage_ssm.service.IRoleService;
import com.luobin.manage_ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(path = "/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping(path = "/save.do")
    @PreAuthorize("'张三'==authentication.principal.username or hasRole('ROLE_ADMIN')")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 根据用户id查询此用户除此之外的角色信息
     *
     * @param id
     * @return
     * @throws Exception
     *
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        UserInfo userInfo = userService.findById(id);
        List<Role> roleList = roleService.findOtherRole(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roleList);
        if (roleList == null || roleList.size() < 0) {
            mv.setViewName("errorPage-01");
        } else {
            mv.setViewName("user-role-add");
        }
        return mv;
    }

    /**
     * 给指定用户添加指定的角色
     *
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUSer(userId, roleIds);
        return "redirect:findAll.do";
    }
}
