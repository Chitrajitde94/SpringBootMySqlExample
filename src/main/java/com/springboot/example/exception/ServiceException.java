package com.springboot.example.exception;

public class ServiceException extends Exception{
	
	String msg;
	
	
	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServiceException(String msg) {
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
