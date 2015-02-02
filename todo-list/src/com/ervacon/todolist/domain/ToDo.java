package com.ervacon.todolist.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.Assert;

public class ToDo implements Comparable<ToDo>, Serializable {
	
	private Long id;
	
	private String title;
	private Date deadline;
	private String comments;
	
	public ToDo() {
	}
	
	public ToDo(String title, Date deadline, String comments) {
		setTitle(title);
		setDeadline(deadline);
		setComments(comments);
	}
	
	public Long getId() {
		return id;
	}
	
	protected void setId(Long id) {
		Assert.notNull(id);
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		Assert.hasText(title);
		this.title = title;
	}
	
	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		Assert.notNull(deadline);
		this.deadline = deadline;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	// business logic
	
	public boolean isActive() {
		return getDeadline().after(new Date());
	}
	
	// misc
	
	public int compareTo(ToDo other) {
		return this.getDeadline().compareTo(other.getDeadline());
	}
	
	@Override
	public String toString() {
		return "Do " + getTitle() + " before " + getDeadline();
	}
}
