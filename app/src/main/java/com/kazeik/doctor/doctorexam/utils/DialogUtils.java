package com.kazeik.doctor.doctorexam.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {
	/**
	 * 显示提示对话框
	 * 
	 * @param con
	 * @param alertTitle
	 * @param cStr
	 * @param onClickListener
	 *@param buttonText  @author kazeik.chen QQ:77132995 2014-4-8下午2:28:37 TODO kazeik@163.com
	 */
	public static void showAlertDialog(Context con, String alertTitle,
									   String cStr, DialogInterface.OnClickListener onClickListener, String buttonText) {
		AlertDialog.Builder builer = new AlertDialog.Builder(con);
		builer.setTitle(alertTitle);
		builer.setMessage(cStr);
		builer.setNegativeButton(buttonText, null);
		builer.create();
		builer.show();
	}

	/**
	 * 显示提示对话框
	 * 
	 * @param buttonText
	 * @param con
	 * @param alertTitle
	 * @param cStr
	 * @author kazeik.chen QQ:77132995 2014-4-8下午2:28:37 TODO kazeik@163.com
	 */
	public static void showAlertDialog(Context con, String alertTitle,
									   String cStr,String buttonText,
									   DialogInterface.OnClickListener listener, String cancelText) {
		AlertDialog.Builder builer = new AlertDialog.Builder(con);
		builer.setTitle(alertTitle);
		builer.setMessage(cStr);
		builer.setNegativeButton(buttonText, listener);
		builer.setPositiveButton(cancelText, null);
		builer.create();
		builer.show();
	}

	/**
	 * 显示提示对话框
	 * 
	 * @param con
	 * @param alertTitle
	 * @param cStr
	 * @param buttonText
	 * @param onclick
	 * @author kazeik.chen QQ:77132995 2014-4-8下午2:29:51 TODO kazeik@163.com
	 */
	public static void showAlertDialog(Context con, String alertTitle,
			String cStr, String buttonText,
			DialogInterface.OnClickListener onclick) {
		AlertDialog.Builder builer = new AlertDialog.Builder(con);
		builer.setTitle(alertTitle);
		builer.setMessage(cStr);
		builer.setNegativeButton(buttonText, onclick);
		builer.create();
		builer.show();
	}
}
