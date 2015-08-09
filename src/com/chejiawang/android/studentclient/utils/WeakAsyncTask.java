package com.chejiawang.android.studentclient.utils;

import java.lang.ref.WeakReference;


import android.os.AsyncTask;

public abstract class WeakAsyncTask<Params, Progress, Result, WeakTarget> extends AsyncTask<Params, Progress, Result> {

	protected WeakReference<WeakTarget> mTarget;
	private String TAG = "WeakAsyncTask" ;
	public WeakAsyncTask(WeakTarget target) {
		super();
		this.mTarget = new WeakReference<WeakTarget>(target);
	}
	
	public final void onPreExecute() {
		final WeakTarget target = mTarget.get();
		if(target != null){
			this.onPreExecute(target);
		}
	};


	protected void onPreExecute(WeakTarget target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Result doInBackground(Params... params) {
		
		final WeakTarget target = mTarget.get();
		if(target != null){
			//此处发现重大错误已经修正，忘记return
			return this.doInBackground(target, params);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Result result) {
	//	Logger.e(TAG , "parse cache: " + "data:" + result);
		final WeakTarget target = mTarget.get();
		if(target != null){
			this.onPostExecute(target, result);
		}
	}
	protected void onPostExecute(WeakTarget target, Result result) {
		// TODO Auto-generated method stub
		
	}

	protected abstract Result doInBackground(WeakTarget target, Params... params);

}
