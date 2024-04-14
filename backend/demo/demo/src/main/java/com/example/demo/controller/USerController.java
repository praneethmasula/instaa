package com.example.demo.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Friends;
import com.example.demo.entity.Posts_Model;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.friendsrepo;

import jakarta.annotation.security.PermitAll;

import com.example.demo.entity.ImageModel;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class USerController {
	
	@Autowired
	private UserRepository repository;
	

	
	@Autowired
	private friendsrepo frdsrepo;
	
	
	@PostMapping(value = "dddd/{id}", consumes = {org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE})
    public HttpStatus  savePost(@PathVariable("id") Integer id,@RequestPart("e") com.example.demo.entity.User  user,@RequestPart("file") MultipartFile[] files) throws IOException{
	
		Set<ImageModel> s=upload(files);
		User user2=repository.findById(user.getId()).orElseThrow();
//		user2.setFiles();
		user2.setFiles(s);
		user2.setBio(user.getBio());
//		user2.setPostss(user2.getPostss());
//		user.setUser(repository.findById(id).get());

		repository.save(user2);
		return HttpStatus.OK;
    	
    }
	
	
	
	@GetMapping("/updateBio/{bio}/{id}")
	public void  updateBio(@PathVariable("bio") String bio,@PathVariable("id") Integer id) {
		User user=repository.findById(id).get();
		user.setBio(bio);
		repository.save(user);
		
	}
	
	
	
	public Set<ImageModel> upload(MultipartFile[] files) throws IOException{
		Set<ImageModel> s=new HashSet<ImageModel>();
		for(MultipartFile file:files) {
			ImageModel model=new ImageModel( file.getOriginalFilename(), file.getContentType(), file.getBytes());
			s.add(model);
		}
		return s;
	}
	
	@PostMapping
	public ResponseEntity  saveUser(@RequestBody User user) {
		this.repository.save(user);
		return  new ResponseEntity<>("saved",HttpStatusCode.valueOf(200));
		
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/isUSerPresent/{usname}")
	public ResponseEntity isUSerPResent(@PathVariable("usname") String uname) {
		User user=repository.findbyUserName(uname);
		User user1=repository.findbyUserName(uname.toUpperCase());
		if(user!=null && user1.getUserName()!=null &&(user.getUserName().equalsIgnoreCase(uname))) {
			return new ResponseEntity(true, HttpStatus.OK);
			}
		return  new ResponseEntity(false, HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{uname}/{password}")
	public User getUSerbyPassword(@PathVariable("uname") String uname,@PathVariable("password") String password) {
		return repository.findByUserNameAndPassword(uname, password);	
	}
	
	@GetMapping("/getAll")
	public List<User> getallll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUSerByID(@PathVariable("id") Integer id) {
		User user=repository.findById(id).get();
//		user.setFollowers(0)
		return user;
		
	}
	@GetMapping("followers/{id}")
	public Set<User> getFollowers(@PathVariable("id") Integer id) {
		Set<Integer> c=frdsrepo.getFollowers(id);
		Set<User> users=new HashSet<>();
		for(int i:c) {
			User user=repository.findById(i).get();
			users.add(user);
			
		}
		
	return users;
		
	}
	
	@GetMapping("following/{id}")
	public Set<User> getFollowing(@PathVariable("id") Integer id) {
		Set<Integer> c=frdsrepo.getFollowing(id);
		Set<User> users=new HashSet<>();
		for(int i:c) {
			User user=repository.findById(i).get();
			users.add(user);
			
		}
		
	return users;
		
	}
	
	@PostMapping("/req/{uid}/d/{fid}")
	public Friends sendRequest(@PathVariable("uid") int id,@PathVariable("fid") int fid) {
		Friends frd=frdsrepo.findbyFriedns(id,fid);
		if(frd!=null) {
			System.out.println("already frnd;");
			frd.setStatus("sent");
			frd.setType("req");
			return frdsrepo.save(frd);
		}
		else {
		Friends friends=new Friends();
		friends.setStatus("sent");
		friends.setType("request");
		User user=repository.findById(id).get();
		User user1=repository.findById(fid).get();
		friends.setUser(user);
		friends.setUser2(user1);
		Friends friends1=new Friends();
		friends1.setStatus("");
		friends1.setType("request");
		User user3=repository.findById(id).get();
		User user12=repository.findById(fid).get();
		friends1.setUser(user12);
		friends1.setUser2(user3);
		frdsrepo.save(friends1);
		return frdsrepo.save(friends);
		}
		
	}
	
	@PostMapping("/removereq/{uid}/d/{fid}")
	public Friends removeREqRequest(@PathVariable("uid") int id,@PathVariable("fid") int fid) {
		Friends friends=frdsrepo.findById(fid).get();
		friends.setStatus("removed");
		friends.setType("request");
		User user=repository.findById(id).get();
		User user1=friends.getUser2();
		friends.setUser(user);
		friends.setUser2(user1);
//		Friends friends1=new Friends();
//		friends1.setStatus("recived");
//		friends1.setType("request");
//		User user3=repository.findById(id).get();
//		User user12=repository.findById(fid).get();
//		friends1.setUser(user12);
//		friends1.setUser2(user3);
//		frdsrepo.save(friends1);
		return frdsrepo.save(friends);
		
	}
	
	
	
	
	@PostMapping("/acept/{uid}/d/{fid}")
	public Friends AcceptRequest(@PathVariable("uid") int id,@PathVariable("fid") int fid) {
		Friends friends=frdsrepo.findById(fid).get();
		friends.setStatus("accepted");
		friends.setType("request");
		User user=repository.findById(id).get();
		User user1=friends.getUser();
		friends.setUser(user1);
		friends.setUser2(user);
//		Friends friends1=new Friends();
//		friends1.setStatus("recived");
//		friends1.setType("request");
//		User user3=repository.findById(id).get();
//		User user12=repository.findById(fid).get();
//		friends1.setUser(user12);
//		friends1.setUser2(user3);
//		frdsrepo.save(friends1);
		return frdsrepo.save(friends);
		
	}
	
	@GetMapping("/all")
	public List<User> getall(){
		return repository.findAll();
	}
	
	
	@GetMapping("/isFriends/{uid}/ff/{fid}")
	public Friends isFreinds(@PathVariable("uid") int id,@PathVariable("fid") int fid) {
		System.out.println("id"+id);
		System.out.println("fid:"+fid);
		Friends f=new Friends();
		 f=frdsrepo.checkIsFrnds(id,fid);
		if(f==null) {
			return new Friends("");
		}
		return f;
		
	}

}
