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
			u.setStatus("user");
			getSession().save(u);
            commit();
			
	        return u;
		}catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
		return null;
	}
	
	public User createAdminUser(User u){
		try{
			begin();
			u.setStatus("admin");
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
			
			q = getSession().createQuery("from Person where personID = :p_id");
			q.setInteger("p_id", user.getPersonID());
			Person per = (Person) q.uniqueResult();
			
			q = getSession().createQuery("delete Preference p where p.person.personID = :pid");
			q.setInteger("pid", per.getPersonID());
			q.executeUpdate();
			
			for(Iterator<Preference> it = up.iterator(); it.hasNext(); ){
				Preference p = it.next();
				p.setPerson(per);

				getSession().save(p);
			}
			
			commit();
            return user;
		}catch (HibernateException ehib) {
            rollback();
            ehib.printStackTrace();
        }
		
		return null;
	}
	
	public List<Preference> getUserPrefernce(User u){
		try{
			begin();
			Query q = getSession().createQuery("from User where email = :eid");
			q.setString("eid", u.getEmail());
			User user = (User) q.uniqueResult();
			
		    q = getSession().createQuery("from Preference p where p.person.personID = :pid)");
			q.setInteger("pid", user.getPersonID());
			List<Preference> p = q.list();
            commit();
            System.out.print("Insider - " + p.size());
            return p;
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
