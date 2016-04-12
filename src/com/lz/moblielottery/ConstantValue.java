package com.lz.moblielottery;

public interface ConstantValue {
	
	/*
	 * 第一个界面
	 * */
	int VIEW_FIRST = 0;
	/*
	 * 第二个界面
	 * */
	int VIEW_SECOND = 1;
	/*
	 * 第三个界面
	 * */
	int VIEW_THIED = 2;
	/*
	 * 购彩大厅界面
	 * */
	int VIew_HALL = 10;
	
	String AGENTERID="1000002";//代理商的标识
	String SOURCE="ivr";//xml文件的来源
	String COMPRESS="DES";//加密算法
	/*
	 * 子代理商密码
	 * 
	 * */
	String PASSWORD = "9ab62a694d8bf6ced1fab6acd48d02f8";
	
	/*
	 * des加密和解密用密钥
	 * */
	String DES_PASSWORD = "9b2648fcdfbad80f";
	/**
	 * 彩票服务器连接
	 */
	String URL_LOTTERY = "http://10.0.2.2:8080/ZCWService/Entrance";
}
