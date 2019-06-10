package com.song.dao.Impl;

import java.util.List;

import com.rock.util.JDBCUtil;
import com.song.dao.IQuestionDao;
import com.song.entities.QuestionBeen;

public class IQuestionDaoImpl implements IQuestionDao{
	
	private  JDBCUtil util=null;
	public IQuestionDaoImpl(){
		super();
		util = new JDBCUtil();
	}
	public List<QuestionBeen> selectQuestion() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM question";
		return util.queryAll(QuestionBeen.class, sql);
	}

}
