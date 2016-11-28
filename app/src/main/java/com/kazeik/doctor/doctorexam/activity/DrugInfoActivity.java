package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.DrugInfoBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.lidroid.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrugInfoActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;

    String id;
    @Bind(R.id.tv_baseInfo)
    TextView tvBaseInfo;
    @Bind(R.id.tv_info)
    TextView tvInfo;
    @Bind(R.id.tv_buliang)
    TextView tvBuliang;
    @Bind(R.id.tv_zhuYi)
    TextView tvZhuYi;
    @Bind(R.id.tv_yaoli)
    TextView tvYaoli;
    @Bind(R.id.tv_yaodong)
    TextView tvYaodong;
    @Bind(R.id.tv_zuoyong)
    TextView tvZuoyong;
    @Bind(R.id.tv_guige)
    TextView tvGuige;
    @Bind(R.id.tv_chenFeng)
    TextView tvChenFeng;
    @Bind(R.id.tv_xingZhuang)
    TextView tvXingZhuang;
    @Bind(R.id.tv_zhuZhi)
    TextView tvZhuZhi;
    @Bind(R.id.tv_jingji)
    TextView tvJingji;
    @Bind(R.id.tv_chuCang)
    TextView tvChuCang;
    @Bind(R.id.tv_jingXi)
    TextView tvJingXi;
    @Bind(R.id.tv_yaojiZhiji)
    TextView tvYaojiZhiji;
    @Bind(R.id.tv_shiyin)
    TextView tvShiyin;
    @Bind(R.id.tv_shengCan)
    TextView tvShengCan;

    @Override
    public int initLayout() {
        return R.layout.activity_drug_info;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void initWeight() {
        tvTitleName.setText("药品详情");
        tvLeft.setText(R.string.back);
        tvLeft.setVisibility(View.VISIBLE);

        RequestParams params = getParams();
        params.addBodyParameter("medicine_id", id);
        requestNetData(params, ApiUtils.drugDetail);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.drugDetail)) {
            DrugInfoBean infoBean = gson.fromJson(value, DrugInfoBean.class);
            tvBaseInfo.setText("名称：" + infoBean.re_msg.trade_name + "\n类别：" + infoBean.re_msg.scientific);
            tvInfo.setText(infoBean.re_msg.dosage);
            tvBuliang.setText(TextUtils.isEmpty(infoBean.re_msg.adverse_reactions) ? "无" : infoBean.re_msg.adverse_reactions);
            tvZhuYi.setText(infoBean.re_msg.note);
            tvYaoli.setText(infoBean.re_msg.pharmacological_action);
            tvYaodong.setText(infoBean.re_msg.pharmacokinetics);
            tvZuoyong.setText(infoBean.re_msg.drug_interactions);
            tvGuige.setText(infoBean.re_msg.dosage_specifications);
            tvChenFeng.setText(infoBean.re_msg.composition);
            tvXingZhuang.setText(infoBean.re_msg.character);
            tvZhuZhi.setText(infoBean.re_msg.indications);
            tvJingji.setText(infoBean.re_msg.contraindications);
            tvChuCang.setText(infoBean.re_msg.storage_unit);

            tvJingXi.setText(infoBean.re_msg.dosage_specifications);
            tvYaojiZhiji.setText(infoBean.re_msg.formulation_preparation_type);
            tvShiyin.setText(infoBean.re_msg.applicable);
            tvShengCan.setText(infoBean.re_msg.producer);

        }
    }

    @OnClick(R.id.tv_left)
    public void onClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
