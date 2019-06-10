package com.icss.oa.folder.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.folder.dao.FilesDao;
import com.icss.oa.folder.dao.FolderDao;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;

@Service
@Transactional(rollbackFor=Exception.class)
public class FolderFileService {

	@Autowired
	FolderDao folderDao;
	
	@Autowired
	FilesDao fileDao;
	
	public int addFolder(Folder folder){
		folderDao.insertFolder(folder);
		return folderDao.getPrimaryKey();
	}
	
	public void editFolder(Folder folder){
		if(folderDao.selectById(folder.getFolderId()).getFolderShare()!=folder.getFolderShare()){
			List<Folder> list = folderDao.getChild2(folder.getFolderId());
			for (Folder folder2 : list) {
				folder2.setFolderShare(folder.getFolderShare());
				folderDao.updateShare(folder2);
			}
		}
		folderDao.updateFolder(folder);
	}
	
	/**
	 * 删除文件夹
	 * @param folderId
	 */
	public int deleteFolder(Integer folderId){
		//获得被删除的文件夹
		Folder folder = folderDao.selectById(folderId);
		int parentId = folder.getFolderParent();
		//得到其父文件夹
		Folder parentFolder = folderDao.selectById(parentId);
		//修改所有父文件夹大小
		while(parentFolder.getCanDelete()!=0){
			parentFolder.setFolderSize(parentFolder.getFolderSize()-folder.getFolderSize());
			folderDao.updateSize(parentFolder);
			parentFolder = folderDao.selectById(parentFolder.getFolderParent());
		}
		//修改根文件夹剩余空间
		parentFolder.setFolderLeftSize(parentFolder.getFolderLeftSize()+folder.getFolderSize());
		folderDao.updateLeftSize(parentFolder);
		//删除该文件夹
		folderDao.deleteFolder(folderId);
		return parentId;
	}
	
	public List<Folder> allFolders(Integer empId) {
		return folderDao.selectByEmp(empId);
	}
	
	public List<Folder> getShareFolders(Integer empId){
		return folderDao.getEmpShareFolder(empId);
	}
	
	public Folder selectFolderById(Integer folderId){
		return folderDao.selectById(folderId);
	}
	
	public void setShare(Folder folder){
		folderDao.updateShare(folder);
		List<Folder> list = folderDao.getChild2(folder.getFolderId());

		for (Folder folder2 : list) {
			folder2.setFolderShare(folder.getFolderShare());
			folderDao.updateShare(folder2);
		}
	}
	
	public boolean addFile(Files file){
		Folder folder = folderDao.selectById(file.getFileFolder());
		Folder rootFolder = folderDao.getRootFolder(folder.getEmpId());
		if(rootFolder.getFolderLeftSize()<file.getFileSize()){
			return false;
		} else {
			fileDao.insertFile(file);
			
			while(folder.getCanDelete()!=0){
				folder.setFolderSize(folder.getFolderSize()+file.getFileSize());
				folderDao.updateSize(folder);
				folder = folderDao.selectById(folder.getFolderParent());
			}
			
			folder.setFolderLeftSize(folder.getFolderLeftSize()-file.getFileSize());
			folderDao.updateLeftSize(folder);
			return true;
		}
	}
	
	public void deleteFile(Integer fileId){
		Files file = fileDao.selectByPrimaryKey(fileId);
		Folder folder = folderDao.selectById(file.getFileFolder());
		while(folder.getCanDelete()!=0){
			folder.setFolderSize(folder.getFolderSize()-file.getFileSize());
			folderDao.updateSize(folder);
			folder = folderDao.selectById(folder.getFolderParent());
		}
		folder.setFolderLeftSize(folder.getFolderLeftSize()+file.getFileSize());
		folderDao.updateLeftSize(folder);
		fileDao.deleteFile(fileId);
	}
	
	public void updateFile(Files files){
		fileDao.updateFile(files);
	}
	
	public Files selectFile(Integer fileId){
		return fileDao.selectByPrimaryKey(fileId);
	}
	
	public List<Files> selectFolderFile(Integer fileFolder){
		return fileDao.selectByFolder(fileFolder);
	}
	
	/**
	 * 选择单层子文件夹
	 * @param folderId
	 * @return
	 */
	public List<Folder> selectChildFolderNotRecursion(Integer folderId){
		return folderDao.getChild(folderId);
	}
	
	/**
	 * 递归选择所有子文件夹
	 * @param folderId
	 * @return
	 */
	public List<Folder> selectChildFolderRescursion(Integer folderId){
		return folderDao.getChild2(folderId);
	}
	
	public List<Folder> selectShareChild(Integer folderId){
		return folderDao.getShareChild(folderId);
	}
	
	public Folder getEmpRootFolder(Integer empId){
		return folderDao.getRootFolder(empId);
	}
	
	public List<Files> getEmpFiles(Integer empId){
		List<Files> allFile = new ArrayList<Files>();
		List<Folder> folders = folderDao.selectByEmp(empId);
		
		for (Folder folder : folders) {
			List<Files> folderFile = fileDao.selectByFolder(folder.getFolderId());
			allFile.addAll(folderFile);
		}
		
		return allFile;
	}
}
