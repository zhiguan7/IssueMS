package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ibm.dao.IssueDao;
import com.ibm.tables.Issue;
import com.ibm.tables.User;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javassist.expr.NewArray;

@Service
public class IssueDaoService implements IssueDao {

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
	
	
	
	public int insert(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		session.beginTransaction();
		System.out.println(issue);
		String sql = "INSERT INTO issue ( issue_name, status, create_date, create_man, level, type, beta, user_id, update_man, step, solution, plan_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(1, issue.getIssueName());
		query.setString(2, "未验证");
		query.setString(3, DateFormat.getDateInstance().format(new Date()));
		query.setString(4, issue.getCreateMan());
		query.setInteger(5, issue.getLevel());
		query.setString(6, issue.getType());
		query.setString(7, issue.getBeta());
		query.setInteger(8, issue.getUserId());
		query.setString(9, issue.getUpdateMan());
		query.setString(10, issue.getStep());
		query.setString(11, issue.getSolution());
		query.setDate(12, issue.getPlanDate());
//		session.save(issue);
		int i = query.executeUpdate();
//		tx.commit();
		session.close();
		return i;
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

	public int update(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("update issue set solution = ? where issue_id = ?");
		query.setString(1, issue.getSolution());
		query.setInteger(2, issue.getIssueId());
		int i = query.executeUpdate();
		tx.commit();
		session.close();
		return i;
	}
	
	
	public List<Issue> searchWithFuzzy(Issue issue,Date createDate2,Date updateDate2) throws SQLException, IOException {

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
		
//		criteria.add(
//	            Restrictions.or(Restrictions.eq("issueId", issue.getIssueId()),
//	            Restrictions.or(Restrictions.eq("status", issue.getStatus()),
//	            Restrictions.or(Restrictions.like("createMan",issue.getCreateMan() ,MatchMode.ANYWHERE),
//	            Restrictions.or(Restrictions.like("updateMan",issue.getUpdateMan() ,MatchMode.ANYWHERE),
//	            Restrictions.or(Restrictions.between("createDate", issue.getCreateDate(), createDate2),
//	            Restrictions.or(Restrictions.between("updateDate", issue.getUpdateDate(), updateDate2)))))))).list();
		
		if(issue.getIssueId()!=0) {
			criteria.add(Restrictions.and(Restrictions.eq("issueId", issue.getIssueId())));
		}
		if(issue.getStatus()!=null) {
			criteria.add(Restrictions.and(Restrictions.eq("status", issue.getStatus())));
		}
		if(issue.getCreateMan()!=null) {
			criteria.add(Restrictions.and(Restrictions.like("createMan",issue.getCreateMan() ,MatchMode.ANYWHERE)));
		}
		if(issue.getUpdateMan()!=null) {
			criteria.add(Restrictions.and(Restrictions.like("updateMan",issue.getUpdateMan() ,MatchMode.ANYWHERE)));
		}
		if(issue.getCreateDate()!=null&&createDate2!=null) {
			criteria.add(Restrictions.and(Restrictions.between("createDate", issue.getCreateDate(), createDate2)));
		}
		if(issue.getUpdateDate()!=null&&updateDate2!=null) {
			criteria.add(Restrictions.and(Restrictions.between("updateDate", issue.getUpdateDate(), updateDate2)));
		}
		
		List<Issue> list = criteria.list();
		
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<Issue> searchWithPage(int pageIndex,int pageSize) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
		
		criteria.setFirstResult((pageIndex-1)*pageSize); //需要修改
		criteria.setMaxResults(pageSize);
		
		List<Issue> list = criteria.list();
		
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public boolean backChange(Issue issue) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("update Issue set status = '待解决'  where issue_id = :id");
			query.setParameter("id", issue.getIssueId());
			query.executeUpdate(); 
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	    session.close();
		return true;
	}

	@Override
	public boolean closeChange(Issue issue){
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("update Issue set status = '关闭'  where issue_id = :id");
			query.setParameter("id", issue.getIssueId());
			query.executeUpdate();
			Query query1 = session.createQuery("update Issue set final_date = :time  where issue_id = :id");
			query1.setParameter("id", issue.getIssueId());
			query1.setParameter("time", new Date(System.currentTimeMillis()));
			query1.executeUpdate();
			session.getTransaction().commit(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    session.close();
		return true;
	}
}
