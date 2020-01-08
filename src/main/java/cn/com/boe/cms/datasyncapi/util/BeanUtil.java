package cn.com.boe.cms.datasyncapi.util;


import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {

	public static Map<String, String> bean2Map(final Object bean) throws Exception {
		Map<String, String> map = BeanUtils.describe(bean);
		return map;

	}
	
	public static Object map2Bean(Object bean, Map<String, ? extends Object> map) throws Exception {
		BeanUtils.populate(bean, map);
		return bean;
	}

	

}
