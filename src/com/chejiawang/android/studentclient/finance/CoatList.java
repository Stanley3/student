package com.chejiawang.android.studentclient.finance;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.bean.DisplayStudentDepositReocrd;
import com.chejiawang.android.studentclient.bean.Entity;
import com.chejiawang.android.studentclient.utils.ListEntity;
import com.chejiawang.android.studentclient.utils.StringUtils;

public class CoatList extends Entity implements ListEntity {
	public static String TAG = "CoatList";
	private List<DisplayStudentDepositReocrd> recordList = new ArrayList<DisplayStudentDepositReocrd>();

	public List<DisplayStudentDepositReocrd> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<DisplayStudentDepositReocrd> recordList) {
		this.recordList = recordList;
	}

	public static CoatList parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);

		Logger.e(TAG, "Response:" + result);
		if (result == null) {
			return null;
		}
		CoatList list = new CoatList();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArry = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				DisplayStudentDepositReocrd record = new DisplayStudentDepositReocrd();
				record.setDeposit_date(recordJson.getString("deposit_date"));
				record.setDeposit_address(recordJson.getString("deposit_address"));
				record.setDeposit_type(recordJson.getInt("deposit_type"));
				list.recordList.add(record);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return list;

	}

	@Override
	public List<?> getList() {

		return recordList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
