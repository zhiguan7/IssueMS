//package com.ibm.controller;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ibm.dao.LoginRegistDao;
//import com.ibm.tables.User;
//
//public class RegistControl {
//
//	@Autowired
//	private IUserDao userService;
//	private LoginRegistDao LoginRegistDao;
//
//	@RequestMapping(value = "/regist", method = { RequestMethod.POST, RequestMethod.GET })
////	@CrossOrigin
////	@PostMapping("/regist")
//	public ModelAndView regist(String username, String password, int userId, String email) {
//		ModelAndView mav = new ModelAndView();
//
////		if (username.equals("")||password.equals("")) {
////			mav.addObject("err", "用户名和密码不能为空");
////			mav.setViewName("regist");
////		}
//		User user = userService.findUser(username);
//		if (user != null) {
//			mav.addObject("msg", "用户已存在");
////			mav.setViewName("regist");
//			return mav;
//		} else {
//			User user2 = new User();
//			user2.setUserName(username);
//			user2.setPassword(password);
//			user2.setUserId(userId);
//			user2.setEmail(email);
//			user2.setStatus("激活");
//			user2.setCreateDate(new Date());
//			LoginRegistDao.save(user2);
//			mav.addObject("succse", "注册成功");
//			mav.setViewName("login");
//			return mav;
//		}
//	}
////	// @ResponseBody
////	public Map<String, Object> regist(@RequestParam String username, @RequestParam int userId,
////			@RequestParam String email, @RequestParam String password) {
////		Map<String, Object> map = new HashMap<String, Object>();
////		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
////			map.put("msg", "账户密码不能为空");
////			return map;
////		}
////		// 验证用户名
////		User user = userMapper.getUserByUsername(username);
////		if (user != null) {
////			map.put("msg", "该用户已经注册");
////			return map;
////		}
////		User user2 = new User();
////		user2.setUserId(userId);
////		user2.setEmail(email);
////		user2.setUserName(username);
////		user2.setPassword(password);
////
////		boolean count = userMapper.saveUser(user2);
////		if (count == false) {
////			map.put("msg", "失败");
////			return map;
////		} else {
////			map.put("msg", "成功");
////		}
////
//////		System.out.println("name:" + user2.getUserName());
//////		System.out.println("password:" + user2.getPassword());
//////		map.put("msg", "注册成功");
////		return map;
////
////	}
//}
