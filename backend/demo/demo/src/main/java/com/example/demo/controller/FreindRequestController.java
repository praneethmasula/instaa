package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Friends;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.friendsrepo;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin("*")
@RestController
@RequestMapping("/friends")
public class FreindRequestController {
	
	@Autowired
	private friendsrepo friendsrepo;
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping
	public void saveFriendrequest(@RequestBody Friends friends) {
		
		friendsrepo.save(friends);
		
	}
	
	
	@GetMapping("/req/{id}")
	public Set<Friends> getRequests(@PathVariable("id") int id){
		System.out.println("id:"+id);
		Set<Friends> req=friendsrepo.getRequests(id);
		Set<User> users=new HashSet<User>();
//		for(Friends i :req) {
//			users.add(repository.findById(i).get());
//		}
		ObjectMapper mapper = new ObjectMapper();
		
		return req;
		}

}
