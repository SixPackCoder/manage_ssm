package com.luobin.manage_ssm.service;

import com.luobin.manage_ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    /**
     * 分页查询所有订单
     * @return
     * @throws Exception
     */
    List<Orders> findAllByPage(int page,int size) throws Exception;

    /**
     * 未分页查询所有订单
     * @return
     * @throws Exception
     */
    List<Orders> findAll() throws Exception;

    /**
     * 根据id查询订单详情
     * @param id
     * @return
     */
    Orders findById(String id) throws Exception;
}
