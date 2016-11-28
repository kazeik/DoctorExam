/**
 * 
 * @author kazeik.chen   
 * 2015-12-6下午2:06:43 
 */
package com.kazeik.doctor.doctorexam.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 
 * @author kazeik.chen QQ:77132995 2015-12-6下午2:06:43 TODO kazeik@163.com
 */
public class ConstListView extends ListView {
	public boolean hasScrollBar = true;

	/**
	 * @param context
	 */
	public ConstListView(Context context) {
		this(context, null);
	}

	public ConstListView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public ConstListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
