package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Category;
import com.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public void storeCategory(Category category) {
		categoryRepository.save(category);
		
	}
	
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}
	public void removeCategoryById(int cid) {
		categoryRepository.deleteById(cid);
	}

	
	public Category findCategoryById(int id) {
		
		return categoryRepository.findByCategoryId(id);
		
	}


}
