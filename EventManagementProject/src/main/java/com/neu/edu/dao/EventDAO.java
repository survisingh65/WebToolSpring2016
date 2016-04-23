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

import com.neu.edu.pojo.*;

public class EventDAO extends DAO{

	public EventDAO(){
		
	}

	public AdminEvent createAdminEvent(AdminEvent ae){
		try{
			begin();
			getSession().save(ae);
            commit();
			
	        return ae;
		}catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
		return null;
	}
	
	public void deleteAdminEvent(String id){
		try{
			begin();
			Query q = getSession().createQuery("delete AdminEvent ae where ae.eventId =:i");
			q.setInteger("i", Integer.parseInt(id));
			q.executeUpdate();
            commit();
	        
			}catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
	}
	
	public List<AdminEvent> getAdminEvent(){
		try{
			begin();
			Query q = getSession().createQuery("from AdminEvent ae");
			List<AdminEvent> alist = q.list();
            commit();
	        return alist;
		}catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
		return null;
	}
}
