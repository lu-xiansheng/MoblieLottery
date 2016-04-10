package com.lz.moblielottery;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

public class Leaf {
	//叶子节点的标签对应名称
	private String tagName;
	//子叶节点的标签值
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
	
	//序列化叶子
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
