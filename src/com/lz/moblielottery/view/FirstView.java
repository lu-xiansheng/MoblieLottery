package com.lz.moblielottery.view;


import com.lz.moblielottery.ConstantValue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * ��һ����
 * @author Administrator
 *
 */


public class FirstView  extends BaseView{
	
	private TextView textView;
	
	public FirstView(Context context) {
		super(context);
		init();
	}

	
	private void init() {
		//�򵥽��棺
		textView = new TextView(context);   

		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		textView.setLayoutParams(layoutParams);

		textView.setBackgroundColor(Color.BLUE);
		textView.setText("���ǵ�һ������");
		
	}


	public View getView() {
	
		return textView;

	}


	/* (non-Javadoc)
	 * @see com.lz.moblielottery.view.BaseView#getId()
	 */
	@Override
	public int getId() {
		return ConstantValue.VIEW_FIRST;
	}

}


