//package com.kazeik.doctor.doctorexam.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.kazeik.doctor.doctorexam.BaseActivity;
//import com.kazeik.doctor.doctorexam.R;
//import com.kazeik.doctor.doctorexam.utils.ApiUtils;
//import com.lidroid.xutils.http.RequestParams;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class ExamAnswerActivity extends BaseActivity {
//
//    @Bind(R.id.tv_titleName)
//    TextView tvTitleName;
//    @Bind(R.id.tv_left)
//    TextView tvLeft;
//    @Bind(R.id.tv_right)
//    TextView tvRight;
//    @Bind(R.id.gd_view)
//    GridView gdView;
//    @Bind(R.id.iv_state)
//    ImageView ivState;
//    @Bind(R.id.tv_state)
//    TextView tvState;
//    @Bind(R.id.iv_level)
//    ImageView ivLevel;
//
//    String encryption;
//
//    @Override
//    public int initLayout() {
//        return R.layout.activity_exam_answer;
//    }
//
//    @Override
//    public void initData() {
//        encryption = getIntent().getStringExtra("encryption");
//    }
//
//    @Override
//    public void initWeight() {
//        tvTitleName.setText("查看答案");
//        tvLeft.setVisibility(View.VISIBLE);
//        tvLeft.setText(R.string.back);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        checkAnswer();
//    }
//
//    private void checkAnswer() {
//        RequestParams params = getParams();
//        params.addBodyParameter("encryption", encryption);
//        requestNetData(params, ApiUtils.examCheckAnswer);
//    }
//
//    @Override
//    public void onNetSuccess(String tag, String value) {
//        if (tag.equals(ApiUtils.examCheckAnswer)) {
//
//        }
//    }
//
//
//    @OnClick({R.id.tv_left, R.id.btn_check, R.id.btn_start})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_left:
//                finish();
//                break;
//            case R.id.btn_check:
//                break;
//            case R.id.btn_start:
//                break;
//        }
//    }
//}
