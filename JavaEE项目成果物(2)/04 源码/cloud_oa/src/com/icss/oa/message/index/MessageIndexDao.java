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
	
	// ����Ŀ¼·��
	public String indexPath = Global.INDEX_PATH + "\\messageIndex";

	// ���ķִ���
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * ��������
	 */
	public void create(Document document) throws IOException {
		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,analyzer);
		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));
		
//		if (IndexWriter.isLocked(directory)) {
//			IndexWriter.unlock(directory);
//			System.out.println("���н�����" + document.get("recipientId"));
//		}
//
//		while(IndexWriter.isLocked(directory)) {
//			try{
//				int i = new Random().nextInt(200)%(111) + 100;
//			    Thread.sleep(i);
//			    System.out.println("create:�������ʱ�䣺"+i);
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

		System.out.println("���������:" + document.get("recipientId"));
	}
	
	/**
	 * ����Term����ɾ����������������ݿ⣬����ID�Ϳ�����
	 * 
	 * Term����������С��λ������ĳ�� Field �е�һ���ؼ��ʣ��磺<title, lucene>
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

		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));

//		while(IndexWriter.isLocked(directory)) {
//			try{
//				int i = new Random().nextInt(200)%(111) + 100;
//			    Thread.sleep(i);
//			    System.out.println("delete:�������ʱ�䣺"+i);
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
		
		System.out.println("ɾ������");
	}
	
	/**
	 * ����Term������������
	 * 
	 * @throws IOException
	 */
	public void update(Term term, Document document) throws IOException {
		
		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
						
		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));
		
//		while(IndexWriter.isLocked(directory)) {
//			try{
//				int i = new Random().nextInt(200)%(111) + 100;
//			    Thread.sleep(i);
//			    System.out.println("update:�������ʱ�䣺"+i);
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
		
		System.out.println("���������ɹ�");
	}

	/**
	 * ��ҳ��ʾ��ѯ���
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public MessageQueryResult search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

//		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);

		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher�����������õ�
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
			
		// �õ�����������ǰ100��
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		System.out.println(topDocs.totalHits);
		
//		// �ܼ�¼��
		int recordCount = topDocs.totalHits;

		// �ĵ�����
		List<Message> recordList = new ArrayList<Message>();

		// ============�����ͽ�ȡĳ���ֶε��ı�ժҪ����=============
		// ���û��Ƶ���β�ַ���
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
				"<span style=\"color:red\">","</span>");
		// �﷨������ʾ����
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(
				query));
		// 100���Ǳ�ʾժҪ������
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		// ===================================================

		/* ��õ�ǰҳ������ */
		int end = Math.min(firstResult - 1 + maxResults, topDocs.totalHits);// ��ֹ�±�
				
		for (int i = firstResult - 1; i < end; i++) {
			
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			
			int docSn = scoreDoc.doc; // �ĵ��ڲ����
			Document doc = indexSearcher.doc(docSn); // ���ݱ��ȡ����Ӧ���ĵ�

			// �õ�������ժҪ����
			String content = doc.get("content");
			
			TokenStream tream = analyzer.tokenStream("content",
					new StringReader(content));
			
			String searchResultContent = highlighter.getBestFragment(tream, content);
			
			//Ӧ�ò�����֣���Ϊ��һ��
			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�,
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
//		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);

		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher�����������õ�
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
			
		// �õ�����������ǰ100��
		TopDocs topDocs = indexSearcher.search(query, 1);
		
		if(topDocs.totalHits==0){
			System.out.println("û���������ҵ�������Ϣ");
			return "xxx";
		}
		
		int docSn = topDocs.scoreDocs[0].doc;
		Document doc = indexSearcher.doc(docSn);
		String saveStatus = doc.get("saveStatus");
		System.out.println("indexDao�в�ѯ���Ľ��"+saveStatus);
		ir.close();
		return saveStatus;
	}
}
