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
		
		//������Ա��
		empDao.insert(emp);
		
		//�������
		int id = empDao.getPrimaryKey();
		
		//����ȫ�ļ�������
		Document document = new Document();
		document.add(new TextField("empId", String.valueOf(id), Store.YES));
		document.add(new TextField("empName", emp.getEmpName(), Store.YES));
		document.add(new TextField("deptId",String.valueOf(emp.getDepartmentId()),Store.YES));
		document.add(new TextField("deptName", empDao.getDeptNameByEmp(id), Store.YES));
		indexDao.create(document);

		//����Ĭ�ϸ����ļ���
		Folder folder = new Folder(null,emp.getEmpName()+"���ƴ洢",(long)1073741824,"�����ư칫Ϊ���ṩ���Ĭ��1G�ƿռ䣬�������Ĺ����������л�������������",0,0,id,(long)1073741824,0);
		folderDao.insertFolder(folder);
		
		//Ĭ�Ͽ��Ը��Լ����ô���
		Agent agent = new Agent(id,id,1);
		agentDao.insert(agent);
		
		//Ĭ�ϸ�����Ƭ��
		CardCategory category = new CardCategory(id, emp.getEmpName()+"��������", 0);
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
	 * ��ѯ����Ա��
	 * @param pager
	 * @return һҳ��Ա�����������ԣ��Լ����ź�ְ�������
	 */
	public List<Map<String,Object>> queryEmployee(Pager pager){
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		return empDao.query(map);
	}

	/**
	 * 
	 * @return Ա������
	 */
	public int count() {
		return empDao.getCount();
	}
	
	public Employee queryById(Integer empId){
		return empDao.queryById(empId);
	}
	
	/**
	 * ��ͨ����
	 * @param pager
	 * @param nameOrDept
	 * @return ��ҳ�������
	 */
	public List<Map<String,Object>> queryByCondition(Pager pager, String nameOrDept){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		map.put("nameOrDept", nameOrDept);
		return empDao.queryByCondition(map);
	}
	
	/**
	 * ��ѯ���ϼ�������Ŀ����
	 * @param nameOrDept
	 * @return
	 */
	public int conditionCount(String nameOrDept){
		return empDao.getConditionCount(nameOrDept);
	}
	
	/**
	 * ��Ա����ӽ�ɫ
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
	 * ɾ��Ա����ɫ
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
	 * �����Զ������ʼ�
	 */
	public void sendBirthdayEmail(){
		//��ý�������
		
		//��ý�������Ա���б�
		List<Employee> birthdayList = empDao.queryTodayBirthday();
		
		//���ò���
		Properties props=new Properties();
		props.setProperty("mail.smtp.auth", "true");//�Ƿ��������֤
		props.setProperty("mail.transport.protocol", "smtp");//ʹ�õĴ���Э��
		props.setProperty("mail.smtp.host", "192.168.1.1");//����SMTP������
		props.setProperty("mail.smtp.from", "jack@icss.com");//���ͷ��ʼ���ַ
		
		//�����ʼ��������Ự���󣬴���������û���������֤����,�˴���������ʵ��
		Session session=Session.getInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("jack@icss.com","123456");
			}			
		});
		
		//��ʾ������Ϣ
		session.setDebug(true);
		
		for (Employee employee : birthdayList) {
			//������Ϣ����
			MimeMessage msg=new MimeMessage(session);
			try {
				
				//���ͷ��������ַ
				msg.setFrom(new InternetAddress("jack@icss.com"));
				//�ʼ�����
				
					msg.setSubject("����˾ף�����տ���");
				
				//���շ��������ַ���飬Message.RecipientType.TO�Ƿ��ͷ�ʽ(TO������CC���ͣ�BCC�ܼ�����)
				msg.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress(employee.getEmail())});
				
				//Ϊ���ģʽ���ʼ����ݶ���
				MimeMultipart msgMultipart = new MimeMultipart("mixed");
							
				//��ͨHTML�ı�
				MimeBodyPart body = new MimeBodyPart();
				body.setContent(employee.getEmpName()+",���ã�<br />�������������գ�ף�����տ���<br />���տ���ƾ���ʼ��ڹ�˾ǰ̨��ȡ������ѵ�ӰƱ��","text/html;charset=gbk");
				
				//������1���뵽�ʼ����ݶ�����
				msgMultipart.addBodyPart(body);
				
				//�����ʼ�����
				msg.setContent(msgMultipart);
				
				//�����ʼ�
				Transport.send(msg);
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}		
		}
		
	}
	
	/**
	 * ����Ա���ø����ļ��д�С
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
	 * ��ѯ���û����Ƿ����
	 * @param empNum
	 * @return �����û��������棬�����ڷ��ؼ�
	 */
	public boolean numIsExist(String empNum){
		if(empDao.empNumIsExist(empNum) != null)
			return true;
		else
			return false;
	}
	
	/**
	 * ȫ�ļ���
	 * @param queryStr
	 * @param pager
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public EmpIndexResultBean queryByIndex(String queryStr, Pager pager) throws ParseException, IOException, InvalidTokenOffsetsException {
		// ���ķִ���
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "empName", "deptName"}, analyzer);
		
		Query query = queryParser.parse(QueryParser.escape(queryStr));

		EmpIndexResultBean resultBean = indexDao.search(query, pager.getStart(), pager.getPageSize());
		
		return resultBean;
	}
	
	/**
	 * ͨ��Ա��ID��ѯ��������
	 * @param empId
	 * @return
	 */
	public String getDeptNameByEmpId(Integer empId){
		return empDao.getDeptNameByEmp(empId);
	}
	
	/**
	 * ͨ��Ա��ID��ѯְ������
	 * @param empId
	 * @return
	 */
	public String getPosNameByEmpId(Integer empId){
		return empDao.getPosNameByEmp(empId);
	}
}
