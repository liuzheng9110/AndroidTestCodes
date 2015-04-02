package com.example.androidtest.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.sqlite.greendao.SimpleUseGreendaoSqliteActivity;
import com.example.androidtest.sqlite.ormlite.SimpleUseOrmliteSqliteActivity;

/**
 * 
 *  Class Name: SqliteMainActivity.java
 *  Function: andorid sqlite database operations 
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年3月4日 下午3:47:49
 *  @Copyright http://liuz.me
 */
public class SqliteOperationActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_main_layout);
	}
	
	@Override
	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.simple_default_sqlite:
			startActivity(new Intent(this, SimpleDefaultSqliteActivity.class));
			break;
		case R.id.simple_use_ormlite:
			startActivity(new Intent(this, SimpleUseOrmliteSqliteActivity.class));
			break;
		case R.id.simple_use_greendao:
			startActivity(new Intent(this, SimpleUseGreendaoSqliteActivity.class));
			break;
		default:
			break;
		}
	}
}
