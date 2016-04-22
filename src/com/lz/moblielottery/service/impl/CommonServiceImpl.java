package com.lz.moblielottery.service.impl;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.Message;
import com.lz.moblielottery.element.CurrentIssueElement;
import com.lz.moblielottery.service.BaseService;
import com.lz.moblielottery.service.CommonService;

import android.util.Xml;



public class CommonServiceImpl extends BaseService implements CommonService {
	@Override
	public Message getCurrentIssueInfo(Integer lotteryId) {
		//1.创建Element
		CurrentIssueElement element = new CurrentIssueElement();
		element.getLotteryId().setTagValue(lotteryId.toString());
		//2.发�?�请求并获取服务器返回的message
		Message result = getResult(null, element);
		if (result != null) {//为空表示md5验证没有成功
			String bodyInfo = result.getBody().getBodyInfo();
			//3.解析bodyInfo;
			XmlPullParser parser = Xml.newPullParser();
			try {
				parser.setInput(new StringReader(bodyInfo));

				int eventType = parser.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String name = "";
					switch (eventType) {
						case XmlPullParser.START_TAG:
							name = parser.getName();
							if ("errorcode".equals(name)) {
								result.getBody().getOelement().setErrorcode(parser.nextText());
							}
							if ("errormsg".equals(name)) {
								result.getBody().getOelement().setErrormsg(parser.nextText());
							}
							if ("element".equals(name)) {
      				// 放置的是回复的Element,这个过程中将之前请求的那个element覆盖�?
								result.getBody().getElements().add(element);
							}
							if ("lotteryname".equals(name)) {
								element.setLotteryname(parser.nextText());
							}
							if ("issue".equals(name)) {
								element.setIssue(parser.nextText());
							}
							if ("lasttime".equals(name)) {
								element.setLasttime(parser.nextText());
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
}
