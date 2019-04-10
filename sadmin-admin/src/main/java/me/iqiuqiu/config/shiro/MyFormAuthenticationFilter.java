package me.iqiuqiu.config.shiro;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	// 不对 OPTIONS 请求进行鉴权，否则跨域请求将失败
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (request instanceof HttpServletRequest) {
			if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
				return true;
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
		// TODO
		throw new UnauthenticatedException();

//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("code", ErrorEnum.E_20011.getErrorCode());
//		jsonObject.put("msg", ErrorEnum.E_20011.getErrorMsg());
//		PrintWriter out = null;
//		HttpServletResponse res = (HttpServletResponse) response;
//		try {
//			res.setCharacterEncoding("UTF-8");
//			res.setContentType("application/json");
//			out = response.getWriter();
//			out.println(jsonObject);
//		} catch (Exception e) {
//		} finally {
//			if (null != out) {
//				out.flush();
//				out.close();
//			}
//		}
//		return false;
	}

	@Bean
	public FilterRegistrationBean registration(MyFormAuthenticationFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}
}
