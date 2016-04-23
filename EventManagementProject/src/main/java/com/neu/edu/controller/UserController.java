package com.neu.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
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
	
	
	@RequestMapping(value = "favoriteevent", method = RequestMethod.GET)
	public ModelAndView myPreferEventPage(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
		mv.setViewName("myevent");
		
	
		return mv;
	}
	

	@RequestMapping(value ="getprefernceurl", method=RequestMethod.GET)
    public String getPreference(HttpSession session, HttpServletResponse response) throws IOException { 
		ModelAndView mv = new ModelAndView();
		User u = (User)session.getAttribute("user");
		if(u == null){
	    	mv.setViewName("home");
			return "home";
	    }
	    UserDAO ud = new UserDAO();
	      
	    List<Preference> plist = ud.getUserPrefernce(u);
   	    
	    StringBuilder sb = new StringBuilder();
	    
	    for(int i = 0; i<plist.size(); i++) {
			sb.append(plist.get(i).getPreferenceCategory() + ",");
			System.out.println(plist.get(i).getPreferenceCategory());
	    }
	    
	    JSONObject obj = new JSONObject();
	    if(sb != null){
	    	obj.put("successmsg", sb.toString());
	    }else{
	    	obj.put("errormsg", "");
	    }
	            
        PrintWriter out = response.getWriter();
        out.print(obj);
	   	return null; 
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
