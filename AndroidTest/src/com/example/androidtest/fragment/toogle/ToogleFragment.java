package com.example.androidtest.fragment.toogle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.sqlite.SimpleData;

public class ToogleFragment extends BaseFragment {
	private int type;
	private boolean isSbxxChange = false;
	private boolean isJkxxChange = false;

    final static String SBXX = "sbxx";
    final static String JKXX = "jkxx";
    
    private String sbxxStr, jkxxStr;
	
	private Activity mActivity;
	private TextView textView;
	private ListView listView;
	private MyFragmentAdapter<SimpleData> adapter;
	private List<SimpleData> userInfos = new ArrayList<SimpleData>();
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			adapter.notifyDataSetChanged();
		};
	};
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = getActivity();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
	 	type = bundle.getInt("type", -1);
	 	isSbxxChange = bundle.getBoolean("isSbxxChange");
	 	isJkxxChange = bundle.getBoolean("isJkxxChange");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if (savedInstanceState != null) {
			sbxxStr = savedInstanceState.getString(SBXX);
			jkxxStr = savedInstanceState.getString(JKXX);
			
			Log.i("liuz", sbxxStr);
		}
		
		View view = inflater.inflate(R.layout.fragment_toogle_jkxx, container, false);
		
		textView = (TextView) view.findViewById(R.id.tips_text);
		listView = (ListView) view.findViewById(R.id.jkxx_listview);
		adapter = new MyFragmentAdapter<SimpleData>(mActivity, userInfos);
		listView.setAdapter(adapter);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i("liuz", "onActivityCreated...");
		super.onActivityCreated(savedInstanceState);
		userInfos.clear();
		if (type == 0) { // sbxx
			textView.setText("sbxx...fragment");
			if (isSbxxChange) {
				showShortToast("get sbxx data from server...");
				
				sbxxStr = "{\"tiripPackage\":{\"service\":{\"serviceId\":\"ITAX.APP.DJH.FHTZ.SFSB\",\"tranSeq\":\"+861869224652020150831174509570806973\",\"tranReqDate\":\"2015-08-3117:45:09\"},\"businessContent\":{\"response\":{\"items\":[{\"ZSXM\":\"个人所得税\",\"ZSPM\":\"工资薪金所得\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":0,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":11365.45},{\"ZSXM\":\"个人所得税\",\"ZSPM\":\"稿酬所得\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":0,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":80274.31},{\"ZSXM\":\"营业税\",\"ZSPM\":\"房屋租赁\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":12500,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":12500},{\"ZSXM\":\"其他收入\",\"ZSPM\":\"工会经费\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":5912.43,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":5912.43},{\"ZSXM\":\"房产税\",\"ZSPM\":\"其他房屋出租\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":30000,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":30000},{\"ZSXM\":\"城市维护建设税\",\"ZSPM\":\"市区（营业税附征）\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":875,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":875},{\"ZSXM\":\"水利建设专项收入\",\"ZSPM\":\"地方其他水利建设基金\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":10398.04,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":10398.04},{\"ZSXM\":\"教育费附加\",\"ZSPM\":\"营业税教育费附加\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":375,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":375},{\"ZSXM\":\"地方教育附加\",\"ZSPM\":\"营业税地方教育附加\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":250,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":250},{\"ZSXM\":\"城市维护建设税\",\"ZSPM\":\"市区（增值税附征）\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":29302.56,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":29302.56},{\"ZSXM\":\"地方教育附加\",\"ZSPM\":\"增值税地方教育附加\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":8372.16,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":8372.16},{\"ZSXM\":\"教育费附加\",\"ZSPM\":\"增值税教育费附加\",\"NSSBRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":12558.24,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":12558.24},{\"ZSXM\":\"企业所得税\",\"ZSPM\":\"应纳税所得额\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":389.2,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":389.2},{\"ZSXM\":\"城市维护建设税\",\"ZSPM\":\"市区（营业税附征）\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":13.62,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":13.62},{\"ZSXM\":\"营业税\",\"ZSPM\":\"转让著作权\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":194.6,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":194.6},{\"ZSXM\":\"地方教育附加\",\"ZSPM\":\"营业税地方教育附加\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":3.89,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":3.89},{\"ZSXM\":\"教育费附加\",\"ZSPM\":\"营业税教育费附加\",\"NSSBRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":5.84,\"YJSE\":0,\"JMSE\":0,\"YBTSE\":5.84}],\"rtnCode\":\"1\",\"rtnMsg\":\"查询分台账税费申报成功\"}},\"returnState\":{\"returnCode\":\"00\",\"returnMessage\":\"成功\"}}}";
				
			}else {
				showShortToast("get sbxx data from local ...");
				
			}
		}else if (type == 1) {
			textView.setText("jkxx...fragment");
			if (isJkxxChange) {
				showShortToast("get jkxx data from server...");
				
				jkxxStr = "{\"tiripPackage\":{\"service\":{\"serviceId\":\"ITAX.APP.DJH.FHTZ.JKXX\",\"tranSeq\":\"+861869224652020150901105414950324761\",\"tranReqDate\":\"2015-09-0110:54:14\"},\"businessContent\":{\"response\":{\"items\":[{\"ZSXM\":\"营业税\",\"ZSPM\":\"房屋租赁\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":12500,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"其他收入\",\"ZSPM\":\"工会经费\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":5912.43,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"房产税\",\"ZSPM\":\"其他房屋出租\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":30000,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"城市维护建设税\",\"ZSPM\":\"市区（营业税附征）\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":875,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"水利建设专项收入\",\"ZSPM\":\"地方其他水利建设基金\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":10398.04,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"教育费附加\",\"ZSPM\":\"营业税教育费附加\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":375,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"地方教育附加\",\"ZSPM\":\"营业税地方教育附加\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":250,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"城市维护建设税\",\"ZSPM\":\"市区（增值税附征）\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":29302.56,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"地方教育附加\",\"ZSPM\":\"增值税地方教育附加\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":8372.16,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"教育费附加\",\"ZSPM\":\"增值税教育费附加\",\"YZFSRQ\":\"2015-08-13\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":12558.24,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"个人所得税\",\"ZSPM\":\"工资薪金所得\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":0,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"个人所得税\",\"ZSPM\":\"稿酬所得\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-07-01\",\"SKSSQZ\":\"2015-07-31\",\"YNSE\":0,\"SKSX\":\"一般申报\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"企业所得税\",\"ZSPM\":\"应纳税所得额\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":389.2,\"SKSX\":\"代扣代缴税款\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"城市维护建设税\",\"ZSPM\":\"市区（营业税附征）\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":13.62,\"SKSX\":\"代扣代缴税款\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"营业税\",\"ZSPM\":\"转让著作权\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":194.6,\"SKSX\":\"代扣代缴税款\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"地方教育附加\",\"ZSPM\":\"营业税地方教育附加\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":3.89,\"SKSX\":\"代扣代缴税款\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"},{\"ZSXM\":\"教育费附加\",\"ZSPM\":\"营业税教育费附加\",\"YZFSRQ\":\"2015-08-14\",\"SKSSQQ\":\"2015-08-01\",\"SKSSQZ\":\"2015-08-31\",\"YNSE\":5.84,\"SKSX\":\"代扣代缴税款\",\"SKSSSWJG\":\"湖南省地方税务局直属局\"}],\"rtnCode\":\"1\",\"rtnMsg\":\"查询分台账缴款信息成功\"}},\"returnState\":{\"returnCode\":\"00\",\"returnMessage\":\"成功\"}}}";
				
			}else {
				showShortToast("get jkxx data from db...");
			}
		}
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current article selection in case we need to recreate the fragment
        outState.putString(SBXX, sbxxStr);
        outState.putString(JKXX, jkxxStr);
    }
	
	public static void getDataFromServer() {

	}
	
	public void updateDatas(int type, boolean isChange) {
		if (type == 0 && isChange) {
			getSbxxFromServer();
		}else if (type == 1 && isChange) {
			getJkxxFromServer();
		}
    }

	private void getJkxxFromServer() {
		showShortToast("getJkxxFromServer....");
	}

	private void getSbxxFromServer() {
		showShortToast("getSbxxFromServer....");
	}
}
