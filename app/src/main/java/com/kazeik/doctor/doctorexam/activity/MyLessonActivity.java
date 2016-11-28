package com.kazeik.doctor.doctorexam.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.MyLessonAdapter;
import com.kazeik.doctor.doctorexam.bean.LessonItemBean;
import com.kazeik.doctor.doctorexam.bean.MyLessonBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.PhoneUtils;
import com.kazeik.doctor.doctorexam.utils.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MyLessonActivity extends BaseActivity implements MyLessonAdapter.onItemEventListener {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    ListView lsListView;
    MyLessonAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_lesson;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText(R.string.mylesson);
        tvLeft.setText(R.string.homepage);
        tvLeft.setVisibility(View.VISIBLE);

        adapter = new MyLessonAdapter(this);
        adapter.setItemEventListener(this);
        lsListView.setAdapter(adapter);

        if(PhoneUtils.isNetworkAvailable(this))
        requestNetData(getParams(), ApiUtils.myLessonList);
        else{
            String body = PreferencesUtils.getString(this,ApiUtils.myLessonList);
            parserMyLesson(body);
        }
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.myLessonList)) {
            PreferencesUtils.putString(this,tag,value);
            parserMyLesson(value);
        }
    }
    private void parserMyLesson(String value){
        List<LessonItemBean> allLesson = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(value);
            JSONObject msgObj = root.optJSONObject("re_msg");
            Iterator msgKeys = msgObj.keys();
            while (msgKeys.hasNext()) {
                String key = msgKeys.next().toString();
                JSONObject tempMsg = msgObj.optJSONObject(key);
                LessonItemBean lessonBean = gson.fromJson(tempMsg.toString(), LessonItemBean.class);
                allLesson.add(lessonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.setData(allLesson);
    }

    @OnClick(R.id.tv_left)
    public void onClick() {
        this.finish();
    }

    @OnItemClick(R.id.ls_listView)
    public void onItemEvent(int index) {
        LessonItemBean lessonBean = adapter.getSrcData().get(index);
        if (lessonBean.is_buy == 1) {
            startItemInfo(lessonBean);
        } else {

        }
    }

    @Override
    public void onItemEvent(int index,LessonItemBean lessonBean) {
        startItemInfo(lessonBean);
    }

    private void startItemInfo(LessonItemBean lessonBean){
        Intent intt = new Intent(this, LessonInfoActivity.class);
        intt.putExtra("lesson", lessonBean);
        intt.putExtra("myLesson",true);
        startOtherView(intt);
    }

    @Override
    public void onNetError(String tag, String value) {
    }
}
