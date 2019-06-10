package com.icss.oa.message.index;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPool {
	private ExecutorService pool = null;
	private static ThreadPool threadPool = null;
	private ThreadPool(){
		this.pool = Executors.newSingleThreadExecutor();
		//�����̵߳��̳߳أ��������ޱ߽�Ķ���
	}
//	public static ThreadPool getThreadPool(){
//		if(threadPool == null){
//			synchronized (ThreadPool.class){
//				threadPool = new ThreadPool();
//			}
//		}
//		return threadPool;
//	}
	//ֱ�Ӱ�ͬ�����ڷ�����Ҳ���ԣ���һ�ַ����������ͬ��֮���ټ�һ��if��������Ǽٵ�����
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
