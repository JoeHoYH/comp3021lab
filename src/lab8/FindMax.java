package lab8;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		//new FindMax().printMax();
		new FindMax().printMaxMultiThread();
	}

	public void printMax() {
		// this is a single threaded version
		int max = findMax(0, array.length - 1);
		System.out.println("the max value is " + max);
	}
	
	public void printMaxMultiThread() {
		MyTask m1 = new MyTask(0,29);
		Thread t1 = new Thread(m1);
		
		MyTask m2 = new MyTask(30,59);
		Thread t2 = new Thread(m2);
		
		MyTask m3 = new MyTask(60,89);
		Thread t3 = new Thread(m3);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		int o1 = m1.getOutput();
		int o2 = m2.getOutput();
		int o3 = m3.getOutput();
		
		if(o1>o2) {
			if(o1>o3) {
				System.out.println("the max value is " + o1);
			}else {
				System.out.println("the max value is " + o3);
			}
		}else {
			if(o2>o3) {
				System.out.println("the max value is " + o2);
			}else {
				System.out.println("the max value is " + o3);
			}
		}
		}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	class MyTask implements Runnable{
		private int begin;
		private int end;
		private int max;
		public MyTask(int begin, int end) {
			this.begin=begin;
			this.end=end;
		}
		@Override
		public void run() {
			max=findMax(begin,end);
		}
		public int getOutput() {
			return max;
		}
	}
}


