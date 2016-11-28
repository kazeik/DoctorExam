/**
 * 
 * @author kazeik.chen   
 * 2016-6-17上午10:20:20 
 */
package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dteviot.epubviewer.epub.NavPoint;
import com.dteviot.epubviewer.epub.TableOfContents;
import com.kazeik.doctor.doctorexam.R;

/**
 * 
 * @author kazeik.chen QQ:77132995 2016-6-17上午10:20:20 TODO kazeik@163.com
 */
public class EpubChapterAdapter extends BaseAdapter {
	Context context;
	private LayoutInflater mInflater;

	TableOfContents mToc;

	public EpubChapterAdapter(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setTable(TableOfContents tempToc) {
		this.mToc = tempToc;
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
		viewHolder.chapter = (NavPoint) getItem(position);
		String title = viewHolder.chapter.getNavLabel();
		viewHolder.textView.setText(title);

		return convertView;
	}

	@Override
	public int getCount() {
		return null == mToc ? 0 : mToc.size();
	}

	@Override
	public Object getItem(int position) {
		return mToc.get(position);
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
