package com.luobin.manage_ssm.controller;

import com.luobin.manage_ssm.domain.Permission;
import com.luobin.manage_ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有权限
     *
     * @return
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 新建/保存权限
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
