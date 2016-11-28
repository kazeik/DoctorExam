package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.NewsAdapter;
import com.kazeik.doctor.doctorexam.bean.NotifyListBean;
import com.kazeik.doctor.doctorexam.bean.StudyInfoBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements NewsAdapter.OnGroupEvent,NewsAdapter.OnSubChildEvent{


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    ListView lsListView;
    NewsAdapter adapter;
    NotifyListBean listBean;

    @Override
    public int initLayout() {
        return R.layout.activity_news;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("定考新闻");
        tvLeft.setText("首页");
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new NewsAdapter(this);
        lsListView.setAdapter(adapter);
        lsListView.setDivider(null);
        lsListView.setDividerHeight(10);
        adapter.setChildEvent(this);
        adapter.setGroupEvent(this);


        requestNetData(getParams(), ApiUtils.notifyList);
    }

    private void getSubInfo(String id){
        Intent intt = new Intent(this, StudyMoreInfoActivity.class);
        intt.putExtra("id", id);
        intt.putExtra("system",1);
        startOtherView(intt);
    }
    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.notifyList)) {
            listBean = new NotifyListBean();
            listBean.re_msg = new ArrayList<>();
            try {
                JSONObject obj = new JSONObject(value);
                JSONObject jObject =obj.optJSONObject("re_msg");
                Iterator<?> keys = jObject.keys();
                while( keys.hasNext() ){
                    NotifyListBean.ReMsgEntity tempInfoBean = new NotifyListBean.ReMsgEntity();
                    String key = (String)keys.next();
                    String val = jObject.getString(key);
                    JSONObject subObj = new JSONObject(val);
                    tempInfoBean.title = subObj.optString("title");
                    tempInfoBean.image = subObj.optString("image");
                    tempInfoBean.id = subObj.optString("id");
                    JSONObject subObject =subObj.optJSONObject("message");
                    Iterator<?> subKey = subObject.keys();
                    tempInfoBean.message = new ArrayList<>();
                    while( subKey.hasNext() ){
                        NotifyListBean.MessageEntity tempMessage = new NotifyListBean.MessageEntity();
                        String tempKey = (String)subKey.next();
                        String tempVal = subObject.getString(tempKey);
                        JSONObject tempObj = new JSONObject(tempVal);
                        tempMessage.add_time = tempObj.optString("add_time");
                        tempMessage.id = tempObj.optString("id");
                        tempMessage.title = tempObj.optString("title");
                        tempInfoBean.message.add(tempMessage);
                    }
                    listBean.re_msg.add(tempInfoBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.setData(listBean.re_msg);
        }else if(tag.equals(ApiUtils.notifyDetail)){

        }
    }


    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }

    @Override
    public void onGroupEvent(int parent) {
        getSubInfo(listBean.re_msg.get(parent).id);
    }

    @Override
    public void onChildEvent(int parent, int child) {
        getSubInfo(listBean.re_msg.get(parent).message.get(child).id);
    }
}
