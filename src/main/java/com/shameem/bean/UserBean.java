package com.shameem.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import com.shameem.dao.HibernateDAO;
import com.shameem.model.User;
import com.shameem.utils.SessionUtils;
import com.shameem.utils.UserUtils;


@ManagedBean(name = "userBean", eager = true)
@RequestScoped
public class UserBean {

	private String userName;
	private String password;

	public String saveUser(){
		HibernateDAO userDao = new HibernateDAO();
		Integer userId = userDao.getUserId();
		User user = new User(userId, userName, UserUtils.getSHA256Hash(password));
		boolean status = userDao.saveUser(user);
		return status? "success" : "welcome";
	}
	
	public String loginUser(){
		HibernateDAO userDao = new HibernateDAO();
		boolean status = userDao.login(userName, password);
		return status ? "home" : "failure";
	}
	
	public String logoutUser(){
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "welcome";
	}

	public String getUserName() {
		return userName;
	}
	
	public String getLoginUserName() {
		return SessionUtils.getUserName();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}