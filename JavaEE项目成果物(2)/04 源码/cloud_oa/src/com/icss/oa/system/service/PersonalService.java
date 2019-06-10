package com.icss.oa.system.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.index.IndexDao;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersonalService {
	
	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private IndexDao indexDao;
	
	public void update(Employee emp) throws IOException {
		dao.update(emp);
		
		// ¸üÐÂË÷Òý
		Document document = new Document();
		Term term = new Term("empId",String.valueOf(emp.getEmpId()));
		document.add(new TextField("empId", String.valueOf(emp.getEmpId()), Store.YES));
		document.add(new TextField("empName", emp.getEmpName(), Store.YES));
		document.add(new TextField("deptId",String.valueOf(emp.getDepartmentId()),Store.YES));
		document.add(new TextField("deptName", dao.getDeptNameByEmp(emp.getEmpId()), Store.YES));
		indexDao.update(term, document);
	}
	
	public String forgetPwd(){
		
		List<Employee> emp = dao.queryByRole(1);
		String phone = null;
		for(Employee e : emp){
			phone = phone + e.getPhone() + "/n";
		}
		return phone;	
	}
	
	public void changePwd(int empId, String pwd) {
		dao.changePwd(empId, pwd);
	}
	
	public Employee queryById(int empId) {
		// TODO Auto-generated method stub
		return dao.queryById(empId);
	}
	
	
}
