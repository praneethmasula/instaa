package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Friends {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
		
	private String status;
	
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user2;

	

	public Friends(Integer id, User user, String status, String type, User user2) {
		super();
		this.id = id;
		this.user = user;
		this.status = status;
		this.type = type;
		this.user2 = user2;
	}
	
	public Friends( String status) {
		super();
	
		this.status = status;
		
	}

	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public String toString() {
		return "Friends [id=" + id + ", user=" + user + ", status=" + status + ", type=" + type + ", user2=" + user2
				+ "]";
	}

	
	
	
	

}
