package com.rock.dao;

import java.util.List;
import java.util.Map;

import com.rock.pojo.Car;

public interface CarDao {
	public int saveCar(Car car);
	public int deleteCarByCarId(String carid);
	public int deleteCarByCar(Car car);
	public int updateCar(Car car);
	public Car queryByCarId(String carid);
	public List<Car> queryAllCar();
	/**
	 * ģ������
	 * @param like
	 * @return
	 */
	public List<Car> queryByLike(Car like);
	
	/**
	 * ע�⣺Mybatis�Ὣ���е�������Ϊ���ԣ��Լ������е�ֵ��Ϊvalue����Map������
	 * @param carid
	 * @return
	 */
	public Map<String,Object> getCarByIdToMap(String carid); 
}
