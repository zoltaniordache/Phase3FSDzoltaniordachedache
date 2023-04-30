package com.controller;

import java.util.List;
import java.util.Optional;

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
import com.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/adminHome",method = RequestMethod.GET)
	public String back(Model mm, Category cc) {
		mm.addAttribute("category", cc);
		return "adminHome";
	}
	
//	@RequestMapping(value = "/addCategoryPage",method = RequestMethod.GET)
//	public String openAddCategoryPage(Model mm, Category cc) {
//		mm.addAttribute("category", cc);
//		return "addCategory";
//	}
	
	
	@GetMapping("/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category" , new Category());
		return "addCategory";
	}
	
	@PostMapping("/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.storeCategory(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/delete/{cid}")
	public String deleteCat(@PathVariable int cid) {
		
		categoryService.removeCategoryById(cid);
		
		return "redirect:/categories";
	}
	
	
	@GetMapping("/categories/update/{cid}")
	public String updateCat(@PathVariable int cid , Model model) {
		
		Category category = categoryService.findCategoryById(cid);
		
		
			model.addAttribute("category" , category);
			return "addCategory";
		
		
	}
	@RequestMapping(value = "/categories",method = RequestMethod.GET)
	public String viewCategory(Model mm, Category cc) {
		List<Category> listOfCategories = categoryService.findAllCategory();
		mm.addAttribute("category", listOfCategories);
		return "categories";
	}
}
