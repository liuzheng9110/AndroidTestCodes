package com.example.androidtest.viewpager;

import com.example.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VerticalViewPagerAdapter extends FragmentPagerAdapter {

	public VerticalViewPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
		// TODO Auto-generated constructor stub
		super(fragmentManager);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		
		Log.i("liuz", "position....." + position);
		
		return PlaceholderFragment.newInstance(position);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			int index = getArguments().getInt(ARG_SECTION_NUMBER);
			
			View rootView = inflater.inflate(R.layout.vertical_viewpager_fragment_layout, container, false);
			TextView textView = (TextView) rootView.findViewById(R.id.textView1);
			textView.setText(Integer.toString(index));
			
			return rootView;
		}
	}
}
