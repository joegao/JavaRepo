/**
 * å�¯æ‰“æ–­
 * 
 * é˜»å¡žçŠ¶æ€�ï¼š åŒ…æ‹¬æ™®é€šé˜»å¡žï¼Œç­‰å¾…é˜Ÿåˆ—ï¼Œé”�æ± é˜Ÿåˆ—ã€‚
 * æ™®é€šé˜»å¡žï¼š sleep(10000)ï¼Œ å�¯ä»¥è¢«æ‰“æ–­ã€‚è°ƒç”¨thread.interrupt()æ–¹æ³•ï¼Œå�¯ä»¥æ‰“æ–­é˜»å¡žçŠ¶æ€�ï¼ŒæŠ›å‡ºå¼‚å¸¸ã€‚
 * ç­‰å¾…é˜Ÿåˆ—ï¼š wait()æ–¹æ³•è¢«è°ƒç”¨ï¼Œä¹Ÿæ˜¯ä¸€ç§�é˜»å¡žçŠ¶æ€�ï¼Œå�ªèƒ½ç”±notifyå”¤é†’ã€‚æ— æ³•æ‰“æ–­
 * é”�æ± é˜Ÿåˆ—ï¼š æ— æ³•èŽ·å�–é”�æ ‡è®°ã€‚ä¸�æ˜¯æ‰€æœ‰çš„é”�æ± é˜Ÿåˆ—éƒ½å�¯è¢«æ‰“æ–­ã€‚
 *  ä½¿ç”¨ReentrantLockçš„lockæ–¹æ³•ï¼ŒèŽ·å�–é”�æ ‡è®°çš„æ—¶å€™ï¼Œå¦‚æžœéœ€è¦�é˜»å¡žç­‰å¾…é”�æ ‡è®°ï¼Œæ— æ³•è¢«æ‰“æ–­ã€‚
 *  ä½¿ç”¨ReentrantLockçš„lockInterruptiblyæ–¹æ³•ï¼ŒèŽ·å�–é”�æ ‡è®°çš„æ—¶å€™ï¼Œå¦‚æžœéœ€è¦�é˜»å¡žç­‰å¾…ï¼Œå�¯ä»¥è¢«æ‰“æ–­ã€‚
 * 
 */
package concurrent.t03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test_03 {
	Lock lock = new ReentrantLock();
	
	void m1(){
		try{
			lock.lock();
			for(int i = 0; i < 5; i++){
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1() method " + i);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	void m2(){
		try{
			lock.lockInterruptibly(); // å�¯å°�è¯•æ‰“æ–­ï¼Œé˜»å¡žç­‰å¾…é”�ã€‚å�¯ä»¥è¢«å…¶ä»–çš„çº¿ç¨‹æ‰“æ–­é˜»å¡žçŠ¶æ€�
			System.out.println("m2() method");
		}catch(InterruptedException e){
			System.out.println("m2() method interrupted");
		}finally{
			try{
				lock.unlock();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final Test_03 t = new Test_03();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		});
		t2.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.interrupt();// æ‰“æ–­çº¿ç¨‹ä¼‘çœ ã€‚é�žæ­£å¸¸ç»“æ�Ÿé˜»å¡žçŠ¶æ€�çš„çº¿ç¨‹ï¼Œéƒ½ä¼šæŠ›å‡ºå¼‚å¸¸ã€‚
	}
}
