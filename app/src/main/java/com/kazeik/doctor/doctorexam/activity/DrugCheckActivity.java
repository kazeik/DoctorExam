package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.DrugAdapter;
import com.kazeik.doctor.doctorexam.bean.DrugItemBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.lidroid.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class DrugCheckActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.et_drugName)
    EditText etDrugName;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.ls_listView)
    ListView lsListView;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;

    DrugAdapter adapter;
    DrugItemBean itemBean;

    @Override
    public int initLayout() {
        return R.layout.activity_drug_check;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("药品查询");
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new DrugAdapter(this);
        lsListView.setAdapter(adapter);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if(tag.equals(ApiUtils.drugSearch)){
             itemBean = gson.fromJson(value,DrugItemBean.class);
            tvNum.setText("共"+itemBean.re_msg.res.size()+"条数据");
            adapter.setData(itemBean.re_msg.res);
        }
    }
    @OnItemClick(R.id.ls_listView)
    public void onItemEvent(int i){
        Intent intt = new Intent(this,DrugInfoActivity.class);
        intt.putExtra("id",itemBean.re_msg.res.get(i).id);
        startOtherView(intt);
    }

    @OnClick({R.id.tv_left, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.btn_search:
                searchDrug();
                break;
        }
    }

    private void searchDrug(){
        String name = etDrugName.getText().toString();
        if(TextUtils.isEmpty(name)){
            AppUtils.showToast(this,"请输入要查询的药品名");
            return;
        }

        RequestParams params = getParams();
        params.addBodyParameter("medicine_name",name);
        requestNetData(params, ApiUtils.drugSearch);
    }
}
