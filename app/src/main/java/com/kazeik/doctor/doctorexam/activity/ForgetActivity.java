//package com.kazeik.doctor.doctorexam.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.kazeik.doctor.doctorexam.BaseActivity;
//import com.kazeik.doctor.doctorexam.R;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class ForgetActivity extends BaseActivity {
//
//
//    @Bind(R.id.tv_titleName)
//    TextView tvTitleName;
//    @Bind(R.id.tv_left)
//    TextView tvLeft;
//
//    @Override
//    public int initLayout() {
//        return R.layout.activity_forget;
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public void initWeight() {
//        tvTitleName.setText("找回密码");
//        tvLeft.setText("返回");
//        tvLeft.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void onNetSuccess(String tag, String value) {
//
//    }
//
//    @OnClick(R.id.tv_left)
//    public void onClick() {
//        this.finish();
//    }
//}
