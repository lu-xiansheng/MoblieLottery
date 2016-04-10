package com.lz.moblielottery.view;

import android.content.Context;
import android.view.View;


	
	public BaseView(Context context) {
		super();
		this.context = context;
	}
	
	/*
	 * ��ȡ��ǰView����
	 * */
	public abstract View getView();
	
	/*
	 * �õ���ǰ������Ψһ��ʶ
	 * */
	public abstract int getId();
	
}
