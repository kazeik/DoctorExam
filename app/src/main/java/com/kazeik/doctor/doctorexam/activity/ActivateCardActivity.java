package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.PhoneUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivateCardActivity extends BaseActivity {
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_card)
    EditText etCard;
    @Bind(R.id.et_code)
    EditText etCode;

    String mobile;

    @Override
    public int initLayout() {
        return R.layout.activity_activate_card;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("激活学习卡");
        tvLeft.setText("返回");
        tvLeft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_left, R.id.tv_lastTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_lastTime:
                sendSms();
                break;
        }
    }

    private void sendSms() {
        mobile = etPhone.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            AppUtils.showToast(this, "手机号不能为空");
            return;
        }
        if (!PhoneUtils.isMobile(mobile)) {
            AppUtils.showToast(this, "手机号格式不正确");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("system_id", ApiUtils.userInfoBean.re_msg.sys);
        params.addBodyParameter("user_id", ApiUtils.userInfoBean.re_msg.id);
        params.addBodyParameter("type", "2");
        params.addBodyParameter("mobile", mobile);
        requestNetData(params, ApiUtils.getVerfiyCode);
    }

    private void actiateCardRequest() {
        String cardNum = etCard.getText().toString();
        String pass = etPass.getText().toString();

        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(cardNum)) {
            AppUtils.showToast(this, "卡号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            AppUtils.showToast(this, "密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(code)) {
            AppUtils.showToast(this, "验证码不能为空");
            return;
        }
        RequestParams params = getParams();
        params.addBodyParameter("card_num", cardNum);
        params.addBodyParameter("card_pwd", pass);
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("verify_code", code);
        requestNetData(params, ApiUtils.activateCard);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        BaseBean bean = gson.fromJson(value, BaseBean.class);
        if (bean.re_st.equals("error"))
            AppUtils.showToast(this, bean.re_msg);
        else {
            if (tag.equals(ApiUtils.getVerfiyCode)) {
                AppUtils.showToast(this, "我们已经给您的手机号码" + mobile + "发送了一条验证短信。");
            } else if (tag.equals(ApiUtils.activateCard)) {
                AppUtils.showToast(this, bean.re_msg);
            }
        }
    }

}
