/**
 * volatileå…³é”®å­—
 * volatileçš„é�žåŽŸå­�æ€§é—®é¢˜
 * volatileï¼Œ å�ªèƒ½ä¿�è¯�å�¯è§�æ€§ï¼Œä¸�èƒ½ä¿�è¯�åŽŸå­�æ€§ã€‚
 * ä¸�æ˜¯æž·é”�é—®é¢˜ï¼Œå�ªæ˜¯å†…å­˜æ•°æ�®å�¯è§�ã€‚
 */
package concurrent.t01;

import java.util.ArrayList;
import java.util.List;

public class Test_10 {
	
	volatile int count = 0;
	synchronized void m(){
		for(int i = 0; i < 10000; i++){
			count++;
		}
	}
	
	public static void main(String[] args) {
		final Test_10 t = new Test_10();
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					t.m();
				}
			}));
		}
		for(Thread thread : threads){
			thread.start();
		}
		for(Thread thread : threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(t.count);
	}
}
