package com.lists;

public class IContainerImpl<E> implements IContainer<E> {
	private E e;
	public IContainerImpl(E e){
		this.e=e;
	}
	public E getInfo(){
		return e;
	}
}
