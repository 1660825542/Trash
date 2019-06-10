package operation;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.*;

import shili.Goods;
import shili.User;

public class User_Operation {
	private Long id;
	public static SessionFactory sessionFactory;
	static {
		try {
			Configuration config = new Configuration().configure();
			sessionFactory = config.buildSessionFactory();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Long getID() {
		return id;
	}

	public User findById(Long id) {// ͨ���û�id��ȡ���ﳵ

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User result = null;
		try {
			tx = session.beginTransaction();
			result = (User) session.get(User.class, id);
			Hibernate.initialize(result.getShopping());
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	public User findUser_Id(Long id) {// ͨ���û�id��ȡ�û���Ϣ

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	public boolean registerFind(String phone) {// ע��ͨ��phone��ѯ

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.createQuery("from User as u where u.phone=" + phone).setMaxResults(1).uniqueResult();
			id = user.getId();
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (user == null)
			return true;
		else
			return false;
	}
	public boolean loginFind(String phone,String password) {// ��½ͨ��phone��ѯ

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.createQuery("from User as u where u.phone='" + phone+"' and u.password= '"+password+"'").setMaxResults(1).uniqueResult();
			id = user.getId();
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (user == null)
			return true;
		else
			return false;
	}

	public void save(User user)// ע��������ݿ�
	{

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public User initUser(Long user_id) {//��ʼ���Ñ�
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user=null;
		try {
			tx=session.beginTransaction();			
			user=(User)session.load(User.class, user_id);
			Hibernate.initialize(user.getOrders());
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

}
