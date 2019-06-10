package com.icss.oa.assign.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.assign.dao.EmpComDao;
import com.icss.oa.assign.pojo.EmpCom;
import com.icss.oa.assign.dao.AssignEmpDao;
import com.icss.oa.assign.pojo.AssignEmp;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmpComService {

	@Autowired
	private EmpComDao dao;
	
	@Autowired
	private AssignEmpDao empdao;

	// 查询不使用事务
	@Transactional(readOnly = true)
	public List<EmpCom> query() {
		return dao.query();
	}

	@Transactional(readOnly = true)
	public List<AssignEmp> queryEmp() {
		return empdao.query();
	}
	
	public void updateByEmpCom(AssignEmp ase) throws IOException {	
		empdao.updateByEmpCom(ase);
	}
	
	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}

	public void insertByEmpCom(EmpCom ase,AssignEmp asp) throws IOException {	
		dao.insert(ase);
		
		int assid = ase.getAssId();
		
		asp.setAssId(assid);
		
		empdao.updateByEmpCom(asp);
	}
	
	public void updateByEmpCom(EmpCom ase,AssignEmp asp) throws IOException {	
		dao.update(ase);
		
		int assid = ase.getAssId();
		
		asp.setAssId(assid);
		
		empdao.updateByEmpCom(asp);
	}
	
	public void insert(EmpCom ase) throws IOException {	
		dao.insert(ase);
	}
	
	public void delete(Integer empComId) throws IOException {
		dao.delete(empComId);
	}
	
	public void update(EmpCom ase) throws IOException {
		dao.update(ase);
	}
	
	//根据id查询
	public EmpCom queryById(Integer empComId) {
		return dao.queryById(empComId);
	}

	//分页查询
	@Transactional(readOnly = true)
	public List<EmpCom> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}
	
	@Transactional(readOnly = true)
	public List<EmpCom> queryAll() {	
		HashMap<String, Integer> map = new HashMap<String, Integer>();		
		return dao.exportExcel(map);
	}
	
	//输出EXCEL文件
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream os) throws IOException {
		
		//获得员工派遣的实体类
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//工作簿对象（对应一个excel文件）
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//工作表对象
		HSSFSheet sheet1 = workbook.createSheet("员工外派关系记录");
		
		//设置工作表的列宽
		sheet1.setDefaultColumnWidth(15);
		
		//设置行数
		HSSFRow row = sheet1.createRow(0);

		//行对象,0代表第一行
		HSSFRow row1 = sheet1.createRow(0);
		
		//单元格对象,0代表第一个单元格
		HSSFCell cell1 = row1.createCell(0);
		//单元格赋值
		cell1.setCellValue("外派编号");
		
		HSSFCell cell2 = row1.createCell(1);
		cell2.setCellValue("员工编号");
		
		HSSFCell cell3 = row1.createCell(2);
		cell3.setCellValue("员工姓名");
		
		HSSFCell cell4 = row1.createCell(3);
		cell4.setCellValue("公司编号");
		
		HSSFCell cell5 = row1.createCell(4);
		cell5.setCellValue("公司名称");
		
				
		//获得总记录条数
		int recordCount = dao.getCount();
		System.out.println("总共有"+recordCount+"条员工派遣纪录");
		
		//查询得到全部的员工派遣记录
		List<EmpCom> exc = dao.exportExcel(map);
		
		//循环输出员工派遣表的内容
		int i;
		for(i=0;i<exc.size();i++){
			row = sheet1.createRow(i+1);
			row.createCell(0).setCellValue(exc.get(i).getEmpComId());
			row.createCell(1).setCellValue(exc.get(i).getAssId());
			row.createCell(2).setCellValue(exc.get(i).getAssName());
			row.createCell(3).setCellValue(exc.get(i).getAssComId());
			row.createCell(4).setCellValue(exc.get(i).getComName());
			
		}
		
		//把数据写入到输出流
		workbook.write(os);
		
		os.close();
		
	}

}