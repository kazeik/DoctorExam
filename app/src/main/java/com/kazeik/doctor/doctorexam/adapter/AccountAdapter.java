package com.kazeik.doctor.doctorexam.adapter;/**
 * 我的帐户列表
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 23:53
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.AccountBean;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的帐户列表
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 23:53
 */

public class AccountAdapter extends MyBaseAdapter<AccountBean.ReMsgEntity.DetailArrayEntity> {
    public AccountAdapter(Context cont) {
        super(cont);
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_account, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) arg1.getTag();
        }
        AccountBean.ReMsgEntity.DetailArrayEntity bean = getSrcData().get(arg0);
        viewHolder.tvMoney.setText(bean.in_out_amount_st == 1?bean.amount:"-"+bean.amount);
        viewHolder.tvInfo.setText(bean.pay_type+" "+ bean.in_out_amount);
        viewHolder.tvTime.setText(bean.order_id);
        viewHolder.tvTimeInfo.setText( bean.order_time);
        return arg1;
    }

    static class ViewHolder {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_timeInfo)
        TextView tvTimeInfo;
        @Bind(R.id.tv_money)
        TextView tvMoney;
        @Bind(R.id.tv_info)
        TextView tvInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
