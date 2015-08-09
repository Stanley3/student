package com.chejiawang.android.studentclient.base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.chejiawang.android.studentclient.R;
import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.utils.TDevice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class RecycleBaseAdapter extends Adapter<RecycleBaseAdapter.ViewHolder> {
	
	public static final int STATE_EMPTY_ITEM = 0;
	public static final int STATE_LOAD_MORE = 1;
	public static final int STATE_NO_MORE = 2;
	public static final int STATE_NO_DATA = 3;
	public static final int STATE_LESS_ONE_PAGE = 4;
	public static final int STATE_NETWORK_ERROR =5;

	public static final int TYPE_FOOTER = 0x101;
	public static final int TYPE_HEADER = 0x102;
	protected int state = STATE_LESS_ONE_PAGE;
	
	protected ArrayList _data = new ArrayList();
	
	private WeakReference<OnItemClickListener> mListener;
	public interface OnItemClickListener{
		public void onItemClick(View view);
	}
	private WeakReference<OnItemLongClickListener> mLongListener;
	public interface OnItemLongClickListener{
		public boolean onItemLongClick(View view);
	}
	protected View mHeaderView;
	
	protected int _loadmoreTest;
	protected int _loadFinishText;
	
	private LayoutInflater mInflater;
	public RecycleBaseAdapter() {
		_loadFinishText = R.string.loading_no_more;
		_loadmoreTest = R.string.loading;
	}
	public int getState() {
		return this.state;
	}
	public void setState(int state){
		this.state = state;
	}
	public View getmHeaderView() {
		return mHeaderView;
	}
	public void setmHeaderView(View mHeaderView) {
		this.mHeaderView = mHeaderView;
	}
	
	public ArrayList get_data() {
		return _data;
	}
	public void set_data(ArrayList _data) {
		this._data = _data;
		notifyDataSetChanged();
	}
	public void setOnItemClickListener(OnItemClickListener mListener) {
		this.mListener = new WeakReference<RecycleBaseAdapter.OnItemClickListener>(mListener);
	}
	public void setOnItemLongClickListener(
			OnItemLongClickListener mLongListener) {
		this.mLongListener = new WeakReference<RecycleBaseAdapter.OnItemLongClickListener>(mLongListener);
	}
	public void set_loadmoreTest(int _loadmoreTest) {
		this._loadmoreTest = _loadmoreTest;
	}
	public void set_loadFinishText(int _loadFinishText) {
		this._loadFinishText = _loadFinishText;
	}
	public void addData(List data){
		if(_data == null){
			_data = new ArrayList();
		}
		_data.addAll(data);
		notifyDataSetChanged();
	}
	public void addItem(Object obj){
		if(_data == null){
			_data = new ArrayList();
		}
		_data.add(obj);
		notifyDataSetChanged();
	}
	public void addItem(int pos, Object obj){
		if(_data == null){
			_data = new ArrayList();
		}
		_data.add(pos, obj);
		notifyDataSetChanged();
	}
	public void removeItem(Object obj){
		_data.remove(obj);
		notifyDataSetChanged();
	}
	public void clear(){
		_data.clear();
		notifyDataSetChanged();
	}
	public Object getItem(int position){
		if(position < 0){
			return null;
		}
		if(_data.size() > position){
			return _data.get(position);
		}
		return null;
	}
	@Override
	public long getItemId(int position){
		return position;
	}
	@Override
	public int getItemCount() {
		int size = getDataSize();
		if( hasFooter()){
			size += 1;
		}
		if( hasHeader()){
			size += 1;
		}
		return size;
	}
	private boolean hasHeader() {
		return mHeaderView != null;
	}
	
	private boolean hasFooter() {
		switch(getState()){
		case STATE_EMPTY_ITEM:
		case STATE_LOAD_MORE:
		case STATE_NO_MORE:
		case STATE_NETWORK_ERROR:
			return true;
		default:
			break;
		}
		return false;
	}
	@Override
	public void onBindViewHolder(RecycleBaseAdapter.ViewHolder holder, int position) {
		if( (getItemViewType(position) == TYPE_HEADER && position == 0)|| holder instanceof HeaderViewHolder){
			onBindHeaderViewHolder(holder, position);
		}else if((getItemViewType(position) == TYPE_FOOTER && position == getItemCount() - 1)|| holder instanceof FooterViewHolder){
			onBindFooterViewHolder(holder, position);
		}else{
			onBindItemViewHolder(holder, hasHeader() ? position -1 : position);
		}
		
	}

	protected void onBindItemViewHolder(ViewHolder vh, int position) {
		//需要去继承
	}
	private void onBindHeaderViewHolder(ViewHolder holder, int position) {
		// TODO Auto-generated method stub
		
	}
	private void onBindFooterViewHolder(ViewHolder holder, int position) {
		FooterViewHolder vh = (FooterViewHolder) holder;
		if(!loadMoreHadBg()){
			vh.loadMore.setBackgroundDrawable(null);
		}
		switch(getState()){
		case STATE_LOAD_MORE:
			vh.loadMore.setVisibility(View.VISIBLE);
			vh.progress.setVisibility(View.VISIBLE);
			vh.text.setVisibility(View.VISIBLE);
			vh.text.setText(_loadmoreTest);
			break;
		case STATE_NO_MORE:
			vh.loadMore.setVisibility(View.VISIBLE);
			vh.progress.setVisibility(View.GONE);
			vh.text.setVisibility(View.VISIBLE);
			vh.text.setText(_loadFinishText);
			break;
		case STATE_EMPTY_ITEM:
			vh.progress.setVisibility(View.GONE);
			vh.loadMore.setVisibility(View.GONE);
			vh.text.setVisibility(View.GONE);
			break;
		case STATE_NETWORK_ERROR:
			vh.loadMore.setVisibility(View.VISIBLE);
			vh.progress.setVisibility(View.GONE);
			vh.text.setVisibility(View.VISIBLE);
			if(TDevice.hasInternet()){
				vh.text.setText(AppContext.string(R.string.tip_load_date_error));
			}else{
				vh.text.setText(AppContext.string(R.string.tip_network_error));
			}
			break;
		default:
			vh.loadMore.setVisibility(View.GONE);
			vh.progress.setVisibility(View.GONE);
			vh.text.setVisibility(View.GONE);
		}
	}
	@Override
	public RecycleBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ViewHolder vh;
		if(viewType == TYPE_FOOTER){
			View v = getLayoutInflater(parent.getContext()).inflate(R.layout.v2_list_cell_footer, null);
			vh = new FooterViewHolder(viewType, v);
		}else if(viewType == TYPE_HEADER){
			if(mHeaderView == null){
				throw new RuntimeException("Header view is null");
			}
			vh = new HeaderViewHolder(viewType, mHeaderView);
		}else{
			final View itemView = OnCreateItemView(parent, viewType);
			if( itemView != null){
				if(mListener != null){
					itemView.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							OnItemClickListener lis = mListener.get();
							if(lis != null){
								lis.onItemClick(itemView);
							}
							
						}
					});
				}
				if(mLongListener != null){
					itemView.setOnLongClickListener(new OnLongClickListener() {
						@Override
						public boolean onLongClick(View v) {
							OnItemLongClickListener lis = mLongListener.get();
							if(lis != null){
								return lis.onItemLongClick(itemView);
							}
							return false;
						}
					});
				}
			}
			vh = onCreateItemViewHolder(itemView, viewType);
		}
		return vh;
	}
	protected abstract View OnCreateItemView(ViewGroup parent, int viewType);
	protected abstract ViewHolder onCreateItemViewHolder(View view, int viewType);
	protected LayoutInflater getLayoutInflater(Context context) {
		if( mInflater == null){
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		return mInflater;
	}
	protected boolean loadMoreHadBg() {
		return true;
	}
	
	public int getDataSize(){
		return _data.size();
	}
	public static class ViewHolder extends RecyclerView.ViewHolder{
		int viewType;
		public ViewHolder(int viewType, View itemView) {
			super(itemView);
			this.viewType = viewType;
		}
	}
	public static class HeaderViewHolder extends ViewHolder{
		public HeaderViewHolder(int viewType, View itemView) {
			super(viewType, itemView);
		}
	}
	public static class FooterViewHolder extends ViewHolder{
		public ProgressBar progress;
		public TextView text;
		public View loadMore;
		public FooterViewHolder(int viewType, View v){
			super(viewType, v);
			loadMore = v;
			progress = (ProgressBar) v.findViewById(R.id.progressbar);
			text = (TextView) v.findViewById(R.id.text);
		}
		
	}
}
