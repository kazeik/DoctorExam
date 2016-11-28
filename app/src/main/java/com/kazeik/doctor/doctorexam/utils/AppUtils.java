package com.kazeik.doctor.doctorexam.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.kazeik.doctor.doctorexam.BaseActivity;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class AppUtils {
	// public static final String BASE_URL =
	//http://www.nanfang001.com/Support/
	//http://123.56.128.49:8082/logviewer.aspx 	调试日志查看
	//http://123.56.128.49:8082/interfacetester/接口调试工具
	public static boolean debug = false;
//	public static boolean subDebug = true;

	public static int textSize =15;


	public static String DirPath = "";

	public static String BASE_URL;

	public static void logs(String tag, String msg) {
		if (debug) {
			Log.e(tag, msg);
		}
	}

	public static void logs(Class<?> classBody, String msg) {
		if (debug) {
			Log.e(classBody.getName(), msg);
		}
	}

	public static void showToast(Context ct, CharSequence msg) {
		Toast toast = Toast.makeText(ct, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showToast(Context ct, int msg) {
		Toast toast = Toast.makeText(ct, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void hideKey(Activity act) {
		((InputMethodManager) act
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(
						act.getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static void getSystemImage(BaseActivity act, int code) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		act.startActivityForResult(Intent.createChooser(intent, "请选择您的操作"),
				code);
	}

	public static void startMapService(Activity act, String lat, String lon,
			String address) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String temp = "";
		if (!TextUtils.isEmpty(address)) {
			temp = "," + address;
		}
		Uri uri = Uri.parse("geo:" + lat + "," + lon + temp);
		intent.setData(uri);
		try {
			act.startActivity(intent);
		} catch (Exception ex) {
			AppUtils.showToast(act, "未发现地图服务");
		}
	}
	
	public static void startCallPhone(Activity act,String phone){
		Uri uri = Uri.parse("tel:"+phone);
		Intent it = new Intent(Intent.ACTION_DIAL, uri); 
		act.startActivity(it);
	}

	public static void cropImage(BaseActivity act, Uri uri, int outputX,
			int outputY, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("return-data", true);
		act.startActivityForResult(intent, requestCode);
	}

	public static void showKey(Activity act) {
		InputMethodManager imm2 = (InputMethodManager) act
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm2.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
	}

	/**
	 * �?
	 * 
	 * @param value
	 * @param value1
	 * @return
	 * @author kazeik.chen QQ:77132995 2015-8-25上午10:45:16 TODO kazeik@163.com
	 */
	public static float subMoney(float value, float value1) {
		BigDecimal b1 = new BigDecimal(value + "");
		BigDecimal b2 = new BigDecimal(value1 + "");
		return b1.subtract(b2).floatValue();
	}

	/**
	 * �?
	 * 
	 * @param value
	 * @param value1
	 * @return
	 * @author kazeik.chen QQ:77132995 2015-8-25上午10:45:26 TODO kazeik@163.com
	 */
	public static float subMoney(String value, float value1) {
		BigDecimal b1 = new BigDecimal(value);
		BigDecimal b2 = new BigDecimal(value1 + "");
		return b1.subtract(b2).floatValue();
	}

	public static float addMoney(float value, float value1) {
		BigDecimal b1 = new BigDecimal(value + "");
		BigDecimal b2 = new BigDecimal(value1 + "");
		return b1.add(b2).floatValue();
	}

	public static float addMoney(float value, String value1) {
		BigDecimal b1 = new BigDecimal(value + "");
		BigDecimal b2 = new BigDecimal(value1);
		return b1.add(b2).floatValue();
	}

	public static float addMoney(String value, float value1) {
		BigDecimal b1 = new BigDecimal(value);
		BigDecimal b2 = new BigDecimal(value1 + "");
		return b1.add(b2).floatValue();
	}

	public static float mul(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).floatValue();
	}

	/*
	 * MD5加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	/**
	 * 得到参数列表字符串
	 * 
	 * @param method
	 *            请求类型 get or post
	 * @param paramValues
	 *            参数map对象
	 * @return 参数列表字符串
	 */
	public static String getParams(String method,
			Map<String, Object> paramValues) {
		String params = "";
		Set<String> key = paramValues.keySet();
		String beginLetter = "";
		if (method.equalsIgnoreCase("get")) {
			beginLetter = "?";
		}
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			if (params.equals("")) {
				params += beginLetter + s + "=" + paramValues.get(s);
			} else {
				params += "&" + s + "=" + paramValues.get(s);
			}
		}
		return params;
	}
}
