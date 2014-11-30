package com.example.androidtest;

import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.example.androidtest.main.BaseActivity;

public class TextScaleAct extends BaseActivity {
	
	private TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.text_scale_layout);
		
		mTextView = (TextView) findViewById(R.id.scale_textview);
//		mTextView.setText(getString(R.string.text));
		
		mTextView.setText("【业务描述】\n 单位纳税人、个人独资企业、一人有限责任公司、外国企业常驻代表机构申报办理税务登记。\n 【报送资料】\n （1）《税务登记表（适用单位纳税人）》1份（实行国税局、地税局联合办理税务登记证的，应提供2份）。\n （2）工商营业执照或其他核准执业证件原件及复印件。\n （3）组织机构代码证书副本原件及复印件。\n （4）有关合同、章程、协议书复印件。\n （5）法定代表人（负责人）居民身份证或护照及其他证明身份的合法证件原件及复印件。\n （6）纳税人跨县（市）设立的分支机构办理税务登记时，应提供总机构的税务登记证副本复印件。\n （7）改组改制企业应提供有关改组改制的相关文件原件及复印件。\n （8）汽油、柴油消费税纳税人还应提供以下资料：\n ——生产企业基本情况表。\n ——生产装置及工艺路线的简要说明。\n ——企业生产的所有油品名称、产品标准及用途。\n （9）外商投资企业还应提供商务部门批复设立证书原件及复印件。\n （10）外国企业常驻代表机构还应提供以下资料：\n ——注册地址及经营地址证明（产权证、租赁协议）原件及复印件；如为自有房产，应提供产权证或买卖契约等合法的产权证明原件及复印件；如为租赁的场所，应提供租赁协议原件及复印件，出租人为自然人的还应提供产权证明的原件及复印件。\n ——首席代表（负责人）护照或其他合法身份证件的原件及复印件。\n ——外国企业设立代表机构的相关决议文件及在中国境内设立的其他代表机构名单（包括名称、地址、联系方式、首席代表姓名等）。");
		
		mTextView.setOnTouchListener(new TouchZoomListener());
	}
	
	class TouchZoomListener implements OnTouchListener{
		int mode = 0;  // 触摸点的个数
		float oldDist;  
		float textSize = 0; 
		TextView textView = null;
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			textView = (TextView) v;
		    if (textSize == 0) {
		    	textSize = textView.getTextSize();
			}
		    
		    switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:// 单点按下
					mode = 1;
					break;
				case MotionEvent.ACTION_UP:// 单点松开
					mode = 0;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:// 多点按下
					oldDist = spacing(event);
					mode += 1;
					break;
				case MotionEvent.ACTION_POINTER_UP:// 多点松开
					mode -= 1;
					break;
				case MotionEvent.ACTION_MOVE:// 移动
					if (mode >= 2) {
						float newDist = spacing(event);
						if (newDist > oldDist + 1) {
							zoom(newDist / oldDist);
							oldDist = newDist;
						}
						
						if (newDist < oldDist - 1) {
							zoom(newDist / oldDist);
							oldDist = newDist;
						}
					}
					break;
				default:
					break;
			}
			return true;
		}
		
		/**
		 * 放大 缩小
		 * @param f
		 */
		private void zoom(float f) {
			textView.setTextSize(textSize *= f);
		}
		
		/**
		 * 获取移动距离
		 * @param event
		 * @return
		 */
		private float spacing(MotionEvent event) {
	        float x = event.getX(0) - event.getX(1);  
	        float y = event.getY(0) - event.getY(1);  
	        return FloatMath.sqrt(x * x + y * y);  
		}
	}
}
