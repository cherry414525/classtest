package room.filter;

import java.io.IOException;

import org.apache.tomcat.jakartaee.bcel.classfile.Code;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/room/*" ,"/bookingroom/*"})
public class PassCodeFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		System.out.println("PassCodeFilter for Room,session="+session.getId());
		String code = request.getParameter("code")==null?"":request.getParameter("code");
		String passCode = session.getAttribute("passCode")+"";
		System.out.println("code="+code+",passCode="+passCode);
		if(code.equals(passCode)) {
			//將code寫到session屬性中
			session.setAttribute("code", code);
			System.out.println("code="+code);
			chain.doFilter(request, response);//pass
		}else if(session.getAttribute("code")!=null) {//檢查session屬性中的code是否有資料
			chain.doFilter(request, response);//pass
			
		}else {
			//response.getWriter().print("Wrong PassCode");
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/passcode.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}
	
}