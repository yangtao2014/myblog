package cn.yt.beans;

import java.util.Date;


/**
 * 
 * @author TC
 * 评论实体类
 */
public class Critique {
	private int id;         //评论id（几楼）
	private int aid;        //评论对应文章
	private String content; //评论内容
	private String username;//发表评论的用户名
	private Date date;    //发表时间
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
