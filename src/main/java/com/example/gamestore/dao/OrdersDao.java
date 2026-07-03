package com.example.gamestore.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamestore.entity.Orders;

public interface OrdersDao extends JpaRepository<Orders, String>{
//	List<Orders> findAll(); 
//	Orders findById(String id); 
//	void create(Orders o); 
//	void update(Orders o); 
//	void delete(String id);
	List<Orders> findByUsername(String username);
	@Query("select o from Orders o where o.username = :username and o.status = :status")
	List<Orders> findByUsernameAndStatus(@Param("username")String username,@Param("status")String status);
	@Query(value = """
		    SELECT
		        order_date AS revenueDate,
		        SUM(total_amount) AS revenue
		    FROM Orders
		    WHERE status = 'Paid'
		      AND order_date BETWEEN :startDate AND :endDate
		    GROUP BY order_date
		    ORDER BY order_date
		    """, nativeQuery = true)
		List<Object[]> revenueByDate(
		        @Param("startDate") LocalDate startDate,
		        @Param("endDate") LocalDate endDate);
		@Query("select o from Orders o where order_date between :startDate and :endDate")
		List<Orders> findByDate(@Param("startDate") LocalDate startDate,
		        @Param("endDate") LocalDate endDate);
}
