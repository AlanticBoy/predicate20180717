package com.predicate.blockingQueue.test;


import com.predicate.blockingQueue.instance.BlockingQueueInstance;

public class BlockQueueTest {

	/*public static void main(String[] args) {
		BlockingQueueDemo demo=new BlockingQueueDemo();
		demo.execute();
	}*/
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueueInstance instance=new BlockingQueueInstance();
		instance.execute();
	}
}
