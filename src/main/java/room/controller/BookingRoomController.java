package room.controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/bookingroom")
public class BookingRoomController extends HttpServlet {
	// 建立 RoomService 物件
	private BookingRoomService bookingRoomService = new BookingRoomService();
	private RoomService roomService = new RoomService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 是否有 deleteId 參數帶入 ?
		String deleteId = req.getParameter("deleteId");
		// 如果有 deleteId 參數帶入
		if (deleteId != null) {
			// 刪除房間
			bookingRoomService.deleteBookingRoom(Integer.valueOf(deleteId));
			// 重新導向至 /room
			resp.sendRedirect(req.getContextPath() + "/bookingroom");
			return;
		}
		
		// dto 列表(空)
				List<BookingRoomDto> bookingRoomDtos = new ArrayList<>();
				
				// po 轉 dto
				List<BookingRoom> bookingRooms = bookingRoomService.findAllBookingRooms();
				for(BookingRoom bookingRoom : bookingRooms) {
					BookingRoomDto dto = new BookingRoomDto();
					dto.setBookingId(bookingRoom.getBookingId());
					dto.setCheckinDate(bookingRoom.getCheckinDate());
					dto.setCreateTime(bookingRoom.getCreateTime());
					dto.setUserId(bookingRoom.getUserId());
					Room room = roomService.getRoom(bookingRoom.getRoomId());
					dto.setRoom(room);
					
					// 加入到集合
					bookingRoomDtos.add(dto);
				}
				
		// 取得所有 rooms 資料
		List<Room> rooms = roomService.getRooms();
		List<BookingRoomCount> bookingRoomCounts = bookingRoomService.getBookingRoomCounts();		
		
		// 將所有房間資料放入 request 物件中
		req.setAttribute("bookingRoomDtos", bookingRoomDtos);
		req.setAttribute("bookingRoomCounts", bookingRoomCounts);
		req.setAttribute("rooms", rooms);
		// 轉交至 /WEB-INF/jsp/room.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/booking_room.jsp");
		// 轉交
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得 roomId 及 roomName 參數
		
		Integer roomId = Integer.valueOf(req.getParameter("room_id"));
		// 新增房間
		Integer userId = 1;//登入者的id
		String checkinDate = req.getParameter("checkin_date");
		// 新增房間
		bookingRoomService.addBookingRoom(roomId,userId, checkinDate);
		// 重新導向至首頁
		resp.sendRedirect(req.getContextPath() + "/bookingroom"); // resp.sendRedirect("/JavaWeb/room");
	}
	
	
}
