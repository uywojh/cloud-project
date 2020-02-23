package com.simons.cloud.user.provider.service.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class UserPremissionFilter extends ZuulFilter{
	
	@Resource
	private UrlPermissionIntercept urlPermissionIntercept;
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        boolean result = false;
		try {
			result = urlPermissionIntercept.preHandle(request);
			ctx.setSendZuulResponse(result);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", result);// 设值，让下一个Filter看到上一个Filter的状态
		} catch (Exception e) {
			ctx.setSendZuulResponse(result);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody(e.getMessage());// 返回错误内容
            ctx.set("isSuccess", result);
		}
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;// 是否执行该过滤器，此处为true，说明需要过滤
	}
 
	@Override
	public int filterOrder() {
		return 0;// 优先级为0，数字越大，优先级越低
	}
 
	@Override
	public String filterType() {
		return "pre";// 前置过滤器
	}


}
