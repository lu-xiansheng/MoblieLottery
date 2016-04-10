package com.lz.moblielottery;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

public class Leaf {
	//Ҷ�ӽڵ�ı�ǩ��Ӧ����
	private String tagName;
	//��Ҷ�ڵ�ı�ǩֵ
	private String tagValue;
	
	public Leaf(String tagName, String tagValue) {
		super();
		this.tagName = tagName;
		this.tagValue = tagValue;
	}

	public Leaf(String tagName) {
		super();
		this.tagName = tagName;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	
	//���л�Ҷ��
	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, tagName);
			if(StringUtils.isBlank(tagValue)) {
				tagValue = "";
			}
			serializer.text(tagValue);
			serializer.endTag(null, tagName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
