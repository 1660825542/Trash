package com.rock.test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.rock.dao.CarDao;
import com.rock.pojo.Car;

public class CarTest {
	private CarDao carDao=null; 
	@Before
	public void init(){
		try {
//			创建SqlSession对象，用于从其中获取dao对象
			SqlSession sqlSession=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml")).openSession(true);
//			获取CarDao对象
			carDao=sqlSession.getMapper(CarDao.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryByLike(){
		Car car=new Car();
		car.setBrand("M");
		System.out.println(carDao.queryByLike(car));
	}
	
	@Test
	public void testGetCarByIdToMap(){
		Map<String,Object> maps=carDao.getCarByIdToMap("c001");
		for(String key:maps.keySet()){
			System.out.println(key+":"+maps.get(key));
		}
	}
	@Test
	public void testQueryAllCar(){
		System.out.println(carDao.queryAllCar());
	}
	
	
	@Test
	public void testQueryByCarId(){
		System.out.println(carDao.queryByCarId("c001"));
	}
	@Test
	public void testUpdateCar(){
		Car car=new Car("c002","AMW","500000",300,new Date());
		System.out.println(carDao.updateCar(car));
	}
	
	
	@Test
	public void testDeleteCarByCar(){
		System.out.println(carDao.deleteCarByCarId("c001"));
	}
	
	@Test
	public void testDeleteCarByCarId(){
		System.out.println(carDao.deleteCarByCarId("c001"));
	}
	@Test
	public void testSaveCar(){
		for(int i=2;i<=10000;i++){
			Car  car=new Car("c00"+i,"Audi",""+(300000+i),260,new Date());
			carDao.saveCar(car);
		}
	}
	
	
}
