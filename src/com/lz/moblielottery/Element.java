package com.lz.moblielottery;

import org.xmlpull.v1.XmlSerializer;

public abstract class Element {
	//35个请求，35个回复
	//工作分配出去
	//序列化
	public abstract void serializer(XmlSerializer serializer);
	//获取请求标识
	public abstract String getTransactiontype();
}
