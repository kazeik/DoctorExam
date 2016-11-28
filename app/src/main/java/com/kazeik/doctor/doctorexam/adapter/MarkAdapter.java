/**
 * @author kazeik.chen
 * 2016-6-17上午10:20:20
 */
package com.kazeik.doctor.doctorexam.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.bean.MarkItem;
import com.dteviot.epubviewer.epub.NavPoint;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.utils.MyDateUtils;

/**
 * @author kazeik.chen QQ:77132995 2016-6-17上午10:20:20 TODO kazeik@163.com
 */
public class MarkAdapter extends BaseAdapter {
    Context context;
    private LayoutInflater mInflater;

    ArrayList<MarkItem> allItem;

    public MarkAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTable(ArrayList<MarkItem> items) {
        this.allItem = items;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // holds references to current item's GUI

        // if convertView is null, inflate GUI and create ViewHolder;
        // otherwise, get existing ViewHolder
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.epub_list_item, parent,
                    false);
            viewHolder = new ViewHolder();

            viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.epub_title);
            convertView.setTag(viewHolder); // store as View's tag
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the list item (view) with the chapters details
        String fileName = allItem.get(position).fileName;
        if (!TextUtils.isEmpty(fileName)) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
        }
        viewHolder.textView.setText(allItem.get(position).markName + "\n" + fileName + "\n" + MyDateUtils.getDate(Long.parseLong(allItem.get(position).time)));

        return convertView;
    }

    @Override
    public int getCount() {
        return null == allItem || allItem.isEmpty() ? 0 : allItem.size();
    }

    @Override
    public Object getItem(int position) {
        return allItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * Class implementing the "ViewHolder pattern", for better ListView
     * performance
     */
    public static class ViewHolder {
        public TextView textView; // refers to ListView item's TextView
        public NavPoint chapter;
    }

}
