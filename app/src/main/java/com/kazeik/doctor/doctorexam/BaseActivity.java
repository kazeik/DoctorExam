package com.kazeik.doctor.doctorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;
import com.kazeik.doctor.doctorexam.utils.SdcardUtils;
import com.kazeik.doctor.doctorexam.view.OFHud;
import com.kazeik.doctor.doctorexam.view.SystemBarTintManager;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.ButterKnife;

//http://www.cnblogs.com/likeandroid/p/4501758.html
public abstract class BaseActivity extends FragmentActivity {
    public Gson gson;
    protected HttpHandler<?> httpHandler;
    protected BitmapUtils utils;
    private OFHud hud;
    protected SdcardUtils sdcardUtils;
    public MainApplication application;

    protected Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        application = (MainApplication) getApplication();
        application.add(this);
//        android:fitsSystemWindows="true"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.frame_bg);//通知栏所需颜色
        }

        utils = new BitmapUtils(this);
        gson = new Gson();
        sdcardUtils = new SdcardUtils();
        setContentView(initLayout());
        ButterKnife.bind(this);
        initData();
        initWeight();
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public <T> void requestNetData(String url, String title,
                                   RequestCallBack<T> callBack) {
        requestNetData(HttpRequest.HttpMethod.GET, url, null, title, callBack);
    }

    public <T> void requestNetDataAndTitle(String url, String title, String tag) {
        requestNetData(HttpRequest.HttpMethod.GET, url, null, title, tag);
    }

    public <T> void requestNetData(String tag) {
        String url = ApiUtils.baseUrl + tag;
        requestNetData(url, null, tag);
    }

    public <T> void requestNetData(RequestParams params, String tag) {
        String url = ApiUtils.baseUrl + tag;
        requestNetData(url, params, tag);
    }

    public <T> void requestNetData(String url, RequestParams params, String tag) {
        HttpRequest.HttpMethod method = params == null ? HttpRequest.HttpMethod.GET : HttpRequest.HttpMethod.POST;
        requestNetData(method, url, params, null, tag);
    }

    public <T> void requestNetData(String url, String title, boolean show,
                                   RequestCallBack<T> callBack) {
        requestNetData(HttpRequest.HttpMethod.GET, url, null, title, false, callBack);
    }

    public <T> void requestNetData(String url, boolean show,
                                   RequestCallBack<T> callBack) {
        requestNetData(url, "", false, callBack);
    }

    public <T> void requestNetData(String url, RequestCallBack<T> callBack) {
        requestNetData(url, "", callBack);
    }

    public <T> void requestNetData(HttpRequest.HttpMethod method, String url,
                                   RequestParams params, RequestCallBack<T> callBack) {
        requestNetData(method, url, params, "", callBack);
    }

    public <T> void requestNetData(HttpRequest.HttpMethod method, String url,
                                   RequestParams params, String title, RequestCallBack<T> callBack) {
        requestNetData(method, url, params, title, true, callBack);
    }

    public <T> void requestNetData(String tag, RequestParams params) {
        requestNetData(HttpRequest.HttpMethod.POST, tag, params);
    }

    public <T> void requestNetData(HttpRequest.HttpMethod method, String tag, RequestParams params) {
        if (TextUtils.isEmpty(ApiUtils.baseUrl)) {
            AppUtils.showToast(this, "baseUrl is null");
            return;
        }
        requestNetData(method, ApiUtils.baseUrl + tag,
                params, null, tag);
    }

    public <T> void requestNetData(HttpRequest.HttpMethod method, String url, RequestParams params, String tag) {
        requestNetData(method, url, params, null, tag);
    }

    public <T> void requestNetData(HttpRequest.HttpMethod method, String url,
                                   RequestParams params, String title, final String tag) {
        showHud(TextUtils.isEmpty(title) ? getString(R.string.loading) : title);
        AppUtils.logs(getClass(), url);
        HttpUtils utils = new HttpUtils();
//        utils.configResponseTextCharset("utf-8");
//        if (null != params)
//            params.addHeader("content-type", "application/json");
//        utils.configUserAgent("application/json; charset=utf-8");
        utils.configCurrentHttpCacheExpiry(50);
        httpHandler = utils.send(method, url, params,
                new RequestCallBack<String>() {

                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        // TODO Auto-generated method stub
                        hideDialog();
                        AppUtils.showToast(BaseActivity.this, R.string.request_error);
                        AppUtils.logs(
                                getClass(),
                                tag + " | " + arg1 + " | "
                                        + arg0.getExceptionCode() + " | "
                                        + arg0.getMessage());
                        onNetError(tag, arg1);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        // TODO Auto-generated method stub
                        hideDialog();
                        AppUtils.logs(getClass(), tag + " | " + arg0.result);
                        if (TextUtils.isEmpty(arg0.result)) {
                            AppUtils.showToast(BaseActivity.this, R.string.message_error);
                            return;
                        }

                        if (tag.equals(ApiUtils.orderWebViewPay)) {
                            onNetSuccess(tag, arg0.result);
                            return;
                        }

                        try {
                            JSONObject obj = new JSONObject(arg0.result);
                            String flag = obj.optString("re_st");
                            if (flag.equals("error") || flag.equals("empty")) {
                                String msg = obj.optString("re_msg");
                                if (msg.equals("null")) {
                                    msg = getString(R.string.empty_msg);
                                }
                                onNetError(tag, arg0.result);
                                AppUtils.showToast(BaseActivity.this, msg);
                            } else
                                onNetSuccess(tag, arg0.result);
                        } catch (JSONException e) {
                            AppUtils.showToast(BaseActivity.this, R.string.message_error);
                        }
                    }
                });
    }

    public <T> void requestNetData(HttpRequest.HttpMethod method, String url,
                                   RequestParams params, String title, boolean show,
                                   RequestCallBack<T> callBack) {
        if (show)
            showHud(TextUtils.isEmpty(title) ? getString(R.string.loading) : title);
        AppUtils.logs(getClass(), url);
        HttpUtils utils = new HttpUtils();
        utils.configCurrentHttpCacheExpiry(50);
        utils.configTimeout(1000 * 30);
        utils.configRequestRetryCount(0);
        httpHandler = utils.send(method, url, params, callBack);
    }

    /**
     * 带自定义提示文字无法按返回键取消的进度提示条
     *
     * @param message
     * @author kazeik.chen QQ:77132995 2015-3-5上午9:53:02 TODO kazeik@163.com
     */
    public void showHud(String message) {
        showHud(message, false);
    }

    /**
     * 无自定义提示文字无法按返回键取消的进度提示条
     *
     * @author kazeik.chen QQ:77132995 2015-3-5上午9:53:25 TODO kazeik@163.com
     */
    public void showHud() {
        showHud("");
    }

    /**
     * 无自定义提示文字的进度提示条
     *
     * @param isCancel 是否可以按返回键取消进度条显示
     * @author kazeik.chen QQ:77132995 2015-3-5上午9:53:55 TODO kazeik@163.com
     */
    public void showHud(boolean isCancel) {
        showHud("", isCancel);
    }

    public void showHud(String message, boolean isCancel) {
        if (hud == null) {
            hud = new OFHud(this);
            if (!TextUtils.isEmpty(message)) {
                hud.setMessage(message);
            } else {
                hud.setMessage(getString(R.string.loading));
            }
            hud.setCancelable(isCancel);
            hud.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancelHandler();
                    hideDialog();
                }
            });
        }
        hud.show();
    }

    protected void cancelHandler() {
        if (null != httpHandler) {
            httpHandler.cancel();
            httpHandler = null;
        }
    }

    public void hideDialog() {
        if (hud != null) {
            hud.dismiss();
            hud = null;
        }
    }

    @Override
    protected void onDestroy() {
        if (httpHandler != null)
            httpHandler.cancel(true);
        hideDialog();
        super.onDestroy();
    }

    public void startOtherView(Class<?> tempClass, boolean falsh) {
        Intent intt = new Intent(this, tempClass);
        startOtherView(intt, falsh);
    }

    public void startOtherView(Class<?> tempClass) {
        startOtherView(tempClass, false);
    }

    public void startOtherView(Intent intt) {
        startOtherView(intt, false);
    }

    public void startOtherView(Intent intt, boolean falsh) {
        startActivity(intt);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        if (falsh) {
            this.finish();
        }
    }

    public void startOtherViewForCode(Intent intt, int code) {
        startActivityForResult(intt, code);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    //-------------
    public abstract int initLayout();

    public abstract void initData();

    public abstract void initWeight();

    public abstract void onNetSuccess(String tag, String value);

    public void onNetError(String tag, String value) {
    }

    public RequestParams getParams() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("system_id", ApiUtils.userInfoBean.re_msg.sys);
        params.addBodyParameter("user_id", ApiUtils.userInfoBean.re_msg.id);
        params.addBodyParameter("password", ApiUtils.userInfoBean.re_msg.password);
        AppUtils.logs(getClass(), "systemid = " + ApiUtils.userInfoBean.re_msg.sys + " | user_id=  " + ApiUtils.userInfoBean.re_msg.id + " |password = " + ApiUtils.userInfoBean.re_msg.password);
        return params;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
