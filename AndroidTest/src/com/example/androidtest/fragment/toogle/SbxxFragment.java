//package com.example.androidtest.fragment.toogle;
//
//import com.example.androidtest.R;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
///**
// * 
// *  Class Name: SbxxFragment.java
// *  Function:
// *  
// *  @author liuzheng
// *  @version 1.0 
// *  @created 2015年9月2日 下午2:44:56
// *  @Copyright http://liuz.me 
// *  @url
// */
//public class SbxxFragment extends BaseFragment {
//	
//	private ListView listView;
//	private int type;
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		
//		if (savedInstanceState != null) {
//			 type = savedInstanceState.getInt("type", 0);
//		}
//	}
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_toogle_sbxx, container, false);
//		return view;
//	}
//}
