package com.rock.dao;


import com.rock.pojo.Person;

public interface PersonDao {
	public Person queryByid(String personid);
	public int saveSerson(Person person);
	public Person queryByidUseResultMap(String personid);
	
	public Person queryByid1(String personid);
	
	public Person queryByIdWithCar(String personid);
	
}
