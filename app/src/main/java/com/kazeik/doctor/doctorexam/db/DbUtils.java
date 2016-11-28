/**
 * @author kazeik.chen
 * 2016-6-17下午1:24:36
 */
package com.kazeik.doctor.doctorexam.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.kazeik.doctor.doctorexam.bean.MarkItem;

/**
 * @author kazeik.chen QQ:77132995 2016-6-17下午1:24:36 TODO kazeik@163.com
 */
public class DbUtils extends SQLiteOpenHelper {
    private static final String DBNAME = "examDB";
    private static final int VERSION = 1;
    public static final String tab_mark = "tab_mark";
    public static final String tab_loading = "tab_loading";
    public static final String tab_downloading = "tab_downloading";
    private static DbUtils instance;

    public static DbUtils getInstance(Context context) {
        if (null == instance) {
            instance = new DbUtils(context);
        }
        return instance;
    }

    /**
     * @param context
     */
    private DbUtils(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    /**
     * @param arg0
     * @author kazeik.chen QQ:77132995 2016-6-17下午1:24:36
     */
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL("create table "
                + tab_mark
                + "(id integer primary key autoincrement,uri text,fileName text,scrolly float,titleName text,markTime text)");
        arg0.execSQL("create table " + tab_loading + "(id integer primary key autoincrement,uri text,fileName text,scrolly float)");
        arg0.execSQL("create table "+ tab_downloading+"(id integer primary key autoincrement,path text,state integer,current text)");
    }

    /**
     * @param sqliteDatabase
     * @param arg1
     * @param arg2
     * @author kazeik.chen QQ:77132995 2016-6-17下午1:24:36
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int arg1, int arg2) {
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS " + tab_mark);
        sqliteDatabase.execSQL("drop table if exists " + tab_loading);
        sqliteDatabase.execSQL("drop table if exists " + tab_downloading);
        onCreate(sqliteDatabase);
    }

    public SQLiteDatabase getDB() {
        return getReadableDatabase();
    }

    public void installValue(ContentValues cv) {
        getDB().insert(tab_mark, null, cv);
    }

    public void installLoadValue(ContentValues cv, String path) {
        Cursor cursor = getDB().rawQuery("select fileName from " + tab_loading + " where fileName='" + path + "';", null);
        if (cursor.getCount() == 0)
            getDB().insert(tab_loading, null, cv);
        else {
            getDB().update(tab_loading, cv, "fileName=?", new String[]{path});
        }
    }

    public void installDownLoading(ContentValues cv,String path){
        Cursor cursor = getDB().rawQuery("select path from "+tab_downloading +" where path='"+path+"';",null);
        if(cursor.getCount() == 0)
        getDB().insert(tab_downloading,null,cv);
        getDB().update(tab_downloading,cv,"path=?",new String[]{path});
        cursor.close();
    }

    public int getFileDownloadState(String path){
        int state = 0;
        Cursor cursor = getDB().rawQuery("select state from "+tab_downloading +" where path='"+path+"';",null);
        if(cursor.getCount() != 0) {
            while(cursor.moveToNext()){
               state = cursor.getInt(cursor.getColumnIndex("state"));
            }
        }
        return state;
    }

    public MarkItem loadState(String path) {
        if (TextUtils.isEmpty(path))
            return null;
        Cursor cursor = getDB().rawQuery("select * from " + tab_loading + " where fileName='" + path + "';", null);
        MarkItem item = new MarkItem();
        while (cursor.moveToNext()) {
            item.uri = cursor.getString(cursor.getColumnIndex("uri"));
            item.fileName = cursor.getString(cursor
                    .getColumnIndex("fileName"));
            item.scrollY = cursor
                    .getFloat(cursor.getColumnIndex("scrolly"));
        }
        return item;
    }

    public ArrayList<MarkItem> getRecodeItem(String fileTitle) {
        ArrayList<MarkItem> allItem = new ArrayList<MarkItem>();
        if (TextUtils.isEmpty(fileTitle))
            return allItem;
        String sql = "select * from "
                + tab_mark
                + (TextUtils.isEmpty(fileTitle) ? ";" : " where fileName='"
                + fileTitle + "';");
        Cursor cursor = getDB().rawQuery(sql, null);
        Log.e("tag", "slq = " + sql);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MarkItem item = new MarkItem();
                item.uri = cursor.getString(cursor.getColumnIndex("uri"));
                item.fileName = cursor.getString(cursor
                        .getColumnIndex("fileName"));
                item.scrollY = cursor
                        .getFloat(cursor.getColumnIndex("scrolly"));
                item.time = cursor.getString(cursor.getColumnIndex("markTime"));
                item.markName = cursor.getString(cursor
                        .getColumnIndex("titleName"));
                allItem.add(item);
            }
        }
        return allItem;
    }

    public long deleteItem(String titleName) {
        return getDB().delete(tab_mark, "titleName=?", new String[]{titleName});
    }
}
