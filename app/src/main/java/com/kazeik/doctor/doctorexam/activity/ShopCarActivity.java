package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.ShopCarAdapter;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.MockItemBean;
import com.kazeik.doctor.doctorexam.bean.ShopCarBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.lidroid.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity implements ShopCarAdapter.OnDeleteEventListener {
    //    MockItemBean.ReMsgEntity msgEntity;
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    ShopCarAdapter adapter;
//    List<MockItemBean.ReMsgEntity> allItem = null;

    ShopCarBean carBean;
    int deleteIndex;

    @Override
    public int initLayout() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void initData() {
//        msgEntity = (MockItemBean.ReMsgEntity) getIntent().getSerializableExtra("body");
    }

    @Override
    public void initWeight() {
        tvRight.setText(R.string.settlement);
        tvRight.setVisibility(View.VISIBLE);
        tvTitleName.setText(R.string.shoplist);
        tvLeft.setText(R.string.back);
        tvLeft.setVisibility(View.VISIBLE);

//        allItem = new ArrayList<>();
//        allItem.add(msgEntity);

        adapter = new ShopCarAdapter(this);
        adapter.setDeleteEventListener(this);
        lsListView.setAdapter(adapter);
        lsListView.setDivider(null);
        lsListView.setDividerHeight(10);
//        adapter.setData(allItem);

        getListData();
    }

    private void getListData(){
        RequestParams params = getParams();
        params.addBodyParameter("type","2");
        requestNetData(params, ApiUtils.cartList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListData();
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.cartList)) {
            carBean = gson.fromJson(value, ShopCarBean.class);
            if(carBean.re_msg != null)
            adapter.setData(carBean.re_msg.cart_list);
        } else if (tag.equals(ApiUtils.cartDelete)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            AppUtils.showToast(this, bean.re_msg);
            if (bean.re_st.equals("success")) {
                carBean.re_msg.cart_list.remove(deleteIndex);
                adapter.setData(carBean.re_msg.cart_list);
            }
        }else if(tag.equals(ApiUtils.orderInfo)){
            Intent intt = new Intent(this,OrderInfoActivity.class);
            intt.putExtra("body",value);
            intt.putExtra("shop",true);
            startOtherView(intt);
        }
    }


    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_right:
                if (adapter.getHash().isEmpty()) {
                    AppUtils.showToast(this, R.string.choiceshop);
                    return;
                }
                ArrayList<ShopCarBean.ReMsgEntity.CartListEntity> tempItem = new ArrayList<>();
//                StringBuffer sb = new StringBuffer();
                RequestParams params = getParams();
                int index = 0;
                Iterator iterator = adapter.getHash().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if ((Boolean) value) {
                        index++;
                        ShopCarBean.ReMsgEntity.CartListEntity entity = carBean.re_msg.cart_list.get((Integer)key);
                        tempItem.add(entity);
                        params.addBodyParameter("item[" + index + "]", entity.id);

//                        sb.append("'");
//                        sb.append(entity.id);
//                        sb.append("'");
//                        sb.append(",");
                    }
                }
//                Intent intt = new Intent(this, PayOrderActivity.class);
//                intt.putExtra("value", tempItem);
//                startOtherView(intt);
//                String val = sb.toString().substring(0, sb.toString().length() - 1);
//                AppUtils.logs(getClass(),val);
//                StringBuffer sb2 = new StringBuffer();
//                sb2.append("[");
//                sb2.append(val);
//                sb2.append("]");
//                AppUtils.logs(getClass(),sb2.toString()+"  ----------  ");

                requestNetData(params, ApiUtils.orderInfo);
                break;

        }
    }

    @Override
    public void onDeleteEvent(int pos) {
        deleteIndex = pos;
        RequestParams params = getParams();
        params.addBodyParameter("cart_id", carBean.re_msg.cart_list.get(pos).id);
        requestNetData(params, ApiUtils.cartDelete);
    }
}
