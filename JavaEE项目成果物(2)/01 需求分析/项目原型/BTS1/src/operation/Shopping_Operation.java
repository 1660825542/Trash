package operation;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import shili.Goods;
import shili.Shopping;
import shili.User;

public class Shopping_Operation {
	 public static SessionFactory sessionFactory;
	  static{
	     try{
	       Configuration config = new Configuration().configure();
	       sessionFactory = config.buildSessionFactory(); 
	    }catch(RuntimeException e){e.printStackTrace();throw e;}
	  }
	  public void Save(Shopping shopping)
	  {

	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try {
	        tx = session.beginTransaction();
	        session.save(session.merge(shopping));//处理一个id存在两个对象
	        tx.commit();
	    }catch (RuntimeException e) {
	        if (tx != null) {
	          tx.rollback();
	        }
	        e.printStackTrace();
	      } finally {
	        session.close();
	      }
	  }
	  public List findShoppings(Long id){//通过用户ID查询购物车

		  Session session = sessionFactory.openSession();
		    Transaction tx = null;
		    List result=null;
		    try {
		        tx = session.beginTransaction();    
		        Query query= session.createQuery("from Shopping as s where s.user.id="+id);  
		        result=query.list();
		        tx.commit();
		  }catch (RuntimeException e) {
		      if (tx != null) {
		        tx.rollback();
		      }
		      e.printStackTrace();
		    } finally {
		      session.close();
		    }
		    return result;
		}
	  public Shopping find(Long id,Long id1){//通过用户ID及商品ID查询

		  Session session = sessionFactory.openSession();
		    Transaction tx = null;
		    Shopping result=null;
		    try {
		        tx = session.beginTransaction();    
		        Query query= session.createQuery("from Shopping as s where s.goods.id="+id +"and s.user.id="+id1);  
		        result=(Shopping)query.uniqueResult();
		        tx.commit();
		  }catch (RuntimeException e) {
		      if (tx != null) {
		        tx.rollback();
		      }
		      e.printStackTrace();
		    } finally {
		      session.close();
		    }
		    return result;
		}
	  public Shopping findById(Long id){//通过购物车ID查询购物车信息进行删除

		  Session session = sessionFactory.openSession();
		    Transaction tx = null;
		    Shopping result=null;
		    try {
		        tx = session.beginTransaction();    
		        Query query= session.createQuery("from Shopping as s where s.id="+id);  
		        result=(Shopping)query.uniqueResult();
		        tx.commit();
		  }catch (RuntimeException e) {
		      if (tx != null) {
		        tx.rollback();
		      }
		      e.printStackTrace();
		    } finally {
		      session.close();
		    }
		    return result;
		  }
	  public void deleteShopping(Shopping shopping) {
		  Session session = sessionFactory.openSession();
		    Transaction tx = null;
		    try {
		        tx = session.beginTransaction();    
		        session.delete(shopping);
		        tx.commit();
		  }catch (RuntimeException e) {
		      if (tx != null) {
		        tx.rollback();
		      }
		      e.printStackTrace();
		    } finally {
		      session.close();
		    }
		  }
}
