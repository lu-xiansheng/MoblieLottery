package com.lz.moblielottery.service;

import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlPullParser;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.Element;
import com.lz.moblielottery.HttpClientAdapter;
import com.lz.moblielottery.Message;
import com.lz.moblielottery.bean.User;
import com.lz.moblielottery.utill.DES;

import android.util.Xml;


public abstract class BaseService {
	/**
	 * ����Md5��֤ͨ����Ľ�� 
	 * @param user
	 * @param element
	 * @return
	 */
	protected Message getResult(User user,Element element)
	{
		String xml = getXml(user,element);
		InputStream is = sendRequest(xml);
		if(is!=null)
		{
			Message result = firstParser(is);
			boolean checkMd5 = checkMd5(result);
			if(checkMd5)
			{
				return result;
			}
		}
		return null;
	}
	
	
	/**
	 * 2���õ�xml
	 * @param user
	 * @param element
	 * @return
	 */
	protected String getXml(User user,Element element){
		Message message = new Message();
		if(user!=null)
		{
			message.getHeader().getUsername().setTagValue(user.getUsername());
		}
		return message.getXml(element);
		
	}
	
	/**
	 * 3���������ӣ�����xml
	 * @param xml
	 * @return
	 */
	protected InputStream sendRequest(String xml)
	{
		HttpClientAdapter adapter = new HttpClientAdapter();
		return adapter.sendPostRequest(ConstantValue.URL_LOTTERY, xml);

	}
	/**
	 * ��һ�ν���
	 * @param is
	 * @return
	 */
	protected Message firstParser(InputStream is)
	{
		Message result=new Message();
		// ��һ����
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, "utf-8");

			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String name = "";
				switch (eventType) {
					case XmlPullParser.START_TAG:
						name = parser.getName();

						if ("timestamp".equalsIgnoreCase(name)) {
							result.getHeader().getTimestamp().setTagValue(parser.nextText());
						}

						if ("digest".equalsIgnoreCase(name)) {
							result.getHeader().getDigest().setTagValue(parser.nextText());
						}

						if ("body".equalsIgnoreCase(name)) {
							result.getBody().setDesBody(parser.nextText());
						}
						break;
				}

				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;

	}
	/**
	 * MD5��Ϣ��֤
	 * @param result
	 * @return
	 */
	protected boolean checkMd5(Message result)
	{
		// У��MD5
		DES des = new DES();
		String desInfo = "<body>" + des.authcode(result.getBody().getDesBody(), "ENCODE", ConstantValue.DES_PASSWORD) + "</body>";

		String md5Info = result.getHeader().getTimestamp().getTagValue() + ConstantValue.PASSWORD + desInfo;
		String md5Hex = DigestUtils.md5Hex(md5Info);

		if (md5Hex.equals(result.getHeader().getDigest().getTagValue())) {
			result.getBody().setBodyInfo(desInfo);
			return true;
		}
		return false;
	}
	
}
