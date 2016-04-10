package com.lz.moblielottery.element;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.Element;
import com.lz.moblielottery.Leaf;

public class UserRegisterElement extends Element {
	
	private Leaf actpassword = new Leaf("actpassword");

	

	public Leaf getActpassword() {
		return actpassword;
	}

	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, "element");
			actpassword.serializer(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

	@Override
	public String getTransactiontype() {
		// TODO Auto-generated method stub
		return "10001";
	}

}
