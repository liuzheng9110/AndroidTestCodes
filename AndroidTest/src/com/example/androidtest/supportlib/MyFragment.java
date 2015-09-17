package com.example.androidtest.supportlib;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtest.R;

public class MyFragment extends Fragment implements OnRefreshListener, MyRecyclerViewAdapter.OnItemClickListener, MyStaggeredViewAdapter.OnItemClickListener {
	private View view;
	private SwipeRefreshLayout swipeRefreshLayout;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager rLayoutManager;
	//
	private MyRecyclerViewAdapter rvAdapter;
	private MyStaggeredViewAdapter svAdapter;
	
	// 
	private int spanCount = 2; // girdview ÿ����ʾ��
	private int flag = 0;
    private static final int VERTICAL_LIST = 0;  // 
    private static final int HORIZONTAL_LIST = 1;// 
    private static final int VERTICAL_GRID = 2;  // 
    private static final int HORIZONTAL_GRID = 3;// 
    private static final int STAGGERED_GRID = 4; // �ٲ���
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.support_lib_frag_main, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.support_lib_swiperefresh_layout);
		recyclerView = (RecyclerView) view.findViewById(R.id.support_lib_recycler_view);
		
		// ���ݲ�ͬ flag ��ͬ��ʾ
		flag = getArguments().getInt("flag");
		setRecycleView();
		
		// �����л���swiperefreshlayout ԲȦˢ����ɫ�仯 
		swipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);;
		swipeRefreshLayout.setOnRefreshListener(this);
	}
	
	/**
	 * 
	 *  Function: ���ݲ�ͬ flag ��ͬ��ʾ
	 *  @author liuzheng
	 *  @created 2015��7��20�� ����9:48:18
	 */
	private void setRecycleView() {
		switch (flag) {
		case VERTICAL_LIST:
			rLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
			break;
		case HORIZONTAL_LIST:
			rLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
			break;
		case VERTICAL_GRID:
			rLayoutManager = new GridLayoutManager(getActivity(), spanCount, GridLayoutManager.VERTICAL, false);
			break;
		case HORIZONTAL_GRID:
			rLayoutManager = new GridLayoutManager(getActivity(), spanCount, GridLayoutManager.HORIZONTAL, false);
			break;
		case STAGGERED_GRID:
			rLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
			break;
		}
		
		// ���ٲ���
		if (flag != STAGGERED_GRID) {
			rvAdapter = new MyRecyclerViewAdapter(getActivity());
			// ע��˴��¼�Ϊ adapter ���Զ����¼�
			rvAdapter.setOnItemClickListener(this);
			recyclerView.setAdapter(rvAdapter);
		}else {
			svAdapter = new MyStaggeredViewAdapter(getActivity());
			svAdapter.setOnItemClickListener(this);
			recyclerView.setAdapter(svAdapter);
		}
		recyclerView.setLayoutManager(rLayoutManager);
	}
	
	@Override
	public void onItemClick(View view, int position) {
		SnackbarUtil.show(recyclerView, "item click .... ", position);
	}
	
	@Override
	public void onItemLongClick(View view, int position) {
		SnackbarUtil.show(recyclerView, "item long click .... ", position);
	}
	
	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				swipeRefreshLayout.setRefreshing(false);
				int temp = (int) (Math.random() * 10);
				
				if (flag != STAGGERED_GRID) {
					rvAdapter.datas.add(0, "new " + temp);
					rvAdapter.notifyDataSetChanged();	
				}else {
					svAdapter.datas.add(0, "new " + temp);
					svAdapter.heights.add(0, (int) (Math.random() * 300) + 200);
					svAdapter.notifyDataSetChanged();
				}
			}
		}, 1000);
	}
}
