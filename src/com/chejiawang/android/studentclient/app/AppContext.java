package com.chejiawang.android.studentclient.app;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import com.chejiawang.android.studentclient.cache.CacheManager;
import com.chejiawang.android.studentclient.db.DBHelper;
import com.chejiawang.android.studentclient.network.ApiHttpClient;
import com.chejiawang.android.studentclient.utils.StringUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppContext extends BaseApplication {
	
	public static HashMap<String, Object> standInfo = new HashMap<String, Object>();
	
	private static AppContext instance;
	private static final String KEY_APP_ID = "key_app_id";
	private static final String KEY_COOKIE = "key_cookie";
	
	public static final String PART_URL = "studentLoginInfo/login";
	
	public static AppContext instance(){
		return instance;
	}
	@Override
	public void onCreate() {
		super.onCreate();
        //注册App异常崩溃处理器
        //Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
       CacheManager.initCacheDir(Contants.CACHE_DIR, getApplicationContext(),new DBHelper(getApplicationContext(), 1, "app_cache", null, null));
       instance = this;
       AsyncHttpClient client = new AsyncHttpClient();
       PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
       client.setCookieStore(myCookieStore);
       ApiHttpClient.setHttpClient(client);
      // ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));
       
       //初始化ImageLoader
       initImageLoader(getApplicationContext());
      // Fresco.initialize(this);
       
	}
	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}
	public static String getProperty(String key) {
		return getPreferences().getString(key, null);
	}

	/**
	 * 保存对象
	 * @param ser
	 * @param file
	 * @return
	 */
	public boolean saveObject(Serializable serial, String file){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = openFileOutput(file, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(serial);
			oos.flush();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void saveInfo(String name, Object key){
		AppContext.standInfo.put(name, key);
	}
	public static Object getInfo(String name){
		return AppContext.standInfo.get(name);
	}
	/**
	 * 反序列化读取对象
	 */
	public Serializable readObject(String file){
		if(!isExistDataCache(file)){
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			
			if(ex instanceof InvalidClassException){
				File data = getFileStreamPath(file);
				data.delete();
			}
		}finally{
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 判断缓存是否存在
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile) {
		boolean exist = false;
		File data = getFileStreamPath(cachefile);
		if(data.exists()){
			exist = true;
		}
		return exist;
	}
	/**
	 * 判断网络连接是否正常
	 * @return
	 */
	public boolean isNetworkConnected(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}
	/**
	 * 判断缓存数据是否可读 true 可读 false 不可读
	 * @param cachefile
	 * @return
	 */
	public boolean isReadDataCache(String cachefile){
		return readObject(cachefile) != null;
	}
	
//	/**
//	 * 登录验证
//	 * @param userName
//	 * @param password
//	 * @return
//	 */
//	public CoachBasicInfo loginVerify(String userName, String password) throws AppException {
//		return  ApiClient.login(this, userName, password);
//	}
	public void setProperty(String key, String value) {
		Editor editor = getPreferences().edit();
		editor.putString(key, value);
		apply(editor);	
	}
	public String getAppId() {
		String uniqueID = getProperty(KEY_APP_ID );
		if(StringUtils.isEmpty(uniqueID)){
			uniqueID = UUID.randomUUID().toString();
			setProperty(KEY_APP_ID, uniqueID);
		}
		return uniqueID;
	}
	
	/**
	 * 获取cookie
	 */
	public static String getCookie(){
		return getProperty(KEY_COOKIE);
	}
	public static void initImageLoader( Context context){
		
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
		
	}
	public static String string(int id) {
		return _resource.getString(id);
	}
	public static String string(int id, Object...args){
		return _resource.getString(id, args);
	}

	public static void login(String username, String password, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("username", username);
		params.put("password", password);
		params.put("source", "android");
		ApiHttpClient.post(PART_URL, params, handler);
	}
}
