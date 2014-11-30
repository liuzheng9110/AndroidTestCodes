package com.example.androidtest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;

import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.util.Utils;

/**
 * 
 * @author liuzheng
 * @date 2014-11-11 下午4:06:00
 * 
 */
public class ImageTextAct extends BaseActivity {
	private TextView textView;
	private String text = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_text_act);
		
		textView = (TextView)findViewById(R.id.image_text);
		
		text = getString(R.string.text);
//		text = "阿拉丁快放假啊但是萨拉肯德基发生#image_001.jpg#kjahdfjka 科技大厦放假啊号富华大厦快接啊示范点";
		
		SpannableString spannableString = new SpannableString(text);
		
		Pattern pattern = Pattern.compile("#.+#"); // @[^\\s:：]+[：:\\s]|\\[[^0-9]{1,4}\\]|#.+#
		Matcher matcher = pattern.matcher(spannableString);
		
		while (matcher.find()) {
			String match=matcher.group();
			if(match.startsWith("#")){ //#...#，加亮字体
				spannableString.setSpan(new ImageSpan(this, Utils.getSmallBitmap(this, "image/img1.jpg")), matcher.start(), matcher.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
			}
		}
		
		textView.setText(spannableString);
	}
}
