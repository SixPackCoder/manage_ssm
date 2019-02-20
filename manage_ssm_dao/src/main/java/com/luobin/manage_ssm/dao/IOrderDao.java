package com.luobin.manage_ssm.dao;

import com.luobin.manage_ssm.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDao {
    /**
     * 分页查询所有订单
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.luobin.manage_ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAllByPage() throws Exception;

    /**
     * 未分页查询所有订单
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.luobin.manage_ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;


    /**
     * 根据id查询订单详情
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.luobin.manage_ssm.dao.IProductDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select =
                    "com.luobin.manage_ssm.dao.ITravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",one = @One(select =
                    "com.luobin.manage_ssm.dao.IMemberDao.findById")),
    })
    Orders findById(String id) throws Exception;
}
