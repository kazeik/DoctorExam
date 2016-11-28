package com.kazeik.doctor.doctorexam.activity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.view.HTML5WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll_layout)
    LinearLayout llLayout;

    HTML5WebView wvView;

    boolean isUrl;
    String body;

    @Override
    public int initLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initData() {
        body = getIntent().getStringExtra("body");
        isUrl = getIntent().getBooleanExtra("url", false);
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void initWeight() {
        tvLeft.setText(R.string.back);
        tvRight.setVisibility(View.GONE);
        tvLeft.setVisibility(View.VISIBLE);
        tvTitleName.setText(R.string.onlinepay);

//        WebViewClient client = new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                view.loadUrl(url); // 在当前的webview中跳转到新的url
//                return true;
//            }
//
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                // handler.cancel(); // Android默认的处理方式
//                handler.proceed(); // 接受所有网站的证书
//                // handleMessage(Message msg); // 进行其他处理d
//            }
//        };
        wvView = new HTML5WebView(this);
        llLayout.addView(wvView.getLayout());

//        wvView.setWebViewClient(client);

//        WebSettings settings = wvView.getSettings();
//        settings.setJavaScriptEnabled(true); // 开启js
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 禁止缓存
//        wvView.requestFocus();

        if (isUrl)
            wvView.loadUrl(body);
        else
            wvView.loadData(body, "text/html", "utf-8");

    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }

    @Override
    public void onStop() {
        super.onStop();
        wvView.stopLoading();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (wvView.inCustomView()) {
                wvView.hideCustomView();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.tv_left)
    public void onClick() {
//        setResult(99,);
        backList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backList();
    }

    private void backList(){
        startOtherView(OrderListActivity.class,true);
    }
}
