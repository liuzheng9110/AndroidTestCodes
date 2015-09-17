package com.example.androidtest.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.androidtest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Utils {
	private static Toast mToast;

	/**
	 * 
	 * @param mContext
	 * @param text
	 */
	public static void showShortToast(Context mContext, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	/**
	 * 
	 * Function:设置listview高度 (注意 必须在 setadapter 之后)
	 * 
	 * @author liuzheng
	 * @created 2014-5-16 下午10:34:13
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 
	 * Function:
	 * 
	 * @author liuzheng
	 * @created 2014-11-12 上午8:54:44
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static Bitmap getBitmapFromAssert(Context context, String fileName) {
		Bitmap bitmap = null;
		try {
			InputStream inputStream = context.getAssets().open(fileName);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}

			// bitmap = BitmapFactory.decodeStream(inputStream);
			bitmap = BitmapFactory.decodeByteArray(outStream.toByteArray(), 0, outStream.toByteArray().length);

			bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (bitmap == null) {
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_02);
		}

		return bitmap;
	}

	public static Bitmap getSmallBitmap(Context context, String fileName) {

		InputStream inputStream = null;
		try {
			inputStream = context.getAssets().open(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// BitmapFactory.decodeFile(filePath, options);
		BitmapFactory.decodeStream(inputStream, null, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		// Bitmap bm = BitmapFactory.decodeFile(filePath, options);
		Bitmap bm = BitmapFactory.decodeStream(inputStream, null, options);
		if (bm == null) {
			return null;
		}
		// int degree = readPictureDegree(filePath);
		// bm = rotateBitmap(bm,degree) ;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 30, baos);

		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bm;
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
		}

		return inSampleSize;
	}

	/**
	 * 流转字符串
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String inStream2String(InputStream is) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = is.read(buf)) != -1) {
			baos.write(buf, 0, len);
		}
		return new String(baos.toByteArray());
	}

	/**
	 * 字符串转流
	 * 
	 * @param str
	 * @return
	 */
	public static InputStream string2InStream(String str) {
		InputStream input = new ByteArrayInputStream(str.getBytes());
		return input;
	}
	
	/**
	 * dip杞负 px
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 *  px 杞负 dip
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	public static int getViewMeasuredHeight(View view) {
		// int height = view.getMeasuredHeight();
		// if(0 < height){
		// return height;
		// }
		calcViewMeasure(view);
		return view.getMeasuredHeight();
	}

	/**
	 * 测量控件的尺寸
	 * 
	 * @param view
	 */
	public static void calcViewMeasure(View view) {
		// int width =
		// View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		// int height =
		// View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		// view.measure(width,height);

		int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
		view.measure(width, expandSpec);
	}
}
