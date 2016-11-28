/**
 * 
 * @author kazeik.chen   
 * 2015-7-1上午11:54:37 
 */
package com.kazeik.doctor.doctorexam.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 不可滑动的GridView
 * @author kazeik.chen QQ:77132995 2015-7-1上午11:54:37 TODO kazeik@163.com
 */
public class MGridView extends GridView {
	public boolean hasScrollBar = true;

	/**
	 * @param context
	 */
	public MGridView(Context context) {
		this(context, null);
	}

	public MGridView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public MGridView(Context context, AttributeSet attrs, int defStyle) {
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
