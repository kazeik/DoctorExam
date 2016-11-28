package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.PinMoneyBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kazeik.chen on 2016/4/22 0022 15:48.
 * email:kazeik@163.com ,QQ:77132995
 */
public class PinMoneyAdapter extends MyBaseAdapter<PinMoneyBean.SurveyListBean> {
    OnJoinEventListener joinEventListener;

    public PinMoneyAdapter(Context cont) {
        super(cont);
    }

    public void setJoinEventListener(OnJoinEventListener joinEventListener) {
        this.joinEventListener = joinEventListener;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (arg1 == null) {
            arg1 = inflater.inflate(R.layout.layout_pinmoney, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) arg1.getTag();
        }
        final PinMoneyBean.SurveyListBean listBean = getSrcData().get(arg0);
        viewHolder.tvPinTitle.setText(listBean.title);
        viewHolder.tvEndTime.setText("结束：" + listBean.end_time);
        viewHolder.tvPayMoney.setText("支付现金：" + listBean.c_point);
        bitUtils.display(viewHolder.ivIcon, listBean.img);
        viewHolder.ivJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != joinEventListener)
                    joinEventListener.onJoinEvent(listBean);
            }
        });
        return arg1;
    }

    public interface OnJoinEventListener {
        public void onJoinEvent(PinMoneyBean.SurveyListBean listBean);
    }

    static class ViewHolder {
        @Bind(R.id.tv_pinTitle)
        TextView tvPinTitle;
        @Bind(R.id.tv_endTime)
        TextView tvEndTime;
        @Bind(R.id.tv_payMoney)
        TextView tvPayMoney;
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.iv_join)
        ImageView ivJoin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
