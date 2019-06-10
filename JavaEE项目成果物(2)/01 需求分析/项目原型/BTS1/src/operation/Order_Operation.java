package operation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.*;

import shili.User;
import shili.Order;
public class Order_Operation {
	public static SessionFactory sessionFactory;
	static {
		Configuration config = new Configuration().configure();
		sessionFactory = config.buildSessionFactory();
	}

	public void saveOrder(Order order) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/** ��������ļ�������λ�����+��ǰ������ʱ���� * * @return */
	public String getOrdernumber() {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");// ��ǰʱ��
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// ��ȡ5λ�����
		String number = rannum + str;
		return number;
	}
	public List getOrder_ByUserId(int first,int size,Long user_id) {  //�����ѯ�û�id   
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		List user_goods=null;
		try {
			tx=session.beginTransaction();
			user_goods=session.createQuery("from Order as o where o.user.id="+user_id).setFirstResult(first).setMaxResults(size).list();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return user_goods;
	}
	public int getOrder_Counts(Long user_id) {  //�����ѯ�û�id   
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		List user_goods=null;
		try {
			tx=session.beginTransaction();
			user_goods=session.createQuery("from Order as o where o.user.id="+user_id).list();
			tx.commit();
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return user_goods.size();
	}
}
