package com.icss.oa.system.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

import com.icss.oa.common.Global;
import com.icss.oa.system.pojo.Position;

@Component
public class IndexDao {

	// 索引目录路径
	public String indexPath = Global.INDEX_PATH;

	// 中文分词器
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * 新增索引
	 * @param document
	 * @throws IOException
	 */
	public void create(Document document) throws IOException {
		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.addDocument(document);
		indexWriter.commit();
		indexWriter.close();

		System.out.println("索引被添加");
	}
	
	/**
	 * 根据Term条件删除索引，如果是数据库，根据ID就可以了
	 * 
	 * Term是搜索的最小单位，代表某个 Field 中的一个关键词，如：<title, lucene>
	 * 
	 * new Term( "title", "lucene" );
	 * 
	 * new Term( "id", "5" );
	 * 
	 * new Term( "id", UUID );
	 * 
	 * @param term
	 * @throws IOException
	 */
	public void delete(Term term) throws IOException {

		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.deleteDocuments(term);
		indexWriter.commit();
		indexWriter.close();
		
		System.out.println("删除索引");
	}
	
	/**
	 * 根据Term条件更新索引
	 * 
	 * @throws IOException
	 */
	public void update(Term term, Document document) throws IOException {
		
		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
						
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.updateDocument(term, document);
		indexWriter.commit();
		indexWriter.close();
		
		System.out.println("更新索引");
	}
	
	/**
	 * 分页显示查询结果
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public EmpIndexResultBean search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {
		
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher是用来搜索用的
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
	
		// 得到满足条件的前1000行
		TopDocs topDocs = indexSearcher.search(query, 1000);
			
		// 总记录数
		int recordCount = topDocs.totalHits;

		// 文档集合
		List<Map<String,Object>> recordList = new ArrayList<Map<String,Object>>();

		// ============高亮和截取某个字段的文本摘要设置=============
		// 设置环绕的首尾字符串
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
				"<span style=\"color:red\">","</span>");
		// 语法高亮显示设置
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(
				query));
		// 100是是表示摘要的字数
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		// ===================================================

		/* 获得当前页的数据 */
		int end = Math.min(firstResult - 1 + maxResults, topDocs.totalHits);// 终止下标
				
		for (int i = firstResult - 1; i < end; i++) {
			
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			
			int docSn = scoreDoc.doc; // 文档内部编号
			Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档

			// 得到高亮及摘要文字
			String empName = doc.get("empName");
			
			TokenStream tream = analyzer.tokenStream("empName",
					new StringReader(empName));
			
			String searchResultEmpName = highlighter.getBestFragment(tream, empName);
			
			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResultEmpName == null) {
				int minLen = empName.length() >= 100 ? 100 : empName
						.length();
				searchResultEmpName = empName.substring(0, minLen);
			}						

			String deptName = doc.get("deptName");
			
			tream = analyzer.tokenStream("deptName", new StringReader(
					deptName));
			
			String searchResultDeptName = highlighter.getBestFragment(tream, deptName);

			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResultDeptName == null) {
				int minLen = deptName.length() >= 100 ? 100 : deptName
						.length();
				searchResultDeptName = deptName.substring(0, minLen);
			}		
						
			Map<String, Object> emp = new HashMap<String, Object>();
			emp.put("empId",Integer.parseInt(doc.get("empId")));
			emp.put("empName",searchResultEmpName);
			emp.put("deptId",Integer.parseInt(doc.get("deptId")));	
			emp.put("deptName", searchResultDeptName);
			recordList.add(emp);
		}

		ir.close();

		return new EmpIndexResultBean(recordCount, recordList);
	}
	
	/**
	 * 全文检索职务
	 * @param query
	 * @param firstResult
	 * @param maxResults
	 * @return 
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public PosIndexResultBean searchPos(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher是用来搜索用的
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
	
		// 得到满足条件的前1000行
		TopDocs topDocs = indexSearcher.search(query, 1000);
			
		// 总记录数
		int recordCount = topDocs.totalHits;

		// 文档集合
		List<Position> recordList = new ArrayList<Position>();

		// ============高亮和截取某个字段的文本摘要设置=============
		// 设置环绕的首尾字符串
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
				"<span style=\"color:red\">","</span>");
		// 语法高亮显示设置
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(
				query));
		// 100是是表示摘要的字数
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		// ===================================================

		/* 获得当前页的数据 */
		int end = Math.min(firstResult - 1 + maxResults, topDocs.totalHits);// 终止下标
				
		for (int i = firstResult - 1; i < end; i++) {
			
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			
			int docSn = scoreDoc.doc; // 文档内部编号
			Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档

			// 得到高亮及摘要文字
			String posName = doc.get("posName");
			
			TokenStream tream = analyzer.tokenStream("posName",
					new StringReader(posName));
			
			String searchResultPosName = highlighter.getBestFragment(tream, posName);
			
			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResultPosName == null) {
				int minLen = posName.length() >= 100 ? 100 : posName
						.length();
				searchResultPosName = posName.substring(0, minLen);
			}						

			// 得到高亮及摘要文字
			String posInfo = doc.get("posInfo");
			
			tream = analyzer.tokenStream("posInfo",
					new StringReader(posInfo));
			
			String searchResultPosInfo = highlighter.getBestFragment(tream, posInfo);
			
			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResultPosInfo == null) {
				int minLen = posInfo.length() >= 100 ? 100 : posInfo
						.length();
				searchResultPosInfo = posInfo.substring(0, minLen);
			}		
			
			Position pos = new Position();
			pos.setPosId(Integer.parseInt(doc.get("posId")));
			pos.setPosName(searchResultPosName);
			pos.setPosInfo(searchResultPosInfo);
			recordList.add(pos);
		}

		ir.close();

		return new PosIndexResultBean(recordCount, recordList);
	}
}
