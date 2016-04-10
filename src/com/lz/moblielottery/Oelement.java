package com.lz.moblielottery;

/*服务器回复的信息*/

public class Oelement {
	private String errorcode;//服务器处理结果的状态值
	private String errormsg ;//服务器处理结果的状态描述信息
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
}
