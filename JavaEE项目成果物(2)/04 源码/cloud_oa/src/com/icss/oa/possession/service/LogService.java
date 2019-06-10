package com.icss.oa.possession.service;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Date;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.possession.dao.HouseDao;
import com.icss.oa.possession.dao.LogDao;
import com.icss.oa.possession.pojo.House;
import com.icss.oa.possession.pojo.Log;
@Service
@Transactional(rollbackFor = Exception.class)
public class LogService {
	@Autowired
	private LogDao dao;
	
	@Autowired
	private HouseDao houseDao;
	public void insert(Log log) {
		House house = houseDao.queryById(log.getPossId());
		house.setPossUse(house.getPossUse()+log.getBorNum());
		house.setPossUnuse(house.getPossUnuse()-log.getBorNum());
		houseDao.update(house);
		dao.insert(log);
		
	}

	public void update(Log log) {
		dao.update(log);
	}

	public void delete(Integer logId) {
		dao.delete(logId);
	}

	public Log queryById(Integer logId) {
		return dao.queryById(logId);
	}

	public List<Log> query(Pager pager) {
		return dao.query(pager);
	}

	public int getCount() {
		return dao.getCount();
	}
	@Transactional(readOnly = true)
	public List<Log> queryAll() {	
		return dao.exportExcel();
	}
	
	//输出EXCEL文件
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream os) throws IOException {
		
		//工作簿对象（对应一个excel文件）
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//工作表对象
		HSSFSheet sheet1 = workbook.createSheet("办公用品消耗记录");
		
		//设置工作表的列宽
		sheet1.setDefaultColumnWidth(20);
		
		//设置行数
		HSSFRow row = sheet1.createRow(0);

		//行对象,0代表第一行
		HSSFRow row1 = sheet1.createRow(0);
		
		//单元格对象,0代表第一个单元格
		HSSFCell cell1 = row1.createCell(0);
		//单元格赋值
		cell1.setCellValue("记录编号");
		
		HSSFCell cell2 = row1.createCell(1);
		cell2.setCellValue("资产编号");
		
		HSSFCell cell3 = row1.createCell(2);
		cell3.setCellValue("资产名称");
		
		HSSFCell cell4 = row1.createCell(3);
		cell4.setCellValue("资产类别");
		
		HSSFCell cell5 = row1.createCell(4);
		cell5.setCellValue("员工编号");
		
		HSSFCell cell6 = row1.createCell(5);
		cell6.setCellValue("员工姓名");
		
		HSSFCell cell7 = row1.createCell(6);
		cell7.setCellValue("消耗日期");
		
		HSSFCell cell8 = row1.createCell(7);
		cell8.setCellValue("消耗数量");
		
		HSSFCell cell9 = row1.createCell(8);
		cell9.setCellValue("详述");
				
	
		
		//查询得到全部的员工派遣记录
		List<Log> exc = dao.exportExcel();
		//时间转码
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//循环输出员工派遣表的内容
		int i;
		for(i=0;i<exc.size();i++){
			row = sheet1.createRow(i+1);
			row.createCell(0).setCellValue(i+1);
			row.createCell(1).setCellValue(exc.get(i).getPossId());
			row.createCell(2).setCellValue(exc.get(i).getPossName());
			row.createCell(3).setCellValue(exc.get(i).getPossCate());
			row.createCell(4).setCellValue(exc.get(i).getEmpId());
			row.createCell(5).setCellValue(exc.get(i).getEmpName());
			//util.Date转string
			Date borDate = exc.get(i).getBorDate();
			
			String st = sdf.format(borDate);
			
			
			row.createCell(6).setCellValue(st);
			row.createCell(7).setCellValue(exc.get(i).getBorNum());
			row.createCell(8).setCellValue(exc.get(i).getBorDes());
			
		}
		
		//把数据写入到输出流
		workbook.write(os);
		
		os.close();
	}
	
}

		
