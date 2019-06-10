#  Java集合框架

1、数组：	int []as;    String[] as;   MyClass[] as;   

​	特点：组织管理多个数据；数组中只能存储相同类型的数据；数组是定长（长度不变）

2、集合框架：类（Class）  lll

public class Person(){}

3、前置知识：泛型：<E>，什么是泛型：泛指任何类型

①、泛型类	：

②、泛型接口 ：

③、泛型方法：若希望某个方法接收的参数是任何类型时|返回值是任何类型，使用泛型方法



4、封装类：将来需要将基本类型转化成引用类型时，需要用到封装类将基本类型数据进行封箱操作后即为引用类型

byte： Byte

short：Short

int   Integer

long：Long

float：Float

double：Double

boolean：Boolean

char：Character

5、Java中的集合框架（List，Set，Map）

​	①、Collection

​	②、Map

~~~ java
package com.lists;

import java.util.ArrayList;
import java.util.List;
//	Eclipse快捷键
//	导包：Ctrl+Shift+O
//	当行注释：Ctrl+/
//	删除当前行：Ctrl+D
//	代码提示：Alt+/
//	排除异常：Ctrl+1
//	选择：上下箭头
public class ListDemo {
	public static void main(String[] args) {
//		①、创建List集合对象
		List<String> ss=new ArrayList<>();
//		②、向List中添加元素   {"刘备","关羽","张飞","赵云","赵云"}
		ss.add("刘备");
		ss.add("关羽");
		ss.add("赵云");
		ss.add("张飞");
		ss.add("赵云");
//		返回List几种的元素的个数
		System.out.println(ss.size());
//		清除所有的元素
//		ss.clear();
		System.out.println(ss.size());
//		判断集合中是否包含了指定元素  有：true   没有：false
		boolean b=ss.contains("张飞6");
		System.out.println(b);
//		返回指定索引位置上的元素
		String s=ss.get(0);
		System.out.println(s);
//		返回指定元素的索引位置
		System.out.println(ss.indexOf("赵云"));
//		返回List是否是空的
		System.out.println(ss.isEmpty());
		System.out.println(ss.lastIndexOf("赵云"));
//		删除List中的指定索引的元素，并将这个元素返回
//		System.out.println(ss.remove(2));
		System.out.println("----------------------------------------------");
//		遍历List将其中的所有元素打印到控制台
		for(int i=0;i<ss.size();i++){
			System.out.println(ss.get(i));
		}
		System.out.println("----------------------------------------------");
//		将指定位置的元素跟新成新元素
//		System.out.println(ss.set(2, "大乔"));
		System.out.println("==================================");
		ss.add("貂蝉");
		List<String> subList=ss.subList(1, 4);///[1, 4)
		for(int i=0;i<subList.size();i++)
			System.out.println(subList.get(i));
		System.out.println("**********************************************");
        // 将List集合转化成一维数组
		Object[] objs=ss.toArray();
		for(Object obj:objs)
			System.out.println(obj);
		
		
	}
}




package com.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListDemo1 {
	public static void main(String[] args) {
//		List是接口，而ArrayList是其实现类
		List<User> users=new ArrayList<>();
		users.add(new User("刘备"));
		users.add(new User("孙尚香"));
		users.add(new User("荀彧"));
		users.add(new User("司马懿"));
		users.add(new User("三国杀"));
		
		Scanner in =new Scanner(System.in);
		while(true){
			System.out.println("请输入姓名");
			String name=in.next();
			if("exit".equals(name))//"exit".equals(name)  判断name的值和exit是否相等 不能用==比较
				break;
			User u=new User(name);
			users.add(u);
		}
		for(int i=0;i<users.size();i++){
			User user=users.get(i);
			System.out.println(user);
		}
		
	}
}

~~~

