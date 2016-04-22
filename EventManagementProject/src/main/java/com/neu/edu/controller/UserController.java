package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.UserDAO;
import com.neu.edu.pojo.Event;
import com.neu.edu.pojo.Preference;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/")
public class UserController {

	@RequestMapping(value = "history", method = RequestMethod.GET)
	public ModelAndView getUserEventHistory(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
		UserDAO ud = new UserDAO();
		User user = ud.getUserEventHistory(u);
		mv.addObject("userev",user.getEvents());
		mv.setViewName("history");
		System.out.println(user.getEvents().size());
		
		return mv;
	}
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView homePage(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
	
		mv.setViewName("home");
	
		return mv;
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logoutPage(@ModelAttribute("user")User user,HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		session.removeAttribute("user");
	
		mv.setViewName("index");
		
	
		return mv;
	}
	
	@RequestMapping(value = "favorite", method = RequestMethod.GET)
	public ModelAndView favoritePage(@ModelAttribute("user")User user,HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
		mv.setViewName("favoritecategory");
		
	
		return mv;
	}
	
	

	@RequestMapping(value ="getprefernceurl", method=RequestMethod.POST)
    public ModelAndView getPreference(HttpSession session) { 
		ModelAndView mv = new ModelAndView();
		User u = (User)session.getAttribute("user");
	    
		if(u == null){
	    	mv.setViewName("home");
			return mv;
	    }
	    UserDAO ud = new UserDAO();
	      
	    //ud.updateUserPrefernce(u, prefSet);
   	    
	   	return mv; 
    }

	
	
	@RequestMapping(value ="saveprefernceurl", method=RequestMethod.POST)
    public ModelAndView savePreference(@RequestParam("up") String pref,HttpSession session) { 
		ModelAndView mv = new ModelAndView();
		User u = (User)session.getAttribute("user");
	    
		if(u == null){
	    	mv.setViewName("home");
			return mv;
	    }
	    
	    System.out.println(pref);

	    UserDAO ud = new UserDAO();
	    
	    String pList[]=StringUtils.split(pref, ",");
	    
	    //List<Preference> prefList = new ArrayList<Preference>();
	    
	    Set prefSet = new HashSet<Preference>();
	    
	    for (String s : pList) {
			Preference p = new Preference();
			p.setPreferenceCategory(s);
			prefSet.add(p);
	    }  
	    ud.updateUserPrefernce(u, prefSet);
   	    
	    mv.setViewName("home");
	    
	   	return mv; 
    }
}
