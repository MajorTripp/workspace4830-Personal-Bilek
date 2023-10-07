/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.TaskBilek;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBBilek {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<TaskBilek> listTasks() {
      List<TaskBilek> resultList = new ArrayList<TaskBilek>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> tasks = session.createQuery("FROM TaskBilek").list();
         for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
            TaskBilek task = (TaskBilek) iterator.next();
            resultList.add(task);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<TaskBilek> listTasks(String keyword) {
      List<TaskBilek> resultList = new ArrayList<TaskBilek>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((TaskBilek)session.get(TaskBilek.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM TaskBilek");
         List<?> tasks = session.createQuery("FROM TaskBilek").list();
         for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
            TaskBilek task = (TaskBilek) iterator.next();
            if (task.getAssignee().startsWith(keyword)) {
               resultList.add(task);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   public static void deleteTask(Integer id) {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	      try {
	    	 tx = session.beginTransaction();
	         TaskBilek task = new TaskBilek();
	         task.setId(id);
	         session.delete(task);
	         tx.commit();
	      } catch (HibernateException e) {
	    	 if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
   }

   public static void createTasks(String deadline, String description, String assignee) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new TaskBilek(deadline, description, assignee));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
