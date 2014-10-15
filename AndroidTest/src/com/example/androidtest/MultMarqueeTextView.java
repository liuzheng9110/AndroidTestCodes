package com.example.androidtest;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class MultMarqueeTextView extends TextView {
	public MultMarqueeTextView(Context context) {
		this(context, null);
	}

	public MultMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		setFocusable(true);
		setFocusableInTouchMode(true);

		setSingleLine();// 单行
		setEllipsize(TextUtils.TruncateAt.MARQUEE);// 滚动
		setMarqueeRepeatLimit(-1);// 无限重复
	}

	public MultMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setFocusable(true);
		setFocusableInTouchMode(true);

		setSingleLine();
		setEllipsize(TextUtils.TruncateAt.MARQUEE);
		setMarqueeRepeatLimit(-1);
	}
	
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if (focused) {
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if (hasWindowFocus) {
			super.onWindowFocusChanged(hasWindowFocus);
		}
	}
	
	@Override
	public boolean isFocused() {
		return true;
	}	

}