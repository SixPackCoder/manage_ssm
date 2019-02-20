package com.luobin.manage_ssm.service;

import com.luobin.manage_ssm.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 分页查询所有产品
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    List<Product> findAll(int page,int pageSize) throws Exception;

    /**
     * 保存一个产品
     * @param product
     */
    void sava(Product product);
}
