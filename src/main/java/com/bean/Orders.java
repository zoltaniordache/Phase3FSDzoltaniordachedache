package com.bean;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int orderid;
@DateTimeFormat(pattern = "YYYY-mm-dd") // mysql default date format. 
private LocalDate orderplaced;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "pid", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
private Product product;		//FK 
private String emailid;		
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "cid", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE) //FK
private Category category ;				//FK
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public LocalDate getOrderplaced() {
	return orderplaced;
}
public void setOrderplaced(LocalDate orderplaced) {
	this.orderplaced = orderplaced;
}
@JsonIgnore
public Product getProduct() {
    return product;
}

@JsonIgnore
public void setProduct(Product product) {
    this.product = product;
}
public int getProductId(){
    return product.getPid();
}

//getter Method to get the author's full name
public String getProductPName(){
    return product.getPname();
}
public String getProductImage(){
    return product.getProductimage();
}
//public Integer getProductid() {
//	return productid;
//}
//public void setProductid(Integer productid) {
//	this.productid = productid;
//}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}


}
