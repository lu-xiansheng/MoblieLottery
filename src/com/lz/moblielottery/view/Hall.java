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
 * 购彩大厅
 * 
 * @author Administrator
 * 
 */
public class Hall extends BaseView {
	// private LinearLayout container;//购彩大厅界面
	private TextView ssqSummary;// 显示当前销售期信息
	private ImageView ssqBet;// 双色球的立即投注

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
				// 跳转到双色球的选号界面
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
				// 在界面，调用服务层处理获取当前销售期信息的方法
				CommonService service = BeanFactory.getImpl(CommonService.class);
				Message result = service.getCurrentIssueInfo(params[0]);//就是executeProxy()传递的参数
				return result;
			}
			@Override
			protected void onPostExecute(Message result) {//改变UI,回到UI线程中执行
				// 判断返回结果是否为空，不为空的时候拿到Oelement返回的errorCode 信息
				if (result != null) {
					String errorcode = result.getBody().getOelement().getErrorcode();
					if ("0".equals(errorcode)) {
						// 获取element里面封装的信息，改变界面的内容
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
	private Bundle ssqBundle;//双色球的数据封装
	/* 改变界面提示信息*/
	protected void changeSummary(CurrentIssueElement element) {
		int lotteryId = Integer.parseInt(element.getLotteryId().getTagValue());
		switch (lotteryId) {
			case ConstantValue.SSQ:
				// ssqSummary
				String issue = element.getIssue();
				String lastTime = element.getLasttime();
				ssqSummary.setText("第" + issue + "期还有" + getLasttime(lastTime) + "停售");
				ssqBundle=new Bundle();
				ssqBundle.putString("lotteryIssue", issue);
				break;
			default:
				break;
		}

	}
	
	/**
	 * 将秒时间转换成日时分格式
	 */
	public String getLasttime(String lasttime) {
		StringBuffer result = new StringBuffer();
		if (StringUtils.isNumericSpace(lasttime)) {
			int time = Integer.parseInt(lasttime);
			int day = time / (24 * 60 * 60);
			result.append(day).append("天");
			if (day > 0) {
				time = time - day * 24 * 60 * 60;
			}
			int hour = time / 3600;
			result.append(hour).append("时");
			if (hour > 0) {
				time = time - hour * 60 * 60;
			}
			int minute = time / 60;
			result.append(minute).append("分");
		}
		return result.toString();
	}

}
