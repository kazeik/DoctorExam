package com.kazeik.doctor.doctorexam.activity;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

import com.dteviot.epubviewer.EpubMainActivity;
import com.kazeik.doctor.doctorexam.db.DbUtils;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.SdcardUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.task.Priority;

import org.apache.http.Header;

import java.io.File;

public class VideoDownService extends Service {
    HttpHandler httpHandler;
    public static final int DOWN_STATE = 0;
    public static final int DOWN_STATE_DOWNING = 1;
    public static final int DOWN_STATE_ERROR = 2;
    public static final int DOWN_STATE_OK = 3;

    String encryption;
    String name;
    int downIndex;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intt = new Intent(ApiUtils.VIDEODOWN);
            intt.putExtra("state", msg.what);
            intt.putExtra("index", downIndex);
            LocalBroadcastManager.getInstance(VideoDownService.this).sendBroadcast(intt);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        encryption = intent.getStringExtra("encryption");
        name = intent.getStringExtra("path");
        downIndex = intent.getIntExtra("index", 0);
        downFile();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void downFile() {
        SdcardUtils sdcardUtils = new SdcardUtils();
        final String path = sdcardUtils.getSDPath() + "/DoctorExam/video/" + name;
//        File file = new File(path);
//        if (file != null && file.exists()) {
//            file.delete();
//        }
        String url = ApiUtils.baseUrl + ApiUtils.videoDownload + "?encryption=" + encryption + "&system_id=" + ApiUtils.userInfoBean.re_msg.sys + "&user_id=" + ApiUtils.userInfoBean.re_msg.id + "&password=" + ApiUtils.userInfoBean.re_msg.password;
        AppUtils.logs(getClass(), url + "\n" + path);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("Accept-Length", "identity");
        params.setContentType("application/octet-stream");
//        params.setPriority(Pr);
        httpHandler = httpUtils.download(url, path, params, true,false,new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                ContentValues cv = new ContentValues();
                cv.put("path",path);
                cv.put("state",VideoDownService.DOWN_STATE_OK);
                cv.put("current",0);
                DbUtils.getInstance(VideoDownService.this).installDownLoading(cv,path);
                AppUtils.logs(getClass(),"---------");
                Header[] headers = responseInfo.getAllHeaders();
                AppUtils.logs(getClass(), responseInfo.contentEncoding + " | " + responseInfo.contentLength + " | " + responseInfo.contentType + " | " + responseInfo.reasonPhrase + " | " + responseInfo.result);
                for (Header temp : headers) {
                    AppUtils.logs(getClass(), temp.getName() + " | " + temp.getValue());
                }
                mhandler.sendEmptyMessage(DOWN_STATE_OK);

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                AppUtils.logs(getClass(), msg);
//                tvShow.setText("下载");

                ContentValues cv = new ContentValues();
                cv.put("path",path);
                cv.put("state",VideoDownService.DOWN_STATE_ERROR);
                cv.put("current",0);
                DbUtils.getInstance(VideoDownService.this).installDownLoading(cv,path);
                mhandler.sendEmptyMessage(DOWN_STATE_ERROR);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
//                tvShow.setText("下载中..");
                ContentValues cv = new ContentValues();
                cv.put("path",path);
                cv.put("state",VideoDownService.DOWN_STATE_DOWNING);
                cv.put("current",current+"");
                DbUtils.getInstance(VideoDownService.this).installDownLoading(cv,path);
                AppUtils.logs(getClass(), current + " ===  " + total);
                mhandler.sendEmptyMessage(DOWN_STATE_DOWNING);
            }

            @Override
            public void onStart() {
                super.onStart();
                ContentValues cv = new ContentValues();
                cv.put("path",path);
                cv.put("state",VideoDownService.DOWN_STATE_DOWNING);
                cv.put("current",0);
                DbUtils.getInstance(VideoDownService.this).installDownLoading(cv,path);
                mhandler.sendEmptyMessage(DOWN_STATE_DOWNING);
            }
        });
    }

}
