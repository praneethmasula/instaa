package com.example.demo.entity;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String caption;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date postedTime;
	private String location;
	private Integer likes;
	private String comment;
	
	private Integer uid;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="Posts_images",joinColumns = {
			@JoinColumn(name="post_id")
	},inverseJoinColumns = {
			@JoinColumn(name="img_id")
	})
	@Column(name="files")
	private Set<Posts_Model> files;
	
	
	
	public Posts(Integer id, String caption, Date postedTime, String location, Integer likes, String comment,
			Integer uid, User user, Set<Posts_Model> files) {
		super();
		this.id = id;
		this.caption = caption;
		this.postedTime = postedTime;
		this.location = location;
		this.likes = likes;
		this.comment = comment;
		this.uid = uid;
		this.user = user;
		this.files = files;
	}
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public java.util.Date getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(java.util.Date postedTime) {
		this.postedTime = postedTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Set<Posts_Model> getFiles() {
		return files;
	}
	public void setFiles(Set<Posts_Model> files) {
		this.files = files;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Posts [id=" + id + ", caption=" + caption + ", postedTime=" + postedTime + ", location=" + location
				+ ", likes=" + likes + ", comment=" + comment + ", user=" + user + ", files=" + files + "]";
	}
	
	
	

}
