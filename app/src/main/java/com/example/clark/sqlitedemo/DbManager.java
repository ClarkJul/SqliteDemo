package com.example.clark.sqlitedemo;

/**
 * Author by Clark, Date on 2018/10/7.
 * PS: Not easy to write code, please indicate.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 对数据库操作的工具类
 */
public class DbManager {
    private static MySqliteHelper helper;

    //单例创建管理器
    public static MySqliteHelper getIntance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句在数据库中执行语句
     *
     * @param db
     * @param sql
     */
    public static void execSQL(SQLiteDatabase db, String sql) {
        if (db != null) {
            if (sql != null && !"".equals(sql)) {
                db.execSQL(sql);
            }
        }

    }
}
