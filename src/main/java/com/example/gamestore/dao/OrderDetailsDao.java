package com.example.gamestore.dao;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamestore.entity.OrderDetails;

public interface OrderDetailsDao extends JpaRepository<OrderDetails, String>{
//	List<OrderDetails> findAll(); 
//	OrderDetails findById(String id); 
//	void create(OrderDetails od); 
//	void update(OrderDetails od); 
//	void delete(String id);
	@Query("select o from OrderDetails o where o.order.order_id = :id")
	List<OrderDetails> findByOrderID(@Param("id") String id);
	@Query("select o from OrderDetails o where o.game_id = :id")
	List<OrderDetails> findByGameId(@Param("id") String id);
	@Query(value = """
	        SELECT
	            CAST(o.order_date AS DATE) AS revenueDate,
	            SUM((od.price - od.discount_amount) * od.quantity) AS revenue
	        FROM Orders o
	        JOIN OrderDetails od ON o.order_id = od.order_id
	        JOIN Game g ON g.game_id = od.game_id
	        WHERE g.developer_id = :developerId
	            AND o.status = 'Paid'
	            AND o.order_date BETWEEN :startDate AND :endDate
	        GROUP BY CAST(o.order_date AS DATE)
	        ORDER BY revenueDate
	        """, nativeQuery = true)
	    List<Object[]> revenueByDate(
	            @Param("developerId") String developerId,
	            @Param("startDate") LocalDate startDate,
	            @Param("endDate") LocalDate endDate);
}