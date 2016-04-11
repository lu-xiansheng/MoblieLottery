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
	//1，滚动条---耗时操作时的提示
	//2，消息框---一个耗时操作完成时的结果提示
	//          用户退出系统时的提示
	//3，重要过程中的相关提示
	//4，应用异常提示
	/*控制Toast显示*/
	private static boolean isShow = true;
	
	/**滚动条的显示
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
	
	/**关闭滚动条
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
				//显示取消按钮
				.setNegativeButton(R.string.is_negative, null)//
				.setPositiveButton(R.string.is_positive, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				});
		builder.show();		
	}
	
	/**无网络提示对话框
	 * @param context
	 */
	public static void showNoNetWork(final Context context) {
		new AlertDialog.Builder(context)//
				.setIcon(R.drawable.icon)//
				.setTitle(R.string.app_name)//
				.setMessage("当前无网络")//
				.setPositiveButton("网络设置", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 跳转到系统的网络设置界面
						Intent intent=new Intent();
						intent.setAction(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						context.startActivity(intent);
					}
				})//
				.setNegativeButton("我知道了", null)//
				.show();
	}

	/**
	 * 显示错误提示框
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
	 * 显示错误提示框
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
	 * 测试用
	 */
	public static void showToastTest(Context context, String msg) {
		if (isShow) {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}

}
