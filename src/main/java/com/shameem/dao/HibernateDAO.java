package com.shameem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.shameem.model.Product;
import com.shameem.model.User;
import com.shameem.utils.HibernateUtil;
import com.shameem.utils.SessionUtils;
import com.shameem.utils.UserUtils;

public class HibernateDAO {
	
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	public boolean login(String userName, String password){
		List<User> userList = session.createCriteria(User.class)
				.add(Restrictions.eq("userName", userName))
				.add(Restrictions.eq("password", UserUtils.getSHA256Hash(password))).list();
		
		if(userList.size() > 0)
		{
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("userName", userName);
			return true;
		}
		return false;
	}
	
	public boolean saveUser(User user) 
	{
		try
		{
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean saveProduct(Product product)
	{
		try
		{
			session.beginTransaction();
			product.setId(getProductId());
			session.save(product);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void updateProduct(Product product){
		session.beginTransaction();
		session.update(product);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteProduct(Product product)
	{
		session.beginTransaction();
		session.delete(product);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}
	
	public List<Product> getProductList()
	{
		List<Product> oldProductList = session.createCriteria(Product.class).list();
		List<Product> productList = new ArrayList<Product>();
		for(Product product : oldProductList)
		{
			product.setPrice(UserUtils.decrypt(product.getPrice()));
			productList.add(product);
		}
		
		return productList;
	}
	
	public Integer getUserId (){
		String hql = "select max(user.id) from User user";
		Query query = session.createQuery(hql);
		List<Integer> results = query.list();
		Integer userId = 1;
        if (results.get(0) != null ) {
        	userId = results.get(0) + 1;
        }
        return userId;
	}
	
	public Integer getProductId (){
		String hql = "select max(product.id) from Product product";
		Query query = session.createQuery(hql);
		List<Integer> results = query.list();
		Integer productId = 1;
        if (results.get(0) != null ) {
        	productId = results.get(0) + 1;
        }
        return productId;
	}
	
}
