package com.lz.moblielottery;

import org.xmlpull.v1.XmlSerializer;

public abstract class Element {
	//35������35���ظ�
	//���������ȥ
	//���л�
	public abstract void serializer(XmlSerializer serializer);
	//��ȡ�����ʶ
	public abstract String getTransactiontype();
}
