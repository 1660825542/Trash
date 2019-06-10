package com.icss.oa.system.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.card.dao.CardCategoryDao;
import com.icss.oa.card.dao.CardDao;
import com.icss.oa.card.pojo.Card;
import com.icss.oa.card.pojo.CardCategory;
import com.icss.oa.common.Pager;
import com.icss.oa.folder.dao.FolderDao;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.index.EmpIndexResultBean;
import com.icss.oa.system.index.IndexDao;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.work.dao.AgentDao;
import com.icss.oa.work.pojo.Agent;

@Service
@Transactional(rollbackFor=Exception.class)
public class EmployeeService {
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private IndexDao indexDao;
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private FolderDao folderDao;
	
	@Autowired
	private CardCategoryDao cardCategoryDao;
	
	@Autowired
	private CardDao cardDao;
	
	public void addEmployee(Employee emp) throws IOException{
		String password = emp.getPassword();
		String security = new Sha256Hash(password, "icssoa", 10).toBase64();
		emp.setPassword(security);
		
		//插入新员工
		empDao.insert(emp);
		
		//获得主码
		int id = empDao.getPrimaryKey();
		
		//创建全文检索索引
		Document document = new Document();
		document.add(new TextField("empId", String.valueOf(id), Store.YES));
		document.add(new TextField("empName", emp.getEmpName(), Store.YES));
		document.add(new TextField("deptId",String.valueOf(emp.getDepartmentId()),Store.YES));
		document.add(new TextField("deptName", empDao.getDeptNameByEmp(id), Store.YES));
		indexDao.create(document);

		//创建默认个人文件夹
		Folder folder = new Folder(null,emp.getEmpName()+"的云存储",(long)1073741824,"中软云办公为您提供免费默认1G云空间，在您今后的工作生活中有机会增加容量。",0,0,id,(long)1073741824,0);
		folderDao.insertFolder(folder);
		
		//默认可以给自己设置代办
		Agent agent = new Agent(id,id,1);
		agentDao.insert(agent);
		
		//默认个人名片夹
		CardCategory category = new CardCategory(id, emp.getEmpName()+"的名件夹", 0);
		cardCategoryDao.insert(category);
		
		int cataId = cardCategoryDao.getPrimaryKey();
		String deptName = empDao.getDeptNameByEmp(id);
		String posName = empDao.getPosNameByEmp(id);
		Card card = new Card(cataId, emp.getEmpName(), emp.getPhone(), posName, deptName);
		cardDao.insert(card);
	}
	
	public void editEmployee(Employee emp) throws IOException{
		String password = emp.getPassword();
		System.out.println(password);
		if(password!=null && !("".equals(password))){
			String security = new Sha256Hash(password, "icssoa", 10).toBase64();
			emp.setPassword(security);
		}
		
		emp.setIntroduction(empDao.queryById(emp.getEmpId()).getIntroduction());
			
		empDao.update(emp);
		
		Document document = new Document();
		Term term = new Term("empId",String.valueOf(emp.getEmpId()));
		document.add(new TextField("empId", String.valueOf(emp.getEmpId()), Store.YES));
		document.add(new TextField("empName", emp.getEmpName(), Store.YES));
		document.add(new TextField("deptId",String.valueOf(emp.getDepartmentId()),Store.YES));
		document.add(new TextField("deptName", empDao.getDeptNameByEmp(emp.getEmpId()), Store.YES));
		indexDao.update(term, document);
	}
	
	public void deleteEmployee(Integer empId) throws IOException{
		empDao.delete(empId);
		
		Term term = new Term("empId", String.valueOf(empId));
		indexDao.delete(term);
	}
	
	/**
	 * 查询所有员工
	 * @param pager
	 * @return 一页内员工的所有属性，以及部门和职务的名称
	 */
	public List<Map<String,Object>> queryEmployee(Pager pager){
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		return empDao.query(map);
	}

	/**
	 * 
	 * @return 员工总数
	 */
	public int count() {
		return empDao.getCount();
	}
	
	public Employee queryById(Integer empId){
		return empDao.queryById(empId);
	}
	
	/**
	 * 普通检索
	 * @param pager
	 * @param nameOrDept
	 * @return 分页检索结果
	 */
	public List<Map<String,Object>> queryByCondition(Pager pager, String nameOrDept){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		map.put("nameOrDept", nameOrDept);
		return empDao.queryByCondition(map);
	}
	
	/**
	 * 查询符合检索的条目数量
	 * @param nameOrDept
	 * @return
	 */
	public int conditionCount(String nameOrDept){
		return empDao.getConditionCount(nameOrDept);
	}
	
	/**
	 * 给员工添加角色
	 * @param empId
	 * @param roleId
	 */
	public void addRoleToEmp(Integer empId, Integer roleId){
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("empId", empId);
		map.put("roleId", roleId);
		empDao.addEmpRole(map);
	}
	
	/**
	 * 删除员工角色
	 * @param empId
	 * @param roleId
	 */
	public void deleteEmpRole(Integer empId, Integer roleId){
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("empId", empId);
		map.put("roleId", roleId);
		empDao.deleteEmpRole(map);
	}
	
	/**
	 * 生日自动发送邮件
	 */
	public void sendBirthdayEmail(){
		//获得今日日期
		
		//获得今日生日员工列表
		List<Employee> birthdayList = empDao.queryTodayBirthday();
		
		//配置参数
		Properties props=new Properties();
		props.setProperty("mail.smtp.auth", "true");//是否有身份验证
		props.setProperty("mail.transport.protocol", "smtp");//使用的传输协议
		props.setProperty("mail.smtp.host", "192.168.1.1");//设置SMTP服务器
		props.setProperty("mail.smtp.from", "jack@icss.com");//发送方邮件地址
		
		//连接邮件服务器会话对象，传入参数和用户名密码验证对象,此处用匿名类实现
		Session session=Session.getInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("jack@icss.com","123456");
			}			
		});
		
		//显示调试信息
		session.setDebug(true);
		
		for (Employee employee : birthdayList) {
			//复杂信息对象
			MimeMessage msg=new MimeMessage(session);
			try {
				
				//发送方的邮箱地址
				msg.setFrom(new InternetAddress("jack@icss.com"));
				//邮件主题
				
					msg.setSubject("中软公司祝您生日快乐");
				
				//接收方的邮箱地址数组，Message.RecipientType.TO是发送方式(TO正常，CC抄送，BCC密件抄送)
				msg.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress(employee.getEmail())});
				
				//为混合模式的邮件内容对象
				MimeMultipart msgMultipart = new MimeMultipart("mixed");
							
				//普通HTML文本
				MimeBodyPart body = new MimeBodyPart();
				body.setContent(employee.getEmpName()+",您好：<br />今天是您的生日，祝您生日快乐<br />今日可以凭此邮件在公司前台领取两张免费电影票！","text/html;charset=gbk");
				
				//将内容1加入到邮件内容对象中
				msgMultipart.addBodyPart(body);
				
				//设置邮件内容
				msg.setContent(msgMultipart);
				
				//发送邮件
				Transport.send(msg);
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}		
		}
		
	}
	
	/**
	 * 管理员设置个人文件夹大小
	 * @param empId
	 * @param size
	 */
	public void setFolderSize(Integer empId,Long size){
		Folder rootFolder = folderDao.getRootFolder(empId);
		long newLeftSize = size-rootFolder.getFolderSize()+rootFolder.getFolderLeftSize();
		rootFolder.setFolderSize(size);
		rootFolder.setFolderLeftSize(newLeftSize);
		folderDao.updateSize(rootFolder);
		folderDao.updateLeftSize(rootFolder);
	}
	
	/**
	 * 查询此用户名是否存在
	 * @param empNum
	 * @return 存在用户名返回真，不存在返回假
	 */
	public boolean numIsExist(String empNum){
		if(empDao.empNumIsExist(empNum) != null)
			return true;
		else
			return false;
	}
	
	/**
	 * 全文检索
	 * @param queryStr
	 * @param pager
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public EmpIndexResultBean queryByIndex(String queryStr, Pager pager) throws ParseException, IOException, InvalidTokenOffsetsException {
		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "empName", "deptName"}, analyzer);
		
		Query query = queryParser.parse(QueryParser.escape(queryStr));

		EmpIndexResultBean resultBean = indexDao.search(query, pager.getStart(), pager.getPageSize());
		
		return resultBean;
	}
	
	/**
	 * 通过员工ID查询部门名称
	 * @param empId
	 * @return
	 */
	public String getDeptNameByEmpId(Integer empId){
		return empDao.getDeptNameByEmp(empId);
	}
	
	/**
	 * 通过员工ID查询职务名称
	 * @param empId
	 * @return
	 */
	public String getPosNameByEmpId(Integer empId){
		return empDao.getPosNameByEmp(empId);
	}
}
