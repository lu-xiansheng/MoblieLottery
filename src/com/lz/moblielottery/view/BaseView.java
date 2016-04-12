package com.lz.moblielottery.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;

public abstract class BaseView {
	
	protected Context context;  
	
	protected static Handler myHandler;
	
	public static void setMyHandler(Handler myHandler) {
		BaseView.myHandler = myHandler;
	}

	public BaseView(Context context){
		super();
		this.context = context;
	}
	
	
	public abstract View getView();
	
	
	public abstract int getId();
	

}
