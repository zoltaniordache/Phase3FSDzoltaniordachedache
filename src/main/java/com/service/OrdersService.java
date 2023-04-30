package com.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Login;
import com.bean.Orders;
import com.bean.Product;
import com.repository.OrdersRepository;
import com.repository.ProductRepository;

@Service
public class OrdersService {

	@Autowired
	OrdersRepository ordersRepository;
	
	
	public String placeOrder(Orders order) {
		
		
		ordersRepository.save(order);
		return "Order Placed successfully";
	}
	
	public List<Orders> viewAllOrderDetails() {
		return ordersRepository.findAll();
	}
//	public List<Orders> viewAllOrderByPid(Product product) {
//		return ordersRepository.findAllOrdersByPid(product);
//	}
//	
//	public String deleteOrdersByProductId(int pid) {
//		List<Orders> listOfOrders = ordersRepository.findAllOrdersByPid(pid);
//		for (Orders order : listOfOrders) {
//			ordersRepository.delete(order);
//        }
//		 
//	       
//		
//		return "Order Placed successfully";
//	}
	
	public List<Orders> findAllOrders() {
		return ordersRepository.findAll();
	}
	
	public List<Orders> findAllOrdersByEmail(String email) {
		return ordersRepository.findAllOrdersByEmail( email);
	}
	public List<LocalDate> findAllDates() {
		return ordersRepository.findAllDates();
	}

	public List<Orders> findOrdersByCategory(Integer cid) {
		return ordersRepository.findOrdersByCategory(cid);
	}

	public List<Orders> findOrdersByDate(LocalDate date) {
		return ordersRepository.findOrdersByCategory(date);
	}

	
}
