package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZZH
 * @date 2018/11/30 0030 17:56
 **/
@Slf4j
public class CookieUtil {
    private final static String COOKIE_DOMAIN = "zzh.com";
    private final static String COOKIE_NAME = "mmall_login_token";

    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                log.info("read cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                if (StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    log.info("return cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void writeLoginToken(HttpServletResponse response, String token){
        Cookie cookie = new Cookie(COOKIE_NAME ,token);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath("/");//代表设置在根目录
        cookie.setHttpOnly(true);
        //单位为秒
        //如果maxage不设置，cookie将不会写入硬盘，而是写入内存，仅当前页面有效
        cookie.setMaxAge(60 * 60 * 24 * 365);//-1代表永久
        log.info("write cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
        response.addCookie(cookie);
    }

    public static void delLoginToken(HttpServletResponse response,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    cookie.setDomain(COOKIE_DOMAIN);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);//设置为0，代表删除此cookie
                    log.info("del cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                    response.addCookie(cookie);
                    return;
                }
            }
        }
    }
}
