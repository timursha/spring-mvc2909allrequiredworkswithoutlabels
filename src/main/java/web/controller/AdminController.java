package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.dao.RoleDao;
import web.models.Role;
import web.models.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

	private  UserService userServiceEntityImpl;


	public AdminController(UserService userServiceEntityImpl) {
		this.userServiceEntityImpl = userServiceEntityImpl;
	}

	@GetMapping(value = {"admin/users","/"})
	public String indexUsers(Model model){
		model.addAttribute("users", userServiceEntityImpl.listUsers());
		// getting all users
//		List<Role> rolesList = new ArrayList<>();
//		rolesList.add(new Role("ROLE_ADMIN"));
//		rolesList.add(new Role("ROLE_USER"));
//		userServiceEntityImpl.saveRoles(rolesList);
//		User user = new User("Ivan", "abc@mail.ru");
//		user.setRoles(rolesList);
		return "index_users";
	}

	@GetMapping("admin/users/remove/{id}")
	public String removeUser(@PathVariable Long id) {
		userServiceEntityImpl.removeUserById(id);
		return "redirect:/admin/users";
	}

	@PostMapping("admin/users/edituser")
	public String updateUser(@ModelAttribute User user) {
		userServiceEntityImpl.updateUser(user);
		return "redirect:/admin/users";
	}

	@GetMapping("admin/users/edituser/{id}")
	public String updateUser(@PathVariable Long id, Model model){
		List<Role> rolesList = new ArrayList<>();
		rolesList.add(new Role("ROLE_ADMIN"));
		rolesList.add(new Role("ROLE_USER"));
		model.addAttribute("edituser", userServiceEntityImpl.getUserById(id));
		model.addAttribute("allRoles", rolesList);
		return "edituser";
	}

	@GetMapping("/admin/users/new")
		public String create(Model model){
		List<Role> rolesList = new ArrayList<>();
		rolesList.add(new Role("ROLE_ADMIN"));
		rolesList.add(new Role("ROLE_USER"));
		model.addAttribute("allRoles", userServiceEntityImpl.getAllRoles());
		model.addAttribute("user", new User());
			return "new";
		}


	@PostMapping("admin/users/new")
	public String create(@ModelAttribute("user") User user){

		userServiceEntityImpl.addUser(user);
		return "redirect:/admin/users";
	}
}
