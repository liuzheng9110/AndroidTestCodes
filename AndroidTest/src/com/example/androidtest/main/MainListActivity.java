package com.example.androidtest.main;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import com.example.androidtest.annotation.ButterKnifeActivity;
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
import com.example.androidtest.webservice.MainWebserviceActivity;

/**
 * 
 *  Class Name: MainListActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年2月11日 上午8:27:42
 *  @Copyright http://liuz.me
 */
public class MainListActivity extends BaseActivity {
	
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private static ArrayList<String> data = new ArrayList<String>();
	@SuppressWarnings("rawtypes")
	private static ArrayList<Class> activities = new ArrayList<Class>();
	
	static{
		//
		data.add("MoniPop");
		data.add("XZQLDetail");
		data.add("DynamicAddView");
		data.add("MyDatePickerDialogAct");
		data.add("MySpinnerAct");
		data.add("LongClickSelCopyAct");
		data.add("LongClickVoiceAct");
		data.add("InputFaceAct");
		data.add("TimerTaskAct");
		data.add("EditListAct");
		data.add("HorizontalScrollViewAct");
		data.add("CusDialogAct");
		data.add("SingleChoiceAct");
		data.add("CusDatePickerAct");
		data.add("HtmlTextViewAct");
		data.add("JsonParseAct");
		data.add("FirstActivity");
		data.add("MyServiceAct");
		data.add("MyShapeAct");
		data.add("FixTextViewAct");
		data.add("HttpMainAct");
		data.add("ShowDialogAct");
		data.add("MultMarqueeTextViewAct");
		data.add("MultAnimateAct");
		data.add("PhoneInfoAct");
		data.add("ActionBarSupportAct");
		data.add("AndroidVolleyAct");
		data.add("DrawerLayoutAct");
		data.add("FragmentDemoAct");
		data.add("SlidingAct");
		data.add("CusEditTextAct");
		data.add("VerticalViewpagerAct");
		data.add("NineGridViewAct");
		data.add("ActionBarAndroidAct");
		data.add("AndroidUpdateAct");
		data.add("AndroidNotificationAct");
		data.add("CusToastAct");
		data.add("WebViewAct");
		data.add("MultiAppInstallAct");
		data.add("LastOnTouchTimeAct");
		data.add("ScreenOnOffAct");
		data.add("SimpleBroadcastReceiverAct");
		data.add("AndroidJs2JavaAct");
		data.add("ImageTextAct");
		data.add("TextScaleAct");
		data.add("GestureDetectorAct");
		data.add("AutoFitTextViewAct");
		data.add("CusAnimationAct");
		data.add("HtmlParstAct");
		data.add("ActivityInLayoutAct");
		data.add("MenuAct");
		data.add("MyTextViewAct");
		data.add("PullRefreshActivity");
		data.add("MyPreferenceActivity");
		data.add("MyPreferenceFragmentActivity");
		data.add("TaxperGridActivity");
		data.add("TaxperTableRowActivity");
		data.add("RadioButtonActivity");
		data.add("SpannableActivity");
		data.add("NotificationActivity");
		data.add("SqliteOperationActivity");
		data.add("RecyclerCardViewActivity");
		data.add("TabHostActivity");
		data.add("ButterKnifeActivity");
		data.add("MainWebserviceActivity");
		
		//
		activities.add(MoniPop.class);
		activities.add(XZQLDetail.class);
		activities.add(DynamicAddView.class);
		activities.add(MyDatePickerDialogAct.class);
		activities.add(MySpinnerAct.class);
		activities.add(LongClickSelCopyAct.class);
		activities.add(LongClickVoiceAct.class);
		activities.add(InputFaceAct.class);
		activities.add(TimerTaskAct.class);
		activities.add(EditListAct.class);
		activities.add(HorizontalScrollViewAct.class);
		activities.add(CusDialogAct.class);
		activities.add(SingleChoiceAct.class);
		activities.add(CusDatePickerAct.class);
		activities.add(HtmlTextViewAct.class);
		activities.add(JsonParseAct.class);
		activities.add(FirstActivity.class);
		activities.add(MyServiceAct.class);
		activities.add(MyShapeAct.class);
		activities.add(FixTextViewAct.class);
		activities.add(HttpMainAct.class);
		activities.add(ShowDialogAct.class);
		activities.add(MultMarqueeTextViewAct.class);
		activities.add(MultAnimateAct.class);
		activities.add(PhoneInfoAct.class);
		activities.add(ActionBarSupportAct.class);
		activities.add(AndroidVolleyAct.class);
		activities.add(DrawerLayoutAct.class);
		activities.add(FragmentDemoAct.class);
		activities.add(SlidingAct.class);
		activities.add(CusEditTextAct.class);
		activities.add(VerticalViewpagerAct.class);
		activities.add(NineGridViewAct.class);
		activities.add(ActionBarAndroidAct.class);
		activities.add(AndroidUpdateAct.class);
		activities.add(AndroidNotificationAct.class);
		activities.add(CusToastAct.class);
		activities.add(WebViewAct.class);
		activities.add(MultiAppInstallAct.class);
		activities.add(LastOnTouchTimeAct.class);
		activities.add(ScreenOnOffAct.class);
		activities.add(SimpleBroadcastReceiverAct.class);
		activities.add(AndroidJs2JavaAct.class);
		activities.add(ImageTextAct.class);
		activities.add(TextScaleAct.class);
		activities.add(GestureDetectorAct.class);
		activities.add(AutoFitTextViewAct.class);
		activities.add(CusAnimationAct.class);
		activities.add(HtmlParstAct.class);
		activities.add(ActivityInLayoutAct.class);
		activities.add(MenuAct.class);
		activities.add(MyTextViewAct.class);
		activities.add(PullRefreshActivity.class);
		activities.add(MyPreferenceActivity.class);
		activities.add(MyPreferenceFragmentActivity.class);
		activities.add(TaxperGridActivity.class);
		activities.add(TaxperTableRowActivity.class);
		activities.add(RadioButtonActivity.class);
		activities.add(SpannableActivity.class);
		activities.add(NotificationActivity.class);
		activities.add(SqliteOperationActivity.class);
		activities.add(RecyclerCardViewActivity.class);
		activities.add(TabHostActivity.class);
		activities.add(ButterKnifeActivity.class);
		activities.add(MainWebserviceActivity.class);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_list_layout);
		
		listView = (ListView) findViewById(R.id.main_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startIntent(position);
			}
		});
	}

	private void startIntent(int position) {
		Intent intent = new Intent(MainListActivity.this, activities.get(position));
		startActivity(intent);
	}
}
