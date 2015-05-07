package cn.appm.base.wrapper;

import java.util.Map;

/**
 * MAP 数据包装器
 * 
 * @author flatychen
 * 
 */
public class MapDataWrapper extends CommonDataWrapper<Map<String, Object>> {

	public static MapDataWrapper newInstance() {
		return new MapDataWrapper();
	}

}
