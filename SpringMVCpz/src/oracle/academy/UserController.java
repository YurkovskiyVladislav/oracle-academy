package oracle.academy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import oracle.academy.model.Role;
import oracle.academy.model.User;

@Controller
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
	
	//Fill users to DB
		User user1 = new User();
		user1.setFirstName("Serg");
		user1.setLastName("Klistov");
		user1.setAge(23);
		user1.setEmail("123@mail");
		user1.setRole(Role.SUPER_ADMIN);
		userDao.create(user1);
		
		User user2 = new User();
		user2.setFirstName("Eva");
		user2.setLastName("Lebowski");
		user2.setAge(16);
		user2.setEmail("baby@mail");
		user2.setRole(Role.USER);
		userDao.create(user2);
		
		User user3 = new User();
		user3.setFirstName("Vasya");
		user3.setLastName("Pupkin");
		user3.setAge(34);
		user3.setEmail("uasya@mail");
		user3.setRole(Role.USER);
		userDao.create(user3);
		
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView viewAllUsers() {
		ModelAndView modelAndView = new ModelAndView("userList");
		List<User> users = userDao.getAll();
		users = userDao.getAll();
		System.out.println(users.size());
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUser(String id) {
		userDao.delete(userDao.getById(Long.parseLong(id)));
		return "redirect:/users";		
	}	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(String id) {
		User user = userDao.getById(Long.parseLong(id));
		return new ModelAndView("addUser", "user", user);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser() {
		return new ModelAndView("addUser");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String updateUser(@RequestParam(required = false) String id,
			String firstName, String lastName, String age, String email, String role) {
		User user = new User();
		user.setAge(Integer.parseInt(age));
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		Role role2 = null;
		if (Role.ADMIN.toString().equals(role)) {
			role2 = Role.ADMIN;
		} 
		else if (Role.SUPER_ADMIN.toString().equals(role)) {
			role2 = Role.SUPER_ADMIN;
		}
		else role2 = Role.USER;
		user.setRole(role2);
		if (id != null) {
			user.setId(Long.parseLong(id));
			userDao.update(user);
		}
		else {
			userDao.create(user);
		}		
		return "redirect:/users";		
	}

}







