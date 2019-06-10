



















































































## 总结

### 1、变量、数据类型

数据类型  变量名=初始值;

数据类型：

A：基本类型：byte  short  int  long   float  double  char  boolean

B：类   接口    数组  枚举

## 

###  2、控制语句

条件语句：if ...else   switch ....case

循环语句：for  while   do...while

break   continue

### 3、类

类是抽象的，和对象刚好相反，对象是具象的，而类是抽象的。

public class  Car{}

Car  car=new  Car();

car.run();	对

car=null;

car.run();  	 错



### 4、类中可以有哪些代码

> ①、变量
>
> ②、方法
>
> ③、语句块  |   静态语句块  （语句块：每实例化一次对象，运行一次；而静态块实例化多次只运行一次）
>
> ④、内部类



### 5、构造方法，常用的重载形式之一（构造器

















































































































































### 有多个）

具有继承关系的类

































































































中的构造方法运行特点？？？？？

### 6、this|super





















































































































































































































































两种用法：

this.|super.		:调用其它的成员		不用能在static修饰的方法中

this()|super();	:调用本类或者是父类中的指定构造方法，是构造方法第一条语句







~~~  java
package com.corejava;

/**
 * FileName		:com.corejava 	Car.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年2月12日:上午9:00:42
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年2月12日 	Rock 		1.0 		1.0 Version
 * 
 
 
 
 
 
 
 
 
 
 
 
 
 */
public class Car {
	
	private String brand;
	private int speed;
	
	public Car(){
        
        
        
        
        
//		this("AUDI");
		System.out.println("Car.Car()");
	}
	
	public Car(String brand){
		System.out.println("Car.Car(String brand)");
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	public void run(){
		System.out.println("Car.run()");
	}
	public void run(int a){
		
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	}
	public  void go(){
		this.run();
		System.out.println("Car.go()");
	}
	public static void main(String[] args) {
//		new Car("BYD");
//		new Car();
		new Car().go();
	}
}

~~~



### 7、重载，同名异式

​	不包括返回值

### 8、类的继承  extends

### 9、重写方法   

在子类中定义的和父类中一样（参数和返回值）的方法，其中访问修饰符只能变大不能缩小

### 10、访问修饰符

public  protected  private  (default)

~~~ java
package com.corejava;

import javax.swing.plaf.synth.SynthToggleButtonUI;

/**
 * FileName		:com.corejava 	Person.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年2月12日:上午9:07:36
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年2月12日 	Rock 		1.0 		1.0 Version
 * 
 */
public class Person {
	private String name;
	private int age;
	public void think(){
		System.out.println("Person.think()");
	}
	protected  int run() {
		System.out.println("Person.run()");
		return 10;
	}
	private void go(){
		System.out.println("Person.go()");
	}
}


package com.corejava;

/**
 * FileName		:com.corejava 	Solder.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年2月12日:上午9:08:11
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年2月12日 	Rock 		1.0 		1.0 Version
 * 
 */
public class Solder  extends  Person{
	public  int  run() {
		System.out.println("Solder.run()");
		return 10;
	}
	
	
	public void think(){
		System.out.println("Solder.think()");
	} 
}

~~~



### 11、类高级特性

> ①、static：static只能修饰变量和方法，若一个变量用static修饰，这个变量被当前类的对个对象共享；static修饰方法，类方法，类方法只能直接访问类型成员不能直接方法实例成员。类方法中不能使用this|super。因为类方法的调用实际上无需创建对象，而是通过类名.方法名()直接调用
>
> ②、abstract：抽象的，abstract可以修饰方法和类，不能修饰变量。修饰类：抽象类，不能实例化的话，只能被继承。修饰方法叫抽象方法，抽象方法没有方法体，只有方法的声明，抽象方法所在的类必须是抽象类，但是抽象类中可以有非抽象方法。抽象方法的方法体由子类去实现，若子类也没有能力实现父类中的抽象方法，那么需要将子类也定义为抽象类
>
> ③、interface：接口，接口是一个极其特殊的引用类型，其中所定义的所有变量都默认是静态常量，接口中所定义的方法都默认是抽象方法。接口是不能实例化的，接口中的所有抽象方法由其实现类实现。若实现类也没有能力实现接口中的所有方法体，实现类可以定义为抽象的。接口I可以继承接口，同时可以继承多个接口，一个类可以实现多个接口。（接口是面向对象的基础，是解决类之间的耦合度的最佳方案）
>
> ④、final：最终的，修饰变量：常量。一旦初始化后，其值不能发生改变。final修饰方法，这个方法是最终方法，不能被重写。final修饰类，最终类，最终类不能被继承
>
> ​	常量：一般都定义为静态常量，一般都是由大写字母定义
>
> ​	public final static float PI=3.14F;
>
> ⑤、封装类，8个基本类型对应8个封装类   float-->Float   int-->Integer。封箱和拆箱
>
> ​	Float  f=new Float(3.14F);
>
> ​	经常性的将基本类型和String字符串类型之间完成转化。。。。



### 12、java中的异常处理机制

​	异常：程序在运行期间所发生的不可预知的错误，叫异常（bug）。JVM默认的处理方法是终止程序的执行。所以提供了异常处理机制，采用了抓抛模型，即try...catch...finally实现。注意：一个try可以跟多个catch，但是多个catch的异常类型的辈分要逐渐变大，而不能变小。

error：

Exception：

> ①、运行时异常：在运行期间发生的异常，比如除数为零异常，空指针异常等
>
> ②、编译时异常：在编译期间发生的异常，eclipse会提示，编译时异常在程序运行之前必须通过异常处理机制去处理他，否则程序无法运行。同时程序的异常处理时机最好是在编译期间处理。IO方面的异常

try   catch  finally   throws  throw

在调用一个方法时，若这个方法时在运行期间发生了异常，那么JVM会将这个异常传递给调用者。

若某个方法没有能力处理这个异常，可以指定抛出这个异常，通过throws关键抛出异常。

throw：定义抛出异常

自定义异常：定义类继承类库中的任何一个异常类即可。



~~~ java
public class H  extends Exception{
	private String error;
	public H(String error){
		this.error=error;
	}
	public void printError(){
		System.out.println(error);
	}
}


public class HTest {
	public void testMyException(){
		System.out.println(1);
		try {
			throw new H("发生了自定义异常H类型的异常");
		} catch (H e) {
			e.printError();
		}
		System.out.println(2);
	}
	public static void main(String[] args) {
		HTest t=new HTest();
		t.testMyException();
	}
}


public class HTest {
	public void testMyException(){
		System.out.println(1);
		try {
			throw new H("发生了自定义异常H类型的异常");
		} 
		catch(Exception ex){
			System.out.println(ex);
		}
		catch (H e) {
			e.printError();
		}
		System.out.println(2);
	}
	public static void main(String[] args) {
		HTest t=new HTest();
		t.testMyException();
	}
}
~~~



### 13、集合框架

数组：对多个相同类型数据的统一管理控制。

特点：数组长度不能发生改变（固定长度）

泛型：<T>   <E>

泛型类：		public class Container<E>{}

泛型接口：	public interface Container<E>{}

泛型方法：	public  <E>void run(){}

注意泛型有界：extends       	<E extends Person>

(Collection | Map)

Set	:	HashSet    LikedHashSet   TreeSet

List	:	ArrayList   LinkedList  Stack   Vector

Map:	HashMap  LinkedHashMap  Properties

### 14、JavaBean

封装一条实体的信息

~~~~ kava

package com.corejava2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * FileName		:com.corejava2 	Root.java
 * TODO			:入口I类
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年2月12日:下午2:24:11
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年2月12日 	Rock 		1.0 		1.0 Version
 * 
 */
public class Root {
	
	List<Employee> emps=new ArrayList<Employee>();
	{
		emps.add(new Employee("1001","刘备", "liubei@163.com", "15641103831", 10, "F"));
		emps.add(new Employee("1002","关羽", "guanyu@163.com", "15641103832", 30, "F"));
		emps.add(new Employee("1003","张飞", "zhangfei@163.com", "15641103833", 20, "M"));
		emps.add(new Employee("1004","赵云", "zhaoyun@163.com", "15641103834", 10, "F"));
		emps.add(new Employee("1005","貂蝉", "diaocan@163.com", "15641103835", 10, "M"));
	}
	
	public static void main(String[] args) {
		Root root=new Root();
//		root.writeEmployees();
//		root.printEmployee();
		root.printMenu();
	}

	public void printMenu(){
		System.out.println("1、录入员工信息");
		System.out.println("2、打印员工信息");
		System.out.println("3、删除员工信息");
		System.out.println("4、修改员工电话");
		Scanner in=new Scanner(System.in);
		int type=in.nextInt();
		if(type==1){
			writeEmployees();
		}
		else if(type==2){
			printEmployee();
		}
		else if(type==3){
			removeEmployee();
		}
		else if(type==4){
			updateMobile();
		}
	}
	/**
	 * @TODO	 :修改电话号码
	 * @Date	 :2019年2月12日 下午3:02:30
	 * @Author	 :Rock   :
	 */
	public void updateMobile(){
		Scanner in=new Scanner(System.in);
		System.out.println("请输入要修改号码的员工编号");
		String empno=in.next();
		
		System.out.println("请输入要修改号码的电话号码");
		String emobile=in.next();
		
		boolean flag=doUpdateMobile(empno, emobile);
		if(flag){
			printEmployee();
		}
		else{
			System.out.println("电话修改失败");
			printMenu();
		}
		
	}
	/**
	 * @TODO	 :完成电话的修改
	 * @Date	 :2019年2月12日 下午3:04:30
	 * @Author	 :Rock
	 * @param empno
	 * @param emobile
	 * @return   :
	 */
	private boolean doUpdateMobile(String empno,String emobile){
//		定位当前编号对应的员工在集合List中的索引位置
		int  index=searchEmployeeIndex(empno);
//		根据这个索引位置获取到这个员工对象
		Employee e=emps.get(index);
		e.setEmobile(emobile);
		
		emps.set(index, e);
		return true;
	}
	/**
	 * @TODO	 :删除员工信息
	 * @Date	 :2019年2月12日 下午3:02:22
	 * @Author	 :Rock   :
	 */
	public void removeEmployee(){
		Scanner in=new Scanner(System.in);
		System.out.println("请输入要删除的员工编号");
		String empno=in.next();
		int index=searchEmployeeIndex(empno);
		emps.remove(index);
		printEmployee();
		
	}
	/**
	 * @TODO	 :根据员工的编号获取员工在集合中的索引位置
	 * @Date	 :2019年2月12日 下午2:46:16
	 * @Author	 :Rock
	 * @param empno
	 * @return   :
	 */
	private int searchEmployeeIndex(String empno){
		int index=-1;
		for(int i=0;i<emps.size();i++){//遍历List集合   根据索引位置遍历集合List
			Employee emp=emps.get(i);//指定位置上的Employee对象
			if(emp.getEmpno().equals(empno)){
				index=i;
				break;
			}
		}
		return index;
	}
	
	
	/**
	 * @TODO	 :打印所有员工的信息到控制台
	 * @Date	 :2019年2月12日 下午2:38:03
	 * @Author	 :Rock   
	 */
	public void printEmployee(){
		if(emps.size()==0){
			System.out.println("---------------暂时没有员工信息，请输入1号菜单录入员工信息--------------");
		}
		for(Employee emp:emps){
			System.out.println(emp);
		}
		printMenu();
	}
	/**
	 * @TODO	 :录入员工信息
	 * @Date	 :2019年2月12日 下午2:37:40
	 * @Author	 :Rock   
	 */
	public void writeEmployees(){
		Scanner in=new Scanner(System.in);
//		从控制台录入多名员工信息   规定若输入的姓名是exit的话，就退出录入
		while(true){
			System.out.println("请输入员工姓名，若退出录入，可以输入exit");
			String ename=in.next();
			if("exit".equals(ename)){
				break;
			}
			System.out.println("请输入员工的编号");
			String empno=in.next();
			
			System.out.println("请输入员工的邮箱");
			String email=in.next();
			
			System.out.println("请输入员工的电话");
			String emobile=in.next();
			
			System.out.println("请输入员工的所在部门编号");
			int deptno=in.nextInt();
			
			System.out.println("请输入员工的性别");
			String sex=in.next();
			
			Employee employee=new Employee(empno, ename, email, emobile, deptno, sex);
			emps.add(employee);
		}
		printMenu();
	}
}

~~~~





Map的CURD操作

~~~ java
package com.corejava3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.corejava2.Employee;

/**
 * FileName		:com.corejava2 	Root.java
 * TODO			:入口I类
 * Copyright	:Copyright (c) 2015-2016 maps Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年2月12日:下午2:24:11
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年2月12日 	Rock 		1.0 		1.0 Version
 * 
 */
public class Root {
//	String:员工的编号
//	Employee：当前编号对应的那个实体bean
	Map<String,Employee> maps=new HashMap<String, Employee>();
	
//	语句块初始化Map集合
	{
		maps.put("1001", new Employee("1001","刘备", "liubei@163.com", "15641103831", 10, "F"));
		maps.put("1002", new Employee("1002","关羽", "guanyu@163.com", "15641103832", 30, "F"));
		maps.put("1003", new Employee("1003","张飞", "zhangfei@163.com", "15641103833", 20, "M"));
		maps.put("1004", new Employee("1004","赵云", "zhaoyun@163.com", "15641103834", 10, "F"));
		maps.put("1005", new Employee("1005","貂蝉", "diaocan@163.com", "15641103835", 10, "M"));
	}
	
	public static void main(String[] args) {
		Root root=new Root();
//		root.writeEmployees();
//		root.printEmployee();
		root.printMenu();
	}

	/**
	 * @TODO	 :功能菜单的显示
	 * @Date	 :2019年2月12日 下午3:13:10
	 * @Author	 :Rock   :
	 */
	public void printMenu(){
		System.out.println("1、录入员工信息");
		System.out.println("2、打印员工信息");
		System.out.println("3、删除员工信息");
		System.out.println("4、修改员工电话");
		Scanner in=new Scanner(System.in);
		int type=in.nextInt();
		if(type==1){
			writeEmployees();
		}
		else if(type==2){
			printEmployee();
		}
		else if(type==3){
			removeEmployee();
		}
		else if(type==4){
			updateMobile();
		}
	}
	/**
	 * @TODO	 :修改电话号码
	 * @Date	 :2019年2月12日 下午3:02:30
	 * @Author	 :Rock   :
	 */
	public void updateMobile(){
		Scanner in=new Scanner(System.in);
		System.out.println("请输入要修改号码的员工编号");
		String empno=in.next();
		
		System.out.println("请输入要修改号码的电话号码");
		String emobile=in.next();
		
		boolean flag=doUpdateMobile(empno, emobile);
		if(flag){
			printEmployee();
		}
		else{
			System.out.println("电话修改失败");
			printMenu();
		}
	}
	/**
	 * @TODO	 :完成电话的修改
	 * @Date	 :2019年2月12日 下午3:04:30
	 * @Author	 :Rock
	 * @param empno
	 * @param emobile
	 * @return   :
	 */
	private boolean doUpdateMobile(String empno,String emobile){
//		定位当前编号对应的员工在集合List中的索引位置
//		int  index=searchEmployeeIndex(empno);
//		根据这个索引位置获取到这个员工对象
		
//		获取要更新数据的那个bean对象
		Employee e=maps.get(empno);
		e.setEmobile(emobile);
//		emps.set(index, e);
		return true;
	}
	/**
	 * @TODO	 :删除员工信息
	 * @Date	 :2019年2月12日 下午3:02:22
	 * @Author	 :Rock   :
	 */
	public void removeEmployee(){
		Scanner in=new Scanner(System.in);
		System.out.println("请输入要删除的员工编号");
		String empno=in.next();
		maps.remove(empno);
		printEmployee();
		
	}
	
	/**
	 * @TODO	 :打印所有员工的信息到控制台
	 * @Date	 :2019年2月12日 下午2:38:03
	 * @Author	 :Rock   
	 */
	public void printEmployee(){
		/*if(emps.size()==0){
			System.out.println("---------------暂时没有员工信息，请输入1号菜单录入员工信息--------------");
		}
		for(Employee emp:emps){
			System.out.println(emp);
		}
		printMenu();*/
		
		if(maps.isEmpty())
			System.out.println("---------------暂时没有员工信息，请输入1号菜单录入员工信息--------------");
//		怎样遍历map集合
//		①、
//		Set<String> keys=maps.keySet();
//		for(String key:keys){
//			System.out.println(maps.get(key));
//		}
//		②、
		Set<Map.Entry<String,Employee>> ss=maps.entrySet();
		for(Map.Entry<String,Employee> s:ss)
			System.out.println(s.getKey()+"----->"+s.getValue());
		printMenu();
	}
	/**
	 * @TODO	 :录入员工信息
	 * @Date	 :2019年2月12日 下午2:37:40
	 * @Author	 :Rock   
	 */
	public void writeEmployees(){
		Scanner in=new Scanner(System.in);
//		从控制台录入多名员工信息   规定若输入的姓名是exit的话，就退出录入
		while(true){
			System.out.println("请输入员工姓名，若退出录入，可以输入exit");
			String ename=in.next();
			if("exit".equals(ename)){
				break;
			}
			System.out.println("请输入员工的编号");
			String empno=in.next();
			
			System.out.println("请输入员工的邮箱");
			String email=in.next();
			
			System.out.println("请输入员工的电话");
			String emobile=in.next();
			
			System.out.println("请输入员工的所在部门编号");
			int deptno=in.nextInt();
			
			System.out.println("请输入员工的性别");
			String sex=in.next();
			
			Employee employee=new Employee(empno, ename, email, emobile, deptno, sex);
//			emps.add(employee);
			maps.put(empno, employee);
		}
		printMenu();
	}
}

~~~

### 15、IO

IO:
1、分类：
①、输入流和输出流
②、字节流和字符流
③、节点流和处理流
2、四个基类
InputStream	：字节输入流
OutputStream	：字节输出流
Reader		：字符输入流
Writer		：字符输出流
3、根据文件操作的四个类
FileInputStream		：字节输入流（文件读取）	
FileOutputStream	：字节输出流（文件写入）
FileReader		：字符输入流（文件读入）
FileWriter		：字符输出流（文件写入）
4、转化流：
InputStreamReader	:将字节输入流转化成字符输入流
OutputStreamReader	:将字节输出流转化成字符输出流
5、序列化流
ObjectInputStream  	:反序列化（将保存在硬盘上的对象读取出来）
ObjectOutputStream	:序列化（将对象保存在硬盘上）
6、随机访问流
RandomAccessFile	：可以指定光标的位置，然后从指定位置开始读写操作
7、三个read方法以及三个writer方法要搞懂
int read();
int read(byte []bs)
int read(byte []bs,int offset,int len)

write(int a)
write(byte []bs)
write(byte []bs,int offset,int len)

