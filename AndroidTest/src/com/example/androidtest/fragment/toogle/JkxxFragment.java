//package com.example.androidtest.fragment.toogle;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.androidtest.R;
//import com.example.androidtest.sqlite.SimpleData;
//import com.example.androidtest.sqlite.greendao.bean_dao.UserInfo;
//
///**
// * 
// * Class Name: JkxxFragment.java Function:
// * 
// * @author liuzheng
// * @version 1.0
// * @created 2015年9月2日 下午2:44:56
// * @Copyright http://liuz.me
// * @url
// */
//public class JkxxFragment extends BaseFragment {
//	private int type;
//	private boolean isSbxxChange = false;
//	private boolean isJkxxChange = false;
//	
//	private Activity mActivity;
//	private TextView textView;
//	private ListView listView;
//	private MyFragmentAdapter<SimpleData> adapter;
//	private List<SimpleData> userInfos = new ArrayList<SimpleData>();
//	
//	private Handler handler = new Handler(){
//		public void handleMessage(Message msg) {
//			adapter.notifyDataSetChanged();
//		};
//	};
//	
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//	}
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		mActivity = getActivity();
//
//		Bundle bundle = getArguments();
//	 	type = bundle.getInt("type", -1);
//	 	isSbxxChange = bundle.getBoolean("isSbxxChange");
//	 	isJkxxChange = bundle.getBoolean("isJkxxChange");
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_toogle_jkxx, container, false);
//		
//		textView = (TextView) view.findViewById(R.id.tips_text);
//		listView = (ListView) view.findViewById(R.id.jkxx_listview);
//		adapter = new MyFragmentAdapter<SimpleData>(mActivity, userInfos);
//		listView.setAdapter(adapter);
//		
//		return view;
//	}
//	
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		userInfos.clear();
//		if (type == 0) { // sbxx
//			textView.setText("sbxx...fragment");
//			if (isSbxxChange) {
//				showShortToast("get sbxx data from server...");
//			}else {
//				showShortToast("get sbxx data from local ...");
//			}
//		}else if (type == 1) {
//			textView.setText("jkxx...fragment");
//			if (isJkxxChange) {
//				showShortToast("get jkxx data from server...");
//			}else {
//				showShortToast("get jkxx data from db...");
//			}
//		}
//	}
//	
//	@Override
//	public void onResume() {
//		super.onResume();
//	}
//	
//	@Override
//	public void onDetach() {
//		super.onDetach();
//	}
//	
//	
//	public static void getDataFromServer() {
//
//	}
//	
//	
//	
//}
