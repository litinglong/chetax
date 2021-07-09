package com.silva.chetax.schedule.center.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtils {

	public static final Long SECOND = 1000L;
	public static final Long MINITE = 60 * SECOND;
	public static final Long HOUR = 60 * MINITE;

	public static String formateMs(Long targetMs) {
		Long time = targetMs;
		if (time == null) {
			time = 0L;
		}
		Long h = time / HOUR;
		Long m = (time % HOUR) / MINITE;
		Long s = (time % MINITE) / SECOND;
		Long ms = time % SECOND;
		String result = "";
		if (time == 0) {
			result = "0";
		}
		if (h != 0) {
			result += String.format("%d小时", h);
		}
		if (m != 0) {
			result += String.format("%d分钟", m);
		}
		if (s != 0) {
			result += String.format("%d秒", s);
		}
		if (ms != 0) {
			result += String.format("%d毫秒", ms);
		}
		return result;
	}
	public static Long usedTime(LocalDateTime start, LocalDateTime end) {
		//获取秒数
//		Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
		if(start == null || end == null) {
			return null;
		}
//		//获取毫秒数
		Long milliSecondStart = start.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		Long milliSecondEnd = end.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		Long milliSecondDivide = milliSecondEnd - milliSecondStart;
		return milliSecondDivide;
	}
	
	public static String usedTimeFormated(LocalDateTime start, LocalDateTime end) {
		Long milliSecondDivide = usedTime(start, end);
		if(milliSecondDivide == null) {
			return "";
		}
		if(milliSecondDivide < 0) {
			return "结束时间早于开始时间";
		}
		return formateMs(milliSecondDivide);
	}
	


	public static void main(String[] args) {
		String s = formateMs(99999999999L);
		System.out.println(s);
		s = formateMs(null);
		System.out.println(s);
		LocalDateTime start = LocalDateTime.now();
		try {
			Thread.sleep(1234);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LocalDateTime end = LocalDateTime.now();
		s = usedTimeFormated(start,end);
		System.out.println(s);
	}
}
