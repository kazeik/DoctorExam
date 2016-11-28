package com.kazeik.doctor.doctorexam.activity;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.CityBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;

public class StartActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        requestNetData(ApiUtils.getAdds);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.getAdds)) {
            ApiUtils.cityBean = gson.fromJson(value, CityBean.class);
            startOtherView(LoginActivity.class, true);
        }
    }

    @Override
    public void onNetError(String tag, String value) {
//        AppUtils.showToast(this, "网络异常，请检查网络");
        this.finish();
    }
}
