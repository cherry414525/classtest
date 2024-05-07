<%@page import="javax.swing.JList.DropLocation"%>
<%@page import="room.model.dto.BookingRoomDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%!
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String dateFormat(Date date) {
		return date == null ? "" : sdf.format(date);
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<meta charset="UTF-8">
		<title>Room Update</title>
	</head>
	<body style="padding: 15px">
		<!-- 修改Room -->
		<form class="pure-form" action="/JavaWeb/bookingroom/update" method="post">
			<fieldset>
				<legend>Room Update</legend>
				Booking Id:<input type="text" id="bookingroomId" name="bookingroomId" value="${bookingroomId}" readonly /> <br>
				Room Name: <select id="room_id" name="room_id" >
							<option value="${bookingroom.roomId }"  >${dto.room.roomName }</option>
							<c:forEach items="${ rooms }" var="room" >
								<option value="${ room.roomId }">${ room.roomName }</option>
							</c:forEach>
							
						   </select><p>
				Check-in Date: <input type="date" id="checkin_date" name="checkin_date" value="${checkinDate }" /><p>
				<button type="submit" class="pure-button pure-button-primary"  >修改Room</button>
			</fieldset>
		</form>
		
		
	</body>
</html>