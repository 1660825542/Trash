package com.icss.oa.assign.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.assign.dao.AssEmpIndexDao;
import com.icss.oa.assign.dao.AssignEmpDao;
import com.icss.oa.assign.pojo.AssignEmp;
import com.icss.oa.assign.pojo.ResultBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class AssignEmpService {

	@Autowired
	private AssignEmpDao dao;
	
	@Autowired
	private AssEmpIndexDao indexDao;

	// ��ѯ��ʹ������
	@Transactional(readOnly = true)
	public List<AssignEmp> query() {
		return dao.query();
	}

	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}

	public void insert(AssignEmp ase) throws IOException {	
		dao.insert(ase);
		
		int id = dao.getPrimaryKey();
		
		// ����ȫ�ļ�������
		Document document = new Document();
		document.add(new TextField("assId", String.valueOf(id),Store.YES));
		document.add(new TextField("assName", ase.getAssName(), Store.YES));
		document.add(new TextField("education", ase.getEducation(), Store.YES));
		document.add(new TextField("workExp", ase.getWorkExp(), Store.YES));
		document.add(new TextField("skill", ase.getSkill(), Store.YES));


		indexDao.create(document);
	}
	
	public void delete(Integer assId) throws IOException {
		dao.delete(assId);
		
		// ɾ������
		Term term = new Term("assId", String.valueOf(assId));
		indexDao.delete(term);
	}
	
	public void update(AssignEmp ase) throws IOException {
		dao.update(ase);
		
		// ��������
		Document document = new Document();

		Term term = new Term("assId", String.valueOf(ase.getAssId()));

		document.add(new TextField("assId", String.valueOf(ase.getAssId()),Store.YES));
		document.add(new TextField("assName", ase.getAssName(), Store.YES));
		document.add(new TextField("education", ase.getEducation(), Store.YES));
		document.add(new TextField("workExp", ase.getWorkExp(), Store.YES));
		document.add(new TextField("skill", ase.getSkill(), Store.YES));
		indexDao.update(term, document);

	}
	
	//����id��ѯ
	public AssignEmp queryById(Integer assId) {
		return dao.queryById(assId);
	}

	//��ҳ��ѯ
	@Transactional(readOnly = true)
	public List<AssignEmp> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}
	
	@Transactional(readOnly = true)
	public ResultBean queryByIndex(String queryStr, Pager pager) throws Exception {

		// ���ķִ���
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "assName","education","skill","workExp"}, analyzer);
		
		Query query = queryParser.parse(QueryParser.escape(queryStr));

		ResultBean resultBean = indexDao.search(query, pager.getStart(), pager.getPageSize());
		
		return resultBean;
	}

}