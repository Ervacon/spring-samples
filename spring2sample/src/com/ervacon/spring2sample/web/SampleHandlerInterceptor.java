package com.ervacon.spring2sample.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SampleHandlerInterceptor implements HandlerInterceptor {
	
	private String prefix = "<<<";
	private String suffix = ">>>";
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	private String decorate(String message) {
		StringBuilder buf = new StringBuilder();
		buf.append(prefix).append(message).append(suffix);
		return buf.toString();
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(decorate("preHandle() for " + handler));
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(decorate("postHandle() for " + handler));
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(decorate("afterCompletion() for " + handler));
	}

}
