package com.rlike.model;

import java.util.*;
import java.sql.*;

public class REPLY_LIKEJDBCDAO implements REPLY_LIKEDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO REPLY_LIKE (replySN,userId) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT replyLikeSN,replySN,userId FROM REPLY_LIKE order by replyLikeSN";
		private static final String GET_ONE_STMT = 
			"SELECT replyLikeSN,replySN,userId FROM REPLY_LIKE where replyLikeSN = ?";
		private static final String DELETE = 
			"DELETE FROM REPLY_LIKE where replyLikeSN = ?";
		private static final String UPDATE = 
			"UPDATE REPLY_LIKE set replySN=?, userId=? where replyLikeSN = ?";
	

	@Override
	public int insert(REPLY_LIKEVO rlikeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,rlikeVO.getReplySN());
			pstmt.setInt(2,rlikeVO.getUserId());
				
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return updateCount;
	}

	@Override
	public int update(REPLY_LIKEVO rlikeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(3,rlikeVO.getReplyLikeSN());
			pstmt.setInt(1,rlikeVO.getReplySN());
			pstmt.setInt(2,rlikeVO.getUserId());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return updateCount;
	}

	@Override
	public int delete(Integer replyLikeSN) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, replyLikeSN);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return updateCount;
	}

	@Override
	public REPLY_LIKEVO findByPrimaryKey(Integer replyLikeSN) {
		REPLY_LIKEVO rlikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, replyLikeSN);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rlikeVO = new REPLY_LIKEVO();
				rlikeVO.setReplyLikeSN(rs.getInt("replyLikeSN"));
				rlikeVO.setReplySN(rs.getInt("replySN"));
				rlikeVO.setUserId(rs.getInt("userId"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return rlikeVO;
	}

	@Override
	public List<REPLY_LIKEVO> getAll() {
		List<REPLY_LIKEVO> list = new ArrayList<REPLY_LIKEVO>();
		REPLY_LIKEVO rlikeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rlikeVO = new REPLY_LIKEVO();
				rlikeVO.setReplyLikeSN(rs.getInt("replyLikeSN"));
				rlikeVO.setReplySN(rs.getInt("replySN"));
				rlikeVO.setUserId(rs.getInt("userId"));
				list.add(rlikeVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public static void main(String[] args) {

		REPLY_LIKEJDBCDAO dao = new REPLY_LIKEJDBCDAO();

//		 新增
//		REPLY_LIKEVO aVO1 = new REPLY_LIKEVO();
//		aVO1.setReplySN(6004);
//		aVO1.setUserId(1004);
//		 int updateCount_insert = dao.insert(aVO1);
//		 System.out.println(updateCount_insert);
				

		 //修改
//		REPLY_LIKEVO aVO2 = new REPLY_LIKEVO();
//		aVO2.setReplyLikeSN(7004);
//		aVO2.setReplySN(6004);
//		aVO2.setUserId(1001);
//		 int updateCount_update = dao.update(aVO2);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete(7004);
//		 System.out.println(updateCount_delete);

		// 查詢
//		REPLY_LIKEVO aVO3 = dao.findByPrimaryKey(5001);
//		System.out.print(aVO3.getReplyLikeSN() + ",");
//		System.out.print(aVO3.getReplySN() + ",");
//		System.out.print(aVO3.getUserId() + ",");
//		System.out.println("---------------------");

//		// 查詢
//		List<REPLY_LIKEVO> list = dao.getAll();
//		for (REPLY_LIKEVO aEmp : list) {
//			System.out.print(aEmp.getReplyLikeSN() + ",");
//			System.out.print(aEmp.getReplySN() + ",");
//			System.out.print(aEmp.getUserId());
//			System.out.println();
//		}
	}
	
}
