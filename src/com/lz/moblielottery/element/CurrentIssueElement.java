/**
 * 
 */
package com.lz.moblielottery.element;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.Element;
import com.lz.moblielottery.Leaf;

/**
 * @author L.Z.
 *
 */
public class CurrentIssueElement extends Element{

	/* (non-Javadoc)
	 * @see com.lz.moblielottery.Element#serializer(org.xmlpull.v1.XmlSerializer)
	 */
	
	private Leaf lotteryId = new Leaf("lotteryId");//��ǰҪ��ѯ�Ĳ��ֵı�ʶ
	private Leaf issues = new Leaf("issues","1"); 
	
	/**********������������Ϣ*******************/
	private String lotteryname;//���ֵ�����
	private String issue;//�ں�
	private String lasttime;//���ڽ�ʣ��ʱ�� 
	
	
	public String getLotteryname() {
		return lotteryname;
	}

	public void setLotteryname(String lotteryname) {
		this.lotteryname = lotteryname;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	@Override
	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, "element");
			lotteryId.serializer(serializer);
			issues.serializer(serializer);
			serializer.endTag(null, "element");  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.lz.moblielottery.Element#getTransactiontype()
	 */
	@Override
	public String getTransactiontype() {
		return "12002";
	}

	public Leaf getLotteryId() {
		return lotteryId;
	}
	
	
} 
