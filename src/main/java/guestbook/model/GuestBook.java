package guestbook.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBook {
	private Integer id;
	private String username;
	private String content;
	private Date createDate;
	private Date updateDate;
	
	//使用NoArgsConstructor，沿用無參數建構子
	public GuestBook(String name, String content) {
		
		this.username = name;
		this.content = content;
	}
	
	
	
}