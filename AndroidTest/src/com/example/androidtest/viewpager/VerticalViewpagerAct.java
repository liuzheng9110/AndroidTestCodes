package com.example.androidtest.viewpager;

import com.example.androidtest.R;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class VerticalViewpagerAct extends FragmentActivity {
	
	private VerticalViewPager verticalViewPager;

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vertical_viewpager);
		
		verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalViewPager1);
        verticalViewPager.setAdapter(new VerticalViewPagerAdapter(getSupportFragmentManager()));
        verticalViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin));
        verticalViewPager.setPageMarginDrawable(new ColorDrawable(getResources().getColor(android.R.color.holo_green_dark)));
        
//        verticalViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View view, float position) {
//                int pageWidth = view.getWidth();
//                int pageHeight = view.getHeight();
//
//                if (position < -1) { // [-Infinity,-1)
//                    // This page is way off-screen to the left.
//                    view.setAlpha(0);
//
//                } else if (position <= 1) { // [-1,1]
//                    // Modify the default slide transition to shrink the page as well
//                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//                    if (position < 0) {
//                        view.setTranslationY(vertMargin - horzMargin / 2);
//                    } else {
//                        view.setTranslationY(-vertMargin + horzMargin / 2);
//                    }
//
//                    // Scale the page down (between MIN_SCALE and 1)
//                    view.setScaleX(scaleFactor);
//                    view.setScaleY(scaleFactor);
//
//                    // Fade the page relative to its size.
//                    view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//                } else { // (1,+Infinity]
//                    // This page is way off-screen to the right.
//                    view.setAlpha(0);
//                }
//            }
//        });
	}
}
