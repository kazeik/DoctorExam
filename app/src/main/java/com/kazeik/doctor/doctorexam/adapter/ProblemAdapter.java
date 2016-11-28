package com.kazeik.doctor.doctorexam.adapter;/**
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 20:59
 */

import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.HelpTypeBean;

/**
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 20:59
 */

public class ProblemAdapter extends BaseExpandableListAdapter {
    HelpTypeBean groupBean;
    LayoutInflater inflater;

    public ProblemAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setGroupBean(HelpTypeBean groupBean) {
        this.groupBean = groupBean;
    }

    @Override
    public int getGroupCount() {
        return groupBean.re_msg.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groupBean == null || groupBean.re_msg == null || groupBean.re_msg.get(i) == null || groupBean.re_msg.get(i).allSub == null || groupBean.re_msg.get(i).allSub.isEmpty() ? 0 : groupBean.re_msg.get(i).allSub.size();
    }

    @Override
    public Object getGroup(int i) {
        return groupBean.re_msg.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return groupBean.re_msg.get(i).allSub.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupView groupView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_problem_group, viewGroup, false);
            groupView = new GroupView();
            groupView.tv_groupName = (TextView) view.findViewById(R.id.tv_groupText);
            view.setTag(groupView);
        } else {
            groupView = (GroupView) view.getTag();
        }
        groupView.tv_groupName.setText(Html.fromHtml(groupBean.re_msg.get(i).title));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        GroupView groupView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_problem_group, viewGroup, false);
            groupView = new GroupView();
            groupView.tv_groupName = (TextView) view.findViewById(R.id.tv_groupText);
            view.setTag(groupView);
        } else {
            groupView = (GroupView) view.getTag();
        }
        groupView.tv_groupName.setText(Html.fromHtml(groupBean.re_msg.get(i).allSub.get(i1).title));
        groupView.tv_groupName.setPadding(30,0,0,0);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class GroupView {
        TextView tv_groupName;
    }
}
