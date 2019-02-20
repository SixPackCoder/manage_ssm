package com.luobin.manage_ssm.controller;

import com.luobin.manage_ssm.domain.SysLog;
import com.luobin.manage_ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date startTime;//访问时间
    private Method executionMethod;//访问的方法
    private Class executionClass;//访问的类

    /**
     * 前置通知 获取访问的时间,类,方法
     *
     * @param jp
     */
    @Before("execution(* com.luobin.manage_ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date();//访问的开始时间
        executionClass = jp.getTarget().getClass();//获取访问的类
        //下面开始获取访问的方法对象
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//访问方法的参数
        if (args.length == 0 || args == null) {//无参数
            executionMethod = executionClass.getMethod(methodName);//这个只能获取无参的方法
        } else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classArgs);//获取有参数的方法
        }
    }

    /**
     * 后置通知  主要获取日志中其它信息，时长、ip、url...
     *
     * @param jp
     */
    @After("execution(* com.luobin.manage_ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        //1.获取访问时长
        long time = new Date().getTime() - startTime.getTime();//获取访问时长

        //2.下面获取url 类上的+方法上的
        String url = "";
        //获取类上和方法上的requestMapping的值
        //前提 访问的不是本类
        if (executionClass != null && executionMethod != null && executionClass != LogAop.class) {
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
//                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
//                    String[] methodValue = methodAnnotation.value();
                    url = classAnnotation.path()[0] + methodAnnotation.path()[0];//url就是类上的url+方法上的url
                }
            }
        }

        //3.获取ip
        String ip = request.getRemoteAddr();

        //4.获取登录的用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        //5.封装sysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setExecutionTime(time);
        sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(userName);
        sysLog.setVisitTime(startTime);
        sysLog.setVisitTimeStr(startTime.toString());

        //6.保存
        sysLogService.save(sysLog);
    }

}
