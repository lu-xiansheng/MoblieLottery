package com.lz.moblielottery.view;


import com.lz.moblielottery.ConstantValue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * �ڶ�����
 * @author Administrator
 *
 */


public class SecondView extends BaseView{

	private TextView textView;
	public SecondView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		//�򵥽��棺
		textView = new TextView(context);

		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		textView.setLayoutParams(layoutParams);

		textView.setBackgroundColor(Color.GREEN);
		textView.setText("���ǵڶ�������");				
	}

	public View getView() {
		
		return textView;

	}

	/* (non-Javadoc)
	 * @see com.lz.moblielottery.view.BaseView#getId()
	 */
	@Override
	public int getId() {
		return ConstantValue.VIEW_SECOND;
	}
	
}


