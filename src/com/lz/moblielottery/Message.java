package com.lz.moblielottery;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class Message {
	private Header header = new Header();//头
	private Body body = new Body();//体
	public Header getHeader() {
		return header;
	}
	public Body getBody() {
		return body;
	}
	
	/*返回现阶段处理后的xml
	 * 获取指定请求的xml（登陆请求）*/
	public String getXml(Element element) {    
		try {
			XmlSerializer serializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			serializer.setOutput(writer);
			serializer.startDocument("utf-8", null);
			
			body.getElements().add(element);
			this.serializer(serializer);
			
			serializer.endDocument();
			
			return writer.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void serializer(XmlSerializer serializer) {
		
		//序列化根节点
		try {
			serializer.startTag(null, "message");
			serializer.attribute(null, "version", "1.0");
			
			Element element = body.getElements().get(0);
			//请求的表示（唯一的标定一个请求）
			header.getTransactiontype().setTagValue(element.getTransactiontype());
			
			header.serializer(serializer,body.getBody());
			serializer.startTag(null, "body");
			serializer.text(body.getDESBoy());
			serializer.endTag(null, "body");
			
			serializer.endTag(null, "message");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
}
