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

	private Button randomRed;// ��ѡ����
	private GridView redNumberContainer;// �����ѡ������
	private Button randomBlue;// ��ѡ����
	private GridView blueNumberContainer;// �����ѡ������  

	/*private PollAdapter redAdapter;
	private PollAdapter blueAdapter;*/

	private List<Integer> redNum;// �����洢�Ѿ�ѡ���˵ĺ���
	private List<Integer> blueNum;// �����洢�Ѿ�ѡ���˵ĺ���

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
				// ÿ���һ�Σ��õ���ǰ���û�����ĺ���
				// �ж�һ�£���ǰ������洢�������ѡ��������������Ƿ��е�ǰ�û������
				// �����ǰ�����ﺬ���û������������룬�ı����ı���ͼƬΪĬ��
				// �������û�У���һ��

				int num = (Integer) redAdapter.getItem(position);
				if (redNum.contains(num)) {
					// �����ǰ�����ﺬ���û������������룬�ı����ı���ͼƬΪĬ��
					view.setBackgroundResource(R.drawable.id_defalut_ball);
				} else {
					redNum.add(num);
					view.setBackgroundResource(R.drawable.id_redball);
					// �������û�У���һ��
					view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.ia_ball_shake));
				}

			}
		});
		blueNumberContainer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ÿ���һ�Σ��õ���ǰ���û�����ĺ���
				// �ж�һ�£���ǰ������洢�������ѡ��������������Ƿ��е�ǰ�û������
				// �����ǰ�����ﺬ���û������������룬�ı����ı���ͼƬΪĬ��
				// �������û�У���һ��

				int num = (Integer) blueAdapter.getItem(position);
				if (blueNum.contains(num)) {
					// �����ǰ�����ﺬ���û������������룬�ı����ı���ͼƬΪĬ��
					view.setBackgroundResource(R.drawable.id_defalut_ball);
				} else {
					blueNum.add(num);
					view.setBackgroundResource(R.drawable.id_blueball);
					// �������û�У���һ��
					view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.ia_ball_shake));
				}

			}
		});*/
	}

	/*@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ii_ssq_random_blue:
				// ��ѡ����
				getRandomNum(0, 1);
				break;
			case R.id.ii_ssq_random_red:
				// ��ѡ����
				// ����6�����������
				// ���ɣ�1~33��6�����򣬷���һ���������棬���������󴫵�redAdapter��������������ĺ���ı���Ӧ��ı���ͼƬ
				getRandomNum(6, 0);

				break;

			default:
				break;
		}
		super.onClick(v);
	}*/

	/*private void getRandomNum(int red, int blue) {
		// ���ֺ���������
		// �������� ���������
		// 6 0 ������ɺ���
		// 0 1 �����������
		// 6 1 �������һע------�ֻ�ҡ��ҡ

		if (red > 0) {
			redNum.clear();
			// ���ɣ�1~33��6�����򣬷���һ���������棬���������󴫵�redAdapter��������������ĺ���ı���Ӧ��ı���ͼƬ
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
			// ���ɣ�1~16��1�����򣬷���һ���������棬���������󴫵�blueAdapter��������������ĺ���ı���Ӧ��ı���ͼƬ
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

	// ���ñ���
	private void setTitle() {
		if (bundle != null) {
			String issue = bundle.getString("lotteryIssue");
			TopManager.getinstance().setTopTitle("˫ɫ���" + issue + "��");
		} else {
			TopManager.getinstance().setTopTitle(R.string.is_ssq_title_default);
		}

	}

}
