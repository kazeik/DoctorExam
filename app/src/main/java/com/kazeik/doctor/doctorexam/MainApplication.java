/**
 * @author kazeik.chen
 * 2015-6-30上午12:09:00
 */
package com.kazeik.doctor.doctorexam;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author kazeik.chen QQ:77132995 2015-6-30上午12:09:00 TODO kazeik@163.com
 */
public class MainApplication extends Application {
    public List<Activity> activityList = new LinkedList<Activity>();
    public static Map<String, Long> map;

//    public String uuid;
//    public String time;
//    public String name;
//    public String page;
//    public String state;
//    public String x;
//    public String y;
//    public String index;
//    public String CurrentSpineElementInde;
//    public String bookname;
//    static MainApplication instance;
//    public MARK markinfo;

    public List<Activity> tempList = new LinkedList<Activity>();
//
//    public static synchronized MainApplication getInstance() {
//        if (instance == null) {
//            instance = new MainApplication();
//        }
//        return instance;
//    }

//    public PackageInfo getPackageInfo() {
//        PackageInfo info = null;
//        try {
//            info = getPackageManager().getPackageInfo(getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace(System.err);
//        }
//        if (info == null) info = new PackageInfo();
//        return info;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//		TestinAgentConfig config = new TestinAgentConfig.Builder(
//				getApplicationContext())
//				.withAppKey("c27ed167420758423a2e3fe02dfaadac") // Appkey of
//																// your
//																// appliation,
//																// required
//				.withAppChannel("bangban") // Channel of your application
//				.withUserInfo("kazeik@163.com") // User infomation like login
//												// account of user
//				.withDebugModel(true) // Output the crash log in local if you
//										// open debug mode
//				.withErrorActivity(true) // Output the activity info in crash or
//											// error log
//				.withCollectNDKCrash(true) // Collect NDK crash or not if you
//											// use our NDK
//				.withOpenCrash(true) // Monitor crash if true
//				.withReportOnlyWifi(true) // Report data only on wifi mode
//				.withReportOnBack(true) // allow to report data when application
//										// in background
//				.build();
//		TestinAgent.init(config);
//		TestinAgent.setLocalDebug(true);

    }

    public void exit() {
        for (Activity a : activityList) {
            a.finish();
        }
    }

    public void add(Activity act) {
        activityList.add(act);
    }
}
