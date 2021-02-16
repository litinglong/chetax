package com.silva.chetax.spring.life.test.demo.exception;

import java.util.Random;

public class TestService {
	private final Random r = new Random();
	
	public Random getR() {
		return r;
	}

	void testUncheckedException1() {
		if(getR().nextInt(2) == 0) {
			throw new UncheckedException();
		}
	}
	
	void testUncheckedException2() throws UncheckedException {
		if(getR().nextInt(2) == 0) {
			throw new UncheckedException();
		}
	}
	
	void testCheckedException() throws CheckedException {
		if(getR().nextInt(2) == 0) {
			throw new CheckedException();
		}
	}
}
