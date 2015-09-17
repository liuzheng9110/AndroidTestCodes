package com.example.androidtest.fragment.toogle;

import com.example.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 
 *  Class Name: FragmentToogleActivity.java
 *  Function: fragment 切换
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年9月2日 下午2:38:20
 *  @Copyright http://liuz.me 
 *  @url http://segmentfault.com/a/1190000000650573
 */
public class FragmentToogleActivity extends FragmentActivity {
	
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	
//	private SbxxFragment sbxxFragment;
	private ToogleFragment sbxxFragment;
	private ToogleFragment jkxxFragment;
	
	private Button sbxxBtn, jkxxBtn;
	
	private static final String TAG_SBXX = "sbxx";
	private static final String TAG_JKXX = "jkxx";
	
	private int mType = 0;
	private boolean isSbxxChange = true;
	private boolean isJkxxChange = true;
	private Bundle bundle = new Bundle();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_toogle_layout);

		fragmentManager = getSupportFragmentManager();
		
		sbxxBtn = (Button)findViewById(R.id.sbxx_btn);
		jkxxBtn = (Button)findViewById(R.id.jkxx_btn);
		
		////////////////////////////////////////////////// 
		bundle.putInt("type", mType);
		bundle.putBoolean("isSbxxChange", isSbxxChange);
		bundle.putBoolean("isJkxxChange", isJkxxChange);
		
		sbxxFragment = new ToogleFragment();
		sbxxFragment.setArguments(bundle);

		fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
		fragmentTransaction.add(R.id.main_layout, sbxxFragment, TAG_SBXX);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
	
	
	public void click_listener(View view) {
		switch (view.getId()) {
		case R.id.search_btn:
			if (mType == 0) {
				isSbxxChange = true;
				sbxxBtn.performClick();
			}else if (mType == 1) {
				isJkxxChange = true;
				jkxxBtn.performClick();
			}
			break;
		case R.id.sbxx_btn:
			mType = 0;
			Log.i("liuz", "TAG_SBXX..." + fragmentManager.findFragmentByTag(TAG_SBXX));
			if (fragmentManager.findFragmentByTag(TAG_SBXX) == null) {
				bundle.putInt("type", mType);
				bundle.putBoolean("isSbxxChange", isSbxxChange);
				
				sbxxFragment = new ToogleFragment();
				sbxxFragment.setArguments(bundle);

				fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
				fragmentTransaction.replace(R.id.main_layout, sbxxFragment, TAG_SBXX);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
				isSbxxChange = false;
			}else{
				sbxxFragment.updateDatas(mType, isSbxxChange);
				
				isSbxxChange = false;
			}
			break;

		case R.id.jkxx_btn: 
			mType = 1;
			Log.i("liuz", "TAG_JKXX..." + fragmentManager.findFragmentByTag(TAG_JKXX));
			if (fragmentManager.findFragmentByTag(TAG_JKXX) == null) {
				bundle.putInt("type", mType);
				bundle.putBoolean("isJkxxChange", isJkxxChange);

				jkxxFragment = new ToogleFragment();
				jkxxFragment.setArguments(bundle);
				
				fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
				fragmentTransaction.replace(R.id.main_layout, jkxxFragment, TAG_JKXX);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
				isJkxxChange = false;
			}else {
				jkxxFragment.updateDatas(mType, isJkxxChange);
				
				isJkxxChange = false;
			}
			break;
		}
		
//		switch (view.getId()) {
//		case R.id.sbxx_btn:
//			if (fragmentManager.findFragmentByTag(TAG_SBXX) == null) {
////				sbxxFragment = new SbxxFragment();
//				sbxxFragment = new JkxxFragment();
//				
//				bundle.putInt("type", 0);
//				sbxxFragment.setArguments(bundle);
//				
//				fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
//				
//				if (sbxxFragment != null) {
//					fragmentTransaction.hide(jkxxFragment);
//				}
//				
//				fragmentTransaction.add(R.id.main_layout, sbxxFragment, TAG_SBXX).commit();
//			}else {
//				fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
//				if (!sbxxFragment.isAdded()) {
//					fragmentTransaction.add(R.id.main_layout, sbxxFragment, TAG_SBXX).commit();
//				}else {
//					fragmentTransaction.hide(jkxxFragment).show(sbxxFragment).commit();
//				}
//			}
//			
//			break;
//		case R.id.jkxx_btn:
//			if (fragmentManager.findFragmentByTag(TAG_JKXX) == null) {
//				jkxxFragment = new JkxxFragment();
//
//				bundle.putInt("type", 1);
//				jkxxFragment.setArguments(bundle);
//				
//				fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
//				
//				if (sbxxFragment != null) {
//					fragmentTransaction.hide(sbxxFragment);
//				}
//				
//				fragmentTransaction.add(R.id.main_layout, jkxxFragment, TAG_JKXX).commit();
//			}else{
//				fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, R.anim.fade_out);
//				if (!jkxxFragment.isAdded()) {
//					fragmentTransaction.add(R.id.main_layout, jkxxFragment, TAG_JKXX).commit();
//				}else {
//					fragmentTransaction.hide(sbxxFragment).show(jkxxFragment).commit();
//				}
//			}
//				
//			break;
//		}
	}
}
