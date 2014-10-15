package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DrawerLayoutAct extends Activity implements OnClickListener {
	
	private Button topBtn;
	private DrawerLayout drawerLayout;
	private Button button_left,button_top,button_right,button_bottom;
	private ListView listView, contentListView;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_lay_01);
		drawerLayout.setDrawerTitle(Gravity.TOP, "Title");
		drawerLayout.setScrimColor(0x00000000);
		
		button_left = (Button) findViewById(R.id.button_left);
		button_left.setOnClickListener(this);

		contentListView = (ListView) findViewById(R.id.content_list);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, 
				new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" });
		contentListView.setAdapter(adapter);
		contentListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		listView = (ListView) findViewById(R.id.left_drawer);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, 
				new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String day = (String) parent.getItemAtPosition(position);
				button_left.setText(day);
				drawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
	}

	protected void contentListViewCheck(int position) {
		// TODO Auto-generated method stub
		adapter.getView(position, null, null).setSelected(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_left:
			openDrawer(Gravity.LEFT);
			break;
		default:
			break;
		}
	}

	private void openDrawer(int gravity) {
		drawerLayout.openDrawer(gravity);
	}
}
