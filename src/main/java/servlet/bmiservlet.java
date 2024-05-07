package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet/bmi")
public class bmiservlet extends HttpServlet{
	//存放bmi的歷史紀錄
	private  List<Map<String, Object>> bmiList=new CopyOnWriteArrayList<>();
	
	//給查詢使用
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;cherset=utf-8");
		
		//將bmilist印出
		//resp.getWriter().print(bmiList);
		//回應內容(html)
		resp.getWriter().print("<html>");
		resp.getWriter().print("<head>");
		resp.getWriter().print("</head>");
		resp.getWriter().print("<body>");
		resp.getWriter().print("<table border='1'>");
		//表頭
		resp.getWriter().print("<thead>");
		resp.getWriter().print("<tr>");
		resp.getWriter().print("<th>name</th>");
		resp.getWriter().print("<th>height</th>");
		resp.getWriter().print("<th>weight</th>");
		resp.getWriter().print("<th>bmi</th>");
		resp.getWriter().print("<tr>");
		resp.getWriter().print("<thead>");
		//表格資料
		resp.getWriter().print("<tbody>");
		
		bmiList.forEach(map->{
			try {
				resp.getWriter().print("<tr>");
				resp.getWriter().print("<td>"+map.get("name")+"</td>");
				resp.getWriter().print("<td>"+map.get("height")+"</td>");
				resp.getWriter().print("<td>"+map.get("weight")+"</td>");
				resp.getWriter().print("<td>"+map.get("bmi")+"</td>");
				resp.getWriter().print("</tr>");
			}catch (Exception e) {
				// TODO: handle exception
			}
		});
		resp.getWriter().print("</tbody>");
		
		
		//
		resp.getWriter().print("</table>");
		resp.getWriter().print("</body>");
		resp.getWriter().print("</html>");
	}
	
	
	
	//給表單使用
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;cherset=utf-8");
		
		//接收參數
		String username =req.getParameter("username");
		String userheight =req.getParameter("userheight");
		String userweight =req.getParameter("userweight");

		//計算bmi
		//將userheight 與userweight由string 轉double
		double height=Double.parseDouble(userheight);
		double weight=Double.parseDouble(userweight);
		double bmi=weight/Math.pow(height/100,2);
		
		//建立map放置表單資訊與bmi內容，將資料存到bmilist集合中
		Map<String,Object> map= new LinkedHashMap<>();
		map.put("name", username);
		map.put("height", height);
		map.put("weight", weight);
		map.put("bmi", bmi);
		//將資料儲存到bmilist集合中
		bmiList.add(map);
		
		
		
		//回應內容
		resp.getWriter().print("BMI Resule="+bmi);
	}

}
