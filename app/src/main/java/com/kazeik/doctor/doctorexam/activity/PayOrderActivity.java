//package com.kazeik.doctor.doctorexam.activity;
//
//import android.view.View;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.kazeik.doctor.doctorexam.BaseActivity;
//import com.kazeik.doctor.doctorexam.R;
//import com.kazeik.doctor.doctorexam.adapter.PayOrderAdapter;
//import com.kazeik.doctor.doctorexam.bean.MockItemBean;
//import com.kazeik.doctor.doctorexam.bean.ShopCarBean;
//import com.kazeik.doctor.doctorexam.utils.AppUtils;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Map;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//
//public class PayOrderActivity extends BaseActivity {
//    @Bind(R.id.tv_titleName)
//    TextView tvTitleName;
//    @Bind(R.id.tv_left)
//    TextView tvLeft;
//    @Bind(R.id.tv_right)
//    TextView tvRight;
//
//    PayOrderAdapter adapter;
//    @Bind(R.id.ls_listView)
//    ListView lsListView;
//
//    ArrayList<ShopCarBean.ReMsgEntity.CartListEntity> payData;
//
//    @Override
//    public int initLayout() {
//        return R.layout.activity_shop_car;
//    }
//
//    @Override
//    public void initData() {
//        payData = (ArrayList<ShopCarBean.ReMsgEntity.CartListEntity>) getIntent().getSerializableExtra("value");
//    }
//
//    @Override
//    public void initWeight() {
//        tvTitleName.setText(R.string.orderinfo);
//        tvLeft.setVisibility(View.VISIBLE);
//        tvRight.setVisibility(View.VISIBLE);
//        tvRight.setText(R.string.surepay);
//        tvLeft.setText(R.string.back);
//
//        adapter = new PayOrderAdapter(this);
//        lsListView.setAdapter(adapter);
//
////        adapter.setData(payData);
//
//    }
//
//    @Override
//    public void onNetSuccess(String tag, String value) {
//
//    }
//
//
//    @OnClick({R.id.tv_left, R.id.tv_right})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_left:
//                finish();
//                break;
//            case R.id.tv_right:
//                if(adapter.getHash().isEmpty()){
//                    AppUtils.showToast(this, R.string.choiceshop);
//                    return;
//                }
//                ArrayList<MockItemBean.ReMsgEntity> tempItem = new ArrayList<>();
//                Iterator iterator = adapter.getHash().entrySet().iterator();
//                while(iterator.hasNext()){
//                    Map.Entry entry = (Map.Entry) iterator.next();
//                    Object key = entry.getKey();
//                    Object value= entry.getValue();
//                    if((Boolean)value){
////                        tempItem.add(payData.get((Integer)key));
//                    }
//                }
//
////                Intent intt = new Intent(this,PayOrderActivity.class);
////                intt.putExtra("value",tempItem);
////                startOtherView(intt);
//                break;
//        }
//    }
//
//}
