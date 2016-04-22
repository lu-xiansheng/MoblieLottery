package com.lz.moblielottery.view;



import org.apache.commons.lang3.StringUtils;

import com.lz.moblielottery.ConstantValue;
import com.lz.moblielottery.Message;
import com.lz.moblielottery.R;
import com.lz.moblielottery.element.CurrentIssueElement;
import com.lz.moblielottery.service.CommonService;
import com.lz.moblielottery.utill.BeanFactory;
import com.lz.moblielottery.view.manager.PromotManager;
import com.lz.moblielottery.view.manager.UIManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ���ʴ���
 * 
 * @author Administrator
 * 
 */
public class Hall extends BaseView {
	// private LinearLayout container;//���ʴ�������
	private TextView ssqSummary;// ��ʾ��ǰ��������Ϣ
	private ImageView ssqBet;// ˫ɫ�������Ͷע

	public Hall(Context context,Bundle bundle) {
		super(context,bundle);
	}

	protected void init() {
		container = (LinearLayout) inflater.inflate(R.layout.il_hall, null);
		ssqSummary = (TextView) findViewById(R.id.ii_hall_ssq_summary);

		ssqBet = (ImageView) findViewById(R.id.ii_hall_ssq_bet);
	}

	protected void setListener() {
		ssqBet.setOnClickListener(this);
	}

	@Override
	public int getId() {
		return ConstantValue.VIew_HALL;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ii_hall_ssq_bet:
				// ��ת��˫ɫ���ѡ�Ž���
				UIManager.getInstance().changeView(PlaySSQ.class, ssqBundle);
				break;

			default:
				break;
		}
		super.onClick(v);
	}

	@Override
	public void onResume() {
		new HttpTask<Integer, Message>() {
			@Override
			protected Message doInBackground(Integer... params) {
				// �ڽ��棬���÷���㴦���ȡ��ǰ��������Ϣ�ķ���
				CommonService service = BeanFactory.getImpl(CommonService.class);
				Message result = service.getCurrentIssueInfo(params[0]);//����executeProxy()���ݵĲ���
				return result;
			}
			@Override
			protected void onPostExecute(Message result) {//�ı�UI,�ص�UI�߳���ִ��
				// �жϷ��ؽ���Ƿ�Ϊ�գ���Ϊ�յ�ʱ���õ�Oelement���ص�errorCode ��Ϣ
				if (result != null) {
					String errorcode = result.getBody().getOelement().getErrorcode();
					if ("0".equals(errorcode)) {
						// ��ȡelement�����װ����Ϣ���ı���������
						CurrentIssueElement element = (CurrentIssueElement) result.getBody().getElements().get(0);
						changeSummary(element);
					} else {
						PromotManager.showToast(context, result.getBody().getOelement().getErrormsg());
					}
				} else {
					PromotManager.showToast(context, R.string.is_error_network);
				}
				super.onPostExecute(result);
			}
		}.executeProxy(ConstantValue.SSQ);
		super.onResume();
	}
	private Bundle ssqBundle;//˫ɫ������ݷ�װ
	/* �ı������ʾ��Ϣ*/
	protected void changeSummary(CurrentIssueElement element) {
		int lotteryId = Integer.parseInt(element.getLotteryId().getTagValue());
		switch (lotteryId) {
			case ConstantValue.SSQ:
				// ssqSummary
				String issue = element.getIssue();
				String lastTime = element.getLasttime();
				ssqSummary.setText("��" + issue + "�ڻ���" + getLasttime(lastTime) + "ͣ��");
				ssqBundle=new Bundle();
				ssqBundle.putString("lotteryIssue", issue);
				break;
			default:
				break;
		}

	}
	
	/**
	 * ����ʱ��ת������ʱ�ָ�ʽ
	 */
	public String getLasttime(String lasttime) {
		StringBuffer result = new StringBuffer();
		if (StringUtils.isNumericSpace(lasttime)) {
			int time = Integer.parseInt(lasttime);
			int day = time / (24 * 60 * 60);
			result.append(day).append("��");
			if (day > 0) {
				time = time - day * 24 * 60 * 60;
			}
			int hour = time / 3600;
			result.append(hour).append("ʱ");
			if (hour > 0) {
				time = time - hour * 60 * 60;
			}
			int minute = time / 60;
			result.append(minute).append("��");
		}
		return result.toString();
	}

}
