package guestbook.service;

import java.util.List;

import guestbook.dao.GuestBookDao;
import guestbook.dao.GuestBookDaoMySQL;
import guestbook.model.GuestBook;

public class GuestBookServicImpl implements GuestBookService{

	//private Guestbookdao dao=new guestbookinmemory();
	private GuestBookDao dao=new GuestBookDaoMySQL();
	
	@Override
	public boolean add(String username, String content) {
		GuestBook gb=new GuestBook(username,content);
		int rowcount=dao.create(gb);
		return rowcount==1;
	}

	@Override
	public GuestBook getbyid(Integer id) {
		
		return dao.findById(id);
	}

	@Override
	public List<GuestBook> quaryall() {
		
		return dao.findAll();
	}

	@Override
	public boolean updatename(Integer id, String username) {
		GuestBook gb=dao.findById(id);
		if(gb==null) {
			return false;
		}
		gb.setUsername(username);
		int rowcount=dao.update(gb);
		return rowcount==1;
	}

	@Override
	public boolean updatecontent(Integer id, String content) {
		GuestBook gb=dao.findById(id);
		if(gb==null) {
			return false;
		}
		gb.setContent(content);
		int rowcount=dao.update(gb);
		return rowcount==1;
	}

	@Override
	public boolean removebyid(Integer id) {
		int rowcount=dao.deleteById(id);
		return rowcount==1;
	}

}
