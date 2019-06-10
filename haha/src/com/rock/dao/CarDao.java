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
	 * 模糊检索
	 * @param like
	 * @return
	 */
	public List<Car> queryByLike(Car like);
	
	/**
	 * 注意：Mybatis会将表中的列名作为可以，以及整个列的值作为value存于Map集合中
	 * @param carid
	 * @return
	 */
	public Map<String,Object> getCarByIdToMap(String carid); 
}
