package cn.appm.utils.base;


import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

public class BaseBeanUtils  {
	
	public static void copyProperties(Object desc,Object orig){
		try {
			PropertyUtils.copyProperties(desc, orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

}
