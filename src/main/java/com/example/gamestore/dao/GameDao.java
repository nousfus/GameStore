package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gamestore.entity.Game;

@Repository
public interface GameDao extends JpaRepository<Game, String>,
                                 JpaSpecificationExecutor<Game> {
//	List<Game> findAll(); 
//	Game findById(String id); 
//	void create(Game g); 
//	void update(Game g); 
//	void delete(String id); 
//	List<Game> searchByName(String keyword);
	List<Game> findTop3ByRating(int rating);
	@Query("SELECT g FROM Game g")
	Page<Game> findAllGame(Pageable pageable);
	List<Game> findByDeveloper_Developerid(String id);
	@Query("SELECT g FROM Game g WHERE LOWER(g.GameName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Game> findByGameNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
}
