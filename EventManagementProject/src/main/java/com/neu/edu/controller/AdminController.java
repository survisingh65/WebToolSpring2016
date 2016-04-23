package com.neu.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.EventDAO;
import com.neu.edu.dao.UserDAO;
import com.neu.edu.pojo.AdminEvent;
import com.neu.edu.pojo.Event;
import com.neu.edu.pojo.Preference;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/")
public class AdminController {
	
	@RequestMapping(value = "viewcreatedevents", method = RequestMethod.GET)
	public ModelAndView getAdminCreatedEvent(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
		EventDAO edao = new EventDAO();
		List<AdminEvent> alist = edao.getAdminEvent();
		
		mv.addObject("al",alist);
		mv.setViewName("adminViewEvent");
		return mv;
	}
	
	@RequestMapping(value = "uploadnewevent", method = RequestMethod.GET)
	public String loadForm(@ModelAttribute("adminEvent") AdminEvent adminEvent, BindingResult result) {
		return "adminUploadEvent";
	}
	
	@RequestMapping(value = "createevent", method = RequestMethod.POST)
	public ModelAndView adminCreateEvent(@ModelAttribute("adminEvent") AdminEvent adminEvent, HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
		EventDAO edao = new EventDAO();
		edao.createAdminEvent(adminEvent);
		
		return getAdminCreatedEvent(session);
	}
	
	@RequestMapping(value = "deleteevent", method = RequestMethod.POST)
	public ModelAndView adminDeleteEvent(@RequestParam("id") String id, HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		User u = (User) session.getAttribute("user");
         
		if (u == null) {
			mv.setViewName("index");
			return mv;
		}
		
		EventDAO edao = new EventDAO();
		edao.deleteAdminEvent(id);
		
		return getAdminCreatedEvent(session);
	}
	
	@RequestMapping(value = "createadminuser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user) {

		try {
			System.out.print("Just Inside register");
			UserDAO ud = new UserDAO();
			ud.createAdminUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "adminCreatedSuccessfully";

	}
	
	@RequestMapping(value = "newadminform", method = RequestMethod.GET)
	public String getAdminCreateForm(@ModelAttribute("user") User user, BindingResult result) {
		return "createAdminUser";
	}

	@RequestMapping(value = "getadminevent", method = RequestMethod.GET)
	public String getAdminEvent(HttpSession session, HttpServletResponse response) throws IOException {
		
		EventDAO ed = new EventDAO();
		
		List<AdminEvent> alist = ed.getAdminEvent();
	    
		JSONArray jsonArray = new JSONArray();
		
	    if(alist.size() > 0){
		    for(AdminEvent ae : alist){
		    	JSONObject obj = new JSONObject();
		    	obj.put("eventId", ae.getEventId());
		    	obj.put("eventName", ae.getEventName());
		    	obj.put("date", ae.getDate());
		    	obj.put("time", ae.getTime());
		    	obj.put("description", ae.getDescription());
		    	obj.put("venue", ae.getVenue());
		    	obj.put("city", ae.getCity());
		    	obj.put("address", ae.getAddress());
		    	obj.put("category", ae.getCategory());
		    	obj.put("organizer", ae.getOrganizer());
		    	
		    	jsonArray.put(obj);
		    }
	    }else{
	    	jsonArray.put("");
	    }
	            
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
	   	return null;
		
	}

}
		
		
		