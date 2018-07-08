package com.predicate.blockingQueue.instance;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

	private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

	private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public BlockingQueueDemo() {
		try {
			blockingQueue.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void execute(){
		for(int i=0;i<10000;i++){
			threadPool.execute(new Producer(i));
			threadPool.execute(new Customer());
		}
		threadPool.shutdown();
	}
	static class Producer implements Runnable {

		Integer num=0;
		 public Producer(Integer num) {
			 this.num=num;
		}
		@Override
		public void run() {

			try {
				TimeUnit.MILLISECONDS.sleep(20);
				   System.out.println(" 生产数据 :  "+num);
					blockingQueue.put(num);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	static class Customer implements Runnable {

		@Override
		public void run() {
			Integer integer;
			try {
				TimeUnit.MILLISECONDS.sleep(20);
				integer = blockingQueue.take();
				System.out.println(" 从队列取出数据:  "+integer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
		}
	}
}
