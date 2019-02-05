/**
 * synchronizedå…³é”®å­—
 * é”�å¯¹è±¡å�˜æ›´é—®é¢˜
 * å�Œæ­¥ä»£ç �ä¸€æ—¦åŠ é”�å�Žï¼Œé‚£ä¹ˆä¼šæœ‰ä¸€ä¸ªä¸´æ—¶çš„é”�å¼•ç”¨æ‰§è¡Œé”�å¯¹è±¡ï¼Œå’ŒçœŸå®žçš„å¼•ç”¨æ— ç›´æŽ¥å…³è�”ã€‚
 * åœ¨é”�æœªé‡Šæ”¾ä¹‹å‰�ï¼Œä¿®æ”¹é”�å¯¹è±¡å¼•ç”¨ï¼Œä¸�ä¼šå½±å“�å�Œæ­¥ä»£ç �çš„æ‰§è¡Œã€‚
 */
package concurrent.t01;

import java.util.concurrent.TimeUnit;

public class Test_13 {
	Object o = new Object();

	int i = 0;
	int a(){
		try{
			/*
			 * return i ->
			 * int _returnValue = i; // 0;
			 * return _returnValue;
			 */
			return i;
		}finally{
			i = 10;
		}
	}
	
	void m(){
		System.out.println(Thread.currentThread().getName() + " start");
		synchronized (o) {
			while(true){
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " - " + o);
			}
		}
	}
	
	public static void main(String[] args) {
		final Test_13 t = new Test_13();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}, "thread1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}, "thread2");
		t.o = new Object();
		thread2.start();
		
		System.out.println(t.i);
		System.out.println(t.a());
		System.out.println(t.i);
	}
	
}
