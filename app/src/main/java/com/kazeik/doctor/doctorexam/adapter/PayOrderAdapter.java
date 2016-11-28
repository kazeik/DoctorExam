package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.MockItemBean;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kazeik.chen on 2016/5/4 0004 13:11.
 * email:kazeik@163.com ,QQ:77132995
 */
public class PayOrderAdapter extends MyBaseAdapter<MockItemBean.ReMsgEntity> {
    OnDeleteEventListener deleteEventListener;
    HashMap<Integer, Boolean> hash = new HashMap<>();

    public void setDeleteEventListener(OnDeleteEventListener deleteEventListener) {
        this.deleteEventListener = deleteEventListener;
    }

    public HashMap<Integer, Boolean> getHash() {
        return hash;
    }

    public PayOrderAdapter(Context cont) {
        super(cont);
    }

    public interface OnDeleteEventListener {
        public void onDeleteEvent(int pos, MockItemBean.ReMsgEntity item);
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_shopcar, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) arg1.getTag();
        }
        final MockItemBean.ReMsgEntity item = getSrcData().get(arg0);
        viewHolder.tvMoney.setText(Html.fromHtml("金额小计：<font color='#e97525'>" + item.price+ "</font>"));
        viewHolder.tvName.setText(item.name);
        viewHolder.tvType.setText("商品分类：" + item.order_type );
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != deleteEventListener) {
                    deleteEventListener.onDeleteEvent(arg0, item);
                }
            }
        });
        viewHolder.cbIndex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hash.put(arg0, isChecked);
            }
        });
        return arg1;
    }

    static class ViewHolder {
        @Bind(R.id.cb_index)
        CheckBox cbIndex;
        @Bind(R.id.iv_delete)
        ImageView ivDelete;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_type)
        TextView tvType;
        @Bind(R.id.tv_money)
        TextView tvMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
