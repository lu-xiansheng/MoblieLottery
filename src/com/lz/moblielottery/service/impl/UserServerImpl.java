package com.lz.moblielottery.service.impl;

import java.io.InputStream;
import java.io.StringReader;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.HttpClientAdapter;
import com.lz.moblielottery.Message;
import com.lz.moblielottery.bean.User;
import com.lz.moblielottery.element.UserLoginElement;
import com.lz.moblielottery.service.BaseService;
import com.lz.moblielottery.service.UserService;
import com.lz.moblielottery.utill.DES;

public class UserServerImpl extends BaseService implements UserService{
	
	public Message login(User user){
		//生成用户登陆请求
		UserLoginElement element = new UserLoginElement();
		element.getActpassword().setTagValue(user.getPassword());
		
		Message result = getResult(user, element);
		
		if(result != null) {
			//MD5验证通过了
			//解析body
			//第二次解析（解析内容是body信息）
			XmlPullParser parser = Xml.newPullParser();	
			try {
				parser.setInput(new StringReader(result.getBody().getBodyInfo()));
				 
				int eventType = parser.getEventType();
				while(eventType != XmlPullParser.END_DOCUMENT) {
					String name = "";
					switch(eventType) {
					case XmlPullParser.START_TAG:
						name = parser.getName();
						
						if("errorcode".equalsIgnoreCase(name)) {
							result.getBody().getOelement().setErrorcode(parser.nextText());
						}
						
						if("errormsg".equalsIgnoreCase(name)) {
							result.getBody().getOelement().setErrormsg(parser.nextText());
						}
						/*if("errormsg".equalsIgnoreCase(name)) {
							result.getBody().getOelement().setErrormsg(parser.nextText());
						}*/
						
						break;
					}
					
					eventType = parser.next();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	
}
	/*
	 * 
	 * 用户登陆
	 * 
	public Message login(User user) {
		//生成用户登陆请求
		UserLoginElement element = new UserLoginElement();
		element.getActpassword().setTagValue(user.getPassword());
		
		Message message = new Message();
		message.getHeader().getUsername().setTagValue(user.getUsername());
		//拿到xml
		String xml = message.getXml(element);
		//建立连接，发送xml
		HttpClientAdapter adapter = new HttpClientAdapter();
		InputStream is = adapter.sendPostRequest(ConstantValue.URL_LOTTERY, xml);
		
		Message result = new Message();
		if(is!=null) {
			XmlPullParser parser = Xml.newPullParser();
			try {
				parser.setInput(is,"utf-8");
				
				int eventType = parser.getEventType();
				while(eventType != XmlPullParser.END_DOCUMENT) {
					String name = "";
					switch(eventType) {
					case XmlPullParser.START_TAG:
						name = parser.getName();
						
						if("timestamp".equalsIgnoreCase(name)) {
							result.getHeader().getTimestamp().setTagValue(parser.nextText());
						}
						
						if("digest".equalsIgnoreCase(name)) {
							result.getHeader().getDigest().setTagValue(parser.nextText());
						}
						
						if("body".equalsIgnoreCase(name)) {
							result.getBody().setDesBody(parser.nextText());
						}
						break;
					}
			
					eventType = parser.next();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//检验MDS
			DES des = new DES();
			String desInfo = "<body>"+des.authcode(result.getBody().getDesBody(),"ENCODE","0102030405060708")+"</body>";
			
			String md5Info=result.getHeader().getTimestamp().getTagValue() + ConstantValue.PASSWORD + desInfo;
			String md5Hex = DigestUtils.md5Hex(md5Info);
			
			if(md5Hex.equals(result.getHeader().getDigest().getTagValue())) {
				//MD5验证通过了
				//解析body
				//第二次解析（解析内容是body信息）
				parser = Xml.newPullParser();	
				try {
					parser.setInput(new StringReader(md5Info));
					
					int eventType = parser.getEventType();
					while(eventType != XmlPullParser.END_DOCUMENT) {
						String name = "";
						switch(eventType) {
						case XmlPullParser.START_TAG:
							name = parser.getName();
							
							if("errorcode".equalsIgnoreCase(name)) {
								result.getBody().getOelement().setErrorcode(parser.nextText());
							}
							
							if("errormsg".equalsIgnoreCase(name)) {
								result.getBody().getOelement().setErrormsg(parser.nextText());
							}
							if("errormsg".equalsIgnoreCase(name)) {
								result.getBody().getOelement().setErrormsg(parser.nextText());
							}
							
							break;
						}
						
						eventType = parser.next();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		return result;
	}
}
*/