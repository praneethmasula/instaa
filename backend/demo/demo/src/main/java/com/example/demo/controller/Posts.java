package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Posts_Model;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.friendsrepo;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@CrossOrigin("*")
@RestController
@RequestMapping("/post")
public class Posts {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private friendsrepo friendsrepo;
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping(value = "/{id}", consumes = {org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE})
    public String  savePost(@PathVariable("id") Integer id, @RequestPart("e") com.example.demo.entity.Posts  posts,@RequestPart("file") MultipartFile[] files) throws IOException{
		Set<Posts_Model> s=upload(files);
		posts.setFiles(s);
		posts.setUser(repository.findById(id).get());
		postRepo.save(posts);
		return "saved";
    	
    }
	
	@GetMapping("/arefreinds/{id}/fid{}")
	boolean checkareFreinds(@PathVariable("id") Integer id,@PathVariable("fid") Integer fid) {
		
		return false;
		
	}
	
	public Set<Posts_Model> upload(MultipartFile[] files) throws IOException{
		Set<Posts_Model> s=new HashSet<Posts_Model>();
		for(MultipartFile file:files) {
			Posts_Model model=new Posts_Model( file.getOriginalFilename(), file.getContentType(), file.getBytes());
			s.add(model);
		}
		return s;
	}
	@GetMapping("/{id}")
	public List<com.example.demo.entity.Posts> getPostsByID(@PathVariable("id") Integer id){
		List<Integer> lis=friendsrepo.getFriends(id);
	lis.add(id);
	List<com.example.demo.entity.Posts> pos=postRepo.getPostsbyID(lis);
//	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//	Date d1 = null;
//	Date d2 = null;
//	  LocalDateTime now = LocalDateTime.now();  
	
	for(com.example.demo.entity.Posts p:pos) {
	    
        p.setUser(repository.findById(p.getUser().getId()).get());
		p.setLikes(repository.likesofPost(p.getId()));
	}
	
	
	
	return pos;
		
		
	}
	
	@GetMapping("user/{id}/post/{idd}")
	public String getPostsByIDdd(@PathVariable("id") Integer id,@PathVariable("idd") Integer idd){
	User lis=repository.findById(idd).get();
	System.out.println("id:"+id);
	System.out.println("iddd:"+idd);
	Set<com.example.demo.entity.Posts> postslis=new HashSet<>();
	com.example.demo.entity.Posts posts=postRepo.findById(id).get();
	Set<Integer> posss=repository.getpostsLikedByUSer(lis.getId());
	for(int i:posss) {
		postslis.add(postRepo.findById(i).get());
	}
	postslis.add(posts);
	lis.setPostss(postslis);
	repository.save(lis);
	return "updated";
		
		
	}
	
	@GetMapping("userr/{id}/post/{idd}")
	public String getPostsByIDdddd(@PathVariable("id") Integer id,@PathVariable("idd") Integer idd){
	User lis=repository.findById(idd).get();
	System.out.println("id:"+id);
	System.out.println("iddd:"+idd);
	Set<com.example.demo.entity.Posts> postslis=new HashSet<>();
	com.example.demo.entity.Posts posts=postRepo.findById(id).get();
	Set<Integer> posss=repository.getpostsLikedByUSer(lis.getId());
	for(int i:posss) {
		postslis.add(postRepo.findById(i).get());
	}
	
	postslis.remove(posts);
	
	lis.setPostss(postslis);
	repository.save(lis);
	return "updated";
		
		
	}
	
	@GetMapping("postsliked/{id}")
	public Set<com.example.demo.entity.Posts> getPostsliked(@PathVariable("id") Integer id){
		Set<Integer> posss=repository.getpostsLikedByUSer(id);
		System.out.println("id:"+id);
		System.out.println("len:"+posss.size());
		Set<com.example.demo.entity.Posts> postslis=new HashSet<>();
		for(int i:posss) {
			postslis.add(postRepo.findById(i).get());
		}
		return postslis;
		
	}
	
	@GetMapping("/posts/{id}")
	public List<com.example.demo.entity.Posts> getPostofOwn(@PathVariable("id") Integer id){
		
		List<com.example.demo.entity.Posts> posts=postRepo.getPostsofOwn(id);
		for(com.example.demo.entity.Posts p:posts) {
		p.setLikes(repository.likesofPost(p.getId()));
		}
		
		return posts;
				
		
		
	}

}
