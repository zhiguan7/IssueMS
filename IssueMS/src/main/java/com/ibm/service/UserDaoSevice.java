package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
	
	public List<User> UsearchWithPage(int pageIndex,int pageSize) throws SQLException, IOException{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		
		criteria.setFirstResult((pageIndex-1)*pageSize); //需要修改
		criteria.setMaxResults(pageSize);
		
		List<User> list = criteria.list();
		
		tx.commit();
//		session.close();
		return list;
	}

	public String login(int userId, String password) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = session.get(User.class, userId);
		if (user == null) {
			System.out.println("该用户不存在");
			return "0";
		} else if (!user.getPassword().equals(password)) {
			System.out.println("密码错误");
			return "1";
		} else if (user.getStatus().equals("注销")) {
			System.out.println("登录失败，该用户已注销");
			return "2";
		} else {
//			System.out.println(
//					"{name:" + user.getUserName() + "userid:" + user.getUserId() + "iden:" + user.getIdentity() + "}");
			return "{name:" + user.getUserName() + " userid:" + user.getUserId() + " iden:" + user.getIdentity() + "}";
		}
//		tx.commit();
//		return null;

	}
	

	@Override
	public List<User> searchWithFuzzy(int id, String name) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		
		if(id!=0) {
			criteria.add(Restrictions.and(Restrictions.eq("userId", id)));
		}
		if(name!=null) {
			criteria.add(Restrictions.and(Restrictions.like("userName", name,MatchMode.ANYWHERE)));
		}
		List<User> list = criteria.list();
		
		tx.commit();
		session.close();
		return list;

	}

	public List findByName(String userName) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("userName", userName));
		List result = cr.list();
		System.out.println(result);
		tx.commit();
//		session.close();
		return result;

	}
}
