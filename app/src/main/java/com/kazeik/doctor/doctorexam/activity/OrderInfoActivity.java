package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.OrderInfoBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class OrderInfoActivity extends BaseActivity {
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    String body;
    String orderId;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    OrderInfoAdapter adapter;
    OrderInfoBean infoBean;

    boolean isShop;

    @Override
    public int initLayout() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void initData() {
        body = getIntent().getStringExtra("body");
        orderId = getIntent().getStringExtra("id");
        isShop = getIntent().getBooleanExtra("shop", false);
    }

    @Override
    public void initWeight() {
        tvLeft.setText(R.string.back);
        tvLeft.setVisibility(View.VISIBLE);
        tvTitleName.setText(R.string.orderinfo);

        adapter = new OrderInfoAdapter(this);
        lsListView.setAdapter(adapter);

        if (TextUtils.isEmpty(body)) {
            getListData();
            return;
        }

        tvRight.setText(R.string.surepay);
        tvRight.setVisibility(View.VISIBLE);

        infoBean = new OrderInfoBean();
        infoBean.re_msg = new OrderInfoBean.ReMsgEntity();
        infoBean.re_msg.order_details = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(body);
            JSONObject msgObj = obj.optJSONObject("re_msg");
            infoBean.re_msg.need_handbook = msgObj.optInt("need_handbook");
            infoBean.re_msg.security_code = msgObj.optString("security_code");
            infoBean.re_msg.good_ids = msgObj.optString("good_ids");
            infoBean.re_msg.member_base = msgObj.optDouble("member_blance");
            JSONArray array = msgObj.optJSONArray("order_details");
            for (int i = 0; i < array.length(); i++) {
                JSONObject temObj = array.getJSONObject(i);
                OrderInfoBean.OrderDetails orderDetails = gson.fromJson(temObj.toString(), OrderInfoBean.OrderDetails.class);
                infoBean.re_msg.order_details.add(orderDetails);
            }

            adapter.setData(infoBean.re_msg.order_details);

            JSONObject typeObj = msgObj.optJSONObject("order_pay_type");
            infoBean.re_msg.order_pay_type = new ArrayList<>();
            Iterator iterator = typeObj.keys();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String va = typeObj.optString(key.toString());
                infoBean.re_msg.order_pay_type.add(va);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getListData() {
        RequestParams params = getParams();
        params.addBodyParameter("order_id", orderId);
        requestNetData(params, ApiUtils.orderMoreInfo);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.orderPay)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            AppUtils.showToast(this, bean.re_msg);
            if (bean.re_st.equals("alipay")) {
                String url = "http://appapi.kaohewang.com/order/alipay?" + bean.re_url;
                Intent intt = new Intent(this, WebActivity.class);
                intt.putExtra("url", true);
                intt.putExtra("body", url);
                startOtherView(intt);
            } else if (bean.re_st.equals("success")) {
                if (isShop)
                    startOtherView(OrderListActivity.class, true);
                else
                    finish();
            }
        } else if (tag.equals(ApiUtils.orderMoreInfo)) {
            OrderDetailBean bean = new OrderDetailBean();
            bean.re_msg = new OrderDetailBean.ReMsgEntity();
            try {
                JSONObject obj = new JSONObject(value);
                JSONObject msgObj = obj.optJSONObject("re_msg");
                bean.re_msg.order_info_id = msgObj.optString("order_info_id");
                bean.re_msg.order_id = msgObj.optString("order_id");
                bean.re_msg.uid = msgObj.optString("uid");
                bean.re_msg.order_status = msgObj.optString("order_status");
                bean.re_msg.delivery_status = msgObj.optString("delivery_status");
                bean.re_msg.pay_type = msgObj.optString("pay_type");
                bean.re_msg.total_amount = msgObj.optString("total_amount");
                bean.re_msg.balance_amount = msgObj.optString("balance_amount");
                bean.re_msg.pay_amount = msgObj.optString("pay_amount");
                bean.re_msg.add_time = msgObj.optString("add_time");
                bean.re_msg.pay_time = msgObj.optString("pay_time");
                JSONObject detaObj = msgObj.optJSONObject("detail");
                bean.re_msg.detail = new ArrayList<>();
                Iterator iterator = detaObj.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    JSONObject tem = detaObj.optJSONObject(key);
                    OrderDetailBean.Detail detail = new OrderDetailBean.Detail();
                    detail.good_id = tem.optString("good_id");
                    detail.good_name = tem.optString("good_name");
                    detail.price = tem.optString("price");
                    bean.re_msg.detail.add(detail);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (bean.re_msg.detail == null) {
                return;
            }
            List<OrderInfoBean.OrderDetails> tempBean = new ArrayList<>();
            for (int i = 0; i < bean.re_msg.detail.size(); i++) {
                OrderInfoBean.OrderDetails detail = new OrderInfoBean.OrderDetails();
                OrderDetailBean.Detail tempDetail = bean.re_msg.detail.get(i);
                detail.resinfo = new OrderInfoBean.ResinfoEntity();
                detail.resinfo.price = tempDetail.price;
                detail.resinfo.title = tempDetail.good_name;
                detail.order_type = bean.re_msg.pay_type;
                tempBean.add(detail);
            }
            adapter.setData(tempBean);
            lsListView.setDivider(null);
            lsListView.setDividerHeight(10);
        }
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_right:
                surePay();
                break;
        }
    }

    private void surePay() {
        if (infoBean.re_msg.order_details.isEmpty()) {
            AppUtils.showToast(this, R.string.no_order_pay);
            return;
        }
        RequestParams params = getParams();
        for (int i = 0; i < infoBean.re_msg.order_details.size(); i++) {
            params.addBodyParameter("item[" + i + "]", infoBean.re_msg.order_details.get(i).id);
        }
        params.addBodyParameter("is_balance", "1");
        requestNetData(params, ApiUtils.orderPay);
    }
}
