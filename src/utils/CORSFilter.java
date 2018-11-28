package utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Enable it for Servlet 3.x implementations
@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class CORSFilter implements Filter {

	public CORSFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		System.out.println("CORSFilter HTTP Request: " + request.getMethod());

		if (!((HttpServletResponse) servletResponse).containsHeader("Access-Control-Allow-Origin")) {
			((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
		}
		if (!((HttpServletResponse) servletResponse).containsHeader("Access-Control-Allow-Methods")) {
			((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
					"GET, OPTIONS, HEAD, PUT, POST, DELETE");
		}
		if (!((HttpServletResponse) servletResponse).containsHeader("Access-Control-Allow-Headers")) {
			((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
		}

		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		if (request.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		chain.doFilter(request, servletResponse);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}