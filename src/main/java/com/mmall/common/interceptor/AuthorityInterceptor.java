package com.mmall.common.interceptor;


import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle");
		//请求中Controller中的方法名
		HandlerMethod handleMethod = (HandlerMethod)handler;
		
		//解析HandlerMathod
		String methodName = handleMethod.getMethod().getName();
		String claseName = handleMethod.getBean().getClass().getSimpleName();
		
		//解析参数，具体的参数key以及value是什么，我们打印日志
		StringBuffer requestParamBuffer = new StringBuffer();
		Map paramMap = request.getParameterMap();
		Iterator it = paramMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String mapKey = (String) entry.getKey();
			
			String mapValue = org.apache.commons.lang3.StringUtils.EMPTY;
			
			//request参数map，value值返回的是一个String[]
			Object obj = entry.getValue();
			if(obj instanceof String[]) {
				String[] strs = (String[])obj;
				mapValue = Arrays.toString(strs);
			}
			requestParamBuffer.append(mapKey).append("=").append(mapValue);
		}
		
		if(StringUtils.equals(claseName, "UserManageController") && 
				StringUtils.equals(methodName, "login")) {
			log.info("权限拦截器拦截到请求,className:{},methodName:{}",claseName,methodName);
			return true;
		}
		
		User user = null;
		
		String loginToken = CookieUtil.readLoginToken(request);
		if (StringUtils.isNotEmpty(loginToken)){
			String userjsonStr = RedisShardedPoolUtil.get(loginToken);
	        user = JsonUtil.string2Obj(userjsonStr,User.class);
        }
		
		if(user == null || (user.getRole().intValue() != Const.Role.ROLE_ADMIN)) {
			//返回false，即不会调用Controller里的方法
			response.reset();//要添加reset方法，否则会报异常
			response.setCharacterEncoding("UTF-8");//设置编码，防止乱码
			response.setContentType("application/json;charset=UTF-8");//设置返回值类型
			
			PrintWriter out = response.getWriter();
			
			if(user == null) {
				out.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户未登录")));
				
			}else {
				out.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户无权限操作")));
			}
			out.flush();
			out.close();//关闭
			
			return false;
		}
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion");
	}

}
