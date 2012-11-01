package cn.oyeah.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.oyeah.dao.PropPurchaseDao;
import cn.oyeah.domain.Product;
import cn.oyeah.domain.PurchaseProp;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.PageModel;

public class SQLServerPropPurchaseDaoImpl implements PropPurchaseDao {
    
	/**
	 * 查询所有已购买的道具并分页
	 * @param pageNo, pageSize
	 */
	public PageModel<PurchaseProp> queryAllPurchaseProp(String startTime, String endTime, int pageNo, int pageSize) {

		StringBuffer sqlString = new StringBuffer();
		sqlString.append(" select top " + pageSize + " * ");
		sqlString.append(" from ( ");
		sqlString.append("      select row_number()over(order by tb.pCount desc) as rowNumber ,* ");
		sqlString.append("       from (");
		sqlString.append(" 				select propId,sum(propCount) as pCount ,amount ");
		sqlString.append("      		from [PurchaseRecord]");
		sqlString.append("      		where time >= '"+ startTime +"' and time <= '"+ endTime +"'");
		sqlString.append("      		group by propId,amount ");
		sqlString.append("       ) tb");
		sqlString.append("   ) tb2");
		sqlString.append(" where  rowNumber > "+ pageSize +" * (" + pageNo + "-1)");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<PurchaseProp> pageModel = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sqlString.toString());
			rs = pstmt.executeQuery();
			List<PurchaseProp> propList = new ArrayList<PurchaseProp>();
			while (rs.next()) {
				PurchaseProp purchaseProp = new PurchaseProp();
				//purchaseProp.setProductId(rs.getInt("productId"));
				purchaseProp.setPropId(rs.getInt("propId"));
				purchaseProp.setAmount(rs.getInt("amount"));
				purchaseProp.setPropCount(rs.getInt("pCount"));
				//purchaseProp.setTime(rs.getDate("t"));
				purchaseProp.setGameName(this.queryPropNameAndProductName(conn, rs.getInt("propId"))[0]);
				purchaseProp.setPropName(this.queryPropNameAndProductName(conn, rs.getInt("propId"))[1]);
				propList.add(purchaseProp);
			}
			pageModel = new PageModel<PurchaseProp>();
			pageModel.setList(propList);
			pageModel.setTotalRecords(this.getAllTotalRecords(conn,0, startTime, endTime));
			pageModel.setTotalPrice(this.queryTotalPrice(conn, 0, startTime, endTime));
			pageModel.setPageSize(pageSize);
			pageModel.setPageNo(pageNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}
	
	/**
	 * 查询单款游戏已购买的道具并分页
	 * @param pageNo, pageSize
	 */
	public PageModel<PurchaseProp> queryPurchasePropById(String startTime, String endTime, int id, int pageNo, int pageSize) {

		StringBuffer sqlString = new StringBuffer();
		sqlString.append(" select top " + pageSize + " * ");
		sqlString.append(" from ( ");
		sqlString.append("      select row_number()over(order by tb.pCount desc) as rowNumber ,* ");
		sqlString.append("       from (");
		sqlString.append(" 				select propId,sum(propCount) as pCount ,amount ");
		sqlString.append("      		from [PurchaseRecord]");
		sqlString.append("      		where productId=" + id + " and time >= '" + startTime + "' and time <= '" + endTime + "'");
		sqlString.append("      		group by propId,amount ");
		sqlString.append("       ) tb");
		sqlString.append("   ) tb2");
		sqlString.append(" where  rowNumber > "+ pageSize +" * (" + pageNo + "-1)");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<PurchaseProp> pageModel = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sqlString.toString());
			rs = pstmt.executeQuery();
			List<PurchaseProp> propList = new ArrayList<PurchaseProp>();
			while (rs.next()) {
					PurchaseProp purchaseProp = new PurchaseProp();
					//purchaseProp.setProductId(rs.getInt("productId"));
					purchaseProp.setPropId(rs.getInt("propId"));
					purchaseProp.setAmount(rs.getInt("amount"));
					purchaseProp.setPropCount(rs.getInt("pCount"));
					//purchaseProp.setTime(rs.getDate("t"));
					purchaseProp.setGameName(this.queryPropNameAndProductName(conn, rs.getInt("propId"))[0]);
					purchaseProp.setPropName(this.queryPropNameAndProductName(conn, rs.getInt("propId"))[1]);
					propList.add(purchaseProp);
				}
				pageModel = new PageModel<PurchaseProp>();
				pageModel.setList(propList);
				pageModel.setTotalRecords(this.getAllTotalRecords(conn,id, startTime, endTime));
				pageModel.setTotalPrice(this.queryTotalPrice(conn, id, startTime, endTime));
				pageModel.setPageSize(pageSize);
				pageModel.setPageNo(pageNo);
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				ConnectionManager.close(rs);
				ConnectionManager.close(pstmt);
			}
		return pageModel;
	}
	
	/**
	 * 查询所有的游戏产品
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


	/**
	 * 所有游戏取得总记录数
	 * @param conn,productId,startTime,endTime
	 * @return
	 */
	private int getAllTotalRecords(Connection conn,int productId, String startTime, String endTime) 
	throws SQLException {
		String sql = "";
		if(productId == 0){
				 sql = "select propId from [PurchaseRecord] " 
					+" where CONVERT(varchar(100), time, 23) >= '" +startTime+"' and CONVERT(varchar(100), time, 23) <= '" + endTime 
					+ "' group by propId";
		} else {
			 sql = "select propId from [PurchaseRecord] where productId="+ productId 
					+" and CONVERT(varchar(100), time, 23) >= '" +startTime+"' and CONVERT(varchar(100), time, 23) <= '" + endTime 
					+ "' group by  propId";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count ++;
			}
			//System.out.println("count:"+count);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return count;
	}

	/**
	 * 查询并计算购买道具的总额
	 * @return
	 * @throws SQLException 
	 */
	private long queryTotalPrice(Connection conn, int productId, String startTime, String endTime) 
	throws SQLException{
		long totalPrice = 0;
		String sql = "";
		if (productId == 0){
			 sql = "select CONVERT(varchar(100), time, 23), propCount, amount from [PurchaseRecord] "
				 +" where CONVERT(varchar(100), time, 23) >= '" +startTime+"' and CONVERT(varchar(100), time, 23) <= '" + endTime + "'";
		} else {
			 sql = "select time, propCount, amount from [PurchaseRecord] where productId = " + productId
			 +" and CONVERT(varchar(100), time, 23) >= '" +startTime+"' and CONVERT(varchar(100), time, 23) <= '" + endTime + "'";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalPrice += rs.getInt("propCount") * rs.getInt("amount");
			}
			//System.out.println("totalPrice:" + totalPrice);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return totalPrice / 10;
	}
	
	/**
	 * 根据道具Id查询道具的名称和游戏的名称
	 * @param conn
	 * @param propId
	 * @return
	 */
	private String[] queryPropNameAndProductName(Connection conn, int propId)
	throws SQLException{
		String[] names = new String[2];
		String sql = "select productName,propName from [PurchaseRecord] where propId= " + propId;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				names[0] = rs.getString("productName");
				names[1] = rs.getString("propName");
			}
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}	
		return names;
	}

}
