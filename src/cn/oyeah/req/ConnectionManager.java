package cn.oyeah.req;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.oyeah.domain.JdbcConfig;
import cn.oyeah.util.ApplicationException;
import cn.oyeah.util.SysConfigReader;

/**
 * 封装数据库连接
 * @version 1.0   2011-12-8
 */
public class ConnectionManager {
	
	private static final ThreadLocal<ConnectionManager> requestContexts = new ThreadLocal<ConnectionManager>(); 
	
	private Connection conn;
	
	public static Connection getConnection() {
		ConnectionManager rc = requestContexts.get();
		if (rc == null) {
			rc = new ConnectionManager();
			rc.setConn(rc.openConnection());
			requestContexts.set(rc);
		}
		return rc.conn;
	}
	
	public static void remove() {
		ConnectionManager rc = requestContexts.get();
		if (rc != null && rc.conn != null) {
			rc.close(rc.conn);
		}
		requestContexts.remove();
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}
	
	
	/**
	 * 打开 Connection
	 * @return
	 */
	
	private  Connection openConnection() {
	
		Connection conn = null;
		try {
			JdbcConfig jdbcConfig = SysConfigReader.getInstance().getJdbcConfig();
			Class.forName(jdbcConfig.getDriverName());
			conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUserName(), jdbcConfig.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ApplicationException("系统错误，请联系系统管理员");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("系统错误，请联系系统管理员");
		}
		return conn;
	
	}
	
	private  void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs ) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void beginTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false); //手动提交
				}
			}
		}catch(SQLException e) {}
	}
	
	public static void commitTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
			}
		}catch(SQLException e) {}
	}
	
	public static void rollbackTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
			}
		}catch(SQLException e) {}
	}
	
	public static void resetConnection(Connection conn) {
		try {
			if (conn != null) {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}else {
					conn.setAutoCommit(true);
				}
			}
		}catch(SQLException e) {}
	}
	
}
