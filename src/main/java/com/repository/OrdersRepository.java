package com.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bean.Orders;
import com.bean.Product;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
//	@Query("select o from Orders o where o.productid = :productid ")
//	public  List<Orders> findAllOrdersByPid(@Param("productid") Integer productid);
	
		@Query("select distinct orderplaced from Orders ")
	public  List<LocalDate> findAllDates();

		@Query("select o from Orders o where o.emailid = :email ")
		public  List<Orders> findAllOrdersByEmail(@Param("email") String email);
		@Query("select o from Orders o where o.category.cid = :cid ")
		public List<Orders> findOrdersByCategory(@Param("cid")Integer cid);
		@Query("select o from Orders o where o.orderplaced = :orderplaced ")
		public List<Orders> findOrdersByCategory(@Param("orderplaced")LocalDate date);
		
		
		
}
