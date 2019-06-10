package com.song.service.Impl;



import java.util.List;
import java.util.Map;

import com.song.dao.IGoodDao;

import com.song.dao.Impl.IGoodDaoImpl;
import com.song.entities.AttrBeen;
import com.song.entities.GoodBeen;
import com.song.service.IGoodService;

public class IGoodServiceImpl implements IGoodService{
	
	
	IGoodDao igooddao = null ;
	public IGoodServiceImpl(){
		super();
		igooddao = new IGoodDaoImpl();
		
	}
	
	public int insertGood(GoodBeen good) {
		// TODO Auto-generated method stub
		return igooddao.insertGood(good);
	}

	@Override
	public int updateGood(String number, String picture) {
		// TODO Auto-generated method stub
		return igooddao.updateGood(number,picture);
	}

	@Override
	public int deleteGood(String number) {
		// TODO Auto-generated method stub
		return igooddao.deleteGood(number);
	}

	@Override
	public GoodBeen selectGood(String number) {
		// TODO Auto-generated method stub
		return igooddao.selectGood(number);
	}

	@Override
	public int updateGood(GoodBeen good) {
		// TODO Auto-generated method stub
		return igooddao.updateGood(good);
	}

	@Override
	public List<GoodBeen> selectAllGood() {
		// TODO Auto-generated method stub
		return igooddao.selectAllGood();
	}

	@Override
	public Map<Integer, List<GoodBeen>> selectAll() {
		// TODO Auto-generated method stub
		return igooddao.selectAll();
	}

	@Override
	public List<AttrBeen> selectall() {
		// TODO Auto-generated method stub
		return igooddao.selectall();
	}

	@Override
	public List<GoodBeen> selectlike(String name) {
		// TODO Auto-generated method stub
		return igooddao.selectlike(name);
	}

}
