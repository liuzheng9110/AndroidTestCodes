package com.example.androidtest.textview;

import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.example.androidtest.main.MyApplication;

public class MyTextViewAct extends BaseActivity {
	
	private TextView textView;
	private String textString = "唐香湘: (1890)不爱了连分手也不敢说，冷暴力。姑娘要么以为自己是哪儿不好了加倍对男生好却换来一句你对我太好了。姑娘要么没安全感只能作于是最后男生顺势将分手的错也赖在姑娘头上。怂。爆。了。。真的，靠冷战消磨掉恋爱中所有的美好简直太让人失望。";
	
	private int textViewWid = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_textview_layout);
		
		textView = (TextView) findViewById(R.id.text_view);
		textView.setText(textString);
		
		SpannableString spannableString = new SpannableString(textString);
		
		Log.i("liuz", "line height..." + textView.getLineHeight());
		Log.i("liuz", "width..." + MyApplication.width);
		Log.i("liuz", "height..." + MyApplication.height);
		
		textViewWid = MyApplication.width - 100 - 10;
		
	}
}
