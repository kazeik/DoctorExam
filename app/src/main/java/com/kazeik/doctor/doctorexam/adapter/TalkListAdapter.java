package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.NotifyListBean;
import com.kazeik.doctor.doctorexam.bean.TalkListBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kazeik.chen on 2016/4/21 0021 13:52.
 * email:kazeik@163.com ,QQ:77132995
 */
public class TalkListAdapter extends MyBaseAdapter<TalkListBean.ReMsgEntity> {
    public TalkListAdapter(Context cont) {
        super(cont);
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_problem_group, arg2, false);
            viewHolder = new ViewHolder(arg1);
            arg1.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) arg1.getTag();
        }
        TalkListBean.ReMsgEntity entity = getSrcData().get(arg0);
        viewHolder.tvGroupText.setText("客服"+entity.uid);
        return arg1;
    }

    static class ViewHolder {
        @Bind(R.id.tv_groupText)
        TextView tvGroupText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
