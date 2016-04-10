package com.lz.moblielottery.view.manager;


import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.MainActivity;
import com.lz.moblielottery.R;
import com.lz.moblielottery.view.FirstView;
import com.lz.moblielottery.view.SecondView;

import android.app.Activity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


/*
 * 管理顶部标题（容器）
 * */
public class TopManager implements Observer{  
	
	//完成了TopManager的单例
	/*1、单例类只能有一个实例。
	2、单例类必须自己创建自己的唯一实例。
	3、单例类必须给所有其他对象提供这一实例。*/
	
	protected static final String TAG = "TopManager";

	private TopManager() {
		
	}
	/*单例类只能有一个实例*/
	private static TopManager instance = new TopManager();
	
	public static TopManager getinstance() {
		return instance;  
	}
	
	
	/******************容器**********************/
	private RelativeLayout commonTopContaner;//通用的标题
	private RelativeLayout loginTopContaner;//登陆后标题
	private RelativeLayout unloginTopContaner;//未登录标题
	/****************************************/
	private ImageButton topReturn;//返回按钮
	private TextView topTitle;//标题内容
	private ImageButton topHelp;//帮助按钮
	
	private Button regist;//注册按钮
	private Button login;//登陆按钮
	
	private TextView userInfoTextView;//用户信息
	
	public void init(Activity activity) {
		commonTopContaner = (RelativeLayout)activity.findViewById(R.id.ii_top_common_continer);
		loginTopContaner = (RelativeLayout)activity.findViewById(R.id.ii_top_login_continer);
		unloginTopContaner = (RelativeLayout)activity.findViewById(R.id.ii_top_unlogin_continer);
		
		topReturn = (ImageButton)activity.findViewById(R.id.ii_top_return);
		topTitle = (TextView)activity.findViewById(R.id.ii_top_title);
		topHelp = (ImageButton)activity.findViewById(R.id.ii_top_help);

		regist = (Button)activity.findViewById(R.id.ii_top_regist);
		login = (Button)activity.findViewById(R.id.ii_top_login);
		
		userInfoTextView = (TextView)activity.findViewById(R.id.ii_top_user_info);
		
		setListener();
	} 
	/*创建监听器*/
	private void setListener() {
		topReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG,"点击了返回按钮");
			}
		});
		
		topHelp.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Log.i(TAG,"点击了帮助按钮");
				UIManager.getInstance().changeView(SecondView.class);
				
			} 
		});
		
		regist.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				UIManager.getInstance().changeView(SecondView.class);
				Log.i(TAG,"点击了注册按钮");
			} 
		});
		
		login.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Log.i(TAG,"点击了登陆按钮");
			} 
		});

	}             
	/*
	 * 隐藏所有的标题
	 * */
	private void initTitle() {
		commonTopContaner.setVisibility(View.GONE);
		loginTopContaner.setVisibility(View.GONE);
		unloginTopContaner.setVisibility(View.GONE);
	}
	/*显示通用的标题*/
	public void showCommonTitle() {
		initTitle();
		commonTopContaner.setVisibility(View.VISIBLE);
	}
	/*显示已经登陆的标题*/
	public void showLoginTitle(String userInfo) {
		initTitle();
		loginTopContaner.setVisibility(View.VISIBLE);
		userInfoTextView.setText(userInfo);
	}
	/*显示未登陆的标题*/
	public void showUnLoginTitle() {  
		initTitle();
		unloginTopContaner.setVisibility(View.VISIBLE);
		
	}
	
	/*
	 * 设置标题
	 * */
	public void setTopTitle(int resId) {
		topTitle.setText(resId);
	}
	
	public void setTopTitle(String title) {
		topTitle.setText(title);
	}
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object data) {
		if(data!=null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case ConstantValue.VIEW_FIRST:
				showUnLoginTitle();
				break;
				
			case ConstantValue.VIEW_SECOND:
				showCommonTitle();
				break; 
			}
		}
	}
}
