package com.kazeik.doctor.doctorexam.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.adapter.OnlineTalkAdapter;
import com.kazeik.doctor.doctorexam.bean.ChatItemBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class OnlineTalkActivity extends BaseActivity {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.ls_listView)
    ListView lsListView;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    public WebSocketConnection mConnection = new WebSocketConnection();
    final String wsuri = "ws://chat.kaohewang.com:4008";

    OnlineTalkAdapter talkAdapter;
    String uid;
    @Bind(R.id.et_input)
    EditText etInput;

    @Override
    public int initLayout() {
        return R.layout.activity_online_talk;
    }

    @Override
    public void initData() {
        uid = getIntent().getStringExtra("id");
    }

    List<ChatItemBean> allList;

    @Override
    public void initWeight() {
        tvTitleName.setText("在线咨询");
        tvLeft.setText("首页");
        tvLeft.setVisibility(View.VISIBLE);
        lsListView.setDivider(null);

        allList = new ArrayList<>();
        talkAdapter = new OnlineTalkAdapter(this);
        lsListView.setAdapter(talkAdapter);

        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                }

                @Override
                public void onTextMessage(String payload) {
                    AppUtils.logs(getClass(), "收到消息 = " + payload);
                    try {
                        JSONObject obj = new JSONObject(payload);
                        int msg = obj.optInt("st");
                        if (msg == 2) {
                            AppUtils.showToast(OnlineTalkActivity.this, "客服不在线");
                        } else if (msg == 1) {
                            JSONArray arr = obj.optJSONArray("remsg");
                            if (null != arr) {
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject subObj = arr.getJSONObject(i);
                                    ChatItemBean subItem = gson.fromJson(subObj.toString(), ChatItemBean.class);
                                    subItem.send = false;
                                    allList.add(subItem);
                                    talkAdapter.notifyDataSetChanged();
                                    lsListView.setSelection(allList.size() - 1);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                    AppUtils.logs(getClass(), code + " | " + reason);
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("system_id", ApiUtils.userInfoBean.re_msg.sys);
        params.addBodyParameter("user_id", ApiUtils.userInfoBean.re_msg.id);
        params.addBodyParameter("security_code", "0ae247d746ebb5010bbb18e7226b46b9");
        params.addBodyParameter("uid", ApiUtils.userInfoBean.re_msg.id);
        params.addBodyParameter("fid", uid);
        requestNetData(params, ApiUtils.chatHistory);

        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String value = etInput.getText().toString().trim();
                    if (TextUtils.isEmpty(value)) {
                        return false;
                    }

                    HashMap<String, Object> jsonMap = new HashMap<String, Object>();
                    jsonMap.put("fromid", ApiUtils.userInfoBean.re_msg.id);
                    jsonMap.put("toid", uid);
                    jsonMap.put("messages", value);
                    String msg = new JSONObject(jsonMap).toString();
                    AppUtils.logs(getClass(), "发出的消息 -> " + msg);
                    mConnection.sendTextMessage(msg);

                    ChatItemBean item = new ChatItemBean();
                    item.send = true;
                    item.mtime = MyDateUtils.getDate();
                    item.toid = uid;
                    item.messages = etInput.getText().toString();
                    allList.add(item);
                    etInput.setText("");
                    talkAdapter.notifyDataSetChanged();
                    lsListView.setSelection(allList.size() - 1);
                    return true;
                }
                return false;
            }

        });
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.chatHistory)) {
            try {
                JSONObject root = new JSONObject(value);
                JSONObject msgObj = root.optJSONObject("re_msg");
                if (msgObj != null) {
                    Iterator keys = msgObj.keys();
                    while (keys.hasNext()) {
                        String key = keys.next().toString();
                        JSONObject obj = msgObj.optJSONObject(key);
                        ChatItemBean itemBean = gson.fromJson(obj.toString(), ChatItemBean.class);
                        if (itemBean.fromid.equals(ApiUtils.userInfoBean.re_msg.id)) {
                            itemBean.send = true;
                        } else {
                            itemBean.send = false;
                        }
                        allList.add(itemBean);
                        allList = order(allList);
                        talkAdapter.setData(allList);
                        lsListView.setSelection(allList.size() - 1);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private List<ChatItemBean> order(List<ChatItemBean> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                ChatItemBean a;
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
        finish();
    }
}
