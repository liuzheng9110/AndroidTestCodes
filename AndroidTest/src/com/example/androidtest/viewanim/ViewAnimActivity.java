package com.example.androidtest.viewanim;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

/**
 * 
 * Class Name: ViewAnimActivity.java Function: view 展开/搜索动画
 * 
 * @author liuzheng
 * @version 1.0
 * @created 2015年7月23日 上午10:37:41
 * @Copyright http://liuz.me
 * @url 
 *      http://stackoverflow.com/questions/4946295/android-expand-collapse-animation
 *      && http://mthli.github.io/post/View%E7%9A%84%E5%B1%95%E5%BC%80%E4%B8%
 *      8E%E6%8A%98%E5%8F%A0.html
 */
public class ViewAnimActivity extends BaseActivity implements OnClickListener {

	private LinearLayout toogleLayout;
	private ImageView mainIv;
	private boolean isToogle = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_anim_layout);

		initView();
	}

	@Override
	protected void initView() {
		toogleLayout = (LinearLayout) findViewById(R.id.toogle_layout);

		mainIv = (ImageView) findViewById(R.id.main_imageview);
		mainIv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_imageview:
			if (isToogle) {
				isToogle = false;
				expand(toogleLayout);
				mainIv.setImageResource(R.drawable.toolbar_plus);
			} else {
				isToogle = true;
				coolapse(toogleLayout);
				mainIv.setImageResource(R.drawable.toolbar_plusback);
			}
			break;
		}
	}

	/**
	 * 
	 * Function: 展开
	 * 
	 * @author liuzheng
	 * @created 2015年7月23日 下午3:30:26
	 * @param view
	 */
	private void expand(final View view) {
		/* 通过View.measure()方法，其父类视图将尝试推测出需要展开的view将要占用的空间大小 */
		view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		/* 获取view展开后的宽度 */
		final int targetWidth = view.getMeasuredWidth();
		/* 将view的宽度初始设置为0 */
		view.getLayoutParams().width = 0;
		/* 将隐藏的view设置为可见 */
		view.setVisibility(View.VISIBLE);

		/*
		 * 自定义Animation实现我们想要的功能： applyTransformation()实时调整view展开过程中的空间占用；
		 * willChangeBounds()确认移动过程中会影响view的边界。
		 */
		Animation animation = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				view.getLayoutParams().width = interpolatedTime == 1 ? ViewGroup.LayoutParams.WRAP_CONTENT : (int) (targetWidth * interpolatedTime);
				view.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};
		/*
		 * Animation.setDuration()设置动画时间； 在这里我们计算出屏幕的实际像素来精确控制时间。
		 */
		animation.setDuration((int) (targetWidth / view.getContext().getResources().getDisplayMetrics().density));
		/* Animation.setInterpolator()设置动画显示效果 */
		animation.setInterpolator(new AccelerateDecelerateInterpolator());
		/* View.startAnimation()开启动画显示 */
		view.startAnimation(animation);
	}

	/**
	 * 
	 * Function: 收缩
	 * 
	 * @author liuzheng
	 * @created 2015年7月23日 下午4:04:24
	 * @param view
	 */
	private void coolapse(final View view) {
		final int initialWidth = view.getMeasuredWidth();

		/*
		 * 自定义Animation实现我们想要的功能：
		 * applyTransformation()实时调整view折叠过程中的空间占用，注意当动画结束时将view设置为不可见；
		 * willChangeBounds()确认移动过程中会影响view的边界。
		 */
		Animation animation = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				if (interpolatedTime == 1) {
					view.setVisibility(View.GONE);
				} else {
					view.getLayoutParams().width = initialWidth - (int) (initialWidth * interpolatedTime);
					view.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		animation.setDuration((int) (initialWidth / view.getContext().getResources().getDisplayMetrics().density));
		animation.setInterpolator(new AccelerateDecelerateInterpolator());
		view.startAnimation(animation);
	}
}
