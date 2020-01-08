package cn.com.boe.cms.datasyncapi.config;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import cn.com.boe.cms.datasyncapi.util.GsonUtil;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class ServiceLogAspect {

    @Pointcut("execution(* cn.com.boe.cms.datasyncapi.service..*.*(..))")
    public void excuteService() {}




    @AfterReturning(value = "excuteService()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("class_name = {},method_name = {},请求结束===返回值:{}", classType, methodName, GsonUtil.getJson(result));
    }

    @Before(value = "excuteService()")
    public void before(JoinPoint joinPoint) {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("class_name = {}", classType);
        log.info("method_name = {}", methodName);

        Object[] args = joinPoint.getArgs();

        try {
            /**
             * 获取方法参数名称
             */
            String[] paramNames = getFieldsName(classType, methodName);

            /**
             * 打印方法的参数名和参数值
             */
            logParam(paramNames, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 判断是否为基本类型：包括String
     * 
     * @param clazz clazz
     * @return true：是; false：不是
     */
    private boolean isPrimite(Class<?> clazz) {
        if (clazz.isPrimitive() || clazz == String.class) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 打印方法参数值 基本类型直接打印，非基本类型需要重写toString方法
     * 
     * @param paramsArgsName  方法参数名数组
     * @param paramsArgsValue 方法参数值数组
     */
    private void logParam(String[] paramsArgsName, Object[] paramsArgsValue) {
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            log.info("该方法没有参数");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paramsArgsName.length; i++) {
            // 参数名
            String name = paramsArgsName[i];
            // 参数值
            Object value = paramsArgsValue[i];
            builder.append(name + " = ");
            if (isPrimite(value.getClass())) {
                builder.append(value + "  ,");
            } else {
                builder.append(value.toString() + "  ,");
            }
        }
        log.info(builder.toString());
    }

    /**
     * 使用javassist来获取方法参数名称
     * 
     * @param class_name  类名
     * @param method_name 方法名
     * @return
     * @throws Exception
     */
    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramsArgsName.length; i++) {
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }

}