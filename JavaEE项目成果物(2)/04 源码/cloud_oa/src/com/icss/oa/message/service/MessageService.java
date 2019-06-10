package com.icss.oa.message.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.message.dao.MessageDao;
import com.icss.oa.message.index.MessageIndexDao;
import com.icss.oa.message.index.MessageQueryResult;
import com.icss.oa.message.index.ThreadPool;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.system.dao.EmployeeDao;

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService {

	@Autowired
	private MessageDao dao;
	
	@Autowired
	private MessageIndexDao indexDao;
	
	//插入新信息到草稿箱中，需要Message对象，包括除ID外所有属性
	public void saveAsDraft(Message message){
		long messageId = dao.insertMessage(message);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("messageId", messageId);
		map.put("senderId",message.getSenderId());
		dao.insertSendedMessage(map);
		
		message = this.queryById(messageId);
		
		// 创建索引
		Document document = new Document();
		document.add(new StringField("messageId",String.valueOf(messageId),Field.Store.YES));
		document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
		if(message.getRecipientId()!=null){
			document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
		}
		document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
		document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
		document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
		document.add(new TextField("content",htmlToText(message.getContent()),Field.Store.YES));
		document.add(new StringField("saveStatus","sender",Field.Store.YES));
		Thread thread = new Thread(new CreateIndex(document));
		ThreadPool.getThreadPool().getPool().execute(thread);
	}
	
	//更新草稿,该Message对象需要所有属性，包括ID
	public void updateDraft(Message message){
		dao.update(message);
		
		Document document = new Document();
		Term term = new Term("messageId",String.valueOf(message.getMessageId()));
		
		document.add(new StringField("messageId",String.valueOf(message.getMessageId()),Field.Store.YES));
		document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
		if(message.getRecipientId()!=null){
			document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
		}
		document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
		document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
		document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
		document.add(new TextField("content",htmlToText(message.getContent()),Field.Store.YES));
		document.add(new StringField("saveStatus","sender",Field.Store.YES));
		
		Thread thread = new Thread(new UpdateIndex(term,document));
		ThreadPool.getThreadPool().getPool().execute(thread);
	}
	
	//发送信息,需要Message对象
	public void sendMessage(Message message){
		long messageId = 0;
		if(message.getMessageId() == null || message.getMessageId()==0){//直接发送，还未保存至草稿箱
			messageId = dao.insertMessage(message);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("messageId", messageId);
			map.put("senderId",message.getSenderId());
			dao.insertSendedMessage(map);
		}else{
			messageId = message.getMessageId();
		}
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("messageId", messageId);
		map.put("recipientId",message.getRecipientId());
		
		//更新发送时间
		dao.updateSendDate(messageId);
		//改变发送状态码，把草稿改为已发送
		dao.updateSendStatus(messageId);
		//更新收件箱
		dao.insertReceivedMessage(map);
		
		message = this.queryById(messageId);
		
		Document document = new Document();
		Term term = new Term("messageId",String.valueOf(messageId));
		
		document.add(new StringField("messageId",String.valueOf(messageId),Field.Store.YES));
		document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
		document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
		document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
		document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
		document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
		document.add(new TextField("content",htmlToText(message.getContent()),Field.Store.YES));
		document.add(new StringField("saveStatus","both",Field.Store.YES));
		Thread thread = new Thread(new UpdateIndex(term,document));
		ThreadPool.getThreadPool().getPool().execute(thread);
		
	}
	
	//设置信息为已读,信息的id
	public void setReaded(long messageId) throws ParseException, IOException, InvalidTokenOffsetsException{
		dao.updateReadStatus(messageId);

		Message message = this.queryById(messageId);
		
		Document document = new Document();
		Term term = new Term("messageId",String.valueOf(message.getMessageId()));
		
		document.add(new StringField("messageId",String.valueOf(message.getMessageId()),Field.Store.YES));
		document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
		document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
		document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
		document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
		document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
		document.add(new TextField("content",htmlToText(message.getContent()),Field.Store.YES));
		if(this.querySaveStatus(messageId).equals("both")){
			document.add(new StringField("saveStatus","both",Field.Store.YES));
		}else if(this.querySaveStatus(messageId).equals("xxx")){
			return;
		}else{
			document.add(new StringField("saveStatus","receiv",Field.Store.YES));
		}
		
		Thread thread = new Thread(new UpdateIndex(term,document));
		ThreadPool.getThreadPool().getPool().execute(thread);
	}
	
	//删除Message,发件箱,该Message对象需要所有属性，包括ID
	public void deleteSendedMessage(long messageId,Integer senderId) throws ParseException, IOException, InvalidTokenOffsetsException{
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("messageId",messageId);
		map.put("senderId",senderId);
		dao.deleteSendedMessage(map);
		
		Message message = this.queryById(messageId);
	
		Term term = new Term("messageId", String.valueOf(messageId));
		if(this.querySaveStatus(messageId).equals("sender")){
			// 删除索引
//			indexDao.delete(term);
//			new Thread(new DeleteIndex(term)).start();
			Thread thread = new Thread(new DeleteIndex(term));
			ThreadPool.getThreadPool().getPool().execute(thread);
		}else if(this.querySaveStatus(messageId).equals("xxx")){
			return;
		}else{
			Document document = new Document();
			
			document.add(new StringField("messageId",String.valueOf(message.getMessageId()),Field.Store.YES));
			document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
			document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
			document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
			document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
			document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
			document.add(new TextField("content",htmlToText(message.getContent()),Field.Store.YES));
			document.add(new StringField("saveStatus","receiv",Field.Store.YES));
			
			Thread thread = new Thread(new UpdateIndex(term,document));
			ThreadPool.getThreadPool().getPool().execute(thread);
		}
	}
	
	//删除Message，收件箱中的,该Message对象需要所有属性，包括ID
	public void deleteReceivedMessage(long messageId,Integer recipientId) throws ParseException, IOException, InvalidTokenOffsetsException{
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("messageId", messageId);
		map.put("recipientId",recipientId);
		dao.deleteReceivedMessage(map);
		
		Message message = this.queryById(messageId);
		
		Term term = new Term("messageId", String.valueOf(messageId));
		if(this.querySaveStatus(messageId).equals("receiv")){
			Thread thread = new Thread(new DeleteIndex(term));
			ThreadPool.getThreadPool().getPool().execute(thread);
		}else if(this.querySaveStatus(messageId).equals("xxx")){
			return;
		}else{
			Document document = new Document();
			
			document.add(new StringField("messageId",String.valueOf(message.getMessageId()),Field.Store.YES));
			document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
			document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
			document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
			document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
			document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
			document.add(new TextField("content",htmlToText(message.getContent()),Field.Store.YES));
			document.add(new StringField("saveStatus","sender",Field.Store.YES));
			
			Thread thread = new Thread(new UpdateIndex(term,document));
			ThreadPool.getThreadPool().getPool().execute(thread);
		}
	}
	
	//删除草稿,把Message也删除掉
	public void deleteDraft(long messageId,Integer senderId) throws ParseException, IOException, InvalidTokenOffsetsException{
		dao.deleteMessage(messageId);
		
		// 删除索引
		Term term = new Term("messageId", String.valueOf(messageId));
		Thread thread = new Thread(new DeleteIndex(term));
		ThreadPool.getThreadPool().getPool().execute(thread);
	}
	
	
	//得到草稿总数
	@Transactional(readOnly = true)
	public int getNotSendCount(Integer id){
		return dao.getNotSendCount(id);
	}
	
	//查询草稿箱
	@Transactional(readOnly = true)
	public List<Message> queryDraft(Pager pager,Integer id){
		List<Message> list = dao.queryNotSendMessage(pager,id);
		return list;
	}
	
	//全文检索草稿箱
	@Transactional(readOnly = true)
	public MessageQueryResult queryDraftByIndex(String queryStr, Pager pager,Integer id) throws ParseException, IOException, InvalidTokenOffsetsException{

		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_47,"content",analyzer);
		
		String lucene = "+(content:"+QueryParser.escape(queryStr)+")+(senderId:"+id+")+(sendStatus:0)";
		
		Query query = queryParser.parse(lucene);
		
		return indexDao.search(query, pager.getStart(), pager.getPageSize());
	}
	
	//得到发件箱总数
	@Transactional(readOnly = true)
	public int getSendedCount(Integer id){
		return dao.getSendedCount(id);
	}
	
	//得到已发Message
	@Transactional(readOnly = true)
	public List<Message> querySendedMessage(Pager pager,Integer id) {
		List<Message> list = dao.querySendedMessage(pager, id);
		return list;
	}
	
	//全文检索发件箱
	@Transactional(readOnly = true)
	public MessageQueryResult querySendedByIndex(String queryStr, Pager pager,Integer id) throws ParseException, IOException, InvalidTokenOffsetsException{

		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_47,"content",analyzer);
		
		String lucene = "+(content:"+QueryParser.escape(queryStr)+")+(senderId:"+id+")+(sendStatus:1)+(saveStatus:both saveStatus:sender)";
		
		Query query = queryParser.parse(lucene);
		System.out.println(query);

		
		return indexDao.search(query, pager.getStart(), pager.getPageSize());
	}
	
	//得到收件箱总数
	@Transactional(readOnly = true)
	public int getReceivedCount(Integer id){
		return dao.getReceivedCount(id);
	}
	
	//得到已收Message
	@Transactional(readOnly = true)
	public List<Message> queryReceivedMessage(Pager pager,Integer id) {
		List<Message> list = dao.queryReceivedMessage(pager, id);
		return list;
	}
	
	//全文检索收件箱
	@Transactional(readOnly = true)
	public MessageQueryResult queryReceivedByIndex(String queryStr, Pager pager,Integer id) throws ParseException, IOException, InvalidTokenOffsetsException{

		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_47,"content",analyzer);
		
		String lucene = "+(content:"+QueryParser.escape(queryStr)+")+(recipientId:"+id+")+(sendStatus:1)+(saveStatus:both saveStatus:receiv)";
		
		Query query = queryParser.parse(lucene);
		
		return indexDao.search(query, pager.getStart(), pager.getPageSize());
	}
	
	//得到未读Message总数
	@Transactional(readOnly = true)
	public int getNotReadCount(Integer id){
		return dao.getNotReadCount(id);
	}
	
	//得到未读Message
	@Transactional(readOnly = true)
	public List<Message> queryNotReadMessage(Pager pager,Integer id) {
		List<Message> list = dao.queryNotReadMessage(pager, id);
		return list;
	}
	
	//全文检索未阅消息
	@Transactional(readOnly = true)
	public MessageQueryResult queryNotReadByIndex(String queryStr, Pager pager,Integer id) throws ParseException, IOException, InvalidTokenOffsetsException{

		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_47,"content",analyzer);
		
		String lucene = "+(content:"+QueryParser.escape(queryStr)+")+(recipientId:"+id+")+(sendStatus:1)+(readStatus:0)+(saveStatus:both saveStatus:receiv)";
		
		Query query = queryParser.parse(lucene);
		
		return indexDao.search(query, pager.getStart(), pager.getPageSize());
	}
	
	@Transactional(readOnly = true)
	public Message queryById(long id) {		
		return dao.queryById(id);
	}
	
	//重建所有索引——带完善
	public void resetAllIndex(){
		List<Message> list = dao.queryAll();
		for(Message message:list){
			Document document = new Document();
			
			document.add(new StringField("messageId",String.valueOf(message.getMessageId()),Field.Store.YES));
			if(message.getSenderId()!=null){
				document.add(new StringField("senderId",String.valueOf(message.getSenderId()),Field.Store.YES));
			}
			if(message.getRecipientId()!=null){
				document.add(new StringField("recipientId",String.valueOf(message.getRecipientId()),Field.Store.YES));
			}
			document.add(new StringField("sendStatus",String.valueOf(message.getSendStatus()),Field.Store.YES));
			document.add(new StringField("readStatus",String.valueOf(message.getReadStatus()),Field.Store.YES));
			document.add(new StringField("sendDate",String.valueOf(message.getSendDate()),Field.Store.YES));
			document.add(new TextField("content",message.getContent(),Field.Store.YES));
			try {
				indexDao.create(document);
			} catch (IOException e) {
				//添加到日志中？
				System.out.println("草稿索引添加失败");
				e.printStackTrace();
			}
		}
	}
	
	//全文索引的删除状态
	@Transactional(readOnly = true)
	public String querySaveStatus(long messageId) throws ParseException, IOException, InvalidTokenOffsetsException{

		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_47,"content",analyzer);
		
		String lucene = "(messageId:"+messageId+")";
		
		Query query = queryParser.parse(lucene);
		
		return indexDao.querySaveStatus(query);
	}
	
	//工具方法
	@Transactional(readOnly = true)
	public String htmlToText(String content) {
		String reStr = "";//结果字符串
		if (content == null)
			return "";
		char[] tempChar = content.toCharArray();
		for (int kk = 0; kk < tempChar.length; kk++) {
			if(tempChar[kk]=='<'){
				kk++;
				while(tempChar[kk]!='>'){
					kk++;
				}
				continue;
			}
			reStr += tempChar[kk];
		}
		return reStr;
	}
	
	private class CreateIndex implements Runnable {
        private Document document;
        public CreateIndex(Document document){
        	this.document = document;
        }        	
		public void run() {
        	try {
				indexDao.create(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
	private class DeleteIndex implements Runnable {
        private Term term;
        public DeleteIndex(Term term){
        	this.term = term;
        }        	
		public void run() {
        	try {
        		indexDao.delete(term);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
	private class UpdateIndex implements Runnable {
        private Term term;
        private Document document;
        public UpdateIndex(Term term,Document document){
        	this.term = term;
        	this.document = document;
        }        	
		public void run() {
        	try {
        		indexDao.update(term, document);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
