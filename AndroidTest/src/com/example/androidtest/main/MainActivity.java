package com.example.androidtest.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.androidtest.ActivityInLayoutAct;
import com.example.androidtest.AndroidJs2JavaAct;
import com.example.androidtest.AndroidNotificationAct;
import com.example.androidtest.CusAnimationAct;
import com.example.androidtest.CusDatePickerAct;
import com.example.androidtest.CusDialogAct;
import com.example.androidtest.CusEditTextAct;
import com.example.androidtest.DrawerLayoutAct;
import com.example.androidtest.DynamicAddView;
import com.example.androidtest.FixTextViewAct;
import com.example.androidtest.GestureDetectorAct;
import com.example.androidtest.HorizontalScrollViewAct;
import com.example.androidtest.HtmlTextViewAct;
import com.example.androidtest.ImageTextAct;
import com.example.androidtest.InputFaceAct;
import com.example.androidtest.JsonParseAct;
import com.example.androidtest.LastOnTouchTimeAct;
import com.example.androidtest.LongClickSelCopyAct;
import com.example.androidtest.MenuAct;
import com.example.androidtest.MoniPop;
import com.example.androidtest.MultAnimateAct;
import com.example.androidtest.MultMarqueeTextViewAct;
import com.example.androidtest.MultiAppInstallAct;
import com.example.androidtest.MyDatePickerDialogAct;
import com.example.androidtest.MyShapeAct;
import com.example.androidtest.MySpinnerAct;
import com.example.androidtest.NineGridViewAct;
import com.example.androidtest.PhoneInfoAct;
import com.example.androidtest.R;
import com.example.androidtest.RadioButtonActivity;
import com.example.androidtest.ShowDialogAct;
import com.example.androidtest.SimpleBroadcastReceiverAct;
import com.example.androidtest.SingleChoiceAct;
import com.example.androidtest.SpannableActivity;
import com.example.androidtest.TextScaleAct;
import com.example.androidtest.TimerTaskAct;
import com.example.androidtest.WebViewAct;
import com.example.androidtest.XZQLDetail;
import com.example.androidtest.a_foresee.gridview.TaxperGridActivity;
import com.example.androidtest.a_foresee.gridview.TaxperTableRowActivity;
import com.example.androidtest.actionbar_android.ActionBarAndroidAct;
import com.example.androidtest.actionbar_support.ActionBarSupportAct;
import com.example.androidtest.alertwindow.FirstActivity;
import com.example.androidtest.autofit.AutoFitTextViewAct;
import com.example.androidtest.editlist.EditListAct;
import com.example.androidtest.fragment.FragmentDemoAct;
import com.example.androidtest.fragment.tabhost.TabHostActivity;
import com.example.androidtest.htmlparse.HtmlParstAct;
import com.example.androidtest.http.HttpMainAct;
import com.example.androidtest.notification.NotificationActivity;
import com.example.androidtest.preference.MyPreferenceActivity;
import com.example.androidtest.preference.MyPreferenceFragmentActivity;
import com.example.androidtest.pullrefresh.PullRefreshActivity;
import com.example.androidtest.recycle_cardview.RecyclerCardViewActivity;
import com.example.androidtest.screenonoff.ScreenOnOffAct;
import com.example.androidtest.service.MyServiceAct;
import com.example.androidtest.sliding.SlidingAct;
import com.example.androidtest.sqlite.SqliteOperationActivity;
import com.example.androidtest.textview.MyTextViewAct;
import com.example.androidtest.toast.CusToastAct;
import com.example.androidtest.update.AndroidUpdateAct;
import com.example.androidtest.viewpager.VerticalViewpagerAct;
import com.example.androidtest.voice.LongClickVoiceAct;
import com.example.androidtest.volley.AndroidVolleyAct;


public class MainActivity extends BaseActivity {
	
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
			startActivity(new Intent(MainActivity.this, MyDatePickerDialogAct.class));
			break;
		case R.id.btn05:
			startActivity(new Intent(MainActivity.this, MySpinnerAct.class));
			break;
		case R.id.btn06:
			startActivity(new Intent(MainActivity.this, LongClickSelCopyAct.class));
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
			startActivity(new Intent(MainActivity.this, HorizontalScrollViewAct.class));
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
		case R.id.btn34:
			startActivity(new Intent(MainActivity.this, ActionBarAndroidAct.class));
			break;
		case R.id.btn35:
			startActivity(new Intent(MainActivity.this, AndroidUpdateAct.class));
			break;
		case R.id.btn36:
			startActivity(new Intent(MainActivity.this, AndroidNotificationAct.class));
			break;
		case R.id.btn37:
			startActivity(new Intent(MainActivity.this, CusToastAct.class));
			break;
		case R.id.btn38:
			startActivity(new Intent(MainActivity.this, WebViewAct.class));
			break;
		case R.id.btn39:
			startActivity(new Intent(MainActivity.this, MultiAppInstallAct.class));
			break;
		case R.id.btn40:
			startActivity(new Intent(MainActivity.this, LastOnTouchTimeAct.class));
			break;
		case R.id.btn41:
			startActivity(new Intent(MainActivity.this, ScreenOnOffAct.class));
			break;
		case R.id.btn42:
			startActivity(new Intent(MainActivity.this, SimpleBroadcastReceiverAct.class));
			break;
		case R.id.btn43:
			startActivity(new Intent(MainActivity.this, AndroidJs2JavaAct.class));
			break;
		case R.id.btn44:
			startActivity(new Intent(MainActivity.this, ImageTextAct.class));
			break;
		case R.id.btn45:
			startActivity(new Intent(MainActivity.this, TextScaleAct.class));
			break;
		case R.id.btn46:
			startActivity(new Intent(MainActivity.this, GestureDetectorAct.class));
			break;
		case R.id.btn47:
			startActivity(new Intent(MainActivity.this, AutoFitTextViewAct.class));
			break;
		case R.id.btn48:
			startActivity(new Intent(MainActivity.this, CusAnimationAct.class));
			break;
		case R.id.btn49:
			startActivity(new Intent(MainActivity.this, HtmlParstAct.class));
			break;
		case R.id.btn50:
			startActivity(new Intent(MainActivity.this, ActivityInLayoutAct.class));
			break;
		case R.id.btn51:
			startActivity(new Intent(MainActivity.this, MenuAct.class));
			break;
		case R.id.btn52:
			startActivity(new Intent(MainActivity.this, MyTextViewAct.class));
			break;
		case R.id.btn53:
			startActivity(new Intent(MainActivity.this, PullRefreshActivity.class));
			break;
		case R.id.btn54:
			startActivity(new Intent(MainActivity.this, MyPreferenceActivity.class));
			break;
		case R.id.btn55:
			startActivity(new Intent(MainActivity.this, MyPreferenceFragmentActivity.class));
			break;
		case R.id.btn56:
			startActivity(new Intent(MainActivity.this, TaxperGridActivity.class));
			break;
		case R.id.btn57:
			startActivity(new Intent(MainActivity.this, TaxperTableRowActivity.class));
			break;
		case R.id.btn58:
			startActivity(new Intent(MainActivity.this, RadioButtonActivity.class));
			break;
		case R.id.btn59:
			startActivity(new Intent(MainActivity.this, SpannableActivity.class));
			break;
		case R.id.btn60:
			startActivity(new Intent(MainActivity.this, NotificationActivity.class));
			break;
		case R.id.btn61:
			startActivity(new Intent(MainActivity.this, SqliteOperationActivity.class));
			break;
		case R.id.btn62:
			startActivity(new Intent(MainActivity.this, RecyclerCardViewActivity.class));
			break;
		case R.id.btn63:
			startActivity(new Intent(MainActivity.this, TabHostActivity.class));
			break;
		default:
			break;
		}
	}
	
	private ScrollView scrollView;
	private TextView tipsTopTextView, tipsBottomTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scrollView = (ScrollView) findViewById(R.id.main_layout);
		tipsTopTextView = (TextView) findViewById(R.id.tips_top_text);
		tipsBottomTextView = (TextView) findViewById(R.id.tips_bottom_text);
		
		tipsTopTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollView.fullScroll(ScrollView.FOCUS_UP);
			}
		});
		
		tipsBottomTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});
	}
}
