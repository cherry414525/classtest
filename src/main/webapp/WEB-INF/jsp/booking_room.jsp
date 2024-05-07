<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String currentDate=sdf.format(new Date());
%>
  
  
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button.css">
		<meta charset="UTF-8">
		<title>Booking Room</title>
	</head>
	<body style="padding: 15px">
		<!-- 新增Booking Room -->
		<form class="pure-form" action="/JavaWeb/bookingroom" method="post">
			<fieldset>
				<legend>BookingRoom Add</legend>
				Room Name: <select id="room_id" name="room_id">
							<c:forEach items="${ rooms }" var="room">
								<option value="${ room.roomId }">${ room.roomName }</option>
							</c:forEach>
							
						   </select><p>
				Check-in Date: <input type="date" id="checkin_date" name="checkin_date" value="<%= currentDate %>"/><p>
				<button type="submit" class="pure-button pure-button-primary">Booking</button>
			</fieldset>
		</form>
		<!-- BookingRoom列表 -->
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>Booking Id</th>
					<th>Room Id</th>
					<th>Room Name</th>
					<th>User Id</th>
					<th>Booking Date</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookingRoomDtos}" var="bookingRoomDto">
					<tr>
						<td>${ bookingRoomDto.bookingId }</td>
						<td>${ bookingRoomDto.room.roomId }</td>
						<td>${ bookingRoomDto.room.roomName }</td>
						<td>${ bookingRoomDto.userId }</td>
						<td>${ bookingRoomDto.checkinDate }</td>
						<td>
							<a href="/JavaWeb/bookingroom/update?updateId=${bookingRoomDto.bookingId}" class=" pure-button button-success">編輯</a>
						</td>
						<td>
							<a href="/JavaWeb/bookingroom?deleteId=${bookingRoomDto.bookingId}" class="pure-button button-error ">刪除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<P>
		<!-- BookingRoomCounts列表 -->
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>Room Id</th>
					<th>Room Name</th>
					<th>Count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookingRoomCounts}" var="bookingRoomCount">
					<tr>
						<td>${ bookingRoomCount.roomId }</td>
						<td>${ bookingRoomCount.roomName }</td>
						<td>${ bookingRoomCount.bookingCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
</body>
</html>