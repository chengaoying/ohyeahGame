package cn.oyeah.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.oyeah.dao.UserDao;
import cn.oyeah.domain.User;
import cn.oyeah.req.ConnectionManager;
import cn.oyeah.util.ApplicationException;
import cn.oyeah.util.PageModel;

public class MySQLUserDaoImpl implements UserDao {
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User u) {
		String sqlString = "insert into " +
				"Administrator(name, password, role, authority, createtime, logintime) values(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassWord());
			pstmt.setString(3, u.getRole());
			pstmt.setInt(4, u.getAuthority());
			pstmt.setTimestamp(5, new Timestamp(new Date().getTime()));
			pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("添加用户失败");
		} finally {
			ConnectionManager.close(pstmt);
		}

	}

	public boolean delUserById(int id) {
		String sql = "delete from Administrator where id=" + id;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("删除用户失败");
		} finally {
			ConnectionManager.close(pstmt);
		}
		return true;
	}

	/**
	 * 分页查询
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条数据
	 * @return pageModel
	 *
	 */
	public PageModel<User> queryUserList(int pageNo, int pageSize) {

		String sql = "select * from Administrator where name <> 'ohyeah' limit ?, ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<User> pageModel = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo -1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setAuthority(rs.getInt("authority"));
				user.setCreateTime(rs.getDate("createtime"));
				user.setLoginTime(rs.getDate("logintime"));
				userList.add(user);
			}
			pageModel = new PageModel<User>();
			pageModel.setList(userList);
			pageModel.setTotalRecords(getTotalRecords(conn));
			pageModel.setPageSize(pageSize);
			pageModel.setPageNo(pageNo);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询用户失败");
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return pageModel;
	}
	
	/**
	 * 取得总记录数
	 * @param conn
	 * @return
	 */
	private int getTotalRecords(Connection conn) 
	throws SQLException {
		String sql = "select count(*) from Administrator";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return count;
	}
	
	/**
	 * 查询单个用户
	 * @param name
	 */
	public User loadUserByName(String name) {
		String sqlString = "select * from Administrator where name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setAuthority(rs.getInt("authority"));
				user.setCreateTime(rs.getDate("createtime"));
				user.setLoginTime(rs.getDate("logintime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("查询单个用户失败");
		} finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return user;
	}
	
	/**
	 * 修改管理员最后登入的时间
	 * @param name
	 */
	public void updateUserLoginTime(String name) {
		String sql = "update Administrator set logintime = ?  where name = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(pstmt);
		}	
	}
	/**
	 * 更改用户的信息
	 * @param u
	 */
	public void updateUser(User u) {
		String sql = "update Administrator set name = ?, password = ?,role = ?, authority = ? where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassWord());
			pstmt.setString(3, u.getRole());
			pstmt.setInt(4, u.getAuthority());
			pstmt.setInt(5, u.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("更改用户信息失败");
		} finally {
			ConnectionManager.close(pstmt);
		}
	}
}
