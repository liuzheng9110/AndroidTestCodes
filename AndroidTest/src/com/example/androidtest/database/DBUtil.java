package com.example.androidtest.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.GetChars;

import com.example.androidtest.main.MyApplication;

/**
 * 
 *  Class Name: DBUtil.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月25日 上午10:13:50
 *  @Copyright http://liuz.me 
 *  @url
 */
public class DBUtil {

	private final static byte[] _writeLock = new byte[0];// 数据库操作锁
	private DBHelper helper;
	private static DBUtil instance = new DBUtil();
	private Context context;
	private static int connectCount = 0;

	public DBUtil() {
		context = MyApplication.getInstance().getApplicationContext();
		helper = new DBHelper(context);
	}

	public static synchronized DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		connectCount++;
		return instance;
	}

	public void close() {
		if (connectCount > 1) {
			connectCount--;
		} else if (helper != null) {
			helper.close();
			helper = null;
		}

		// if (db != null) {
		// db.close();
		// db = null;
		// }

		instance = null;
	}
	
	public void addUserInfo(ArrayList<UserInfo> userInfos){
		synchronized (_writeLock) {
			SQLiteDatabase db = null;
			try {
				db = helper.getWritableDatabase();
				int num = userInfos.size();
				for (int i = 0; i < num; i++) {
					UserInfo userInfo = userInfos.get(i);
					ContentValues values = new ContentValues();
					values.put(DBData.USER_ID, userInfo.getUserId());
					values.put(DBData.USER_NAME, userInfo.getUserName());
					db.insert(DBData.USER_INFO_TABLE, null, values);
				}
			} catch (SQLiteException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		}
	}
	
	public ArrayList<UserInfo> getUserInfos(){
		ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
		synchronized (_writeLock) {

			SQLiteDatabase db = null;
			Cursor cursor = null;
			try {
				db = helper.getReadableDatabase();
				cursor = db.query(DBData.USER_INFO_TABLE, new String[] {
						DBData.USER_ID, DBData.USER_NAME }, null,null, null, null, null);
				while (cursor.moveToNext()) {
					userInfos.add(new UserInfo(cursor.getString(0), cursor.getString(1)));
				}
			} catch (SQLiteException e) {
				e.printStackTrace();
			} finally {
				if (cursor != null) {
					cursor.close();
					cursor = null;
				}
				if (db != null) {
					db.close();
				}
			}
		}
		return userInfos;
	}

}
