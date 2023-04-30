package com.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Category;
import com.bean.Login;
import com.bean.Orders;
import com.bean.Product;
import com.service.CategoryService;
import com.service.OrdersService;
import com.service.ProductService;

@Controller
public class OrdersController {

	@Autowired
	ProductService productService;
	@Autowired
	OrdersService ordersService;
	@Autowired
	CategoryService categoryService;	
	
	@RequestMapping(value = "placeOrder/{pid}")
	public String placeOrder(Model mm,@PathVariable("pid") int pid,HttpSession hs,Orders order) {
		//System.out.println("Pid is "+pid);
		String emailid = (String)hs.getAttribute("emailid");
		Product product = productService.findProductById(pid);
		order.setEmailid(emailid);
		order.setOrderplaced(LocalDate.now());
		//order.setProductid(pid);
		order.setProduct(product);
		Category category = categoryService.findCategoryById(product.getCategoryid());
		order.setCategory(category);
		String result = ordersService.placeOrder(order);

		
		productService.decrementQty(pid);
		List<Product> listOfProducts = productService.findAllProducts();
		mm.addAttribute("products", listOfProducts);
		mm.addAttribute("categories" , categoryService.findAllCategory());
		return "customerHome";
	}	
	
	@RequestMapping(value = "/orders/email",method = RequestMethod.GET)
	public String seeAllOrdersByEma(Model mm, HttpSession hs,Login loginDetails) {
		String email = (String)hs.getAttribute("emailid");
		List<Orders> listOfOrders = ordersService.findAllOrdersByEmail(email);
		float total = 0;
		int i = 0;
        while (i < listOfOrders.size()) {
            System.out.println(listOfOrders.get(i));
             total = listOfOrders.get(i).getProduct().getPrice();
            i++;
        }
        
		mm.addAttribute("orders", listOfOrders);
		mm.addAttribute("total", total);
		return "orders";
	}

	
	@RequestMapping(value = "/orders_admin",method = RequestMethod.GET)
	public String seeAllOrdersByEma(Model mm) {
		
		List<Orders> listOfOrders = ordersService.findAllOrders();
		List<LocalDate> listOfDates = ordersService.findAllDates();
		mm.addAttribute("dates", listOfDates);

		mm.addAttribute("categories" , categoryService.findAllCategory());
		mm.addAttribute("orders", listOfOrders);

		return "ordersReport";
	}
	@RequestMapping(value = "orders/category/{cid}",method = RequestMethod.GET)
	public String seeAllOrdersByCategory(Model mm,@PathVariable("cid") Integer cid) {
		
		
		List<Orders> listOfOrders = ordersService.findOrdersByCategory(cid);
		mm.addAttribute("orders", listOfOrders);


		return "ordersReport";
	}
	@RequestMapping(value = "orders/{date}",method = RequestMethod.GET)
	public String seeAllOrdersByCategory(Model mm,@PathVariable("date") String date) {
		 LocalDate localDate = LocalDate.parse(date);
	
		
		List<Orders> listOfOrders = ordersService.findOrdersByDate(localDate);
		mm.addAttribute("orders", listOfOrders);


		return "ordersReport";
	}
	
	
}
