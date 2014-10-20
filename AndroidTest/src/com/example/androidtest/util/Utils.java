package com.example.androidtest.util;

import android.content.Context;
import android.widget.Toast;

public class Utils {
	private static Toast mToast;
	
	/**
	 * 
	 * @param mContext
	 * @param text
	 */
	public static void showShortToast(Context mContext, String text){
		if (mToast==null) {
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		}else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}
}
