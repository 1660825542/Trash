package com.lists;

public class Base2Class {
	public static void main(String[] args) {
		char c='a';
//		①、将基本类型完成了一个封箱操作，即将基本类型转化成了引用类型，因为Character是一个class是一个final class
		Character obj=new Character(c);
		System.out.println(obj);
		
		
		Integer a=new Integer(3);
		System.out.println(a);
		
		Integer a1=new Integer("4");
		System.out.println(a1);
		
//		②、JVM会自动将a1这个引用类型转化成基本类型（拆箱）后+2；
		System.out.println(a1+2);
		
		
		Float f=new Float(3.14F);
		System.out.println(f);
	
		System.out.println(f+1);
		
//		③、String类型和基本类型之间的转化，看目标类型是什么就用什么类型中的方法进行转化
		
		String s="5";
		System.out.println(Integer.parseInt(s)+1);//将Integer.parseInt(s) 将String转成了int
		
		
		int a2=210;
		System.out.println(String.valueOf(a2)+999999);//String.valueOf(a2) 将int转化成了String
		
	}
}
