package com.icss.oa.work.index;

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
import org.springframework.stereotype.Component;

import com.icss.oa.common.Global;
import com.icss.oa.work.pojo.Task;

/**
 * ���ӣ�ɾ�����޸ģ���ѯ����
 * 
 */
@Component
public class TaskIndexDao {

	// ����Ŀ¼·��
	public String indexPath = Global.INDEX_PATH;

	// ���ķִ���
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * ��������
	 */
	public void create(Document document) throws IOException {
		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.addDocument(document);
		indexWriter.commit();
		indexWriter.close();

		System.out.println("���������");
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

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.deleteDocuments(term);
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
		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.updateDocument(term, document);
		indexWriter.commit();
		indexWriter.close();
		
		System.out.println("��������");
	}

	/**
	 * ��ҳ��ʾ��ѯ���
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public TaskQueryResult search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

		// ���������ķִ���
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);

		// ����Ŀ¼
		Directory directory = FSDirectory.open(new File(indexPath));

		// IndexSearcher�����������õ�
		IndexReader ir = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
	

		// �õ�����������ǰ1000��
		TopDocs topDocs = indexSearcher.search(query, 1000);
			
		// �ܼ�¼��
		int recordCount = topDocs.totalHits;

		// �ĵ�����
		List<Task> recordList = new ArrayList<Task>();

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
			String taskName = doc.get("taskName");
			TokenStream tream = analyzer.tokenStream("taskName",
					new StringReader(taskName));
			String searchResultTaskName = highlighter.getBestFragment(tream, taskName);
			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
			if (searchResultTaskName == null) {
				int minLen = taskName.length() >= 100 ? 100 : taskName
						.length();
				searchResultTaskName = taskName.substring(0, minLen);
			}
						

			String empInfo = doc.get("Introduction");
			tream = analyzer.tokenStream("Introduction", new StringReader(
					empInfo));
			String searchResultEmpInfo = highlighter.getBestFragment(tream, empInfo);

			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
			if (searchResultEmpInfo == null) {
				int minLen = empInfo.length() >= 100 ? 100 : empInfo
						.length();
				searchResultEmpInfo = empInfo.substring(0, minLen);
			}			
			
			Task task = new Task();
			task.setTaskId(Integer.parseInt(doc.get("taskId")));
			task.setTaskName(searchResultTaskName);
			task.setTaskContent(searchResultEmpInfo);

			recordList.add(task);
		}

		ir.close();

		return new TaskQueryResult(recordCount, recordList);
	}

}