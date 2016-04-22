/**
 * 
 */
package com.lz.moblielottery.service;

import com.lz.moblielottery.Message;

/**处理公共信息
 * @author L.Z.
 * 
 */
public interface CommonService {
	/*
	 * 获取当前销售期信息
	 * */
	Message getCurrentIssueInfo(Integer lotteryId);
	
}
