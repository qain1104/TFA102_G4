//本表由TFA10201黃鼎謙負責
package com.GeneralUser.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;
import com.GeneralUser.model.GeneralUserVO;

public class GeneralUserJDBCDAO implements GeneralUserDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO GENERAL_USER(registerStatus, userAccount, userPassword, userName, id, email, address, phone, profilePic , createdTime ,gender) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT userId,registerStatus,userAccount,userPassword,userName,id,email,address,phone,profilePic,createdTime,gender FROM GENERAL_USER order by userId";
	private static final String GET_ONE_STMT = "SELECT userId,registerStatus,userAccount,userPassword,userName,id,email,address,phone,profilePic,createdTime,gender FROM GENERAL_USER where userId = ?";
	private static final String DELETE = "DELETE FROM GENERAL_USER where userId = ?";
	private static final String UPDATE = "UPDATE GENERAL_USER set registerStatus=?,userAccount=?,userPassword=?,userName=?,id=?,email=?,address=?,phone=?,profilePic=?,createdTime=?,gender=? where userId=?";

	@Override
	public void insert(GeneralUserVO generalUserVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			// 執行前先設定參數(即pstmt裡的?)
			pstmt.setInt(1, generalUserVO.getRegisterStatus());
			pstmt.setString(2, generalUserVO.getUserAccount());
			pstmt.setString(3, generalUserVO.getUserPassword());
			pstmt.setString(4, generalUserVO.getUserName());
			pstmt.setString(5, generalUserVO.getId());
			pstmt.setString(6, generalUserVO.getEmail());
			pstmt.setString(7, generalUserVO.getAddress());
			pstmt.setString(8, generalUserVO.getPhone());
			pstmt.setBytes(9, generalUserVO.getProfilePic());
			pstmt.setTimestamp(10, generalUserVO.getCreatedTime());
			pstmt.setInt(11, generalUserVO.getGender());
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
	public void update(GeneralUserVO generalUserVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, generalUserVO.getRegisterStatus());
			pstmt.setString(2, generalUserVO.getUserAccount());
			pstmt.setString(3, generalUserVO.getUserPassword());
			pstmt.setString(4, generalUserVO.getUserName());
			pstmt.setString(5, generalUserVO.getId());
			pstmt.setString(6, generalUserVO.getEmail());
			pstmt.setString(7, generalUserVO.getAddress());
			pstmt.setString(8, generalUserVO.getPhone());
			pstmt.setBytes(9, generalUserVO.getProfilePic());
			pstmt.setTimestamp(10, generalUserVO.getCreatedTime());
			pstmt.setInt(11, generalUserVO.getGender());
			pstmt.setInt(12, generalUserVO.getUserId());
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
	public void delete(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, userId);
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
	public GeneralUserVO findByPrimaryKey(Integer userId) {

		GeneralUserVO generalUserVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// generalUserVO 也稱為 Domain objects
				generalUserVO = new GeneralUserVO();
				generalUserVO.setUserId(userId);
				generalUserVO.setRegisterStatus(rs.getInt("registerStatus"));
				generalUserVO.setUserAccount(rs.getString("userAccount"));
				generalUserVO.setUserPassword(rs.getString("userPassword"));
				generalUserVO.setUserName(rs.getString("userName"));
				generalUserVO.setId(rs.getString("id"));
				generalUserVO.setEmail(rs.getString("email"));
				generalUserVO.setAddress(rs.getString("address"));
				generalUserVO.setPhone(rs.getString("phone"));
				generalUserVO.setProfilePic(rs.getBytes("profilePic"));
				generalUserVO.setCreatedTime(rs.getTimestamp("createdTime"));
				generalUserVO.setGender(rs.getInt("gender"));
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
		return generalUserVO;
	}

	@Override
	public List<GeneralUserVO> getAll() {
		List<GeneralUserVO> list = new ArrayList<GeneralUserVO>();
		GeneralUserVO generalUserVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// generalUserVO 也稱為 Domain objects
				generalUserVO = new GeneralUserVO();
				generalUserVO.setUserId(rs.getInt("userId"));
				generalUserVO.setRegisterStatus(rs.getInt("registerStatus"));
				generalUserVO.setUserAccount(rs.getString("userAccount"));
				generalUserVO.setUserPassword(rs.getString("userPassword"));
				generalUserVO.setUserName(rs.getString("userName"));
				generalUserVO.setId(rs.getString("id"));
				generalUserVO.setEmail(rs.getString("email"));
				generalUserVO.setAddress(rs.getString("address"));
				generalUserVO.setPhone(rs.getString("phone"));
				generalUserVO.setProfilePic(rs.getBytes("profilePic"));
				generalUserVO.setCreatedTime(rs.getTimestamp("createdTime"));
				generalUserVO.setGender(rs.getInt("gender"));
				list.add(generalUserVO); // Store the row in the list
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
