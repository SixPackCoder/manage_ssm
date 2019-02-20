package com.luobin.manage_ssm.dao;

import com.luobin.manage_ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 旅客
 */
@Repository
public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    Traveller findByOrdersId(String ordersId) throws Exception;
}
