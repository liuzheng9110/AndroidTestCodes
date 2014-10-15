package com.example.androidtest.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class FragmentList extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, 
				new String[] { "One", "Two", "Three", "Four", 
						"Five", "Six", "Seven",
						"Eight", "Nine", "Ten" }));
	}
	
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		Toast.makeText(getActivity(), (String)l.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
		
		((FragmentDemoAct) getActivity()).showDetail(position);
		
		super.onListItemClick(l, v, position, id);
	}
}
