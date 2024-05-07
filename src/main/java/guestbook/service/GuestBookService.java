package guestbook.service;

import java.util.List;

import guestbook.model.GuestBook;

//controller->service->dao
public interface GuestBookService {
	//回傳前端
	boolean add(String username,String content); //新增
	GuestBook getbyid(Integer id);//單筆查詢
	List<GuestBook> quaryall();//多筆查詢
	boolean updatename(Integer id,String username);//修改使用者名稱
	boolean updatecontent(Integer id,String content);//修改留言內容
	boolean removebyid(Integer id);//刪除
	
}
