package com.example.androidtest.sqlite.ormlite;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * 
 *  Class Name: OrmTest.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015��4��2�� ����8:54:09
 *  @Copyright http://liuz.me 
 *  @url 
 *  	http://www.cnblogs.com/vanezkw/archive/2012/07/31/2617210.html
 *  	http://www.cnblogs.com/vanezkw/archive/2012/08/02/2619798.html
 *  	http://www.cnblogs.com/vanezkw/archive/2012/08/03/2621770.html
 *  	http://www.cnblogs.com/vanezkw/archive/2012/08/15/2640290.html
 *  
 *  ע�����㣺��
 *  1�������� <application /> �ڵ������  <uses-library android:name="android.test.runner"/>
 *  2�������� <manifest /> �ڵ������  <instrumentation android:name="android.test.InstrumentationTestRunner" android:label="your tests label" android:targetPackage="your package name" />
 *  �ص�ע�⣺��instrumentation ���� manifest �ڵ��� ������ application �ڵ���
 *  
 */
public class OrmTest extends AndroidTestCase {
	
	private List<UserData> userDatas;

	public void testAdd(){
		try {
			UserData data = null;
			DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
			Dao<UserData,Integer> userDao = helper.getUserDao();
			data = new UserData("name1", "sex1", "age1");
			userDao.create(data);
			data = new UserData("name2", "sex2", "age2");
			userDao.create(data);
			data = new UserData("name3", "sex3", "age3");
			userDao.create(data);
			data = new UserData("name4", "sex4", "age4");
			userDao.create(data);
			data = new UserData("name5", "sex5", "age5");
			userDao.create(data);
			data = new UserData("name6", "sex6", "age6");
			userDao.create(data);
			
//			testList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testList() {
		DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
		try {
			userDatas = helper.getUserDao().queryForAll();
			Log.i("liuz", userDatas.size()  + "....list...." + userDatas.toString());
		} catch (Exception e) {
		}
	}

	public void testDel() {
		DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
		try {
			// helper.getUserDao().deleteById(19); // ��ɾ��
			
			DeleteBuilder<UserData, Integer> builder = helper.getUserDao().deleteBuilder();
			builder.where().eq("id", 19);// ���where ɾ��ĳһ�� ����ȫɾ
	 		int delId = builder.delete();
			
			Log.i("liuz", "delid...." + delId);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 *  @created 2015��4��2�� ����11:07:43
	 *  @url http://stackoverflow.com/questions/12497287/how-to-update-column-with-ormlite
	 */
	public void  testUpd() {
		DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
		try {
			// �޸�ָ��id������
			UpdateBuilder<UserData,Integer> updBuilder = helper.getUserDao().updateBuilder();
			updBuilder.where().eq("id", 38);
			updBuilder.updateColumnValue("user_sex", "female");
			updBuilder.updateColumnValue("user_age", "25");
			updBuilder.updateColumnValue("user_name", "liuz");
			updBuilder.update();
			
			testList();
			
		} catch (Exception e) {
		}
	}
}
