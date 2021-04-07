package io.payment.api.tenant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.payment.api.tenant.TenantContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestTenantInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		log.debug("-------- PRE HANDLE ----------");
		log.debug("We are Intercepting the Request");
		String requestURI = request.getRequestURI();
		log.debug("RequestURI::" + requestURI);

		String tenantID = request.getHeader("X-TenantID");
		log.debug("Search for X-TenantID :: " + tenantID);
		log.debug("-------------------------------");

		if (tenantID != null) {
			TenantContext.setCurrentTenant(tenantID);
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}

}
