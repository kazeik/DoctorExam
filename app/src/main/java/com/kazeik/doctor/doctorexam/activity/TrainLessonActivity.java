package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.MyLessonAdapter;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.LessonItemBean;
import com.kazeik.doctor.doctorexam.bean.MockItemBean;
import com.kazeik.doctor.doctorexam.bean.MyLessonBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.PhoneUtils;
import com.kazeik.doctor.doctorexam.utils.PreferencesUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.PreferenceInject;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class TrainLessonActivity extends BaseActivity implements MyLessonAdapter.onItemEventListener {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    MyLessonAdapter adapter;
    List<LessonItemBean> allLesson;
    int cIndex;

    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.trainlesson);
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new MyLessonAdapter(this);
        adapter.setItemEventListener(this);
        adapter.setShowMoney(true);
        lsListView.setAdapter(adapter);

        if (PhoneUtils.isNetworkAvailable(this)) {
            requestNetData(getParams(), ApiUtils.allLessonList);
        } else {
            String body = PreferencesUtils.getString(this, ApiUtils.allLessonList);
            if (!TextUtils.isEmpty(body))
                parserData(body);
        }
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.allLessonList)) {
            PreferencesUtils.putString(this, tag, value);
            parserData(value);
        } else if (tag.equals(ApiUtils.addCar)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            AppUtils.showToast(this, bean.re_msg);
            if (bean.re_st.equals("success")) {
                allLesson.get(cIndex).in_cart = 1;
                allLesson.get(cIndex).is_buy = 2;
                adapter.notifyDataSetChanged();
            }
        }
    }

    @OnItemClick(R.id.ls_listView)
    public void onItemEvent(int index) {
        List<LessonItemBean> data = adapter.getSrcData();
        if (null == data || data.isEmpty()) {
            AppUtils.showToast(this, "数据为空");
            return;
        }
        LessonItemBean lessonBean = data.get(index);
//        if (lessonBean.is_buy == 1) {
        Intent intt = new Intent(this, LessonInfoActivity.class);
        intt.putExtra("lesson", lessonBean);
        startOtherView(intt);
//        } else if (lessonBean.is_buy == 2) {
//            MockItemBean.ReMsgEntity itemBean = new MockItemBean.ReMsgEntity();
//            itemBean.show_type = lessonBean.is_buy;
//            itemBean.security_code = lessonBean.security_code;
//            itemBean.id = lessonBean.id;
//            itemBean.name= lessonBean.title;
//            itemBean.price = lessonBean.original_cost;
//            itemBean.order_type = lessonBean.order_type;
//            Intent intt = new Intent(this,ShopCarActivity.class);
//            intt.putExtra("body",itemBean);
//            startOtherView(intt);
        //----------
//            if(lessonBean.in_cart == 1){
//                startOtherView(ShopCarActivity.class);
//            }else if(lessonBean.in_cart == 2){
//
//            }
//        }
    }

    private void parserData(String value) {
        allLesson = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(value);
            JSONObject msgObj = root.optJSONObject("re_msg");
            Iterator msgKeys = msgObj.keys();
            while (msgKeys.hasNext()) {
                String key = msgKeys.next().toString();
                JSONObject tempMsg = msgObj.optJSONObject(key);
                LessonItemBean lessonBean = gson.fromJson(tempMsg.toString(), LessonItemBean.class);
                allLesson.add(lessonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.setData(allLesson);
    }

    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }

    @Override
    public void onItemEvent(int index, LessonItemBean lessonBean) {
        if (lessonBean.is_buy == 1) {
            Intent intt = new Intent(this, LessonInfoActivity.class);
            intt.putExtra("lesson", lessonBean);
            startOtherView(intt);
        } else {
            if (lessonBean.in_cart == 1) {
                startOtherView(ShopCarActivity.class);
            } else {
                cIndex = index;
                RequestParams params = getParams();
                params.addBodyParameter("course_id", lessonBean.id);
                params.addBodyParameter("security_code", lessonBean.security_code);
                params.addBodyParameter("order_type", lessonBean.order_type);
                requestNetData(params, ApiUtils.addCar);
            }
        }
    }
}
