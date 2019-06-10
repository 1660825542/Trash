package com.song.dao.Impl;

import java.util.List;

import com.rock.util.JDBCUtil;
import com.song.dao.IGoodDao;
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
		String sql = "insert into goods(number,name,price,picture,stock,attri) value(?,?,?,?,?,?)";
		return util.runSql(sql, good.getNumber(),good.getName(),good.getPrice(),good.getNumber(),good.getStock(),good.getAttri());
	}
	@Override
	public int updateGood(String number, String picture) {
		// TODO Auto-generated method stub
		String sql = "update goods set picture = ? where number = ?";
		return util.runSql(sql, picture, number);
	}
	public int deleteGood(String number) {
		// TODO Auto-generated method stub
		String sql = "delete from goods where number= ?";
		return util.runSql(sql, number);
	}
	@Override
	public GoodBeen selectGood(String number) {
		// TODO Auto-generated method stub
		String sql = "select * from goods where number = ?";
		return util.queryById(GoodBeen.class, sql, number);
	}
	@Override
	public int updateGood(GoodBeen good) {
		// TODO Auto-generated method stub
		String sql ="update goods set name = ?,price = ?,stock = ?,attri = ? where number = ?";
		return util.runSql(sql, good.getName(), good.getPrice(), good.getStock(), good.getAttri(), good.getNumber());
	}
	@Override
	public List<GoodBeen> selectAllGood() {
		// TODO Auto-generated method stub
		String sql = "select * from goods";
		return util.queryAll(GoodBeen.class, sql);
	}
	

}
