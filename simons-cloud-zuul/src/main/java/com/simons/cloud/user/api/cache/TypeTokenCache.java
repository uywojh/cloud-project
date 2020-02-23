package com.simons.cloud.user.api.cache;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class TypeTokenCache {
	public static final Type LISTTOKEN = new TypeToken<ArrayList<String>>() {}.getType();
	
	public static final Type MAPLISTTOKEN = new TypeToken<ArrayList<Map<String,Object>>>() {}.getType();
}
