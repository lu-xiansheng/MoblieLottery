package com.lz.moblielottery;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlSerializer;

public class Header {
	//可以确定值
	private Leaf agenterid = new Leaf("agenterid",ConstantValue.AGENTERID);
	private Leaf source = new Leaf("source",ConstantValue.SOURCE);
	private Leaf compress = new Leaf("compress", ConstantValue.COMPRESS);
	
	//值，现阶段无法确认的
	private Leaf messagerid = new Leaf("messagerid"); //标识xmlid
	private Leaf timestamp = new Leaf("timestamp");//时间戳
	private Leaf digest = new Leaf("digest");//MDS加密
	
	//值，是需要外界来传递的
	private Leaf transactiontype = new Leaf("transactiontype"); //请求唯一标识
	private Leaf username = new Leaf("username"); //用户名
	
	public Leaf getTransactiontype() {
		return transactiontype;
	}
	public Leaf getUsername() {
		return username;
	}
	
	public void serializer(XmlSerializer serializer,String body) {
		
		//格式化时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = dateFormat.format(new Date());
		
		//处理六位的随机数
		Random random = new Random();
		int num = random.nextInt(999999)+1;//0-999999
		
		//格式化,补零
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		String numFormat = decimalFormat.format(num);
		
		//xml id 设置
		messagerid.setTagValue(time + numFormat);
		              
		//时间戳设置
		timestamp.setTagValue(time);
		
		//digest加密方式设置MD5
		String md5Info = time + ConstantValue.PASSWORD + body;
		String md5Hex = DigestUtils.md5Hex(md5Info);
		digest.setTagValue(md5Hex);
		
		//header序列化
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
