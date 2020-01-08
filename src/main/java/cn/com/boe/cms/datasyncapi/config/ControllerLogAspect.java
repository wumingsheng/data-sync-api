package cn.com.boe.cms.datasyncapi.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.boe.cms.datasyncapi.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class ControllerLogAspect {

    @Pointcut("execution(* cn.com.boe.cms.datasyncapi.controller..*.*(..))")
    public void excuteController() {}




    @AfterReturning(value = "excuteController()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("class_name = {},method_name = {},请求结束===返回值:{}", classType, methodName, GsonUtil.getJson(result));
    }

    @Before(value = "excuteController()")
    public void before(JoinPoint joinPoint) {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("class_name = {}", classType);
        log.info("method_name = {}", methodName);

        Object[] args = joinPoint.getArgs();

         RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            String params = "";

            //获取请求参数集合并进行遍历拼接
            if(args.length>0){
                if("POST".equals(method)){
                    Object object = args[0];
                    Map<String, Object> map = getKeyAndValue(object);
                    
                    params =  GsonUtil.getJson(map);
                    
                    
                    
                    
                }else if("GET".equals(method)){
                    params = queryString;
                }
            }


            log.info("请求开始===地址:"+uri);
            log.info("请求开始===类型:"+method);
            log.info("请求开始===参数:"+params);





    }
    private  Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class<? extends Object> userCla = (Class<? extends Object>) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            }
        return map;
    }




}
