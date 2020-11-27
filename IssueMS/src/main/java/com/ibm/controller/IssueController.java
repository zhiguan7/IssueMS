package com.ibm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.dao.IssueDao;
import com.ibm.service.IssueDaoService;
import com.ibm.tables.Issue;
import com.ibm.tables.Issue_Image;
import com.ibm.tables.Total_Issue;
import com.mysql.jdbc.Blob;

import io.micrometer.core.ipc.http.HttpSender.Request;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@RestController
public class IssueController {
	
	@Autowired
	private IssueDao issueDao;


//	/*
//	 * 分页查询
//	 * 
//	 * pageindex:页码
//	 * pagesize:页大小
//	 * 
//	 * */
//	@PostMapping(value = "/PageIssue")
//	public List<Issue> searchPage(@RequestBody Map<String, Integer> page) throws SQLException, IOException{
//		issueDao = new IssueDaoService();
//		List<Issue> list = null;
//		list = issueDao.searchWithPage(page.get("pageindex"),page.get("pagesize"));
//		return list;
//	}
	
	/*
	 * 模糊查询
	 * 
	 * issueid: issue No
	 * status: 状态
	 * createman: 创建人
	 * updateman: 修改人
	    *  创建时间: createdate1 至 createdate2
	    *  修改时间: updatedate1 至 updatedate2
	 *
	 * */
	//模糊查询（按创建时间排序）
	@CrossOrigin
	@RequestMapping(value = "/searchIssue",method = RequestMethod.POST)
	public Total_Issue searchFuzzy(@RequestBody Map<String, String> issue) throws Exception{
		issueDao = new IssueDaoService();
		Issue i = new Issue();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(issue.get("issueId")==null) {
			i.setIssueId(0);
		}else {
			i.setIssueId(Integer.parseInt(issue.get("issueId")));
		}
		
		i.setStatus(issue.get("status"));
		i.setCreateMan(issue.get("createMan"));
		i.setUpdateMan(issue.get("updateMan"));
		String c1 = issue.get("createDate"),c2 = issue.get("date2") , u1 = issue.get("updateDate"), u2 = issue.get("date4");
		Date c3 = null ,u3 = null;  
		if (c1!=null) { 
			i.setCreateDate(format.parse(c1));
		}
		if (u1!=null) {
			i.setUpdateDate(format.parse(u1));
		}
		if (c2!=null) {
			c3 = format.parse(c2);
		}
		if (u2!=null) {
			u3 = format.parse(u2);
		}
//		i.setCreateDate(new Date(issue.get("createdate1")));
//		i.setUpdateDate(new Date(issue.get("updatedate1")));
		Total_Issue tIssue = null;
		tIssue = issueDao.searchWithFuzzy(i,issue.get("userId"),c3,u3,Integer.parseInt(issue.get("pageIndex")),Integer.parseInt(issue.get("pageSize")));
		return tIssue;
}
	
    //模糊查询（按issueId排序）
	@CrossOrigin
	@RequestMapping(value = "/searchIssue2",method = RequestMethod.POST)
	public Total_Issue searchFuzzy2(@RequestBody Map<String, String> issue) throws Exception{
		issueDao = new IssueDaoService();
		Issue i = new Issue();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		i.setIssueId(Integer.parseInt(issue.get("issueId")));
		i.setStatus(issue.get("status"));
		i.setCreateMan(issue.get("createMan"));
		i.setUpdateMan(issue.get("updateMan"));
		String c1 = issue.get("createDate"),c2 = issue.get("date2") , u1 = issue.get("updateDate"), u2 = issue.get("date4");
		Date c3 = null ,u3 = null; 
		if (c1!=null) { 
			i.setCreateDate(format.parse(c1));
		}
		if (u1!=null) {
			i.setUpdateDate(format.parse(u1));
		}
		if (c2!=null) {
			c3 = format.parse(c2);
		}
		if (u2!=null) {
			u3 = format.parse(u2);
		}
//		i.setCreateDate(new Date(issue.get("createdate1")));
//		i.setUpdateDate(new Date(issue.get("updatedate1")));
		Total_Issue tIssue = null;
		tIssue = issueDao.searchWithFuzzy2(i,issue.get("userId"),c3,u3,Integer.parseInt(issue.get("pageIndex")),Integer.parseInt(issue.get("pageSize")));
		return tIssue;
}
	
	//全局搜索
	@CrossOrigin
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public List<Issue> searchGlobal(@RequestBody Map<String, String> receive){
		List<Issue> list = null;
		try {
			if(receive.get("send").equals("")||receive.get("send").trim().isEmpty()) {
				return null;
			}else {
				list = issueDao.searchWithGlobal(receive.get("send").trim());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return list;
}
	
	//退回修改
	@CrossOrigin
	@RequestMapping(value = "/back",method = RequestMethod.POST)
	public boolean Back(@RequestBody Map<String, Integer> i) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(i.get("issueId"));
		boolean flag = false;
		flag = issueDao.backChange(issue);
		return flag;
	}
	
	//关闭issue
	@CrossOrigin
	@RequestMapping(value = "/finish",method = RequestMethod.POST)
	public boolean finish(@RequestBody Map<String, Integer> i) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(i.get("issueId"));
		boolean flag = false;
		flag = issueDao.closeChange(issue);
		return flag;
	}
	
	//创建issue
	@CrossOrigin
	@PostMapping(path = "/createIssue")
	public int createIssue(@RequestBody Issue issue) throws SQLException, IOException {
		System.out.println(issue);
		issueDao = new  IssueDaoService();
		int i = issueDao.insert(issue);
		return i;
	}
	
	//修改issue-填写解决方案
	@CrossOrigin
	@PostMapping(path = "/updateSolotion")
	public int updateSolotion(@RequestBody Issue issue) throws SQLException, IOException {
		issueDao = new  IssueDaoService();
		Issue issue2 = new Issue();
		issue2.setIssueId(issue.getIssueId());
		issue2.setSolution(issue.getSolution());
		issue2.setStatus("待验证");
		System.out.println(issue2);
		int i = issueDao.update(issue2);
		return i;
	}
	
	//上传issue截图
	@CrossOrigin
	@RequestMapping(value="uploadImage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public int uploadImage(@RequestParam("image")MultipartFile file1,@RequestParam("issueId")int id,HttpServletRequest request,HttpServletResponse response) throws SQLException {
		issueDao = new  IssueDaoService();
		
		System.out.println("-----------------"+file1);
		 File file = null;
         try {   
            String originalFilename = file1.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file=File.createTempFile(filename[0], "."+filename[1]);
            file1.transferTo(file);
             file.deleteOnExit();        
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println("-----------------"+file);
		System.out.println("+++++++++++++++++"+id);
		Issue_Image image = new Issue_Image(file,id);
		int i = issueDao.upLoadIssue_Image(image);
		return 1 ;
	}
	@CrossOrigin
	@RequestMapping(value="/downloadImage",method=RequestMethod.POST)
	@ResponseBody
	public String downloadImage(@RequestBody Issue issue1) throws SQLException, IOException {
		issueDao = new  IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(issue1.getIssueId());
		issue = issueDao.downloadIssue_Image(issue);
		System.out.println("======================="+issue1.getIssueId());
		if(issue.getImage()==null) {
			return "-1";
		}
		BufferedImage bi1 = ImageIO.read(new ByteArrayInputStream(issue.getImage()));
	    Image image = SwingFXUtils.toFXImage(bi1, null);
	    System.out.println(image);
	    File outputfile  = new File("save"+new Date().getSeconds()+".jpg");
	    ImageIO.write(bi1,"jpg",outputfile);
	    String url = outputfile.getPath();
	    outputfile.deleteOnExit(); 
		return url;
	}
	
}
