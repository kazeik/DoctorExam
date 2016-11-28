package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.PinMoneyAdapter;
import com.kazeik.doctor.doctorexam.bean.PinMoneyBean;
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

public class PinMoneyActivity extends BaseActivity implements PinMoneyAdapter.OnJoinEventListener {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.et_withDraw)
    EditText etWithDraw;
    @Bind(R.id.tv_wasJoin)
    TextView tvWasJoin;
    @Bind(R.id.tv_income)
    TextView tvIncome;
    @Bind(R.id.tv_withdraw)
    TextView tvWithdraw;
    @Bind(R.id.tv_noWithdraw)
    TextView tvNoWithdraw;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    PinMoneyAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_pin_money;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("赚零花钱");
        tvLeft.setText("首页");
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new PinMoneyAdapter(this);
        adapter.setJoinEventListener(this);
        lsListView.setAdapter(adapter);
        lsListView.setDivider(null);
        lsListView.setDividerHeight(20);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestNetData(getParams(), ApiUtils.surveyList);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.exchangeSurvey)) {
            try {
                JSONObject obj = new JSONObject(value);
                AppUtils.showToast(this, obj.optString("re_msg"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (tag.equals(ApiUtils.surveyList)) {
            PinMoneyBean moneyBean = new PinMoneyBean();
            moneyBean.re_msg = new PinMoneyBean.ReMsgEntity();
            moneyBean.re_msg.survey_list = new ArrayList<>();
            moneyBean.re_msg.user_join_info = new PinMoneyBean.UserJoinBean();
            try {
                JSONObject obj = new JSONObject(value);
                JSONObject msgObj = obj.optJSONObject("re_msg");
                JSONObject userJoinObj = msgObj.optJSONObject("user_join_info");
                moneyBean.re_msg.user_join_info = gson.fromJson(userJoinObj.toString(), PinMoneyBean.UserJoinBean.class);

                JSONObject surveyObj = msgObj.optJSONObject("survey_list");
                if (null != surveyObj) {
                    Iterator keys = surveyObj.keys();
                    while (keys.hasNext()) {
                        String key = keys.next().toString();
                        JSONObject surObj = surveyObj.optJSONObject(key);
                        PinMoneyBean.SurveyListBean surveyListBean = gson.fromJson(surObj.toString(), PinMoneyBean.SurveyListBean.class);
                        moneyBean.re_msg.survey_list.add(surveyListBean);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            tvWasJoin.setText(moneyBean.re_msg.user_join_info.survey);
            tvNoWithdraw.setText(moneyBean.re_msg.user_join_info.total);
            tvWithdraw.setText(moneyBean.re_msg.user_join_info.exchange_out);
            tvIncome.setText(moneyBean.re_msg.user_join_info.exchange_balance);

            adapter.setData(moneyBean.re_msg.survey_list);
        } else if (tag.equals(ApiUtils.gotoSurvey)) {
            try {
                JSONObject obj = new JSONObject(value);
                JSONObject msgObj = obj.optJSONObject("re_msg");
                String url = msgObj.optString("survey_link");
                Intent intt = new Intent(this, JoinActivity.class);
                intt.putExtra("url", url);
                startOtherView(intt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.btn_withdraw, R.id.tv_left})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_withdraw:
                String drawMoney = etWithDraw.getText().toString();
                if (TextUtils.isEmpty(drawMoney)) {
                    AppUtils.showToast(this, "请输入要提现的金额");
                    return;
                }
                RequestParams params = getParams();
                params.addBodyParameter("exnum", drawMoney);
                requestNetData(params, ApiUtils.exchangeSurvey);
                break;
            case R.id.tv_left:
                finish();
                break;
        }
    }

    @Override
    public void onJoinEvent(PinMoneyBean.SurveyListBean listBean) {
        RequestParams params = getParams();
        params.addBodyParameter("ost", "2");
        params.addBodyParameter("survey_id", listBean.id);
        requestNetData(params, ApiUtils.gotoSurvey);
    }
}
