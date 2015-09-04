package com.web.web.vo;


public class Res {
	public static final String success = "success";
	public static final String fail = "fail";
	
	private String status = success;
	private Object result;
	
	public Res() {
		super();
	}
	public Res(Object result) {
		super();
		this.result = result;
	}
	public Res(String status, Object result) {
		this.status = status;
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
