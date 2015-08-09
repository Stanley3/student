//package com.chejiawang.android.studentclient.order;
//
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.chejiawang.android.R;
//import com.chejiawang.android.bean.OrderRecord;
//import com.chejiawang.android.dao.PhotoDao;
//import com.chejiawang.android.globe.App;
//import com.chejiawang.android.globe.Logger;
//import com.chejiawang.android.network.BitmapManager;
//import com.chejiawang.android.network.NetWorkUtils;
//import com.chejiawang.android.network.Urls;
//import com.chejiawang.android.utils.StringUtils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.text.format.DateFormat;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class OrderListAdapter extends MyBaseAdapter implements OnScrollListener {
//	private String TAG = "OrderListAdapter";
//
//	private List<OrderRecord> list;
//	private Context context;
//	private ViewHolder viewHolder;
//	private ListView listView;
//
//	/**
//	 * 记录所有正在下载或等待下载的任务。
//	 */
//	private Set<BitmapWorkerTask> taskCollection;
//
//	/**
//	 * 第一张可见图片的下标
//	 */
//	private int mFirstVisibleItem;
//
//	/**
//	 * 一屏有多少张图片可见
//	 */
//	private int mVisibleItemCount;
//
//	/**
//	 * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
//	 */
//	private boolean isFirstEnter = true;
//
//	private PhotoDao photoDao;
//
//	// private BitmapManager bitmapManager;
//	public OrderListAdapter(List<OrderRecord> list, Context context,
//			ListView listView) {
//		super(BitmapFactory.decodeResource(context.getResources(),
//				R.drawable.photo_test));
//		this.listView = listView;
//		this.list = list;
//		this.context = context;
//		photoDao = new PhotoDao(context);
//		taskCollection = new HashSet<BitmapWorkerTask>();
//		listView.setOnScrollListener(this);
//	}
//
//	@Override
//	public int getCount() {
//		return list.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return list.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// if(convertView != null){
//		viewHolder = new ViewHolder();
//		convertView = View.inflate(context, R.layout.list_item, null);
//		viewHolder.coach_photo = (ImageView) convertView
//				.findViewById(R.id.iv_coach_photo);
//		viewHolder.coach_name = (TextView) convertView
//				.findViewById(R.id.tv_coach_name);
//		viewHolder.course_status = (TextView) convertView
//				.findViewById(R.id.tv_course_status);
//		viewHolder.reach_before = (TextView) convertView
//				.findViewById(R.id.tv_reach_before);
//		viewHolder.order_day = (TextView) convertView
//				.findViewById(R.id.tv_order_day);
//		viewHolder.order_time = (TextView) convertView
//				.findViewById(R.id.tv_order_time);
//		viewHolder.online_status = (ImageView) convertView
//				.findViewById(R.id.iv_online_status);
//		convertView.setTag(viewHolder);
//		// }else{
//		// viewHolder = (ViewHolder) convertView.getTag();
//		// }
//		viewHolder.coach_name.setText(list.get(position).getCoach_name());
//		String course = list.get(position).getCourse_status();
//		if (course == "2") {
//			viewHolder.course_status.setText("科目二教练");
//		} else {
//			viewHolder.course_status.setText("科目三教练");
//		}
//		viewHolder.reach_before.setText("请您提前到达练车场");
//		viewHolder.order_day.setText(list.get(position).getOrder_time());
//		viewHolder.order_time.setText(list.get(position)
//				.getTraining_start_time());
//		// 获取URL
//		String coach_id = list.get(position).getCoach_id();
//		String imgUrl = Urls.PHOTOHEAD + coach_id;
//		Log.e(TAG, imgUrl);
//		if (StringUtils.isEmpty(imgUrl)
//				|| (list.get(position).getHavePhoto() == "0")) {
//			viewHolder.coach_photo.setImageResource(R.drawable.photo_test);
//		} else {
//			if (!imgUrl.contains("http")) {
//				imgUrl = Urls.HTTP + Urls.HOST + "/" + imgUrl;
//			}
//			viewHolder.coach_photo.setTag(imgUrl);
//			this.loadBitmap(imgUrl, viewHolder.coach_photo);
//		}
//		return convertView;
//	}
//
//	private class ViewHolder {
//		ImageView coach_photo;
//		TextView coach_name;
//		TextView course_status;
//		TextView reach_before;
//		TextView order_day;
//		TextView order_time;
//		ImageView online_status;
//	}
//
//	/**
//	 * 加载Bitmap对象。此方法会在LruCache中检查所有屏幕中可见的ImageView的Bitmap对象，
//	 * 如果发现任何一个ImageView的Bitmap对象不在缓存中，就会开启异步线程去下载图片。
//	 * 
//	 * @param firstVisibleItem
//	 *            第一个可见的ImageView的下标
//	 * @param visibleItemCount
//	 *            屏幕中总共可见的元素数
//	 */
//	private void loadBitmaps(int firstVisibleItem, int visibleItemCount) {
//		try {
//			for (int i = firstVisibleItem; i < firstVisibleItem
//					+ visibleItemCount; i++) {
//				String coach_id = list.get(i).getCoach_id();
//				String imageUrl = Urls.PHOTOHEAD + coach_id;
//				Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
//				if (bitmap != null) {
//					ImageView imageView = (ImageView) listView
//							.findViewWithTag(imageUrl);
//					if (imageView != null && bitmap != null) {
//						imageView.setImageBitmap(bitmap);
//					}
//				} else {
//					bitmap = photoDao.findPhoto(imageUrl);
//					if (bitmap != null) {
//						ImageView imageView = (ImageView) listView
//								.findViewWithTag(imageUrl);
//						if (imageView != null && bitmap != null) {
//							imageView.setImageBitmap(bitmap);
//						}
//					} else {
//						Logger.e(TAG, imageUrl);
//						BitmapWorkerTask task = new BitmapWorkerTask();
//						Logger.e(TAG, i + " " + i);
//						taskCollection.add(task);
//						task.execute(imageUrl);
//					}
//				}
//
//				// if (bitmap == null) {
//				//
//				// bitmap = photoDao.findPhoto(imageUrl);
//				// if(bitmap == null){
//				// Logger.e(TAG, imageUrl);
//				// BitmapWorkerTask task = new BitmapWorkerTask();
//				// Logger.e(TAG, i + " " + i);
//				// taskCollection.add(task);
//				// task.execute(imageUrl);
//				// }
//				// } else {
//				// ImageView imageView = (ImageView) listView
//				// .findViewWithTag(imageUrl);
//				// if (imageView != null && bitmap != null) {
//				// imageView.setImageBitmap(bitmap);
//				// }
//				// }
//			}
//		} catch (Exception e) {
//			Logger.e(TAG, "下载异常");
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 取消所有下载的任务
//	 */
//	private void cancelAllTasks() {
//		if (taskCollection != null) {
//			for (BitmapWorkerTask task : taskCollection) {
//				task.cancel(false);
//			}
//		}
//	}
//
//	@Override
//	public void onScrollStateChanged(AbsListView view, int scrollState) {
//		// 仅当GridView静止时才去下载图片，GridView滑动时取消所有正在下载的任务
//		Logger.e(TAG, "OnScrollStateChanged");
//		if (scrollState == SCROLL_STATE_IDLE) {
//			loadBitmaps(mFirstVisibleItem, mVisibleItemCount);
//		} else {
//			cancelAllTasks();
//		}
//
//	}
//
//	@Override
//	public void onScroll(AbsListView view, int firstVisibleItem,
//			int visibleItemCount, int totalItemCount) {
//		mFirstVisibleItem = firstVisibleItem;
//		mVisibleItemCount = visibleItemCount;
//		Logger.e(TAG, firstVisibleItem + " " + visibleItemCount);
//		// 下载的任务应该由onScrollStateChanged里调用，但首次进入程序时onScrollStateChanged并不会调用，
//		// 因此在这里为首次进入程序开启下载任务。
//		if (isFirstEnter && visibleItemCount > 0) {
//			loadBitmaps(firstVisibleItem, visibleItemCount);
//			isFirstEnter = false;
//		}
//
//	}
//
//	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
//
//		/**
//		 * 图片的URL地址
//		 */
//		private String imageUrl;
//
//		protected Bitmap doInBackground(String... params) {
//			imageUrl = params[0];
//			// 在后台开始下载图片
//			Logger.e("TASK", "我在下载。。。");
//			Bitmap bitmap = NetWorkUtils.downloadBitmap(params[0]);
//			if (bitmap != null) {
//				// 图片下载完成后缓存到LrcCache中
//				addBitmapToMemoryCache(params[0], bitmap);
//				photoDao.addPhoto(params[0], bitmap);
//			}
//			return bitmap;
//		}
//
//		@Override
//		protected void onPostExecute(Bitmap bitmap) {
//			super.onPostExecute(bitmap);
//			// 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
//			ImageView imageView = (ImageView) listView
//					.findViewWithTag(imageUrl);
//			if (imageView != null && bitmap != null) {
//				imageView.setImageBitmap(bitmap);
//			}
//			taskCollection.remove(this);
//		}
//	}
//
//	/**
//	 * 将一张图片存储到LruCache中。
//	 * 
//	 * @param key
//	 *            LruCache的键，这里传入图片的URL地址。
//	 * @param bitmap
//	 *            LruCache的键，这里传入从网络上下载的Bitmap对象。
//	 */
//	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
//		if (getBitmapFromMemoryCache(key) == null) {
//			getmMemoryCache().put(key, bitmap);
//		}
//	}
//}
