package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.utils.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinActivity extends BaseActivity {
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.wv_view)
    WebView wvView;
    String url ;

    @Override
    public int initLayout() {
        return R.layout.activity_join;
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    public void initWeight() {
        tvTitleName.setText("参加活动");
        tvLeft.setText("返回");
        tvLeft.setVisibility(View.VISIBLE);
        url = url.replace("\\","");
        AppUtils.logs(getClass(),url+" ====");

        WebSettings settings = wvView.getSettings();
        settings.setJavaScriptEnabled(true);
        wvView.loadUrl(url);
        wvView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }


    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }
}
