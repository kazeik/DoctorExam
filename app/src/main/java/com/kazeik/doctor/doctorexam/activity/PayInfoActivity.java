package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayInfoActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_shopNum)
    TextView tvShopNum;
    @Bind(R.id.tv_shopType)
    TextView tvShopType;
    @Bind(R.id.tv_shopName)
    TextView tvShopName;
    @Bind(R.id.tv_orderType)
    TextView tvOrderType;
    @Bind(R.id.tv_orderMoney)
    TextView tvOrderMoney;
    @Bind(R.id.tv_orderInfo)
    TextView tvOrderInfo;

    int pageIndex;

    @Override
    public int initLayout() {
        return R.layout.activity_pay_info;
    }

    @Override
    public void initData() {
        pageIndex = getIntent().getIntExtra("index",0);
    }

    @Override
    public void initWeight() {
        tvTitleName.setText("支付收银台");
        tvLeft.setText("返回");
        tvLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }


    @OnClick(R.id.btn_pay)
    public void onClick() {
        finish();
    }
}
