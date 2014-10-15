package com.example.androidtest.sliding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SlidingMenuListFragment extends ListFragment {
	private ArrayAdapter<String> arrayAdapter;
	private String[] data = new String[20];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		for (int i = 0; i < 20; i++) {
			data[i] = "Sample List " + (i+1);
		}

		arrayAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, data);
		setListAdapter(arrayAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "Sample List " + (position+1), Toast.LENGTH_SHORT).show();

		Fragment contentFragment = new SlidingMenuContentFragment("Sample List " + (position+1),  "Sample List " + (position+1), SlidingAct.TYPE_SLIDING_MENU);
		switchFragment(contentFragment);
		
		super.onListItemClick(l, v, position, id);
	}

	private void switchFragment(Fragment fragment) {
		if (getActivity()==null) {
			return;
		}
		
		if (getActivity() instanceof SlidingMenuAct) {
			SlidingMenuAct slidingMenuAct = (SlidingMenuAct) getActivity();
			
			slidingMenuAct.switchContent(fragment);
		}
	}
}
