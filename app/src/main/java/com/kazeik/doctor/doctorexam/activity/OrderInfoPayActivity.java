package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.MyLessonBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 未支付
 */
public class OrderInfoPayActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_shopItemName)
    TextView tvShopItemName;
    @Bind(R.id.tv_shopInfo)
    TextView tvShopInfo;
    @Bind(R.id.tv_shopNum)
    TextView tvShopNum;
    @Bind(R.id.tv_shopType)
    TextView tvShopType;
    @Bind(R.id.tv_shopMoney)
    TextView tvShopMoney;
    @Bind(R.id.tv_orderCreateTime)
    TextView tvOrderCreateTime;
    @Bind(R.id.tv_payType)
    TextView tvPayType;
    @Bind(R.id.tv_orderInfo)
    TextView tvOrderInfo;
    MyLessonBean.ReMsgEntity body;
    @Override
    public int initLayout() {
        return R.layout.activity_order_info_nopay;
    }

    @Override
    public void initData() {
        body = (MyLessonBean.ReMsgEntity) getIntent().getSerializableExtra("body");
    }

    @Override
    public void initWeight() {
        tvLeft.setText("返回");
        tvLeft.setVisibility(View.VISIBLE);
        tvTitleName.setText("订单详情");

        tvOrderCreateTime.setText(body.add_time);
        tvShopType.setText(body.pay_type);
        tvShopNum.setText(body.id);
        tvShopItemName.setText(body.name);
        tvShopMoney.setText(body.total_amount);
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }

    @OnClick({R.id.tv_left, R.id.tv_orderInfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_orderInfo:
                break;
        }
    }


    @OnClick(R.id.tv_left)
    public void onClick() {
    }
}
