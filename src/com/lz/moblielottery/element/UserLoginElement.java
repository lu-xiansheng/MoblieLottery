package com.lz.moblielottery.element;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.Element;
import com.lz.moblielottery.Leaf;

/*
 * 用户登陆的请求
 * */
public class UserLoginElement extends Element {
	
	private Leaf actpassword = new Leaf("actpassword");

	public Leaf getActpassword() {
		return actpassword;
	}

	@Override
	public void serializer(XmlSerializer serializer) {
		//序列化相应的请求的内容
	
		try {
			serializer.startTag(null,"actpassword");
			actpassword.serializer(serializer);
			serializer.endTag(null, "actpassword");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getTransactiontype() {
		// TODO Auto-generated method stub
		return "14001";
	}
	
}
