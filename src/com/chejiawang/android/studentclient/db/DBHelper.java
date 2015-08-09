package com.chejiawang.android.studentclient.db;



import com.chejiawang.android.studentclient.app.Logger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库类
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	private String TAG = DBHelper.class.getName();
	public static final String CACHE_TABLE = "app_cache";
	private static String initSQL;
	private static String upgradeSQL;
	private static String dbName = "app_cache.db";
	static{
		initSQL = "CREATE TABLE IF NOT EXISTS app_cache (id INTEGER PRIMARY KEY, key NVARCHAR(255), file NVARCHAR(255), size NUMERIC, status INTEGER, time NUMERIC, expire NUMERIC);";
		upgradeSQL = initSQL;
	}
	public DBHelper(Context context, int version, String name, String ver, String ver2) {
		super(context, name, null, version);
		if(name == null || name.trim().equals("")){
			name = dbName;
		}
		if(ver != null && !ver.trim().equals("")){
			initSQL = new StringBuilder(String.valueOf(initSQL)).append(ver).toString();
			upgradeSQL = initSQL;
		}
		if(ver2 != null && !ver2.trim().equals("")){
			upgradeSQL = new StringBuilder(String.valueOf(upgradeSQL)).append(ver2).toString();
		}
	}
	/**
	 * 数据库创建时候调用
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Logger.e(TAG, "initialize dataBase");
		String[] as = initSQL.split(";");
		for(int i = 0; i < as.length; i++){
			String sql = as[i] + ";";
			Logger.e(TAG, "exeSQL: " + sql);
			db.execSQL(sql);
		}
	}
	/**
	 * 数据库升级时候调用
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String[] as = upgradeSQL.split(";");
		for(int i = 0; i < as.length; i++){
			String sql = as[i] + ";";
			Logger.e(TAG, "exeSQL: " + sql + ";");
			db.execSQL(sql);
		}
		onCreate(db);
	}

}
