package com.silva.chetax.schedule.center.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
	public static String stackTraceToString(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw,true));
		return sw.getBuffer().toString();
	}
}
