import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.DepartmentDao;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.dao.PositionDao;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Position;


public class TestDao {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("application*.xml");

	
	@Test
	public void insertEmp(){
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");

		Employee emp1 = new Employee("ÁõºúÀ¼","em002","123456", 1,Date.valueOf("1995-1-1"),1,1,"110","abc@aaa.com","111111","hhah");
		dao.insert(emp1);
		dao.insert(emp1);
		dao.insert(emp1);
		dao.insert(emp1);
		dao.insert(emp1);
		dao.insert(emp1);
	}
	@Test
	public void testEmpDao() {
		
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");
		
		Employee emp2 = new Employee(3,"tom","em003","123456", 1,Date.valueOf("1995-1-1"),1,1,"110","abc@aaa.com","111111","hhah");

		
		Pager pager = new Pager(dao.getCount(), 8, 5);
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum() * pager.getPageSize());
		List<Map<String,Object>> list = dao.query(map);
		for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> employee = iterator.next();
			System.out.println(employee.get("EMP_ID")+","+employee.get("EMP_NAME"));
		}

		dao.update(emp2);
		dao.delete(10);
		System.out.println(dao.queryById(3).getEmpName());
		
		Pager pager2 = new Pager(dao.getConditionCount("ÁõºúÀ¼"), 50, 1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("start", pager2.getStart());
		map2.put("end", pager2.getPageNum() * pager2.getPageSize());
		map2.put("nameOrDept", "ÁõºúÀ¼");
		List<Map<String,Object>> list2 = dao.queryByCondition(map2);
		
		for (Iterator<Map<String, Object>> iterator = list2.iterator(); iterator.hasNext();) {
			Map<String, Object> employee = iterator.next();
			System.out.println(employee.get("EMP_ID")+","+employee.get("EMP_NAME"));
		}

	}
	
	@Test
	public void testDeptPosDao(){
		
		DepartmentDao deptDao = (DepartmentDao) context.getBean("departmentDao");
		PositionDao posDao = (PositionDao) context.getBean("positionDao");
		
		Department dept1 = new Department("¹þ¹þ²¿","hahahahhaha");
		Department dept2 = new Department(2,"À²À²²¿","llalala");
		
		Position pos1 = new Position("ÎûÎû","xixixixiixix");
		Position pos2 = new Position(2,"²»²»²»²»","aaaaaaxiixix");

		deptDao.insert(dept1);
		deptDao.insert(dept1);
		deptDao.insert(dept1);
		
		List<Department> list = deptDao.query();
		for (Iterator<Department> iterator = list.iterator(); iterator.hasNext();) {
			Department department = (Department) iterator.next();
			System.out.println(department.getDeptId()+","+department.getDeptName());
		}
		
		deptDao.update(dept2);
		deptDao.delete(7);
		System.out.println(deptDao.queryById(2).getDeptName());
		
		
		posDao.insert(pos1);
		posDao.insert(pos1);
		posDao.insert(pos1);

		List<Position> list2 = posDao.queryAll();
		for (Iterator<Position> iterator = list2.iterator(); iterator.hasNext();) {
			Position position = (Position) iterator.next();
			System.out.println(position.getPosId()+","+position.getPosName());
		}
		
		posDao.update(pos2);
		posDao.delete(3);
		System.out.println(posDao.queryById(2).getPosName());
	}
	@Test
	public void testQueryNameByEmp(){
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");
		System.out.println(dao.getDeptNameByEmp(1));
		List<Employee> list = dao.queryEmpByDept(2);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
				System.out.println(employee.getEmpId());
		}
	}

}
