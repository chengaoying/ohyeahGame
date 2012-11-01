package cn.oyeah.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.oyeah.dao.RealStatisticDao;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.ApplicationException;

public class MySQLRealStatisticDaoImpl implements RealStatisticDao {
	
	/**
	 * 获取当天某时间段内的充值总额  
	 */
	public Map<String, Integer> queryRealSubscribe(String time) {
		String sql = "select hour(time) as h, productId, sum(amount) as sum from SubscribeRecord where (time >= '"+
		time +" 00:00:00') and (time <='" + time + " 23:59:59') group by hour(time),productId order by hour(time) desc";
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
				subMap.put(rs.getInt("h") + rs.getString("productId"), rs.getInt("sum"));
			}
			//System.out.println(subMap.size());
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("获取该时间段游戏当天的充值总额失败--->>实时统计");
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
	public Map<String,Integer> getAllSubscribeDetail(String time){
		String sql = "select hour(time) as h, productId, sum(amount) as sum from SubscribeRecord where (time >= '"+
		time +" 00:00:00') and (time <='" + time + " 23:59:59') group by hour(time),productId order by hour(time) desc";
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
			throw new ApplicationException("获取该时间段内所有游戏的充值总额失败--->>实时统计");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return subMap;
	}

	/**
	 * 获取已经充值的游戏
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public Map<String,String> getPurchaseGameName(String time){
		String sql = "select distinct productId, productName from SubscribeRecord where (time >= '"+
		time +" 00:00:00') and (time <='" + time + " 23:59:59') order by productId desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,String> gameNames = new HashMap<String,String>();
		try {
			//DateUtils.parseDate("ss", new String[]{"yyyy-MM-dd HH:mm:ss", });
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String productName = rs.getString("productName");
				int productId = rs.getInt("productId");
				gameNames.put(Integer.toString(productId),productName);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("获得该时间段内的列数失败--->>实时统计");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return gameNames;
	}
}
