package com.luobin.manage_ssm.controller;

import com.luobin.manage_ssm.domain.Permission;
import com.luobin.manage_ssm.domain.Role;
import com.luobin.manage_ssm.service.IPermissionService;
import com.luobin.manage_ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有角色
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 新建/保存角色
     *
     * @param role
     * @return
     */
    @RequestMapping(path = "/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 给角色添加权限
     * 1.根据roleId查询角色
     * 2.根据roleId查询此角色除此之外的其余权限
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        List<Permission> permissionList = permissionService.findOtherPermission(roleId);
        mv.addObject("permissionList", permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 给角色添加权限信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/addPermissionToRole.do")
    public String addPermissionToRole(
            @RequestParam(name = "roleId", required = true) String roleId,
            @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
