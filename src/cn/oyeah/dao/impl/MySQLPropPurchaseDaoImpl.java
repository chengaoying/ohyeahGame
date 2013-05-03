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
import cn.oyeah.util.ApplicationException;
import cn.oyeah.util.PageModel;

public class MySQLPropPurchaseDaoImpl implements PropPurchaseDao {
    
	/**
	 * 查询所有已购买的道具并分页
	 * @param pageNo, pageSize
	 */
	public PageModel<PurchaseProp> queryAllPurchaseProp(int providerId,String productIds, String startTime, String endTime, int pageNo, int pageSize) {
		String sql = "";
		if(providerId!=1){
			sql = "select date(time) as t,productId, productName, propId, propName, sum(propCount) as pCount,amount from PurchaseRecord "
					+" where time >= '" +startTime+"' and time <= '" + endTime
					+"' and productId in("+productIds+")"
			        +" group by  propId order by sum(propCount) desc limit ?, ?";
		}else{
			sql = "select date(time) as t,productId, productName, propId, propName, sum(propCount) as pCount,amount from PurchaseRecord "
					+" where time >= '" +startTime+"' and time <= '" + endTime
			        +"' group by  propId order by sum(propCount) desc limit ?, ?";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<PurchaseProp> pageModel = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo -1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<PurchaseProp> propList = new ArrayList<PurchaseProp>();
			while (rs.next()) {
				PurchaseProp purchaseProp = new PurchaseProp();
				purchaseProp.setProductId(rs.getInt("productId"));
				purchaseProp.setPropId(rs.getInt("propId"));
				purchaseProp.setAmount(rs.getInt("amount"));
				purchaseProp.setPropCount(rs.getInt("pCount"));
				purchaseProp.setTime(rs.getDate("t"));
				purchaseProp.setGameName(rs.getString("productName"));
				purchaseProp.setPropName(rs.getString("propName"));
				propList.add(purchaseProp);
			}
			pageModel = new PageModel<PurchaseProp>();
			pageModel.setList(propList);
			pageModel.setTotalRecords(this.getAllTotalRecords(conn,0, startTime, endTime, providerId, productIds));
			//System.out.println(this.getAllTotalRecords(conn,0, startTime, endTime));
			pageModel.setTotalPrice(this.queryTotalPrice(conn, 0, startTime, endTime, providerId, productIds));
			//System.out.println(this.queryTotalPrice(conn, 0, startTime, endTime));
			pageModel.setPageSize(pageSize);
			pageModel.setPageNo(pageNo);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询所有已购买的道具出现异常");
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
	public PageModel<PurchaseProp> queryPurchasePropById(String startTime, String endTime, int productId, int pageNo, int pageSize) {
		String sql = "select date(time) as t,productId,productName,propId,propName,sum(propCount) as pCount,amount from PurchaseRecord where productId = ? "
					+" and time >= '" +startTime+"' and time <= '" + endTime
					+"' group by propId order by sum(propCount) desc limit ?, ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<PurchaseProp> pageModel = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			pstmt.setInt(2,(pageNo -1) * pageSize);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			List<PurchaseProp> propList = new ArrayList<PurchaseProp>();
			while (rs.next()) {
					PurchaseProp purchaseProp = new PurchaseProp();
					purchaseProp.setProductId(rs.getInt("productId"));
					purchaseProp.setPropId(rs.getInt("propId"));
					purchaseProp.setAmount(rs.getInt("amount"));
					purchaseProp.setPropCount(rs.getInt("pCount"));
					purchaseProp.setTime(rs.getDate("t"));
					purchaseProp.setGameName(rs.getString("productName"));
					purchaseProp.setPropName(rs.getString("propName"));
					propList.add(purchaseProp);
				}
				pageModel = new PageModel<PurchaseProp>();
				pageModel.setList(propList);
				pageModel.setTotalRecords(this.getAllTotalRecords(conn,productId, startTime, endTime,-1,null));
				pageModel.setTotalPrice(this.queryTotalPrice(conn, productId, startTime, endTime,-1,null));
				pageModel.setPageSize(pageSize);
				pageModel.setPageNo(pageNo);
			}catch(SQLException e) {
				e.printStackTrace();
				throw new ApplicationException("查询单款已购买的道具出现异常");
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
	public List<Product> queryAllProduct(int providerId){
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

	/**
	 * 所有游戏取得总记录数
	 * @param conn,productId,startTime,endTime
	 * @return
	 */
	private int getAllTotalRecords(Connection conn,int productId, String startTime, String endTime, int providerId, String productIds) 
	throws SQLException {
		String sql = "";
		if(productId == 0){
			if(providerId!=1){
				sql = "select date(time) as t,propId, sum(propCount) from PurchaseRecord " 
						+" where date(time) >= '" +startTime+"' and date(time) <= '" + endTime 
						+"'  and productId in ("+productIds+")"
						+ " group by propId  order by sum(propCount) desc";
			}else{
				sql = "select date(time) as t,propId, sum(propCount) from PurchaseRecord " 
						+" where date(time) >= '" +startTime+"' and date(time) <= '" + endTime 
						+ "' group by propId  order by sum(propCount) desc";
			}
				       
		} else {
			    sql = "select date(time) as t,productId,propId, sum(propCount) from PurchaseRecord where productId= "+ productId 
					+" and date(time) >= '" +startTime+"' and date(time) <= '" + endTime 
					+ "' group by  propId  order by sum(propCount) desc";
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
	private long queryTotalPrice(Connection conn, int productId, String startTime, String endTime, int providerId, String productIds) 
	throws SQLException{
		long totalPrice = 0;
		String sql = "";
		if (productId == 0){
			if(providerId!=1){
				 sql = "select date(time) as t,propId, sum(propCount) as pCount, amount from PurchaseRecord "
						 +" where time >= '" +startTime+"' and time <= '" + endTime
						 +"' and productId in ("+productIds+")"
						 + " group by propId,amount order by sum(propCount) desc";
			}else{ 
				sql = "select date(time) as t,propId, sum(propCount) as pCount, amount from PurchaseRecord "
					 +" where time >= '" +startTime+"' and time <= '" + endTime
					 + "' group by propId,amount order by sum(propCount) desc";
			}
		} else {
			 sql = "select date(time) as t,productId,propId, sum(propCount) as pCount, amount from PurchaseRecord where productId = " + productId
			 +" and time >= '" +startTime+"' and time <= '" + endTime 
			 + "' group by propId,amount order by sum(propCount) desc";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalPrice += rs.getInt("pCount") * rs.getInt("amount");
			}
			//System.out.println("totalPrice:" + totalPrice);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return totalPrice / 10;
	}

}
