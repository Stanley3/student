package com.chejiawang.android.studentclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import android.content.Context;

public class AppConfig {
	
	private Context mContext;
	private final static String APP_CONFIG = "config";
	private static AppConfig appConfig;
	
	public static AppConfig getAppConfig(Context context)
	{
		if(appConfig == null){
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}

	public String get(String key) {
		Properties props = get();
		return (props != null) ? props.getProperty(key) : null;
	}

	public Properties get() {
		FileInputStream fis = null;
		Properties props = new Properties();
		
		try {
			//读取files目录下的config
			//读取app_config目录下的config
			File dirConf = mContext.getDir(APP_CONFIG , Context.MODE_PRIVATE);
			fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fis.close();
			}catch(Exception e){
			}
		}
		return props;
	}
	
}
