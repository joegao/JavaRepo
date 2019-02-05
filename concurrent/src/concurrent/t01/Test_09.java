/**
 * volatileå…³é”®å­—
 * volatileçš„å�¯è§�æ€§
 * é€šçŸ¥OSæ“�ä½œç³»ç»Ÿåº•å±‚ï¼Œåœ¨CPUè®¡ç®—è¿‡ç¨‹ä¸­ï¼Œéƒ½è¦�æ£€æŸ¥å†…å­˜ä¸­æ•°æ�®çš„æœ‰æ•ˆæ€§ã€‚ä¿�è¯�æœ€æ–°çš„å†…å­˜æ•°æ�®è¢«ä½¿ç”¨ã€‚
 * 
 */
package concurrent.t01;

import java.util.concurrent.TimeUnit;

public class Test_09 {
	
	volatile boolean b = true;
	
	void m(){
		System.out.println("start");
		while(b){}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		final Test_09 t = new Test_09();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t.b = false;
	}
	
}
