package com.lz.moblielottery;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.utill.DES;

import android.util.Xml;

public class Body {
	//这是一个集合
	private List<Element> elements = new ArrayList<Element>();
	private Oelement oelement = new Oelement();
	
	private String desBody;//存储服务器返回的des加密后的信息
	private String md5info;//解密之后的信息
	
	
	
	public String getMd5info() {
		return md5info;
	}
	public void setMd5info(String md5info) {
		this.md5info = md5info;
	}
	public String getDesBody() {
		return desBody;
	}
	public void setDesBody(String desBody) {
		this.desBody = desBody;
	}
	public Oelement getOelement() {
		return oelement;
	}
	public void setOelement(Oelement oelement) {
		this.oelement = oelement;
	}
	public List<Element> getElements() {
		return elements;
	}
	
	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, "body");
			serializer.startTag(null, "elements");
			
			//处理请求（只是调用了基类里面的方法）
			for(Element element:elements) {
				element.serializer(serializer);
			}
			
			serializer.endTag(null, "elements");
			serializer.endTag(null, "body");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	
 * 获取body部分的内容（包含body标签）
 * */
	public String getBody() { 
		XmlSerializer temp = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			temp.setOutput(writer);
			this.serializer(temp);
			temp.flush();
			return writer.toString();       
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/*获取<element>.....</element>的加密结果
	 * 
	 * */
	public String getDESBoy() {
		String bodyInfo = getBody();
		
		String elementInfo = StringUtils.substringBetween
				(bodyInfo,"<body>","</body>");
		DES des = new DES();
		String authcode = des.authcode(elementInfo,"DECODE",ConstantValue.DES_PASSWORD);
		return authcode;
	}
}
