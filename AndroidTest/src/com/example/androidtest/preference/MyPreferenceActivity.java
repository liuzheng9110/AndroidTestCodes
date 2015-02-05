package com.example.androidtest.preference;

import com.example.androidtest.R;
import com.example.androidtest.R.xml;
import com.example.androidtest.util.Constants;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * 
 *  Class Name: MyPreferenceActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-30 ÉÏÎç10:36:46
 *  @Copyright http://liuz.me
 */
public class MyPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	private String defValue = "0.0";
	
	private EditTextPreference eletricEtp, waterEtp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.my_pref_setting);
		
		eletricEtp = (EditTextPreference) findPreference(Constants.ELECTRIC_PREFS_NAME);
		waterEtp = (EditTextPreference) findPreference(Constants.WATER_PREFS_NAME);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        
		eletricEtp.setSummary(sharedPreferences.getString(Constants.ELECTRIC_PREFS_NAME, defValue));
		waterEtp.setSummary(sharedPreferences.getString(Constants.WATER_PREFS_NAME, defValue));
		
		// Register the listener whenever a key changes
		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    } 

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(Constants.ELECTRIC_PREFS_NAME)) {
			eletricEtp.setSummary(sharedPreferences.getString(key, defValue));
        } else if(key.equals(Constants.WATER_PREFS_NAME)) {
        	waterEtp.setSummary(sharedPreferences.getString(key, defValue));
        }
	}
}
