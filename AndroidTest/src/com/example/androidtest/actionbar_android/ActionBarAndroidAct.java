package com.example.androidtest.actionbar_android;

import java.lang.reflect.Field;

import com.example.androidtest.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.ViewConfiguration;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;


/**
 * 
 * @author liuzheng
 * @date 2014-10-10 下午4:04:35
 * http://blog.csdn.net/guolin_blog/article/details/18234477
 */
public class ActionBarAndroidAct extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.actionbar_android_lay);
		// 
		ActionBar actionBar = getActionBar();
		actionBar.show();
		actionBar.setDisplayHomeAsUpEnabled(true);// 设置actionbar 返回箭头

		getOverflowMenu();
	}
	
	private void getOverflowMenu() {
        try {
           ViewConfiguration config = ViewConfiguration.get(this);
           Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
           if(menuKeyField != null) {
               menuKeyField.setAccessible(true);
               menuKeyField.setBoolean(config, true);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	
	private Intent getDefaultIntent() {  
	    Intent intent = new Intent(Intent.ACTION_SEND);  
	    intent.setType("image/*");  
	    return intent;  
	}  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.actionbar_android_menu_01, menu);
		
		MenuItem shareItem = menu.findItem(R.id.action_share);  
	    ShareActionProvider shareProvider = (ShareActionProvider) shareItem.getActionProvider();  
	    shareProvider.setShareIntent(getDefaultIntent());  
	    
	    
	    
	    
		return super.onCreateOptionsMenu(menu);
	};
	
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// TODO Auto-generated method stub
//		MenuInflater menuInflater = getMenuInflater();
//		menuInflater.inflate(R.menu.actionbar_android_menu, menu);
//		
//		// 获取menuitem view 给menuitem 添加事件
//		MenuItem searchItem = menu.findItem(R.id.action_search);
//		SearchView searchView = (SearchView) searchItem.getActionView();
//		// 设置itemview 的属性  .......
//		searchItem.setOnActionExpandListener(new OnActionExpandListener() {
//			
//			@Override
//			public boolean onMenuItemActionExpand(MenuItem item) {
//				// TODO Auto-generated method stub
//				showToast("onMenuItemActionExpand");
//				return true;
//			}
//			
//			@Override
//			public boolean onMenuItemActionCollapse(MenuItem item) {
//				// TODO Auto-generated method stub
//				showToast("onMenuItemActionCollapse");
//				return true;
//			}
//		});
//		return super.onCreateOptionsMenu(menu);
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:// actionbar 顶部返回
			/**
			 * 在manifest 中设置 android:parentActivityName 消除 顶部返回和手机自带物理机冲突
			 */
			// finish();// 
			
			Intent upIntent = NavUtils.getParentActivityIntent(this);  
	        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {  
	            TaskStackBuilder.create(this)  
	                    .addNextIntentWithParentStack(upIntent)  
	                    .startActivities();  
	        } else {  
	            upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            NavUtils.navigateUpTo(this, upIntent);  
	        } 
			
			break;
		case R.id.action_compose:
			showToast("action_compose");
			break;
		case R.id.action_delete:
			showToast("action_delete");
			break;
		case R.id.action_setting:
			showToast("action_setting");
			break;
		case R.id.action_settings:
			showToast("action_settings");
			break;
//		case R.id.action_search:
//			showToast("action_search");
//			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
