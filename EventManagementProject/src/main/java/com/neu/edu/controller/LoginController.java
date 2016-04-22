package com.neu.edu.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.*;
import com.neu.edu.pojo.Event;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;
	
	//@Autowired
	//UserDAO ud; 
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(userValidator);
	}
	
	@RequestMapping(value ="login.html", method=RequestMethod.POST)
	public String doSubmit(@ModelAttribute("user")User user, BindingResult result,HttpSession session){
		 
		userValidator.validate(user, result);
		if(result.hasErrors()){
			return "home2";
		}
		
		try{
			System.out.print("Just Inside Login Controller");
			UserDAO ud = new UserDAO();
			User u = ud.verfiyLogin(user);
			if(u == null){
				return "index";
			}
			else
			{
				session.setAttribute("user",user);	
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "home";
		
	}

	@RequestMapping(method=RequestMethod.GET)
    public String loadForm(@ModelAttribute("user")User user, BindingResult result) { 
   
        return "index"; 
    }
	
	@RequestMapping(value ="login.html", method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("user")User user, BindingResult result) { 
   
        return "index"; 
    }
	
	@RequestMapping(value ="register", method=RequestMethod.GET)
    public String registerForm(@ModelAttribute("user")User user, BindingResult result) { 
   
        return "registration"; 
    }
	
	@RequestMapping(value ="saveevent", method=RequestMethod.POST)
    public String saveEvent(@RequestParam("eventId") String id, 
    		@RequestParam("venue") String venue, @RequestParam("name") String name,
    		@RequestParam("address") String address, @RequestParam("details") String details,
    		@RequestParam("time") String time, HttpSession session) { 
	    
		User u = (User)session.getAttribute("user");
	    
		if(u == null){
	    	return "index";
	    }
	    
	    System.out.println(u.getEmail());
	    System.out.println(u.getPersonID());
	    System.out.println(u.getFirstName());
	    System.out.println(u.getLastName());
	    
	    Event event = new  Event();
	    Double eid = Double.parseDouble(id);
	    event.setEventId(eid);
	    event.setVenue(venue);
	    event.setTime(time);
	    event.setEventName(name);
	    //event.setDescription(details);
	    
	    UserDAO ud = new UserDAO();
		ud.updateUserEvent(u, event);

		return "home"; 
    }
	
	@RequestMapping(value ="saveregistereduser", method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user")User user, BindingResult result){
		
		userValidator.validate(user, result);
		if(result.hasErrors()){
			return "home";
		}
		
		try{
			System.out.print("Just Inside register");
			  UserDAO ud = new UserDAO();
			  ud.createUserDatabase(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return "index";
		
	}
	
	
		
	
}
