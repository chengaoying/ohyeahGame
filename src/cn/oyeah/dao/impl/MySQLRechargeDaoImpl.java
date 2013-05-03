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

public class MySQLRechargeDaoImpl implements RechargeDao {

	
	/**
	 * 获取该时间段内单款游戏当天的充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getSubscribeDetail(int providerId, String productIds,String startTime, String endTime){
		String sql = "";
		if(providerId != 1){
			sql = "select date(time) as t, productId, sum(amount) as sum from SubscribeRecord where (time >= '"+
			        startTime +"') and (time <='" + endTime + "') and productId in ("+productIds+") group by date(time), productId order by date(time) desc";
		}else{
			sql = "select date(time) as t, productId, sum(amount) as sum from SubscribeRecord where (time >= '"+
			        startTime +"') and (time <='" + endTime + "') group by date(time), productId order by date(time) desc";
		}
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
				subMap.put(rs.getDate("t").toString() + rs.getString("productId"), rs.getInt("sum"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("获取该时间段内单款游戏当天的充值总额失败");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return subMap;
	}
	
	/**
	 * 获取该时间段内单款游戏充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getAllSubscribeDetail(int providerId, String productIds,String startTime, String endTime){
		String sql = "";
		if(providerId != 1){
			 sql = "select date(time) as t, productId, sum(amount) as sum from SubscribeRecord where (time >= '"+
				        startTime +"') and (time <='" + endTime + "') and productId in ("+productIds+") group by date(time), productId order by date(time) desc";
		}else{
			 sql = "select date(time) as t, productId, sum(amount) as sum from SubscribeRecord where (time >= '"+
				        startTime +"') and (time <='" + endTime + "') group by date(time), productId order by date(time) desc";
		}
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
				if(subMap.get(rs.getString("productId")) == null){
					subMap.put(rs.getString("productId"), rs.getInt("sum"));
				} else {
					subMap.put(rs.getString("productId"), subMap.get(rs.getString("productId")) + rs.getInt("sum"));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("获取该时间段内所有游戏的充值总额失败");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return subMap;
	}
	
	/**
	 * 获得该时间段内的行数
	 * @param startTime
	 * @param endTime
	 * @throws ParseException 
	 */
	public List<String> getTableRows(int providerId, String productIds,String startTime, String endTime) {
		String sql = "";
		if(providerId != 1){
			sql = "select date(time) as t from SubscribeRecord where (time >= '"+
      			  startTime +"') and (time <='" + endTime + "') and productId in ("+productIds+") group by date(time) order by date(time) desc";
		}else{
			sql = "select date(time) as t from SubscribeRecord where (time >= '"+
      			  startTime +"') and (time <='" + endTime + "') group by date(time) order by date(time) desc";
		}
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
			throw new ApplicationException("获得该时间段内的行数失败");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return rowsList;
	} 
	
	/**
	 * 获取已经充值的游戏
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public Map<String,String> getTableColumns(int providerId, String productIds,String startTime, String endTime){
		String sql = "";
		if(providerId != 1){
			sql = "select distinct productId, productName from SubscribeRecord where (time >= '"+
					startTime +"') and (time <='" + endTime + "') and productId in ("+productIds+") order by productId desc";
		}else{
			sql = "select distinct productId, productName from SubscribeRecord where (time >= '"+
					startTime +"') and (time <='" + endTime + "') order by productId desc";
		}
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
				String productName = rs.getString("productName");
				int productId = rs.getInt("productId");
				columnsList.put(Integer.toString(productId),productName);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("获得该时间段内的列数失败");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return columnsList;
	}

}
