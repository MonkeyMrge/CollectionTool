package com.colletionUtils.Serialize;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonSerialize implements SerializeUtils {

	private static final TypeReference<Map<Object, Object>> MAP_TYPE_REF = new TypeReference<Map<Object, Object>>() {
	};

	@Override
	public String Obj2Json(Object object) {
		// 如果value的值为null则key值传递到服务端也不见了，用SerializerFeature.WriteMapNullValue来保持key的属性
		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
	}

	@Override
	public <T> Object Json2Obj(String jsonStr, Class<T> clazz) {
		return JSON.parseObject(jsonStr, clazz);
	}

	@Override
	public Map<Object, Object> Json2Map(String jsonStr) {
		return JSON.parseObject(jsonStr, MAP_TYPE_REF);
	}

	@Override
	public <T> List<T> Json2List(String jsonStr, TypeReference<List<T>> typeReference) {
		return JSON.parseObject(jsonStr, typeReference);
	}

}
