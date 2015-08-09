package com.chejiawang.android.studentclient.base;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;

import org.apache.http.Header;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.app.Contants;
import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.cache.CacheManager;
import com.chejiawang.android.studentclient.subview.DividerItemDecoration;
import com.chejiawang.android.studentclient.subview.EmptyLayout;
import com.chejiawang.android.studentclient.utils.ListEntity;
import com.chejiawang.android.studentclient.utils.TDevice;
import com.chejiawang.android.studentclient.utils.WeakAsyncTask;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class BaseRecycleViewFragment extends BasedFragment implements
		RecycleBaseAdapter.OnItemClickListener,
		RecycleBaseAdapter.OnItemLongClickListener {
	public static final String BUNDLE_KEY_CATALOG = "BUNDLE_KEY_CATALOG";
	private static final String TAG = "BaseRecycleViewFragment";

	protected SwipeRefreshLayout mSwipeRefresh;// 下拉刷新控件
	protected RecyclerView mRecyclerView;
	protected LinearLayoutManager mLayoutManager;
	protected EmptyLayout mErrorLayout;
	protected int mStoreEmptyState = -1;
	protected String mStoreEmptyMessage = "";
	protected RecycleBaseAdapter mAdapter;
	protected int mCurrentPage = 0;
	protected int mCatalog = 0; // 类别
	// 异步解析线程
	private ParserTask mParserTask;
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutRes(), container, false);
	//	Logger.e(TAG, "load fragment view success !");
		initViews(view);
		return view;
	}
	/**
	 * 获取Fragment的布局文件
	 * 
	 * @return
	 */
	protected int getLayoutRes() {
		return R.layout.v2_fragment_swipe_refresh_recyclerview;
	}


	protected void initViews(View view) {
		mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
		mErrorLayout.setOnLayoutClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentPage = 0;
				mState = STATE_REFRESH;
				mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
				requestData(true);
			}
		});
		mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
		mSwipeRefresh.setColorSchemeResources(R.color.main_green,
				R.color.main_gray, R.color.main_black, R.color.main_purple);
		mSwipeRefresh.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				refresh();
			}

		});
		mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
		mRecyclerView.setOnScrollListener(mScrollListener);
		if (isNeedListDivider()) {
			mRecyclerView.addItemDecoration(new DividerItemDecoration(
					getActivity(), DividerItemDecoration.VERTICAL_LIST));
		}
		mLayoutManager = new LinearLayoutManager(getActivity());
		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setHasFixedSize(false);

		if (mAdapter != null) {
			mRecyclerView.setAdapter(mAdapter);
			mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
		} else {
			mAdapter = getListAdapter();
			mAdapter.setOnItemClickListener(this);
			mAdapter.setOnItemLongClickListener(this);
			mRecyclerView.setAdapter(mAdapter);
			if (requestDataIfViewCreated()) {
				mCurrentPage = 0;
				mState = STATE_REFRESH;
				mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
				new ReadCacheTask(this).execute();
			} else {
				mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
			}

		}
		if (mStoreEmptyState != -1) {
			mErrorLayout.setErrorType(mStoreEmptyState);
		}
		if (!TextUtils.isEmpty(mStoreEmptyMessage)) {
			mErrorLayout.setErrorMessage(mStoreEmptyMessage);
		}
	}

	@Override
	public void onDestroyView() {
		mStoreEmptyState = mErrorLayout.getErrorState();
		mStoreEmptyMessage = mErrorLayout.getErrorMessage();
		super.onDestroyView();
	}

	protected boolean requestDataIfViewCreated() {
		return true;
	}

	/**
	 * 设置Adapter
	 * 
	 * @return
	 */
	protected RecycleBaseAdapter getListAdapter() {
		return null;
	}

	/**
	 * 是否需要分割线
	 * 
	 * @return
	 */
	protected boolean isNeedListDivider() {
		return true;
	}

	/**
	 * 刷新
	 */
	protected void refresh() {
		mCurrentPage = 0;
		mState = STATE_REFRESH;
		requestData(true);
	}

	/**
	 * 加载更多
	 */
	protected void loadMore() {
		if (mState == STATE_NONE) {
			if (mAdapter.getState() == RecycleBaseAdapter.STATE_LOAD_MORE) {
				mCurrentPage ++;
				mState = STATE_LOADMORE;
				requestData(false);
			}
		}
	}

	/**
	 * 请求数据
	 * 
	 * @param b
	 */
	protected void requestData(boolean refresh) {
		sendRequestData();
	}

	protected void sendRequestData() {

	}

	private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
			int totalItemCount = mLayoutManager.getItemCount();
			if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
				if (mState == STATE_NONE && mAdapter != null
						&& mAdapter.getDataSize() > 0) {
					loadMore();
				}
			}
		};
	};

	@Override
	public boolean onItemLongClick(View view) {
		return onItemLongClick(view, mRecyclerView.getChildAdapterPosition(view));
	}

	public boolean onItemLongClick(View view, int childAdapterPosition) {

		return false;
	}

	@Override
	public void onItemClick(View view) {
		onItemClick(view, mRecyclerView.getChildAdapterPosition(view));

	}

	public void onItemClick(View view, int childAdapterPosition) {

	}

	static class ReadCacheTask extends
			WeakAsyncTask<Void, Void, byte[], BaseRecycleViewFragment> {

		public ReadCacheTask(BaseRecycleViewFragment target) {
			super(target);
		}

		@Override
		protected byte[] doInBackground(BaseRecycleViewFragment target,
				Void... params) {
			if (target == null) {
				return null;
			}
			if (TextUtils.isEmpty(target.getCacheKey())) {
				return null;
			}
			byte[] data = CacheManager.getCache(target.getCacheKey());
			if (data == null) {
				Logger.e(TAG, "cache data is empty. :" + target.getCacheKey());
				return null;
			}
			Logger.e(TAG, "exist cache: " + target.getCacheKey() + "data:"
					+ data);
			return null;
		//	return data;

		}

		@Override
		protected void onPostExecute(BaseRecycleViewFragment target,
				byte[] result) {
			super.onPostExecute(target, result);
			if (target == null) {
				return;
			}
			if (result != null) {
				target.executeParseTask(result, true);
				return;
			}
			target.refresh();
		}

	}

	protected String getCacheKey() {
		return new StringBuffer(getCacheKeyPrefix()).append(mCatalog)
				.append("_").append(mCurrentPage).append("_")
				.append(TDevice.getPageSize()).toString();
	}

	private void executeParseTask(byte[] data, boolean fromCache) {
		cacelParserTask();
		mParserTask = new ParserTask(this, data, fromCache);
		mParserTask.execute();
	}

	private void cacelParserTask() {
		if (mParserTask != null) {
			mParserTask.cancel(true);
			mParserTask = null;
		}
	}

	protected String getCacheKeyPrefix() {
		return null;
	}

	private static class ParserTask extends AsyncTask<Void, Void, String> {
		private WeakReference<BaseRecycleViewFragment> mInstance;
		private byte[] responseData;
		private boolean parseError;
		private boolean fromCache;
		private List<?> list;

		public ParserTask(BaseRecycleViewFragment instance,
				byte[] responseData, boolean fromCache) {
			super();
			this.mInstance = new WeakReference<BaseRecycleViewFragment>(
					instance);
			this.responseData = responseData;
			this.fromCache = fromCache;
		}

		@Override
		protected String doInBackground(Void... params) {
			BaseRecycleViewFragment instance = mInstance.get();
			if (instance == null)
				return null;
			try {
				ListEntity data = instance.parseList(new ByteArrayInputStream(
						responseData));
				if (!fromCache) {
					// ////////////// 此处需要发送广播时间，通知用户现在有新的消息
				}
				if (!fromCache && instance.mCurrentPage == 0
						&& !TextUtils.isEmpty(instance.getCacheKey())) {
					CacheManager.setCache(instance.getCacheKey(), responseData, instance.getCacheExpire(), CacheManager.TYPE_INTERNAL);
					//Logger.e(TAG, "cache write success:" + instance.getCacheKey());
				}
				list = data.getList();
			} catch (Exception e) {
				e.printStackTrace();
				parseError = true;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			BaseRecycleViewFragment instance = mInstance.get();
			if (instance != null) {
				if (parseError) {
					instance.executeOnLoadDataError(null);
				} else {
					instance.executeOnLoadDataSuccess(list);
					if (!fromCache) {
						if (instance.mState == STATE_REFRESH) {
							instance.onRefreshNetworkSuccess();
						}
					}
					instance.executeOnLoadFinish();
				}

			}
		}

	}

	protected void onRefreshNetworkSuccess() {

	}

	public long getCacheExpire() {
		return Contants.CACHE_EXPIRE_ONE_DAY;
	}

	protected void executeOnLoadFinish() {
		mSwipeRefresh.setRefreshing(false);
		mState = STATE_NONE;
	}

	/**
	 * 获取数据失败时候调用的代码
	 * 
	 * @param error
	 */
	protected void executeOnLoadDataError(String error) {
		if (mCurrentPage == 0) {
			if (mAdapter.getDataSize() == 0) {
				mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
			} else {
				mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
				String message = error;
				if (TextUtils.isEmpty(error)) {
					if (TDevice.hasInternet()) {
						message = getString(R.string.tip_load_date_error);
					} else {
						message = getString(R.string.tip_network_error);
					}
				}
				AppContext.showToashShort(message);
			}
		} else {
			mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
			mAdapter.setState(RecycleBaseAdapter.STATE_NETWORK_ERROR);
		}
		mAdapter.notifyDataSetChanged();
	}

	protected void executeOnLoadDataSuccess(List<?> data) {
		if (mState == STATE_REFRESH) {
			mAdapter.clear();
		}
		mAdapter.addData(data);
		mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
		if (data.size() == 0 && mState == STATE_REFRESH) {
			mErrorLayout.setErrorType(EmptyLayout.NODATA);
		} else if (data.size() < TDevice.getPageSize()) {
			if (mState == STATE_REFRESH) {
				mAdapter.setState(RecycleBaseAdapter.STATE_LESS_ONE_PAGE);
			} else
				mAdapter.setState(RecycleBaseAdapter.STATE_NO_MORE);
		} else {
			mAdapter.setState(RecycleBaseAdapter.STATE_LOAD_MORE);
		}
	}

	protected ListEntity parseList(InputStream is) throws Exception {
		return null;
	}
	
	protected ListEntity readList(Serializable seri){
		return null;
	}
	
	protected AsyncHttpResponseHandler getResponseHandler() {
		
		return new ResponseHandler(this);
	}
	private static class ResponseHandler extends AsyncHttpResponseHandler{
		private WeakReference<BaseRecycleViewFragment> mInstance;
		
		public ResponseHandler(BaseRecycleViewFragment instance) {
			mInstance = new WeakReference<BaseRecycleViewFragment>(instance);
		}
		
		@Override
		public void onFailure(int i, Header[] header, byte[] responseBytes,
				Throwable throwable) {
			BaseRecycleViewFragment instance = mInstance.get();
			if( instance.isAdded()){
				instance.executeOnLoadDataError(null);
				instance.executeOnLoadFinish();
			}
		}

		@Override
		public void onSuccess(int i, Header[] header, byte[] responseBytes) {
			BaseRecycleViewFragment instance = mInstance.get();
			if( instance.isAdded()){
				instance.executeParseTask(responseBytes, false);
			}
			
		}
		
	}
}
