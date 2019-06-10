package com.icss.oa.folder.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.folder.pojo.Folder;

@Repository
public class FolderDao {
	
	@Autowired
	private SqlSessionFactory factory;
	
	public void insertFolder(Folder folder){
		SqlSession session = factory.openSession();
		session.insert("FOLDER.insert", folder);
	}
	
	public void deleteFolder(Integer folderId){
		SqlSession session = factory.openSession();
		session.delete("FOLDER.deleteByPrimaryKey",folderId);
	}
	
	public List<Folder> selectByEmp(Integer empId){
		SqlSession session = factory.openSession();
		List<Folder> list = session.selectList("FOLDER.selectByEmp", empId);
		return list;
	}
	
	public Folder selectById(Integer folderId){
		SqlSession session = factory.openSession();
		Folder folder = session.selectOne("FOLDER.selectByPrimaryKey", folderId);
		return folder;
	}
	
	public void updateFolder(Folder folder){
		SqlSession session = factory.openSession();
		session.update("FOLDER.updateByPrimaryKey", folder);
	}
	
	public void updateShare(Folder folder){
		SqlSession session = factory.openSession();
		session.update("FOLDER.updateShare", folder);
	}
	
	public void updateSize(Folder folder){
		SqlSession session = factory.openSession();
		session.update("FOLDER.updateSize", folder);
	}
	
	public void updateLeftSize(Folder folder){
		SqlSession session = factory.openSession();
		session.update("FOLDER.updateLeftSize", folder);
	}
	
	public Folder getRootFolder(Integer empId){
		SqlSession session = factory.openSession();
		Folder folder = session.selectOne("FOLDER.getRootFolder", empId);
		return folder;
	}
	
	public List<Folder> getEmpShareFolder(Integer empId){
		SqlSession session = factory.openSession();
		List<Folder> list = session.selectList("FOLDER.getEmpShareFolder", empId);
		return list;
	}
	
	/**
	 * 得到目录子文件夹，仅仅得到一层的子目录
	 * @param folderParent
	 * @return
	 */
	public List<Folder> getChild(Integer folderParent){
		SqlSession session = factory.openSession();
		List<Folder> list = session.selectList("FOLDER.getChildFolder", folderParent);
		return list;
	}
	
	/**
	 * 得到目录所有子文件夹极其所有子文件夹
	 * @param folderParent
	 * @return
	 */
	public List<Folder> getChild2(Integer folderParent){
		SqlSession session = factory.openSession();
		
		List<Folder> returnList = session.selectList("FOLDER.getChildFolder", folderParent);
		List<Folder> currentList = new ArrayList<Folder>(returnList);
		while(!currentList.isEmpty()){
			List<Folder> addList = new ArrayList<Folder>();
			for (Folder folder : currentList) {
				List<Folder> list = session.selectList("FOLDER.getChildFolder", folder.getFolderId());
				returnList.addAll(list);
				addList.addAll(list);
			}
			currentList.clear();
			currentList.addAll(addList);
		}
		return returnList;
	}
	
	/**
	 * 获取共享的子目录
	 * @param folderParent
	 * @return
	 */
	public List<Folder> getShareChild(Integer folderParent){
		SqlSession session = factory.openSession();
		List<Folder> list = session.selectList("FOLDER.getShareChildFolder", folderParent);
		return list;
	}
	
	public int getPrimaryKey(){
		SqlSession session = factory.openSession();
		int pk = session.selectOne("FOLDER.getPrimaryKey");
		return pk;
	}
}
