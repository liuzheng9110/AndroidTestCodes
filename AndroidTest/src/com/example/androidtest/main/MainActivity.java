package com.example.androidtest.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.example.androidtest.CusDatePickerAct;
import com.example.androidtest.CusDialogAct;
import com.example.androidtest.CusEditTextAct;
import com.example.androidtest.DrawerLayoutAct;
import com.example.androidtest.DynamicAddView;
import com.example.androidtest.FixTextViewAct;
import com.example.androidtest.HtmlTextViewAct;
import com.example.androidtest.InputFaceAct;
import com.example.androidtest.JsonParseAct;
import com.example.androidtest.LongClickSelCopyAct;
import com.example.androidtest.MoniPop;
import com.example.androidtest.MultAnimateAct;
import com.example.androidtest.MultMarqueeTextViewAct;
import com.example.androidtest.MyDatePickerDialogAct;
import com.example.androidtest.MyShapeAct;
import com.example.androidtest.MySpinnerAct;
import com.example.androidtest.NineGridViewAct;
import com.example.androidtest.PhoneInfoAct;
import com.example.androidtest.R;
import com.example.androidtest.ShowDialogAct;
import com.example.androidtest.SingleChoiceAct;
import com.example.androidtest.TimerTaskAct;
import com.example.androidtest.XZQLDetail;
import com.example.androidtest.actionbar_android.ActionBarAndroidAct;
import com.example.androidtest.actionbar_support.ActionBarSupportAct;
import com.example.androidtest.alertwindow.FirstActivity;
import com.example.androidtest.editlist.EditListAct;
import com.example.androidtest.fragment.FragmentDemoAct;
import com.example.androidtest.http.HttpMainAct;
import com.example.androidtest.service.MyServiceAct;
import com.example.androidtest.sliding.SlidingAct;
import com.example.androidtest.viewpager.VerticalViewpagerAct;
import com.example.androidtest.voice.LongClickVoiceAct;
import com.example.androidtest.volley.AndroidVolleyAct;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((Button)findViewById(R.id.btn34)).performClick();
		
	}

	public void click_listener(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn01:
			startActivity(new Intent(MainActivity.this, MoniPop.class));
			break;
		case R.id.btn02:
			startActivity(new Intent(MainActivity.this, XZQLDetail.class));
			break;
		case R.id.btn03:
			startActivity(new Intent(MainActivity.this, DynamicAddView.class));
			break;
		case R.id.btn04:
			startActivity(new Intent(MainActivity.this,
					MyDatePickerDialogAct.class));
			break;
		case R.id.btn05:
			startActivity(new Intent(MainActivity.this, MySpinnerAct.class));
			break;
		case R.id.btn06:
			startActivity(new Intent(MainActivity.this,
					LongClickSelCopyAct.class));
			break;
		case R.id.btn07:
			startActivity(new Intent(MainActivity.this, LongClickVoiceAct.class));
			break;
		case R.id.btn08:
			startActivity(new Intent(MainActivity.this, InputFaceAct.class));
			break;
		case R.id.btn09:
			startActivity(new Intent(MainActivity.this, TimerTaskAct.class));
			break;
		case R.id.btn10:
			startActivity(new Intent(MainActivity.this, EditListAct.class));
			break;
		case R.id.btn11:
			startActivity(new Intent(MainActivity.this, HorizontalScrollView.class));
			break;
		case R.id.btn12:
			startActivity(new Intent(MainActivity.this, CusDialogAct.class));
			break;
		case R.id.btn13:
			startActivity(new Intent(MainActivity.this, SingleChoiceAct.class));
			break;
		case R.id.btn14:
			startActivity(new Intent(MainActivity.this, CusDatePickerAct.class));
			break;
		case R.id.btn15:
			startActivity(new Intent(MainActivity.this, HtmlTextViewAct.class));
			break;
		case R.id.btn16:
			startActivity(new Intent(MainActivity.this, JsonParseAct.class));
			break;
		case R.id.btn17:
			startActivity(new Intent(MainActivity.this, FirstActivity.class));
			break;
		case R.id.btn18:
			startActivity(new Intent(MainActivity.this, MyServiceAct.class));
			break;
		case R.id.btn19:
			startActivity(new Intent(MainActivity.this, MyShapeAct.class));
			break;
		case R.id.btn20:
			startActivity(new Intent(MainActivity.this, FixTextViewAct.class));
			break;
		case R.id.btn21:
			startActivity(new Intent(MainActivity.this, HttpMainAct.class));
			break;
		case R.id.btn22:
			startActivity(new Intent(MainActivity.this, ShowDialogAct.class));
			break;
		case R.id.btn23:
			startActivity(new Intent(MainActivity.this, MultMarqueeTextViewAct.class));
			break;
		case R.id.btn24:
			startActivity(new Intent(MainActivity.this, MultAnimateAct.class));
			break;
		case R.id.btn25:
			startActivity(new Intent(MainActivity.this, PhoneInfoAct.class));
			break;
		case R.id.btn26:
			startActivity(new Intent(MainActivity.this, ActionBarSupportAct.class));
			break;
		case R.id.btn34:
			startActivity(new Intent(MainActivity.this, ActionBarAndroidAct.class));
			break;
		case R.id.btn27:
			startActivity(new Intent(MainActivity.this, AndroidVolleyAct.class));
			break;
		case R.id.btn28:
			startActivity(new Intent(MainActivity.this, DrawerLayoutAct.class));
			break;
		case R.id.btn29:
			startActivity(new Intent(MainActivity.this, FragmentDemoAct.class));
			break;
		case R.id.btn30:
			startActivity(new Intent(MainActivity.this, SlidingAct.class));
			break;
		case R.id.btn31:
			startActivity(new Intent(MainActivity.this, CusEditTextAct.class));
			break;
		case R.id.btn32:
			startActivity(new Intent(MainActivity.this, VerticalViewpagerAct.class));
			break;
		case R.id.btn33:
			startActivity(new Intent(MainActivity.this, NineGridViewAct.class));
			break;
		default:
			break;
		}
	}
}
