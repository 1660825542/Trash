package com.icss.oa.message.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.message.index.MessageQueryResult;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.EmpService;
import com.icss.oa.message.service.MessageService;
import com.opensymphony.xwork2.ModelDriven;
import com.icss.oa.system.pojo.Employee;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/message")
@Results({ @Result(name = "error", location = "/pages/error.jsp", type = "redirect") })
public class MessageAction extends BaseAction implements ModelDriven<Message> {

	@Autowired
	private MessageService service;

	@Autowired
	private EmpService empService;

	private Message message = new Message();

	private int pageNum;// ҳ��

	private Integer[] recipientIdList;// Ⱥ���б�

	private String keyword;// ȫ�ļ����ؼ���

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer[] getRecipientIdList() {
		return recipientIdList;
	}

	public void setRecipientIdList(Integer[] recipientIdList) {
		this.recipientIdList = recipientIdList;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public Message getModel() {
		return message;
	}

	// ��ת��"�ҵ��ռ���"����,�ҵ���Ϣ��Ĭ��ҳ
	@Action(value = "toMyMessage", results = { @Result(name = "success", location = "/pages/Message/showReceivedMessage.jsp", type = "dispatcher") })
	public String toMyMessage() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(service.getReceivedCount(id), pageNum);
			List<Message> list = service.queryReceivedMessage(pager, id);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// �������ȫ�ļ���
	@Action(value = "queryReceivedMessage", results = { @Result(name = "success", location = "/pages/Message/showReceivedMessage.jsp", type = "dispatcher") })
	public String queryReceivedMessage() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(pageNum);
			List<Message> list;
			MessageQueryResult messageQueryResult;
			String keyword2 = "";
			try {
				keyword2 = URLDecoder.decode(keyword, "utf-8");
				System.out.println("keyword2:" + keyword2);
			} catch (UnsupportedEncodingException e1) {
				System.out.println("ȫ�ļ����ؼ��ֽ���ʧ��");
				e1.printStackTrace();
			}
	
			try {
				messageQueryResult = service.queryReceivedByIndex(keyword2, pager,
						id);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			list = messageQueryResult.getRecordList();
			pager = new Pager(messageQueryResult.getRecordCount(), pageNum);
			request.setAttribute("keyword", keyword2);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ��ת��"�ҵķ�����"����
	@Action(value = "showSendedMessage", results = { @Result(name = "success", location = "/pages/Message/showSendedMessage.jsp", type = "dispatcher") })
	public String showSendedMessage() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(service.getSendedCount(id), pageNum);
			List<Message> list = service.querySendedMessage(pager, id);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// �������ȫ�ļ���
	@Action(value = "querySendedMessage", results = { @Result(name = "success", location = "/pages/Message/showSendedMessage.jsp", type = "dispatcher") })
	public String querySendedMessage() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(pageNum);
			List<Message> list;
			MessageQueryResult messageQueryResult;
			String keyword2 = "";
			try {
				keyword2 = URLDecoder.decode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				System.out.println("ȫ�ļ����ؼ��ֽ���ʧ��");
				e1.printStackTrace();
			}
	
			try {
				messageQueryResult = service
						.querySendedByIndex(keyword2, pager, id);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			list = messageQueryResult.getRecordList();
			pager = new Pager(messageQueryResult.getRecordCount(), pageNum);
			request.setAttribute("keyword", keyword2);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ��ת��"�ҵĲݸ���"����
	@Action(value = "showDraft", results = { @Result(name = "success", location = "/pages/Message/showDraft.jsp", type = "dispatcher") })
	public String showDraft() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(service.getNotSendCount(id), pageNum);
			List<Message> list = service.queryDraft(pager, id);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// �ݸ��ȫ�ļ���
	@Action(value = "queryDraft", results = { @Result(name = "success", location = "/pages/Message/showDraft.jsp", type = "dispatcher") })
	public String queryDraft() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(pageNum);
			List<Message> list;
			MessageQueryResult messageQueryResult;
			String keyword2 = "";
			try {
				keyword2 = URLDecoder.decode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				System.out.println("ȫ�ļ����ؼ��ֽ���ʧ��");
				e1.printStackTrace();
			}
	
			try {
				messageQueryResult = service.queryDraftByIndex(keyword2, pager, id);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			list = messageQueryResult.getRecordList();
			pager = new Pager(messageQueryResult.getRecordCount(), pageNum);
			request.setAttribute("keyword", keyword2);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ��ȡδ����Ϣ������
	@Action(value = "getNotReadCount")
	public String getNotReadCount(){

		try{
			PrintWriter out = response.getWriter();
			int count = 0;
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
	
			try {
				count = service.getNotReadCount(id);
			} catch (Exception e) {
				out.print(-1);
				return null;
			}
	
			out.print(count);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// ��ת��"�ҵ�δ����Ϣ"����,�ҵ���Ϣ��Ĭ��ҳ
	@Action(value = "showNotRead", results = { @Result(name = "success", location = "/pages/Message/showNotRead.jsp", type = "dispatcher") })
	public String showNotRead() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(service.getNotReadCount(id), pageNum);
			List<Message> list = service.queryNotReadMessage(pager, id);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// δ�ĵ�ȫ�ļ���
	@Action(value = "queryNotRead", results = { @Result(name = "success", location = "/pages/Message/showNotRead.jsp", type = "dispatcher") })
	public String queryNotRead() {
		try{
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			Pager pager = new Pager(pageNum);
			List<Message> list;
			MessageQueryResult messageQueryResult;
			String keyword2 = "";
			try {
				keyword2 = URLDecoder.decode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				System.out.println("ȫ�ļ����ؼ��ֽ���ʧ��");
				e1.printStackTrace();
			}
	
			try {
				messageQueryResult = service.queryNotReadByIndex(keyword2, pager,
						id);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			list = messageQueryResult.getRecordList();
			pager = new Pager(messageQueryResult.getRecordCount(), pageNum);
			request.setAttribute("keyword", keyword2);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ��ת��"��������Ϣ"����
	@Action(value = "toSendMessage", results = { @Result(name = "success", location = "/pages/Message/writeNewMessage.jsp", type = "dispatcher") })
	public String toSendMessage() {
		try{
			if(message.getMessageId()!=0){
				message = service.queryById(message.getMessageId());
				if(message.getRecipientId()!=null){
					String recipientName = empService.queryById(message.getRecipientId());
					request.setAttribute("recipientName", recipientName);
				}
				request.setAttribute("message", message);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ���浽�ݸ�
	@Action(value = "saveAsDraft", results = { @Result(name = "success", location = "/message/showDraft.action", type = "redirect") })
	public String saveAsDraft() {
		try{
			if(recipientIdList.length==1){
				if (message.getMessageId() != 0) {
					service.updateDraft(message);
				} else {
					service.saveAsDraft(message);
				}
				return "success";
			}
			for (Integer recipientId : recipientIdList) {
				if (recipientId != null) {
					message.setRecipientId(recipientId);
					if (message.getMessageId() != 0) {
						service.updateDraft(message);
					} else {
						service.saveAsDraft(message);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ������Ϣ
	@Action(value = "sendMessage", results = { @Result(name = "success", location = "/message/showSendedMessage.action", type = "redirect") })
	public String sendMessage() {
		try{
			for (Integer recipientId : recipientIdList) {
				if (recipientId != null) {
					message.setRecipientId(recipientId);
					try {
						service.sendMessage(message);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ɾ���ݸ�
	@Action(value = "deleteDraft")
	public String deleteDraft() throws IOException {

		try{
			PrintWriter out = response.getWriter();
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
	
			try {
				service.deleteSendedMessage(message.getMessageId(), id);
				service.deleteDraft(message.getMessageId(), id);
			} catch (Exception e) {
				System.out.println("�����쳣0��" + e.getMessage());
				e.printStackTrace();
				out.print(0);
				return null;
			}
	
			out.print(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// ɾ���յ�����Ϣ
	@Action(value = "deleteReceivedMessage")
	public String deleteReceivedMessage() throws IOException {
		try{
			PrintWriter out = response.getWriter();
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
	
			try {
				service.deleteReceivedMessage(message.getMessageId(), id);
			} catch (Exception e) {
				System.out.println("�����쳣1��" + e.getMessage());
				e.printStackTrace();
				out.print(0);
				return null;
			}
	
			out.print(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// ɾ���ѷ��͵���Ϣ
	@Action(value = "deleteSendedMessage")
	public String deleteSendedMessage() throws IOException {
		try{
			PrintWriter out = response.getWriter();
			int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
	
			try {
				service.deleteSendedMessage(message.getMessageId(), id);
			} catch (Exception e) {
				System.out.println("�����쳣2��" + e.getMessage());
				e.printStackTrace();
				out.print(0);
				return null;
			}
	
			out.print(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// ���Ϊ�Ѷ�
	@Action(value = "setReaded")
	public String setReaded() throws IOException {
		try{

			PrintWriter out = response.getWriter();
	
			try {
				service.setReaded(message.getMessageId());
			} catch (Exception e) {
				out.print(0);
				return null;
			}
	
			out.print(1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// ������Ϣ����
	@Action(value = "showMessage", results = { @Result(name = "success", location = "/pages/Message/message.jsp", type = "dispatcher") })
	public String showMessage() {
		try{
			message = service.queryById(message.getMessageId());
			if(message.getRecipientId()!=null){
				String recipientName = empService.queryById(message.getRecipientId());
				request.setAttribute("recipientName", recipientName);
			}
			request.setAttribute("message", message);
			int id =(int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
			if(id==message.getSenderId()){
				if(id==message.getRecipientId()){
					request.setAttribute("state", -1);
					String recipientName = empService.queryById(message.getSenderId());
					request.setAttribute("recipientName", recipientName);
				}else{
					request.setAttribute("state", 1);
				}
			}else{
				request.setAttribute("state", 0);
				String recipientName = empService.queryById(message.getSenderId());
				request.setAttribute("recipientName", recipientName);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// ����ѡ���ռ���
	@Action(value = "toSelectReceiver", results = { @Result(name = "success", location = "/pages/Message/selectReceiver.jsp", type = "dispatcher") })
	public String toSelectReceiver() {
		try{
			Pager pager = new Pager(empService.getCount(), 11, pageNum);
			List<Map<String, Object>> list = empService.queryEmployee(pager);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	// ��ȡԱ������
	@Action(value = "getEmpName")
	public String getEmpName() throws IOException {
		try{
			response.setContentType("text/html;charset=utf-8");
			String name = "";
			PrintWriter out = response.getWriter();
			try {
				name = empService.queryById(message.getRecipientId());
			} catch (Exception e) {
				out.print(0);
				return null;
			}
			out.write(name);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
