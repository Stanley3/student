package com.chejiawang.android.studentclient.cache;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.db.DBHelper;
import com.chejiawang.android.studentclient.utils.FileUtils;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.format.DateFormat;

/**
 * 管理缓存
 * 
 * @author 孙晓雨
 *
 */
public class CacheManager {
	private static final String TAG = CacheManager.class.getSimpleName();

	public static final int TYPE_INTERNAL = 1;// 存储在内部存储卡上的标志位
	public static final int TYPE_EXTERNAL = 2;// 存储在外部存储卡上的标志位

	private static ArrayList<CacheTask> cacheTasks = new ArrayList<CacheTask>();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");// 格式化Date类型数据
	private static Thread cacheThread;// 缓存线程，长期在后台运行
	public static File cacheDirInternal;// 内部缓存文件
	public static File cacheDirExternal;// 外部缓存文件
	public static File cachePhotoDirExternal;
	public static File tempCacheDirExternal;
	public static File tempCacheDirInternal;
	private static DBHelper dbHelper;
	static {
		cacheThread = new Thread() {
			@Override
			public void run() {
				synchronized (cacheThread) {
					while (true) {
						Logger.e(TAG, "cache task start");
						if (cacheTasks != null && !cacheTasks.isEmpty()
								&& cacheTasks.size() > 0) {
							saveCache(cacheTasks.get(0));
							if (cacheTasks != null && !cacheTasks.isEmpty()) {
								cacheTasks.remove(0);
							}
						} else {
							try {
								wait();
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		};
		cacheThread.start();
	}

	/**
	 * 构造函数
	 */
	public CacheManager() {

	}

	/**
	 * 初始化数据库,创建所有的缓存文件目录
	 * 
	 * @param baseFolder
	 * @param context
	 * @param dbHelper
	 */
	public static void initCacheDir(String baseFolder, Context context,
			DBHelper dbHelper) {
		int memoryClass = ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
		Cache.memoryCacheSize = (0x100000 * memoryClass) / 10;
		CacheManager.dbHelper = dbHelper;
		cacheDirInternal = new File(context.getCacheDir(), "app");
		// 创建缓存文件
		File file = new File(Environment.getExternalStorageDirectory(),
				baseFolder + "/cache");
		cacheDirExternal = file;
		cachePhotoDirExternal = file;
		cacheDirExternal = new File(cachePhotoDirExternal, "app");
		tempCacheDirExternal = new File(cacheDirExternal, "temp");
		tempCacheDirInternal = new File(cacheDirInternal, "temp");
		if (!cacheDirInternal.exists() || !cacheDirInternal.isDirectory()) {
			cacheDirInternal.mkdirs();
		}
		if (!tempCacheDirInternal.exists()
				|| !tempCacheDirInternal.isDirectory()) {
			tempCacheDirInternal.mkdirs();
		}
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			if (!cacheDirExternal.exists() || !cacheDirExternal.isDirectory()) {
				cacheDirExternal.mkdirs();
			}
			if (!cachePhotoDirExternal.exists()
					|| !cachePhotoDirExternal.isDirectory()) {
				cachePhotoDirExternal.mkdirs();
			}
			if (!tempCacheDirExternal.exists()
					|| !tempCacheDirExternal.isDirectory()) {
				tempCacheDirExternal.mkdirs();
			}
		}
	}

	/**
	 * 保存缓存
	 * 
	 * @param task
	 */
	public static synchronized void saveCache(CacheTask task) {
		File file = saveByteToFile(task);
		if (file != null && file.exists()) {
			String key = getCacheKey(task.getKey());

			try {
				long fileSize = file.length();
				long currentTime = System.currentTimeMillis();
				ContentValues cv = new ContentValues();
				cv.put("key", key);
				cv.put("file", file.getAbsolutePath());
				cv.put("size", Long.valueOf(fileSize));
				cv.put("status", Integer.valueOf(task.getType()));
				cv.put("time", Long.valueOf(currentTime));
				if (task.getExpire() <= 0L) {
					cv.put("expire", Long.valueOf(currentTime));
				} else {
					cv.put("expire", Long.valueOf(currentTime + 1000L
							+ task.getExpire()));
				}
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				while (db.isDbLockedByCurrentThread()) {
					Thread.sleep(10);
				}
				Cache cache = null;
				Cursor cursor = db.rawQuery(
						"select * from app_cache where key = '" + key + "'",
						null);
				if (cursor != null && cursor.getCount() > 0
						&& cursor.moveToNext()) {
					cache = new Cache();
					cache.parse(cursor);
				}
				if (cursor != null) {
					cursor.close();
				}
				db = dbHelper.getWritableDatabase();
				while (db.isDbLockedByCurrentThread()) {
					Thread.sleep(10);
				}
				if (cache == null) {
					db.insert("app_cache", null, cv);
				} else {
					cv.put("id", Long.valueOf(cache.getId()));
					db.update("app_cache", cv, "id=?",
							new String[] { Long.toString(cache.getId()) });
				}
			} catch (Exception e) {
				Logger.e(TAG, "set cache date failed: " + key);
			}
		}
	}

	/**
	 * 将task中的byte 存起来
	 * 
	 * @param task
	 * @return
	 */
	public static File saveByteToFile(CacheTask task) {
		if (task == null) {
			return null;
		}
		// 如果存储在SD卡上，但没有装载SD卡 直接返回
		if (Environment.getExternalStorageState() != null
				&& !Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)
				&& task.getType() == TYPE_EXTERNAL) {
			return null;
		}
		// 如果缓存任务数据不完整，直接返回
		if (task.getKey() == null || task.getContent() == null
				|| task.getContent().length <= 0) {
			return null;
		}
		String fileName = getCacheFileName(task.getKey());
		Logger.e(TAG, "set cache date: cache name :"  + fileName);
		if (fileName == null) {
			Logger.e(TAG, "set cache date: cache name is null");
		}
		String dateName = dateFormat.format(new Date());

		File cacheFile = null;
		File tmpCacheDir = null;
		File cacheDir = null;
		if (task.getType() == TYPE_INTERNAL) {
			if (cacheDirInternal != null && cacheDirInternal.exists()
					&& tempCacheDirInternal != null
					&& tempCacheDirInternal.exists()) {
				// 用当前时间生成一个缓存文件
				cacheDir = new File(cacheDirInternal, dateName);
				cacheFile = new File(cacheDir, fileName);
				tmpCacheDir = new File(tempCacheDirInternal, fileName);
			} else {
				Logger.e(TAG,
						"set cache date: cache internal dir is not exists");
				return null;
			}
		} else if (task.getType() == TYPE_EXTERNAL) {
			if (cacheDirExternal != null && cacheDirExternal.exists()
					&& tempCacheDirExternal != null
					&& tempCacheDirExternal.exists()) {
				// 用当前时间生成一个缓存文件
				cacheDir = new File(cacheDirExternal, dateName);
				cacheFile = new File(cacheDir, fileName);
				tmpCacheDir = new File(tempCacheDirExternal, fileName);
			} else {
				Logger.e(TAG,
						"set cache date: cache internal dir is not exists");
				return null;
			}
		}
		if (cacheDir != null && !cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		try {
			// 先将文件写入临时缓存
			FileUtils.writeFile(tmpCacheDir, task.getContent());
			if (tmpCacheDir != null && tmpCacheDir.exists()
					&& tmpCacheDir.isFile()) {
				if (cacheFile != null && cacheFile.exists()
						&& cacheFile.isFile()) {
					cacheFile.delete();
				}
				FileUtils.move(tmpCacheDir, cacheFile);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cacheFile;
	}

	/**
	 * 根据名字生成UUID
	 * 
	 * @param key
	 * @return
	 */
	private static String getCacheFileName(String key) {
		return getCacheKey(key);
	}

	/**
	 * 生成唯一的名字
	 * 
	 * @param key
	 * @return
	 */
	public static String getCacheKey(String key) {
		if (key != null && key.trim().length() > 0) {
			return UUID.nameUUIDFromBytes(key.getBytes()).toString();
		}
		return null;
	}

	/**
	 * 删除所有缓存
	 */
	public static synchronized void clearAllCache() {
		clearAllCache(TYPE_INTERNAL);
		clearAllCache(TYPE_EXTERNAL);
	}

	public static synchronized void clearAllCache(int type) {
		dbHelper.getWritableDatabase().delete("app_cache", "status=" + type,
				null);
		clearTempCache(type);
	}

	public static synchronized void clearTempCache(int type) {

	}

	public static byte[] getCache(String key) {
		Cache cache = getCacheFile(key);
		if (cache != null && cache.getExpire() > System.currentTimeMillis()
				&& cache.getFile() != null && cache.getFile().exists() && cache.getFile().isFile()) {
//		if (cache != null && cache.getFile() != null){
			try {
				byte[] data = FileUtils.readFileToByte(cache.getFile());
				Logger.e(TAG, "get cache data: " + key);
				return data;
			} catch (Exception e) {
				e.printStackTrace();
				Logger.e(TAG, "get cache data ignore expire fail: " + key);
			}
		}else{
			Logger.e(TAG, "get cache data fail: " + key);
		}
		return null;
	}

	/**
	 * 获取缓存
	 * 
	 * @param name
	 * @return
	 */
	public static Cache getCacheFile(String name) {
		Cache cache = null;
		String key = getCacheKey(name);
		if (dbHelper != null) {
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from app_cache where key = '"
					+ key + "'", null);
			if (cursor != null && cursor.getCount() > 0) {
				if (cursor.moveToNext()) {
					try {
						cache = new Cache();
						cache.parse(cursor);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					Logger.e(TAG, "cache cursor is no data with:" + key);
				}
			} else {
				Logger.e(TAG, "cache cursor is empty with key:" + key);
			}
			if (cursor != null) {
				cursor.close();
			}
		} else {
			Logger.e(TAG, "db helper is null");
		}
		Logger.e(TAG, "get cache file :" + cache);
		return cache;
	}

	/**
	 * 缓存任务类
	 * 
	 * @author Administrator
	 *
	 */
	private static class CacheTask {
		private byte[] content;
		private long expire;
		private String key;
		private int type;// 缓存是内部的还是外部的缓存

		public byte[] getContent() {
			return content;
		}

		public void setContent(byte[] content) {
			this.content = content;
		}

		public long getExpire() {
			return expire;
		}

		public void setExpire(long expire) {
			this.expire = expire;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

	}

	public static void setCache(String key, byte[] content,
			long expire, int type) {
		Logger.e(TAG, "add cache task: " + key + "expire:" + expire );
		CacheTask task = new CacheTask();
		task.setKey(key);
		task.setContent(content);
		task.setExpire(expire);
		task.setType(type);
		cacheTasks.add(task);
		synchronized (cacheThread) {
			cacheThread.notify();
		}
	}
}
