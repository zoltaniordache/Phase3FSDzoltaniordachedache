package com.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer cid;
private String categoryname;
@OneToMany
@JoinColumn(name="categoryid")			//FK
private List<Product> listOfProducts;
@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//@JoinColumn(name="productid")		//FK
private List<Orders> listOfOrders;
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
	
}
public String getCategoryname() {
	return categoryname;
}
public void setCategoryname(String categoryname) {
	this.categoryname = categoryname;
}
public List<Product> getListOfProducts() {
	return listOfProducts;
}
public void setListOfProducts(List<Product> listOfProducts) {
	this.listOfProducts = listOfProducts;
}

}
