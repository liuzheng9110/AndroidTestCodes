package com.example.androidtest.sqlite.ormlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * 
 *  Class Name: DatabaseHelper.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年4月1日 下午3:06:23
 *  @Copyright http://liuz.me 
 *  @url
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TABLE_NAME = "user_data.db";
	private static final int TABLE_VERSION = 2;
	
	private Dao<UserData, Integer> userDao;
	
	public DatabaseHelper(Context context) {
		super(context, TABLE_NAME, null, TABLE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, UserData.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, UserData.class, true);
			onCreate(sqLiteDatabase, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static DatabaseHelper instance;
	public static synchronized DatabaseHelper getInstance(Context context){
		if (instance == null) {
			synchronized (DatabaseHelper.class) {
				if (instance == null) {
					instance = new DatabaseHelper(context);
				}
			}
		}
		return instance;
	}
	
	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 *  @created 2015年4月1日 下午4:06:14 
	 *  @return
	 *  @throws SQLException
	 */
	public Dao<UserData, Integer> getUserDao() throws SQLException{
		if (userDao == null) {
			userDao = getDao(UserData.class);
		}
		return userDao;
	}
	
	@Override
	public void close() {
		super.close();
		userDao = null;
	}
	
}
