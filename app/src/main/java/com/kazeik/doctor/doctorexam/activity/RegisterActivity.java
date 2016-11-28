package com.kazeik.doctor.doctorexam.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.RegisterBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.RegExUtils;
import com.kazeik.doctor.doctorexam.view.TextParser;
import com.kazeik.doctor.doctorexam.view.TimeButton;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.et_rigister_phone)
    EditText etRigisterPhone;
    @Bind(R.id.et_rigister_pass)
    EditText etRigisterPass;
    @Bind(R.id.et_rigister_code)
    EditText etRigisterCode;
    @Bind(R.id.tv_address)
    TextView tvAdd;

    String cityId;

    String mobile;
    @Bind(R.id.et_surePass)
    EditText etSurePass;
    @Bind(R.id.tb_time)
    TimeButton tbTime;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.cb_ask)
    CheckBox cbAsk;


    @Override
    public int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {

    }

    private void showCityList() {
        String[] cityName = new String[ApiUtils.cityBean.re_msg.size()];
        for (int i = 0; i < ApiUtils.cityBean.re_msg.size(); i++) {
            cityName[i] = ApiUtils.cityBean.re_msg.get(i).name;
        }
        Dialog alertDialog = new AlertDialog.Builder(this).
                setTitle("请选择你所在的省份")
                .setItems(cityName, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityId = ApiUtils.cityBean.re_msg.get(which).sys;
                        tvAdd.setText(ApiUtils.cityBean.re_msg.get(which).name);
                    }
                }).create();
        alertDialog.show();
    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.new_user);
        tvLeft.setText(R.string.cancel);
        tvLeft.setVisibility(View.VISIBLE);

        TextParser parser = new TextParser();
        parser.append("我已经阅读并同意", 40, Color.BLACK);
        parser.append("使用条款和隐私政策", 40, Color.parseColor("#17566f"),
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
//                        startOtherView(AgreementActivity.class);
                    }
                });
        parser.parse(cbAsk);
    }

    private void startRegister() {
        String pass = etRigisterPass.getText().toString();
        String verifyCode = etRigisterCode.getText().toString();
        String surePass = etSurePass.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            AppUtils.showToast(this, "密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(surePass)) {
            AppUtils.showToast(this, "确认密码不能为空");
            return;
        }
        if (!TextUtils.equals(pass, surePass)) {
            AppUtils.showToast(this, "两次密码不一致");
            return;
        }
        if (TextUtils.isEmpty(verifyCode)) {
            AppUtils.showToast(this, "验证码不能为空");
            return;
        }

        if (TextUtils.equals(verifyCode, surePass)) {
            AppUtils.showToast(this, "验证码不正确");
            return;
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("system_id", cityId);
        params.addBodyParameter("password", pass);
        params.addBodyParameter("verify_code", verifyCode);
        requestNetData(params, ApiUtils.register);
    }


    @OnClick({R.id.tv_left, R.id.btn_register, R.id.ll_address, R.id.tb_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                this.finish();
                break;
            case R.id.btn_register:
                startRegister();
                break;
            case R.id.ll_address:
                showCityList();
                break;
            case R.id.tb_time:
                mobile = etRigisterPhone.getText().toString();
                if (TextUtils.isEmpty(cityId)) {
                    startTime();
                    AppUtils.showToast(this, "请选择你所在的城市");
                    return;
                }
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
                params.addBodyParameter("system_id", cityId);
                params.addBodyParameter("mobile", mobile);
                params.addBodyParameter("type", "4");
                requestNetData(params, ApiUtils.getVerfiyCode);
                break;
        }
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.getVerfiyCode)) {
            tbTime.setEnabled(true);
            try {
                JSONObject object = new JSONObject(value);
                String msg = object.optString("re_msg");
                String flag = object.optString("re_st");
                if (flag.equals("error"))
                    AppUtils.showToast(this, msg);
                else
                    AppUtils.showToast(this, "我们已经给您的手机号码" + mobile + "发送了一条验证短信。");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (tag.equals(ApiUtils.register)) {
            try {
                JSONObject object = new JSONObject(value);
                String flag = object.optString("re_st");
                if (flag.equals("error")) {
                    String msg = object.optString("re_msg");
                    AppUtils.showToast(this, msg);
                } else {
                    RegisterBean registerBean = gson.fromJson(value,RegisterBean.class);
                    AppUtils.showToast(this,"注册成功");
                    startOtherView(LoginActivity.class,true);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNetError(String tag, String value) {
        if (tag.equals(ApiUtils.getVerfiyCode)) {
            startTime();
        }
    }
    private void startTime(){
        tbTime.setTextBefore("发送验证码").setLenght(60 * 1000);
        tbTime.onDestroy();
    }
}
