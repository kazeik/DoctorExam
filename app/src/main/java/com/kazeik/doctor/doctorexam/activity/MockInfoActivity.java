package com.kazeik.doctor.doctorexam.activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.MockBodyAdapter;
import com.kazeik.doctor.doctorexam.bean.BaseBean;
import com.kazeik.doctor.doctorexam.bean.MockExamBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class MockInfoActivity extends BaseActivity implements View.OnClickListener {

    String id;
    String code;
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ls_listView)
    ListView lsListView;

    MockBodyAdapter bodyAdapter;
    MockExamBean examBean;

    boolean checkAnswer;
    boolean isMyLesson;

    String fileBody;

    String encryption;


    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
        code = getIntent().getStringExtra("code");
        checkAnswer = getIntent().getBooleanExtra("check", false);
        fileBody = getIntent().getStringExtra("body");
        isMyLesson = getIntent().getBooleanExtra("myLesson", false);
        encryption = getIntent().getStringExtra("encryption");
    }

    @Override
    public void initWeight() {
        int res = isMyLesson ? R.string.lessonPage : R.string.mock;
        tvTitleName.setText(res);
        tvLeft.setText(R.string.back);
        tvRight.setText(R.string.answer_car);
        tvLeft.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);

        bodyAdapter = new MockBodyAdapter(this);
        if (!checkAnswer) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_footview, null);
            view.findViewById(R.id.btn_answer).setOnClickListener(this);
            lsListView.addFooterView(view);
            bodyAdapter.setCheckAnswer(false);
        } else {
            bodyAdapter.setCheckAnswer(true);
        }
        lsListView.setAdapter(bodyAdapter);

        if (TextUtils.isEmpty(fileBody)) {
            if (isMyLesson)
                examAnswer();
            else
                mockExam();
        } else {
            parserExam(fileBody);
        }
    }

    private void mockExam() {
        RequestParams params = getParams();
        params.addBodyParameter("paper_id", id);
        params.addBodyParameter("encryption", id + "_" + code);
        requestNetData(params, ApiUtils.mockBody);
    }

    private void examAnswer() {
        RequestParams params = getParams();
        params.addBodyParameter("encryption", encryption);
        AppUtils.logs(getClass(),encryption);
        requestNetData(params, ApiUtils.examCheckAnswer);
    }


    private void parserExam(String value) {
        examBean = new MockExamBean();
        examBean.re_msg = new MockExamBean.ReMsgEntity();
        try {
            JSONObject object = new JSONObject(value);
            AppUtils.logs(getClass(), value + " --> ");
            JSONObject msgObj = object.optJSONObject("re_msg");
            if (null == msgObj)
                return;
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
        bodyAdapter.setData(examBean.re_msg.entity501);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.mockBody) ||tag.equals(ApiUtils.examCheckAnswer)) {
            parserExam(value);
        } else if (tag.equals(ApiUtils.submitAnswer) || tag.equals(ApiUtils.examSubmit)) {
            BaseBean bean = gson.fromJson(value, BaseBean.class);
            if (null == bean) {
                AppUtils.showToast(this, "数据请求错误");
                return;
            }
            if (bean.re_st.equals("success")) {
                AppUtils.showToast(this, bean.re_msg);
                Intent intt = new Intent();
                intt.putExtra("finish", true);
                setResult(99, intt);
                finish();
            }
        }
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_right:
                Intent intt = new Intent(this, ExamIndexActivity.class);
                intt.putExtra("len", examBean.re_msg.entity501.size());
                intt.putExtra("choice", bodyAdapter.getChoiceHash());
                startOtherViewForCode(intt, 99);
                break;
            case R.id.btn_answer:
                if (bodyAdapter.getAnswHash().size() < examBean.re_msg.entity501.size()) {
                    AppUtils.showToast(this, "您还有未答完的题目");
                    return;
                }
                showAlert();
                break;
        }
    }

    private void submitAnswer() {
        StringBuffer sb = new StringBuffer();
        HashMap<String, String> answerMap = bodyAdapter.getAnswHash();
        Iterator iterator = answerMap.entrySet().iterator();
//        sb.append("[");
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String answ = "a[\"" + entry.getKey().toString() + "\"][\"u\"]=\"" + entry.getValue().toString() + "\"";
//            sb.append(answ);
            //------
            sb.append(entry.getKey().toString() + "_" + entry.getValue().toString());
            //-----
            sb.append(",");
        }
        String temp = sb.toString();
        temp = temp.substring(0, temp.length() - 1);
//        sb.append(temp);
//        sb.append("]");
        String val = temp;
//        //-----
//        JSONObject object = new JSONObject(answerMap);
//        val = object.toString();
//        //------
        AppUtils.logs(getClass(), val);
        submitAnswer(val);
    }

    private void showAlert() {
        final Dialog dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(R.layout.follow_pracitce_dialog);
        TextView ldms_btn = (TextView) dialog.findViewById(R.id.tv_leftText);
        TextView vip_btn = (TextView) dialog.findViewById(R.id.tv_rightText);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_dialog_title);
        tv_title.setText("温馨提示");
        tv_content.setText("你确定要交卷吗？");
        ldms_btn.setText("取消");
        vip_btn.setText("交卷");
        ldms_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                dialog.cancel();
            }
        });
        vip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog.cancel();
                submitAnswer();
            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 99) {
            int index = data.getIntExtra("index", 0);
            lsListView.setSelection(index);
        }
    }

    private void submitAnswer(String answer) {
        RequestParams params = getParams();
        String encryp = id + "_" + code;
        params.addBodyParameter("encryption", isMyLesson ? encryption : encryp);
        params.addBodyParameter("a", answer);
        AppUtils.logs(getClass(), "encryption = " + (isMyLesson ? encryption : encryp) + " | a= " + answer);
        requestNetData(params, isMyLesson ? ApiUtils.examSubmit : ApiUtils.submitAnswer);
    }
}
