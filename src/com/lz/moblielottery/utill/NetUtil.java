package com.lz.moblielottery.utill;

import com.lz.moblielottery.GloableParams;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/*
 * 判断手机联网的渠道
 * */
public class NetUtil {
	
	/*检查当前手机网络*/
	public static boolean checkNet(Context context) {
		boolean resule = false;
		//判断连接方式
		boolean wifiConnect = isWIFIConnected(context);
		boolean mobileConnect = isMOBILEConnected(context);
		if(wifiConnect == false && mobileConnect == false) {
			//如果都没有连接返回false 提示用户没有网络
			return false;
		}
		
		//判断到当前是mobile链接，设置apn
		if(mobileConnect == true) {
			setApnParame(context);
			
		}
		return true;
	}
	//判断手机使用的wifi还是moblie
	
	/*
	 * 判断手机是否采用wifi链接
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
	 * 设置Apn信息，保存到应用的全局变量里面
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
