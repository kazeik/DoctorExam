package com.kazeik.doctor.doctorexam.adapter;/**
 * 索引卡
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 20 21:33
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.IndexSelectBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 索引卡
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 20 21:33
 */

public class ExamIndexAdapter extends MyBaseAdapter<IndexSelectBean> {
    public ExamIndexAdapter(Context cont) {
        super(cont);
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (arg1 == null) {
            arg1 = inflater.inflate(R.layout.layout_exam_index, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) arg1.getTag();
        }
        IndexSelectBean indexSelectBean = getSrcData().get(arg0);
        viewHolder.tvIndex.setText((arg0+1)+"");
        viewHolder.cbIndex.setChecked(!indexSelectBean.select);
        return arg1;
    }

    static class ViewHolder {
        @Bind(R.id.tv_index)
        TextView tvIndex;
        @Bind(R.id.cb_index)
        CheckBox cbIndex;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
