package com.imatrix4j.demo1.spring.boot.netty;

public class CCMessageHeader {
	protected byte[] messageFlag = new byte[2];
	protected int length;
	protected int type;
	protected String data;

	CCMessageHeader() {
		messageFlag[0] = (byte) 0xaa;
		messageFlag[1] = (byte) 0xbb;
	}

	public byte[] getMessageFlag() {
		return messageFlag;
	}

	public void setMessageFlag(byte[] messageFlag) {
		this.messageFlag = messageFlag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
		length = data.length() + 4;
	}
}
