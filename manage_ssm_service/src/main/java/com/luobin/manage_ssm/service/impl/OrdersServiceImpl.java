package com.luobin.manage_ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.luobin.manage_ssm.dao.IOrderDao;
import com.luobin.manage_ssm.domain.Orders;
import com.luobin.manage_ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrderDao orderDao;
    /**
     * 查询所有订单
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAllByPage(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.findAllByPage();
    }

    /**
     * 未分页查询所有订单
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll() throws Exception {
        return orderDao.findAll();
    }

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    @Override
    public Orders findById(String id) throws Exception {
        return orderDao.findById(id);
    }
}
