package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.ibm.dao.UserDao;
import com.ibm.tables.Issue;
import com.ibm.tables.Statistics;
import com.ibm.tables.Total_Statistics;
import com.ibm.tables.Total_User;
import com.ibm.tables.User;

@Service
public class UserDaoSevice implements UserDao {

	private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

	
	
	public void insert(User user) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}

	public Total_User queryAll() throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		List<User> users = criteria.list();
		Total_User Users = new Total_User();
		int num = users.size();
		Users.setUsers(users);
		Users.setTotal(num);
		transaction.commit();
//		session.close();
		return Users;
	}

	public void delete(User user) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}

	public int update(String userid, String userName, String email, String pwd1, String pwd2)
			throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("userId", userid));
		List<User> result = cr.list();
		User user = result.get(0);
		if (!(pwd1.equals(pwd2))) {
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

	public int cancellationUser(String userid) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
//			User user = (User) session.get(User.class, userid);
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("userId", userid));
			List<User> result = cr.list();
			User user = result.get(0);
			user.setStatus("注销");
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
//		session.close();
		return 1;
	}

	public int UpdateAuthority(String userid) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
//			User user = (User) session.get(User.class, userid);
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("userId", userid));
			List<User> result = cr.list();
			User user = result.get(0);
			user.setIdentity("经理");
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
//		session.close();
		return 1;
	}

//	public List<User> UsearchWithPage(int pageIndex, int pageSize) throws SQLException, IOException {
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
//		Criteria criteria = session.createCriteria(User.class);
//
//		criteria.setFirstResult((pageIndex - 1) * pageSize); 
//		criteria.setMaxResults(pageSize);
//
//		List<User> list = criteria.list();
//
//		tx.commit();
////		session.close();
//		return list;
//	}

	public User login(String userId, String password) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
//		User user = session.get(User.class, userId);
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("userId", userId));
		System.out.println("--------------------"+cr);
		List<User> result = cr.list();
		System.out.println("-----------------"+result);
		User user = new User();
		if(!result.isEmpty()) {
			user = result.get(0);
		}
		if (result.isEmpty()) {
			System.out.println("该用户不存在");
			user.setIdentity("该用户不存在");
			return user;
		} 
		else if (!user.getPassword().equals(password)) {
			
			System.out.println("密码错误");
			user.setIdentity("密码错误");
			return user;
		} else if (user.getStatus().equals("注销")) {
			
			System.out.println("登录失败，该用户已注销");
			user.setIdentity("登录失败，该用户已注销");
			return user;
		} else {
			user = result.get(0);
//			System.out.println(
//					"{name:" + user.getUserName() + "userid:" + user.getUserId() + "iden:" + user.getIdentity() + "}");
			return user;
		}
//		tx.commit();
//		return null;

	}

	public List findByName(String userName) throws SQLException, IOException {
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

	public void saveUser(User user) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
//		String sql = "insert into user(userId , userName, password, email, identity, status) values(?,?,?,?,?,?)";
//		SQLQuery sqlQuery = session.createSQLQuery(sql);
//		sqlQuery.setInteger(1, user.getUserId());
//		sqlQuery.setString(2, user.getUserName());
//		sqlQuery.setString(3, user.getPassword());
//		sqlQuery.setString(4, user.getEmail());
//		sqlQuery.setString(5, user.getIdentity());
//		sqlQuery.setString(6, user.getStatus());
//		sqlQuery.executeUpdate();
		session.save(user);
		tx.commit();
		session.flush();
//		session.close();
	}

	@Override
	public Total_Statistics searchWithFuzzy(String id, String name, int pageIndex, int pageSize)
			throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria1 = null;
		Criteria criteria2 = null;
		Criteria criteria3 = null;
		Criteria criteria4 = session.createCriteria(User.class);
		Total_Statistics tStatistics = new Total_Statistics();
		Statistics s = null;
		List<Statistics> statistics = new ArrayList<Statistics>();
		List<User> list = null;

		if (id != null) {
			criteria4.add(Restrictions.and(Restrictions.like("userId", id, MatchMode.ANYWHERE)));
		}
		if (name != null) {
			criteria4.add(Restrictions.and(Restrictions.like("userName", name, MatchMode.ANYWHERE)));
		}
		
//		int row = list.size();
		criteria4.setFirstResult((pageIndex-1)*pageSize);
		criteria4.setMaxResults(pageSize);
		list = criteria4.list();

		for (User i : list) {
			s = new Statistics();
			s.setUserId(i.getUserId());
			s.setUserName(i.getUserName());
			criteria1 = session.createCriteria(Issue.class);
			criteria2 = session.createCriteria(Issue.class);
			criteria3 = session.createCriteria(Issue.class);

			criteria1.add(Restrictions.eq("userId", i.getUserId()));
			int cNum = ((Long) criteria1.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			s.setcNum(cNum);
//
			criteria2.add(Restrictions.eq("updateMan", i.getUserName()));
			int rNum = ((Long) criteria2.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			s.setrNum(rNum);

			criteria3.add(Restrictions.eq("status", "关闭"));
			criteria3.add(Restrictions.eq("userId", i.getUserId()));
			int aNum = ((Long) criteria3.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			s.setaNum(aNum);

			s.setRate((float) aNum / (float) rNum);

			System.out.println(s);
			statistics.add(s);

			//session.flush();
		}

//		int row = statistics.size();
//		if (row / pageSize + 1 == pageIndex) {
//			statistics = statistics.subList((pageIndex - 1) * pageSize, row);
//		} else {
//			statistics = statistics.subList((pageIndex - 1) * pageSize, pageIndex * pageSize);
//		}

		tStatistics.setStatistics(statistics);

		criteria4.setFirstResult(0);
		int allCounts = ((Long) criteria4.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		tStatistics.setTotal(allCounts);
		tx.commit();
//		session.close();
		return tStatistics;

	}

	public Total_User AdminFuzzyquery(String useid, String username, int pageIndex, int pageSize)
			throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		Total_User user = new Total_User();

		if (useid != null) {
			criteria.add(Restrictions.and(Restrictions.like("userId", useid, MatchMode.ANYWHERE)));
		}
		if (username != null) {
			criteria.add(Restrictions.and(Restrictions.like("userName", username, MatchMode.ANYWHERE)));
		}
		criteria.addOrder(Order.asc("userId"));
		criteria.setFirstResult((pageIndex - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		user.setUsers(criteria.list());

		criteria.setFirstResult(0);
		int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		user.setTotal(allCounts);

		tx.commit();
//		session.close();
		return user;
	}

}
