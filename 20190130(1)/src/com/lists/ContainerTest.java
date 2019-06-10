package com.lists;

public class ContainerTest {
	public static void main(String[] args) {
		Container<String> con1=new Container<String>("Hello  刘备");
		con1.print();
		
		Container<Person> con2=new Container<Person>(new Person("关羽"));
		con2.print();
		
	}
}
