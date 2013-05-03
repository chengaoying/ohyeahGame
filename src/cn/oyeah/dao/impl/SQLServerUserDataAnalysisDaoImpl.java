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

public class SQLServerUserDataAnalysisDaoImpl implements UserDataAnalysisDao {

	public PageModel<UserDataAnalysis> queryAllUserData(int providerId, String productIds,String startTime,
			String endTime, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select top "+ pageSize +" * ");
		sql.append("from ( ");
		sql.append("	select row_number()over(order by sum(amount) desc) as rowNumber ,userId, sum(amount) as a ");
		sql.append("	from [SubscribeRecord] where time >= '"+startTime+"' and time <= '"+endTime+"'");
		if(providerId != 1){
			sql.append(" and productId in ("+productIds+")");
		}
		sql.append("    group by userId");
		sql.append(") tb2 ");
		sql.append("where  rowNumber > "+pageSize+" * ("+pageNo+"-1) ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserDataAnalysis> pageModel = null;
		int totalPrice = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
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
			pageModel.setTotalRecords(this.queryAllRecords(conn,startTime,endTime,0, providerId, productIds));
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询所有已购买的道具出现异常(sqlserver)");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}

	public PageModel<UserDataAnalysis> querySingleUserData(int providerId, String productIds,String startTime,
			String endTime, int pageNo, int pageSize, int productId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select top "+pageSize+" * ");
		sql.append("from ( ");
		sql.append("	select row_number()over(order by sum(amount) desc) as rowNumber, productName ,userId, sum(amount) as a ");
		sql.append("	from [SubscribeRecord] where productId="+productId+" and time >= '"+startTime+"' and time <= '"+endTime+"'");
		if(providerId != 1){
			sql.append(" and productId in ("+productIds+")");
		}
		sql.append("    group by userId,productName");
		sql.append(") tb2 ");
		sql.append("where  rowNumber > "+pageSize+" * ("+pageNo+"-1) ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserDataAnalysis> pageModel = null;
		int totalPrice = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
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
			pageModel.setTotalPrice(totalPrice);
			pageModel.setPageNo(pageNo);
			pageModel.setTotalRecords(this.queryAllRecords(conn,startTime,endTime,productId, providerId, productIds));
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询所有已购买的道具出现异常(sqlserver)");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}
	
	private int queryAllRecords(Connection conn, String startTime, String endTime, int productId,int providerId, String productIds) throws SQLException{
		String sql = "";
		if(productId == 0){
			if(providerId != 1){
				sql = "select count(*) ,userId from [SubscribeRecord] where time >='"
						+ startTime +"' and time <='"+ endTime +"' and productId in ("+productIds+") group by userId";
			}else{
				sql = "select count(*) ,userId from [SubscribeRecord] where time >='"
						+ startTime +"' and time <='"+ endTime +"' group by userId";
			}
		} else {
			sql =  "select count(*) ,userId from [SubscribeRecord] where productId="+ productId +" and time >='"
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
		String sql = "select * from [Product]";
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
