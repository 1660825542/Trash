package com.icss.oa.folder.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.folder.pojo.Files;

@Repository
public class FilesDao {

	@Autowired
	private SqlSessionFactory factory;
	
	public void insertFile(Files file){
		SqlSession session = factory.openSession();
		session.insert("FILES.insert", file);
	}
	
	public void deleteFile(Integer fileId){
		SqlSession session = factory.openSession();
		session.delete("FILES.deleteByPrimaryKey",fileId);
	}
	
	public void updateFile(Files file){
		SqlSession session = factory.openSession();
		session.update("FILES.updateByPrimaryKey", file);
	}
	
	public Files selectByPrimaryKey(Integer fileId){
		SqlSession session = factory.openSession();
		Files file = session.selectOne("FILES.selectByPrimaryKey", fileId);
		return file;
	}
	
	public List<Files> selectByFolder(Integer fileFolder){
		SqlSession session = factory.openSession();
		List<Files> list = session.selectList("FILES.selectByFolder", fileFolder);
		return list;
	}
}
