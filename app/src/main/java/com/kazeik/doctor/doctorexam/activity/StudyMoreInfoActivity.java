package com.kazeik.doctor.doctorexam.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.StudyDetailBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.lidroid.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudyMoreInfoActivity extends BaseActivity {

    String id;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_form)
    TextView tvForm;

    int isSystem;
    @Bind(R.id.wv_body)
    WebView wvBody;

    @Override
    public int initLayout() {
        return R.layout.activity_study_more_info;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
        isSystem = getIntent().getIntExtra("system", 0);
    }

    @Override
    public void initWeight() {
        String title = "";
        String url = "";
        switch (isSystem) {
            case 0:
                title = "考务信息";
                url = ApiUtils.newsDetail;
                break;
            case 1:
                title = "系统消息";
                url = ApiUtils.notifyDetail;
                break;
            case 2:
                title = "定考百问";
                url = ApiUtils.helpDetail;
                break;
        }

        tvTitleName.setText(title);
        tvLeft.setText(R.string.back);
        tvLeft.setVisibility(View.VISIBLE);

        RequestParams params = getParams();
        params.addBodyParameter("id", id);
        requestNetData(params, url);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.newsDetail) || tag.equals(ApiUtils.notifyDetail) || tag.equals(ApiUtils.helpDetail)) {
            StudyDetailBean detailBean = gson.fromJson(value, StudyDetailBean.class);
            tvTitle.setText(detailBean.re_msg.title);
            tvForm.setText("来源:" + (TextUtils.isEmpty(detailBean.re_msg.source) ? "" : detailBean.re_msg.source)
                    + "   时间:" + detailBean.re_msg.add_time + "   阅读次数:" + (TextUtils.isEmpty(detailBean.re_msg.click_num) ? "" : detailBean.re_msg.click_num));
            wvBody.loadDataWithBaseURL(null, detailBean.re_msg.content, "text/html", "utf-8", null);
        }
    }

    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
