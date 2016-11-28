package com.kazeik.doctor.doctorexam.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dteviot.epubviewer.EpubMainActivity;
import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.LessonInfoBean;
import com.kazeik.doctor.doctorexam.bean.LessonItemBean;
import com.kazeik.doctor.doctorexam.db.DbUtils;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;
import com.kazeik.doctor.doctorexam.utils.DialogUtils;
import com.kazeik.doctor.doctorexam.utils.PhoneUtils;
import com.kazeik.doctor.doctorexam.utils.PreferencesUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.sevenheaven.segmentcontrol.SegmentControl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.OnClick;

public class LessonInfoActivity extends BaseActivity implements SegmentControl.OnSegmentControlClickListener {

    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_lessonName)
    TextView tvLessonName;
    @Bind(R.id.tv_lessonBody)
    TextView tvLessonBody;
    @Bind(R.id.sv_ment)
    SegmentControl svMent;

    LessonItemBean lessonBean;
    @Bind(R.id.tv_item1)
    TextView tvItem1;
    @Bind(R.id.tv_item2)
    TextView tvItem2;
    @Bind(R.id.tv_item3)
    TextView tvItem3;
    @Bind(R.id.tv_item4)
    TextView tvItem4;

    @Bind(R.id.tv_book_item1)
    TextView tvBookItem1;
    @Bind(R.id.tv_book_item2)
    TextView tvBookItem2;
    @Bind(R.id.tv_book_item3)
    TextView tvBookItem3;
    @Bind(R.id.tv_book_item4)
    TextView tvBookItem4;

    @Bind(R.id.sl_lessonView)
    ScrollView slLessonView;
    @Bind(R.id.ll_view)
    LinearLayout ll_view;
    LessonInfoBean infoBean;

    boolean isMylesson;

    HttpHandler httpHandler;
    @Bind(R.id.ll_page1)
    LinearLayout llPage1;
    @Bind(R.id.ll_page_view1)
    LinearLayout llPageView1;
    @Bind(R.id.ll_page2)
    LinearLayout llPage2;
    @Bind(R.id.ll_page_view2)
    LinearLayout llPageView2;
    @Bind(R.id.ll_page3)
    LinearLayout llPage3;
    @Bind(R.id.ll_page_view3)
    LinearLayout llPageView3;

    TextView tvVideoItem1;
    TextView tvVideoItem2;
    TextView tvVideoItem3;
    TextView tvVideoItem4;

    LocalBroadcastManager mLocalBroadcastManager;
    IntentFilter mIntentFilter;

    int mockIndex;

    @Override
    public int initLayout() {
        return R.layout.activity_lesson_info;
    }

    @Override
    public void initData() {
        lessonBean = (LessonItemBean) getIntent().getSerializableExtra("lesson");
        isMylesson = getIntent().getBooleanExtra("myLesson", false);
    }

    private void initView(int index) {
        switch (index) {
            case 0:
                llPageView1.setVisibility(View.VISIBLE);
                llPageView2.setVisibility(View.GONE);
                llPageView3.setVisibility(View.GONE);

                initPage1();
                break;
            case 1:
                llPageView1.setVisibility(View.GONE);
                llPageView2.setVisibility(View.VISIBLE);
                llPageView3.setVisibility(View.GONE);

                initPage2();
                break;
            case 2:
                llPageView1.setVisibility(View.GONE);
                llPageView2.setVisibility(View.GONE);
                llPageView3.setVisibility(View.VISIBLE);

                initPage3();
                break;
        }
    }
    private void changeBg(View view, int i){
        if(i %2 ==0){
            view.setBackgroundColor(Color.parseColor("#c1c1c1"));
        }
    }
    private void initPage1() {
        if (null == infoBean || infoBean.re_msg == null || infoBean.re_msg.courseware == null) {
//            AppUtils.showToast(this, "视频课件暂无数据");
            return;
        }
        tvItem1.setText("课件名");
        tvItem2.setText("课时");
        tvItem3.setText("学科");
        tvItem4.setText("操作");
        llPage1.removeAllViews();
        tvItem2.setVisibility(View.VISIBLE);
        for (int i = 0; i < infoBean.re_msg.courseware.size(); i++) {
            final LessonInfoBean.CourseWareItemBean itemBean = infoBean.re_msg.courseware.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_lesson_item, null);
            changeBg(view,i);
            llPage1.addView(view);

            tvVideoItem1 = (TextView) view.findViewById(R.id.tv_item1);
            tvVideoItem2 = (TextView) view.findViewById(R.id.tv_item2);
            tvVideoItem3 = (TextView) view.findViewById(R.id.tv_item3);
            tvVideoItem4 = (TextView) view.findViewById(R.id.tv_item4);

            tvVideoItem1.setText(itemBean.title);
            tvVideoItem2.setText(itemBean.course_class_hour);
            tvVideoItem3.setText(itemBean.key_course_major_id);

            if (!isMylesson) {
                if (lessonBean.is_buy == 2) {
                    tvItem4.setVisibility(View.GONE);
                    tvVideoItem4.setVisibility(View.GONE);
                } else {
                    tvItem4.setVisibility(View.VISIBLE);
                    tvVideoItem4.setVisibility(View.VISIBLE);
                }
            }

            String tempPath = sdcardUtils.getSDPath() + "/DoctorExam/video/" + itemBean.title + "_" + itemBean.id + ".mp4";
            itemBean.downState = DbUtils.getInstance(this).getFileDownloadState(tempPath);
            final File file = new File(tempPath);
//            if (file.exists()) {
//                tvVideoItem4.setText("开始学习");
//                tvVideoItem4.setTextColor(Color.parseColor("#32cba8"));
//            } else {
//                tvVideoItem4.setText("下载");
//                tvVideoItem4.setTextColor(Color.parseColor("#e97525"));
//            }

            if(!file.exists()){
                itemBean.downState = 0;
            }
            switch (itemBean.downState) {
                case VideoDownService.DOWN_STATE_DOWNING:
                    tvVideoItem4.setText("下载中..");
                    tvVideoItem4.setTextColor(Color.parseColor("#32cba8"));
                    break;
                case VideoDownService.DOWN_STATE_ERROR:
                case 0:
                    tvVideoItem4.setText("下载");
                    tvVideoItem4.setTextColor(Color.parseColor("#e97525"));
                    break;
                case VideoDownService.DOWN_STATE_OK:
                    tvVideoItem4.setText("开始学习");
                    tvVideoItem4.setTextColor(Color.parseColor("#32cba8"));
                    break;
            }

            final int temp = i;
            tvVideoItem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (itemBean.downState) {
                        case VideoDownService.DOWN_STATE_DOWNING:
                            if (PhoneUtils.isWifi(LessonInfoActivity.this)) {
                                downloadVideo(itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", temp);
                            } else {
                                DialogUtils.showAlertDialog(LessonInfoActivity.this, "提示", "您当前非wifi环境，下载将需要消耗您手机流量，需要继续下载吗？", "继续", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {
//                                        downFile(true, itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", tvVideoItem4);
                                        downloadVideo(itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", temp);
                                    }
                                }, "取消");
                            }
                            break;
                        case VideoDownService.DOWN_STATE_ERROR:
                            if (file.exists())
                                file.delete();
                            if (PhoneUtils.isWifi(LessonInfoActivity.this)) {
                                downloadVideo(itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", temp);
                            } else {
                                DialogUtils.showAlertDialog(LessonInfoActivity.this, "提示", "您当前非wifi环境，下载将需要消耗您手机流量，需要继续下载吗？", "继续", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {
//                                        downFile(true, itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", tvVideoItem4);
                                        downloadVideo(itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", temp);
                                    }
                                }, "取消");
                            }
                            break;
                        case VideoDownService.DOWN_STATE_OK:
                            if (file.exists()) {
                                startVideo(file.getPath());
                            }
                            break;
                        case 0:
                            if (file.exists())
                                file.delete();
//                                startVideo(file.getPath());
//                            } else {
                                if (PhoneUtils.isWifi(LessonInfoActivity.this)) {
                                    downloadVideo(itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", temp);
                                } else {
                                    DialogUtils.showAlertDialog(LessonInfoActivity.this, "提示", "您当前非wifi环境，下载将需要消耗您手机流量，需要继续下载吗？", "继续", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int position) {
//                                        downFile(true, itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", tvVideoItem4);
                                            downloadVideo(itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", temp);
                                        }
                                    }, "取消");
                                }
//                            }
                            break;
                    }
//                    if (file.exists()) {
//                        if (itemBean.downState == VideoDownService.DOWN_STATE_OK) {
//                            startVideo(file.getPath());
//                        }
//                    } else {
//                        if (PhoneUtils.isWifi(LessonInfoActivity.this)) {
//                            downFile(true, itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", tvVideoItem4);
//                        } else {
//                            DialogUtils.showAlertDialog(LessonInfoActivity.this, "提示", "您当前非wifi环境，下载将需要消耗您手机流量，需要继续下载吗？", "继续", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    downFile(true, itemBean.encryption, itemBean.title + "_" + itemBean.id + ".mp4", tvVideoItem4);
//                                }
//                            }, "取消");
//                        }
//                    }
                }
            });
        }
    }

    /**
     * 注册本地广播接收者
     */
    private void registerLocalBroadcastReceiver() {
        mIntentFilter = new IntentFilter(ApiUtils.VIDEODOWN);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(receiver, mIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocalBroadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerLocalBroadcastReceiver();
    }

    private void downloadVideo(String encryption, String path, int index) {
        Intent intt = new Intent(this, VideoDownService.class);
        intt.putExtra("encryption", encryption);
        intt.putExtra("path", path);
        intt.putExtra("index", index);
        startService(intt);
    }


    private void initPage2() {
        if (null == infoBean || infoBean.re_msg == null || infoBean.re_msg.ebook == null) {
            AppUtils.showToast(this, "电子书暂无数据");
            return;
        }

        tvBookItem1.setText("课件名");
        tvBookItem2.setText("课时");
        tvBookItem3.setText("学科");
//        tvBookItem4.setText("所属分类");

        llPage2.removeAllViews();
        for (int i = 0; i < infoBean.re_msg.ebook.size(); i++) {
            final LessonInfoBean.EbookItemBean itemBean = infoBean.re_msg.ebook.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_lesson_item, null);
            changeBg(view,i);
            llPage2.addView(view);
            TextView tvSubItem1 = (TextView) view.findViewById(R.id.tv_item1);
            TextView tvSubItem2 = (TextView) view.findViewById(R.id.tv_item2);
            TextView tvSubItem3 = (TextView) view.findViewById(R.id.tv_item3);
            final TextView tvSubItem4 = (TextView) view.findViewById(R.id.tv_item4);

            if (!isMylesson) {
                if (lessonBean.is_buy == 2) {
                    tvBookItem4.setVisibility(View.GONE);
                    tvSubItem4.setVisibility(View.GONE);
                } else {
                    tvBookItem4.setVisibility(View.VISIBLE);
                    tvBookItem4.setText("操作");
                    tvSubItem4.setVisibility(View.VISIBLE);
                }
            } else {
                tvBookItem4.setVisibility(View.VISIBLE);
                tvBookItem4.setText("操作");
            }

            tvSubItem1.setText(itemBean.name);
            tvSubItem2.setText(itemBean.professional_field);
            tvSubItem3.setText(itemBean.key_pro_field);
            tvSubItem4.setText("下载");
            tvSubItem4.setTextColor(Color.parseColor("#e97525"));

            tvItem2.setVisibility(View.VISIBLE);
            tvSubItem2.setVisibility(View.VISIBLE);

            final File file = new File(sdcardUtils.getSDPath() + "/DoctorExam/ebook/" + itemBean.name + "_" + itemBean.id + ".epub");
            if (file.exists()) {
                tvSubItem4.setText("开始学习");
                tvSubItem4.setTextColor(Color.parseColor("#32cba8"));
            } else {
                tvSubItem4.setText("下载");
                tvSubItem4.setTextColor(Color.parseColor("#e97525"));
            }

            tvSubItem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (file.exists()) {
                        Intent intt = new Intent(LessonInfoActivity.this, EpubMainActivity.class);
                        intt.putExtra("path", file.getPath());
                        startOtherView(intt);
                    } else {
                        if (PhoneUtils.isWifi(LessonInfoActivity.this)) {
                            downFile(false, itemBean.encryption, itemBean.name + "_" + itemBean.id + ".epub", tvSubItem4);
                        } else {
                            DialogUtils.showAlertDialog(LessonInfoActivity.this, "提示", "您当前非wifi环境，下载将需要消耗您手机流量，需要继续下载吗？", "继续", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    downFile(false, itemBean.encryption, itemBean.name + "_" + itemBean.id + ".epub", tvSubItem4);
                                }
                            }, "取消");
                        }

                    }
                }
            });
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null == intent)
                return;
            int index = intent.getIntExtra("index", 0);
            int state = intent.getIntExtra("state", 0);
            infoBean.re_msg.courseware.get(index).downState = state;
//            if (state == VideoDownService.DOWN_STATE_OK || state == VideoDownService.DOWN_STATE_DOWNING || state==VideoDownService.DOWN_STATE_ERROR)
                initPage1();
        }
    };

    private void startVideo(String path) {
        if (TextUtils.isEmpty(path)) {
            AppUtils.showToast(this, "视频路径为空");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "video/*");
        try {
            startActivity(intent);
        } catch (Exception ex) {
            AppUtils.showToast(LessonInfoActivity.this, "未找到合适的播放器");
        }
    }

    private void downFile(final boolean video, String encryption, String name, final TextView tvShow) {
        final String path = sdcardUtils.getSDPath() + "/DoctorExam/" + (video ? "video/" : "ebook/") + name;
        File file = new File(path);
        if (file != null && file.exists()) {
            file.delete();
        }
        String url = ApiUtils.baseUrl + (video ? ApiUtils.videoDownload : ApiUtils.ebookDownload) + "?encryption=" + encryption + "&system_id=" + ApiUtils.userInfoBean.re_msg.sys + "&user_id=" + ApiUtils.userInfoBean.re_msg.id + "&password=" + ApiUtils.userInfoBean.re_msg.password;
        AppUtils.logs(getClass(), url + "\n" + path);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("Accept-Length", "identity");
        params.setContentType("application/octet-stream");
        showHud(false);
        httpHandler = httpUtils.download(url, path, params, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                hideDialog();
                tvShow.setText("开始学习");
                tvShow.setTextColor(Color.parseColor("#32cba8"));
                if (video) {
                    startVideo(path);
                } else {
                    Intent intt = new Intent(LessonInfoActivity.this, EpubMainActivity.class);
                    intt.putExtra("path", path);
                    startOtherView(intt);
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                AppUtils.logs(getClass(), msg);
                AppUtils.showToast(LessonInfoActivity.this, "下载出错");
                tvShow.setText("下载");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                AppUtils.logs(getClass(), total + " | " + current);
//                tvShow.setText(current + "/" + total);
//                tvShow.setText(current + "");
                tvShow.setText("下载中..");
            }
        });
    }

    private void downExamFile(String encryption, String name, final TextView tvShow) {
        final String path = sdcardUtils.getSDPath() + "/DoctorExam/exam/" + name + ".txt";
        File file = new File(path);
        if (file != null && file.exists()) {
            file.delete();
        }
        String url = ApiUtils.baseUrl + ApiUtils.examDownload + "?encryption=" + encryption + "&type=2&system_id=" + ApiUtils.userInfoBean.re_msg.sys + "&user_id=" + ApiUtils.userInfoBean.re_msg.id + "&password=" + ApiUtils.userInfoBean.re_msg.password;
        AppUtils.logs(getClass(), url + "\n" + path);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("Accept-Length", "identity");
        params.setContentType("application/octet-stream");
        showHud(false);
        httpHandler = httpUtils.download(url, path, params, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                hideDialog();
                tvShow.setText("开始考试");
                tvShow.setTextColor(Color.parseColor("#32cba8"));
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                AppUtils.logs(getClass(), msg);
                AppUtils.showToast(LessonInfoActivity.this, "下载出错");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                AppUtils.logs(getClass(), total + " | " + current);
//                tvShow.setText(current + "/" + total);
                tvShow.setText(current + "");
            }
        });
    }

    private void initPage3() {
        if (null == infoBean || infoBean.re_msg == null
                || infoBean.re_msg.paperItemBeans == null) {
            AppUtils.showToast(this, "模拟卷数据为空");
            return;
        }
        llPage3.removeAllViews();
        View topView = null;
        if (isMylesson) {
            topView = LayoutInflater.from(this).inflate(R.layout.layout_lesson_mock_item, null);
            TextView tvSubItem1 = (TextView) topView.findViewById(R.id.tv_mock_item1);
            TextView tvSubItem4 = (TextView) topView.findViewById(R.id.tv_mock_item4);

            tvSubItem1.setText("考卷标题");
            tvSubItem4.setText("操作");
        } else {
            topView = LayoutInflater.from(this).inflate(R.layout.layout_lesson_item, null);
            TextView tvTopItem1 = (TextView) topView.findViewById(R.id.tv_item1);
            TextView tvTopItem2 = (TextView) topView.findViewById(R.id.tv_item2);
            TextView tvTopItem3 = (TextView) topView.findViewById(R.id.tv_item3);
            TextView tvTopItem4 = (TextView) topView.findViewById(R.id.tv_item4);


//            tvSubItem1.setVisibility(View.GONE);
            tvTopItem1.setText("考卷标题");
//            tvTopItem2.setText("考卷级别");
            tvTopItem2.setVisibility(View.GONE);
            tvTopItem3.setText("课时");
            tvTopItem4.setText("操作");

            if (lessonBean.is_buy != 1) {
                tvTopItem3.setVisibility(View.GONE);
                tvTopItem4.setVisibility(View.GONE);
            }
        }

        llPage3.addView(topView);
        for (int i = 0; i < infoBean.re_msg.paperItemBeans.size(); i++) {
            final LessonInfoBean.PaperItemBean itemBean = infoBean.re_msg.paperItemBeans.get(i);
            View view = null;
            TextView tvSubItem2 = null;
            TextView tvSubItem3 = null;
            TextView tvSubItem1 = null;
            TextView tvSubItem4 = null;
            if (isMylesson) {
                view = LayoutInflater.from(this).inflate(R.layout.layout_lesson_mock_item, null);
                tvSubItem1 = (TextView) view.findViewById(R.id.tv_mock_item1);
                tvSubItem4 = (TextView) view.findViewById(R.id.tv_mock_item4);
            } else {
                view = LayoutInflater.from(this).inflate(R.layout.layout_lesson_item, null);
                tvSubItem2 = (TextView) view.findViewById(R.id.tv_item2);
                tvSubItem3 = (TextView) view.findViewById(R.id.tv_item3);
                tvSubItem1 = (TextView) view.findViewById(R.id.tv_item1);
                tvSubItem4 = (TextView) view.findViewById(R.id.tv_item4);

                if (!isMylesson) {
                    if (lessonBean.is_buy == 2) {
                        tvItem4.setVisibility(View.GONE);
                        tvSubItem4.setVisibility(View.GONE);
                    } else {
                        tvItem4.setVisibility(View.VISIBLE);
                        tvSubItem4.setVisibility(View.VISIBLE);
                    }
                }
                tvSubItem2.setVisibility(View.GONE);
                tvItem2.setVisibility(View.GONE);
            }
            changeBg(view,i);
            llPage3.addView(view);
            tvSubItem1.setText(itemBean.caption);
            if (!isMylesson) {
                if (lessonBean.is_buy == 1) {
                    tvSubItem2.setVisibility(View.VISIBLE);
                    tvSubItem2.setText(itemBean.key_grade);
                    tvSubItem3.setText(itemBean.category);
                    tvSubItem4.setText("下载");
                    tvSubItem4.setTextColor(Color.parseColor("#e97525"));
                } else {
                    tvSubItem2.setVisibility(View.GONE);
                    tvSubItem2.setText(itemBean.category);
                    tvSubItem3.setText(itemBean.key_grade);
                    tvSubItem4.setText("");
                }
                tvSubItem2.setVisibility(View.GONE);
            }

            item4Download(tvSubItem4, itemBean, i);
        }
    }

    private void item4Download(TextView tvSubItem4, final LessonInfoBean.PaperItemBean itemBean, final int index) {
        final File file = new File(sdcardUtils.getSDPath() + "/DoctorExam/exam/" + itemBean.caption + "_" + itemBean.category + ".txt");
        if (itemBean.isFinish || itemBean.is_submit.equals("1")) {
            tvSubItem4.setText(R.string.check);
            tvSubItem4.setTextColor(Color.parseColor("#32cba8"));
        } else {
            if (file.exists()) {
                tvSubItem4.setText("开始考试");
                tvSubItem4.setTextColor(Color.parseColor("#32cba8"));
            } else {
                tvSubItem4.setText("下载");
                tvSubItem4.setTextColor(Color.parseColor("#e97525"));
            }
        }
        final TextView tempItem4 = tvSubItem4;
        tvSubItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemBean.isFinish || itemBean.is_submit.equals("1")) {
                    Intent intt = new Intent(LessonInfoActivity.this, MockInfoActivity.class);
                    intt.putExtra("id", itemBean.id);
                    intt.putExtra("code", itemBean.category);
                    intt.putExtra("myLesson", true);
                    intt.putExtra("encryption", itemBean.encryption);
                    intt.putExtra("check", true);
                    startOtherView(intt);
                } else {
                    if (file.exists()) {
                        mockIndex = index;
                        try {
                            String body = sdcardUtils.readTxtFile(file);
                            Intent intt = new Intent(LessonInfoActivity.this, MockInfoActivity.class);
                            intt.putExtra("id", itemBean.id);
                            intt.putExtra("code", itemBean.category);
                            intt.putExtra("encryption", itemBean.encryption);
                            intt.putExtra("body", body);
                            intt.putExtra("myLesson", true);
                            startOtherViewForCode(intt, 99);
                        } catch (Exception e) {
                            AppUtils.showToast(LessonInfoActivity.this, "读取文件出错");
                        }
                    } else {
                        downExamFile(itemBean.encryption, itemBean.caption + "_" + itemBean.category, tempItem4);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && data != null) {
            boolean isOk = data.getBooleanExtra("finish", false);
            infoBean.re_msg.paperItemBeans.get(mockIndex).isFinish = isOk;
            initPage3();
        }
    }

    @Override
    public void initWeight() {
        tvTitleName.setText(lessonBean.title);
        tvLeft.setText(R.string.back);
        tvLeft.setVisibility(View.VISIBLE);

        utils.display(ivIcon, "http://" + lessonBean.pic);

        tvLessonName.setText(lessonBean.title);
        tvLessonBody.setText(lessonBean.describe);
        initView(0);
        svMent.setOnSegmentControlClickListener(this);

        ll_view.setBackgroundColor(Color.parseColor("#f1f1f1"));

        if (PhoneUtils.isNetworkAvailable(this)) {
            RequestParams params = getParams();
            params.addBodyParameter("course_id", lessonBean.id);
            requestNetData(params, ApiUtils.lessonDetail);
        } else {
            String body = PreferencesUtils.getString(this, ApiUtils.lessonDetail);
            parserData(body);
        }
    }

    @Override
    public void onNetSuccess(String tag, String value) {
        if (tag.equals(ApiUtils.lessonDetail)) {
            PreferencesUtils.putString(this, tag, value);
            parserData(value);
        } else if (tag.equals(ApiUtils.ebookDownload)) {

        } else if (tag.equals(ApiUtils.videoDownload)) {

        }
    }

    private void parserData(String value) {
        infoBean = new LessonInfoBean();
        infoBean.re_msg = new LessonInfoBean.ReMsgBean();
        infoBean.re_msg.courseware = new ArrayList<>();
        infoBean.re_msg.paperItemBeans = new ArrayList<>();
        infoBean.re_msg.ebook = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(value);
            JSONObject msgObj = root.optJSONObject("re_msg");
            //----------------------
            JSONObject courseObj = msgObj.optJSONObject("courseware");
            Iterator courseObjKey = courseObj.keys();
            while (courseObjKey.hasNext()) {
                String key = courseObjKey.next().toString();
                JSONArray courseArr = courseObj.optJSONArray(key);
                for (int i = 0; i < courseArr.length(); i++) {
                    JSONObject itemObj = courseArr.optJSONObject(i);
                    LessonInfoBean.CourseWareItemBean itemBean = gson.fromJson(itemObj.toString(), LessonInfoBean.CourseWareItemBean.class);
                    infoBean.re_msg.courseware.add(itemBean);
                }
            }
            //------------------------
            JSONObject paperObj = msgObj.optJSONObject("paper");
            Iterator paperKey = paperObj.keys();
            while (paperKey.hasNext()) {
                String key = paperKey.next().toString();
                JSONArray courseArr = paperObj.optJSONArray(key);
                for (int i = 0; i < courseArr.length(); i++) {
                    JSONObject itemObj = courseArr.optJSONObject(i);
                    LessonInfoBean.PaperItemBean itemBean = gson.fromJson(itemObj.toString(), LessonInfoBean.PaperItemBean.class);
                    infoBean.re_msg.paperItemBeans.add(itemBean);
                }
            }
            //------------------------
            JSONObject ebookObj = msgObj.optJSONObject("ebook");
            Iterator ebookKey = ebookObj.keys();
            while (ebookKey.hasNext()) {
                String key = ebookKey.next().toString();
                JSONArray courseArr = ebookObj.optJSONArray(key);
                for (int i = 0; i < courseArr.length(); i++) {
                    JSONObject itemObj = courseArr.optJSONObject(i);
                    LessonInfoBean.EbookItemBean itemBean = gson.fromJson(itemObj.toString(), LessonInfoBean.EbookItemBean.class);
                    infoBean.re_msg.ebook.add(itemBean);
                }
            }
            //------------------------
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initView(0);
    }


    @OnClick(R.id.tv_left)
    public void onClick() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != httpHandler) {
            httpHandler.cancel();
        }
    }

    @Override
    public void onSegmentControlClick(int index) {
        initView(index);
    }
}
