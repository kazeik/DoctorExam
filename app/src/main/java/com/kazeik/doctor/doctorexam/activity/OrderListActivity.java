package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.OrderListAdapter;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.MyLessonBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.view.ViewPagerIndicatorView;
import com.lidroid.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderListActivity extends BaseActivity implements OrderListAdapter.OnInfoEventListener{
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    OrderListAdapter wasPayAdapter;
    int page;
    @Bind(R.id.ls_listView)
    ListView lsListView;
    MyLessonBean bean;

    int choiceIndex;

    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.orderlist);
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);


        wasPayAdapter = new OrderListAdapter(this);
        wasPayAdapter.setInfoEventListener(this);
        lsListView.setAdapter(wasPayAdapter);
        lsListView.setSelector(R.color.trans);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderList();
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.orderList)) {
            bean = gson.fromJson(value, MyLessonBean.class);
            wasPayAdapter.setData(bean.re_msg);
        }else if(tag.equals(ApiUtils.orderPay)){

        }else if(tag.equals(ApiUtils.orderWebViewPay)){
            Intent intt = new Intent(this,WebActivity.class);
            intt.putExtra("body",value);
            startOtherView(intt,true);
        }else if(tag.equals(ApiUtils.cancelOrder)){
            BaseBean temp = gson.fromJson(value,BaseBean.class);
            bean.re_msg.remove(choiceIndex);
            wasPayAdapter.notifyDataSetChanged();
            AppUtils.showToast(this,temp.re_msg);
        }
    }

    private void getOrderList() {
        RequestParams params = getParams();
        params.addBodyParameter("offset", page + "");
        requestNetData(params, ApiUtils.orderList);
    }


    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }

    @Override
    public void onInfoEvent(int arg, MyLessonBean.ReMsgEntity msgEntity) {
        Intent intt = new Intent(OrderListActivity.this, OrderInfoActivity.class);
        intt.putExtra("id", msgEntity.id);
        intt.putExtra("shop",false);
        startOtherView(intt);
    }

    @Override
    public void onOrderCancelEvent(int arg, MyLessonBean.ReMsgEntity msgEntity) {
        choiceIndex = arg;
        RequestParams params = getParams();
        params.addBodyParameter("order_id",msgEntity.id);
        requestNetData(params,ApiUtils.cancelOrder);
    }

    @Override
    public void onOrderPayEvent(int arg, MyLessonBean.ReMsgEntity msgEntity) {
        RequestParams params = getParams();
        params.addBodyParameter("order_id",msgEntity.id);
        params.addBodyParameter("security_code",msgEntity.security_code);
        requestNetData(params, ApiUtils.orderWebViewPay);

//        Intent intt = new Intent(this,WebActivity.class);
//        intt.putExtra("order_id",msgEntity.id);
//        intt.putExtra("security_code",msgEntity.security_code);
//        startOtherView(intt);
    }
}
