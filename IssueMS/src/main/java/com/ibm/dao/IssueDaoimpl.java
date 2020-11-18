package com.ibm.dao;

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
import org.springframework.stereotype.Service;

import com.ibm.tables.Issue;

@Service
public class IssueDaoimpl implements IssueDao {

	private final static int PAGE_SIZE = 20;
	
	
	private static SessionFactory factory;
	
	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

	
//测试代码
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
	
//	public static void main(String[] args) throws SQLException, IOException {
//		Issue issue = new Issue();
//		IssueDaoimpl i = new IssueDaoimpl();
////		issue.setIssueId(1);
////		issue.setUserId(1);
////		issue.setBeta("12343214");
////		issue.setCreateMan("三");
////		i.insert(issue);
////		i.fuzzySearch(issue);
//	}
	
	

	public void insert(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(issue);
		tx.commit();
		session.close();
	}

	public List<Issue> queryAll() throws SQLException, IOException {
		List<Issue> issues = factory.openSession().createQuery("FROM Issue").list();
//		List<Issue> issues = factory.openSession().createSQLQuery("select * from issue").addEntity(Issue.class).list();
//		for (Issue issue : issues) {
//			System.out.println("id=" + issue.getUserId() + ", name=" + issue.getIssueName() + ", status="
//					+ issue.getStatus() + ", create_date=" + DateFormat.getDateInstance().format(issue.getCreateDate())
//					+ ", create_man=" + issue.getCreateMan() + ", level=" + issue.getLevel() + ", type="
//					+ issue.getType() + ", beta=" + issue.getBeta() + ", user_id=" + issue.getUserId());
//		}
		return issues;
	}

	public void delete(Issue issue) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(issue);
		tx.commit();
		session.close();
	}

	public void update(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(issue);
		tx.commit();
		session.close();
	}
	
	
	public List<Issue> searchWithFuzzy(Issue issue) throws SQLException, IOException {

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
		
		@SuppressWarnings("unchecked")
		List<Issue> list = criteria.add(
	            Restrictions.or(Restrictions.eq("issueId", issue.getIssueId()),
	            Restrictions.or(Restrictions.eq("status", issue.getStatus()),
	            Restrictions.or(Restrictions.like("createMan",issue.getCreateMan() ,MatchMode.ANYWHERE),
	            Restrictions.or(Restrictions.eq("createDate",issue.getCreateDate())))))).list();
		
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<Issue> searchWithPage(int pageIndex) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Issue.class);
		
		criteria.setFirstResult((pageIndex-1)*PAGE_SIZE); //需要修改
		criteria.setMaxResults(PAGE_SIZE);
		
		List<Issue> list = criteria.list();
		
		tx.commit();
		session.close();
		return list;
	}
}
