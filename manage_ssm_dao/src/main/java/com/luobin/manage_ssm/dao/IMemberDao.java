package com.luobin.manage_ssm.dao;

import com.luobin.manage_ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 会员
 */
@Repository
public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;
}
