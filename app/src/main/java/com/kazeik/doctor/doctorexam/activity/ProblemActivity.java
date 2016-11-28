package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.ProblemAdapter;
import com.kazeik.doctor.doctorexam.bean.HelpTypeBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProblemActivity extends BaseActivity implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.elv_listView)
    ExpandableListView elvListView;

    ProblemAdapter adapter;

    HelpTypeBean helpTypeBean;
    int index;

    @Override
    public int initLayout() {
        return R.layout.activity_problem;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.problem);
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        elvListView.setOnChildClickListener(this);
        elvListView.setOnGroupClickListener(this);

        adapter = new ProblemAdapter(this);


        RequestParams params = new RequestParams();
        params.addBodyParameter("system_id", ApiUtils.userInfoBean.re_msg.sys);
        params.addBodyParameter("system_type", "2");
        requestNetData(params, ApiUtils.helpType);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.helpType)) {
            helpTypeBean = gson.fromJson(value, HelpTypeBean.class);
            adapter.setGroupBean(helpTypeBean);
            elvListView.setAdapter(adapter);
        } else if (tag.equals(ApiUtils.helpList)) {
            List<HelpTypeBean.SubInfo> list = new ArrayList<>();
            try {
                JSONObject root = new JSONObject(value);
                JSONObject msgObj = root.optJSONObject("re_msg");
                Iterator msgKey = msgObj.keys();
                while (msgKey.hasNext()) {
                    String key = msgKey.next().toString();
                    JSONObject subKey = msgObj.optJSONObject(key);
                    Iterator subKeyIter = subKey.keys();
                    while (subKeyIter.hasNext()) {
                        String subTempKey = subKeyIter.next().toString();
                        JSONObject temp = subKey.optJSONObject(subTempKey);
                        HelpTypeBean.SubInfo subInfo = gson.fromJson(temp.toString(), HelpTypeBean.SubInfo.class);
                        list.add(subInfo);
                        list = order(list);
                        helpTypeBean.re_msg.get(index).allSub = list;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }

    private List<HelpTypeBean.SubInfo> order(List<HelpTypeBean.SubInfo> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                HelpTypeBean.SubInfo a;
                if (list.get(j - 1).id - list.get(j).id > 0) {
                    a = list.get(j - 1);
                    list.set((j - 1), list.get(j));
                    list.set(j, a);
                }
            }
        }
        return list;
    }
    @OnClick(R.id.tv_left)
    public void onClick() {
        this.finish();
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        Intent intt = new Intent(this, StudyMoreInfoActivity.class);
        intt.putExtra("id", helpTypeBean.re_msg.get(i).allSub.get(i1).id+"");
        intt.putExtra("system", 2);
        startOtherView(intt);
        return true;
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
        index = i;
        RequestParams params = new RequestParams();
        params.addBodyParameter("system_id", ApiUtils.userInfoBean.re_msg.sys);
        params.addBodyParameter("type[" + i + "]", helpTypeBean.re_msg.get(i).id);
        requestNetData(params, ApiUtils.helpList);
        return false;
    }
}
