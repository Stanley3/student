package com.chejiawang.android.studentclient.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ListEntity extends Serializable {

	public List<?> getList();
	public Map<?, ?> getMap();
}
