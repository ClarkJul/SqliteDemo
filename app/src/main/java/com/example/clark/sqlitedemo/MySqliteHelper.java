package com.example.clark.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author by Clark, Date on 2018/10/7.
 * PS: Not easy to write code, please indicate.
 */

/**
 * 可以新建一个常量类对数据库操作的信息进行封装（Constant）
 * 再创建一个构造方法，方便使用
 * 创建一个管理数据库的工具DbManager
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    /**
     * 构造函数
     * @param context 上下文
     * @param name    数据库名称
     * @param factory 游标工厂
     * @param version 版本号 >=1
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    /**
     * 当数据库创建时回调的函数
     *
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建数据库时顺便创建person表
        String sql="create table "+Constant.TABLE_NAME+"("+Constant._ID+" Integer primary key,"+
                Constant.NAME+" varchar(10),"+Constant.AGE+" Integer)";
        db.execSQL(sql);//执行sql命令
    }

    /**
     * 当数据库版本更新时回调的函数
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 当数据库打开时调用的方法
     *
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
