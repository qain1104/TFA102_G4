package com.reply.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class REPLYJNDIDAO implements REPLYDAO_interface {
	
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
			"INSERT INTO REPLY (articleSN,userId,replyContent,replyLikes,replyDate,replyUpDate,replyStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT replySN,articleSN,userId,replyContent,replyLikes,replyDate,replyUpDate,replyStatus FROM REPLY order by replySN";
		private static final String GET_ONE_STMT = 
			"SELECT replySN,articleSN,userId,replyContent,replyLikes,replyDate,replyUpDate,replyStatus FROM REPLY where replySN = ?";
		private static final String DELETE = 
			"DELETE FROM REPLY where replySN = ?";
		private static final String UPDATE = 
			"UPDATE REPLY set articleSN=? ,userId=? ,replyContent=? ,replyLikes=? ,replyDate=? ,replyUpDate=? ,replyStatus=?  where replySN = ?";
	
	
	@Override
	public int insert(REPLYVO replyVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,replyVO.getArticleSN());
			pstmt.setInt(2,replyVO.getUserId());
			pstmt.setBytes(3,replyVO.getReplyContent());
			pstmt.setInt(4,replyVO.getReplyLikes());
			pstmt.setTimestamp(5,replyVO.getReplyDate());
			if(replyVO.getReplyUpDate()==null) {
				pstmt.setNull(6, java.sql.Types.INTEGER);
			}else {
			pstmt.setTimestamp(6,replyVO.getReplyUpDate());
			}
			pstmt.setInt(7,replyVO.getReplyStatus());
				
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
	public int update(REPLYVO replyVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,replyVO.getArticleSN());
			pstmt.setInt(2,replyVO.getUserId());
			pstmt.setBytes(3,replyVO.getReplyContent());
			pstmt.setInt(4,replyVO.getReplyLikes());
			pstmt.setTimestamp(5,replyVO.getReplyDate());
			if(replyVO.getReplyUpDate()==null) {
				pstmt.setNull(6, java.sql.Types.INTEGER);
			}else {
			pstmt.setTimestamp(6,replyVO.getReplyUpDate());
			}
			pstmt.setInt(7,replyVO.getReplyStatus());
			pstmt.setInt(8,replyVO.getReplySN());

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
	public int delete(Integer replySN) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, replySN);
			
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
	public REPLYVO findByPrimaryKey(Integer replySN) {
		REPLYVO replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, replySN);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				replyVO = new REPLYVO();
				replyVO.setReplySN(rs.getInt("replySN"));
				replyVO.setArticleSN(rs.getInt("articleSN"));
				replyVO.setUserId(rs.getInt("userId"));
				replyVO.setReplyContent(rs.getBytes("replyContent"));
				replyVO.setReplyLikes(rs.getInt("replyLikes"));
				replyVO.setReplyDate(rs.getTimestamp("replyDate"));
				replyVO.setReplyUpDate(rs.getTimestamp("replyUpDate"));
				replyVO.setReplyStatus(rs.getInt("replyStatus"));
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
		return replyVO;
	}

	@Override
	public List<REPLYVO> getAll() {
		List<REPLYVO> list = new ArrayList<REPLYVO>();
		REPLYVO replyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				replyVO = new REPLYVO();
				replyVO.setReplySN(rs.getInt("replySN"));
				replyVO.setArticleSN(rs.getInt("articleSN"));
				replyVO.setUserId(rs.getInt("userId"));
				replyVO.setReplyContent(rs.getBytes("replyContent"));
				replyVO.setReplyLikes(rs.getInt("replyLikes"));
				replyVO.setReplyDate(rs.getTimestamp("replyDate"));
				replyVO.setReplyUpDate(rs.getTimestamp("replyUpDate"));
				replyVO.setReplyStatus(rs.getInt("replyStatus"));
				list.add(replyVO); // Store the row in the vector
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
