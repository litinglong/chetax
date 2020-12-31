package com.test4.service;

import com.test4.exception.CheckedException;
import com.test4.exception.UncheckedException;

public interface Test4Service {
	String sayName(String name);
	
	String checkedExceptionTest(String text) throws CheckedException;
	String uncheckedExceptionTest(String text) throws UncheckedException;
	
}
