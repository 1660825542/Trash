package com.lists;

//泛型类：
public class Container<E> {
	private E e;
	public Container(E e){
		this.e=e;
	}
	public void print(){
		System.out.println(e);
	}
}
