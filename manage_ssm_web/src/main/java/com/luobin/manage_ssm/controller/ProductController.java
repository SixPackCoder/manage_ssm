package com.luobin.manage_ssm.controller;

import com.github.pagehelper.PageInfo;
import com.luobin.manage_ssm.domain.Product;
import com.luobin.manage_ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    /**
     * 分页查询所有产品
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findAllByPage.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "10")Integer pageSize)
    throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 保存一个产品
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/save.do")
    public String save(Product product) throws Exception{
        productService.sava(product);
        return "redirect:findAllByPage.do?page=1&size=4";
    }
}
