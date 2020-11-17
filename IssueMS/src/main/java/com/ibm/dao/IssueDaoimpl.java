package com.ibm.dao;

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

public class IssueDaoimpl implements IssueDao {

	private static SessionFactory factory;

	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

//���Է���������
//	public static void main(String[] args) throws SQLException, IOException {
//		ueryAll();
//		System.out.println("---------------------------");
//		User user1 = new User();
//		user1.setUserId(6);
//		user1.setUserName("ղķ˹");
//		user1.setPassword("8888888");
//		user1.setEmail("10000@qq.com");
//		user1.setCreateDate(new Date());
//		user1.setIdentity("����Ա");
//		user1.setStatus("ע��");
//		user1.toString();
//		insert(user1);
//		ueryAll();
//		User user2 = new User();
//		user2.setUserId(6);
//		delete(user2);
//		User user3 = new User();
//		user3.setUserId(4);
//		user3.setUserName("ղķ˹");
//		user3.setPassword("8888888");
//		user3.setEmail("10000@qq.com");
//		user3.setCreateDate(new Date());
//		user3.setIdentity("����Ա");
//		user3.setStatus("ע��");
//		user3.toString();
//		update(user3);
//		ueryAll();
//
//		factory.close();
//	}

	private static void insert(User user) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}

	private static void ueryAll() throws SQLException, IOException {
		List<User> users = factory.openSession().createQuery("FROM User").list();

		for (User user : users) {
			System.out.println("id=" + user.getUserId() + ", name=" + user.getUserName() + ", password="
					+ user.getPassword() + ", email=" + user.getEmail() + ", identity=" + user.getIdentity()
					+ ", createdate=" + DateFormat.getDateInstance().format(user.getCreateDate()) + ", status="
					+ user.getStatus());
		}

	}

	private static void delete(User user) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}

	private static void update(User user) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}
}
