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

	// ����Ŀ¼·��
	public String indexPath = Global.INDEX_PATH;

	// ���ķִ���
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * ��������
	 * @param document
	 * @throws IOException
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
	public EmpIndexResultBean search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {
		
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
		List<Map<String,Object>> recordList = new ArrayList<Map<String,Object>>();

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
			String empName = doc.get("empName");
			
			TokenStream tream = analyzer.tokenStream("empName",
					new StringReader(empName));
			
			String searchResultEmpName = highlighter.getBestFragment(tream, empName);
			
			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
			if (searchResultEmpName == null) {
				int minLen = empName.length() >= 100 ? 100 : empName
						.length();
				searchResultEmpName = empName.substring(0, minLen);
			}						

			String deptName = doc.get("deptName");
			
			tream = analyzer.tokenStream("deptName", new StringReader(
					deptName));
			
			String searchResultDeptName = highlighter.getBestFragment(tream, deptName);

			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
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
	 * ȫ�ļ���ְ��
	 * @param query
	 * @param firstResult
	 * @param maxResults
	 * @return 
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public PosIndexResultBean searchPos(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

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
		List<Position> recordList = new ArrayList<Position>();

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
			String posName = doc.get("posName");
			
			TokenStream tream = analyzer.tokenStream("posName",
					new StringReader(posName));
			
			String searchResultPosName = highlighter.getBestFragment(tream, posName);
			
			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
			if (searchResultPosName == null) {
				int minLen = posName.length() >= 100 ? 100 : posName
						.length();
				searchResultPosName = posName.substring(0, minLen);
			}						

			// �õ�������ժҪ����
			String posInfo = doc.get("posInfo");
			
			tream = analyzer.tokenStream("posInfo",
					new StringReader(posInfo));
			
			String searchResultPosInfo = highlighter.getBestFragment(tream, posInfo);
			
			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
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
