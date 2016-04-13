package com.lz.moblielottery.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;

public abstract class BaseView implements View.OnClickListener{
	
	protected Context context;  
	
	protected static Handler myHandler;
	
	public static void setMyHandler(Handler myHandler) {
		BaseView.myHandler = myHandler;
	}

	public BaseView(Context context){
		super();
		this.context = context;
		init();//初始化
		setListener();//设置监听
	}
	
	
	
	protected abstract void setListener();

	
	protected abstract void init();

	public abstract View getView();
	
	
	public abstract int getId();

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
