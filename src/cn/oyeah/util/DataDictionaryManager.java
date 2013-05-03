package cn.oyeah.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.oyeah.domain.DataDictionary;
import cn.oyeah.domain.ProductProvider;
import cn.oyeah.req.ConnectionManager;

/**
 * 数据字典的操作: 角色,权限.
 * @author xiaochen
 *
 */
public class DataDictionaryManager {
	
	/**
	 * 单例
	 */
	private static  DataDictionaryManager dataDictionaryManager = new DataDictionaryManager();
	
	private DataDictionaryManager(){}
	
	public static DataDictionaryManager getInstance(){
		return dataDictionaryManager;
	}
	
	/**
	 * 根据名称读取数据
	 * @param name
	 * @return
	 */
	public List<DataDictionary> getDictionaryByName(String name){
		String sql = "select * from DataDictionary where name = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			List<DataDictionary> dataDictionaryList = new ArrayList<DataDictionary>();
			while (rs.next()) {
				DataDictionary dataDictionary = new DataDictionary();
				dataDictionary.setName(rs.getString("name"));
				dataDictionary.setValue(rs.getString("value"));
				
				dataDictionaryList.add(dataDictionary);
			}
			return dataDictionaryList;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}	
	}
	
	public List<ProductProvider> getAllProductProvider(){
		String sql = "select * from ProductProvider";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<ProductProvider> list = new ArrayList<ProductProvider>();
			while (rs.next()) {
				ProductProvider productProvider = new ProductProvider();
				productProvider.setProviderID(Integer.parseInt(rs.getString("providerId")));
				productProvider.setProviderName(rs.getString("providerName"));
				productProvider.setType(rs.getString("type"));
				list.add(productProvider);
			}
			return list;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}	
	}
	
	public static void main(String[] args){
		//List<DataDictionary> roles = DataDictionaryManager.getInstance().getDictionaryByName("authority");
		//System.out.println(roles.size());
		/*List<ProductProvider> list = DataDictionaryManager.getInstance().getAllProductProvider();
		for(ProductProvider pp:list){
			System.out.println(pp.getProviderName());
			System.out.println(pp.getProviderID());
		}*/
	}

}
