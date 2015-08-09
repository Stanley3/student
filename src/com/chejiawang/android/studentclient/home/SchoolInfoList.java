package com.chejiawang.android.studentclient.home;

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
import com.chejiawang.android.studentclient.bean.SchoolInfoBasedDistance;
import com.chejiawang.android.studentclient.utils.ListEntity;
import com.chejiawang.android.studentclient.utils.StringUtils;

public class SchoolInfoList extends Entity implements ListEntity {
	public static String TAG = "HaveOrderList";
	private List<SchoolInfoBasedDistance> schoolList = new ArrayList<SchoolInfoBasedDistance>();

	public List<SchoolInfoBasedDistance> getHaveOrderList() {
		return schoolList;
	}

	public void setHaveOrderList(List<SchoolInfoBasedDistance> haveOrderList) {
		this.schoolList = haveOrderList;
	}

	public static SchoolInfoList parse(InputStream inputStream) throws Exception {
		String result = StringUtils.inStream2String(inputStream);

		Logger.e(TAG, "Response:" + result);
		if (result == null) {
			return null;
		}
		SchoolInfoList list = new SchoolInfoList();
		try {
			JSONObject resultObject = new JSONObject(result);
			JSONArray jsonArry = resultObject.getJSONArray("data");
			for (int i = 0; i < jsonArry.length(); i++) {
				JSONObject recordJson = jsonArry.getJSONObject(i);
				SchoolInfoBasedDistance record = new SchoolInfoBasedDistance();
				
				/***********************
				 *	private Integer school_id;
		private String school_name;
	private String school_address;
	private String school_type;
	private Integer device_2_status;
	private Integer device_3_status;
	private Integer subject_2_number;
	private Integer subject_3_number;
	private Integer vehicle_number;
	private Integer register_number;
	private Double distance;
	private  Double longitude; //经度
	private Double latitude; //纬度 
				 */
				record.setSchool_name(recordJson.getString("school_name"));
				record.setSchool_id(recordJson.getInt("school_id"));
			//	record.setSchool_address(recordJson.getString("school_address"));
			//	record.setSchool_type(recordJson.getString("school_type"));
			//	record.setDevice_2_status(recordJson.getInt("device_2_status"));
			//	record.setDevice_3_status(recordJson.getInt("device_3_status"));
			//	record.setSubject_2_number(recordJson.getInt("subject_2_number"));
			//	record.setSubject_3_number(recordJson.getInt("subject_3_number"));
			//	record.setVehicle_number(recordJson.getInt("vehicle_number"));
			//	record.setRegister_number(recordJson.getInt("register_number"));
				record.setDistance(recordJson.getDouble("distance"));
			//	record.setLongitude(recordJson.getDouble("longitude"));
			//	record.setLatitude(recordJson.getDouble("latitude"));
				
				list.getHaveOrderList().add(record);
			}

		} catch (JSONException e) {
			Logger.e(TAG, "parse the distance error!");
			e.printStackTrace();
			return null;
		}

		return list;

	}

	@Override
	public List<?> getList() {

		return schoolList;
	}

	@Override
	public Map<?, ?> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
