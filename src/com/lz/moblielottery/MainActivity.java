package com.lz.moblielottery;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.utill.NetUtil;
import com.lz.moblielottery.view.BaseView;
import com.lz.moblielottery.view.FirstView;
import com.lz.moblielottery.view.Hall;
import com.lz.moblielottery.view.SecondView;
import com.lz.moblielottery.view.manager.BottomManager;
import com.lz.moblielottery.view.manager.PromotManager;
import com.lz.moblielottery.view.manager.TopManager;
import com.lz.moblielottery.view.manager.UIManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	protected static final int value = 0;

	private RelativeLayout middleContainer;//中间容器
	
	private ProgressDialog progressDialog;//通用的进度条


	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case value://显示一个进度条
				PromotManager.showProgressDialog(progressDialog, "获取服务器信息....");
				break;

			default:
				break;
			} 
			super.handleMessage(msg);
		}
		
	};
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
	
		//方式一：直接new （）
		//方式二：单例  
		init();  
	}  
	
	private void init() {  
		middleContainer = (RelativeLayout)findViewById(R.id.ii_main_middle);
		UIManager.getInstance().setMiddleContainer(middleContainer);
		
		UIManager.getInstance().addObserver(TopManager.getinstance());
		UIManager.getInstance().addObserver(BottomManager.getInstrance());
		
		BottomManager.getInstrance().init(this);
		TopManager.getinstance().init(this);
		
		TopManager.getinstance().showCommonTitle();
		
		/*FirstView child = new FirstView(this);
		middleContainer.addView(child.getView());*/
		UIManager.getInstance().changeView(Hall.class, null);
		
		/*if(!NetUtil.checkNet(this)) {
			PromotManager.showNoNetWork(this);
		}*/
		
		BaseView.setHandler(handler);
		/*handler.sendEmptyMessageDelayed(0,2000); */ 
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			// 调用切换的方法  
			boolean changeCache = UIManager.getInstance().changeCache();
			if(changeCache == false) {
				//退出应用
				//提示用户是否退出应用（AlertDialog）
				PromotManager.showExitDialog(this, "你确定要离我而去吗？0=0");
 		}else {
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}