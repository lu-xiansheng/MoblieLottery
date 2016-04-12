/**
 * 
 */
package com.lz.moblielottery.view;

import com.lz.moblielottery.ConstantValue;


import com.lz.moblielottery.R;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author L.Z.
 *
 */
public class Hall extends BaseView {
	private LinearLayout container;//¹º²Ê´óÌü½çÃæ

	public Hall(Context context) {
		super(context);
		init();
	}

	
	private void init() { 
		LayoutInflater inflater = LayoutInflater.from(context);
		container = (LinearLayout)inflater.inflate(R.layout.il_hall, null);
		
		container.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	}

	public View getView() {
		return container;
	}

	
	public int getId() {
		return ConstantValue.VIew_HALL;
	}

}
