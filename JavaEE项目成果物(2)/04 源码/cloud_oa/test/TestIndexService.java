import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.system.index.IndexDao;
import com.icss.oa.system.service.EmployeeService;


public class TestIndexService {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("application*.xml");
		
		EmployeeService service = (EmployeeService) context.getBean("employeeService");
		IndexDao indexDao = (IndexDao) context.getBean("indexDao");
		
		List<Map<String,Object>> emp = service.queryEmployee(new Pager(service.count(),service.count(),1));
		
		for (Iterator iterator = emp.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			Document document = new Document();
			document.add(new TextField("empId", map.get("EMP_ID").toString(), Store.YES));
			document.add(new TextField("empName", (String) map.get("EMP_NAME"), Store.YES));
			document.add(new TextField("deptId", (String) map.get("DEPARTMENT_ID").toString(), Store.YES));
			document.add(new TextField("deptName", (String) map.get("DEPT_NAME"), Store.YES));
			
			indexDao.create(document);
			System.out.println("索引"+map.toString()+"以创建");
			
		}
		
		
	}

}
