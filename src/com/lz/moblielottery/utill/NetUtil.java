package com.lz.moblielottery.utill;

import com.lz.moblielottery.GloableParams;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/*
 * �ж��ֻ�����������
 * */
public class NetUtil {
	
	/*��鵱ǰ�ֻ�����*/
	public static boolean checkNet(Context context) {
		boolean resule = false;
		//�ж����ӷ�ʽ
		boolean wifiConnect = isWIFIConnected(context);
		boolean mobileConnect = isMOBILEConnected(context);
		if(wifiConnect == false && mobileConnect == false) {
			//�����û�����ӷ���false ��ʾ�û�û������
			return false;
		}
		
		//�жϵ���ǰ��mobile���ӣ�����apn
		if(mobileConnect == true) {
			setApnParame(context);
			
		}
		return true;
	}
	//�ж��ֻ�ʹ�õ�wifi����moblie
	
	/*
	 * �ж��ֻ��Ƿ����wifi����
	 * */
	public static boolean isWIFIConnected(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService
				(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo =  manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if(networkInfo!=null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
	
	public static boolean isMOBILEConnected(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService
				(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo =  manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if(networkInfo!=null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
	/*
	 * ����Apn��Ϣ�����浽Ӧ�õ�ȫ�ֱ�������
	 * */
	public static void setApnParame(Context context) {
		Uri uri = Uri.parse("content://telephony/carriers/preferapn");
		
		ContentResolver contentResolver = context.getContentResolver();
		Cursor query =  contentResolver.query(uri, null, null, null, null);
		if(query != null && query.getCount() == 1) {
			if(query.moveToFirst()) {
				GloableParams.PROXY_IP = query.getString(query.getColumnIndex("proxy"));
				GloableParams.PROXY_PORT = query.getInt(query.getColumnIndex("port"));
			}
		}
		query.close();
	}

}
