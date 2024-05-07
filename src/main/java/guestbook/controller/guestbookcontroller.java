package guestbook.controller;

import java.io.IOException;
import java.util.List;

import guestbook.model.GuestBook;
import guestbook.service.GuestBookService;
import guestbook.service.GuestBookServicImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guestbook")
public class guestbookcontroller extends HttpServlet{
	//一般html只接受get、post
	
	private GuestBookService service=new  GuestBookServicImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//是否有deletid參數帶入 ? ，有參數代表要先刪除然後重新傳入map資料
		String deleteId=req.getParameter("deleteId");
		if(deleteId!=null) {
			//轉int
			Integer id=Integer.valueOf(deleteId);
			//刪除指定id
			service.removebyid(id);
		}
		
		//取得所有留言紀錄
		List<GuestBook> guestBooks=service.quaryall();
		req.setAttribute("guestBooks", guestBooks);
		//內重導到guestbook.jsp
		RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/jsp/guestbook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username =req.getParameter("username");
		String content =req.getParameter("content");
		
		boolean state=service.add(username, content);
		req.setAttribute("state",state);
		
		//內重導到jsp
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/jsp/guestbook_result.jsp");
		rd.forward(req, resp);
	}
		
		
}

	

