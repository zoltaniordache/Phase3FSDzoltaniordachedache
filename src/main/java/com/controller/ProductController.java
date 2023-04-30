package com.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@GetMapping("/products")
	public String showProducts(Model model) {
		
		model.addAttribute("products" , productService.findAllProducts());
		
		return "products";
	}
	
	@RequestMapping(value = "/product/delete/{pid}")
	public String placeOrder(Model mm,@PathVariable("pid") int pid) {
		
		String result=productService.deleteProductByPid(pid);
		
		return "redirect:/products";
	}
	@GetMapping("/products/add")
	public String addProductPage(Model model,Product pp) {
		model.addAttribute("product", pp);

		model.addAttribute("categories" , categoryService.findAllCategory());
		return "addProducts";
	}
	
	@PostMapping("/products/add")
	public String addProduct(Model mm,@ModelAttribute("product")  Product pp) {
		String result = productService.storeProduct(pp);
		
		return "redirect:/products";
	}
	
	
	@RequestMapping(value = "/product/update/{pid}",method = RequestMethod.GET)
	public String updateProduct(@PathVariable("pid") int pid, Model mm) { // DI for Product 
		Product product = productService.findProductById(pid);
		mm.addAttribute("product", product);		// store product object with name product 
		mm.addAttribute("categories" , categoryService.findAllCategory());
		return "addProducts";
	}
	
	
	
	
//	@RequestMapping(value = "/openUpdateForm/{pid}",method = RequestMethod.GET)
//	public String openUpdateForm(@PathVariable("pid") int pid, Model mm) { // DI for Product 
//		Product product = productService.findProductById(pid);
//		mm.addAttribute("product", product);		// store product object with name product 
//		return "updateProduct";
//	}
//	
//	@RequestMapping(value = "updateProduct",method = RequestMethod.POST)
//	public String updateProduct(Model mm,Product product) {
//
//		
//		String result = productService.updateProduct(product);
//		List<Product> listOfProduct = productService.findAllProducts();
//		
//		mm.addAttribute("products", listOfProduct);
//		mm.addAttribute("product", product);
//		return "viewProductsAdmin";
//	}
	
	
//	@RequestMapping(value = "/viewProductDetailsByCustomer",method = RequestMethod.GET)
//	public String viewProduct(Model mm, Product pp) {
//		List<Product> listOfProducts = productService.findAllProducts();
//		mm.addAttribute("products", listOfProducts);
//		
//		return "viewProductsByCustomer";
//	}
	
//	@RequestMapping(value = "/viewProductDetailsAdmin",method = RequestMethod.GET)
//	public String viewProductsAdmni(Model mm, Product pp) {
//		List<Product> listOfProducts = productService.findAllProducts();
//		mm.addAttribute("products", listOfProducts);
//		
//		return "viewProductsAdmin.html";
//	}
	@GetMapping("/shop")
	public String customerHomePage(Model model,Product pp) {
		model.addAttribute("categories" , categoryService.findAllCategory());
		model.addAttribute("products" , productService.findAllProducts());
		return "customerHome";
	}
	
	
	@RequestMapping(value = "shop/category/{cid}")
	public String searchProductsByCategory(Model mm,@PathVariable("cid") Integer cid) {
		
		List<Category> listOfCategories = categoryService.findAllCategory();
		mm.addAttribute("category", listOfCategories);
		List<Product> listOfProducts = productService.findProductsByCategoryId(cid);
		
		mm.addAttribute("products", listOfProducts);
		return "customerHome";
	}	
	
	

	
	
	
	
}
