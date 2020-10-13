package demo.support;

import demo.config.annotation.LogRecord;
import demo.entity.Log;
import demo.entity.User;
import demo.service.LogserviceImpl;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class AoplogImpl {

    @Autowired
    private LogserviceImpl logservice;

    @Pointcut("@annotation(demo.config.annotation.LogRecord)")
    public void logcontroller(){}

    @Around("logcontroller()")
    public Object aroundlog(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request=getHttpServletRequest();

        if(request==null){
            return null;
        }

        Log log=new Log();

        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();

        LogRecord logRecord=method.getAnnotation(LogRecord.class);

        Object proceed =joinPoint.proceed();

        if(logRecord!=null&&proceed!=null){

            String op=logRecord.operation();
            String type=logRecord.type();

            String token=request.getHeader("token");

            if(StringUtils.isBlank(token)){//isblank判断字符串是否为空，或者为空字符串组成或者=“”
                return null;
            }

            User user=JWTUtil.unsign(token,User.class);
            if(user==null){
                return null;
            }

            log.setLog_op(op);
            log.setLog_type(type);
            log.setUid(user.getId());
            log.setLtime(System.currentTimeMillis());
            log.setUrl(request.getRequestURI());

            logservice.log_insert(log);
        }
        return proceed;
    }


    private HttpServletRequest getHttpServletRequest(){
        RequestAttributes attributes= RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) attributes;
        return servletRequestAttributes!=null? servletRequestAttributes.getRequest():null;
    }
}
