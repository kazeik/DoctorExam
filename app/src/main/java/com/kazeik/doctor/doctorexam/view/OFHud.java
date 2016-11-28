package com.kazeik.doctor.doctorexam.view;


import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;


/**
 * 
 * @author kazeik.chen QQ:77132995  2014-12-23上午9:46:40 
 * TODO kazeik@163.com
 */
public class OFHud extends OFDialog {
	ProgressBar pb;
	TextView tv_msg;

	public OFHud(Context context) {
		super(context, R.style.OFHud);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.of_hud);
		pb = (ProgressBar) findViewById(R.id.progressBar);
		tv_msg = (TextView) findViewById(R.id.message);
	}

	public void setMessage(String msg) {
		this.tv_msg.setText(msg);
	}

}
