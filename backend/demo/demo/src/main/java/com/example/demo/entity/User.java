package com.example.demo.entity;

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
import jakarta.persistence.OneToMany;

import java.util.*;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String userName;
	private String password;
	private int followers;

	private int following;
	private String phooneNumber;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="User_images",joinColumns = {
			@JoinColumn(name="user_id")
	},inverseJoinColumns = {
			@JoinColumn(name="img_id")
	})
	@Column(name="files")
	private Set<ImageModel> files;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Friends> friends;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Posts> posts;
	
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
	    name = "users_movies",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private Set<Posts> postss;
	
	private String bio; 
	
	
	




	public User(int id, String name, String userName, String password, int followers, int following,
			String phooneNumber, Set<ImageModel> files, List<Friends> friends, List<Posts> posts, Set<Posts> postss,
			String bio) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.followers = followers;
		this.following = following;
		this.phooneNumber = phooneNumber;
		this.files = files;
		this.friends = friends;
		this.posts = posts;
		this.postss = postss;
		this.bio = bio;
	}




	public String getBio() {
		return bio;
	}




	public void setBio(String bio) {
		this.bio = bio;
	}




	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public Set<ImageModel> getFiles() {
		return files;
	}


	public void setFiles(Set<ImageModel> files) {
		this.files = files;
	}


	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public int getFollowing() {
		return following;
	}
	public void setFollowing(int following) {
		this.following = following;
	}
	public String getPhooneNumber() {
		return phooneNumber;
	}
	public void setPhooneNumber(String phooneNumber) {
		this.phooneNumber = phooneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
     
	

	public List<Friends> getFriends() {
		return friends;
	}




	public void setFriends(List<Friends> friends) {
		this.friends = friends;
	}




	public List<Posts> getPosts() {
		return posts;
	}




	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}




	public Set<Posts> getPostss() {
		return postss;
	}




	public void setPostss(Set<Posts> postss) {
		this.postss = postss;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", followers=" + followers + ", following=" + following + ", phooneNumber=" + phooneNumber
				+ ", files=" + files + ", friends=" + friends + ", posts=" + posts + ", postss=" + postss + ", bio="
				+ bio + "]";
	}








	
	
	
	
	
	

}
