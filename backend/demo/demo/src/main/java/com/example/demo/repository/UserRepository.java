package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
      User findByUserNameAndPassword(String uname,String password);
     
      @Query(value = "select* from user u where u.user_name =:uname ",nativeQuery = true)
      User findbyUserName(@Param("uname")  String unme);
      
      
      @Query(value = "select count(user_id) from users_movies where post_id=:id",nativeQuery = true)
      int likesofPost(@Param("id") int id);

      @Query(value = "select post_id from users_movies where user_id=:id",nativeQuery = true)
	  Set<Integer> getpostsLikedByUSer(@Param("id") int id);
      @Modifying
      @Transactional
  	@Query(value = "delete from  user_images where user_id=:id",nativeQuery = true)
  	public void del(@Param("id") Integer id );


}
