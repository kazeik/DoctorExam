package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.RegExUtils;
import com.kazeik.doctor.doctorexam.view.TimeButton;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetChangeActivity extends BaseActivity {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_bank)
    EditText etBank;
    @Bind(R.id.et_sureBank)
    EditText etSureBank;
    @Bind(R.id.tv_lastTime)
    TimeButton tvLastTime;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_left)
    TextView tvLeft;

    String mobile;

    @Override
    public int initLayout() {
        return R.layout.activity_set_change;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("设置兑换帐号");
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText(R.string.back);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        BaseBean bean = gson.fromJson(value, BaseBean.class);
        if (bean.re_st.equals("error"))
            AppUtils.showToast(this, bean.re_msg);
        else {
            if (tag.equals(ApiUtils.getVerfiyCode)) {
                tvLastTime.setEnabled(true);
                AppUtils.showToast(this, "我们已经给您的手机号码" + mobile + "发送了一条验证短信。");
            } else if (tag.equals(ApiUtils.setChange)) {
                AppUtils.showToast(this, bean.re_msg);
            }
        }
    }

    @OnClick({R.id.tv_left, R.id.tv_lastTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_lastTime:
                mobile = etPhone.getText().toString();
                if (TextUtils.isEmpty(mobile)) {
                    startTime();
                    AppUtils.showToast(this, "手机号不能为空");
                    return;
                }
                if (!RegExUtils.isMobileNO(mobile)) {
                    startTime();
                    AppUtils.showToast(this, "手机号格式不正确");
                    return;
                }
                RequestParams params = new RequestParams();
                params.addBodyParameter("mobile", mobile);
                params.addBodyParameter("type", "3");
                requestNetData(params, ApiUtils.getVerfiyCode);
                break;
        }
    }

    private void setChangeInfo() {
        String bankName = etBank.getText().toString();
        String cardNum = etAccount.getText().toString();
        String cardName = etName.getText().toString();
        String sureBank = etSureBank.getText().toString();
        String verfiyCode = etCode.getText().toString();
        if (TextUtils.isEmpty(bankName)) {
            AppUtils.showToast(this, "开户人名不能为空");
            return;
        }
        if (!RegExUtils.isMobileNO(cardNum)) {
            AppUtils.showToast(this, "银行卡号不能为空");
            return;
        }
        if (TextUtils.isEmpty(cardName)) {
            AppUtils.showToast(this, "银行名不能为空");
            return;
        }
        if (!RegExUtils.isMobileNO(sureBank)) {
            AppUtils.showToast(this, "开户行不能为空");
            return;
        }
        if (TextUtils.isEmpty(verfiyCode)) {
            AppUtils.showToast(this, "验证码不能为空");
            return;
        }
        RequestParams params = getParams();
        params.addBodyParameter("bank_card", cardNum);
        params.addBodyParameter("bank_name", bankName);
        params.addBodyParameter("bank_user", cardName);
        params.addBodyParameter("open_bank", sureBank);
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("verify_code", verfiyCode);
        requestNetData(params, ApiUtils.setChange);
    }

    private void startTime(){
        tvLastTime.setTextBefore("发送验证码").setLenght(60 * 1000);
        tvLastTime.onDestroy();
    }
}
