package operation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.*;

import shili.Goods;
import shili.Shopping;
import shili.User;

public class Goods_Operation {
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

	public List find() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List result;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Goods");
			result = query.list();// 查询放入list
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	public Goods findById(Long id) {// 通过商品id查询

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Goods result;
		try {
			tx = session.beginTransaction();
			result = (Goods) session.get(Goods.class, id);
			Hibernate.initialize(result.getShopping());
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	public void delete(String number) {// 通过商品编号删除

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Goods goods = null;
		try {
			tx = session.beginTransaction();
			goods = (Goods) session.createQuery("from Goods as g where g.goods_number=" + number).setMaxResults(1)
					.uniqueResult();
			session.delete(goods);
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

	public Goods find1(String number) {// 通过商品编号删除

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Goods goods = null;
		try {
			tx = session.beginTransaction();
			goods = (Goods) session.createQuery("from Goods as g where g.goods_number=" + number).setMaxResults(1)
					.uniqueResult();
			
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return goods;
	}
	public void save(Goods goods)// 将商品插入数据库
	{

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(goods);
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
	public List Search(String name) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List result;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Goods g where g.name like '%"+name+"%'");
			result = query.list();// 查询放入list
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
}
