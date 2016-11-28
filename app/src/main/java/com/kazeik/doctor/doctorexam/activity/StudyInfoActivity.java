package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.StudyAdapter;
import com.kazeik.doctor.doctorexam.bean.StudyInfoBean;
import com.kazeik.doctor.doctorexam.bean.StudyItemBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class StudyInfoActivity extends BaseActivity {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    ListView lsListView;
    StudyAdapter adapter;
    StudyInfoBean infoBean;
    StudyItemBean itemBean;

    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("考务信息");
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new StudyAdapter(this);
        lsListView.setAdapter(adapter);


        getData("");
    }

    private void getData(String typeId){
        RequestParams params = new RequestParams();
        params.addBodyParameter("system_id", ApiUtils.userInfoBean.re_msg.sys);
        if (!TextUtils.isEmpty(typeId))
            params.addBodyParameter("type", typeId);
        requestNetData(params, TextUtils.isEmpty(typeId)?ApiUtils.getNewsType :ApiUtils.newsList);
    }
    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.getNewsType)) {
            itemBean = gson.fromJson(value, StudyItemBean.class);
            getMoreInfo();
        }else if(tag.equals(ApiUtils.newsList)){
            infoBean = new StudyInfoBean();
            infoBean.re_msg = new ArrayList<>();
            try {
                JSONObject obj = new JSONObject(value);
                JSONObject jObject =obj.optJSONObject("re_msg");
                Iterator<?> keys = jObject.keys();
                while( keys.hasNext() ){
                    StudyInfoBean.ReMsgEntity tempInfoBean = new StudyInfoBean.ReMsgEntity();
                    String key = (String)keys.next();
                    String val = jObject.getString(key);
                    JSONObject subObj = new JSONObject(val);
                    tempInfoBean.add_time = subObj.optString("add_time");
                    tempInfoBean.click_num = subObj.optString("click_num");
                    tempInfoBean.description = subObj.optString("description");
                    tempInfoBean.id = subObj.optString("id");
                    tempInfoBean.istop = subObj.optString("istop");
                    tempInfoBean.recommend = subObj.optString("recommend");
                    tempInfoBean.source = subObj.optString("source");
                    tempInfoBean.title = subObj.optString("title");
                    infoBean.re_msg.add(tempInfoBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.setData(infoBean.re_msg);
        }

    }
    private void getMoreInfo(){
        for(int i=0;i<itemBean.re_msg.size();i++){
            String typeId = itemBean.re_msg.get(i).id;
            getData(typeId);
        }
    }
    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }


    @OnItemClick(R.id.ls_listView)
    public void onItemClick(int i) {
        Intent intt = new Intent(this, StudyMoreInfoActivity.class);
        intt.putExtra("id", infoBean.re_msg.get(i).id);
        intt.putExtra("system",0);
        startOtherView(intt);
    }
}
