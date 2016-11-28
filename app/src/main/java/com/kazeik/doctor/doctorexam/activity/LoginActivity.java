package com.kazeik.doctor.doctorexam.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.LoginUserBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;
import com.kazeik.doctor.doctorexam.utils.PhoneUtils;
import com.kazeik.doctor.doctorexam.utils.PreferencesUtils;
import com.lidroid.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.et_userPhone)
    EditText etUserPhone;
    @Bind(R.id.et_userPass)
    EditText etUserPass;
    @Bind(R.id.tv_userAddress)
    TextView tvUserAddress;
    String cityId;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initWeight() {
        if(AppUtils.debug){
//            etUserPhone.setText("15821804473");
//            etUserPass.setText("333333");
            etUserPhone.setText("test");
            etUserPass.setText("222222");
        }

        String user_id = PreferencesUtils.getString(this, "user_id");
        String systemId = PreferencesUtils.getString(this, "system_id");
        String password = PreferencesUtils.getString(this, "password");
        long time = PreferencesUtils.getLong(this, "loginTime");
        long len = MyDateUtils.getAppointTimeDifference(MyDateUtils.getDateForTime(time), MyDateUtils.getDateForTime(System.currentTimeMillis()));
        if (TextUtils.isEmpty(systemId) || len>15) {
            requestNetData(ApiUtils.getAdds);
        } else {
            ApiUtils.userInfoBean = new LoginUserBean();
            ApiUtils.userInfoBean.re_msg = new LoginUserBean.ReMsgEntity();
            ApiUtils.userInfoBean.re_msg.id = user_id;
            ApiUtils.userInfoBean.re_msg.password = password;
            ApiUtils.userInfoBean.re_msg.sys = systemId;
            startOtherView(MainActivity.class, true);
        }
    }

    private void showCityList() {
        String[] cityName = new String[ApiUtils.cityBean.re_msg.size()];
        for (int i = 0; i < ApiUtils.cityBean.re_msg.size(); i++) {
            cityName[i] = ApiUtils.cityBean.re_msg.get(i).name;
        }
        Dialog alertDialog = new AlertDialog.Builder(this).
                setTitle("请选择你所在的区域")
                .setItems(cityName, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityId = ApiUtils.cityBean.re_msg.get(which).sys;
                        tvUserAddress.setText(ApiUtils.cityBean.re_msg.get(which).name);
                    }
                }).create();
        alertDialog.show();
    }

    @OnClick({R.id.rl_address, R.id.btn_login, R.id.tv_register, R.id.tv_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address:
                showCityList();
                break;
            case R.id.tv_forget:
                startOtherView(ChangePassActivity.class);
                break;
            case R.id.btn_login:
                String pass = etUserPass.getText().toString();
                String mobile = etUserPhone.getText().toString();
                if (TextUtils.isEmpty(cityId)) {
                    AppUtils.showToast(this, "请选择你所在的城市");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    AppUtils.showToast(this, "手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    AppUtils.showToast(this, "密码不能为空");
                    return;
                }

                RequestParams params = new RequestParams();
                params.addBodyParameter("system_id", cityId);
                params.addBodyParameter("password",pass);
                params.addBodyParameter("username", mobile);
                params.addBodyParameter("uuid", PhoneUtils.getDeviceId(this));
                params.addBodyParameter("type", "2");
                requestNetData(params, ApiUtils.userLogin);

//                startOtherView(MainActivity.class, true);
                break;
            case R.id.tv_register:
                startOtherView(RegisterActivity.class);
                break;
        }
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if(tag.equals(ApiUtils.userLogin)){
            ApiUtils.userInfoBean = gson.fromJson(value,LoginUserBean.class);
            if(null != ApiUtils.userInfoBean || ApiUtils.userInfoBean.re_msg != null){
                AppUtils.showToast(this,"登录成功");
                saveUserInfo();
                startOtherView(MainActivity.class, true);
            }else{
                AppUtils.showToast(this,"登录失败");
            }
        }
    }

    private void saveUserInfo(){
        PreferencesUtils.putString(this, "user_id", ApiUtils.userInfoBean.re_msg.id);
        PreferencesUtils.putString(this, "system_id", ApiUtils.userInfoBean.re_msg.sys);
        PreferencesUtils.putString(this, "password", ApiUtils.userInfoBean.re_msg.password);
        PreferencesUtils.putLong(this,"loginTime",System.currentTimeMillis());
    }
}
