package com.lz.moblielottery;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import com.lz.moblielottery.view.FirstView;
import com.lz.moblielottery.view.SecondView;
import com.lz.moblielottery.view.manager.BottomManager;
import com.lz.moblielottery.view.manager.PromotManager;
import com.lz.moblielottery.view.manager.TopManager;
import com.lz.moblielottery.view.manager.UIManager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	private RelativeLayout middleContainer;
	
	/*private Handler handler = new Handler() {

		
		public void handleMessage(Message msg) {
//			UIManager.getInstance().changeView(new SecondView(MainActivity.this));
//			changeview();
			super.handleMessage(msg);
		}
		
	};*/
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
	
		//��ʽһ��ֱ��new ����
		//��ʽ��������  
		init();  
	}  
	
	private void init() {  
		middleContainer = (RelativeLayout)findViewById(R.id.ii_main_middle);
		UIManager.getInstance().setMiddleContainer(middleContainer);
		
		UIManager.getInstance().addObserver(TopManager.getinstance());
		UIManager.getInstance().addObserver(BottomManager.getInstrance());
		
		BottomManager.getInstrance().init(this);
		TopManager.getinstance().init(this);
		
		TopManager.getinstance().showCommonTitle();
		
		/*FirstView child = new FirstView(this);
		middleContainer.addView(child.getView());*/
		UIManager.getInstance().changeView(FirstView.class);
		
		/*handler.sendEmptyMessageDelayed(0,2000); */ 
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			// �����л��ķ���  
			boolean changeCache = UIManager.getInstance().changeCache();
			if(changeCache == false) {
				//�˳�Ӧ��
				//��ʾ�û��Ƿ��˳�Ӧ�ã�AlertDialog��
				PromotManager.showExitDialog(this, "��ȷ��Ҫ���Ҷ�ȥ��0=0");
 		}else {
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	
}