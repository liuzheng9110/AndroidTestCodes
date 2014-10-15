package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class FixTextViewAct extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String text = "FixTextViewActFixT阿萨德范德萨extViewActFi撒旦法xTextViewActFixText阿斯蒂芬ViewActFixTextViewA撒旦发射ctFixTextViewActFi阿萨德范德萨xTextViewActFixTextVi阿斯蒂芬的事ewActFixTextViewActFixTad发疯似extViewActFixTextViewActFixTextV阿斯蒂芬iewActFixTex爱的色放tViewActFix 阿斯蒂芬TextViewActFixTextViewAct";
		
//		TextView textView = new TextView(this);
		FixTextView textView = new FixTextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));

		
		text = ToDBC(text);
		
		textView.setText(text);
		setContentView(textView);

	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}
}
