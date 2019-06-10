package com.icss.oa.message.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

import com.icss.oa.common.Global;
import com.icss.oa.message.pojo.Message;

@Repository
public class MessageIndexDao {
	
	// 索引目录路径
	public String indexPath = Global.INDEX_PATH + "\\messageIndex";

	// 中文分词器
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * 增加索引
	 */
	public void create(Document document) throws IOException {
		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,analyzer);
		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));
		
//		if (IndexWriter.isLocked(directory)) {
//			IndexWriter.unlock(directory);
//			System.out.println("进行解锁：" + document.get("recipientId"));
//		}
//
//		while(IndexWriter.isLocked(directory)) {
//			try{
//				int i = new Random().nextInt(200)%(111) + 100;
//			    Thread.sleep(i);
//			    System.out.println("create:随机挂起时间："+i);
//			}catch (InterruptedException e) {
//			    e.printStackTrace();
//			}
//		}
		
		IndexWriter indexWriter = new IndexWriter(directory, config);
		try{
			indexWriter.addDocument(document);
		}catch(Exception e){
			indexWriter.rollback();
			indexWriter.close();
			e.printStackTrace();
		}
		indexWriter.commit();
		indexWriter.close();

		System.out.println("索引被添加:" + document.get("recipientId"));
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

//		while(IndexWriter.isLocked(directory)) {
//			try{
//				int i = new Random().nextInt(200)%(111) + 100;
//			    Thread.sleep(i);
//			    System.out.println("delete:随机挂起时间："+i);
//			}catch (InterruptedException e) {
//			    e.printStackTrace();
//			}
//		}
		IndexWriter indexWriter = new IndexWriter(directory, config);
		try{
			indexWriter.deleteDocuments(term);
		}catch(Exception e){
			indexWriter.rollback();
			indexWriter.close();
			e.printStackTrace();
		}
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
		
//		while(IndexWriter.isLocked(directory)) {
//			try{
//				int i = new Random().nextInt(200)%(111) + 100;
//			    Thread.sleep(i);
//			    System.out.println("update:随机挂起时间："+i);
//			}catch (InterruptedException e) {
//			    e.printStackTrace();
//			}
//		}
		
		IndexWriter indexWriter = new IndexWriter(directory, config);
		try{
			indexWriter.updateDocument(term, document);
		}catch(Exception e){
			indexWriter.rollback();
			indexWriter.close();
			e.printStackTrace();
		}
		indexWriter.commit();
		indexWriter.close();
		
		System.out.println("更新索引成功");
	}

	/**
	 * 分页显示查询结果
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public MessageQueryResult search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

//		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);

		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher是用来搜索用的
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
			
		// 得到满足条件的前100行
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		System.out.println(topDocs.totalHits);
		
//		// 总记录数
		int recordCount = topDocs.totalHits;

		// 文档集合
		List<Message> recordList = new ArrayList<Message>();

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
			String content = doc.get("content");
			
			TokenStream tream = analyzer.tokenStream("content",
					new StringReader(content));
			
			String searchResultContent = highlighter.getBestFragment(tream, content);
			
			//应该不会出现，因为单一域
			// 如果内容 不包含关键字，会返回null，就截取前100个字符,
//			if (searchResultContent == null) {
//				int minLen = content.length() >= 100 ? 100 : content
//						.length();
//				searchResultContent = content.substring(0, minLen);
//			}							
		
			Message message = new Message();
			message.setMessageId((long)Integer.parseInt(doc.get("messageId")));
			message.setSenderId(Integer.parseInt(doc.get("senderId")));
			if(doc.get("recipientId")!=null){
				message.setRecipientId(Integer.parseInt(doc.get("recipientId")));				
			}
			message.setReadStatus((short)Integer.parseInt(doc.get("readStatus")));
			message.setSendStatus((short)Integer.parseInt(doc.get("sendStatus")));
			message.setContent("");
			message.setSummary(searchResultContent);
			message.setSendDate(Timestamp.valueOf(doc.get("sendDate")));
			recordList.add(message);
		}

		ir.close();
		
		MessageQueryResult messageQueryResult = new MessageQueryResult(recordCount,recordList);

		return messageQueryResult;
	}

	public String querySaveStatus(Query query) throws IOException{
//		// 设置索引的分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);

		// 索引目录
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher是用来搜索用的
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
			
		// 得到满足条件的前100行
		TopDocs topDocs = indexSearcher.search(query, 1);
		
		if(topDocs.totalHits==0){
			System.out.println("没在索引中找到该条消息");
			return "xxx";
		}
		
		int docSn = topDocs.scoreDocs[0].doc;
		Document doc = indexSearcher.doc(docSn);
		String saveStatus = doc.get("saveStatus");
		System.out.println("indexDao中查询到的结果"+saveStatus);
		ir.close();
		return saveStatus;
	}
}
