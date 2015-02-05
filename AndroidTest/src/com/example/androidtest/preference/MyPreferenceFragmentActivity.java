package com.example.androidtest.preference;

import com.example.androidtest.R;
import com.example.androidtest.util.Constants;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * 
 *  Class Name: MyPreferenceFragmentActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-31 上午10:45:44
 *  @Copyright http://liuz.me
 */
public class MyPreferenceFragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPrefFragment()).commit();
		
	}
	
	public static class MyPrefFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
		
		private String defValue = "0.0";
		private EditTextPreference eletricEtp, waterEtp;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.my_pref_fragment_setting);
			
			eletricEtp = (EditTextPreference) findPreference("fragment_"+Constants.ELECTRIC_PREFS_NAME);
			waterEtp = (EditTextPreference) findPreference("fragment_"+Constants.WATER_PREFS_NAME);
			
		}
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
			
			// 取出已经存在的值
			eletricEtp.setSummary(sharedPreferences.getString("fragment_"+Constants.ELECTRIC_PREFS_NAME, defValue));
			waterEtp.setSummary(sharedPreferences.getString("fragment_"+Constants.WATER_PREFS_NAME, defValue));
			
			sharedPreferences.registerOnSharedPreferenceChangeListener(this);
		}
		
		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			
			getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			//  设置修改后的值
			if (key.equals("fragment_" + Constants.ELECTRIC_PREFS_NAME)) {
				eletricEtp.setSummary(sharedPreferences.getString(key, defValue));
	        } else if(key.equals("fragment_" + Constants.WATER_PREFS_NAME)) {
	        	waterEtp.setSummary(sharedPreferences.getString(key, defValue));
	        }
		}
	}
}
