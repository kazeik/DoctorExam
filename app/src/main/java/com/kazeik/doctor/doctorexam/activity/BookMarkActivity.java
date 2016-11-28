package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookMarkActivity extends BaseActivity {
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    @Override
    public int initLayout() {
        return R.layout.activity_book_mark;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvRight.setVisibility(View.GONE);
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_left)
    public void onClick() {

    }
}
