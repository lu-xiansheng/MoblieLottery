package com.lz.moblielottery.view;

import android.content.Context;
import android.view.View;

public abstract class BaseView {
	
	protected Context context;     

	public BaseView(Context context){
		super();
		this.context = context;
	}
	
	/*
	 * 閿熸枻鎷峰彇閿熸枻鎷峰墠View閿熸枻鎷烽敓鏂ゆ嫹
	 * */
	public abstract View getView();
	
	/*
	 * 閿熺煫纰夋嫹閿熸枻鎷峰墠閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰敮涓�閿熸枻鎷疯瘑
	 * */
	public abstract int getId();
	

}
