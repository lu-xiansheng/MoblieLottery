package com.lz.moblielottery;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnConnectionPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParamBean;
import org.apache.http.entity.StringEntity;

public class HttpClientAdapter {
	private HttpClient client;
	private HttpPost post;
	
	
	public HttpClientAdapter() {
		if(StringUtils.isNotBlank(GloableParams.PROXY_IP)) {
			//���ô�����Ϣ
			HttpHost host = new HttpHost(GloableParams.PROXY_IP,GloableParams.PROXY_PORT);
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,host);
		
		}  
	} 
		
	
	/*
	 * ����һ��post����
	 * ����������
	 * xml ���͵���Ϣ
	 * */
	public InputStream sendPostRequest(String uri,String xml) {
		//��ȷPost��Get������URL����
		post = new HttpPost(uri);
		//ʹ�õ�����post�����÷��͵�����
		StringEntity entity;
		try {
			entity = new StringEntity(xml);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			//����������״̬���жϣ�200��
			if(response.getStatusLine().getStatusCode() == 200) {
				return response.getEntity().getContent();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
