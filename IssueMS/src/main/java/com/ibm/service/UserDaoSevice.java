package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ibm.dao.UserDao;
import com.ibm.tables.Issue;
import com.ibm.tables.Statistics;
import com.ibm.tables.Total_Statistics;

import com.ibm.tables.Total_Issue;
import com.ibm.tables.Total_User;

import com.ibm.tables.User;
import com.sun.org.glassfish.external.statistics.Statistic;

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

	public int update(int userid, String userName, String email, String pwd1, String pwd2) throws SQLException, IOException {
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
	public Total_Statistics searchWithFuzzy(int id, String name,int pageIndex,int pageSize) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);

		if (id != 0) {
			criteria.add(Restrictions.and(Restrictions.eq("userId", id)));
		}
		if (name != null) {
			criteria.add(Restrictions.and(Restrictions.like("userName", name, MatchMode.ANYWHERE)));
		}
		List<User> list = criteria.list();
		List<Statistics> sList = null;
		Total_Statistics tStatistics = new Total_Statistics();
		Statistics s = new Statistics();
		Query query1 = session.createQuery("from Issue count(*) where create_man=:cm");
		Query query2 = session.createQuery("from Issue count(*) where update_man=:um");
		Query query3 = session.createQuery("from Issue count(*) where update_man=:m and status = '关闭' ");
		for(User u: list) {
			s.setUserId(u.getUserId());
			s.setUserName(u.getUserName());
			
			query1.setParameter("cm", u.getUserName());
			Integer ref1 = (Integer)query1.uniqueResult();
			s.setcNum(ref1);
			
			query2.setParameter("um", u.getUserName());
			Integer ref2 = (Integer)query2.uniqueResult();
			s.setrNum(ref2);
			
			query3.setParameter("m", u.getUserName());
			Integer ref3 = (Integer)query3.uniqueResult();
			s.setaNum(ref3);
			
			float rate = (float)ref3/ (float)ref2;
			s.setRate(rate);
			sList.add(s);
		}
//		int row = sList.size();
//		tStatistics.setTotal(row);
//		
//		if(row/pageSize == pageIndex) {
//			sList = sList.subList((pageIndex-1)*pageSize, row);
//		}else {
//			sList = sList.subList((pageIndex-1)*pageSize, pageIndex*pageSize);
//		}
		
		tx.commit();
		session.close();
		return tStatistics;

	}

}
