package com.article.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ARTICLEJNDIDAO implements ARTICLEDAO_interface {
	
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
	
	private static final String INSERT_STMT = 
			"INSERT INTO ARTICLE (userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT articleSN,userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus FROM ARTICLE order by articleSN";
		private static final String GET_ONE_STMT = 
			"SELECT articleSN,userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus FROM ARTICLE where articleSN = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE where articleSN = ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE set articleClass=?,articleType=?,articleTitle=?,articleContent=?,articlePop=?,articleLikes=?,articleUpDate=?,articleStatus=? where articleSN = ?";
		
		private static final String GET_CLASS_STMT = 
				"SELECT articleSN,userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDate,articleUpDate,articleStatus FROM ARTICLE where articleClass = ?";
		
	
	@Override
	public int insert(ARTICLEVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
		}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,articleVO.getArticleClass());
			pstmt.setInt(2,articleVO.getArticleType());
			pstmt.setString(3,articleVO.getArticleTitle());
			pstmt.setBytes(4,articleVO.getArticleContent());
			pstmt.setInt(5,articleVO.getArticlePop());
			pstmt.setInt(6,articleVO.getArticleLikes());
			if(articleVO.getArticleUpDate()==null) {
				pstmt.setNull(7, java.sql.Types.INTEGER);
			}else {				
				pstmt.setTimestamp(7,articleVO.getArticleUpDate());
			}
			pstmt.setInt(8,articleVO.getArticleStatus());
			pstmt.setInt(9,articleVO.getArticleSN());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, articleSN);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}
