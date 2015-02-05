package com.example.androidtest;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

public class InputFaceAct extends Activity {
	
	private Button faceInputBtn, faceInputSendBtn;
	private EditText faceInputEt;
	private boolean isFace = false;// 是否显示表情  默认不显示
	private GridView faceGridView;
	private InputFaceAdapter faceAdapter;
	private int[] faceIds;
	
	private ListView listView;
	private InputFaceListAdapter adapter;
//	private ArrayAdapter<String> arrayAdapter;
	
	private InputMethodManager imm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_face_act);

		//软键盘管理类
		imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		
		ArrayList<String> dataList = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			dataList.add("aaa" + i);
		}
		
		adapter = new InputFaceListAdapter(this, dataList);
//		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
		
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
//		listView.setAdapter(arrayAdapter);
		
		faceInputEt = (EditText) findViewById(R.id.face_input_et);
		
		faceInputEt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hideFace();
			}
		});
		
		faceInputEt.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				hideFace();
			}
		});
		
		faceInputBtn = (Button) findViewById(R.id.face_input_btn);
		faceInputBtn.setOnClickListener(faceClickListener);
		
		String[] faceIdStr = getResources().getStringArray(R.array.icon_face);
		faceIds = new int[faceIdStr.length];
		try {
			for (int i = 0; i < faceIdStr.length; i++) {
				int resId = getResources().getIdentifier(faceIdStr[i], "drawable", getPackageName());
				
//				Field field = R.drawable.class.getDeclaredField(faceIdStr[i]);
//				int resId = Integer.parseInt(field.get(null).toString());		
//	            if (resId != 0) {
//	                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
//	            }
	            
				faceIds[i] = resId;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		faceGridView = (GridView) findViewById(R.id.face_gridview);
		faceAdapter = new InputFaceAdapter(this, faceIds, faceIdStr);
		faceGridView.setAdapter(faceAdapter);
		
		faceGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//插入的表情
				SpannableString ss = new SpannableString(view.getTag().toString());
				Drawable d = getResources().getDrawable(faceAdapter.getItem(position));
				d.setBounds(0, 0, 35, 35);//设置表情图片的显示大小
				ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
				ss.setSpan(span, 0, view.getTag().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);				 
				//在光标所在处插入表情
				faceInputEt.getText().insert(faceInputEt.getSelectionStart(), ss);				
			}
		});
		
		faceInputSendBtn = (Button) findViewById(R.id.face_input_send_btn);
		faceInputSendBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String item = faceInputEt.getText().toString();
				adapter.addItem(item);
				
				faceInputEt.setText("");
				
				faceGridView.setVisibility(View.GONE);
				imm.hideSoftInputFromWindow(faceInputEt.getWindowToken(), 0);
			}
		});
	}
	
    private void showIMM() {
    	faceInputBtn.setTag(1);
    	showOrHideIMM();
    }
    private void showFace() {
		faceInputBtn.setBackgroundResource(R.drawable.icon_input);

		//faceInputBtn.setImageResource(R.drawable.widget_bar_keyboard);
		faceInputBtn.setTag(1);
		faceGridView.setVisibility(View.VISIBLE);
    }
    private void hideFace() {
		faceInputBtn.setBackgroundResource(R.drawable.icon_face);
    	
    	//faceInputBtn.setImageResource(R.drawable.widget_bar_face);
		faceInputBtn.setTag(null);
		faceGridView.setVisibility(View.GONE);
    }
    private void hideInput(){
    	imm.hideSoftInputFromWindow(faceInputEt.getWindowToken(), 0);
    }
    private void showOrHideIMM() {
    	if(faceInputBtn.getTag() == null){
			//隐藏软键盘
    		hideInput();
			//显示表情
			showFace();				
		}else{
			//显示软键盘
			imm.showSoftInput(faceInputEt, 0);
			//隐藏表情
			hideFace();
		}
    }
    private View.OnClickListener faceClickListener = new View.OnClickListener() {
		public void onClick(View v) {	
			showOrHideIMM();
		}
	};
	
	
}
