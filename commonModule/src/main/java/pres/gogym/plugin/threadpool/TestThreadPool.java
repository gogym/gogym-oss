/*
 * 文件名：TestThreadPool.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2018年6月11日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.plugin.threadpool;

import java.util.Arrays;

public class TestThreadPool {
	public static void main(String[] args) {
		// 创建3个线程的线程池
		// ThreadPool t = ThreadPool.getThreadPool(3);
		// t.execute(new Runnable[] {new Task(), new Task(), new Task()});
		// t.execute(new Runnable[] {new Task(), new Task(), new Task()});
		// System.out.println(t);
		// t.destroy();// 所有线程都执行完成才destory
		// System.out.println(t);

		ThreadPool t = new ThreadPool(2,1);
		t.execute(Arrays.asList(new Task(), new Task(), new Task()));
		
		t.shutDown();
		
		System.out.println(t.isShutDown());
	}

	// 任务类
	static class Task implements Runnable {
		private static volatile int i = 1;

		@Override
		public void run() {// 执行任务
			System.out.println("任务 " + (i++) + " 完成");
		}
	}
}
