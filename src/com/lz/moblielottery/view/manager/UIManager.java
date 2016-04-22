package com.lz.moblielottery.view.manager;



import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import org.apache.commons.lang3.StringUtils;

import com.lz.moblielottery.view.BaseView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * �����м��������л����棩
 * 
 * @author Administrator
 * 
 */
public class UIManager extends Observable {
	private static final String TAG = "UiManager1";
	// ����
	private static UIManager instance = new UIManager();

	private UIManager() {
	}

	public static UIManager getInstance() {
		return instance;
	}

	private RelativeLayout middleContainer;// �м�����

	public void setMiddleContainer(RelativeLayout middleContainer) {
		this.middleContainer = middleContainer;
	}

	private static Map<String, BaseView> VIEWMAP = new HashMap<String, BaseView>();// keyΪBaseView����ļ�����

	private BaseView currentView;// ��ǰ��ʾ�Ľ���

	private static LinkedList<String> VIEWBACK = new LinkedList<String>();// ģ��ջ������ջ��Ԫ��

	/**
	 * �����л�
	 * 
	 * @param targetView
	 *            �л���Ŀ�����
	 * @return
	 */
	public boolean changeView1(BaseView targetView) {
		middleContainer.removeAllViews();

		middleContainer.addView(targetView.getView());

		return true;
	}

	/**
	 * �����л�������Ŀ������������ɣ�
	 * 
	 * @param targetView
	 *            �л���Ŀ�����
	 * @return
	 */
	public boolean changeView2(Class<? extends BaseView> targetViewClass) {
		// ����һ���������洢�Ѿ����ɹ���BaseView�������
		BaseView targetView = null;
		// �жϵ�ǰ�Ƿ���Ŀ��Class��ʵ��
		if (VIEWMAP.containsKey(targetViewClass.getSimpleName())) {
			targetView = VIEWMAP.get(targetViewClass.getSimpleName());
		} else {
			// ���û��Ŀ��Class��ʵ��������һ��Ŀ��Class��SecondeView��ʵ����
			try {
				Constructor<? extends BaseView> constructor = targetViewClass.getConstructor(Context.class);
				targetView = constructor.newInstance(middleContainer.getContext());
				// ��ʵ����ӵ��ҵ���������
				VIEWMAP.put(targetViewClass.getSimpleName(), targetView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		middleContainer.removeAllViews();

		Log.i(TAG, targetView.toString());

		View view = targetView.getView();
		Log.i(TAG, view.toString());

		middleContainer.addView(view);

		return true;
	}

	/**
	 * �����л������ƽ����л��������ǰ��ʾ�Ľ�������Ҫ�л���Ŀ����棬ֱ�ӷ���fasle��
	 * 
	 * @param targetView
	 *            �л���Ŀ�����
	 * @return
	 */
	public boolean changeView3(Class<? extends BaseView> targetViewClass) {
		// Ҫ�и����գ���ǰ���������
		// ��Ŀ�������������ս�����бȽ�

		// ���ڶ��ٸ�����
		// if(currentView!=null&&currentView.getClass().getSimpleName().equals(targetViewClass.getSimpleName()))
		if (currentView != null && currentView.getClass() == targetViewClass) {
			// currentView.getClass().getSimpleName().equals(targetViewClass.getSimpleName())
			// 
			return false;
		}

		// ����һ���������洢�Ѿ����ɹ���BaseView�������
		BaseView targetView = null;
		// �жϵ�ǰ�Ƿ���Ŀ��Class��ʵ��
		if (VIEWMAP.containsKey(targetViewClass.getSimpleName())) {
			targetView = VIEWMAP.get(targetViewClass.getSimpleName());
		} else {
			// ���û��Ŀ��Class��ʵ��������һ��Ŀ��Class��SecondeView��ʵ����
			try {
				Constructor<? extends BaseView> constructor = targetViewClass.getConstructor(Context.class);
				targetView = constructor.newInstance(middleContainer.getContext());
				// ��ʵ����ӵ��ҵ���������
				VIEWMAP.put(targetViewClass.getSimpleName(), targetView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		middleContainer.removeAllViews();

		Log.i(TAG, targetView.toString());

		View view = targetView.getView();
		Log.i(TAG, view.toString());

		middleContainer.addView(view);
		// ����ǰ��ʾ�Ľ��渶��currentView����
		currentView = targetView;

		return true;
	}

	/**
	 * �����л�����ӵ�ǰ���浽ջ����
	 * 
	 * @param targetView
	 *            �л���Ŀ�����
	 * @return
	 */
	public boolean changeView4(Class<? extends BaseView> targetViewClass) {
		// Ҫ�и����գ���ǰ���������
		// ��Ŀ�������������ս�����бȽ�

		// ���ڶ��ٸ�����
		// if(currentView!=null&&currentView.getClass().getSimpleName().equals(targetViewClass.getSimpleName()))
		if (currentView != null && currentView.getClass() == targetViewClass) {
			// currentView.getClass().getSimpleName().equals(targetViewClass.getSimpleName())
			// 
			return false;
		}

		// ����һ���������洢�Ѿ����ɹ���BaseView�������
		BaseView targetView = null;
		// �жϵ�ǰ�Ƿ���Ŀ��Class��ʵ��
		if (VIEWMAP.containsKey(targetViewClass.getSimpleName())) {
			targetView = VIEWMAP.get(targetViewClass.getSimpleName());
		} else {
			// ���û��Ŀ��Class��ʵ��������һ��Ŀ��Class��SecondeView��ʵ����
			try {
				Constructor<? extends BaseView> constructor = targetViewClass.getConstructor(Context.class);
				targetView = constructor.newInstance(middleContainer.getContext());
				// ��ʵ����ӵ��ҵ���������
				VIEWMAP.put(targetViewClass.getSimpleName(), targetView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (currentView != null)
			currentView.onPause();
		middleContainer.removeAllViews();

		Log.i(TAG, targetView.toString());

		View view = targetView.getView();
		Log.i(TAG, view.toString());

		targetView.onResume();
		middleContainer.addView(view);
		// ����ǰ��ʾ�Ľ��渶��currentView����
		currentView = targetView;

		// ��ӵ�ǰ���浽ջ��
		VIEWBACK.addFirst(targetViewClass.getSimpleName());

		changeTitleandBottom(targetView.getId());
		return true;
	}

	/**
	 * �����л���������Ϣ�����������֮��
	 * 
	 * @param targetView
	 *            �л���Ŀ�����
	 * @return
	 */
	public boolean changeView(Class<? extends BaseView> targetViewClass, Bundle bundle) {
		if (currentView != null && currentView.getClass() == targetViewClass) {
			return false;
		}
		BaseView targetView = null;
		if (VIEWMAP.containsKey(targetViewClass.getSimpleName())) {
			targetView = VIEWMAP.get(targetViewClass.getSimpleName());
			//����ж�����ǰ��bundle��Ϊ������һ��targetView��bundle��Ϣ
			if (bundle != null) {
				targetView.setBundle(bundle);
			}
		} else {
			try {
				Constructor<? extends BaseView> constructor = targetViewClass.getConstructor(Context.class, Bundle.class);
				targetView = constructor.newInstance(middleContainer.getContext(), bundle);
				VIEWMAP.put(targetViewClass.getSimpleName(), targetView);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (currentView != null)
			currentView.onPause();
		middleContainer.removeAllViews();
		View view = targetView.getView();
		changeTitleandBottom(targetView.getId());
		targetView.onResume();
		middleContainer.addView(view);
		currentView = targetView;
		VIEWBACK.addFirst(targetViewClass.getSimpleName());
		return true;
	}

	/**
	 * �����ذ�ť
	 * 
	 * @return
	 */
	public boolean changeCache() {
		// ��ȡ��ǰ��ջ��Ԫ�أ������������ʾ�Ľ�����ͬ���Ƴ�ջ��Ԫ��
		// �Ƴ�֮�󣬻�ȡ�µ�ջ��Ԫ�أ��û�������л�
		String key = "";// ����ջ��Ԫ��
		if (VIEWBACK.size() >= 1) {
			key = VIEWBACK.getFirst();
		}

		if (StringUtils.isNotBlank(key)) {
			if (currentView.getClass().getSimpleName().equals(key)) {
				if (VIEWBACK.size() == 1) {
					return false;
				} else
					VIEWBACK.removeFirst();

				// ��ȡ��ǰջ��Ԫ��
				return changeCache();
			} else {
				BaseView targetView = VIEWMAP.get(key);
				middleContainer.removeAllViews();
				middleContainer.addView(targetView.getView());
				currentView = targetView;
				changeTitleandBottom(targetView.getId());
				return true;
			}
		}

		return false;
	}

	/**
	 * �л��ײ������ͱ���
	 */
	private void changeTitleandBottom(int id) {
		// �������м������ı任��ȥ�л��ײ��Ͷ�������
		// �����м�������������ʾ�Ľ���

		// ����һ�����м������ļ�����

		// if(currentView.getClass().getSimpleName().equals("First or second"))
		// switch (id) {
		// case ConstantValue.VIEW_FIRST:
		// TopManager1.getInstance().showUnLoginTitle();
		// BottomManager1.getInstrance().showCommonBottom();
		//				
		// break;
		// case ConstantValue.VIEW_SECOND:
		// TopManager1.getInstance().showCommonTitle();
		// BottomManager1.getInstrance().showGameBottom();
		// break;
		// }

		setChanged();
		notifyObservers(id);

	}
}
