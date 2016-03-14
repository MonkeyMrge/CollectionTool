package com.colletionUtils.Serialize;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.TypeReference;

public interface SerializeUtils {
	/**
	 * Object-->Json
	 * 
	 * @param object
	 * @return
	 */
	public String Obj2Json(Object object);

	/**
	 * Json-->Object
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public <T> Object Json2Obj(String jsonStr, Class<T> clazz);

	/**
	 * Json-->HashMap
	 * 
	 * @param jsonStr
	 * @return
	 */
	public Map<Object, Object> Json2Map(String jsonStr);

	/**
	 * Json-->List
	 * 
	 * @param jsonStr
	 * @param typeReference
	 * @return
	 */
	public <T> List<T> Json2List(String jsonStr, TypeReference<List<T>> typeReference);
}
