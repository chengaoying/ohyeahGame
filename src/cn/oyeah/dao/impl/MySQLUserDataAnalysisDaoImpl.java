package cn.oyeah.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.oyeah.dao.UserDataAnalysisDao;
import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserDataAnalysis;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.ApplicationException;
import cn.oyeah.util.PageModel;

public class MySQLUserDataAnalysisDaoImpl implements UserDataAnalysisDao {

	public PageModel<UserDataAnalysis> queryAllUserData(int providerId, String productIds, String startTime,
			String endTime, int pageNo, int pageSize) {
		String sql = "";
		if(providerId != 1){
			sql = "select userId, sum(amount) as a from SubscribeRecord "
					+" where time >= '" +startTime+"' and time <= '" + endTime
					+"' and productId in ("+productIds+")"
			        +" group by  userId order by a desc limit ?, ?";
		}else{
			sql = "select userId, sum(amount) as a from SubscribeRecord "
					+" where time >= '" +startTime+"' and time <= '" + endTime
			        +"' group by  userId order by a desc limit ?, ?";
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserDataAnalysis> pageModel = null;
		int totalPrice = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo -1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<UserDataAnalysis> userData = new ArrayList<UserDataAnalysis>();
			while (rs.next()) {
				UserDataAnalysis userDataAnalysis = new UserDataAnalysis();
				userDataAnalysis.setUserId(rs.getString("userId"));
				userDataAnalysis.setPrice(rs.getInt("a"));
				totalPrice += rs.getInt("a");
				userData.add(userDataAnalysis);
			}
			pageModel = new PageModel<UserDataAnalysis>();
			pageModel.setList(userData);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalPrice(totalPrice);
			pageModel.setPageNo(pageNo);
			pageModel.setTotalRecords(this.queryAllRecords(conn,startTime,endTime,0,providerId, productIds));
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询所有已购买的道具出现异常");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}

	public PageModel<UserDataAnalysis> querySingleUserData(int providerId, String productIds, String startTime,
			String endTime, int pageNo, int pageSize,int productId) {
		String sql = "";
		if(providerId != 1){
			sql = "select userId,productName, sum(amount) as a from SubscribeRecord "
					+" where productId = "+productId+" and time >= '" +startTime+"' and time <= '" + endTime
					+"' and productId in ("+productIds+")"
			        +" group by  userId order by a desc limit ?, ?";
		}else{
			sql = "select userId,productName, sum(amount) as a from SubscribeRecord "
					+" where productId = "+productId+" and time >= '" +startTime+"' and time <= '" + endTime
			        +"' group by  userId order by a desc limit ?, ?";
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserDataAnalysis> pageModel = null;
		int totalPrice = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo -1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<UserDataAnalysis> userData = new ArrayList<UserDataAnalysis>();
			while (rs.next()) {
				UserDataAnalysis userDataAnalysis = new UserDataAnalysis();
				userDataAnalysis.setUserId(rs.getString("userId"));
				userDataAnalysis.setPrice(rs.getInt("a"));
				userDataAnalysis.setGameName(rs.getString("productName"));
				totalPrice += rs.getInt("a");
				userData.add(userDataAnalysis);
			}
			pageModel = new PageModel<UserDataAnalysis>();
			pageModel.setList(userData);
			pageModel.setPageSize(pageSize);
			pageModel.setPageNo(pageNo);
			pageModel.setTotalPrice(totalPrice);
			pageModel.setTotalRecords(this.queryAllRecords(conn,startTime,endTime,productId,providerId, productIds));
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询所有已购买的道具出现异常");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}
	
	private int queryAllRecords(Connection conn, String startTime, String endTime, int productId, int providerId, String productIds) throws SQLException{
		String sql = "";
		if(productId == 0){
			if(providerId != 1){
				sql = "select count(*) ,userId from SubscribeRecord where time >='"
						+ startTime +"' and time <='"+ endTime +"' and productId in ("+productIds+") group by userId";
			}else{
				 sql = "select count(*) ,userId from SubscribeRecord where time >='"
							+ startTime +"' and time <='"+ endTime +"' group by userId";
			}
		} else {
			sql =  "select count(*) ,userId from SubscribeRecord where productId="+ productId +" and time >='"
				+ startTime +"' and time <='"+ endTime +"' group by userId";
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int records = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				records ++;
			}
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return records;
	}

	public List<Product> queryAllProduct(int providerId) {
		String sql = "";
		if(providerId==1){
			 sql = "select * from Product";
		}else{
			 sql = "select * from Product where providerId = "+providerId;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<Product>();
		Product product = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductClass(rs.getInt("productClass"));
				product.setAppName(rs.getString("appName"));
				product.setAppType(rs.getString("appType"));
				product.setDescription(rs.getString("description"));
				product.setState(rs.getInt("state"));
				
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return productList;
	}

}
