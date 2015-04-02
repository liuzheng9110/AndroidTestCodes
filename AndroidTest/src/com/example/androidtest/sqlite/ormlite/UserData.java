package com.example.androidtest.sqlite.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 *  Class Name: SimpleData.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年4月1日 下午3:00:52
 *  @Copyright http://liuz.me 
 *  @url
 */
@DatabaseTable(tableName = "tb_user")
public class UserData {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(columnName = "user_name")
	private String mUserName;
	@DatabaseField(columnName = "user_sex")
	private String mUserSex;
	@DatabaseField(columnName = "user_age")
	private String mUserAge;

	public UserData() {}
	
	public UserData(String userName, String userSex, String userAge){
		this.mUserName = userName;
		this.mUserSex = userSex;
		this.mUserAge = userAge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmUserSex() {
		return mUserSex;
	}

	public void setmUserSex(String mUserSex) {
		this.mUserSex = mUserSex;
	}

	public String getmUserAge() {
		return mUserAge;
	}

	public void setmUserAge(String mUserAge) {
		this.mUserAge = mUserAge;
	}
	
	@Override
	public String toString() {
		return mUserName + "/" + mUserSex + "/" + mUserAge;
	}
}
