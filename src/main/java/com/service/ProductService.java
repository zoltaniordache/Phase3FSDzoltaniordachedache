package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public String storeProduct(Product product) {
		productRepository.save(product);
		return "Product details stored successfully";
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public void decrementQty(int pid) {
		Optional<Product> result = productRepository.findById(pid);
		if(result.isPresent()) {
			Product p = result.get();
			p.setQty(p.getQty()-1);
			productRepository.saveAndFlush(p);	// like update 
		}
	}
	public List<Product> findProductsByCategoryId(Integer categoryid) {
		return productRepository.findProductsByCategoryId(categoryid);
	}
	
	public String deleteProductByPid(int pid) {
		productRepository.deleteById(pid);
		return "Product details were deleted successfully";
	}
	public String updateProduct(Product product) {
		Optional<Product> result = productRepository.findById(product.getPid());
		if(result.isPresent()) {
			Product p = result.get();
				p.setPrice(product.getPrice());
				p.setPname(product.getPname());
				p.setQty(product.getQty());
				p.setProductimage(product.getProductimage());
				productRepository.saveAndFlush(p);
				return "Product updated successfully";
		}else {
			return "Product not present";
		}
	}
	public Product findProductById(int pid) {
		Optional<Product> result = productRepository.findById(pid);
		if(result.isPresent()) {
			Product p = result.get();
			return p;
		}else {
			return null;
		}
		
	}
}
