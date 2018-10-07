package com.example.clark.sqlitedemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * Author by Clark, Date on 2018/10/7.
 * PS: Not easy to write code, please indicate.
 */

/**
 * 创建SQLite数据库，需要创建一个类继承SQLiteOpenHelper
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建数据库管理器
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取数据库管理器
        helper = DbManager.getIntance(this);

        Button insertApi = findViewById(R.id.btn_insert_api);
        Button updateApi = findViewById(R.id.btn_update_api);
        Button deleteApi = findViewById(R.id.btn_delete_api);
        insertApi.setOnClickListener(this);
        updateApi.setOnClickListener(this);
        deleteApi.setOnClickListener(this);

    }

    /**
     * 点击按钮创建数据库
     *
     * @param view
     */
    public void createDB(View view) {
        /**
         * getReadableDatabase()
         * getWritableDatabase()
         * 都表示创建或打开的数据库（数据库存在时打开，不存在时创建）
         * 默认都是创建或打开可读可写的数据库，在一些特殊的情况下，如磁盘已满时，getReadableDatabase（）打开只读数据库
         */
        SQLiteDatabase db = helper.getWritableDatabase();
    }

    /**
     * 插入数据
     * _id唯一，点击两次会报错
     *
     * @param view
     */
    public void insertDatas(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql1 = "insert into " + Constant.TABLE_NAME + " values(1,'张三',28)";
        DbManager.execSQL(db, sql1);
        String sql2 = "insert into " + Constant.TABLE_NAME + " values(2,'李四',30)";
        DbManager.execSQL(db, sql2);
        db.close();//节约资源，操作完请关闭数据库
    }

    /**
     * 修改数据
     * 有内容才能修改
     *
     * @param view
     */
    public void updateDatas(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql1 = "update" + Constant.TABLE_NAME + " set " + Constant.NAME + "='Clark' where " + Constant._ID + "=1";
        DbManager.execSQL(db, sql1);
        String sql2 = "update" + Constant.TABLE_NAME + " set " + Constant.NAME + "='朱楷' where " + Constant._ID + "=2";
        DbManager.execSQL(db, sql2);
        db.close();
    }

    /**
     * 删除数据
     * 有内容才能删除
     *
     * @param view
     */
    public void deleteDatas(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql1 = "delete from" + Constant.TABLE_NAME + " where " + Constant._ID + "=2";
        DbManager.execSQL(db, sql1);
        db.close();
    }

    /**
     * 通过Sqlite提供的API进行增删改操作
     * @param v
     */
    @Override
    public void onClick(View v) {
        SQLiteDatabase db = helper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.btn_insert_api:
                /**
                 * 1.插入数据
                 *          insert(String table, String nullColumnHack, ContentValues values)
                 *          String nullColumnHack 数据库中不能插入所有字段全部为空的数据
                 *          ContentValues values 键位S听类型的Hashmap集合
                 *          返回值为long类型，-1表示插入异常
                 */
                ContentValues contentValues = new ContentValues();//一条数据的ContentValues
                contentValues.put(Constant._ID, 3);//put(字段名称，值)
                contentValues.put(Constant.NAME, "张三");
                contentValues.put(Constant.AGE, 25);
                long result = db.insert(Constant.TABLE_NAME, null, contentValues);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "插入数据失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.btn_update_api:
                /**
                 * 2.修改数据
                 *          update(String table, ContentValues values, String whereClause, String[] whereArgs)
                 *          String whereClause 修改条件
                 *          String[] whereArgs 修改条件的占位符
                 *          返回值为int类型，表示修改的条数
                 */
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put(Constant.NAME, "王五");//put(需要修改的字段名称，修改后的值)
//               int count = db.update(Constant.TABLE_NAME,contentValues1,Constant._ID+"=3",null);
                int count = db.update(Constant.TABLE_NAME, contentValues1, Constant._ID + "=?", new String[]{"3"});//与上面语句的效果一样
                if (count > 0) {
                    Toast.makeText(MainActivity.this, "修改数据成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "修改数据失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.btn_delete_api:
                /**
                 * 2.删除数据
                 *          delete(String table, String whereClause, String[] whereArgs)
                 *          返回值为int类型，表示删除的条数
                 */
                int num =db.delete(Constant.TABLE_NAME,Constant._ID+"=3",null);
//                int num =db.delete(Constant.TABLE_NAME,Constant._ID+"=?",new String[]{"3"});
                if (num > 0) {
                    Toast.makeText(MainActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "删除数据失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            default:
        }

    }
}
