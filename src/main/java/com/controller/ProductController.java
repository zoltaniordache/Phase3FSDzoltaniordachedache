package com.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Category;
import com.bean.Orders;
import com.bean.Product;
import com.service.CategoryService;
import com.service.OrdersService;
import com.service.ProductService;

@Controller
public class ProductController {

	
	
	@Autowired
	ProductService productService;
	@Autowired
	OrdersService ordersService;
	@Autowired 
	CategoryService categoryService;
	
//	@RequestMapping(value = "/adminHome",method = RequestMethod.GET)
//	public String back(Model mm, Product pp) {
//		mm.addAttribute("product", pp);
//		return "adminHome";
//	}
	
	@RequestMapping(value = "/addProductPage",method = RequestMethod.GET)
	public String openAddProductPage(Model mm, Product pp) {
			mm.addAttribute("product", pp);
		List<Category> listOfCategory = categoryService.findAllCategory();
			mm.addAttribute("category",listOfCategory);
		return "addProduct";
	}
	
	
	@RequestMapping(value = "/storeProductInfo",method = RequestMethod.POST)
	public String storeProductData(Model mm,Product pp) {
		String result = productService.storeProduct(pp);
		mm.addAttribute("product", pp);
		mm.addAttribute("msg", result);
		List<Category> listOfCategory = categoryService.findAllCategory();
		mm.addAttribute("category",listOfCategory);
		System.out.println(pp);  
		return "addProduct";
	}
	
	
	@RequestMapping(value = "/viewProductDetailsByCustomer",method = RequestMethod.GET)
	public String viewProduct(Model mm, Product pp) {
		List<Product> listOfProducts = productService.findAllProducts();
		mm.addAttribute("products", listOfProducts);
		
		return "viewProductsByCustomer";
	}
	
	@RequestMapping(value = "/viewProductDetailsAdmin",method = RequestMethod.GET)
	public String viewProductsAdmni(Model mm, Product pp) {
		List<Product> listOfProducts = productService.findAllProducts();
		mm.addAttribute("products", listOfProducts);
		
		return "viewProductsAdmin.html";
	}
	
	@RequestMapping(value = "searchProductsByCategory/{cid}")
	public String searchProductsByCategory(Model mm,@PathVariable("cid") int cid) {
		System.out.println("cid is "+cid);
		List<Category> listOfCategories = categoryService.findAllCategory();
		mm.addAttribute("category", listOfCategories);
		List<Product> listOfProducts = productService.findProductsByCategoryId(cid);
		
		mm.addAttribute("products", listOfProducts);
		return "viewProductsByCategory";
	}	
	
	@RequestMapping(value = "deleteProduct/{pid}")
	public String placeOrder(Model mm,@PathVariable("pid") int pid) {
		System.out.println("Pid is "+pid);
		ordersService.deleteOrdersByProductId(pid);
		String result=productService.deleteProductByPid(pid);
		mm.addAttribute("msg", result);
		return "viewProductsAdmin";
	}	
	@RequestMapping(value = "/openUpdateForm/{pid}",method = RequestMethod.GET)
	public String openUpdateForm(@PathVariable("pid") int pid, Model mm) { // DI for Product 
		Product product = productService.findProductById(pid);
		mm.addAttribute("product", product);		// store product object with name product 
		return "updateProduct";
	}
	
	@RequestMapping(value = "updateProduct",method = RequestMethod.POST)
	public String updateProduct(Model mm,Product product) {

		
		String result = productService.updateProduct(product);
		List<Product> listOfProduct = productService.findAllProducts();
		mm.addAttribute("msg", result);
		mm.addAttribute("products", listOfProduct);
		mm.addAttribute("product", product);
		return "viewProductsAdmin";
	}
	
	
	
	
}
