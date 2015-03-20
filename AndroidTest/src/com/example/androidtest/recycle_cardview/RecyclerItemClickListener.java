package com.example.androidtest.recycle_cardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 *  Class Name: RecyclerItemClickListener.java
 *  Function: recycler item 点击事件
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2015年3月10日 上午11:19:20
 *  @Copyright http://liuz.me
 *  @url http://stackoverflow.com/questions/24471109/recyclerview-onclick
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

	private OnRecyclerItemClickListener mListener;
	private GestureDetector mGestureDetector;

	public interface OnRecyclerItemClickListener {
		public void onItemClick(View view, int position);
	}

	public RecyclerItemClickListener(Context mContext, OnRecyclerItemClickListener listener) {
		mListener = listener;
		mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}
		});
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
		View childView = view.findChildViewUnder(e.getX(), e.getY());
		if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
			mListener.onItemClick(childView, view.getChildPosition(childView));
			return true;
		}
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView arg0, MotionEvent arg1) {
	}
}