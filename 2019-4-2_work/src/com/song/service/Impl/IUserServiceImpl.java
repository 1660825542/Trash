package com.song.service.Impl;

import com.song.dao.IUserDao;
import com.song.dao.Impl.IUserDaoImpl;

import com.song.entities.SupUserBeen;
import com.song.entities.UserBeen;
import com.song.service.IUserService;

public class IUserServiceImpl implements IUserService{
	
	IUserDao iuserdao = null ;
	
	public IUserServiceImpl(){
		iuserdao = new IUserDaoImpl();
	}
	public UserBeen loginVal(String phonenum, String password) {
		// TODO Auto-generated method stub
		return iuserdao.loginVal(phonenum,password);
	}
	@Override
	public int insertUser(UserBeen user) {
		// TODO Auto-generated method stub
		return iuserdao.insertUser(user);
	}
	@Override
	public SupUserBeen supLoginVal(String rootId, String password) {
		// TODO Auto-generated method stub
		return iuserdao.supLoginVal(rootId,password);
	}
	@Override
	public UserBeen userProving(String phonenum) {
		// TODO Auto-generated method stub
		return iuserdao.userProving(phonenum);
	}
	@Override
	public UserBeen lostPassword(String phonenum) {
		// TODO Auto-generated method stub
		return iuserdao.lostPassword(phonenum);
	}
	@Override
	public UserBeen rePassword(String phonenum, String answer) {
		// TODO Auto-generated method stub
		return iuserdao.rePassword(phonenum,answer);
	}
	
	
	
}
