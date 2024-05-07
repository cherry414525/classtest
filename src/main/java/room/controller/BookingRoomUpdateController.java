package room.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import room.model.dto.BookingRoomCount;
import room.model.dto.BookingRoomDto;
import room.model.po.BookingRoom;
import room.model.po.Room;
import room.service.BookingRoomService;
import room.service.RoomService;

@WebServlet("/bookingroom/update")
public class BookingRoomUpdateController extends HttpServlet {
	// 建立 RoomService 物件
	private BookingRoomService bookingRoomService = new BookingRoomService();
	private RoomService roomService = new RoomService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			// 取得 bookingRoom ID
			Integer bookingroomId = Integer.parseInt(req.getParameter("updateId"));
			//bookingroomdto
			BookingRoomDto dto=new BookingRoomDto();
			dto.setBookingId(bookingroomId);
			dto.setCheckinDate(bookingRoomService.getBookingRoom(bookingroomId).getCheckinDate());
			dto.setCreateTime(bookingRoomService.getBookingRoom(bookingroomId).getCreateTime());
			dto.setUserId(bookingRoomService.getBookingRoom(bookingroomId).getUserId());
			Room room = roomService.getRoom(bookingRoomService.getBookingRoom(bookingroomId).getRoomId());
			dto.setRoom(room);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			//System.out.println(sdf.format(bookingRoomService.getBookingRoom(bookingroomId).getCheckinDate()));
			// 取得 bookingRoom 資料
			req.setAttribute("dto", dto);
			req.setAttribute("checkinDate", sdf.format(bookingRoomService.getBookingRoom(bookingroomId).getCheckinDate()));
			req.setAttribute("bookingroomId", bookingroomId);
			req.setAttribute("bookingroom", bookingRoomService.getBookingRoom(bookingroomId));
			req.setAttribute("rooms", roomService.getRooms());
			// 轉發至 bookingRoom 修改頁面
			req.getRequestDispatcher("/WEB-INF/jsp/bookingroom_update.jsp").forward(req, resp);
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 取得 bookingRoom ID
		Integer bookingroomId = Integer.parseInt(req.getParameter("bookingroomId"));
		// 取得 roomId 及 checkin-date 參數
		Integer roomId = Integer.valueOf(req.getParameter("room_id"));
		
		Integer userId = 1;//登入者的id
		String checkinDate = req.getParameter("checkin_date");
		// 新增房間
		bookingRoomService.updateBookingRoom(bookingroomId,roomId,userId, checkinDate);
		// 重新導向至首頁
		resp.sendRedirect(req.getContextPath() + "/bookingroom"); // resp.sendRedirect("/JavaWeb/room");
	}
	
	
}
