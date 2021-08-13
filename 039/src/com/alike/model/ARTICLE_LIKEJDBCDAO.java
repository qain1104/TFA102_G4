package com.alike.model;

import java.util.*;
import java.sql.*;

public class ARTICLE_LIKEJDBCDAO implements ARTICLE_LIKEDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ARTICLE_LIKE (articleSN,userId) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT articleLikeSN,articleSN,userId FROM ARTICLE_LIKE order by articleLikeSN";
		private static final String GET_ONE_STMT = 
			"SELECT articleLikeSN,articleSN,userId FROM ARTICLE_LIKE where articleLikeSN = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE_LIKE where articleLikeSN = ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE_LIKE set articleSN=?, userId=? where articleLikeSN = ?";
	

	@Override
	public int insert(ARTICLE_LIKEVO alikeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,alikeVO.getArticleSN());
			pstmt.setInt(2,alikeVO.getUserId());
				
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
	public int update(ARTICLE_LIKEVO alikeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(3,alikeVO.getArticleLikeSN());
			pstmt.setInt(1,alikeVO.getArticleSN());
			pstmt.setInt(2,alikeVO.getUserId());

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
	public int delete(Integer articleLikeSN) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, articleLikeSN);
			
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
	public ARTICLE_LIKEVO findByPrimaryKey(Integer articleLikeSN) {
		ARTICLE_LIKEVO alikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, articleLikeSN);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				alikeVO = new ARTICLE_LIKEVO();
				alikeVO.setArticleLikeSN(rs.getInt("articleLikeSN"));
				alikeVO.setArticleSN(rs.getInt("articleSN"));
				alikeVO.setUserId(rs.getInt("userId"));
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
		return alikeVO;
	}

	@Override
	public List<ARTICLE_LIKEVO> getAll() {
		List<ARTICLE_LIKEVO> list = new ArrayList<ARTICLE_LIKEVO>();
		ARTICLE_LIKEVO alikeVO = null;

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
				alikeVO = new ARTICLE_LIKEVO();
				alikeVO.setArticleLikeSN(rs.getInt("articleLikeSN"));
				alikeVO.setArticleSN(rs.getInt("articleSN"));
				alikeVO.setUserId(rs.getInt("userId"));
				list.add(alikeVO); // Store the row in the vector
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

		ARTICLE_LIKEJDBCDAO dao = new ARTICLE_LIKEJDBCDAO();

//		 新增
//		ARTICLE_LIKEVO aVO1 = new ARTICLE_LIKEVO();
//		aVO1.setArticleSN(4002);
//		aVO1.setUserId(1002);
//		 int updateCount_insert = dao.insert(aVO1);
//		 System.out.println(updateCount_insert);
				

		 //修改
//		ARTICLE_LIKEVO aVO2 = new ARTICLE_LIKEVO();
//		aVO2.setArticleLikeSN(5002);
//		aVO2.setArticleSN(4005);
//		aVO2.setUserId(1002);
//		 int updateCount_update = dao.update(aVO2);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete(5007);
//		 System.out.println(updateCount_delete);

		// 查詢
//		ARTICLE_LIKEVO aVO3 = dao.findByPrimaryKey(5001);
//		System.out.print(aVO3.getArticleLikeSN() + ",");
//		System.out.print(aVO3.getArticleSN() + ",");
//		System.out.print(aVO3.getUserId() + ",");
//		System.out.println("---------------------");

//		// 查詢
//		List<ARTICLE_LIKEVO> list = dao.getAll();
//		for (ARTICLE_LIKEVO aEmp : list) {
//			System.out.print(aEmp.getArticleLikeSN() + ",");
//			System.out.print(aEmp.getArticleSN() + ",");
//			System.out.print(aEmp.getUserId());
//			System.out.println();
//		}
	}
	
}
