import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.folder.dao.FilesDao;
import com.icss.oa.folder.dao.FolderDao;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FolderFileService;


public class TestFileFolderDaoService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("application*.xml");

	FilesDao fileDao = (FilesDao) context.getBean("filesDao");
	FolderDao folderDao = (FolderDao) context.getBean("folderDao");
	
	FolderFileService service = (FolderFileService) context.getBean("folderFileService");
	
	@Test
	@Transactional(readOnly=true)
	public void TestGetChild(){
		
		List<Folder> folders = folderDao.getChild(0);
		for (Folder folder : folders) {
			System.out.println(folder.getFolderName());
		}
	}
	
	@Test
	public void TestSelectFileById(){
		Files file = fileDao.selectByPrimaryKey(14);
		System.out.println(file.getFileFolder());
	}
	
	@Test
	public void TestGetChild2(){
		
		List<Folder> folders = folderDao.getChild2(0);
		for (Folder folder : folders) {
			System.out.println(folder.getFolderName());
		}
	}
	
	@Test
	public void TestGetFileAndroid(){
		List<Files> files = service.getEmpFiles(94);
		for (Files files2 : files) {
			System.out.println(files2.getFileName());
		}
	}
}
