package concurrent.t04;

import java.util.LinkedList;

public class TestContainer03<E> {
	
	private final LinkedList<E> list = new LinkedList<E>();
	private final int MAX = 10;
	private int count = 0;
	
	public synchronized int getCount() {
		return count;
	}
	
	public synchronized void put(E e) {
		while(count == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		list.add(e);
		count++;
		this.notifyAll();
	}
	
	public synchronized E get() {
		while(count ==0) {
			try {
				this.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		E e = list.removeFirst();
		count--;
		this.notifyAll();
		return e;
	}
	
	public static void main(String[] args) {
		final TestContainer03<String> c = new TestContainer03<String>();
		
		for(int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j = 0; j < 5; j++){
						System.out.println("GET: " + c.get());
					}
				}
				
			}, "consumer" + i).start();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j = 0; j < 25; j++){
						System.out.println("PUT:" + " value" + j);
						c.put("value" + j);
					}
				}
				
			}, "producer" + i).start();
		}
	}

}
