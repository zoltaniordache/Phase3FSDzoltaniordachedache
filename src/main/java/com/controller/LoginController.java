package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Category;
import com.bean.Login;
import com.service.CategoryService;
import com.service.LoginService;
import com.service.ProductService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String open(Model mm, Login ll) {
		mm.addAttribute("login", ll);
		return "index";
	}
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String openIndexPage(Model mm, Login ll) {
		
		return "index";
	}
	
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String openAdminHome(Model mm, Login ll) {
		mm.addAttribute("login", ll);
		return "adminHome";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String openSignUpPage(Model mm, Login ll) {
		mm.addAttribute("login", ll);
		return "register";
	}
	
	@RequestMapping(value = "/signUp",method = RequestMethod.POST)
	public String signUp(Model mm,@RequestParam("email") String email,@RequestParam("password") String password) {
		Login ll = new Login();
		ll.setEmailid(email);
		ll.setPassword(password);
		ll.setTypeofuser("customer");
		String result = loginService.signUp(ll);
		mm.addAttribute("login", ll);
		System.out.println(result);

		return "index";

	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String signIn(Model mm, @RequestParam("email") String email,@RequestParam("password") String password,HttpSession hs) {
		System.out.println(email);
		System.out.println(password);
		String result = loginService.signIn(email,password);
		
		if(result.equals("Customer login successfully")) {
			hs.setAttribute("emailid",email);		// stored session object of that person 
			mm.addAttribute("categories" , categoryService.findAllCategory());
			mm.addAttribute("products" , productService.findAllProducts());
			return "customerHome";
		}else if(result.equals("Admin login successfully")) {
			mm.addAttribute("products" , productService.findAllProducts());
			return "adminHome";
		}else {
			return "index";
		}
	
	}
	

	
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public String seeAllUsers(Model mm, Login loginDetails) {
		List<Login> listOfUsers = loginService.findAllUsers();
		mm.addAttribute("users", listOfUsers);
		return "viewUsers";
	}
	
	@RequestMapping(value = "/findCustomer",method = RequestMethod.GET)
	public String findCustomer(Model mm, @RequestParam(value = "emailsubject") String emailSubject) {

		Login result = loginService.findCustomerByEmail(emailSubject);
		
		if(result==null) {

			return "viewUsers";
		}else {
			mm.addAttribute("users", result);

			return "viewUsers";
		}
		
	}
		
}
