package com.chejiawang.android.studentclient.order;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chejiawang.android.studentclient.app.Logger;
import com.chejiawang.android.studentclient.bean.Entity;
import com.chejiawang.android.studentclient.bean.MyOrderRecord;
import com.chejiawang.android.studentclient.utils.ListEntity;
import com.chejiawang.android.studentclient.utils.StringUtils;

public class HaveOrderList extends Entity implements ListEntity {
	public static String TAG = "HaveOrderList";
	private List<MyOrderRecord> haveOrderList = new ArrayList<MyOrderRecord>();

	public List<MyOrderRecord> getHaveOrderList() {
		return haveOrderList;
	}

	public void setHaveOrderList(List<MyOrderRecord> haveOrderList) {
		this.haveOrderList = haveOrderList;
	}

	public static HaveOrderList parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);

		Logger.e(TAG, "Response:" + result);
		if (result == null) {
			return null;
		}
		HaveOrderList list = new HaveOrderList();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArry = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				MyOrderRecord record = new MyOrderRecord();
				record.setCoach_id(recordJson.getInt("coach_id"));
				record.setCoach_name(recordJson.getString("coach_name"));

				record.setTraining_start_time(recordJson.getString("training_start_time"));
				record.setTraining_end_time(recordJson.getString("training_end_time"));
				record.setOrder_status(recordJson.getInt("order_status"));
				list.getHaveOrderList().add(record);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return list;

	}

	@Override
	public List<?> getList() {

		return haveOrderList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
