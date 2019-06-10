package operation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import shili.Order_Goods;


public class Order_Goods_Operation {
	public static org.hibernate.SessionFactory sessionFactory;
	static {
		Configuration config=new Configuration().configure();
		sessionFactory=config.buildSessionFactory();
	}
	public void saveOrder_good(Order_Goods order_goods) {
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(order_goods);
			tx.commit();
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	public List getOrder_Good_ByOrderId(Long order_id) {  //外键查询用户id   
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		List order_goods=null;
		try {
			tx=session.beginTransaction();
			order_goods=session.createQuery("from Order_Goods as og where og.order.id="+order_id).list();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return order_goods;
	}
}
