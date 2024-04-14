package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Friends;

@Repository
public interface friendsrepo extends JpaRepository<Friends,Integer>{
	
	@Query(value =  "select user2_id from friends f where f.user_id=:id and f.status='accepted'",nativeQuery = true)
	public List<Integer> getFriends(@Param("id") Integer id);

	@Query(value = "select * from friends f where f.user_id=:id and f.user2_id=:fid",nativeQuery = true)
	public Friends checkIsFrnds(@Param("id") Integer id, @Param("fid") int fid);
	
	@Query(value = "select * from friends f where f.user2_id=:id ",nativeQuery = true)
	public Set<Friends> getRequests(@Param("id") Integer id);

	@Query(value = "select* from friends f where f.user_id=:id and f.user2_id=:fid",nativeQuery = true)
	public Friends findbyFriedns(@Param("id") int id,@Param("fid") int fid);

	@Query(value = "select user_id from friends f where user2_id=:id and f.status='accepted'" , nativeQuery = true)
	public Set<Integer> getFollowers(@Param("id")int  id);
	
	@Query(value = "select user2_id from friends f where user_id=:id and f.status='accepted'" , nativeQuery = true)
	public Set<Integer> getFollowing(@Param("id")int  id);

}
