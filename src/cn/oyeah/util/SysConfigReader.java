package cn.oyeah.util;

import java.io.InputStream;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.oyeah.domain.JdbcConfig;

/**
 * 采用单例模式解析sys-config.xml文件 
 * @author xiaochen 2011-11-20
 *
 */
public class SysConfigReader {

	private static SysConfigReader instance = null;

	
	private JdbcConfig jdbcConfig = new JdbcConfig();
	
	private SysConfigReader() {
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("systemConfig.xml");
		try {
			Document doc = reader.read(in);
			
			//取得jdbc相关配置信息
			Element dataBaseNameElt = (Element)doc.selectObject("/config/db-info/database-name");
			Element driverNameElt = (Element)doc.selectObject("/config/db-info/driver-name");
			Element urlElt = (Element)doc.selectObject("/config/db-info/url");
			Element userNameElt = (Element)doc.selectObject("/config/db-info/user-name");
			Element passwordElt = (Element)doc.selectObject("/config/db-info/password");
			
			//设置jdbc相关的配置
			jdbcConfig.setDataBaseName(dataBaseNameElt.getStringValue());
			jdbcConfig.setDriverName(driverNameElt.getStringValue());
			jdbcConfig.setUrl(urlElt.getStringValue());
			jdbcConfig.setUserName(userNameElt.getStringValue());
			jdbcConfig.setPassword(passwordElt.getStringValue());
			
			System.out.println("jdbcConfig-->>" + jdbcConfig);

		} catch (DocumentException e) {
			e.printStackTrace();
		}			
	}
	
	public static synchronized SysConfigReader getInstance() {
		if (instance == null) {
			instance = new SysConfigReader();
		}
		return instance;
	}
	
	
	/**
	 * 返回jdbc相关配置
	 * @return
	 */
	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}
	

	
	public static void main(String[] args) {

		//JdbcConfig jdbcConfig = SysConfigReader.getInstance().getJdbcConfig();
		//System.out.println(jdbcConfig.getDriverName());
		//System.out.println(jdbcConfig.getDataBaseName());
//		System.out.println(jdbcConfig.getUrl());
//		System.out.println(jdbcConfig.getUserName());
		//System.out.println(jdbcConfig);
	}
}
