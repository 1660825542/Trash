package com.icss.oa.message.index;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPool {
	private ExecutorService pool = null;
	private static ThreadPool threadPool = null;
	private ThreadPool(){
		this.pool = Executors.newSingleThreadExecutor();
		//单个线程的线程池，其中是无边界的队列
	}
//	public static ThreadPool getThreadPool(){
//		if(threadPool == null){
//			synchronized (ThreadPool.class){
//				threadPool = new ThreadPool();
//			}
//		}
//		return threadPool;
//	}
	//直接把同步放在方法上也可以，另一种方法是上面的同步之后再加一个if，否则就是假单例了
//	public synchronized static ThreadPool getThreadPool() {
//		if (threadPool == null) {
//				threadPool = new ThreadPool();
//		}
//		return threadPool;
//	}
	public static ThreadPool getThreadPool(){
		if(threadPool == null){
			synchronized (ThreadPool.class){
				if(threadPool == null){
					threadPool = new ThreadPool();
				}
			}
		}
		return threadPool;
	}
	public ExecutorService getPool(){
		return this.pool;
	}
}
