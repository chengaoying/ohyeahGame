package cn.oyeah.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.oyeah.domain.JdbcConfig;


public class DaoManager {
	
	private static Map<String, Object> beans = new HashMap<String, Object>();


	private static JdbcConfig jdbcConfig = new JdbcConfig();
	
	static {
		initBeans();
	}

	private static void initBeans() {
		jdbcConfig = SysConfigReader.getInstance().getJdbcConfig();
		parseDocument("/daoManager.xml");
	}
	
	
	@SuppressWarnings("unchecked")
	private static void parseDocument(String cfgPath) {
		SAXReader reader = new SAXReader(); 
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(cfgPath);
		String name = null;
		String value = null;
		try {
			Document document = reader.read(is);
			Element root = document.getRootElement(); 
			Iterator<Element> it = (Iterator<Element>)root.elementIterator();
			while (it.hasNext()) {
				Element e = (Element) it.next(); 
				if ("dao".equalsIgnoreCase(e.getName())) {
						name = e.attributeValue("name");
						value = e.attributeValue("class");
						beans.put(name, Class.forName(value).newInstance());
					}
			 }
		} catch (Throwable e) {
			throw new RuntimeException("初始化dao出错，name="+name+", value="+value, e);
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static Object getDao(String name) {
		Object obj = beans.get(jdbcConfig.getDataBaseName()+"."+name);
		if (obj == null) {
			throw new RuntimeException("未获取到dao, name="+name);
		}
		return obj;
	}

}
