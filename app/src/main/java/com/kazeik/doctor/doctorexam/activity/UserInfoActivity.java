package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.UserInfoBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.view.ViewPagerIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {


    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.vpiv_view)
    ViewPagerIndicatorView vpivView;

    UserInfoBean userInfo;
    EditText etName;
    EditText etSex;
    EditText etBirthday;
    EditText etWorkTime;
    EditText etStuLevel;
    EditText etDegree;
    EditText etSchool;
    EditText etFinishDay;
    EditText etMobile;
    EditText etMail;
    EditText etWorkInfo;
    EditText etAboutInfo;

    EditText etCpmlCode;
    EditText etObtainTime;
    EditText etPractisingCode;
    EditText etPractisingTime;
    EditText etRoomName;
    EditText etWorkLong;
    @Bind(R.id.tv_right)
    TextView tvRight;

    boolean edit = false;

    @Override
    public int initLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("个人信息");
        tvLeft.setText("首页");
        tvLeft.setVisibility(View.VISIBLE);

//        tvRight.setText("编辑");
//        tvRight.setVisibility(View.VISIBLE);

        final LinkedHashMap<String, View> map = new LinkedHashMap<String, View>();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_userinfo_base, null);
        etName = (EditText) view.findViewById(R.id.et_name);
        etSex = (EditText) view.findViewById(R.id.et_sex);
        etBirthday = (EditText) view.findViewById(R.id.et_birthday);
        etWorkTime = (EditText) view.findViewById(R.id.et_workTime);
        etStuLevel = (EditText) view.findViewById(R.id.et_stuLevel);
        etDegree = (EditText) view.findViewById(R.id.et_degree);
        etSchool = (EditText) view.findViewById(R.id.et_school);
        etFinishDay = (EditText) view.findViewById(R.id.et_finishDay);
        etMobile = (EditText) view.findViewById(R.id.et_mobile);
        etMail = (EditText) view.findViewById(R.id.et_mail);


        View view1 = inflater.inflate(R.layout.layout_userinfo_priactice, null);
        etObtainTime = (EditText) view1.findViewById(R.id.et_obtainTime);
        etCpmlCode = (EditText) view1.findViewById(R.id.et_cpmlCode);
        etPractisingCode = (EditText) view1.findViewById(R.id.et_practisingCode);
        etPractisingTime = (EditText) view1.findViewById(R.id.et_practisingTime);
        etRoomName = (EditText) view1.findViewById(R.id.et_roomName);
        etWorkLong = (EditText) view1.findViewById(R.id.et_workLong);

        View view2 = inflater.inflate(R.layout.layout_userinfo_doctor, null);
        etWorkInfo = (EditText) view2.findViewById(R.id.et_workInfo);
        etAboutInfo = (EditText) view2.findViewById(R.id.et_aboutInfo);
        map.put("基本资料", view);
        map.put("执业情况", view1);
        map.put("医师简介", view2);
        vpivView.setupLayout(map);

        requestNetData(getParams(), ApiUtils.getUserInfo);
    }

    private void setEdit(boolean edit) {
        etName.setFocusable(edit);
        etSex.setFocusable(edit);
        etBirthday.setFocusable(edit);
        etWorkTime.setFocusable(edit);
        etStuLevel.setFocusable(edit);
        etDegree.setFocusable(edit);
        etSchool.setFocusable(edit);
        etFinishDay.setFocusable(edit);
        etMobile.setFocusable(edit);
        etMail.setFocusable(edit);
        etWorkInfo.setFocusable(edit);
        etAboutInfo.setFocusable(edit);
        etCpmlCode.setFocusable(edit);
        etObtainTime.setFocusable(edit);
        etPractisingCode.setFocusable(edit);
        etPractisingTime.setFocusable(edit);
        etRoomName.setFocusable(edit);
        etWorkLong.setFocusable(edit);

        etName.setFocusableInTouchMode(edit);
        etSex.setFocusableInTouchMode(edit);
        etBirthday.setFocusableInTouchMode(edit);
        etWorkTime.setFocusableInTouchMode(edit);
        etStuLevel.setFocusableInTouchMode(edit);
        etDegree.setFocusableInTouchMode(edit);
        etSchool.setFocusableInTouchMode(edit);
        etFinishDay.setFocusableInTouchMode(edit);
        etMobile.setFocusableInTouchMode(edit);
        etMail.setFocusableInTouchMode(edit);
        etWorkInfo.setFocusableInTouchMode(edit);
        etAboutInfo.setFocusableInTouchMode(edit);
        etCpmlCode.setFocusableInTouchMode(edit);
        etObtainTime.setFocusableInTouchMode(edit);
        etPractisingCode.setFocusableInTouchMode(edit);
        etPractisingTime.setFocusableInTouchMode(edit);
        etRoomName.setFocusableInTouchMode(edit);
        etWorkLong.setFocusableInTouchMode(edit);
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.getUserInfo)) {
            userInfo = new UserInfoBean();
            userInfo.re_msg = new UserInfoBean.ReMsgEntity();
            try {
                JSONObject obj = new JSONObject(value);
                JSONObject msgObj = obj.optJSONObject("re_msg");

                Iterator iter = msgObj.keys();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    JSONObject tempObj = msgObj.optJSONObject(key);
                    if (key.equals("user_info")) {
                        userInfo.re_msg.user_info = gson.fromJson(tempObj.toString(), UserInfoBean.UserInfo.class);
                    } else if (key.equals("user_info_base")) {
                        userInfo.re_msg.user_info_base = gson.fromJson(tempObj.toString(), UserInfoBean.UserInfoBase.class);
                    } else if (key.equals("certificate_type")) {
                        userInfo.re_msg.certificate_type = parserJson(tempObj);
                    } else if (key.equals("nationality")) {
                        userInfo.re_msg.nationality = parserJson(tempObj);
                    } else if (key.equals("professional")) {
                        userInfo.re_msg.professional = parserJson(tempObj);
                    } else if (key.equals("gender")) {
                        userInfo.re_msg.gender = parserJson(tempObj);
                    } else if (key.equals("work_status")) {
                        userInfo.re_msg.work_status = parserJson(tempObj);
                    } else if (key.equals("job_title")) {
                        userInfo.re_msg.job_title = parserJson(tempObj);
                    } else if (key.equals("position")) {
                        userInfo.re_msg.position = parserJson(tempObj);
                    } else if (key.equals("doctor_grade")) {
                        userInfo.re_msg.doctor_grade = parserJson(tempObj);
                    } else if (key.equals("degree")) {
                        userInfo.re_msg.degree = parserJson(tempObj);
                    } else if (key.equals("education")) {
                        userInfo.re_msg.education = parserJson(tempObj);
                    } else if (key.equals("category")) {
                        userInfo.re_msg.category = parserJson(tempObj);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            etName.setText(userInfo.re_msg.user_info.doctor_name);
            etSex.setText(userInfo.re_msg.user_info.gender);
            etBirthday.setText("");
            etWorkTime.setText(userInfo.re_msg.user_info.working_time);
            etStuLevel.setText(userInfo.re_msg.user_info.education);
            etDegree.setText(userInfo.re_msg.user_info.degree);
            etSchool.setText(userInfo.re_msg.user_info.graduate_school);
            etFinishDay.setText(userInfo.re_msg.user_info.graduate_date);
            etMobile.setText(userInfo.re_msg.user_info.mobile);
//            etMail.setText("");

            etObtainTime.setText(userInfo.re_msg.user_info.credentials_no);
            etCpmlCode.setText(userInfo.re_msg.user_info.date_of_get_credential);
            etPractisingCode.setText(userInfo.re_msg.user_info.license_no);
            etPractisingTime.setText(userInfo.re_msg.user_info.date_of_get_license);
            etRoomName.setText(userInfo.re_msg.user_info.department);
            etWorkLong.setText(userInfo.re_msg.user_info.work_years);

            etWorkInfo.setText(userInfo.re_msg.user_info.summary);
            etAboutInfo.setText(userInfo.re_msg.user_info.remark);
        }
    }

    private List<String> parserJson(JSONObject obj) {
        if (null == obj) {
            return null;
        }
        List<String> allStr = new ArrayList<>();
        Iterator iterator = obj.keys();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = obj.optString(key);
            allStr.add(value);
        }
        return allStr;
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_right:
                if (edit) {
                    tvRight.setText("编辑");
                    edit = false;
                    setEdit(false);
                } else {
                    tvRight.setText("提交");
                    edit = true;
                    setEdit(true);
                }
                break;
        }
    }
}
