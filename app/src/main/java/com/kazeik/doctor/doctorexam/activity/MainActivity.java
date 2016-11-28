package com.kazeik.doctor.doctorexam.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.MainGridAdapter;
import com.kazeik.doctor.doctorexam.bean.BannerBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.PreferencesUtils;
import com.kazeik.doctor.doctorexam.view.MGridView;
import com.kazeik.doctor.doctorexam.view.SlideShowView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.ssv_show)
    SlideShowView ssvShow;
    @Bind(R.id.gv_main)
    MGridView gvMain;

    MainGridAdapter adapter;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.app_name);
        tvRight.setText(R.string.exit);
        tvRight.setVisibility(View.VISIBLE);

        adapter = new MainGridAdapter(this);
        gvMain.setAdapter(adapter);

//        ssvShow.initData(adList);
//        ssvShow.show();

        requestNetData(getParams(), ApiUtils.getBanner);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.getBanner)) {
            BannerBean bean = gson.fromJson(value, BannerBean.class);
            ArrayList<String> adList = new ArrayList<>();
            for (BannerBean.ReMsgEntity reMsgEntity : bean.re_msg) {
                adList.add("http://"+reMsgEntity.img);
            }
            ssvShow.initData(adList);
            ssvShow.show();
        }
    }


    @OnItemClick(R.id.gv_main)
    public void onItemEvent(int index) {
        switch (index) {
            case 0:
//                startOtherView(BookReadActivity.class);
                startOtherView(TrainLessonActivity.class);
                break;
            case 1:
                startOtherView(MyLessonActivity.class);
                break;
            case 2:
                startOtherView(AccountActivity.class);
                break;
            case 3:
                startOtherView(OrderListActivity.class);
                break;
            case 4:
                startOtherView(UserInfoActivity.class);
                break;
            case 5:
                startOtherView(ProblemActivity.class);
                break;
            case 6:
                startOtherView(StudyInfoActivity.class);
                break;
            case 7:
                startOtherView(OnlineChatActivity.class);
                break;
            case 8:
                startOtherView(MockActivity.class);
                break;
            case 9:
                startOtherView(DrugCheckActivity.class);
                break;
            case 10:
                startOtherView(PinMoneyActivity.class);
                break;
            case 11:
                startOtherView(NewsActivity.class);
                break;
        }
    }


    @OnClick(R.id.tv_right)
    public void onClick() {
        ApiUtils.userInfoBean = null;
        application.exit();
        upUserInfo();
        startOtherView(LoginActivity.class);
    }

    private void upUserInfo(){
        PreferencesUtils.putString(this, "user_id", "");
        PreferencesUtils.putString(this, "system_id", "");
        PreferencesUtils.putString(this, "password", "");
        PreferencesUtils.putLong(this, "loginTime", 0);
    }
}
