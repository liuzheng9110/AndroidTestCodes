package com.example.androidtest.seven_uncle;


import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class CircleScrollView extends ViewGroup{
	
	private PagerAdapter adapter;
	
	private int xFirstOffset;
	private int xLastOffset;
	private int firstItemIndex;
	private int lastItemIndex;
	private int middleItemIndex;
	private int scrollOffsetX;
	private int xScroll;
	
	private int currentItem;
	
	private int widthMeasureSpec;
	private int heightMeasureSpec;
	
	private int width;
	private int height;
	
	private boolean requestMeasure;
	
	private boolean isDataChanged;
	
	private GestureDetector gestureDetector;
	
	private int dx;
	private int dy;
	
	private int scrollX;
	private int scrollY;
	private boolean scroll;
	
	private float flingX;
	private boolean fling;
	
	private Scroller scroller;
	private static final int GLOBAL_DURATION = 500;
	
	private static final long AUTO_SCROLL_DELAY = 5000L;
	private boolean autoScroll;
	
	private static final int MIN_VELOCITY_FLING = 400;
	private int minVelocityFling;
	
	private static final int MIN_FLING_DISTANCE = 4;
	private int minFlingDistance;
	
	private OnPageChangeListener onPageChangeListener;
	
	public interface OnPageChangeListener{
		public void onPageScroll(float offset, int prePosition, int afterPosition);
		public void onPageSelected(int currentItemPosition);
	}
	
	private SimpleOnGestureListener onGestureListener = new SimpleOnGestureListener(){
		@Override
		public boolean onDown(MotionEvent event){
			scrollX = (int) event.getRawX();
			scrollY = (int) event.getRawY();
			handler.removeCallbacks(onFlingRunnable);
			handler.removeCallbacks(fakeScrollRunnable);
			scroller.abortAnimation();
			scroller.forceFinished(true);
			fling = false;
			flingX = getPaddingLeft();
			
			return true;
		}
		
		@Override
		public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY){
			scroll = true;
			scrollOffsetX = (int) event2.getRawX() - scrollX;
			requestLayout();
			calculateScroll(scrollOffsetX);
			
			scrollX = (int) event2.getRawX();
			scrollY = (int) event2.getRawY();
			
			int tCurrentItem = getViewPositionFromCoordinate(width / 2);
			if(tCurrentItem != currentItem){
				currentItem = tCurrentItem;
				if(onPageChangeListener != null) onPageChangeListener.onPageSelected(currentItem);
			}
			
			if(distanceX > minFlingDistance){
				return true;
			}else{
				return false;
			}
		}
		
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
			scroll = false;
			fling = true;
			View child = getViewFromCoordinate(width / 2);
			if(child != null){
				if(Math.abs(velocityX) > minVelocityFling){
					if(velocityX > 0){
						flingX = getPaddingLeft();
					}else{
						flingX = getPaddingLeft() - child.getMeasuredWidth();
					}
				}else{
					if(child.getLeft() > getPaddingLeft()){
						flingX = getPaddingLeft() - child.getMeasuredWidth();
					}else{
						flingX = getPaddingLeft();
					}
				}
				fling(flingX);
			}
			
			return true;
		}
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent event){
			return false;
		}
		
	};
	
	private void fling(float flingDistance){
		scroller.startScroll(xFirstOffset, 0, (int) flingDistance - xFirstOffset, 0, GLOBAL_DURATION);
		handler.post(onFlingRunnable);
	}
	
	private DataSetObserver circleScrollDataSetObserver = new DataSetObserver(){
		@Override
		public void onChanged(){
			isDataChanged = true;
			requestLayout();
			invalidate();
		}
		
		@Override
		public void onInvalidated(){
			init();
			requestLayout();
			invalidate();
		}
	};
	
	private Handler handler;
	private Runnable onFlingRunnable = new Runnable(){
		
		@Override
		public void run(){
			requestLayout();
			
			scroller.computeScrollOffset();
			scrollOffsetX = scroller.getCurrX() - xFirstOffset;
			calculateScroll(scrollOffsetX);
			
			if(!scroller.isFinished()){
				handler.postDelayed(this, 20);
				int tCurrentItem = getViewPositionFromCoordinate(width / 2);
				if(currentItem != tCurrentItem){
					currentItem = tCurrentItem;
					if(onPageChangeListener != null) onPageChangeListener.onPageSelected(currentItem);
				}
			}else{
				fling = false;
			}
			 
		}
	};
	
	private Runnable fakeScrollRunnable = new Runnable(){
		@Override
		public void run(){
			fakeScroll(1);
			Log.d("fake", "scroll");
			
			handler.postDelayed(this, AUTO_SCROLL_DELAY);
		}
	};
	
	
	private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };
	
	public CircleScrollView(Context context){
		this(context, null);
	}
	
	public CircleScrollView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	public CircleScrollView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		
		init();
		isDataChanged = false;
		
		requestMeasure = true;
		autoScroll = false;
		
		gestureDetector = new GestureDetector(context, onGestureListener);
		
		handler = new Handler();
		
		final float density = context.getResources().getDisplayMetrics().density;
		
		minVelocityFling = (int) (MIN_VELOCITY_FLING * density);
		minFlingDistance = (int) (MIN_FLING_DISTANCE * density);
		
		scroller = new Scroller(context, sInterpolator);
	}
	
	public void startAutoScroll(){
		autoScroll = true;
		handler.postDelayed(fakeScrollRunnable, AUTO_SCROLL_DELAY);
	}
	
	public void stopAutoScroll(){
		autoScroll = false;
		handler.removeCallbacks(fakeScrollRunnable);
	}
	
	public void requestMeasureAndLayout(){
		requestMeasure = true;
		requestLayout();
	}
	
	public void disableMeasure(){
		requestMeasure = false;
	}
	
	private void calculateScroll(int scrollX){
		xFirstOffset += scrollX;
		xLastOffset += scrollX;
	}
	
	private void init(){
		xFirstOffset = getPaddingLeft();
		xLastOffset = getPaddingLeft();
		firstItemIndex = -1;
		lastItemIndex = -1;
		middleItemIndex = 0;
		xScroll = getPaddingLeft();
		currentItem = 0;
	}
	
	public void setAdapter(PagerAdapter adapter){
		if(this.adapter != null){
			this.adapter.unregisterDataSetObserver(circleScrollDataSetObserver);
		}
		
		if(adapter != null){
			this.adapter = adapter;
			this.adapter.registerDataSetObserver(circleScrollDataSetObserver);
		}
		
		firstItemIndex = adapter.getCount() - 1;
		
		isDataChanged = true;
		init();
		requestLayout();
	}
	
	public PagerAdapter getAdapter(){
		return adapter;
	}
	
	public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener){
		this.onPageChangeListener = onPageChangeListener;
	}
	
	public OnPageChangeListener getOnPageChangeListener(){
		return onPageChangeListener;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		
		widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);
		
		this.widthMeasureSpec = widthMeasureSpec;
		this.heightMeasureSpec = heightMeasureSpec;
		
		if(requestMeasure){
			//PLog.d("measure", "measure");
			View child = getChildAt(0);
			boolean add = false;
			if(child == null && adapter != null){
				child = obtainView(0);
				addView(child);
				add = true;
			}
			
			if(child != null){
				child.measure(widthMeasureSpec, heightMeasureSpec);
				if(height != child.getMeasuredHeight()){
					height = child.getMeasuredHeight();
					setMeasuredDimension(width, height);
					
					//heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
					
					//if(height > 0) requestMeasure = false;
					if(add) removeView(child);
				}
			}
		}
	}
	
	private void fillLayout(){
		int totalCount = adapter.getCount();
		View child = getChildAt(getChildCount() - 1);
		if(child == null){
			lastItemIndex = 0;
			child = obtainView(lastItemIndex);
			child.setTag(lastItemIndex);
			addViewInLayout(child, -1, getLayoutParams(child), true);
			child.measure(widthMeasureSpec, heightMeasureSpec);
			
			xLastOffset += child.getMeasuredWidth();
		}
		
		while(child != null && xLastOffset - getPaddingRight() < width){
			lastItemIndex = (Integer) child.getTag() + 1;
			if(lastItemIndex >= totalCount) lastItemIndex = 0;
			child = obtainView(lastItemIndex);
			child.setTag(lastItemIndex);
			addViewInLayout(child, -1, getLayoutParams(child), true);
			child.measure(widthMeasureSpec, heightMeasureSpec);
			
			xLastOffset += child.getMeasuredWidth();
		}
		
		child = getChildAt(0);
		if(child == null){
			firstItemIndex = 0;
			child = obtainView(firstItemIndex);
			child.setTag(firstItemIndex);
			addViewInLayout(child, 0, getLayoutParams(child), true);
			child.measure(widthMeasureSpec, heightMeasureSpec);
			
			xFirstOffset -= child.getMeasuredWidth();
		}
		
		while(child != null && xFirstOffset > getPaddingLeft()){
			firstItemIndex = (Integer) child.getTag() - 1;
			if(firstItemIndex <= -1) firstItemIndex = totalCount - 1;
			child = obtainView(firstItemIndex);
			child.setTag(firstItemIndex);
			addViewInLayout(child, 0, getLayoutParams(child), true);
			child.measure(widthMeasureSpec, heightMeasureSpec);
			
			xFirstOffset -= child.getMeasuredWidth();
		}
		
	}
	
	private View obtainView(int position){
		return (View) adapter.instantiateItem(this, position);
	}
	
	private void removeFromLayout(){
		View child = getChildAt(0);
		while(child != null && xFirstOffset + getPaddingLeft() < -child.getMeasuredWidth()){
			xFirstOffset += child.getMeasuredWidth();
			
			Log.d("xFirstOffset", "x" + xFirstOffset);
			
			int position = (Integer) child.getTag();
			removeViewInLayout(child);
			firstItemIndex = position + 1;
			if(firstItemIndex >= adapter.getCount()) firstItemIndex = 0;
			
			child = getChildAt(0);
		}
		
		child = getChildAt(getChildCount() - 1);
		while(child != null && xLastOffset - getPaddingRight() > width + child.getMeasuredWidth()){
			xLastOffset -= child.getMeasuredWidth();
			
			int position = (Integer) child.getTag();
			removeViewInLayout(child);
			lastItemIndex = position - 1;
			if(lastItemIndex <= -1) lastItemIndex = adapter.getCount() - 1;
			
			child = getChildAt(getChildCount() - 1);
			
		}
	}
	
	private void layoutChild(){
		int length = getChildCount();
		int leftOffset = xFirstOffset;
		
		int top = getTop() + getPaddingTop();
		int bottom = getBottom() + getPaddingBottom();
		for(int i = 0; i < length; i++){
			View child = getChildAt(i);
			if(child != null){
				int childWidth = child.getMeasuredWidth();
				
				int left = leftOffset;
				int right = leftOffset + childWidth;
				
				child.layout(left, top, right, bottom);
				
				leftOffset += childWidth;
			}
		}
	}
	
	private ViewGroup.LayoutParams getLayoutParams(View child){
		ViewGroup.LayoutParams params = child.getLayoutParams();
		if(params == null){
			params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		
		return params;
	}
	
	private View getViewFromCoordinate(int x){
		int length = getChildCount();
		for(int i = 0; i < length; i++){
			View child = getChildAt(i);
			if(child.getLeft() <= x && child.getRight() >= x){
				return child;
			}
		}
		
		return null;
	}
	
	private int getViewPositionFromCoordinate(int x){
		int length = getChildCount();
		for(int i = 0; i < length; i++){
			View child = getChildAt(i);
			if(child.getLeft() <= x && child.getRight() >= x){
				return (Integer) child.getTag();
			}
		}
		
		return -1;
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom){
		
		removeFromLayout();
		fillLayout();
		layoutChild();
	}
	
	public void fakeScroll(int direction){
		scroller.abortAnimation();
		scroller.forceFinished(true);
		if(scroll) return;
		if(fling){
			handler.removeCallbacks(onFlingRunnable);
			fling = false;
		}
		if(direction == 0) throw new IllegalArgumentException();
		if(direction > 0){
			requestLayout();
			
			calculateScroll(-50);
			layout(0, 0, 0, 0);
			int vx = -minVelocityFling - 1;
			View child = getViewFromCoordinate(width / 2);
			if(child != null){
				if(vx > 0){
					flingX = getPaddingLeft();
				}else{
					flingX = getPaddingLeft() - child.getMeasuredWidth();
				}
				Log.d("flingX", flingX + "");
				Log.d("xFirstOffset", xFirstOffset + "");
				fling(flingX);
			}
		}else{
			View child = getViewFromCoordinate(width / 2);
			if(child != null){
				flingX = getPaddingLeft();
				fling(flingX);
			}
		}
	}
	
	public void setCurrentItem(int position, boolean smooth){
		int duration = smooth ? GLOBAL_DURATION : 1;
		
		int distance = 0;
		
		if(smooth){
			int halfCount = adapter.getCount() / 2;
			if(position - currentItem > halfCount){
				
			}
		}else{
			
		}
		
		scroller.startScroll(xFirstOffset, 0, distance, 0, duration);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event){
		switch(event.getActionMasked()){
		case MotionEvent.ACTION_DOWN:
			gestureDetector.onTouchEvent(event);
			dx = (int) event.getX();
			dy = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			dx = (int) (event.getX() - dx);
			dy = (int) (event.getY() - dy);
			
			if(Math.abs(dx) > Math.abs(dy) && dx * dx + dy * dy > minFlingDistance * minFlingDistance){
				getParent().requestDisallowInterceptTouchEvent(true);
				return true;
			}
			
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			scroll = false;
			if(autoScroll) handler.postDelayed(fakeScrollRunnable, AUTO_SCROLL_DELAY);
			if(!fling){
				View child = getViewFromCoordinate(width / 2);
				if(child != null && child.getLeft() != getPaddingLeft()){
					if(child.getLeft() < getPaddingLeft()){
						flingX = getPaddingLeft();
					}else{
						flingX = getPaddingLeft() - child.getMeasuredWidth();
					}
					fling(flingX);
				}
				
			}
			 
			break;
		}
		
		return super.onInterceptTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		gestureDetector.onTouchEvent(event);
		switch(event.getActionMasked()){
		case MotionEvent.ACTION_DOWN:
			handler.removeCallbacks(onFlingRunnable);
			flingX = getPaddingLeft();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			scroll = false;
			if(autoScroll) handler.postDelayed(fakeScrollRunnable, AUTO_SCROLL_DELAY);
			if(!fling){
				View child = getViewFromCoordinate(width / 2);
				if(child != null){
					if(child.getLeft() < getPaddingLeft()){
						flingX = getPaddingLeft();
					}else{
						flingX = getPaddingLeft() - child.getMeasuredWidth();
					}
					fling(flingX);
				}
				
			}
			break;
		}
		
		return super.onTouchEvent(event);
	}
}