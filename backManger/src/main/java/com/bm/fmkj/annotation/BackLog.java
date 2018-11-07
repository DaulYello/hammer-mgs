package com.bm.fmkj.annotation;

import java.lang.annotation.*;

/**
 * @function 自定义操作日志
 * @author hunagshuang
 * @date 2018-11-05
 */
//@Target({ElementType.PARAMETER,ElementType.METHOD})
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BackLog {

    /**
     * 模块
     * @return
     */
    String module() default "";

    /**
     * 功能描述
     * @return
     */
    String actionDesc() default "";
}
