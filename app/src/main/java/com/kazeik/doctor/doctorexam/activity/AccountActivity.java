package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.AccountAdapter;
import com.kazeik.doctor.doctorexam.bean.AccountBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;
import com.lidroid.xutils.http.RequestParams;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_accountMoney)
    TextView tvAccountMoney;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    AccountAdapter adapter;

    AccountBean bean;

    @Override
    public int initLayout() {
        return R.layout.activity_account;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.myaccount);
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new AccountAdapter(this);
        lsListView.setAdapter(adapter);

        requestNetData(getParams(), ApiUtils.payInfo);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.payInfo)) {
            try {
                bean = gson.fromJson(value, AccountBean.class);
                if (null == bean) {
                    return;
                }
                adapter.setData(bean.re_msg.detail_array);
                tvAccountMoney.setText(bean.re_msg.balance);
            } catch (Exception ex) {
                AppUtils.showToast(this, "数据解析出错");
                ex.printStackTrace();
            }
        }
    }

    @OnClick({R.id.rl_card, R.id.tv_left, R.id.rl_account})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_card:
                if (null == bean || null == bean.re_msg ) {//|| null == bean.re_msg.learn_card
                    AppUtils.showToast(this, "您暂时没有需要激活的学习卡");
                    return;
                }
                startOtherView(ActivateCardActivity.class);
                break;
            case R.id.tv_left:
                finish();
                break;
            case R.id.rl_account:
                startOtherView(SetChangeActivity.class);
                break;
        }
    }
}
