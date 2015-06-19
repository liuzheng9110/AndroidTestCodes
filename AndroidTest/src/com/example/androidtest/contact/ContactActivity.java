package com.example.androidtest.contact;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.RawContacts.Data;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * 
 *  Class Name: ContactActivity.java
 *  Function:
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年5月20日 上午10:58:26
 *  @Copyright http://liuz.me 
 *  @url
 */
public class ContactActivity extends BaseActivity implements OnItemClickListener, OnItemLongClickListener {
	
	private ListView contactListView;
	private QuickAdapter<ContactInfo> contactAdapter;
	private ArrayList<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
	
	private static final String[] PHONE_PROJECTION = {Phone.CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_layout);
		
		contactListView = (ListView) findViewById(R.id.contact_listview);
		contactAdapter = new QuickAdapter<ContactInfo>(this, android.R.layout.simple_list_item_2) {
			@Override
			protected void convert(BaseAdapterHelper helper, ContactInfo contactInfo) {
				helper.setText(android.R.id.text1, contactInfo.getName());
				helper.setText(android.R.id.text2, contactInfo.getNumber());
			}
		};
		contactListView.setAdapter(contactAdapter);

		/// 
		contactListView.setOnItemClickListener(this);
		contactListView.setOnItemLongClickListener(this);
		
		getContactData();
	}

	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 *  @created 2015年5月20日 上午11:19:50
	 */
	private void getContactData() {
		// 
		contactInfos.clear();
		// 
		ContentResolver cResolver = this.getContentResolver();
		// 获取联系人数据
		Cursor cursor = cResolver.query(Phone.CONTENT_URI, PHONE_PROJECTION, null, null, null);
		// 
		if (cursor != null) {
			while (cursor.moveToNext()) {
				// 
				String number = cursor.getString(2);
				if (number.isEmpty()) {
					continue;
				}
				String name = cursor.getString(1);
				long contactId = cursor.getLong(0);
				contactInfos.add(new ContactInfo(contactId, name, number));
			}
		}
		cursor.close();
		
		contactAdapter.addAll(contactInfos);
		
//		Log.i("liuz", "..." + contactInfos.size());
	}
	
	/**
	 * 
	 *  Function: 获取通讯录
	 *  @author liuzheng
	 *  @created 2015年5月21日 上午9:45:31
	 */
	private void getContactDataByRawContact(){
		ContentResolver resolver = getContentResolver();
		Cursor rawCursor = resolver.query(RawContacts.CONTENT_URI, new String[]{RawContacts._ID}, null, null, null);

		ContactInfo contactInfo;
		while (rawCursor.moveToNext()) {
			contactInfo = new ContactInfo();
		
			long contactId = rawCursor.getLong(rawCursor.getColumnIndex(RawContacts._ID));
			contactInfo.setContactId(contactId);
			
			Cursor dataCursor = resolver.query(android.provider.ContactsContract.Data.CONTENT_URI, null, Data.RAW_CONTACT_ID + "=?", new String[]{String.valueOf(contactId)}, null);
			while (dataCursor.moveToNext()) {
				String data1 = dataCursor.getString(dataCursor.getColumnIndex(Data.DATA1));
				String mimetype = dataCursor.getString(dataCursor.getColumnIndex(Data.MIMETYPE));

				if (mimetype.equals(StructuredName.CONTENT_ITEM_TYPE)) {
					contactInfo.setName(data1);
				} else if (mimetype.equals(Phone.CONTENT_ITEM_TYPE)) {
					contactInfo.setNumber(data1);
				}
			}
//			contactInfos.add(contactInfo);
			dataCursor.close();
		}
		rawCursor.close();
	}
	
	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 * @param contactInfo 
	 *  @created 2015年5月21日 上午11:24:57
	 */
	private void updContact(ContactInfo contactInfo){
		
	}
	
	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 *  @created 2015年5月21日 上午11:24:41
	 */
	private void delContact(ContactInfo contactInfo){
		ContentResolver resolver = getContentResolver();
		resolver.delete(RawContacts.CONTENT_URI, RawContacts._ID + "=?", new String[]{String.valueOf(contactInfo.getContactId())});
	}
	
	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 *  @created 2015年5月21日 上午11:31:49 
	 *  @param contactInfo
	 */
	private void addContact(ContactInfo contactInfo){
		ContentResolver resolver = getContentResolver();

		ContentValues contentValues = new ContentValues();
		// 获取 contactId
		Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, contentValues);
		long rawContactId = ContentUris.parseId(rawContactUri);
		
		// 电话号码
		ContentValues values1 = new ContentValues();
		values1.put(Data.RAW_CONTACT_ID, rawContactId);
		values1.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		values1.put(Phone.NUMBER, contactInfo.getNumber());
		resolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values1);
		// 名字
		ContentValues values2 = new ContentValues();
		values2.put(Data.RAW_CONTACT_ID, rawContactId);
		values2.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		values2.put(StructuredName.DISPLAY_NAME, contactInfo.getName());
		resolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values2);
	}

	@Override
	public void click_listener(View v) {
		switch (v.getId()) {
		case R.id.add_btn:
			showAddDialog();// 添加联系人对话框
			break;
		}
	}
	
	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 *  @created 2015年5月21日 下午2:39:31
	 */
	private void showAddDialog() {
		View view = View.inflate(this, R.layout.add_contact_layout, null);
		
		final EditText nameEt = (EditText) view.findViewById(R.id.editText1);
		final EditText numberEt = (EditText) view.findViewById(R.id.editText2);
		
		new AlertDialog.Builder(this)
			.setTitle("添加联系人")
			.setView(view)
			.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ContactInfo contactInfo = new ContactInfo();
					
					String nameStr = nameEt.getText().toString();
					String numberStr = numberEt.getText().toString();
					
					if (TextUtils.isEmpty(nameStr)) {
						showShortToast("请输入姓名!");
					}else if (TextUtils.isEmpty(numberStr)) {
						showShortToast("请输入电话!");
					}else {
						contactInfo.setName(nameStr);
						contactInfo.setNumber(numberStr);
						
						addContact(contactInfo);
						getContactData();

						contactAdapter.add(contactInfo);
//						contactAdapter.notifyDataSetChanged();
					}
				}
			})
			.setNegativeButton("取消", null)
			.show();
	}

	/**
	 * 
	 *  Function:
	 *  @author liuzheng
	 * @param contactInfo 
	 *  @created 2015年5月21日 下午3:00:52
	 */
	private void showOperDialog(ContactInfo contactInfo) {
		View view = View.inflate(this, R.layout.add_contact_layout, null);

		final EditText nameEt = (EditText) view.findViewById(R.id.editText1);
		final EditText numberEt = (EditText) view.findViewById(R.id.editText2);

		nameEt.setText(contactInfo.getName());
		numberEt.setText(contactInfo.getNumber());
		
		new AlertDialog.Builder(this)
		.setTitle("修改联系人")
		.setView(view)
		.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ContactInfo contactInfo = new ContactInfo();
				
				String nameStr = nameEt.getText().toString();
				String numberStr = numberEt.getText().toString();
				
				if (TextUtils.isEmpty(nameStr)) {
					showShortToast("请输入姓名!");
				}else if (TextUtils.isEmpty(numberStr)) {
					showShortToast("请输入电话!");
				}else {
					
				}
			}
		})
		.setNegativeButton("取消", null)
		.show();
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ContactInfo contactInfo = contactAdapter.getItem(position);
		showOperDialog(contactInfo);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		ContactInfo contactInfo = contactAdapter.getItem(position);
		delContact(contactInfo);
		return true;
	}
}
