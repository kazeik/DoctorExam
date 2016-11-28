package com.kazeik.doctor.doctorexam.adapter;/**
 * 考务信息
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 16 00:34
 */

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.StudyInfoBean;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 考务信息
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 16 00:34
 */

public class StudyAdapter extends MyBaseAdapter<StudyInfoBean.ReMsgEntity> {
    public StudyAdapter(Context cont) {
        super(cont);
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_studyinfo, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) arg1.getTag();
        }

        StudyInfoBean.ReMsgEntity bean = getSrcData().get(arg0);
        viewHolder.tvStudyInfo.setText(Html.fromHtml(bean.title));
        viewHolder.tvTime.setText(bean.add_time);
        return arg1;
    }

    static class ViewHolder {
        @Bind(R.id.tv_studyInfo)
        TextView tvStudyInfo;
        @Bind(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
