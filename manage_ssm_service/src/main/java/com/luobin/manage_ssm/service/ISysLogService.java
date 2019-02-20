package com.luobin.manage_ssm.service;

import com.luobin.manage_ssm.domain.SysLog;

public interface ISysLogService {
    void save(SysLog sysLog) throws Exception;
}
