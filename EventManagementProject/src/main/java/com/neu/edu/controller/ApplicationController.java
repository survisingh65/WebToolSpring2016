package com.neu.edu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/")
public class ApplicationController {
	
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public ModelAndView contactPage() {
		
		ModelAndView mv = new ModelAndView();
		         
		mv.setViewName("contact");
		
	    return mv;
	}
	
	@RequestMapping(value = "contact2", method = RequestMethod.GET)
	public ModelAndView contact2Page() {
		
		ModelAndView mv = new ModelAndView();
		         
		mv.setViewName("contact2");
		
	    return mv;
	}
}
