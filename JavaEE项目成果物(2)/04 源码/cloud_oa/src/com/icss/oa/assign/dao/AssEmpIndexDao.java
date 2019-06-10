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

	// ����Ŀ¼·��
	public String indexPath = "E:\\luceneIndex";

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
	}

	/**
	 * ��ҳ��ʾ��ѯ���
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public ResultBean search(Query query, int firstResult, int maxResults)
			throws IOException, InvalidTokenOffsetsException {

		// ���������ķִ���
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
//				analyzer);
		
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
		List<AssignEmp> recordList = new ArrayList<AssignEmp>();

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
			String assName = doc.get("assName");
			
			System.out.print(assName);
			
			TokenStream tream = analyzer.tokenStream("assName",new StringReader(assName));
			
			String searchResultAssName = highlighter.getBestFragment(tream, assName);

			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
			if (searchResultAssName == null) {
				int minLen = assName.length() >= 100 ? 100 : assName
						.length();
				searchResultAssName = assName.substring(0, minLen);
			}
			
			// �õ�ѧ����Ϣ
			String education = doc.get("education");
			
			System.out.print(education);
			
			tream = analyzer.tokenStream("education", new StringReader(education));
			
			String searchResultEducation= highlighter.getBestFragment(tream, education);
			
			// ������� �������ؼ��֣��᷵��null���ͽ�ȡǰ100���ַ�
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