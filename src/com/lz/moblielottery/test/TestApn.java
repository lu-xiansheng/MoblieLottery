package com.lz.moblielottery.test;

import com.lz.moblielottery.utill.NetUtil;

import android.test.AndroidTestCase;

public class TestApn extends AndroidTestCase {
	public void getCurrentApn() {
		NetUtil.setApnParame(getContext());
	}
}
