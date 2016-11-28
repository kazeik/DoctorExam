package com.kazeik.doctor.doctorexam.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * 不可滑动的expandleableListView
 * @author kazeik.chen QQ:77132995  2015-11-3下午8:55:14 
 * TODO kazeik@163.com
 */
public class CustomExpandableListView extends ExpandableListView {
	 
    public CustomExpandableListView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
  
        MeasureSpec.AT_MOST);  
  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    } 
}
