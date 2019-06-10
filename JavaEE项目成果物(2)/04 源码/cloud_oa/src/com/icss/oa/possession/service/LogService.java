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
	
	//���EXCEL�ļ�
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream os) throws IOException {
		
		//���������󣨶�Ӧһ��excel�ļ���
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//���������
		HSSFSheet sheet1 = workbook.createSheet("�칫��Ʒ���ļ�¼");
		
		//���ù�������п�
		sheet1.setDefaultColumnWidth(20);
		
		//��������
		HSSFRow row = sheet1.createRow(0);

		//�ж���,0�����һ��
		HSSFRow row1 = sheet1.createRow(0);
		
		//��Ԫ�����,0�����һ����Ԫ��
		HSSFCell cell1 = row1.createCell(0);
		//��Ԫ��ֵ
		cell1.setCellValue("��¼���");
		
		HSSFCell cell2 = row1.createCell(1);
		cell2.setCellValue("�ʲ����");
		
		HSSFCell cell3 = row1.createCell(2);
		cell3.setCellValue("�ʲ�����");
		
		HSSFCell cell4 = row1.createCell(3);
		cell4.setCellValue("�ʲ����");
		
		HSSFCell cell5 = row1.createCell(4);
		cell5.setCellValue("Ա�����");
		
		HSSFCell cell6 = row1.createCell(5);
		cell6.setCellValue("Ա������");
		
		HSSFCell cell7 = row1.createCell(6);
		cell7.setCellValue("��������");
		
		HSSFCell cell8 = row1.createCell(7);
		cell8.setCellValue("��������");
		
		HSSFCell cell9 = row1.createCell(8);
		cell9.setCellValue("����");
				
	
		
		//��ѯ�õ�ȫ����Ա����ǲ��¼
		List<Log> exc = dao.exportExcel();
		//ʱ��ת��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//ѭ�����Ա����ǲ�������
		int i;
		for(i=0;i<exc.size();i++){
			row = sheet1.createRow(i+1);
			row.createCell(0).setCellValue(i+1);
			row.createCell(1).setCellValue(exc.get(i).getPossId());
			row.createCell(2).setCellValue(exc.get(i).getPossName());
			row.createCell(3).setCellValue(exc.get(i).getPossCate());
			row.createCell(4).setCellValue(exc.get(i).getEmpId());
			row.createCell(5).setCellValue(exc.get(i).getEmpName());
			//util.Dateתstring
			Date borDate = exc.get(i).getBorDate();
			
			String st = sdf.format(borDate);
			
			
			row.createCell(6).setCellValue(st);
			row.createCell(7).setCellValue(exc.get(i).getBorNum());
			row.createCell(8).setCellValue(exc.get(i).getBorDes());
			
		}
		
		//������д�뵽�����
		workbook.write(os);
		
		os.close();
	}
	
}

		
