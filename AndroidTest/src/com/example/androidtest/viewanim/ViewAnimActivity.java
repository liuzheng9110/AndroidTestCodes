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
 * Class Name: ViewAnimActivity.java Function: view չ��/��������
 * 
 * @author liuzheng
 * @version 1.0
 * @created 2015��7��23�� ����10:37:41
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
	 * Function: չ��
	 * 
	 * @author liuzheng
	 * @created 2015��7��23�� ����3:30:26
	 * @param view
	 */
	private void expand(final View view) {
		/* ͨ��View.measure()�������丸����ͼ�������Ʋ����Ҫչ����view��Ҫռ�õĿռ��С */
		view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		/* ��ȡviewչ����Ŀ�� */
		final int targetWidth = view.getMeasuredWidth();
		/* ��view�Ŀ�ȳ�ʼ����Ϊ0 */
		view.getLayoutParams().width = 0;
		/* �����ص�view����Ϊ�ɼ� */
		view.setVisibility(View.VISIBLE);

		/*
		 * �Զ���Animationʵ��������Ҫ�Ĺ��ܣ� applyTransformation()ʵʱ����viewչ�������еĿռ�ռ�ã�
		 * willChangeBounds()ȷ���ƶ������л�Ӱ��view�ı߽硣
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
		 * Animation.setDuration()���ö���ʱ�䣻 ���������Ǽ������Ļ��ʵ����������ȷ����ʱ�䡣
		 */
		animation.setDuration((int) (targetWidth / view.getContext().getResources().getDisplayMetrics().density));
		/* Animation.setInterpolator()���ö�����ʾЧ�� */
		animation.setInterpolator(new AccelerateDecelerateInterpolator());
		/* View.startAnimation()����������ʾ */
		view.startAnimation(animation);
	}

	/**
	 * 
	 * Function: ����
	 * 
	 * @author liuzheng
	 * @created 2015��7��23�� ����4:04:24
	 * @param view
	 */
	private void coolapse(final View view) {
		final int initialWidth = view.getMeasuredWidth();

		/*
		 * �Զ���Animationʵ��������Ҫ�Ĺ��ܣ�
		 * applyTransformation()ʵʱ����view�۵������еĿռ�ռ�ã�ע�⵱��������ʱ��view����Ϊ���ɼ���
		 * willChangeBounds()ȷ���ƶ������л�Ӱ��view�ı߽硣
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
