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
import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
public class EventController {

	@RequestMapping(value = "saveevent", method = RequestMethod.POST)
	public String saveUserEvent(@RequestParam("eventId") String id, @RequestParam("venue") String venue,
			@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("details") String details, @RequestParam("time") String time, @RequestParam("img") String img, HttpSession session) {

		System.out.println("Image is - " + img);
		User u = (User) session.getAttribute("user");

		if (u == null) {
			return "index";
		}
		Event event = new Event();
		Double eid = Double.parseDouble(id);
		event.setEventId(eid);
		event.setVenue(venue);
		event.setTime(time);
		event.setEventName(name);
		event.setImg(img);
		// event.setDescription(details);

		UserDAO ud = new UserDAO();
		ud.updateUserEvent(u, event);

		return "home";
	}

}
		
		
		