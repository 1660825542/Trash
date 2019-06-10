package com.icss.oa.card.service;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.card.dao.AddressListDao;


@Service
@Transactional(rollbackFor = Exception.class)
public class AddressListService {

	@Autowired
	private AddressListDao dao;
	
	public Map<String,Object> queryById(Integer empId) {

		return dao.queryById(empId);
	}

	public List <Map<String,Object>> query(Pager pager) {

		return dao.query(pager);
	}


	public List<Map<String,Object>> queryByCondition(Pager pager, String empName) {

		return dao.queryByCondition(pager, empName);
	}

	public int getCount() {

		return dao.getCount();
	}
	
	public int getConditionCount(String empName) {

		return dao.getConditionCount(empName);
	}
	
	public void exportExcel(OutputStream os,String empName) throws IOException {

		Pager pager = new Pager(dao.getConditionCount(empName), 1, dao.getConditionCount(empName));
		List<Map<String,Object>> list = dao.queryByCondition(pager, empName);

		String[] titles = { "Ա����","Ա������","��������","��ϵ��ʽ" };

		// ��������������
		HSSFWorkbook wb = new HSSFWorkbook();

		// �������������
		HSSFSheet sheet1 = wb.createSheet("ͨѶ¼");

		// ����������
		HSSFRow rowTitle = sheet1.createRow(0);
		
		for (int i = 0;i < titles.length;i ++) {
			rowTitle.createCell(i).setCellValue(titles[i]);
		}
		
		//����������
		for (int i = 0;i < list.size();i ++) {
			HSSFRow row = sheet1.createRow(i + 1);
			row.createCell(0).setCellValue(((BigDecimal)list.get(i).get("EMP_ID")).intValue());
			row.createCell(1).setCellValue((String)list.get(i).get("EMP_NAME"));
			row.createCell(2).setCellValue((String)list.get(i).get("DEPT_NAME"));
			row.createCell(3).setCellValue((String)list.get(i).get("EMP_PHONE"));
		}
		
		wb.write(os);
		os.close();

	}


}
