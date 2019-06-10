package com.icss.oa.card.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.card.dao.CardDao;
import com.icss.oa.card.pojo.Card;
import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeDao;


@Service
@Transactional(rollbackFor=Exception.class)
public class CardService {
	
	@Autowired
	private CardDao carddao;
	
	@Autowired
	private EmployeeDao empDao;
	
	public void insert(Card card){
		
		carddao.insert(card);
	}
	
	public void delete(Integer cardId){
		
		carddao.delete(cardId);
	}
	
	public void update(Card card){
		
		carddao.update(card);
	}
	
	public Card queryById(Integer cardId){
		
		Card card = carddao.queryById(cardId);
		return card;
	}
	
	public List<Card> query(Pager card,Integer empId) {

		return carddao.query(card,empId);
	}
	
	public int getCount(Integer cataId) {

		return carddao.getCount(cataId);
	}

	public List<Card> query1(Pager card) {
		return carddao.query1(card);
	}

	public List<Card> queryCatecard(Pager cardcategory,Integer cataId) {

		return carddao.queryCatecard(cardcategory, cataId);
	}

	public int getConditionCount(String cardName) {

		return carddao.getConditionCount(cardName);
	}

	public List<Card> querByCondition(Pager pager, String cardName) {

		return carddao.queryByCondition(pager, cardName);
	}

	/**
	 * 生成EXCEL文件
	 * 
	 * @param os
	 *            输出流
	 * @throws IOException 
	 */
	public void exportExcel(OutputStream os) throws IOException {

		Pager pager = new Pager(empDao.getCount(),empDao.getCount(),1 );
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String,Object>> list = empDao.query(map);

		String[] titles = { "员工编号", "员工姓名", "职务", "所属部门", "联系电话" };

		// 创建工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建工作表对象
		HSSFSheet sheet1 = wb.createSheet("通讯录数据");

		// 创建标题行
		HSSFRow rowTitle = sheet1.createRow(0);
		
		for (int i = 0;i < titles.length;i ++) {
			rowTitle.createCell(i).setCellValue(titles[i]);
		}
		
		//创建数据行
		for (int i = 0;i < list.size();i ++) {
			HSSFRow row = sheet1.createRow(i + 1);
			row.createCell(0).setCellValue(list.get(i).get("EMP_ID").toString());
			row.createCell(1).setCellValue(list.get(i).get("EMP_NAME").toString());
			row.createCell(2).setCellValue(list.get(i).get("POS_NAME").toString());
			row.createCell(3).setCellValue(list.get(i).get("DEPT_NAME").toString());
			row.createCell(4).setCellValue(list.get(i).get("PHONE").toString());
		}
		
		wb.write(os);
		os.close();

	}

}
