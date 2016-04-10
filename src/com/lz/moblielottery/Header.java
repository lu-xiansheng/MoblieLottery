package com.lz.moblielottery;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlSerializer;

public class Header {
	//����ȷ��ֵ
	private Leaf agenterid = new Leaf("agenterid",ConstantValue.AGENTERID);
	private Leaf source = new Leaf("source",ConstantValue.SOURCE);
	private Leaf compress = new Leaf("compress", ConstantValue.COMPRESS);
	
	//ֵ���ֽ׶��޷�ȷ�ϵ�
	private Leaf messagerid = new Leaf("messagerid"); //��ʶxmlid
	private Leaf timestamp = new Leaf("timestamp");//ʱ���
	private Leaf digest = new Leaf("digest");//MDS����
	
	//ֵ������Ҫ��������ݵ�
	private Leaf transactiontype = new Leaf("transactiontype"); //����Ψһ��ʶ
	private Leaf username = new Leaf("username"); //�û���
	
	public Leaf getTransactiontype() {
		return transactiontype;
	}
	public Leaf getUsername() {
		return username;
	}
	
	public void serializer(XmlSerializer serializer,String body) {
		
		//��ʽ��ʱ��
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = dateFormat.format(new Date());
		
		//������λ�������
		Random random = new Random();
		int num = random.nextInt(999999)+1;//0-999999
		
		//��ʽ��,����
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		String numFormat = decimalFormat.format(num);
		
		//xml id ����
		messagerid.setTagValue(time + numFormat);
		              
		//ʱ�������
		timestamp.setTagValue(time);
		
		//digest���ܷ�ʽ����MD5
		String md5Info = time + ConstantValue.PASSWORD + body;
		String md5Hex = DigestUtils.md5Hex(md5Info);
		digest.setTagValue(md5Hex);
		
		//header���л�
		try {
				serializer.startTag(null, "header");
				
				agenterid.serializer(serializer);
				source.serializer(serializer);
				compress.serializer(serializer);
				
				messagerid.serializer(serializer);
				timestamp.serializer(serializer);
				digest.serializer(serializer);
				
				transactiontype.serializer(serializer);
				username.serializer(serializer);
				
				serializer.endTag(null, "header");
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	public Leaf getTimestamp() {
		return timestamp;
	}
	public Leaf getDigest() {
		return digest;
	}
	
	
	
}
