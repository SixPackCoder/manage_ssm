package com.luobin.manage_ssm.service.impl;

import com.luobin.manage_ssm.dao.ISysLogDao;
import com.luobin.manage_ssm.domain.SysLog;
import com.luobin.manage_ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class sysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }
}
