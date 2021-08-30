//本表由TFA10201黃鼎謙負責
package com.CorpUser.model;

import java.sql.Connection;
import java.sql.Date;
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

import java.sql.Timestamp;

public class CorpUserJDBCDAO implements CorpUserDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Sportify");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CORP_USER(registerStatus,corpAccount,corpPassword,companyName,ltdNo,email,phone,address,profilePic,createdTime) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT corpUserId,registerStatus,corpAccount,corpPassword,companyName,ltdNo,email,phone,address,profilePic,createdTime FROM CORP_USER order by corpUserId";
	private static final String GET_ONE_STMT = "SELECT corpUserId,registerStatus,corpAccount,corpPassword,companyName,ltdNo,email,phone,address,profilePic,createdTime FROM CORP_USER where corpUserId=?";
	private static final String DELETE = "DELETE FROM CORP_USER where corpUserId = ?";
	private static final String UPDATE = "UPDATE CORP_USER set registerStatus=?,corpAccount=?,corpPassword=?,companyName=?,ltdNo=?,email=?,phone=?,address=?,profilePic=?,createdTime=? where corpUserId=?";

	@Override
	public void insert(CorpUserVO corpUserVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);// 讓資料庫可預先編譯SQL指令，用於須變數傳遞且重複使用的指令
			// 執行前先設定參數(即pstmt裡的?)
			pstmt.setInt(1, corpUserVO.getRegisterStatus());
			pstmt.setString(2, corpUserVO.getCorpAccount());
			pstmt.setString(3, corpUserVO.getCorpPassword());
			pstmt.setString(4, corpUserVO.getCompanyName());
			pstmt.setString(5, corpUserVO.getLtdNo());
			pstmt.setString(6, corpUserVO.getEmail());
			pstmt.setString(7, corpUserVO.getPhone());
			pstmt.setString(8, corpUserVO.getAddress());
			pstmt.setBytes(9, corpUserVO.getProfilePic());
			pstmt.setTimestamp(10, corpUserVO.getCreatedTime());
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
	public void update(CorpUserVO corpUserVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, corpUserVO.getRegisterStatus());
			pstmt.setString(2, corpUserVO.getCorpAccount());
			pstmt.setString(3, corpUserVO.getCorpPassword());
			pstmt.setString(4, corpUserVO.getCompanyName());
			pstmt.setString(5, corpUserVO.getLtdNo());
			pstmt.setString(6, corpUserVO.getEmail());
			pstmt.setString(7, corpUserVO.getPhone());
			pstmt.setString(8, corpUserVO.getAddress());
			pstmt.setBytes(9, corpUserVO.getProfilePic());
			pstmt.setTimestamp(10, corpUserVO.getCreatedTime());
			pstmt.setInt(11, corpUserVO.getCorpUserId());
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
	public void delete(Integer corpUserId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, corpUserId);
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public CorpUserVO findByPrimaryKey(Integer corpUserId) {
		CorpUserVO corpUserVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, corpUserId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// corpUserVO 也稱為 Domain objects
				corpUserVO = new CorpUserVO();
				corpUserVO.setCorpUserId(corpUserId);
				corpUserVO.setRegisterStatus(rs.getInt("registerStatus"));
				corpUserVO.setCorpAccount(rs.getString("corpAccount"));
				corpUserVO.setCorpPassword(rs.getString("corpPassword"));
				corpUserVO.setCompanyName(rs.getString("companyName"));
				corpUserVO.setLtdNo(rs.getString("ltdNo"));
				corpUserVO.setEmail(rs.getString("email"));
				corpUserVO.setPhone(rs.getString("phone"));
				corpUserVO.setAddress(rs.getString("address"));
				corpUserVO.setProfilePic(rs.getBytes("profilePic"));
				corpUserVO.setCreatedTime(rs.getTimestamp("createdTime"));
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
		return corpUserVO;
	}

	@Override
	public List<CorpUserVO> getAll() {
		List<CorpUserVO> list = new ArrayList<CorpUserVO>();
		CorpUserVO corpUserVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// CorpUserVO 也稱為 Domain objects
				corpUserVO = new CorpUserVO();
				corpUserVO.setCorpUserId(rs.getInt("corpUserId"));
				corpUserVO.setRegisterStatus(rs.getInt("registerStatus"));
				corpUserVO.setCorpAccount(rs.getString("corpAccount"));
				corpUserVO.setCorpPassword(rs.getString("corpPassword"));
				corpUserVO.setCompanyName(rs.getString("companyName"));
				corpUserVO.setLtdNo(rs.getString("ltdNo"));
				corpUserVO.setEmail(rs.getString("email"));
				corpUserVO.setPhone(rs.getString("phone"));
				corpUserVO.setAddress(rs.getString("address"));
				corpUserVO.setProfilePic(rs.getBytes("profilePic"));
				corpUserVO.setCreatedTime(rs.getTimestamp("createdTime"));
				list.add(corpUserVO); // Store the row in the list
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