package com.song.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rock.util.JDBCUtil;
import com.song.dao.IGoodDao;
import com.song.entities.AttrBeen;
import com.song.entities.GoodBeen;


public class IGoodDaoImpl implements IGoodDao{
	
	private  JDBCUtil util=null;
	public IGoodDaoImpl(){
		super();
		util = new JDBCUtil();
	}
	@Override
	public int insertGood(GoodBeen good) {
		// TODO Auto-generated method stub
		String sql = "insert into goods(goodid,name,price,picture,stock,attri) value(?,?,?,?,?,?)";
		return util.runSql(sql, good.getGoodid(),good.getName(),good.getPrice(),good.getGoodid(),good.getStock(),good.getAttri());
	}
	@Override
	public int updateGood(String number, String picture) {
		// TODO Auto-generated method stub
		String sql = "update goods set picture = ? where goodid = ?";
		return util.runSql(sql, picture, number);
	}
	public int deleteGood(String number) {
		// TODO Auto-generated method stub
		String sql = "delete from goods where goodid= ?";
		return util.runSql(sql, number);
	}
	@Override
	public GoodBeen selectGood(String number) {
		// TODO Auto-generated method stub
		String sql = "select * from goods where goodid = ?";
		return util.queryById(GoodBeen.class, sql, number);
	}
	@Override
	public int updateGood(GoodBeen good) {
		// TODO Auto-generated method stub
		String sql ="update goods set name = ?,price = ?,stock = ?,attri = ? where goodid = ?";
		return util.runSql(sql, good.getName(), good.getPrice(), good.getStock(), good.getAttri(), good.getGoodid());
	}
	@Override
	public List<GoodBeen> selectAllGood() {
		// TODO Auto-generated method stub
		String sql = "select * from goods";
		return util.queryAll(GoodBeen.class, sql);
	}
	@Override
	public Map<Integer, List<GoodBeen>> selectAll() {
			
	       Map<Integer,List<GoodBeen>> m=new HashMap<Integer ,List<GoodBeen>>();
	       for(int  i=1;i<6;i++)
	        {
	            String sql="SELECT  * FROM (SELECT * FROM goods WHERE  attri="+i+") f  LIMIT 0,6; ";
	            m.put(i,util.queryAll(GoodBeen.class,sql));

	        }
	        return m;
	}
	@Override
	public List<AttrBeen> selectall() {
		// TODO Auto-generated method stub
		String sql="select * from attr";
        return util.queryAll(AttrBeen.class, sql);
	}
	@Override
	public List<GoodBeen> selectlike(String name) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM goods WHERE NAME LIKE '%" + name + "%'";
		return util.queryAll(GoodBeen.class,sql);
	}
	

}
