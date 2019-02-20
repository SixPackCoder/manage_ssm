package com.luobin.manage_ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.luobin.manage_ssm.dao.IProductDao;
import com.luobin.manage_ssm.domain.Product;
import com.luobin.manage_ssm.service.IProductService;
import com.luobin.manage_ssm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 分页查询所有产品
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll(page,pageSize);
    }

    /**
     * 保存一个产品
     * @param product
     */
    @Override
    public void sava(Product product) {
        productDao.save(product);
    }
}
