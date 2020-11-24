package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import sun.security.util.Length;

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
			if(uList.get(a).getUserName().compareTo(issue.getUpdateMan())==0) {
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
	public List<Issue> searchWithGlobal(String string) throws Exception {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
		Date date = null,date1 = null;
		int len = string.length(),level = 0;
		
		if(string.equals("最高")) {
			level = 4;
		}else if (string.equals("较高")) {
			level = 3;
		}else if (string.equals("一般")) {
			level = 2;
		}else if (string.equals("低")) {
			level = 1;
		}
		
		date = format(string);
		if(len==4) {
			if(date!=null) {
				Calendar  calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(calendar.DATE,364); 
				date1=calendar.getTime(); 
				criteria.add(Restrictions.or(Restrictions.eq("status", string),
						Restrictions.like("createMan",string ,MatchMode.ANYWHERE),
						Restrictions.like("updateMan",string ,MatchMode.ANYWHERE),
						Restrictions.like("issueName",string ,MatchMode.ANYWHERE),
						Restrictions.like("userId",string ,MatchMode.ANYWHERE),
						Restrictions.eq("level", level),
						Restrictions.eq("type", string),
						Restrictions.eq("beta", string),
						Restrictions.between("createDate", date, date1)
						));
				List<Issue> list = criteria.list();
				return list;
			}
		}else if(len==6||len==7){
			if(date!=null) {
				Calendar  calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(calendar.DATE,29); 
				date1=calendar.getTime(); 
				criteria.add(Restrictions.or(Restrictions.eq("status", string),
						Restrictions.like("createMan",string ,MatchMode.ANYWHERE),
						Restrictions.like("updateMan",string ,MatchMode.ANYWHERE),
						Restrictions.like("issueName",string ,MatchMode.ANYWHERE),
						Restrictions.like("userId",string ,MatchMode.ANYWHERE),
						Restrictions.eq("level", level),
						Restrictions.eq("type", string),
						Restrictions.eq("beta", string),
						Restrictions.between("createDate", date, date1)
						));
				List<Issue> list = criteria.list();
				return list;
			}
		}else {
			if(date!=null) {
				criteria.add(Restrictions.or(Restrictions.eq("status", string),
						Restrictions.like("createMan",string ,MatchMode.ANYWHERE),
						Restrictions.like("updateMan",string ,MatchMode.ANYWHERE),
						Restrictions.like("issueName",string ,MatchMode.ANYWHERE),
						Restrictions.like("userId",string ,MatchMode.ANYWHERE),
						Restrictions.eq("level", level),
						Restrictions.eq("type", string),
						Restrictions.eq("beta", string),
						Restrictions.eq("createDate", date)
						));
				List<Issue> list = criteria.list();
				return list;
			}
		}
		criteria.add(Restrictions.or(Restrictions.eq("status", string),
				Restrictions.like("createMan",string ,MatchMode.ANYWHERE),
				Restrictions.like("updateMan",string ,MatchMode.ANYWHERE),
				Restrictions.like("issueName",string ,MatchMode.ANYWHERE),
				Restrictions.like("userId",string ,MatchMode.ANYWHERE),
				Restrictions.eq("level", level),
				Restrictions.eq("type", string),
				Restrictions.eq("beta", string)
				));

		List<Issue> list = criteria.list();
		return list;
	}
	
	//全局搜索日期处理
	public Date format(String strDate) throws Exception {
		Pattern pattern = Pattern
	                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))"
	                		+ "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	                		+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|"
	                		+ "(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|"
	                		+ "(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	                		+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"
	                		+ "(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	    Matcher m = pattern.matcher(strDate);
	        
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		int len = strDate.length();
		System.out.println(m.matches());
		if(m.matches()) {
			if(len==6||len==7) { 
				String year = strDate.substring(0, 4);
				String month = "";
				if(len==7) {
					month = strDate.substring(strDate.length()-2);
				}else {
					month = strDate.substring(strDate.length()-1);
				}
				if(Integer.parseInt(month)>12) {
					return null;
				}
				String date = year + "-" + month + "-01";
				return s.parse(date);
			}
			
			if(len==10||len==9||len==8) {
				String year = strDate.substring(0, 4);
				String month = "";
				String day = "";
				String date = "";
				System.out.println(year);
				
				if(len==10) {
					month = strDate.substring(5,7);
					day = strDate.substring(len-2);
				}
				if(len==8) {
					month = strDate.substring(5,6);
					day = strDate.substring(len-1);
				}
				if(len==9) {
					month = strDate.substring(5,7);
					day = strDate.substring(len-1);
					if(!month.matches("[0-9]+")) {
						month = strDate.substring(5,6);
						day = strDate.substring(len-2);
					}
				}
				date = year + "-" + month + "-" + day;
				return s.parse(date);
			}
			return null;
		}else {
			if(len==4&&strDate.matches("[0-9]+")) {
				String date = strDate +"-01-01";
				return s.parse(date);
				
			}
			if(len==6||len==7) { 
				String year = strDate.substring(0, 4);
				String month = "";
				if(len==7) {
					month = strDate.substring(strDate.length()-2);
				}else {
					month = strDate.substring(strDate.length()-1);
				}
				if(year.matches("[0-9]+")&&month.matches("[0-9]+")) {
					if (month.equals("0")||month.equals("00")) {
						return null;
					}else {
						String date = year + "-" + month + "-01";
						return s.parse(date);
					}
				}
			} 
			return null;
		}
	}
	
	
}
