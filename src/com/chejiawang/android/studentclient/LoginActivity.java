package com.chejiawang.android.studentclient;

import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.studentclient.app.AppContext;
import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.bean.StudentLoginSuccessInfo;
import com.chejiawang.android.studentclient.utils.StringUtils;
import com.chejiawang.android.studentclient.utils.UIHelper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private String Tag = "LoginActivity";
	/**
	 * 用户名
	 */
	private EditText et_username;
	/**
	 * 密码
	 */
	private EditText et_password;
	/**
	 * 登录按钮
	 */
	private Button bt_login;
	/**
	 * 返回按钮
	 */
	private Button bt_back;
	/**
	 * 记住密码
	 */
	private CheckBox cb_remeber;
	private InputMethodManager imm;

	private AppContext context;
	/** 进度条 **/
	private ProgressDialog progressDialog;

	private static final int SUCCESS = 0;
	private static final int FAILURE = 1;
	private static final int CODESUCCESS = 1;
	private static final int CODEPASSWORDERR0R = 2;
	private static final int CODESERVERERROR = 3;

	private long exitStartTime = System.currentTimeMillis();

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			int id = msg.what;
			switch (id) {
			case SUCCESS:
				UIHelper.startActivityUtil(LoginActivity.this, MainGuideActivity.class);
				overridePendingTransition(R.anim.bottom_up, R.anim.up_bottom);
				finish();
				break;
			case FAILURE:

				break;
			}
			progressDialog.dismiss();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		initView();
		bt_login.setOnClickListener(this);
		context = AppContext.instance();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		bt_login = (Button) findViewById(R.id.bt_login);
		bt_back = (Button) findViewById(R.id.bt_back);
		cb_remeber = (CheckBox) findViewById(R.id.cb_remeber);

		String username = context.getProperty("username");
		String password = context.getProperty("password");
		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
			et_username.setText(username);
			et_password.setText(password);
			cb_remeber.setChecked(true);
		}

	}

	/**
	 * 所有按钮的点击事件
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.bt_login:
			Logger.e(Tag, "登录");
			// //隐藏软键盘
			// imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			String username = et_username.getText().toString();
			String password = et_password.getText().toString();
			Boolean isRemember = cb_remeber.isChecked();
			if (StringUtils.isEmpty(username)) {
				UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_login_user_name_null));
				return;
			}
			if (StringUtils.isEmpty(password)) {
				UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_login_password_null));
				return;
			}
			if (isRemember) {
				context.setProperty("username", username);
				context.setProperty("password", password);
			}
			progressDialog = ProgressDialog.show(this, "进度", "正在认证用户名和...", true);
			// login(userName, password, isRemember);
			AppContext.login(username, password, getResponseHandler());

			break;
		case R.id.bt_back:
			break;
		}
	}

	public AsyncHttpResponseHandler getResponseHandler() {
		return new MyHttpHandler(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		long exitFinishTime = System.currentTimeMillis();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (exitFinishTime - exitStartTime > 2000) {
				Toast.makeText(this, "再按一次返回键关闭程序", Toast.LENGTH_SHORT).show();
				;
				exitStartTime = exitFinishTime;
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void parseLoginInfo(byte[] responseBytes, Header[] header) {
		ResultData resultData = null;
		String result = null;
		try {
			result = StringUtils.inStream2String(new ByteArrayInputStream(responseBytes));

			Logger.e(Tag, result);
			// Logger.e(Tag, result);
			resultData = ResultData.parseJson(result);
		} catch (Exception e) {
			Logger.e(Tag, result);
			e.printStackTrace();
			this.loginError();
		}
		if (resultData == null) {
			this.loginError();
		}
		int code = Integer.parseInt(resultData.getCode());
		switch (code) {
		case CODESUCCESS:
			// 1. 获取教练信息
			// Logger.e(Tag, result);
			StudentLoginSuccessInfo info = StudentLoginSuccessInfo.parseJson(result);
			// 2.保存教练信息
			if (info != null) {
				AppContext.saveInfo("student_info", info);
			} else {
				this.loginError();
				return;
			}
			Message msg = new Message();
			msg.what = SUCCESS;
			this.handler.sendMessage(msg);
			break;
		case CODEPASSWORDERR0R:
			this.loginError();
			break;
		case CODESERVERERROR:
			this.loginError();
			break;

		}

	}

	public void loginError() {
		Toast.makeText(context.getApplicationContext(), "用户名或密码错误！", Toast.LENGTH_SHORT).show();
		Message msg = new Message();
		msg.what = FAILURE;
		this.handler.sendMessage(msg);
	}

	public static class MyHttpHandler extends AsyncHttpResponseHandler {

		private WeakReference<LoginActivity> mInstance;

		public MyHttpHandler(LoginActivity instance) {
			super();
			mInstance = new WeakReference<LoginActivity>(instance);
		}

		@Override
		public void onSuccess(int i, Header[] header, byte[] responseBytes) {
			Logger.i("LoginActivity", "onSuccess");
			LoginActivity activity = mInstance.get();
			if (activity != null) {
				activity.parseLoginInfo(responseBytes, header);
			}
		}

		@Override
		public void onFailure(int i, Header[] header, byte[] responseBytes, Throwable throwable) {
			LoginActivity activity = mInstance.get();
			if (activity != null) {
				activity.loginError();
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 登录返回的数据
	 * 
	 * @author Administrator
	 *
	 */
	public static class ResultData {
		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public static ResultData parseJson(String date) {
			if (TextUtils.isEmpty(date)) {
				return null;
			}
			ResultData result = new ResultData();

			try {
				JSONObject jsonObject = new JSONObject(date);
				result.setCode(jsonObject.getString("code"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return result;
		}

	}
}
