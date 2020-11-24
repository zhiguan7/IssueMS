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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ibm.dao.IssueDao;
import com.ibm.dao.UserDao;
import com.ibm.tables.Issue;
import com.ibm.tables.Total_Issue;
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
	
	
	public int insert(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println(issue);
		issue.setStatus("待解决");
		issue.setCreateDate(new Date());
		UserDao userservice = new UserDaoSevice();
		List<User> uList  = userservice.queryAll().getUsers();
		int b = -1;
		for(int a = 0;a<uList.size();a++) {
			if(uList.get(a).getUserId().compareTo(issue.getUpdateMan())==0) {
				b=1;
				}
		}
		if(b==1) {
		int i = -1;
		try {
			session.save(issue);
//			int i = query.executeUpdate();
			tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			i = 0;
		}
		i = 1;
//		session.close();
		return i;
		}else {
			return 2;
		}
		
	}

	public List<Issue> queryAll() throws SQLException, IOException {
		List<Issue> issues = factory.openSession().createQuery("FROM Issue").list();
		return issues;
	}

	public void delete(Issue issue) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(issue);
		tx.commit();
//		session.close();
	}

	public int update(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
//		SQLQuery query = session.createSQLQuery("update issue set solution = ? where issue_id = ?");
//		query.setString(1, issue.getSolution());
//		query.setInteger(2, issue.getIssueId());
//		int i = query.executeUpdate();
		try {
			Issue i = (Issue) session.get(Issue.class, issue.getIssueId());
			i.setSolution(issue.getSolution());
			i.setIssueId(issue.getIssueId());
			session.update(i);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		tx.commit();
//		session.close();
		return 1;
	}
	
	
	public Total_Issue searchWithFuzzy(Issue issue,String userId,Date createDate2,Date updateDate2,int pageIndex,int pageSize) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
		Total_Issue tIssue = new Total_Issue();
		
		if(issue.getIssueId()!=0) {
			criteria.add(Restrictions.and(Restrictions.eq("issueId", issue.getIssueId())));
		}
		if(userId!=null) {
			criteria.add(Restrictions.and(Restrictions.eq("userId",userId)));
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
		criteria.addOrder(Order.desc("createDate"));
		criteria.setFirstResult((pageIndex-1)*pageSize); 
		criteria.setMaxResults(pageSize);
		tIssue.setIssue(criteria.list());
		
		criteria.setFirstResult(0);
		int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		tIssue.setTotal(allCounts);
		
		tx.commit();
//		session.close();
		return tIssue;
	}

//	@Override
//	public List<Issue> searchWithPage(int pageIndex,int pageSize) throws SQLException, IOException {
//		// TODO Auto-generated method stub
//		
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
//		Criteria criteria = session.createCriteria(Issue.class);
//		
//		criteria.setFirstResult((pageIndex-1)*pageSize); //需要修改
//		criteria.setMaxResults(pageSize);
//		List<Issue> list = criteria.list();
//	
//		tx.commit();
//		session.close();
//		return list;
//	}

	@Override
	public boolean backChange(Issue issue) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
//			Query query = session.createQuery("update Issue set status = '待解决'  where issue_id = :id");
//			query.setParameter("id", issue.getIssueId());
//			query.executeUpdate(); 
//			session.getTransaction().commit();
			Issue i = (Issue) session.get(Issue.class, issue.getIssueId());
			i.setStatus("待解决");
			i.setIssueId(issue.getIssueId());
			session.update(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		tx.commit();
//	    session.close();
		return true;
	}

	@Override
	public boolean closeChange(Issue issue){
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
//			Query query = session.createQuery("update Issue set status = '关闭'  where issue_id = :id");
//			query.setParameter("id", issue.getIssueId());
//			query.executeUpdate();
//			Query query1 = session.createQuery("update Issue set final_date = :time  where issue_id = :id");
//			query1.setParameter("id", issue.getIssueId());
//			query1.setParameter("time", new Date(System.currentTimeMillis()));
//			query1.executeUpdate();
//			session.getTransaction().commit(); 
			
			Issue i = (Issue) session.get(Issue.class, issue.getIssueId());
			i.setStatus("关闭");
			i.setFinalDate(new Date());
			i.setIssueId(issue.getIssueId());
			session.update(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
//	    session.close();
		tx.commit();
		return true;
	}

	@Override
	public Total_Issue searchWithGlobal(String string) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
		
		return null;
	}
}
