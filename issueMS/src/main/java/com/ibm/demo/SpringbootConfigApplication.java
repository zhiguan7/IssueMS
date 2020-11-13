package com.ibm.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ibm.tables.User;

public class SpringbootConfigApplication {

	private static SessionFactory factory;

	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static void main(String[] args)throws SQLException, IOException{
		ueryAll();
		System.out.println("---------------------------");
		User user1 = new User();
		user1.setUserId(5);
		user1.setUserName("詹姆斯");
		user1.setPassword("8888888");
		user1.setEmail("10000@qq.com");
		user1.setCreateDate(new Date());
		user1.setIdentity("管理员");
		user1.setStatus("注册");
		insert(user1);
		ueryAll();
	}
	
	private static void insert(User user)throws SQLException,IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}
	
	private static void ueryAll() {
		List<User> users = factory.openSession().createQuery("FROM User").list();

		for (User user : users) {
			System.out.println("id=" + user.getUserId() + ", name=" + user.getUserName() + ", password="
					+ user.getPassword() + ", email=" + user.getEmail() + ", identity=" + user.getIdentity()
					+ ", createdate=" + DateFormat.getDateInstance().format(user.getCreateDate()) + ", status="
					+ user.getStatus());
		}
		factory.close();
	}

}
