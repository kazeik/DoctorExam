package com.kazeik.doctor.doctorexam.adapter;/**
 * 我的课程
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 23:19
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.LessonItemBean;
import com.kazeik.doctor.doctorexam.utils.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的课程
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 23:19
 */

public class MyLessonAdapter extends MyBaseAdapter<LessonItemBean> {
    Context activity;
    boolean showMoney;
    onItemEventListener itemEventListener;

    public MyLessonAdapter(Context cont) {
        super(cont);
        activity = cont;
    }

    public void setShowMoney(boolean showMoney) {
        this.showMoney = showMoney;
    }

    public void setItemEventListener(onItemEventListener itemEventListener) {
        this.itemEventListener = itemEventListener;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (arg1 == null) {
            arg1 = inflater.inflate(R.layout.layout_mylesson, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) arg1.getTag();
        }

        final LessonItemBean bean = getSrcData().get(arg0);
        bitUtils.display(viewHolder.ivIcon, "http://" + bean.pic);
        viewHolder.tvLessonBody.setText(bean.describe);
        viewHolder.tvLessonName.setText(bean.title);
        viewHolder.tvLessonMoney.setText("价格：￥" + (AppUtils.mul(bean.original_cost,bean.rate) /100));
        if (showMoney) {
            viewHolder.tvLessonMoney.setVisibility(View.VISIBLE);
            if (bean.is_buy == 1) {
                viewHolder.btnCheck.setText("已购买");
            } else if(bean.is_buy == 2){
                if(bean.in_cart == 1){
                    viewHolder.btnCheck.setText("已加入购物车");
                }else if(bean.in_cart == 2){
                    viewHolder.btnCheck.setText("加入购物车");
                }
            }

        } else {
            viewHolder.tvLessonMoney.setVisibility(View.GONE);
            viewHolder.btnCheck.setText("查看");
        }
        viewHolder.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemEventListener != null) {
                    itemEventListener.onItemEvent(arg0,bean);
                }
            }
        });
        return arg1;
    }


    static class ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_lessonName)
        TextView tvLessonName;
        @Bind(R.id.btn_check)
        Button btnCheck;
        @Bind(R.id.tv_lessonBody)
        TextView tvLessonBody;
        @Bind(R.id.tv_lessonMoney)
        TextView tvLessonMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface onItemEventListener {
        public void onItemEvent(int index,LessonItemBean lessonBean);
    }
}
