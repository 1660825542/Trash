package com.song.service.Impl;

import java.util.List;

import com.song.dao.IQuestionDao;
import com.song.dao.Impl.IQuestionDaoImpl;
import com.song.entities.QuestionBeen;
import com.song.service.IQuestionService;

public class IQuestionServiceImpl implements IQuestionService{
	
	IQuestionDao iquestion = null ;
	public IQuestionServiceImpl(){
		super();
		iquestion = new IQuestionDaoImpl();
		
	}
	public List<QuestionBeen> selectQuestion() {
		// TODO Auto-generated method stub
		return iquestion.selectQuestion();
	}
	
	
}
