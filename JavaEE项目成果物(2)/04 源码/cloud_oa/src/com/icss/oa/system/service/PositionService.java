package com.icss.oa.system.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.PositionDao;
import com.icss.oa.system.index.IndexDao;
import com.icss.oa.system.index.PosIndexResultBean;
import com.icss.oa.system.pojo.Position;

@Service
@Transactional(rollbackFor=Exception.class)
public class PositionService {

	@Autowired
	private PositionDao posDao;
	
	@Autowired
	private IndexDao indexDao;
	
	public int addPos(Position pos) throws IOException{
		posDao.insert(pos);
		int id = posDao.getPrimaryKey();
		
		Document document = new Document();
		document.add(new TextField("posId", String.valueOf(id), Store.YES));
		document.add(new TextField("posName", pos.getPosName(), Store.YES));
		document.add(new TextField("posInfo", pos.getPosInfo(), Store.YES));
		indexDao.create(document);
		return id;
	}
	
	public void editPos(Position pos) throws IOException{
		posDao.update(pos);
		
		Document document = new Document();
		Term term = new Term("posId",String.valueOf(pos.getPosId()));
		document.add(new TextField("posId", String.valueOf(pos.getPosId()), Store.YES));
		document.add(new TextField("posName", pos.getPosName(), Store.YES));
		document.add(new TextField("posInfo", pos.getPosInfo(), Store.YES));
		indexDao.update(term, document);

	}
	
	public void deletePos(Integer posId) throws IOException{
		posDao.delete(posId);
		
		Term term = new Term("posId",String.valueOf(posId));
		indexDao.delete(term);
	}
	
	public Position queryById(Integer posId){
		return posDao.queryById(posId);
	}
	
	public List<Position> query(Pager pager){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		return posDao.queryByPage(map);
	}
	
	/**
	 * 查询职务总数
	 * @return
	 */
	public int count(){
		return posDao.count();
	}
	
	/**
	 * 查询是否有下属员工
	 * @param posId
	 * @return
	 */
	public boolean isHaveEmp(Integer posId){
		if(posDao.haveEmp(posId))
			return true;
		else
			return false;
	}
	
	/**
	 * 职务分页全文检索
	 * @param queryStr
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public PosIndexResultBean queryByIndex(String queryStr, Pager pager) throws Exception {

		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "posName", "posInfo"}, analyzer);
		
		Query query = queryParser.parse(QueryParser.escape(queryStr));

		PosIndexResultBean resultBean = indexDao.searchPos(query, pager.getStart(), pager.getPageSize());
		
		return resultBean;
	}
}
