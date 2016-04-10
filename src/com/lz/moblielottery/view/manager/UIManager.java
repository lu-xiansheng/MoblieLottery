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
 * �����м��������л�����
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

	//����
	private UIManager() {
		
	}
	
	private static UIManager instance = new UIManager();
	
	public static UIManager getInstance() {
		return instance;
	}
	
	private RelativeLayout middleContainer;//�м�����
	
	public void setMiddleContainer(RelativeLayout middleContainer) {
		this.middleContainer = middleContainer;
	}
	
	
	private static Map<String,BaseView> viewMap = new HashMap<String, BaseView>();
	//keyΪBaseView�ļ�����
	private BaseView currentView;//��ǰ��ʾ�Ľ���
	
	private LinkedList<String> VIEWBACK = new LinkedList<String>();//ģ��ջ������ջ��Ԫ��
	
	
	/**
	 * �����л�(���ƽ�����л��������ǰ��ʾ�Ľ������ҵ�Ŀ����棬ֱ�ӷ���false)
	 * ��ӵ�ǰ���浽ջ��
	 * @param targetView �л���Ŀ�����
	 * @return
	 */
	
	/*
	 * ��û�п���
	 * ����ɭ*/
	public boolean changeView(Class<? extends BaseView> targetViewClass) {
		
		//Ҫ��һ�����գ���ǰ�Ľ���
		//��Ŀ����������������бȽ�
		
		
		if(currentView !=null && currentView.getClass() == targetViewClass) {
			return false;
		}
		
		//����һ���������洢�Ѿ����ɵ�BaseView�������
		BaseView targetView=null;
		//�жϵ�ǰ�Ƿ���Ŀ��Class��ʵ��
		if(viewMap.containsKey(targetViewClass.getSimpleName())) {  
			targetView = viewMap.get(targetViewClass.getSimpleName());
		}
		else {
			//���û��Ŀ��classʵ��������һ��Ŀ��class
			try {
				Constructor<? extends BaseView> constructor = targetViewClass.getConstructor(Context.class);
				targetView = constructor.newInstance(middleContainer.getContext());
				//��ʵ�����������
				viewMap.put(targetViewClass.getSimpleName(), targetView);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		middleContainer.removeAllViews();
		
		View view = targetView.getView();
		Log.i(TAG,view.toString()); 
		middleContainer.addView(view);
		//����ǰ��ʾ�Ľ��渳��currentView����
		currentView = targetView;
		
		//��ӵ�ջ��
		VIEWBACK.addFirst(targetViewClass.getSimpleName());
		
		changeTitleAndBottom(targetView.getId());
		
		return true;
	}
	
	
	/**
	 * �����ذ�ť
	 * @return
	 */
	public boolean changeCache() {
		//��ȡ��ǰ��ջ��Ԫ�أ�����뵱ǰ��ʾ�Ľ�����ͬ�Ƴ�ջ��Ԫ��
		String key = "";//����ջ��Ԫ��
		if(VIEWBACK.size() >=1) {
			key = VIEWBACK.getFirst();
		}
		
		if(StringUtils.isNotBlank(key)) {
			if(currentView.getClass().getSimpleName().equals(key)) {
				if(VIEWBACK.size() == 1) {
					return false;
				}
				VIEWBACK.removeFirst();
				//��ȡ�µ�ջ��Ԫ�أ��û������л�
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
	 * �л�����͵ײ�����
	 */
	private void changeTitleAndBottom(int id) {
		//�����м������ı任��ȥ�л��ײ��Ͷ�������
		//�����м�������������ʾ�Ľ���
		setChanged();
		notifyObservers(id);
	}
	
	
}
