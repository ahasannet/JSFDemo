package com.shameem.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.shameem.dao.HibernateDAO;
import com.shameem.model.Product;
import com.shameem.utils.UserUtils;


@ManagedBean(name = "productBean", eager = true)
@RequestScoped
public class ProductBean {

	private int id;
	private String productName;
	private String price;
	private List<Product> productList;
	private Product product;

	public String saveProduct()
	{
		HibernateDAO productDao = new HibernateDAO();
		Product product = new Product(id, productName, UserUtils.encrypt(price));
		productDao.saveProduct(product);
		return "home";
	}
	
	public String updateProduct()
	{
		product.setEditable(false);
		return null;
	}

	public String updateProductName(int id)
	{
		HibernateDAO productDao = new HibernateDAO();
		for(Product product : productList)
		{
			if(product.getId() == id)
			{
				product.setProductName(productName);
				productDao.saveProduct(product);
				this.product = product;
				break;
			}
		}
		return null;
	}
	
	public String updateProductPrice(int id)
	{
		HibernateDAO productDao = new HibernateDAO();
		for(Product product : productList)
		{
			if(product.getId() == id)
			{
				product.setPrice(UserUtils.encrypt(price));
				productDao.saveProduct(product);
				product.setPrice(price);
				this.product = product;
				break;
			}
		}
		return null;
	}
	
	public String editProduct()
	{
		productName = product.getProductName();
		price = product.getPrice();
		product.setEditable(true);
		return null;
	}

	public String deleteProduct(Product product)
	{
		HibernateDAO productDao = new HibernateDAO();
		productDao.deleteProduct(product);
		productList.remove(product);
		return "home";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Product> getProductList() {
		if(productList == null)
		{
			HibernateDAO productDao = new HibernateDAO();
			productList = productDao.getProductList();
		}
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}