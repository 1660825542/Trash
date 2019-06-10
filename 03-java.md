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



~~~ java
package com.corejava;

import java.util.Comparator;

/**
 * FileName		:com.corejava 	UserComparator.java
 * TODO			:自定义比较器
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年1月31日:下午12:53:37
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年1月31日 	Rock 		1.0 		1.0 Version
 * 
 */
public class UserComparator<T> implements Comparator<T> {
	/**
	 * @TODO	 :重写Comparator中的compare方法，用于约定比较依据（比较标准）
	 * @Date	 :2019年1月31日 下午12:55:56
	 * @Author	 :Rock
	 * @param o1
	 * @param o2
	 * @return   :
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(T o1, T o2) {
		User user1=(User)o1;
		User user2=(User)o2;
		if(user1.getAge()>user2.getAge())
			return 1;
		else if(user1.getAge()==user2.getAge())
			return 0;
		else return -1;
	}
}


package com.corejava;

import java.util.Iterator;
import java.util.TreeSet;

public class ComparatorMain {
	public static void main(String[] args) {
//		UserComparator<User> u=new UserComparator<>();
//		TreeSet(Comparator<? super E> comparator) 
//		在创建TreeSet时指定具体的比较器对象，JVM会根据你重写compare方法的返回值确定两个对象的大小
		TreeSet<User> all=new TreeSet<>(new UserComparator<User>());
		all.add(new User("刘备1",30));
		all.add(new User("关羽2",50));
		all.add(new User("张飞3",40));
		all.add(new User("赵云4",10));
		Iterator<User> it=all.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
	}
}


~~~



TreeSetDemo.java

~~~ java
package com.corejava;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo {
	public static void main(String[] args) {
		TreeSet<String> ts=new TreeSet<>();
		TreeSet<User> ts1=new TreeSet<>();
		ts.add("C");
		ts.add("A");
		ts.add("D");
		ts.add("B");
		
		Iterator<String> it=ts.iterator();
		for(;it.hasNext();){
			System.out.println();
		}
		
	}
}

~~~



~~~ java

package com.corejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FileName		:com.corejava 	ABC.java
 * TODO			:Collection<? extends E>约定了凡是E类型或者E的子类型都是可以的。
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年1月31日:下午1:33:33
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年1月31日 	Rock 		1.0 		1.0 Version
 * 
 */
public class ABC<E> {
	
	public void add(Collection<? extends E> list){
		System.out.println("ABC.add()");
	}
	
	
	public static void main(String[] args) {
		ABC<User> users=new ABC<>();
		
		ArrayList<User> a1=new ArrayList<>();
		List<User> a2=new ArrayList<>();
		List<U> a3=new ArrayList<U>();
		List<A> a4=new ArrayList<A>();
		
		users.add(a1);
		users.add(a2);
		users.add(a3);//√因为U继承于User
//		users.add(a4);//×因为A没有继承于User
	}
}

~~~



~~~ java

package com.corejava;

import java.util.Arrays;
import java.util.List;

/**
 * FileName		:com.corejava 	ArraysDemo.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年1月31日:下午1:49:02
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年1月31日 	Rock 		1.0 		1.0 Version
 * 
 */
public class ArraysDemo {
	public static void main(String[] args) {
//		注意 ：Arrays.asList方法返回的List集合是固定长度的集合，也就是说不能向其中添加其他元素了。
//		分析下:因为asList方法的参数本身就是可变参数，所以返回list是固定长度
		List<String> lists=Arrays.asList("刘备","关羽","张飞","赵云");
//		lists.add("曹操");//×因为这里的lists是固定长度的。
		for(String s:lists)
			System.out.println(s);
	}
}

~~~

Map



~~~ java

package com.corejava;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * FileName		:com.corejava 	MapDemo.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年1月31日:下午2:14:40
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年1月31日 	Rock 		1.0 		1.0 Version
 * 
 */
public class MapDemo {
	public static void main(String[] args) {
//		利用HashMap创建Map集合
		Map<String,String> all=new HashMap<String,String>();
//		put：向Map中添加元素
		all.put("a","AAAAAAA");
		all.put("b","BBBBBBB");
		all.put("c","CCCCCCC");
		all.put("d","DDDDDDD");
//		将所有元素从map中移除
//		all.clear();
//		get方法：是基于key获取value
		String v=all.get("c");
		System.out.println(v);
//		containsKey:判断map中是否包含实参这个key，包含返回true  不包含返回false
		System.out.println(all.containsKey("c"));
//		containsValue:判断map中是否包含实参这个value，包含返回true  不包含返回false
		System.out.println(all.containsValue("CCCCdddddCC"));
//		isEmpty：map是否是空的
		System.out.println(all.isEmpty());
		
		System.out.println(all.get("b"));
//		从map中移除指定的key以及key对应的value
		System.out.println(all.remove("b"));
		System.out.println(all.get("b"));
		
//		size:返回map中元素的个数（映射关系的个数）
		System.out.println(all.size());
		
		System.out.println("----------------------------------------------");
//		keySet:返回了map中的所有key，并将这个key封装到Set集合中
//		values:返回map中的所有value，并封装到Collection集合中
		Collection<String> values=all.values();
		for(String value:values)
			System.out.println(value);
		
		Map<String,String> sub=new HashMap<String,String>();
		sub.put("e","EEEEEEEEEEEEEEEE");
		sub.put("f","FFFFFFFFFFF");
//		putAll将一个map中的元素添加到另一个map中
		all.putAll(sub);
		
		
		System.out.println("----------------------------------------------");
//		①、对map集合元素的遍历
		Set<String> keys=all.keySet();
		
		for(String key:keys)
			System.out.println(key+"::::::"+all.get(key));
		
		System.out.println("----------------------------------------------");
//		②、对map集合的遍历  Set<X> all;
		Set<Map.Entry<String,String>> eles=all.entrySet();
//		Map.Entry<String,String>:特殊的接口，其中包含了getKey和getValue方法，简化对Map元素的获取
		for(Map.Entry<String,String> ele:eles){
			System.out.println(ele.getKey()+"="+ele.getValue());
		}
	}
}

~~~

Map

~~~ java
package com.corejava;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * FileName		:com.corejava 	MapDemo.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年1月31日:下午2:14:40
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年1月31日 	Rock 		1.0 		1.0 Version
 * 
 */
public class MapDemo {
	public static void main(String[] args) {
//		利用HashMap创建Map集合
		Map<String,String> all=new HashMap<String,String>();
//		put：向Map中添加元素
		all.put("a","AAAAAAA");
		all.put("b","BBBBBBB");
		all.put("c","CCCCCCC");
		all.put("d","DDDDDDD");
//		将所有元素从map中移除
//		all.clear();
//		get方法：是基于key获取value
		String v=all.get("c");
		System.out.println(v);
//		containsKey:判断map中是否包含实参这个key，包含返回true  不包含返回false
		System.out.println(all.containsKey("c"));
//		containsValue:判断map中是否包含实参这个value，包含返回true  不包含返回false
		System.out.println(all.containsValue("CCCCdddddCC"));
//		isEmpty：map是否是空的
		System.out.println(all.isEmpty());
		
		System.out.println(all.get("b"));
//		从map中移除指定的key以及key对应的value
		System.out.println(all.remove("b"));
		System.out.println(all.get("b"));
		
//		size:返回map中元素的个数（映射关系的个数）
		System.out.println(all.size());
		
		System.out.println("----------------------------------------------");
//		keySet:返回了map中的所有key，并将这个key封装到Set集合中
//		values:返回map中的所有value，并封装到Collection集合中
		Collection<String> values=all.values();
		for(String value:values)
			System.out.println(value);
		
		Map<String,String> sub=new HashMap<String,String>();
		sub.put("e","EEEEEEEEEEEEEEEE");
		sub.put("f","FFFFFFFFFFF");
//		putAll将一个map中的元素添加到另一个map中
		all.putAll(sub);
		
		
		System.out.println("----------------------------------------------");
//		①、对map集合元素的遍历
		Set<String> keys=all.keySet();
		
		for(String key:keys)
			System.out.println(key+"::::::"+all.get(key));
		
		System.out.println("----------------------------------------------");
//		②、对map集合的遍历  Set<X> all;
		Set<Map.Entry<String,String>> eles=all.entrySet();
//		Map.Entry<String,String>:特殊的接口，其中包含了getKey和getValue方法，简化对Map元素的获取
		for(Map.Entry<String,String> ele:eles){
			System.out.println(ele.getKey()+"="+ele.getValue());
		}
	}
}






package com.corejava;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * FileName		:com.corejava 	MapDemo1.java
 * TODO			:TreeMap会利用Key的自然排序方式对map集合中的所有元素进行排序存储
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年1月31日:下午2:44:33
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年1月31日 	Rock 		1.0 		1.0 Version
 * 
 */
public class MapDemo1 {
	public static void main(String[] args) {
		Map<String,User> all=new TreeMap<String,User>();
//		Map<String,User> all=new LinkedHashMap<String,User>();
		
		all.put("x", new User("CCCCCCC",30));
		all.put("a", new User("AAAAAAA",40));
		all.put("e", new User("EEEEEEE",50));
		all.put("b", new User("BBBBBBB",60));
		
		Set<Map.Entry<String,User>> maps=all.entrySet();
		for(Map.Entry<String,User> u:maps)
			System.out.println(u.getKey()+"----"+u.getValue());
		
	}
}

~~~



