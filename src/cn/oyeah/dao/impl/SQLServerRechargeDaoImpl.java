package cn.oyeah.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.oyeah.dao.RechargeDao;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.ApplicationException;


public class SQLServerRechargeDaoImpl implements RechargeDao {
	/**
	 * 鑾峰彇璇ユ椂闂存鍐呭崟娆炬父鎴忓綋澶╃殑鍏呭�鎬婚
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getSubscribeDetail(String startTime, String endTime){
		String sql = "select CONVERT(varchar(100), time, 23) as t, productId, sum(amount) as sum from [SubscribeRecord] where (time >= '"
			+ startTime +"') and (time <='" + endTime + "') group by CONVERT(varchar(100), time, 23), productId order by CONVERT(varchar(100), time, 23) desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Integer> subMap = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			subMap = new HashMap<String,Integer>();
			while (rs.next()){
				//System.out.println(rs.getDate("time").toString() + rs.getString("productId"));
				//System.out.println(rs.getInt("sum"));
				subMap.put(rs.getDate("t").toString() + rs.getString("productId"), rs.getInt("sum"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("鑾峰彇璇ユ椂闂存鍐呮墍鏈夋父鎴忕殑鍏呭�鎬婚澶辫触");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return subMap;
	}
	

	/**
	 * 鑾峰彇璇ユ椂闂存鍐呭崟娆炬父鎴忓厖鍊兼�棰�
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getAllSubscribeDetail(String startTime, String endTime){
		String sql = "select CONVERT(varchar(100), time, 23) as t, productId, sum(amount) as sum from [SubscribeRecord] where (time >= '"+
        startTime +"') and (time <='" + endTime + "') group by CONVERT(varchar(100), time, 23), productId order by t desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Integer> subMap = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			subMap = new HashMap<String,Integer>();
			while (rs.next()){
				//System.out.println(rs.getDate("time").toString() + rs.getString("productId"));
				//System.out.println(rs.getInt("sum"));
				if(subMap.get(rs.getString("productId")) == null){
					subMap.put(rs.getString("productId"), rs.getInt("sum"));
				} else {
					subMap.put(rs.getString("productId"), subMap.get(rs.getString("productId")) + rs.getInt("sum"));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return subMap;
	}
	
	/**
	 * 鑾峰緱璇ユ椂闂存鍐呯殑琛屾暟
	 * @param startTime
	 * @param endTime
	 * @throws ParseException 
	 */
	public List<String> getTableRows(String startTime, String endTime) {
		String sql = "select CONVERT(varchar(100), time, 23) as t from [SubscribeRecord] where (time >= '"+
        			  startTime +"') and (time <='" + endTime + "') group by CONVERT(varchar(100), time, 23) order by t desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> rowsList = new ArrayList<String>();
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rowsList.add(rs.getDate("t").toString());
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("鑾峰緱璇ユ椂闂存鍐呯殑琛屾暟澶辫触");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return rowsList;
	} 
	
	/**
	 * 鑾峰彇宸茬粡鍏呭�鐨勬父鎴�
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public Map<String,String> getTableColumns(String startTime, String endTime){
		String sql = "select distinct productId from [SubscribeRecord] where (time >= '"+
						startTime +"') and (time <='" + endTime + "') order by productId desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,String> columnsList = new HashMap<String,String>();
		try {
			//DateUtils.parseDate("ss", new String[]{"yyyy-MM-dd HH:mm:ss", });
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String productName = this.getGameNameByProductId(conn, rs.getInt("productId"));
				int productId = rs.getInt("productId");
				columnsList.put(Integer.toString(productId),productName);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("鑾峰緱璇ユ椂闂存鍐呯殑鍒楁暟澶辫触");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return columnsList;
	}

	/**
	 * 鏍规嵁娓告垙浜у搧ID鍙栧緱娓告垙鍚嶇О
	 * @param conn, id
	 * @return
	 */
	private String getGameNameByProductId(Connection conn, int id) 
	throws SQLException {
		String productName = "";
		String sql = "select productName from [ProductDetail] where productId = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			productName = rs.getString("productName");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return productName;
	}

}
