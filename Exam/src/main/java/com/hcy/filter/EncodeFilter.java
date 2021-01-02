package com.hcy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class CharSetFilter
 * 该过滤器用于同一字符编码，防止乱码
 * 
 */
@WebFilter("/EncodeFilter")
public class EncodeFilter implements Filter {

    /**
     * Default constructor. 
     */
	
	
	private String mCharSet ;
    public EncodeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 同一字符编码为utf-8
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//业务逻辑
//		HttpServletRequest req = (HttpServletRequest) request;
//		String path = req.getServletPath();
//		if(path.contains(".css")||path.contains(".png")||path.contains(".js")||path.contains("/IdentifyingCodeServlet")){
			request.setCharacterEncoding(this.mCharSet);
			response.setCharacterEncoding(this.mCharSet);
			// pass the request along the filter chain
			System.out.println("doFilter--->" + this.mCharSet);
			chain.doFilter(request, response);
//		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext = fConfig.getServletContext();
		this.mCharSet = servletContext.getInitParameter("charSet");
		
	}

}
