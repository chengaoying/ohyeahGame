package cn.oyeah.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.oyeah.dao.UserRecordQueryDao;
import cn.oyeah.domain.UserSubscribeRecord;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.ApplicationException;
import cn.oyeah.util.PageModel;

public class MySQLUserRecordQueryDaoImpl implements UserRecordQueryDao {

	public PageModel<UserSubscribeRecord> queryUserPurchaseByUserId(String userId,
			int pageNo, int pageSize) {
		String sql = "select time, amount, propName from PurchaseRecord where userId=? order by time desc limit ?, ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserSubscribeRecord> pageModel = null;
		int totalPrice = 0;
		int totalRecords = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (pageNo -1) * pageSize);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			List<UserSubscribeRecord> userSubscribeRecords = new ArrayList<UserSubscribeRecord>();
			while(rs.next()){
				UserSubscribeRecord userSubscribeRecord = new UserSubscribeRecord();
				userSubscribeRecord.setPurchaseAmount(rs.getInt("amount"));
				userSubscribeRecord.setTime(rs.getTimestamp("time"));
				userSubscribeRecord.setName(rs.getString("propName"));
				totalPrice += rs.getInt("amount");
				totalRecords ++;
				userSubscribeRecords.add(userSubscribeRecord);
			}
			pageModel = new PageModel<UserSubscribeRecord>();
			pageModel.setList(userSubscribeRecords);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalPrice(totalPrice);
			pageModel.setTotalRecords(totalRecords);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询用户充值记录出现异常");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}

	public PageModel<UserSubscribeRecord> queryUserSubscribeByUserId(
			String userId, int pageNo, int pageSize) {
		String sql = "select time, amount, productName from SubscribeRecord where userId=? order by time desc limit ?, ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserSubscribeRecord> pageModel = null;
		int totalPrice = 0;
		int totalRecords = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (pageNo -1) * pageSize);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			List<UserSubscribeRecord> userSubscribeRecords = new ArrayList<UserSubscribeRecord>();
			while(rs.next()){
				UserSubscribeRecord userSubscribeRecord = new UserSubscribeRecord();
				userSubscribeRecord.setSubscribeAmount(rs.getInt("amount"));
				userSubscribeRecord.setTime(rs.getTimestamp("time"));
				userSubscribeRecord.setName(rs.getString("productName"));
				totalPrice += rs.getInt("amount");
				totalRecords ++;
				userSubscribeRecords.add(userSubscribeRecord);
			}
			pageModel = new PageModel<UserSubscribeRecord>();
			pageModel.setList(userSubscribeRecords);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalPrice(totalPrice);
			pageModel.setTotalRecords(totalRecords);
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询用户充值记录出现异常");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}

}
