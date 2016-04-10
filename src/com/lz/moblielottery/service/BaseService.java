package com.lz.moblielottery.service;

import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.Element;
import com.lz.moblielottery.HttpClientAdapter;
import com.lz.moblielottery.Message;
import com.lz.moblielottery.bean.User;
import com.lz.moblielottery.utill.DES;

public abstract class BaseService {
	
	protected Message getResult(User user,Element element) {
		String xml = getXml(user,element);
		InputStream is = sendRequest(xml);
		if(is != null) {
			Message result = firstParser(is);
			if(checkMD5(result)) {
				return result;
			}
		}
		return null;
	}
	/*
	 * 拿到Xml
	 * 
	 * */
	protected String getXml(User user,Element element) {
		Message message = new Message();
		message.getHeader().getUsername().setTagValue(user.getUsername());
		return message.getXml(element);
	}
	/*
	 * 
	 * 建立连接，发送xml
	 * */
	protected InputStream sendRequest(String xml) {
		HttpClientAdapter adapter = new HttpClientAdapter();
		return adapter.sendPostRequest(ConstantValue.URL_LOTTERY, xml);
	}
	
	/*
	 * 第一次解析
	 * */
	protected Message firstParser(InputStream is) {
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
		}
		return result;
	}
	/*
	 * 验证MD5
	 * */
	protected boolean checkMD5(Message result) {
		DES des = new DES();
		String desInfo = "<body>"+des.authcode(result.getBody().getDesBody(),"ENCODE","0102030405060708")+"</body>";
		
		String md5Info=result.getHeader().getTimestamp().getTagValue() + ConstantValue.PASSWORD + desInfo;
		String md5Hex = DigestUtils.md5Hex(md5Info);
		if(md5Hex.equals(result.getHeader().getDigest().getTagValue())) {
			result.getBody().setMd5info(desInfo);
			return true;
		}
		return false;

	}
}
