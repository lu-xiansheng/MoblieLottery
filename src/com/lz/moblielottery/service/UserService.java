package com.lz.moblielottery.service;

import com.lz.moblielottery.Message;
import com.lz.moblielottery.bean.User;



public interface UserService {
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	Message login(User user);
}
