package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Posts;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Repository
public interface PostRepo extends JpaRepository<Posts, Integer>{
	
	@Query(value =  "select * from posts p where p.user_id in :id",nativeQuery = true)
	public List<Posts> getPostsbyID(@Param("id") List<Integer> id);
	
	@Query(value =  "select * from posts p where p.user_id =:id",nativeQuery = true)
	public List<Posts> getPostsofOwn(@Param("id") Integer id);
	
}
