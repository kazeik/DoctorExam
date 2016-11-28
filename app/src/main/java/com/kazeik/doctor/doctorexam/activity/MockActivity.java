package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.MockAdapter;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.MockExamBean;
import com.kazeik.doctor.doctorexam.bean.MockItemBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.SdcardUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MockActivity extends BaseActivity implements MockAdapter.OnAnswerEventListener {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    MockAdapter adapter;

    MockItemBean itemBean;
    int index;
    MockExamBean examBean;

    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.mock);
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new MockAdapter(this);
        adapter.setEventListener(this);
        lsListView.setAdapter(adapter);
        lsListView.setDividerHeight(15);
        lsListView.setDivider(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestNetData(getParams(), ApiUtils.mockList);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.mockList)) {
            itemBean = gson.fromJson(value, MockItemBean.class);
            adapter.setData(itemBean.re_msg);
//        } else if (tag.equals(ApiUtils.mockBody)) {
//            File file = sdcardUtils.ByteToFile(value.getBytes(), sdcardUtils.getSDCardPath() + "DoctorExam/", itemBean.re_msg.get(index).id + "_" + itemBean.re_msg.get(index).security_code);
//            if (file.exists())
//                adapter.notifyDataSetChanged();
        } else if (tag.equals(ApiUtils.checkAnswer)) {
//            parserExam(value);
            Intent intt = new Intent(this, MockInfoActivity.class);
            intt.putExtra("id", itemBean.re_msg.get(index).id);
            intt.putExtra("code", itemBean.re_msg.get(index).security_code);
            intt.putExtra("check", true);
            intt.putExtra("body", value);
            startOtherView(intt);
        } else if (tag.equals(ApiUtils.addCar) || tag.equals(ApiUtils.mockAddCar)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            AppUtils.showToast(this, bean.re_msg);
            if (bean.re_st.equals("success")) {
                itemBean.re_msg.get(index).show_type = 2;
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void parserExam(String value) {
        examBean = new MockExamBean();
        examBean.re_msg = new MockExamBean.ReMsgEntity();
        try {
            JSONObject object = new JSONObject(value);
            JSONObject msgObj = object.optJSONObject("re_msg");
            examBean.re_msg.pid = msgObj.optInt("pid");
            JSONObject paperInfoObj = msgObj.optJSONObject("paper_info");
            examBean.re_msg.paper_info = gson.fromJson(paperInfoObj.toString(), MockExamBean.PaperInfo.class);
            //---------------------
            JSONObject cateObject = msgObj.optJSONObject("category_arr");
            Iterator iterator = cateObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String va = cateObject.optString(key);
                examBean.re_msg.category.add(va);
            }
            //---------------------
            JSONObject proObject = msgObj.optJSONObject("profession_arr");
            Iterator proIter = proObject.keys();
            while (proIter.hasNext()) {
                String key = proIter.next().toString();
                String va = proObject.optString(key);
                examBean.re_msg.profession_arr.put(va, key);
            }
            //----------------------
            JSONObject questionObject = msgObj.optJSONObject("question");
            Iterator questIter = questionObject.keys();
            while (questIter.hasNext()) {
                String tempKey = questIter.next().toString();
                JSONObject s_62 = questionObject.optJSONObject(tempKey);
                Iterator iter_62 = s_62.keys();
                while (iter_62.hasNext()) {
                    String k_501 = iter_62.next().toString();
                    JSONObject s_501 = s_62.optJSONObject(k_501);

                    MockExamBean.Entity501 entity501 = gson.fromJson(s_501.toString(), MockExamBean.Entity501.class);
                    examBean.re_msg.entity501.add(entity501);
                }
            }
            //-----------------------
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        bodyAdapter.setData(examBean.re_msg.entity501);
    }

    @OnClick(R.id.tv_left)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                this.finish();
                break;
        }
    }

    private void downloadCheckExam() {
        RequestParams params = getParams();
        params.addBodyParameter("encryption", itemBean.re_msg.get(index).id + "_" + itemBean.re_msg.get(index).security_code);
        requestNetData(params,  ApiUtils.checkAnswer);
    }

    private void downloadExam() {
        RequestParams params = getParams();
        params.addBodyParameter("encryption", itemBean.re_msg.get(index).id + "_" + itemBean.re_msg.get(index).security_code);
        params.addBodyParameter("type","2");
        AppUtils.logs(getClass(),"encryption = "+  itemBean.re_msg.get(index).id + "_" + itemBean.re_msg.get(index).security_code);
        HttpUtils utils =new HttpUtils();
        String filePath= sdcardUtils.getSDCardPath() + "DoctorExam/"+itemBean.re_msg.get(index).id + "_" + itemBean.re_msg.get(index).security_code;
        File tempFile = new File(filePath);
        if(tempFile.exists()){
            tempFile.delete();
        }
        utils.download(HttpRequest.HttpMethod.POST, ApiUtils.baseUrl + ApiUtils.mockBody, filePath, params, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                AppUtils.showToast(MockActivity.this, "文件下载失败");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
            }
        });
    }
    @Override
    public void onAnswerEvent(int pos, int type) {
        index = pos;

        Intent intt = new Intent();
        switch (type) {
            case 1:
                File file = new File(sdcardUtils.getSDCardPath() + "DoctorExam/" + itemBean.re_msg.get(pos).id + "_" + itemBean.re_msg.get(pos).security_code);
                if (file != null && file.exists()) {
                    try {
                        String txt = sdcardUtils.readTxtFile(file);
                        intt.setClass(this, MockInfoActivity.class);
                        intt.putExtra("id", itemBean.re_msg.get(pos).id);
                        intt.putExtra("code", itemBean.re_msg.get(pos).security_code);
                        intt.putExtra("body", txt);
                        startOtherView(intt);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    downloadExam();
                }
                break;
            case 2:
                intt.setClass(this,ShopCarActivity.class);
                intt.putExtra("body",itemBean.re_msg.get(pos));
                startOtherView(intt);
                break;
            case 3:
                startOtherView(OrderListActivity.class);
                break;
            case 4:
                addCart();
                break;
            case 5:
                downloadCheckExam();
                break;

        }
    }

    private void addCart(){
        RequestParams params = getParams();
        params.addBodyParameter("course_id", itemBean.re_msg.get(index).id);
        params.addBodyParameter("security_code", itemBean.re_msg.get(index).security_code);
        params.addBodyParameter("order_type", itemBean.re_msg.get(index).order_type);
        params.addBodyParameter("paper_id", itemBean.re_msg.get(index).id);
        requestNetData(params, ApiUtils.mockAddCar);
    }
}
