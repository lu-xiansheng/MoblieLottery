/**
 * 
 */
package com.lz.moblielottery.view.manager;

import com.lz.moblielottery.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * @author L.Z.
 *
 */
public class PromotManager {
	//1��������---��ʱ����ʱ����ʾ
	//2����Ϣ��---һ����ʱ�������ʱ�Ľ����ʾ
	//          �û��˳�ϵͳʱ����ʾ
	//3����Ҫ�����е������ʾ
	//4��Ӧ���쳣��ʾ
	
	/**����������ʾ
	 * @param progressDialog
	 * @param msg
	 */
	public static void showProgressDialog(ProgressDialog progressDialog,String msg) {
		if(progressDialog != null && !progressDialog.isShowing()) {
			progressDialog.setTitle(R.string.app_name);
			progressDialog.setIcon(R.drawable.icon);
			progressDialog.setMessage(msg);
			progressDialog.show();
		}
	}
	
	/**�رչ�����
	 * @param progressDialog
	 */
	public static void closeshowProgressDialog(ProgressDialog progressDialog) {
		if(progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}
	
	public static void showExitDialog(Context context,String msg) {
		AlertDialog.Builder builder = new Builder(context);
		
		builder.setTitle(R.string.app_name)//
				.setMessage(msg)//
				//��ʾȡ����ť
				.setNegativeButton(R.string.is_negative, null)//
				.setPositiveButton(R.string.is_positive, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				});
		builder.show();		
	}
}
