package com.icss.oa.room.service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.room.dao.RoomDao;

import com.icss.oa.room.pojo.Room;





@Service
@Transactional(rollbackFor = Exception.class)
public class RoomService {
	@Autowired
	private RoomDao dao;
	//private RoomIndexDao indexDao;

	public void insert(Room room) throws IOException {
		
		dao.insert(room);
	}


	public void delete(Integer roomId) throws IOException {
		dao.delete(roomId);

	}
	
	
	public Room queryById(Integer roomId){
		return dao.queryById(roomId);
		
	}
	

	public List<Room> query(Pager pager) {
		List<Integer> list2=dao.query1();
		List<Room> list1 =dao.query(pager);
		for(Room room:list1){
			int state=0;
			for(Integer roomId:list2){
				
				if(roomId==room.getRoomId())
				state=1;
			}
			if(state==1)
				dao.update1(room.getRoomId());
			else
				dao.update0(room.getRoomId());
		}
		return dao.query(pager);
	}
	
	public List<Room> queryByCondition(Pager pager,String roomPlace,Short airCon,Short roomState, Integer accomNum){
		return dao.queryByCondition(pager, roomPlace, airCon, roomState, accomNum);
		
	}
	
	public List<Room> querByName(Pager pager, String roomName) {

		return dao.queryByName(pager, roomName);
	}
	
	public List<Room> querByPlace(Pager pager, String roomPlace) {

		return dao.queryByPlace(pager, roomPlace);
	}

	public List<Room> querByState(Pager pager, Short roomState) {

		return dao.queryByState(pager, roomState);
	}	
	public List<Room> querByAccomNum(Pager pager, Integer accomNum) {

		return dao.queryByAccomNum(pager, accomNum);
	}	
	public List<Room> querByAirCon(Pager pager, Short airCon) {

		return dao.queryByAirCon(pager, airCon);
	}
	
	public List<Integer> query1(){
		return dao.query1();
	}
	

	
	public int getConditionCount(String roomPlace,Short airCon,Short roomState, Integer accomNum){
		return dao.getConditionCount( roomPlace, airCon,roomState,accomNum);
	}
	
	public int getCount() {

		return dao.getCount();
	}

	public int getNameCount(String roomName) {

		return dao.getNameCount(roomName);
	}
	
	public int getPlaceCount(String roomPlace) {

		return dao.getPlaceCount(roomPlace);
	}

	public int getStateCount(Short roomState) {

		return dao.getStateCount(roomState);
	}
	
	public int getAccomNumCount(Integer accomNum) {

		return dao.getAccomNumCount(accomNum);
	}

	public int getAirConCount(Short airCon) {

		return dao.getAirConCount(airCon);
	}
	


}
