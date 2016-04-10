package com.lz.moblielottery.view.manager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import org.apache.commons.lang3.StringUtils;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.view.BaseView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/*
 * 管理中间容器，切换界面
 * */
/**
 * @author L.Z.
 *
 */
/**
 * @author L.Z.
 *
 */
public class UIManager extends Observable{

	private static final String TAG = "view";

	//单例
	private UIManager() {
		
	}
	
	private static UIManager instance = new UIManager();
	
	public static UIManager getInstance() {
		return instance;
	}
	
	private RelativeLayout middleContainer;//中间容器
	
	public void setMiddleContainer(RelativeLayout middleContainer) {
		this.middleContainer = middleContainer;
	}
	
	
	private static Map<String,BaseView> viewMap = new HashMap<String, BaseView>();
	//key为BaseView的简单名称
	private BaseView currentView;//当前显示的界面
	
	private LinkedList<String> VIEWBACK = new LinkedList<String>();//模拟栈，操做栈顶元素
	
	
	/**
	 * 界面切换(控制界面的切换，如果当前显示的界面是我的目标界面，直接返回false)
	 * 添加当前界面到栈顶
	 * @param targetView 切换的目标界面
	 * @return
	 */
	
	/*
	 * 并没有看懂
	 * 不开森*/
	public boolean changeView(Class<? extends BaseView> targetViewClass) {
		
		//要有一个参照，当前的界面
		//将目标界面与这个界面进行比较
		
		
		if(currentView !=null && currentView.getClass() == targetViewClass) {
			return false;
		}
		
		//放置一个容器，存储已经生成的BaseView子类对象
		BaseView targetView=null;
		//判断当前是否含有目标Class的实例
		if(viewMap.containsKey(targetViewClass.getSimpleName())) {  
			targetView = viewMap.get(targetViewClass.getSimpleName());
		}
		else {
			//如果没有目标class实例，创建一个目标class
			try {
				Constructor<? extends BaseView> constructor = targetViewClass.getConstructor(Context.class);
				targetView = constructor.newInstance(middleContainer.getContext());
				//将实例添加容器中
				viewMap.put(targetViewClass.getSimpleName(), targetView);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		middleContainer.removeAllViews();
		
		View view = targetView.getView();
		Log.i(TAG,view.toString()); 
		middleContainer.addView(view);
		//将当前显示的界面赋给currentView对象
		currentView = targetView;
		
		//添加到栈顶
		VIEWBACK.addFirst(targetViewClass.getSimpleName());
		
		changeTitleAndBottom(targetView.getId());
		
		return true;
	}
	
	
	/**
	 * 处理返回按钮
	 * @return
	 */
	public boolean changeCache() {
		//获取当前的栈顶元素，如果与当前显示的界面相同移除栈顶元素
		String key = "";//接收栈顶元素
		if(VIEWBACK.size() >=1) {
			key = VIEWBACK.getFirst();
		}
		
		if(StringUtils.isNotBlank(key)) {
			if(currentView.getClass().getSimpleName().equals(key)) {
				if(VIEWBACK.size() == 1) {
					return false;
				}
				VIEWBACK.removeFirst();
				//获取新的栈顶元素，用户界面切换
				return changeCache();
			}else {
				BaseView targetView = viewMap.get(key);
				middleContainer.removeAllViews();;
				middleContainer.addView(targetView.getView());
				currentView=targetView;
				
				changeTitleAndBottom(targetView.getId());
				
				return true;
			}
		}
		
		return false;
	
	}
	
	/**
	 * 切换标题和底部导航
	 */
	private void changeTitleAndBottom(int id) {
		//根据中间容器的变换，去切换底部和顶部容器
		//根据中间容器里正在显示的界面
		setChanged();
		notifyObservers(id);
	}
	
	
}
