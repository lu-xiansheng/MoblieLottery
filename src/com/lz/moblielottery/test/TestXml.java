package com.lz.moblielottery.test;



import com.lz.moblielottery.Message;
import com.lz.moblielottery.bean.User;
import com.lz.moblielottery.element.UserLoginElement;
import com.lz.moblielottery.element.UserRegisterElement;
import com.lz.moblielottery.service.CommonService;
import com.lz.moblielottery.service.UserService;
import com.lz.moblielottery.utill.BeanFactory;

import android.test.AndroidTestCase;
import android.util.Log;


public class TestXml extends AndroidTestCase {
	private static final String TAG = "TestXml";

	public void getXml()
	{
		UserLoginElement element=new UserLoginElement();
		element.getActpassword().setTagValue("111111");
		
		
		Message message=new Message();
		message.getHeader().getUsername().setTagValue("13200000000");
		String xml = message.getXml(element);
		Log.i(TAG, xml);
	}
	
	public void getUserRegisteElementXml()
	{
		UserRegisterElement element=new UserRegisterElement();
		element.getActpassword().setTagValue("000000");
		
		
		Message message=new Message();
		message.getHeader().getUsername().setTagValue("13200000000");
		message.getXml(element);
	}
	
	public void testUserLogin()
	{
		UserService service=BeanFactory.getImpl(UserService.class);
		
		User user=new User();
		user.setUsername("13200000000");
		user.setPassword("00000000");
		Message login = service.login(user);
		Log.i(TAG, login.getBody().getOelement().getErrorcode());
	}
	
	public void testGetCurrentIssueInfo()
	{
		CommonService service=BeanFactory.getImpl(CommonService.class);
		Message currentIssueInfo = service.getCurrentIssueInfo(118);
		Log.i(TAG, currentIssueInfo.getBody().getOelement().getErrorcode());
	}
	
	
}
