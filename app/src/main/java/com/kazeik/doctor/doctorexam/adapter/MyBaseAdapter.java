/**
 * @author kazeik.chen
 * 2015-9-29下午9:14:27
 */
package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kazeik.chen QQ:77132995  2015-9-29下午9:14:27
 *         TODO kazeik@163.com
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected List<T> allData;
    protected LayoutInflater inflater;
    protected BitmapUtils bitUtils;

    public MyBaseAdapter(Context cont) {
        inflater = LayoutInflater.from(cont);
        bitUtils = new BitmapUtils(cont);
    }

    public void setData(List<T> srcData) {
        this.allData = srcData;
        notifyDataSetChanged();
    }

    public List<T> getSrcData() {
        return allData;
    }

    /**
     * @return
     * @author kazeik.chen QQ:77132995  2015-9-29下午9:14:28
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return allData == null || allData.isEmpty() ? 0 : allData.size();
    }

    /**
     * @param arg0
     * @return
     * @author kazeik.chen QQ:77132995  2015-9-29下午9:14:28
     */
    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return allData.get(arg0);
    }

    /**
     * @param arg0
     * @return
     * @author kazeik.chen QQ:77132995  2015-9-29下午9:14:28
     */
    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author kazeik.chen QQ:77132995  2015-9-29下午9:14:28
     */
    @Override
    public abstract View getView(int arg0, View arg1, ViewGroup arg2);

}
