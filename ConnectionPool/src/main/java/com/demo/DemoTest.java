package com.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class DemoTest {

	public void a() {
       b();
	}

	private void b() {
		int a = 0;
		while (true) {
			a += 1;
			if (a > 100) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
//		new DemoTest().a();
		
//		int a = 1000.1111111;
		double b = 1000.111111;
		
		AtomicInteger atomicInteger = new AtomicInteger();
		atomicInteger.set(5);
		System.out.println(atomicInteger.get());
	}
}
