package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.jasper.tagplugins.jstl.core.If;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet/bmr")
public class bmrservlet extends HttpServlet{

	//存放bmr的歷史紀錄
		private  List<Map<String, Object>> bmrList=new CopyOnWriteArrayList<>();
		
	//紀錄查詢
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//是否有deletid參數帶入 ? ，有參數代表要先刪除然後重新傳入map資料
		String deleteid=req.getParameter("deleteid");
		if(deleteid!=null) {
			//轉int
			int id=Integer.parseInt(deleteid);
			//刪除指定id
			bmrList.remove(id);
		}
		
		
		//處理資料
		//透過setAttribute將bmrlist放到req的屬性中，就可以將bmrlist傳送給jsp
		req.setAttribute("bmrList", bmrList);
		//將資料分派到jsp，透過分派器RequestDispatcher
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/jsp/bmr_list.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				// 編碼
				req.setCharacterEncoding("utf-8");
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;cherset=utf-8");
				
				
				//* bmr公式:
				//* 男性: BMR = 66 + (13.7 * 體重(kg)) + (5 * 身高(cm)) - (6.8 * 年齡)
				//* 女性: BMR = 655 + (9.6 * 體重(kg)) + (1.8 * 身高(cm)) - (4.7 * 年齡)
				
				//接收參數
				String username =req.getParameter("username");
				String usergender =req.getParameter("usergender");
				String userage =req.getParameter("userage");
				String userheight =req.getParameter("userheight");
				String userweight =req.getParameter("userweight");

				//計算bmr
				//將userheight 與userweight由string 轉double
				int age=Integer.parseInt(userage);
				double height=Double.parseDouble(userheight);
				double weight=Double.parseDouble(userweight);
				
				//根據性別計算bmr
				double bmr=0;
				switch (usergender) {
				case "M":
					bmr=66+ (13.7 *weight) + (5 * height) - (6.8 * age);
					break;

				case "F":
					bmr=655 + (9.6 * weight) + (1.8 *height) - (4.7 * age);
					break;
				}
				
				//建立map放置表單資訊與bmr內容，將資料存到bmrlist集合中
				Map<String,Object> map= new LinkedHashMap<>();
				map.put("name", username);
				map.put("gender", usergender);
				map.put("age", age);
				map.put("height", height);
				map.put("weight", weight);
				map.put("bmr", bmr);
				//將資料儲存到bmrlist集合中
				bmrList.add(map);
				
				
				//回應內容
				resp.getWriter().print("BMR Resule="+bmr+usergender);
	}
	
}
