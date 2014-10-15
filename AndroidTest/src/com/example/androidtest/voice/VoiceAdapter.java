package com.example.androidtest.voice;

import java.util.ArrayList;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

public class VoiceAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<VoiceEntity> mVoiceList;
	// 语音播放
	private MediaPlayer mMediaPlayer = new MediaPlayer();
	
	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;// 收到对方的消息
		int IMVT_TO_MSG = 1;// 自己发送出去的消息
	}
	
	public VoiceAdapter(Context context, ArrayList<VoiceEntity> voiceList){
		this.mContext = context;
		this.mVoiceList = voiceList;  
		
		mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mVoiceList.size();
	}

	@Override
	public VoiceEntity getItem(int position) {
		// TODO Auto-generated method stub
		return mVoiceList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
		VoiceEntity entity = mVoiceList.get(position);

		if (entity.getMsgType()) {// 收到的消息
			return IMsgViewType.IMVT_COM_MSG;
		} else {// 自己发送的消息
			return IMsgViewType.IMVT_TO_MSG;
		}
	}
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final VoiceEntity entity = mVoiceList.get(position);
		boolean isComMsg = entity.getMsgType();

		final VoiceHolder voiceHolder;
		if (convertView == null) {
			if (isComMsg) {
				convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);
			} else {
				convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);
			}

			voiceHolder = new VoiceHolder();
			voiceHolder.ivHeadImage = (ImageView) convertView.findViewById(R.id.iv_userhead);
			voiceHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
			voiceHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
			voiceHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			voiceHolder.isComMsg = isComMsg;
			
			convertView.setTag(voiceHolder);
		} else {
			voiceHolder = (VoiceHolder) convertView.getTag();
		}
		
		voiceHolder.tvSendTime.setText(entity.getDate());
		if (entity.getText().contains(".amr")) {
			voiceHolder.tvContent.setText("");
			voiceHolder.tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.chatto_voice_playing, 0);
			voiceHolder.tvTime.setText(entity.getTime());
		} else {
			voiceHolder.tvContent.setText(entity.getText());			
			voiceHolder.tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			voiceHolder.tvTime.setText("");
		}
		
		voiceHolder.tvContent.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (entity.getText().contains(".amr")) {
					playMusic(android.os.Environment.getExternalStorageDirectory()+"/"+entity.getText()) ;
				}
			}
		});
		
		return convertView;
	}
	
	class VoiceHolder {
		ImageView ivHeadImage;
		TextView tvSendTime;
		TextView tvContent;
		TextView tvTime;
		boolean isComMsg;
	}
	
	/**
	 * @Description
	 * @param name
	 */
	private void playMusic(String name) {
		try {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
			}else {
				mMediaPlayer.reset();
				mMediaPlayer.setDataSource(name);
				mMediaPlayer.prepare();
				mMediaPlayer.start();
				mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						Toast.makeText(mContext, "播放完毕....", Toast.LENGTH_SHORT).show();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
