package com.example.androidtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 *  Class Name: DBHelper.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月25日 上午10:13:33
 *  @Copyright http://liuz.me 
 *  @url
 */
public class DBHelper extends SQLiteOpenHelper {

	//创建user表
	private static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS " + DBData.USER_INFO_TABLE + " (" + "_id integer primary key autoincrement, " 
												+ DBData.USER_ID + " TEXT," + DBData.USER_NAME + " TEXT" + ");";
	
//	private static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS " + DBData.USER_INFO_TABLE + " (" + "_id integer primary key autoincrement, " 
//			+ DBData.USER_ID + " TEXT,"
//			+ DBData.USER_NAME + " TEXT,"
//			+ DBData.USER_ID + " TEXT,"
//			+ ");";
	
    //修改原来的user表名改为_temp_user临时表名
    private static final String CREATE_TEMP_USER = "alter table " + DBData.USER_INFO_TABLE + " rename to _temp_user";
    
    //把临时备份表中的数据copy到新创建的数据库表中
    private static final String INSERT_DATA = "insert into " + DBData.USER_INFO_TABLE + " select * from _temp_user";
//    private static final String INSERT_DATA = "insert into " + DBData.USER_INFO_TABLE + " select *,'' from _temp_user";
    
    //删除临时备份表
    private static final String DROP_USER = "drop table _temp_user";
	
	public DBHelper(Context context) {
		super(context, DBData.DATABASE_NAME, null, DBData.DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建用户信息的表
		db.execSQL(CREATE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("liuz", "oldVersion..." + oldVersion + "...newVersion..." + newVersion);
		
		if (newVersion > oldVersion) {
			db.execSQL(CREATE_TEMP_USER);
			db.execSQL(CREATE_USER);
			db.execSQL(INSERT_DATA);
			db.execSQL(DROP_USER);
		}
	}
}
