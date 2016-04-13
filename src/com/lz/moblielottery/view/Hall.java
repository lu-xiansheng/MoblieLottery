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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author L.Z.
 *
 */
public class Hall extends BaseView {
	private LinearLayout container;//购彩大厅界面
	private TextView ssg_Summary;//显示当前销售信息
	private ImageView ssg_bet;//双色球 立即投注 

	public Hall(Context context) {
		super(context);
	}

	
	protected void init() { 
		LayoutInflater inflater = LayoutInflater.from(context);
		container = (LinearLayout)inflater.inflate(R.layout.il_hall, null);
		
		container.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	
		ssg_Summary = (TextView) container.findViewById(R.id.ii_hall_ssq_summary);
		ssg_bet = (ImageView) container.findViewById(R.id.ii_hall_ssq_bet);
	}

	public View getView() {
		return container;
	}

	
	public int getId() {
		return ConstantValue.VIew_HALL;
	}

	protected void setListener() {
		ssg_bet.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ii_hall_ssq_bet:
			 //TODO 跳转到双色球选号界面
			break;

		default:
			break;
		}
		super.onClick(v);
	}

	
}
