package com.neu.edu.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.neu.edu.pojo.Event;
import com.neu.edu.pojo.Person;
import com.neu.edu.pojo.*;
import com.neu.edu.pojo.User;

public class UserDAO extends DAO{

	public UserDAO(){
		
	}

	public User createUserDatabase(User u){
		try{
			begin();
			getSession().save(u);
            commit();
			
	        return u;
		}catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
		return null;
	}
	
	public User verfiyLogin(User u){
		try{
			begin();
			System.out.println("inside DAO");			
			Query q = getSession().createQuery("from User where email = :eid and password = :pwd");
			q.setString("eid", u.getEmail());
			q.setString("pwd", u.getPassword());
            User user = (User) q.uniqueResult();
            commit();
            return user;
		}catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
		
		return null;
	}
	
	public User updateUserEvent(User u, Event e){
		try{
			begin();
			System.out.println("inside DAO");	
			Query q = getSession().createQuery("from User where email = :eid");
			q.setString("eid", u.getEmail());
			User user = (User) q.uniqueResult();
			user.getEvents().add(e);
			getSession().saveOrUpdate(user);
            commit();
            return user;
		}catch (HibernateException ehib) {
            rollback();
            ehib.printStackTrace();
        }
		
		return null;
	}
	
	public User updateUserPrefernce(User u, Set up){
		try{
			begin();
			Query q = getSession().createQuery("from User where email = :eid");
			q.setString("eid", u.getEmail());
			User user = (User) q.uniqueResult();
//			System.out.println(up);
//			List<String> list = new ArrayList<String>(Arrays.asList(up.split(" , ")));
			
//			Preference p = new Preference();
//			System.out.println(list.size());
			
//			String pList[]=StringUtils.split(up, ",");
//			
//			System.out.println(pList.length);
//			for(String temp: list){
//			p.setPreferenceCategory(temp);
//			}
//			p.setPerson(user);
			//p.setEventCategoryId(up);
			
			q = getSession().createQuery("from Person where personID = :p_id");
			q.setInteger("p_id", user.getPersonID());
			Person per = (Person) q.uniqueResult();
			
			for(Iterator<Preference> it = up.iterator(); it.hasNext(); ){
				Preference p = it.next();
				p.setPerson(per);

				getSession().save(p);
			}
			
			//user.getPreference().addAll(up);
			//user.setPreference(new HashSet<Preference>());
			//user.getPreference().addAll(up);
			
			//getSession().saveOrUpdate(user);
            commit();
            return user;
		}catch (HibernateException ehib) {
            rollback();
            ehib.printStackTrace();
        }
		
		return null;
	}
	
	public User getUserEventHistory(User u){
		try{
			begin();
			Query q = getSession().createQuery("from User where email = :eid");
			q.setString("eid", u.getEmail());
			User user = (User) q.uniqueResult();
            commit();
            return user;
		}catch (HibernateException ehib) {
            rollback();
            ehib.printStackTrace();
        }
		
		return null;
	}
	
	
}
