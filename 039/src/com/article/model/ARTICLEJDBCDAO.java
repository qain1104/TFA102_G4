package com.article.model;

import java.util.*;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import java.io.InputStream;
import java.sql.*;

public class ARTICLEJDBCDAO implements ARTICLEDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ARTICLE (userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT articleSN,userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus FROM ARTICLE order by articleSN";
		private static final String GET_ONE_STMT = 
			"SELECT articleSN,userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus FROM ARTICLE where articleSN = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE where articleSN = ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE set userId=? ,articleClass=?,articleType=?,articleTitle=?,articleContent=?,articlePop=?,articleLikes=?,articleDate=?,articleUpDate=?,articleStatus=? where articleSN = ?";
		
		private static final String GET_CLASS_STMT = 
				"SELECT articleSN,userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus FROM ARTICLE where articleClass = ?";
		
	
	@Override
	public int insert(ARTICLEVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,articleVO.getUserId());
			pstmt.setInt(2,articleVO.getArticleClass());
			pstmt.setInt(3,articleVO.getArticleType());
			pstmt.setString(4,articleVO.getArticleTitle());
			pstmt.setBytes(5,articleVO.getArticleContent());
			pstmt.setInt(6,articleVO.getArticlePop());
			pstmt.setInt(7,articleVO.getArticleLikes());
			pstmt.setTimestamp(8,articleVO.getarticleDate());
			if(articleVO.getArticleUpDate()==null) {
				pstmt.setNull(9, java.sql.Types.INTEGER);
			}else {				
				pstmt.setTimestamp(9,articleVO.getArticleUpDate());
			}
			pstmt.setInt(10,articleVO.getArticleStatus());
				
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
	public int update(ARTICLEVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,articleVO.getUserId());
			pstmt.setInt(2,articleVO.getArticleClass());
			pstmt.setInt(3,articleVO.getArticleType());
			pstmt.setString(4,articleVO.getArticleTitle());
			pstmt.setBytes(5,articleVO.getArticleContent());
			pstmt.setInt(6,articleVO.getArticlePop());
			pstmt.setInt(7,articleVO.getArticleLikes());
			pstmt.setTimestamp(8,articleVO.getarticleDate());
			if(articleVO.getArticleUpDate()==null) {
				pstmt.setNull(9, java.sql.Types.INTEGER);
			}else {				
				pstmt.setTimestamp(9,articleVO.getArticleUpDate());
			}
			pstmt.setInt(10,articleVO.getArticleStatus());
			pstmt.setInt(11,articleVO.getArticleSN());

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
	public int delete(Integer articleSN) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, articleSN);
			
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
	public ARTICLEVO findByPrimaryKey(Integer articleSN) {
		ARTICLEVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, articleSN);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ARTICLEVO();
				articleVO.setArticleSN(rs.getInt("articleSN"));
				articleVO.setUserId(rs.getInt("userId"));
				articleVO.setArticleClass(rs.getInt("articleClass"));
				articleVO.setArticleContent(rs.getBytes("articleContent"));
				articleVO.setArticleType(rs.getInt("articleType"));
				articleVO.setArticleTitle(rs.getString("articleTitle"));
				articleVO.setArticlePop(rs.getInt("articlePop"));
				articleVO.setArticleLikes(rs.getInt("articleLikes"));
				articleVO.setArticleDate(rs.getTimestamp("articleDate"));
				articleVO.setArticleUpDate(rs.getTimestamp("articleUpDate"));
				articleVO.setArticleStatus(rs.getInt("articleStatus"));
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
		return articleVO;
	}
	
	@Override
	public List<ARTICLEVO> findByClassKey(Integer articleClass) {
		List<ARTICLEVO> list = new ArrayList<ARTICLEVO>();
		ARTICLEVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CLASS_STMT);
			
			pstmt.setInt(1, articleClass);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				articleVO = new ARTICLEVO();
				articleVO.setArticleSN(rs.getInt("articleSN"));
				articleVO.setUserId(rs.getInt("userId"));
				articleVO.setArticleClass(rs.getInt("articleClass"));
				articleVO.setArticleContent(rs.getBytes("articleContent"));
				articleVO.setArticleType(rs.getInt("articleType"));
				articleVO.setArticleTitle(rs.getString("articleTitle"));
				articleVO.setArticlePop(rs.getInt("articlePop"));
				articleVO.setArticleLikes(rs.getInt("articleLikes"));
				articleVO.setArticleDate(rs.getTimestamp("articleDate"));
				articleVO.setArticleUpDate(rs.getTimestamp("articleUpDate"));
				articleVO.setArticleStatus(rs.getInt("articleStatus"));
				list.add(articleVO); // Store the row in the vector
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
	

	@Override
	public List<ARTICLEVO> getAll() {
		List<ARTICLEVO> list = new ArrayList<ARTICLEVO>();
		ARTICLEVO articleVO = null;

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
				articleVO = new ARTICLEVO();
				articleVO.setArticleSN(rs.getInt("articleSN"));
				articleVO.setUserId(rs.getInt("userId"));
				articleVO.setArticleClass(rs.getInt("articleClass"));
				articleVO.setArticleContent(rs.getBytes("articleContent"));
				articleVO.setArticleType(rs.getInt("articleType"));
				articleVO.setArticleTitle(rs.getString("articleTitle"));
				articleVO.setArticlePop(rs.getInt("articlePop"));
				articleVO.setArticleLikes(rs.getInt("articleLikes"));
				articleVO.setArticleDate(rs.getTimestamp("articleDate"));
				articleVO.setArticleUpDate(rs.getTimestamp("articleUpDate"));
				articleVO.setArticleStatus(rs.getInt("articleStatus"));
				list.add(articleVO); // Store the row in the vector
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

//	public static void main(String[] args) throws SerialException, SQLException {
//
//		ARTICLEJDBCDAO dao = new ARTICLEJDBCDAO();
//		
//		byte[] bytes = "A byte array".getBytes();
//		Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		//		 新增
//			ARTICLEVO aVO1 = new ARTICLEVO();
//			aVO1.setUserId(1001);
//			aVO1.setArticleClass(0);
//			aVO1.setArticleContent(blob);
//			aVO1.setArticleType(0);
//			aVO1.setArticleTitle("標題");
//			aVO1.setArticlePop(0);
//			aVO1.setArticleLikes(0);
//			aVO1.setArticleDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//			aVO1.setArticleUpDate(null);
//			aVO1.setArticleStatus(0);
//			int updateCount_insert = dao.insert(aVO1);
//			System.out.println(updateCount_insert);
	    
				

		 //修改
//		ARTICLEVO aVO2 = new ARTICLEVO();
//		aVO2.setArticleSN(4001);
//		aVO2.setUserId(1002);
//		aVO2.setArticleClass(0);
//		aVO2.setArticleContent(blob);
//		aVO2.setArticleType(0);
//		aVO2.setArticleTitle("標題");
//		aVO2.setArticlePop(0);
//		aVO2.setArticleLikes(0);
//		aVO2.setArticleDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//		aVO2.setArticleUpDate(null);
//		aVO2.setArticleStatus(0);
//		 int updateCount_update = dao.update(aVO2);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete(4007);
//		 System.out.println(updateCount_delete);

		// 查詢
//		ARTICLEVO aVO3 = dao.findByPrimaryKey(4001);
//		System.out.println(aEmp.toString());
//		System.out.println("---------------------");

//		// 查詢
//		List<ARTICLEVO> list = dao.getAll();
//		for (ARTICLEVO aEmp : list) {
//			System.out.println(aEmp.toString());
//		}
		
//		List<ARTICLEVO> list = dao.findByClassKey(1);
//		for (ARTICLEVO aEmp : list) {
//			System.out.println(aEmp.toString());
//		}
//	}
	
	
}
