package com.song.dao.Impl;

import com.rock.util.JDBCUtil;
import com.song.dao.IUserDao;
import com.song.entities.QuestionBeen;
import com.song.entities.SupUserBeen;
import com.song.entities.UserBeen;

public class IUserDaoImpl implements IUserDao{

	private  JDBCUtil util=null;
	QuestionBeen que = null;
	public IUserDaoImpl(){
		util = new JDBCUtil();
	}
	public UserBeen loginVal(String phonenum, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from user where phonenum = ? and password = ?";
		
		return util.queryById(UserBeen.class, sql, phonenum, password);
	}
	@Override
	public int insertUser(UserBeen user) {
		// TODO Auto-generated method stub
		String sql1 = "select * from question where question = ?";
		que = util.queryById(QuestionBeen.class, sql1, user.getQuestion());
		
		String sql2 = "insert into user (phonenum,password,name,address,questionid,answer) values (?,?,?,?,?,?)";
		
		return util.runSql(sql2, user.getPhonenum(), user.getPassword(), user.getName(), user.getAddress(), que.getQuestionid(), user.getAnswer());
		
		
	}
	@Override
	public SupUserBeen supLoginVal(String rootId, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from root where rootId = ? and password = ?";
		
		return util.queryById(SupUserBeen.class, sql, rootId, password);
	}
	@Override
	public UserBeen userProving(String phonenum) {
		// TODO Auto-generated method stub
		String sql = "select * from user where phonenum = ?";
		return util.queryById(UserBeen.class, sql, phonenum);
	}
	@Override
	public UserBeen lostPassword(String phonenum) {
		// TODO Auto-generated method stub
		String sql1 = "select * from user where phonenum = ?";
		UserBeen user = util.queryById(UserBeen.class,sql1,phonenum);
		if(user == null){
			return null;
		}
		else{
			String sql2 = "SELECT a.*,b.question FROM USER a,question b WHERE a.questionid=b.questionid AND a.phonenum= ?";
			return util.queryById(UserBeen.class,sql2,phonenum);
		}
	}
	@Override
	public UserBeen rePassword(String phonenum,String answer) {
		// TODO Auto-generated method stub
		String sql = "select * from user where phonenum = ? and answer = ?";
		return util.queryById(UserBeen.class, sql, phonenum, answer);
	}
	
	

}
