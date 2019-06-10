package com.icss.oa.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * �Զ����ǩ
 * @author Administrator
 *
 */
public class MyEncodeTag2 extends TagSupport {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doEndTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		
		try {
			//��JSP�����ת��֮�������
			String s = URLEncoder.encode(value, "utf-8");
			s = URLEncoder.encode(s, "utf-8");
			
			out.write(s);
		} catch (Exception e) {			
			e.printStackTrace();
		}

		// ����ִֵ�б�ǩ֮�����ִ��JSP
		return EVAL_PAGE;
	}

}