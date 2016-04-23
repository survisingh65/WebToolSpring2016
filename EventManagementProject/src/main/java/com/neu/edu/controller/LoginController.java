package com.neu.edu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.*;
import com.neu.edu.pojo.AdminEvent;
import com.neu.edu.pojo.Event;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "login.html", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("user") User user, BindingResult result, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			mv.setViewName("home2");
			return mv;
		}

		System.out.print("Just Inside Login Controller");
		UserDAO ud = new UserDAO();
		User u = ud.verfiyLogin(user);
		try {
			if (u == null) {
				mv.setViewName("index");
				return mv;
			} else {
				session.setAttribute("user", u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u.getStatus().equals("admin")) {
			AdminController ac = new AdminController();
			return ac.getAdminCreatedEvent(session);
		} else {
			mv.setViewName("home");
			return mv;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String loadForm(@ModelAttribute("user") User user, BindingResult result) {
		return "index";
	}

	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result) {
		return "index";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerForm(@ModelAttribute("user") User user, BindingResult result) {
		return "registration";
	}

	@RequestMapping(value = "saveregistereduser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user, BindingResult result) {

		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "home";
		}

		try {
			System.out.print("Just Inside register");
			UserDAO ud = new UserDAO();
			ud.createUserDatabase(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "index";

	}

}
