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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ibm.dao.UserDao;
import com.ibm.tables.Statistics;
import com.ibm.tables.Total_Statistics;
import com.ibm.tables.Total_User;
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
		try {
			User user = (User) session.get(User.class, userid);
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

	public int UpdateAuthority(int userid) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			User user = (User) session.get(User.class, userid);
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
	public Total_Statistics searchWithFuzzy(int id, String name, int pageIndex, int pageSize)
			throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
//		Criteria criteria1 = session.createCriteria(Issue.class);
//		Criteria criteria2 = session.createCriteria(Issue.class);
//		Criteria criteria3 = session.createCriteria(Issue.class);
		Criteria criteria4 = session.createCriteria(User.class);
		Total_Statistics tStatistics = new Total_Statistics();
		Statistics s = null;
		List<Statistics> statistics = new ArrayList<Statistics>();
		List<User> list = null;

		if (id != 0) {
			criteria4.add(Restrictions.and(Restrictions.eq("userId", id)));
		}
		if (name != null) {
			criteria4.add(Restrictions.and(Restrictions.like("userName", name, MatchMode.ANYWHERE)));
		}
		list = criteria4.list();

		Query query = session.createSQLQuery("select count(*) from issue where user_id=?");

		for (User i : list) {
			s = new Statistics();
			s.setUserId(i.getUserId());
			s.setUserName(i.getUserName());

			query.setParameter(1, i.getUserId());
			int cNum = ((Number) query.uniqueResult()).intValue();
//			criteria1.add(Restrictions.eq("userId", i.getUserId()));
//			int cNum = ((Long) criteria1.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			s.setcNum(cNum);
//
//			criteria2.add(Restrictions.eq("updateMan", i.getUserName()));
//			int rNum = ((Long) criteria2.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//			s.setrNum(rNum);
//
//			criteria3.add(Restrictions.eq("userId", i.getUserId()));
//			int aNum = ((Long) criteria3.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//			s.setaNum(aNum);

			System.out.println(s);
			statistics.add(s);
		}

		tStatistics.setStatistics(statistics);

		criteria4.setFirstResult(0);
		int allCounts = ((Long) criteria4.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		tStatistics.setTotal(allCounts);
		tx.commit();
//		session.close();
		return tStatistics;

	}

	public Total_User AdminFuzzyquery(int useid, String username, int pageIndex, int pageSize)
			throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		Total_User user = new Total_User();

		if (useid != 0) {
			criteria.add(Restrictions.and(Restrictions.eq("userId", useid)));
		}
		if (username != null) {
			criteria.add(Restrictions.and(Restrictions.like("userName", username, MatchMode.ANYWHERE)));
		}
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
