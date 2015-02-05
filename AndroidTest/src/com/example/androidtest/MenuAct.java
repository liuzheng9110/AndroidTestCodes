package com.example.androidtest;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.example.androidtest.main.BaseActivity;

public class MenuAct extends BaseActivity {

	private TextView textView;
	private Button button;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_act_layout);

		textView = (TextView) findViewById(R.id.text_view);
		
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				unregisterForContextMenu(listView);
				
				showPopupMenu();
			}
		});
		
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "aaa",
						"bbb", "ccc", "ddd", "eee", "fff", "ggg" }));
		
		registerForContextMenu(listView);
		
	}

	// //////////////////////////Creating an Options Menu///////////////////////////////////
	/**
	 * 创建菜单 注意不同版本显示
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_act_options_menu, menu);

		/**
		 * You must return true for the menu to be displayed; if you return
		 * false it will not be shown.
		 */
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			showShortToast("You Click Options Menu 01...");
			break;
		case R.id.item2:
			showShortToast("You Click Options Menu 02...");
			break;
		case R.id.item3:
			showShortToast("You Click Options Menu 03...");
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// //////////////////////////Creating Contextual Menu///////////////////////////////////

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v == listView) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu_act_contextual_menu, menu);
		}

		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		Log.i("liuz", "menuInfo..." + menuInfo.id+"/"+menuInfo.position+"/"+menuInfo.toString());
		switch (item.getItemId()) {
		case R.id.item1:
			showShortToast("You Click Contextual Menu 01...");
			break;
		case R.id.item2:
			showShortToast("You Click Contextual Menu 02...");
			break;
		case R.id.item3:
			showShortToast("You Click Contextual Menu 03...");
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	// //////////////////////////Creating Popup Menu///////////////////////////////////
	public void  showPopupMenu() {
		PopupMenu popupMenu = new PopupMenu(this, button);
		
	    MenuInflater inflater = popupMenu.getMenuInflater();
	    inflater.inflate(R.menu.menu_act_popup_menu, popupMenu.getMenu());
	    
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		popupMenu.show();
	}

}
