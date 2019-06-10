package com.icss.oa.carapply.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.icss.oa.carapply.dao.CarRecordDao;
import com.icss.oa.carapply.dao.CarApplyDao;
import com.icss.oa.car.dao.CarDao;
import com.icss.oa.car.pojo.Car;
import com.icss.oa.carapply.pojo.CarRecord;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarRecordService {

	@Autowired
	private CarRecordDao dao;
	
	@Autowired
	private CarDao Cardao;
	
	@Autowired
	private CarApplyDao CarApplydao;

	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}

	public void insert(CarRecord ase) {
		
		try {
			int applyId = ase.getCarapplyId();
			
			
			int empId = CarApplydao.queryById(applyId).getEmpId();
			String empName = CarApplydao.queryById(applyId).getEmpName();
			String route = CarApplydao.queryById(applyId).getRoute();
			Date startTime = CarApplydao.queryById(applyId).getStartTime();
			Date endTime = CarApplydao.queryById(applyId).getEndTime();
					
			ase.setEmpId(empId);
			ase.setRoute(route);
			ase.setEmpName(empName);
			ase.setStartTime(startTime);
			ase.setEndTime(endTime);
			
			dao.insert(ase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	public void delete(Integer assId) throws IOException {
		dao.delete(assId);
	}
	
	public void update(CarRecord ase) throws IOException {
		dao.update(ase);
	}
	
	public CarRecord queryById(Integer assId) {
		return dao.queryById(assId);
	}

	@Transactional(readOnly = true)
	public List<CarRecord> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}

	@Transactional(readOnly = true)
	public List<Car> queryByTime(Date startTime,Date endTime) {
		
		HashMap<String, Date> map = new HashMap<String, Date>();
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);

		return Cardao.queryByTime(map);
	}
	
	//输出EXCEL文件
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream os) throws IOException {
		
		//获得员工派遣的实体类
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//工作簿对象（对应一个excel文件）
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//工作表对象
		HSSFSheet sheet1 = workbook.createSheet("车辆安排记录");
		
		//设置工作表的列宽
		sheet1.setDefaultColumnWidth(15);
		
		//设置行数
		HSSFRow row = sheet1.createRow(0);

		//行对象,0代表第一行
		HSSFRow row1 = sheet1.createRow(0);
		
		//单元格对象,0代表第一个单元格
		HSSFCell cell1 = row1.createCell(0);
		//单元格赋值
		cell1.setCellValue("员工编号");
		
		HSSFCell cell2 = row1.createCell(1);
		cell2.setCellValue("员工姓名");
		
		HSSFCell cell3 = row1.createCell(2);
		cell3.setCellValue("车辆编号");
		
		HSSFCell cell4 = row1.createCell(3);
		cell4.setCellValue("路线");
		
		HSSFCell cell5 = row1.createCell(4);
		cell5.setCellValue("开始时间");
		
		HSSFCell cell6 = row1.createCell(5);
		cell6.setCellValue("结束时间");
				
		//获得总记录条数
		int recordCount = dao.getCount();
		System.out.println("总共有"+recordCount+"条员工派遣纪录");
		
		//查询得到全部的员工派遣记录
		List<CarRecord> exc = dao.exportExcel(map);
		
		//时间转码
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//循环输出员工派遣表的内容
		int i;
		for(i=0;i<exc.size();i++){
			row = sheet1.createRow(i+1);
			row.createCell(0).setCellValue(exc.get(i).getEmpId());
			row.createCell(1).setCellValue(exc.get(i).getEmpName());
			row.createCell(2).setCellValue(exc.get(i).getCarId());
			row.createCell(3).setCellValue(exc.get(i).getRoute());
			
			//util.Date转string
			Date starttime = exc.get(i).getStartTime();
			Date endtime = exc.get(i).getEndTime();
			String st = sdf.format(starttime);
			String et = sdf.format(endtime);
			
			row.createCell(4).setCellValue(st);
			row.createCell(5).setCellValue(et);
		}
		
		//把数据写入到输出流
		workbook.write(os);
		
		os.close();
		
	}
	
}