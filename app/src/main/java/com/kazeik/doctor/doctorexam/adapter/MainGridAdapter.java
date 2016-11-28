package com.kazeik.doctor.doctorexam.adapter;/**
 * 主界面的Grid
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 22:24
 */

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;

/**
 * 主界面的Grid
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 22:24
 */

public class MainGridAdapter extends BaseAdapter {
    int[] res = { R.drawable.ic1,R.drawable.ic7, R.drawable.ic4,R.drawable.ic6, R.drawable.ic5, R.drawable.ic8,R.drawable.ic0,R.drawable.ic3,  R.drawable.ic2,   R.drawable.ic9, R.drawable.ic10, R.drawable.ic11};
    LayoutInflater inflater;
    String[] itemName;
    public MainGridAdapter(Context cont) {
        inflater = LayoutInflater.from(cont);
        itemName = cont.getResources().getStringArray(R.array.mainGrid);
    }

    @Override
    public int getCount() {
        return res.length;
    }

    @Override
    public Object getItem(int i) {
        return res[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        SubView subView;
        if(arg1 == null){
            subView = new SubView();
            arg1 = inflater.inflate(R.layout.layout_maingrid,arg2,false);
            subView.iv_icon = (ImageView) arg1.findViewById(R.id.iv_icon);
            subView.tvText = (TextView) arg1.findViewById(R.id.tv_gridName);
            arg1.setTag(subView);
        }else{
            subView = (SubView) arg1.getTag();
        }
        subView.iv_icon.setImageResource(res[arg0]);
        subView.tvText.setText(itemName[arg0]);
        return arg1;
    }

    static class SubView{
        ImageView iv_icon;
        TextView tvText;
    }
}
