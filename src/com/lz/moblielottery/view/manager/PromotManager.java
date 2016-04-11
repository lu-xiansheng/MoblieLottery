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
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

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
	/*����Toast��ʾ*/
	private static boolean isShow = true;
	
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
	
	/**��������ʾ�Ի���
	 * @param context
	 */
	public static void showNoNetWork(final Context context) {
		new AlertDialog.Builder(context)//
				.setIcon(R.drawable.icon)//
				.setTitle(R.string.app_name)//
				.setMessage("��ǰ������")//
				.setPositiveButton("��������", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// ��ת��ϵͳ���������ý���
						Intent intent=new Intent();
						intent.setAction(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						context.startActivity(intent);
					}
				})//
				.setNegativeButton("��֪����", null)//
				.show();
	}

	/**
	 * ��ʾ������ʾ��
	 */
	public static void showErrorDialog(Context context, String msg) {
		new AlertDialog.Builder(context)//
				.setIcon(R.drawable.icon)//
				.setTitle(R.string.app_name)//
				.setMessage(msg)//
				.setNegativeButton(context.getString(R.string.is_positive), null)//
				.show();
	}

	/**
	 * ��ʾ������ʾ��
	 */
	public static void showErrorDialog(Context context, String title, String msg) {
		new AlertDialog.Builder(context).setIcon(R.drawable.icon).setTitle(title).setMessage(msg).setNegativeButton(
				context.getString(R.string.is_positive), null).show();
	}

	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void showToast(Context context, int msgResId) {
		Toast.makeText(context, msgResId, Toast.LENGTH_LONG).show();
	}

	/**
	 * ������
	 */
	public static void showToastTest(Context context, String msg) {
		if (isShow) {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}

}
