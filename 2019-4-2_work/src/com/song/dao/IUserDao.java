package com.song.dao;


import com.song.entities.SupUserBeen;
import com.song.entities.UserBeen;

public interface IUserDao {

	UserBeen loginVal(String phonenum, String password);

	int insertUser(UserBeen user);

	SupUserBeen supLoginVal(String rootId, String password);

	UserBeen userProving(String phonenum);

	UserBeen lostPassword(String phonenum);

	UserBeen rePassword(String phonenum, String answer);

}
