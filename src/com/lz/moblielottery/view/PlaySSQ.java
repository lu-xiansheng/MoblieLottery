package com.lz.moblielottery.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.R;
import com.lz.moblielottery.view.manager.TopManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;


public class PlaySSQ extends BaseView {

	private Button randomRed;// 机选红球
	private GridView redNumberContainer;// 红球的选号容器
	private Button randomBlue;// 机选蓝球
	private GridView blueNumberContainer;// 蓝球的选号容器  

	/*private PollAdapter redAdapter;
	private PollAdapter blueAdapter;*/

	private List<Integer> redNum;// 用来存储已经选好了的红球
	private List<Integer> blueNum;// 用来存储已经选好了的红球

	public PlaySSQ(Context context, Bundle bundle) {
		super(context, bundle);
	}

	@Override
	public int getId() {
		return ConstantValue.VIEW_SSQ;
	}

	@Override
	protected void init() {
		container = (ViewGroup) inflater.inflate(R.layout.il_play_ssq, null);
		randomBlue = (Button) findViewById(R.id.ii_ssq_random_blue);
		randomRed = (Button) findViewById(R.id.ii_ssq_random_red);

		redNumberContainer = (GridView) findViewById(R.id.ii_ssq_red_number_container);
		blueNumberContainer = (GridView) findViewById(R.id.ii_ssq_blue_number_container);

		redNum = new ArrayList<Integer>();
		blueNum = new ArrayList<Integer>();

		/*redAdapter = new PollAdapter(context, 33, redNum, R.drawable.id_redball);
		redNumberContainer.setAdapter(redAdapter);

		blueAdapter = new PollAdapter(context, 16, blueNum, R.drawable.id_blueball);
		blueNumberContainer.setAdapter(blueAdapter);*/
	}

	@Override
	protected void setListener() {
		/*randomBlue.setOnClickListener(this);
		randomRed.setOnClickListener(this);

		redNumberContainer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 每点击一次：拿到当前的用户点击的号码
				// 判断一下，当前的这个存储红球的已选号码的容器里面是否含有当前用户点击，
				// 如果当前容器里含有用户点击的这个号码，改变他的背景图片为默认
				// 如果容器没有，晃一晃

				int num = (Integer) redAdapter.getItem(position);
				if (redNum.contains(num)) {
					// 如果当前容器里含有用户点击的这个号码，改变他的背景图片为默认
					view.setBackgroundResource(R.drawable.id_defalut_ball);
				} else {
					redNum.add(num);
					view.setBackgroundResource(R.drawable.id_redball);
					// 如果容器没有，晃一晃
					view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.ia_ball_shake));
				}

			}
		});
		blueNumberContainer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 每点击一次：拿到当前的用户点击的号码
				// 判断一下，当前的这个存储红球的已选号码的容器里面是否含有当前用户点击，
				// 如果当前容器里含有用户点击的这个号码，改变他的背景图片为默认
				// 如果容器没有，晃一晃

				int num = (Integer) blueAdapter.getItem(position);
				if (blueNum.contains(num)) {
					// 如果当前容器里含有用户点击的这个号码，改变他的背景图片为默认
					view.setBackgroundResource(R.drawable.id_defalut_ball);
				} else {
					blueNum.add(num);
					view.setBackgroundResource(R.drawable.id_blueball);
					// 如果容器没有，晃一晃
					view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.ia_ball_shake));
				}

			}
		});*/
	}

	/*@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ii_ssq_random_blue:
				// 机选蓝球
				getRandomNum(0, 1);
				break;
			case R.id.ii_ssq_random_red:
				// 机选红球
				// 生成6个红球（随机）
				// 生成（1~33）6个红球，放置一个容器里面，把容器对象传递redAdapter，依据容器里面的号码改变相应球的背景图片
				getRandomNum(6, 0);

				break;

			default:
				break;
		}
		super.onClick(v);
	}*/

	/*private void getRandomNum(int red, int blue) {
		// 区分红球还是蓝球
		// 红球数量 蓝球的数量
		// 6 0 随机生成红球
		// 0 1 随机生成蓝球
		// 6 1 随机生成一注------手机摇啊摇

		if (red > 0) {
			redNum.clear();
			// 生成（1~33）6个红球，放置一个容器里面，把容器对象传递redAdapter，依据容器里面的号码改变相应球的背景图片
			Random random = new Random();
			while (redNum.size() < 6) {
				int num = random.nextInt(33) + 1;
				if (redNum.contains(num)) {
					continue;
				}
				redNum.add(num);
			}
			redAdapter.notifyDataSetChanged();

		}
		if (blue > 0) {
			blueNum.clear();
			// 生成（1~16）1个蓝球，放置一个容器里面，把容器对象传递blueAdapter，依据容器里面的号码改变相应球的背景图片
			Random random = new Random();
			int num = random.nextInt(16) + 1;
			blueNum.add(num);
			blueAdapter.notifyDataSetChanged();
		}

	}*/

	@Override
	public void onResume() {
		setTitle();
		super.onResume();
	}

	// 设置标题
	private void setTitle() {
		if (bundle != null) {
			String issue = bundle.getString("lotteryIssue");
			TopManager.getinstance().setTopTitle("双色球第" + issue + "期");
		} else {
			TopManager.getinstance().setTopTitle(R.string.is_ssq_title_default);
		}

	}

}
