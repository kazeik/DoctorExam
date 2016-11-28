package com.dteviot.epubviewer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.kazeik.doctor.doctorexam.adapter.MarkAdapter;
import com.kazeik.doctor.doctorexam.bean.MarkItem;
import com.kazeik.doctor.doctorexam.db.DbUtils;
import com.kazeik.doctor.doctorexam.BaseActivity;
import com.kazeik.doctor.doctorexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ListMarkActivity extends BaseActivity implements View.OnClickListener,
        OnItemClickListener {
    public static final String CHAPTERS_EXTRA = "CHAPTERS_EXTRA";
    MarkAdapter adapter;
    ArrayList<MarkItem> allItem;
    @Bind(R.id.tv_titleName)
    TextView tvTitleName;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.ls_listView)
    SwipeMenuListView mListView;


    @Override
    public int initLayout() {
        return R.layout.activity_mark_list;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWeight() {
        tvTitleName.setText("书签");
        tvLeft.setText("返回");
        tvLeft.setVisibility(View.VISIBLE);

        mListView.setOnItemClickListener(this);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu arg0) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_menu_delete);
                // add to menu
                arg0.addMenuItem(deleteItem);
            }

        };
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        long id = DbUtils.getInstance(ListMarkActivity.this)
                                .deleteItem(allItem.get(index).markName);
                        if (id != -1) {
                            Toast.makeText(ListMarkActivity.this, "删除成功",
                                    Toast.LENGTH_SHORT).show();
                            allItem.remove(index);
                            adapter.notifyDataSetChanged();
                        }
                        break;
                }
            }
        });

        String bookPath = getIntent().getStringExtra("bookPath");

        adapter = new MarkAdapter(this);
        mListView.setAdapter(adapter);
        allItem = DbUtils.getInstance(this).getRecodeItem(bookPath);
        adapter.setTable(allItem);
    }

    @Override
    public void onNetSuccess(String tag, String value) {

    }


    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @author kazeik.chen QQ:77132995 2016-6-17下午5:58:43
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if (allItem == null || allItem.isEmpty()) {
            return;
        }
        // return URI of selected chapter
        Intent intent = new Intent();
        intent.putExtra(EpubMainActivity.BOOKMARK_EXTRA, allItem.get(arg2));
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.tv_left)
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.tv_left:
                this.finish();
                break;
        }
    }
}
