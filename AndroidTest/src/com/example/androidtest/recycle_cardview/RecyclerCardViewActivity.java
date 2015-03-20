package com.example.androidtest.recycle_cardview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.main.BaseActivity;

public class RecyclerCardViewActivity extends BaseActivity {
	
	private CardView cardView;
	private RecyclerView recyclerView;
	private MyAdapter mAdapter;
	private List<RecyclerItemObj> objs = new ArrayList<RecyclerCardViewActivity.RecyclerItemObj>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_view_layout);
		
		for (int i = 0; i < 10; i++) {
			RecyclerItemObj obj = new RecyclerItemObj();
			obj.setTitleVal("2015年3月9日 近日热门");
			obj.setDescsVal("摘录了『当很重要的人寻求更大的世界，却因此不得不离开你的时候，支持还是阻止好？』、『科技界曾经出现哪些有趣打脸的「神预言」？』、『如何在 90 天内学好英语？』、『身为维吾尔人在中国生活是什么体验？』、『想买平常能穿出去的汉服有什么推荐？』、『失传的古文字（象形文字、楔形文字等）最初是如何被破译的？如何确认译文的正确性？』、『穷养的女孩子有哪些优秀的品质？』、『男友感染了艾滋病毒，双方父母都不知道，父母逼婚怎么办？』等问题下的32个答案");
			obj.setImageUrls("card_image");
			
			objs.add(obj);
		}
		
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		// 设置布局管理  仅有 LinearLayoutManager 注意recycler 父布局为 LinearLayout
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		// 设置Item动画
		recyclerView.setItemAnimator(new DefaultItemAnimator());
//		// 
//		recyclerView.setHasFixedSize(true);
		// 
		mAdapter = new MyAdapter(this, objs);
		recyclerView.setAdapter(mAdapter);
		// recycle 添加点击事件  方法   
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnRecyclerItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				showShortToast("position...." + position);
				
			}
		}));
	}
	
	class RecyclerItemObj {
		private String titleVal;
		private String descsVal;
		private String imageUrls;
		public String getTitleVal() {
			return titleVal;
		}
		public void setTitleVal(String titleVal) {
			this.titleVal = titleVal;
		}
		public String getDescsVal() {
			return descsVal;
		}
		public void setDescsVal(String descsVal) {
			this.descsVal = descsVal;
		}
		public String getImageUrls() {
			return imageUrls;
		}
		public void setImageUrls(String imageUrls) {
			this.imageUrls = imageUrls;
		}
	}
	
	/**
	 * 
	 *  Class Name: RecyclerViewAct.java
	 *  Function:
	 *  
	 *  @author liuzheng
	 *  @version 1.0 
	 *  @created 2015年3月9日 上午11:10:07
	 *  @Copyright http://liuz.me
	 */
	class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

		private Context mContext;
		private List<RecyclerItemObj> mObjs;
		
		public MyAdapter(Context context, List<RecyclerItemObj> objs) {
			this.mContext = context;
			this.mObjs = objs;
		}
		
		@Override
		public int getItemCount() {
			return mObjs == null ? 0 : mObjs.size();
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup vg, int i) {
			View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, vg, false);
			return new ViewHolder(v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder viewHolder, int location) {
			RecyclerItemObj obj = mObjs.get(location);
			
			viewHolder.titleTv.setText(obj.getTitleVal());
			viewHolder.descsTv.setText(obj.getDescsVal());
//			viewHolder.usrImages.setImageURI(Uri.parse(obj.imageUrls));
			viewHolder.usrImages.setImageResource(R.drawable.card_image);
		}

		class ViewHolder extends RecyclerView.ViewHolder{
			private TextView titleTv;
			private TextView descsTv;
			private ImageView usrImages;
			
			public ViewHolder(View v) {
				super(v);
				titleTv = (TextView) v.findViewById(R.id.title_tv);
				descsTv = (TextView) v.findViewById(R.id.desc_tv);
				usrImages = (ImageView) v.findViewById(R.id.usrs_image);
			}
		}
	}
}
