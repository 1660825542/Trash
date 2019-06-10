package com.icss.oa.assign.dao;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Repository;

import com.icss.oa.assign.pojo.AssignEmp;
import com.icss.oa.assign.pojo.ResultBean;

@Repository
public class AssEmpIndexDao {

	// 索引目录路径
	public String indexPath = "E:\\luceneIndex";

	// 中文分词器
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * 新增索引
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
	}

	/**
	 * 分页显示查询结果
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public ResultBean search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

		// 设置索引的分词器
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
//				analyzer);
		
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
		List<AssignEmp> recordList = new ArrayList<AssignEmp>();

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
		int end = Math.min(firstResult - 1 + maxResults, topDocs.totalHits);// 锟斤拷止锟铰憋拷
				
		for (int i = firstResult - 1; i < end; i++) {
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int docSn = scoreDoc.doc; // 文档内部编号
			Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档

			// 得到高亮及摘要文字
			String assName = doc.get("assName");
			
			System.out.print(assName);
			
			TokenStream tream = analyzer.tokenStream("assName",new StringReader(assName));
			
			String searchResultAssName = highlighter.getBestFragment(tream, assName);

			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResultAssName == null) {
				int minLen = assName.length() >= 100 ? 100 : assName
						.length();
				searchResultAssName = assName.substring(0, minLen);
			}
			
			// 得到学历信息
			String education = doc.get("education");
			
			System.out.print(education);
			
			tream = analyzer.tokenStream("education", new StringReader(education));
			
			String searchResultEducation= highlighter.getBestFragment(tream, education);
			
			// 如果内容 不包含关键字，会返回null，就截取前100个字符
			if (searchResultEducation == null) {
				int minLen = education.length() >= 100 ? 100 : education.length();
				searchResultEducation = education.substring(0, minLen);
			}
			

			String skill = doc.get("skill");
			
			System.out.print(skill);
			
			tream = analyzer.tokenStream("skill", new StringReader(skill));
			
			String searchResultSkill= highlighter.getBestFragment(tream, skill);
			
			if (searchResultSkill == null) {
				int minLen = skill.length() >= 100 ? 100 : skill.length();
				searchResultSkill = skill.substring(0, minLen);
			}

			String workExp = doc.get("workExp");
			
			System.out.print(workExp);
			System.out.println();
			
			tream = analyzer.tokenStream("workExp", new StringReader(workExp));
			
			String searchResultWorkExp= highlighter.getBestFragment(tream, workExp);
			
			if (searchResultWorkExp == null) {
				int minLen = workExp.length() >= 100 ? 100 : workExp.length();
				searchResultWorkExp = workExp.substring(0, minLen);
			}

			AssignEmp assemp = new AssignEmp();
			assemp.setAssId(Integer.parseInt(doc.get("assId")));
			assemp.setAssName(searchResultAssName);
			assemp.setEducation(searchResultEducation);
			assemp.setSkill(searchResultSkill);
			assemp.setWorkExp(searchResultWorkExp);
			
			recordList.add(assemp);
		}

		ir.close();

		return new ResultBean(recordCount, recordList);
	}

}