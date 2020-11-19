package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import com.ibm.dao.UserDao;
import com.ibm.tables.User;

@Service
public class UserDaoSevice implements UserDao {

	private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

//  测试代码
	public static void main(String[] args) throws SQLException, IOException {
//		ueryAll();
//		System.out.println("---------------------------");
//		UserDaoSevice u = new UserDaoSevice();
//		User user1 = new User();
//		user1.setUserId(6);
//		user1.setUserName("ղķ˹");
//		user1.setPassword("8888888");
//		user1.setEmail("10000@qq.com");
//		user1.setCreateDate(new Date());
//		user1.setIdentity("����Ա");
//		user1.setStatus("ע��");
//		user1.toString();
//		u.insert(user1);
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
		
		//测试用户修改个人信息
//		UserDaoSevice u = new UserDaoSevice();
//		int i = u.update(6, "张三", "123456@163.com", "123456", "123456");
//		int i = u.update(6, "李四", "123456@qq.com", "12345", "12345");

//		UserDaoSevice u = new UserDaoSevice();
////		int i = u.UpdateAuthority(4);
//		
//		int i = u.cancellationUser(4);
	}

	public void insert(User user) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}

	public List<User> queryAll() throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		List<User> users = criteria.list();
//		for (User user : users) {
//			System.out.println("id=" + user.getUserId() + ", name=" + user.getUserName() + ", password="
//					+ user.getPassword() + ", email=" + user.getEmail() + ", identity=" + user.getIdentity()
//					+ ", createdate=" + DateFormat.getDateInstance().format(user.getCreateDate()) + ", status="
//					+ user.getStatus());
//		}
		transaction.commit();
//		session.close();
		return users;
	}

	public void delete(User user) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}

	public int update(int userid, String userName, String email, String pwd1, String pwd2)
			throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userid);
		if (user.getPassword().equals(pwd1) || !(pwd1.equals(pwd2))) {
			return 0;
		} else {
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(pwd1);
			session.update(user);
		}

		tx.commit();
//		session.close();
		return 1;
	}

	public int cancellationUser(int userid) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userid);

		user.setStatus("注销");
		session.update(user);

		tx.commit();
//		session.close();
		return 1;
	}

	public int UpdateAuthority(int userid) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userid);

		user.setIdentity("经理");
		session.update(user);

		tx.commit();
//		session.close();
		return 1;
	}

	public void login(int userId, String password) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		tx.commit();
		session.close();
	}
}
