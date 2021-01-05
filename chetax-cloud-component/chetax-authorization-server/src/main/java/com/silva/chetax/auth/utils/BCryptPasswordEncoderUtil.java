package com.silva.chetax.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {

	public static void main(String[] args) {
		if(args.length>0) {
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			System.out.println(encoder.encode(args[0]));
		}
	}
}
