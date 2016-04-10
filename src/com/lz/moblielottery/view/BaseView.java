package com.lz.moblielottery.view;

import android.content.Context;
import android.view.View;


public abstract class BaseView {
	protected Context context;
	
	public BaseView(Context context) {
		super();
		this.context = context;
	}
	
	/*
	 * 抽取当前View方法
	 * */
	public abstract View getView();
	
	/*
	 * 拿到当前容器的唯一标识
	 * */
	public abstract int getId();
	
}
