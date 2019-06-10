package com.song.service;



import java.util.List;

import com.song.entities.GoodBeen;


public interface IGoodService {

	int insertGood(GoodBeen good);

	int updateGood(String number, String picture);

	int deleteGood(String number);

	GoodBeen selectGood(String number);

	int updateGood(GoodBeen good);

	List<GoodBeen> selectAllGood();

	

}
