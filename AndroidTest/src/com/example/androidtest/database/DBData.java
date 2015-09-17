package com.example.androidtest.database;


/**
 * 
 *  Class Name: DBData.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年8月25日 上午10:13:39
 *  @Copyright http://liuz.me 
 *  @url
 */
public class DBData {

	public static final int DATABASE_VERSION = 3;
	
	public static final String DATABASE_NAME = "liuz.db";
	public static final String USER_INFO_TABLE = "userInfo"; // 保存用户信息的表

	/*---- start 用户信息----*/
	public static final String USER_ID = "userid"; // 用户ID
	public static final String USER_NAME = "username"; // 用户姓名
	/*----  end  用户信息----*/
	
}
