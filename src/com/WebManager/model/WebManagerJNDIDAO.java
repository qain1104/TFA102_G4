//������TFA10201�������t�d
package com.WebManager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class WebManagerJNDIDAO implements WebManagerDAO_interface {
	

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO WEB_MANAGER(managerName,managerEmail,managerAccount,managerPassword,managerPic,managerStatus) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT managerId,managerName,managerEmail,managerAccount,managerPassword,managerPic,managerStatus FROM WEB_MANAGER order by managerId";
	private static final String GET_ONE_STMT = "SELECT managerId,managerName,managerEmail,managerAccount,managerPassword,managerPic,managerStatus FROM WEB_MANAGER where managerId = ?";
	private static final String DELETE = "DELETE FROM WEB_MANAGER where managerId = ?";
	private static final String UPDATE = "UPDATE WEB_MANAGER set managerName=?,managerEmail=?,managerAccount=?,managerPassword=?,managerPic=?,managerStatus=? where managerId=?";

	@Override
	public void insert(WebManagerVO webManagerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);// ����Ʈw�i�w���sĶSQL���O�A�Ω��ܼƶǻ��B���ƨϥΪ����O
			// ����e���]�w�Ѽ�(�Ypstmt�̪�?)
			pstmt.setString(1, webManagerVO.getManagerName());
			pstmt.setString(2, webManagerVO.getManagerEmail());
			pstmt.setString(3, webManagerVO.getManagerAccount());
			pstmt.setString(4, webManagerVO.getManagerPassword());
			pstmt.setBytes(5, webManagerVO.getManagerPic());
			pstmt.setInt(6, webManagerVO.getManagerStatus());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(WebManagerVO webManagerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, webManagerVO.getManagerName());
			pstmt.setString(2, webManagerVO.getManagerEmail());
			pstmt.setString(3, webManagerVO.getManagerAccount());
			pstmt.setString(4, webManagerVO.getManagerPassword());
			pstmt.setBytes(5, webManagerVO.getManagerPic());
			pstmt.setInt(6, webManagerVO.getManagerStatus());
			pstmt.setInt(7, webManagerVO.getManagerId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer managerId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, managerId);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public WebManagerVO findByPrimaryKey(Integer managerId) {
		WebManagerVO webManagerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, managerId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// webManagerVO �]�٬� Domain objects
				webManagerVO = new WebManagerVO();
				webManagerVO.setManagerId(managerId);
				webManagerVO.setManagerName(rs.getString("managerName"));
				webManagerVO.setManagerEmail(rs.getString("managerEmail"));
				webManagerVO.setManagerAccount(rs.getString("managerAccount"));
				webManagerVO.setManagerPassword(rs.getString("managerPassword"));
				webManagerVO.setManagerPic(rs.getBytes("managerPic"));
				webManagerVO.setManagerStatus(rs.getInt("managerStatus"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return webManagerVO;
	}

	@Override
	public List<WebManagerVO> getAll() {
		List<WebManagerVO> list = new ArrayList<WebManagerVO>();
		WebManagerVO webManagerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// webManagerVO �]�٬� Domain objects
				webManagerVO = new WebManagerVO();
				webManagerVO.setManagerId(rs.getInt("managerId"));
				webManagerVO.setManagerName(rs.getString("ManagerName"));
				webManagerVO.setManagerEmail(rs.getString("ManagerEmail"));
				webManagerVO.setManagerAccount(rs.getString("ManagerAccount"));
				webManagerVO.setManagerPassword(rs.getString("ManagerPassword"));
				webManagerVO.setManagerPic(rs.getBytes("managerPic"));
				webManagerVO.setManagerStatus(rs.getInt("managerStatus"));
				list.add(webManagerVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}