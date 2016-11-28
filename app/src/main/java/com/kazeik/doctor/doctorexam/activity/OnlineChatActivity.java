package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.TalkListAdapter;
import com.kazeik.doctor.doctorexam.bean.TalkListBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.lidroid.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by kazeik.chen on 2016/4/21 0021 13:35.
 * email:kazeik@163.com ,QQ:77132995
 */
public class OnlineChatActivity extends BaseActivity {
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    TalkListAdapter adapter;
    TalkListBean listBean;
    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("在线客服");
        tvLeft.setText("首页");
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new TalkListAdapter(this);
        lsListView.setAdapter(adapter);

        RequestParams params = getParams();
        params.addBodyParameter("security_code", "0ae247d746ebb5010bbb18e7226b46b9");
        requestNetData(params, ApiUtils.chatList);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if(tag.equals(ApiUtils.chatList)){
            listBean = gson.fromJson(value,TalkListBean.class);
            adapter.setData(listBean.re_msg);
        }
    }
    @OnItemClick(R.id.ls_listView)
    public void onItemEvent(int i){
        Intent intt= new Intent(this,OnlineTalkActivity.class);
        intt.putExtra("id",listBean.re_msg.get(i).uid);
        startOtherView(intt);
    }

    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }
}
