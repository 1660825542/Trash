package com.icss.oa.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.app.dao.ContactsDao;

@Service
@Transactional(rollbackFor=Exception.class)
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactsDao contactsDao;

	@Override
	public List<Map<String, String>> getContacts() {
		return contactsDao.queryContacts();
	}

}
