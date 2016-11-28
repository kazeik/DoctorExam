package com.dteviot.epubviewer;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.adapter.EpubChapterAdapter;
import com.kazeik.doctor.doctorexam.bean.MarkItem;
import com.dteviot.epubviewer.epub.Book;
import com.dteviot.epubviewer.epub.TableOfContents;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.utils.AppUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class EpubMainActivity extends BaseActivity implements IResourceSource,
        View.OnClickListener, OnItemClickListener {
    private final static int LIST_EPUB_ACTIVITY_ID = 0;
    private final static int LIST_CHAPTER_ACTIVITY_ID = 1;
    private final static int CHECK_TTS_ACTIVITY_ID = 2;
    private final static int CHECK_MARK_ACTIVITY_ID = 3;

    public static final String CHAPTER_EXTRA = "CHAPTER_EXTRA";
    public static final String BOOKMARK_EXTRA = "BOOKMARK_EXTRA";

    SlidingMenu menu;
    ListView lsPageIndex;
    EpubChapterAdapter adapter;
    TableOfContents toc;
    public String[] settings;
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll_view)
    LinearLayout llView;

    /*
     * the app's main view
     */
    private EpubWebView mEpubWebView;

    TextToSpeechWrapper mTtsWrapper;

    // private ServerSocketThread mWebServerThread = null;

    private void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowWidthRes(R.dimen.dp_15);
        // menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.dp_80);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        // 为侧滑菜单设置布局
        menu.setMenu(R.layout.leftmenu);

        ImageView ivFont = (ImageView) menu.findViewById(R.id.iv_font);
        ivFont.setOnClickListener(this);
        menu.findViewById(R.id.iv_back).setOnClickListener(this);
        menu.findViewById(R.id.iv_bookMark).setOnClickListener(this);

        lsPageIndex = (ListView) menu.findViewById(R.id.ls_listView);
        lsPageIndex.setOnItemClickListener(this);

        adapter = new EpubChapterAdapter(this);
        lsPageIndex.setAdapter(adapter);

//        launchChaptersList();
    }


    // private ServerSocketThread createWebServer() {
    // FileRequestHandler handler = new FileRequestHandler(this);
    // WebServer server = new WebServer(handler);
    // return new ServerSocketThread(server, Globals.WEB_SERVER_PORT);
    // }

    private EpubWebView createView() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            return new EpubWebView23(this);
        } else {
            return new EpubWebView30(this);
        }
    }

    // @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    // // Inflate the menu; this adds items to the action bar if it is present.
    // getMenuInflater().inflate(R.menu.activity_main, menu);
    // return true;
    // }

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle item selection
//		switch (item.getItemId()) {
//		case R.id.menu_pick_epub:
//			launchBookList();
//			return true;
//		case R.id.menu_bookmark:
//			launchBookmarkDialog();
//			return true;
//		case R.id.menu_chapters:
//			launchChaptersList();
//			return true;
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}

    private void launchBookList() {
        Intent listComicsIntent = new Intent(this, ListEpubActivity.class);
        startActivityForResult(listComicsIntent, LIST_EPUB_ACTIVITY_ID);
    }

    private void launchChaptersList() {
        Book book = getBook();
        if (book == null) {
            Utility.showToast(this, R.string.no_book_selected);
        } else {
            toc = book.getTableOfContents();
            if (toc.size() == 0) {
                Utility.showToast(this, R.string.table_of_contents_missing);
            } else {
                Intent listChaptersIntent = new Intent(this,
                        ListMarkActivity.class);
                toc.pack(listChaptersIntent, ListMarkActivity.CHAPTERS_EXTRA);
                // startActivityForResult(listChaptersIntent,
                // LIST_CHAPTER_ACTIVITY_ID);
                adapter.setTable(toc);
            }
        }
    }

    private void launchBookmarkDialog() {
        BookmarkDialog dlg = new BookmarkDialog(this);
        dlg.show();
        dlg.setSetBookmarkAction(mSaveBookmark);
        dlg.setGotoBookmarkAction(mGotoBookmark);
        dlg.setStartSpeechAction(mStartSpeech);
        dlg.setStopSpeechAction(mStopSpeech);
    }

    /*
     * Should return with epub or chapter to load
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHECK_TTS_ACTIVITY_ID) {
            mTtsWrapper.checkTestToSpeechResult(this, resultCode);
            return;
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LIST_EPUB_ACTIVITY_ID:
                    onListEpubResult(data);
//                    launchChaptersList();
                    break;

                case LIST_CHAPTER_ACTIVITY_ID:
                    onListChapterResult(data);
//                    launchChaptersList();
                    break;
                case CHECK_MARK_ACTIVITY_ID:
                    MarkItem item = (MarkItem) data
                            .getSerializableExtra(BOOKMARK_EXTRA);
                    if (item == null) {
                        return;
                    }
                    if (menu.isMenuShowing())
                        menu.toggle();
                    mEpubWebView.gotoBookmark(new Bookmark(item.fileName, Uri
                            .parse(item.uri), item.scrollY));
                    break;

                default:
                    Utility.showToast(this, R.string.something_is_badly_broken);
            }
            launchChaptersList();
        } else if (resultCode == RESULT_CANCELED) {
            Utility.showErrorToast(this, data);
        }
    }

    private void onListEpubResult(Intent data) {
        String fileName = data.getStringExtra(ListEpubActivity.FILENAME_EXTRA);
        loadEpub(fileName, null);
    }

    private void onListChapterResult(Intent data) {
        Uri chapterUri = data.getParcelableExtra(CHAPTER_EXTRA);
        mEpubWebView.loadChapter(chapterUri);
    }

    private void loadEpub(String fileName, Uri chapterUri) {
        AppUtils.logs(getClass(),fileName +" ===>> ");
        mEpubWebView.setBook(fileName);
        mEpubWebView.loadChapter(chapterUri);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bookmark bookmark = mEpubWebView.getBookmark();
        if (bookmark != null) {
            bookmark.save(outState);
        }
    }

    private IAction mSaveBookmark = new IAction() {
        public void doAction() {
            Bookmark bookmark = mEpubWebView.getBookmark();
            if (bookmark != null) {
                bookmark.saveToSharedPreferences(EpubMainActivity.this, "test");
            }
        }
    };

    private IAction mGotoBookmark = new IAction() {
        public void doAction() {
            Log.e("tag", " BOOK = " + getBook().getFileName());
            mEpubWebView.gotoBookmark(new Bookmark(EpubMainActivity.this));
        }
    };

    private IAction mStartSpeech = new IAction() {
        public void doAction() {
            mTtsWrapper.checkTextToSpeech(EpubMainActivity.this,
                    CHECK_TTS_ACTIVITY_ID);
            mEpubWebView.speak(mTtsWrapper);
        }
    };

    private IAction mStopSpeech = new IAction() {
        public void doAction() {
            mTtsWrapper.stop();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTtsWrapper.onDestroy();
        // mWebServerThread.stopThread();
    }

    @Override
    public int initLayout() {
        return R.layout.activity_book_read;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvLeft.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);
        tvLeft.setText("章节");
        tvRight.setText("添加书签");
        tvTitleName.setText("电子书");

        settings = new String[8];
        // mEpubWebView = (EpubWebView) findViewById(R.id.webview);

        mEpubWebView = createView();
        // setContentView(mEpubWebView);
        llView.addView(mEpubWebView);
        mTtsWrapper = new TextToSpeechWrapper();
        // mWebServerThread = createWebServer();
        // mWebServerThread.startThread();

        initMenu();

        // TestCases.run(this);
        if (savedInstanceState != null) {
            // screen orientation changed, reload 屏幕发生变化时，读取存的状态
            mEpubWebView.gotoBookmark(new Bookmark(savedInstanceState));
        } else {
            // app has just been started.
            // If a bookmark has been saved, go to it, else, ask user for epub
            // to view 刚启动时。如果存有记录。读取记录，否则显示书本列表供用户选择
            String fileName = getIntent().getStringExtra("path");
//            fileName = "/storage/emulated/0/Download/海南医师定期考核业务水平测评考试指南—人文医学(分册)_3.epub";
            Bookmark bookmark = new Bookmark(this);
            bookmark.loadState(this,fileName);
            if (bookmark.isEmpty()) {
//                launchBookList();
                loadEpub(fileName, null);
            } else {
                mEpubWebView.gotoBookmark(bookmark);
            }
            launchChaptersList();
        }
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }

    /*
     * Book currently being used. (Hack to provide book to WebServer.)
     */
    public Book getBook() {
        return mEpubWebView.getBook();
    }

    @Override
    public ResourceResponse fetch(Uri resourceUri) {
        return getBook().fetch(resourceUri);
    }


    private void showDialog() {
        final EditText etMark = new EditText(this);
        etMark.setSingleLine(true);
        new AlertDialog.Builder(this).setTitle("添加书签").setView(etMark)
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String userMsg = etMark.getText().toString();
                        Bookmark bookmark = mEpubWebView.getBookmark();
                        if (bookmark != null) {
                            bookmark.saveToSharedPreferences(EpubMainActivity.this,
                                    userMsg);
                        }
                    }
                }).setNegativeButton("取消", null).create().show();
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @author kazeik.chen QQ:77132995 2016-6-17上午9:48:40
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        Uri uri = toc.get(arg2).getContentWithoutTag();
        // Intent intent = new Intent();
        // intent.putExtra(CHAPTER_EXTRA, uri);
        mEpubWebView.loadChapter(uri);
        menu.toggle();
    }

    // ---- Change Style
    public void setCSS() {
        // navigator.changeCSS(bookSelector, settings);
        // TextSize size = TextSize.valueOf(settings[3]);
        TextSize size = TextSize.NORMAL;
        switch (Integer.parseInt(settings[3])) {
            case 0:
                size = TextSize.LARGEST;
                break;
            case 1:
                size = TextSize.LARGER;
                break;
            case 2:
                size = TextSize.NORMAL;
                break;
            case 3:
                size = WebSettings.TextSize.SMALLER;
                break;
            case 4:
                size = TextSize.SMALLEST;
                break;
        }
        mEpubWebView.getSettings().setTextSize(size);
        mEpubWebView.setBackgroundColor(Color.parseColor(settings[1]));
    }

    public void setBackColor(String my_backColor) {
        settings[1] = my_backColor;
    }

    public void setColor(String my_color) {
        settings[0] = my_color;
    }

    public void setFontType(String my_fontFamily) {
        settings[2] = my_fontFamily;
    }

    public void setFontSize(String my_fontSize) {
        settings[3] = my_fontSize;
    }

    public void setLineHeight(String my_lineHeight) {
        if (my_lineHeight != null)
            settings[4] = my_lineHeight;
    }

    public void setAlign(String my_Align) {
        settings[5] = my_Align;
    }

    public void setMarginLeft(String mLeft) {
        settings[6] = mLeft;
    }

    public void setMarginRight(String mRight) {
        settings[7] = mRight;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                menu.toggle();
                break;
            case R.id.tv_right:
                showDialog();
                break;
            case R.id.iv_bookMark:
                Intent intt = new Intent(this, ListMarkActivity.class);
                intt.putExtra("bookPath", getBook().getFileName());
                startActivityForResult(intt, CHECK_MARK_ACTIVITY_ID);
                menu.toggle();
                break;
            case R.id.iv_font:
                DialogFragment newFragment = new ChangeCSSMenu();
                newFragment.show(getFragmentManager(), "");
                break;
            case R.id.iv_back:
                saveLoading();
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveLoading();
    }

    private void saveLoading() {
        Bookmark bookmark = mEpubWebView.getBookmark();
        if (bookmark != null) {
            bookmark.save(this);
        }
    }
}
