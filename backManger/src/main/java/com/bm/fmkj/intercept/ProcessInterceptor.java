/**
 * 
 */
package com.bm.fmkj.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 * 解决跨域的问题
 *
 */
public class ProcessInterceptor implements HandlerInterceptor {
	 
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*"); //允许跨域

        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, X-Fmkj-token");

        httpServletResponse.addHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");

        String method= httpServletRequest.getMethod();

        if (method.equals("OPTIONS")){
            httpServletResponse.setStatus(200);
            return false;
        }
 
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
 
    }
}
