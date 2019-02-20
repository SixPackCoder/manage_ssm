package com.luobin.manage_ssm.controller;

import com.github.pagehelper.PageInfo;
import com.luobin.manage_ssm.domain.Orders;
import com.luobin.manage_ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 分页查询所有订单
     *
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findAllByPage.do")
    public ModelAndView findAllByPage(@RequestParam(name = "page", required = true, defaultValue =
            "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "10")
                                              Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAllByPage(page, pageSize);
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    /**
     * 未分页查询所有订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList", ordersList);
        mv.setViewName("orders-list");
        return mv;
    }

    /**
     * 根据id查询订单详情 解决
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
