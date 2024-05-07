package guestbook.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//通關密碼:code=1234
//使用者輸入通關密碼才可以進入留言板(guestbook)
@WebFilter("/guestbook")
public class PassCodeFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		System.out.println("passcodefilter過濾"+request.getMethod());
		if(request.getMethod().equals("GET") ) {
			//檢查是否有deleteid
			if(request.getParameter("deleteId")==null) {
				//檢查Code是否為1234
				if(request.getParameter("code")==null || !request.getParameter("code").equals("1234")) {
					response.getWriter().print("passcode error !!");
					return;
				}
			}
			
			
		}
		
		
		//放行
		chain.doFilter(request, response);
	}
	
	
	
	
}
