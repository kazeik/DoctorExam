package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.ExamIndexAdapter;
import com.kazeik.doctor.doctorexam.bean.IndexSelectBean;
import com.kazeik.doctor.doctorexam.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ExamIndexActivity extends BaseActivity {
    int len;
    HashMap<Integer, String> hashMap;
    ExamIndexAdapter adapter;
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.gd_view)
    GridView gdView;

    @Override
    public int initLayout() {
        return R.layout.activity_exam_index;
    }

    @Override
    public void initData() {
        len = getIntent().getIntExtra("len", 0);
        hashMap = (HashMap<Integer, String>) getIntent().getSerializableExtra("choice");
    }

    @Override
    public void initWeight() {
        tvTitleName.setText("索引卡");
        tvLeft.setText("返回");
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new ExamIndexAdapter(this);
        gdView.setAdapter(adapter);

        ArrayList<IndexSelectBean> allIndex = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            IndexSelectBean bean = new IndexSelectBean();
            bean.index = i;
            bean.select = TextUtils.isEmpty(hashMap.get(i));
            allIndex.add(bean);
        }
        adapter.setData(allIndex);
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }

    @OnItemClick(R.id.gd_view)
    public void onItemEvent(int i){
        Intent intt = new Intent();
        intt.putExtra("index",i);
        setResult(99,intt);
        finish();
    }
    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }
}
