package com.kazeik.doctor.doctorexam.adapter;/**
 * 订单列表中的list
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 16 09:44
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.MyLessonBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单列表中的list
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 16 09:44
 */

public class OrderListAdapter extends MyBaseAdapter<MyLessonBean.ReMsgEntity> {
    OnInfoEventListener infoEventListener;


    public OrderListAdapter(Context cont) {
        super(cont);
    }

    public void setInfoEventListener(OnInfoEventListener infoEventListener) {
        this.infoEventListener = infoEventListener;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_orderlist1, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) arg1.getTag();
        }

        final MyLessonBean.ReMsgEntity reMsgEntity = getSrcData().get(arg0);
        viewHolder.tvName.setText(reMsgEntity.name);
        viewHolder.tvMoneyValue.setText(reMsgEntity.total_amount);
        viewHolder.tvState.setText(reMsgEntity.order_status);
        viewHolder.tvOrderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != infoEventListener)
                    infoEventListener.onInfoEvent(arg0, reMsgEntity);
            }
        });

        viewHolder.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != infoEventListener)
                    infoEventListener.onOrderCancelEvent(arg0, reMsgEntity);
            }
        });
        viewHolder.tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != infoEventListener)
                    infoEventListener.onOrderPayEvent(arg0, reMsgEntity);
            }
        });

        if (reMsgEntity.ost == 1) {
            viewHolder.tvCancelOrder.setVisibility(View.VISIBLE);
            viewHolder.tvPay.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvCancelOrder.setVisibility(View.GONE);
            viewHolder.tvPay.setVisibility(View.GONE);
        }

        return arg1;
    }

    @OnClick({R.id.tv_cancelOrder, R.id.tv_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancelOrder:
                break;
            case R.id.tv_pay:
                break;
        }
    }

    public interface OnInfoEventListener {
        public void onInfoEvent(int arg, MyLessonBean.ReMsgEntity msgEntity);

        public void onOrderCancelEvent(int arg, MyLessonBean.ReMsgEntity msgEntity);

        public void onOrderPayEvent(int arg, MyLessonBean.ReMsgEntity msgEntity);
    }


    static class ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_moneyValue)
        TextView tvMoneyValue;
        @Bind(R.id.tv_state)
        TextView tvState;
        @Bind(R.id.tv_orderInfo)
        TextView tvOrderInfo;
        @Bind(R.id.tv_cancelOrder)
        TextView tvCancelOrder;
        @Bind(R.id.tv_pay)
        TextView tvPay;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
