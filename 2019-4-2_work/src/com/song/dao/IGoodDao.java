package com.song.dao;

import java.util.List;
import java.util.Map;

import com.song.entities.AttrBeen;
import com.song.entities.GoodBeen;


public interface IGoodDao {

	int insertGood(GoodBeen good);

	int updateGood(String number, String picture);

	int deleteGood(String number);

	GoodBeen selectGood(String number);

	int updateGood(GoodBeen good);

	List<GoodBeen> selectAllGood();

	Map<Integer, List<GoodBeen>> selectAll();

	List<AttrBeen> selectall();

	List<GoodBeen> selectlike(String name);

}
