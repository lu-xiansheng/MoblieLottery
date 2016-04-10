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
 * ���������⣨������
 * */
public class TopManager implements Observer{  
	
	//�����TopManager�ĵ���
	/*1��������ֻ����һ��ʵ����
	2������������Լ������Լ���Ψһʵ����
	3�������������������������ṩ��һʵ����*/
	
	protected static final String TAG = "TopManager";

	private TopManager() {
		
	}
	/*������ֻ����һ��ʵ��*/
	private static TopManager instance = new TopManager();
	
	public static TopManager getinstance() {
		return instance;  
	}
	
	
	/******************����**********************/
	private RelativeLayout commonTopContaner;//ͨ�õı���
	private RelativeLayout loginTopContaner;//��½�����
	private RelativeLayout unloginTopContaner;//δ��¼����
	/****************************************/
	private ImageButton topReturn;//���ذ�ť
	private TextView topTitle;//��������
	private ImageButton topHelp;//������ť
	
	private Button regist;//ע�ᰴť
	private Button login;//��½��ť
	
	private TextView userInfoTextView;//�û���Ϣ
	
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
	/*����������*/
	private void setListener() {
		topReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG,"����˷��ذ�ť");
			}
		});
		
		topHelp.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Log.i(TAG,"����˰�����ť");
				UIManager.getInstance().changeView(SecondView.class);
				
			} 
		});
		
		regist.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				UIManager.getInstance().changeView(SecondView.class);
				Log.i(TAG,"�����ע�ᰴť");
			} 
		});
		
		login.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Log.i(TAG,"����˵�½��ť");
			} 
		});

	}             
	/*
	 * �������еı���
	 * */
	private void initTitle() {
		commonTopContaner.setVisibility(View.GONE);
		loginTopContaner.setVisibility(View.GONE);
		unloginTopContaner.setVisibility(View.GONE);
	}
	/*��ʾͨ�õı���*/
	public void showCommonTitle() {
		initTitle();
		commonTopContaner.setVisibility(View.VISIBLE);
	}
	/*��ʾ�Ѿ���½�ı���*/
	public void showLoginTitle(String userInfo) {
		initTitle();
		loginTopContaner.setVisibility(View.VISIBLE);
		userInfoTextView.setText(userInfo);
	}
	/*��ʾδ��½�ı���*/
	public void showUnLoginTitle() {  
		initTitle();
		unloginTopContaner.setVisibility(View.VISIBLE);
		
	}
	
	/*
	 * ���ñ���
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
