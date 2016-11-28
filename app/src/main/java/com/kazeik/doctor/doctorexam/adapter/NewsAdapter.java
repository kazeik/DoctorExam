package com.kazeik.doctor.doctorexam.adapter;

import android.app.Fragment;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.NotifyListBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kazeik.chen on 2016/4/19 0019 13:27.
 * email:kazeik@163.com ,QQ:77132995
 */
public class NewsAdapter extends MyBaseAdapter<NotifyListBean.ReMsgEntity> {
    private OnSubChildEvent childEvent;
    OnGroupEvent groupEvent;

    public void setChildEvent(OnSubChildEvent childEvent) {
        this.childEvent = childEvent;
    }

    public void setGroupEvent(OnGroupEvent groupEvent) {
        this.groupEvent = groupEvent;
    }

    public NewsAdapter(Context cont) {
        super(cont);
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        SubView subView;
        NotifyListBean.ReMsgEntity msgEntity = getSrcData().get(arg0);
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_news_item, arg2, false);
            subView = new SubView(arg1);
            arg1.setTag(subView);
        } else {
            subView = (SubView) arg1.getTag();
        }
        bitUtils.display(subView.ivTopIcon, "http://" + msgEntity.image);
        subView.tvTopTitle.setText(msgEntity.title);
        subView.flTopFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupEvent.onGroupEvent(arg0);
            }
        });
        if (msgEntity.message != null && !msgEntity.message.isEmpty()) {
            for (int i = 0; i < msgEntity.message.size(); i++) {
                View view = inflater.inflate(R.layout.layout_news_sub, null);
                View line = inflater.inflate(R.layout.view_line,null);
                TextView textView = (TextView) view.findViewById(R.id.tv_subTitle);
                ImageView ivSubIcon = (ImageView) view.findViewById(R.id.iv_subIcon);
//                ivSubIcon.setImageResource(R.mipmap.ic_launcher);
                textView.setText(Html.fromHtml(msgEntity.message.get(i).title));
                subView.llsSubView.addView(view);
                if(i< msgEntity.message.size()-1){
                    subView.llsSubView.addView(line);
                }
                final int index = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (childEvent != null) {
                            childEvent.onChildEvent(arg0, index);
                        }
                    }
                });
            }
        }

        return arg1;
    }

    public interface OnSubChildEvent {
        public void onChildEvent(int parent, int child);
    }

    public interface OnGroupEvent {
        public void onGroupEvent(int parent);
    }

    static class SubView {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.iv_topIcon)
        ImageView ivTopIcon;
        @Bind(R.id.tv_topTitle)
        TextView tvTopTitle;
        @Bind(R.id.lls_subView)
        LinearLayout llsSubView;
        @Bind(R.id.fl_topFrame)
        FrameLayout flTopFrame;

        public SubView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
