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
import com.kazeik.doctor.doctorexam.utils.PhoneUtils;
import com.kazeik.doctor.doctorexam.utils.RegExUtils;
import com.kazeik.doctor.doctorexam.view.TimeButton;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    //    @Bind(R.id.et_newPass)
//    EditText etNewPass;
    @Bind(R.id.tv_phone)
    EditText tvPhone;
    @Bind(R.id.et_change_pass)
    EditText etChangePass;
    @Bind(R.id.et_surePass)
    EditText etSurePass;
    @Bind(R.id.tb_time)
    TimeButton tbTime;
    @Bind(R.id.et_rigister_code)
    EditText etRigisterCode;

    String mobile;

    @Override
    public int initLayout() {
        return R.layout.activity_forget;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("修改密码");
        tvLeft.setText(R.string.back);
        tvLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.getVerfiyCode)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            if (bean.re_st.equals("error")) {
                AppUtils.showToast(this, bean.re_msg);
            } else {
                AppUtils.showToast(this, "我们已经给您的手机号码" + mobile + "发送了一条验证短信。");
            }
        } else if (tag.equals(ApiUtils.changePass)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            AppUtils.showToast(this, bean.re_msg);
            if (bean.re_st.equals("success")) {
                finish();
            }
        }
    }

    private void changePass() {
        String pass = etChangePass.getText().toString();
        String surePass = etSurePass.getText().toString();
        String changeCode = etRigisterCode.getText().toString();

        if (TextUtils.isEmpty(pass)) {
            AppUtils.showToast(this, "密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(surePass)) {
            AppUtils.showToast(this, "重复密码不能为空");
            return;
        }
        if (!TextUtils.equals(pass, surePass)) {
            AppUtils.showToast(this, "两次密码不一致");
            return;
        }
        if (TextUtils.isEmpty(changeCode)) {
            AppUtils.showToast(this, "验证码不能为空");
            return;
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("password", pass);
        params.addBodyParameter("verify_code", changeCode);
        requestNetData(params, ApiUtils.changePass);
    }

    private void getChangeVerify() {
        mobile = tvPhone.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            AppUtils.showToast(this, "手机号不能为空");
            return;
        }
        if (!RegExUtils.isMobileNO(mobile)) {
            AppUtils.showToast(this, "手机号格式不正确");
            return;
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("type", "5");
        requestNetData(params, ApiUtils.getVerfiyCode);
    }


    @OnClick({R.id.tb_time, R.id.btn_changePass, R.id.tv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_time:
                getChangeVerify();
                break;
            case R.id.btn_changePass:
                changePass();
                break;
            case R.id.tv_left:
                finish();
                break;
        }
    }
}
