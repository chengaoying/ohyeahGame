package cn.oyeah.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.oyeah.dao.UserSubscribeRecordDao;
import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserSubscribeStatistic;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.ApplicationException;
import cn.oyeah.util.DateTimeUtils;
import cn.oyeah.util.PageModel;

public class SQLServerUserStatisticDaoImpl implements UserSubscribeRecordDao {

	public PageModel<UserSubscribeStatistic> queryAllUserSubscribeRecord(
			int pageNo, int pageSize) {

		StringBuffer sqlString = new StringBuffer();
		sqlString.append(" select top " + pageSize + " * ");
		sqlString.append(" from ( ");
		sqlString.append("      select row_number()over(order by tb.t desc) as rowNumber ,* ");
		sqlString.append("       from (");
		sqlString.append(" 				select count(userId) as users, CONVERT(varchar(100), time, 23) as t,sum(amount) as a ");
		sqlString.append("      		from [SubscribeRecord]");
		sqlString.append("      		where CONVERT(varchar(100), time, 23) >= '"+ DateTimeUtils.getStartTime() +"' and CONVERT(varchar(100), time, 23) <= '"+ DateTimeUtils.getEndTime() +"'");
		sqlString.append("      		group by CONVERT(varchar(100), time, 23) ");
		sqlString.append("       ) tb");
		sqlString.append("   ) tb2");
		sqlString.append(" where  rowNumber > "+ pageSize +" * (" + pageNo + "-1)");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserSubscribeStatistic> pageModel = null;
		int totalPrice = 0;
		int users = 0;
		int totalRecords = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sqlString.toString());
			//pstmt.setInt(1, (pageNo -1) * pageSize);
			//pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<UserSubscribeStatistic> userSubscribeList = new ArrayList<UserSubscribeStatistic>();
			while (rs.next()) {
				UserSubscribeStatistic userSubscribe = new UserSubscribeStatistic();
				userSubscribe.setNewCount(this.queryNewCount(conn, rs.getDate("t"),0));
				userSubscribe.setTotalCount(this.queryAllCount(conn, rs.getDate("t"),0));
				userSubscribe.setPrice(rs.getInt("a"));
				userSubscribe.setTime(rs.getDate("t"));
				
				userSubscribeList.add(userSubscribe);
				totalPrice += rs.getInt("a");
				users += rs.getInt("users");
				totalRecords++;
			}
			pageModel = new PageModel<UserSubscribeStatistic>();
			pageModel.setList(userSubscribeList);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalPrice(totalPrice);
			pageModel.setTotalUsers(users);//鏌ヨ鐢ㄦ埛鎬绘暟
			pageModel.setTotalRecords(totalRecords);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("鏌ヨ榛樿鍏呭�鐢ㄦ埛鍑虹幇寮傚父");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}

	public PageModel<UserSubscribeStatistic> queryUserSubscribeRecord(
			String startTime, String endTime, int pageNo, int pageSize, int productId) {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(" select top " + pageSize + " * ");
		sqlString.append(" from ( ");
		sqlString.append("      select row_number()over(order by tb.t desc) as rowNumber ,* ");
		sqlString.append("       from (");
		sqlString.append(" 				select count(userId) as users, CONVERT(varchar(100), time, 23) as t,sum(amount) as a ");
		sqlString.append("      		from [SubscribeRecord]");
		sqlString.append("      		where productId=? and CONVERT(varchar(100), time, 23) >= '"+ startTime +"' and CONVERT(varchar(100), time, 23) <= '"+ endTime +"'");
		sqlString.append("      		group by CONVERT(varchar(100), time, 23) ");
		sqlString.append("       ) tb");
		sqlString.append("   ) tb2");
		sqlString.append(" where  rowNumber > "+ pageSize +" * (" + pageNo + "-1)");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<UserSubscribeStatistic> pageModel = null;
		int totalPrice = 0;
		int users = 0;
		int totalRecords = 0;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sqlString.toString());
			pstmt.setInt(1, productId);
			//pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<UserSubscribeStatistic> userSubscribeList = new ArrayList<UserSubscribeStatistic>();
			while (rs.next()) {
				UserSubscribeStatistic userSubscribe = new UserSubscribeStatistic();
				userSubscribe.setNewCount(this.queryNewCount(conn, rs.getDate("t"),productId));
				userSubscribe.setTotalCount(this.queryAllCount(conn, rs.getDate("t"),productId));
				userSubscribe.setPrice(rs.getInt("a"));
				userSubscribe.setTime(rs.getDate("t"));
				
				userSubscribeList.add(userSubscribe);
				totalPrice += rs.getInt("a");
				users += rs.getInt("users");
				totalRecords++;
			}
			pageModel = new PageModel<UserSubscribeStatistic>();
			pageModel.setList(userSubscribeList);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalPrice(totalPrice);
			pageModel.setTotalUsers(users);//鏌ヨ鐢ㄦ埛鎬绘暟
			pageModel.setTotalRecords(totalRecords);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("鏌ヨ鍏呭�鐢ㄦ埛鍑虹幇寮傚父");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}

	/**
	 * 鏌ヨ鏌愬ぉ鐨勬�鍏呭�鐢ㄦ埛鏁�
	 * @param conn
	 * @param date
	 * @return
	 */
	private int queryAllCount(Connection conn, Date date, int productId) {
		String sql = "";
		if(productId == 0){
			 sql = "select count(*) as s, userId "
				   +" from [SubscribeRecord] "
				   +" where CONVERT(varchar(100), time, 23) = '"+ date +"' group by userId" ; 
		} else {
			 sql = "select count(*) as s, userId "
				   +" from [SubscribeRecord] "
				   +" where productId="+ productId +" and CONVERT(varchar(100), time, 23) = '"+ date +"' group by userId" ; 
		}
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count += rs.getInt("s");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return count;
	}



	/**
	 * 鏌ヨ鏌愬ぉ鐨勬柊澧炲厖鍊肩敤鎴锋暟
	 * @param conn
	 * @param date
	 * @return
	 */
	private int queryNewCount(Connection conn, Date date, int productId) {
		String sql = "";
		if(productId == 0){
			 sql = "select count(*) as s, userId "
				   +" from [SubscribeRecord] "
				   +" where productSubscribeCommand = 1 and CONVERT(varchar(100), time, 23) = '"+ date +"' group by userId" ;
		} else {
			 sql = "select count(*) as s, userId "
				   +" from [SubscribeRecord] "
				   +" where productId ="+ productId +" and productSubscribeCommand = 1 and CONVERT(varchar(100), time, 23) = '"+ date +"' group by userId" ;
		}
		 
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count += rs.getInt("s");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("鏌ヨ鏂板鍏呭�鐢ㄦ埛鍑虹幇寮傚父");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return count;
	}
	

	/**
	 * 鏌ヨ鎵�湁鐨勬父鎴忎骇鍝�
	 * @return List
	 */
	public List<Product> queryAllProduct(){
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
