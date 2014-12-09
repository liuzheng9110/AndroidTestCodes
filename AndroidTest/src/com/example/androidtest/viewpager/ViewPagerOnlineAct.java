package com.example.androidtest.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 *  Class Name: ViewPagerOnlineAct.java
 *  Function:viewpage + load online
 *  
 *  @author liuzheng
 *  @version 1.0 
 *  @created 2014-12-9 下午3:48:48
 *  @Copyright http://liuz.me
 */
public class ViewPagerOnlineAct extends BaseActivity {

	private FrameLayout viewPagerContainer;
	private ViewPager viewPager;
	private TextView indexTextView;
	private int TOTAL_COUNT = 6;
	
	String[] urls = new String[] {
			"http://a.hiphotos.baidu.com/image/pic/item/3bf33a87e950352ad6465dad5143fbf2b2118b6b.jpg",
			"http://c.hiphotos.baidu.com/image/pic/item/3801213fb80e7bec5ed8456c2d2eb9389b506b38.jpg",
			"http://d.hiphotos.baidu.com/image/pic/item/f31fbe096b63f624b88f7e8e8544ebf81b4ca369.jpg",
			"http://e.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3de308a87fc96eef01f3a297969.jpg",
			"http://f.hiphotos.baidu.com/image/pic/item/7aec54e736d12f2ecc3d90f84dc2d56285356869.jpg",
			"http://h.hiphotos.baidu.com/image/pic/item/11385343fbf2b2117c2dc3c3c88065380cd78e38.jpg"
			};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_online_layout);
		
		viewPager = (ViewPager) findViewById(R.id.tiff_view_pager);
		viewPagerContainer = (FrameLayout) findViewById(R.id.tiff_view_pager_container);
		indexTextView = (TextView) findViewById(R.id.tiff_view_pager_index);
		viewPager.setAdapter(new MyViewPagerAdapter()); 
		viewPager.setOffscreenPageLimit(TOTAL_COUNT); // 
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		viewPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
//				switch (event.getAction()) {
//				case MotionEvent.ACTION_DOWN:
//					Log.i("liuz", "ACTION_DOWN...");
//					break;
//				case MotionEvent.ACTION_UP:
//					Log.i("liuz", "ACTION_UP...");
//					break;
//				case MotionEvent.ACTION_MOVE:
////					Log.i("liuz", "ACTION_MOVE...");
//					break;
//				case MotionEvent.ACTION_POINTER_DOWN:
//					Log.i("liuz", "ACTION_POINTER_DOWN...");
//					break;
//				case MotionEvent.ACTION_POINTER_UP:
//					Log.i("liuz", "ACTION_POINTER_UP...");
//					break;
//				default:
//					break;
//				}
				return false;
			}
		});
		
		///
		indexTextView.setVisibility(View.VISIBLE);
		indexTextView.setText(new StringBuilder().append("1/").append(TOTAL_COUNT));
	}
	
	//////////////////////////2014-12-09 10:23:48 MyViewPagerAdapter liuzheng/////////////////////////////
	class MyViewPagerAdapter extends PagerAdapter {
		
		@Override
		public int getCount() {
			return TOTAL_COUNT;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view == object);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView((ImageView)object);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO init item
			ImageView imageView = new ImageView(ViewPagerOnlineAct.this);
//			imageView.setScaleType(ImageView.ScaleType.CENTER);
			imageView.setImageResource(R.drawable.dialog_icon_info);

			if (position == 0) {
				Log.i("liuz", "str..." + urls[0]);
			}
			
			((ViewPager)container).addView(imageView, position);
			return imageView;
		}
	}
	
	////////////////////////handle///////////////////////////
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			Bundle bundle = (Bundle) msg.obj;
			String str = bundle.getString("url");

			Log.i("liuz", "str..." + str);
			
		};
	};
	
	////////////////MyOnPageChangeListener//////////////////
	public class MyOnPageChangeListener implements OnPageChangeListener{
		@Override
		public void onPageScrollStateChanged(int arg0) {}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			if (viewPagerContainer != null) {
                viewPagerContainer.invalidate();
            }
		}

		@Override
		public void onPageSelected(int position) {
			// 设置当前显示数目
			indexTextView.setText(new StringBuilder().append(position + 1).append("/").append(TOTAL_COUNT));
			
			Message msg = new Message();
			Bundle bundle = new Bundle();
			bundle.putString("url", urls[position]);
			msg.obj = bundle;
			handler.sendMessage(msg);
			
		}
	}
}
