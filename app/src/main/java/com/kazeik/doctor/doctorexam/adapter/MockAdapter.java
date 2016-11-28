package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.MockItemBean;
import com.kazeik.doctor.doctorexam.utils.SdcardUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kazeik.chen on 2016/4/20 0020 12:56.
 * email:kazeik@163.com ,QQ:77132995
 */
public class MockAdapter extends MyBaseAdapter<MockItemBean.ReMsgEntity> {
    OnAnswerEventListener eventListener;
    SdcardUtils sdcardUtils;
    public void setEventListener(OnAnswerEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public MockAdapter(Context cont) {
        super(cont);
        sdcardUtils = new SdcardUtils();
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        final SubView subView;
        if (null == arg1) {
            arg1 = inflater.inflate(R.layout.layout_mock, arg2, false);
            subView = new SubView(arg1);
            arg1.setTag(subView);
        } else {
            subView = (SubView) arg1.getTag();
        }


      final  MockItemBean.ReMsgEntity item = getSrcData().get(arg0);
        subView.tvName.setText(item.name);
        subView.tvPrice.setText(item.price + "元");
        subView.tvType.setText(item.type);
        switch (item.show_type) {
            case 1://1: 下载 OR 开始作答,要判断本地是否有
                File file = new File(sdcardUtils.getSDCardPath()+"DoctorExam/"+item.id+"_"+item.security_code);
                if (file == null || !file.exists()) {
                    subView.tvAnswer.setText("下载");
                } else {
                    subView.tvAnswer.setText("开始作答");
                }
                break;
            case 2:
                subView.tvAnswer.setText("已经加入购物车");
                break;
            case 3:
                subView.tvAnswer.setText("等待支付");
                break;
            case 4:
                subView.tvAnswer.setText("加入购物车");
                break;
            case 5:
                subView.tvAnswer.setText(R.string.check);
                break;
        }
        subView.tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventListener != null)
                    eventListener.onAnswerEvent(arg0,item.show_type);
            }
        });
        return arg1;
    }

    public interface OnAnswerEventListener {
        public void onAnswerEvent(int pos,int type);
    }

    static class SubView {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_type)
        TextView tvType;

        @Bind(R.id.tv_answer)
        TextView tvAnswer;

        public SubView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
