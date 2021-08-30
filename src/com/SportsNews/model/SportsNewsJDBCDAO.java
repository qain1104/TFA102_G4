//本表由TFA10201黃鼎謙負責
package com.SportsNews.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SportsNewsJDBCDAO implements SportsNewsDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO SPORT_NEWS(managerId,title,content,newsDate,newsSource,newsType)VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT newsSn,managerId,title,content,newsDate,newsSource,newsType FROM SPORT_NEWS order by newsSn";
	private static final String GET_ONE_STMT = "SELECT newsSn,managerId,title,content,newsDate,newsSource,newsType FROM SPORT_NEWS where newsSn = ?";
	private static final String DELETE = "DELETE FROM SPORT_NEWS where newsSn = ?";
	private static final String UPDATE = "UPDATE SPORT_NEWS set managerId=?,title=?,content=?,newsDate=?,newsSource=?,newsType=? where newsSn=?";

	@Override
	public void insert(SportsNewsVO sportsNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);// 讓資料庫可預先編譯SQL指令，用於須變數傳遞且重複使用的指令
			// 執行前先設定參數(即pstmt裡的?)
			pstmt.setInt(1, sportsNewsVO.getManagerId());
			pstmt.setString(2, sportsNewsVO.getTitle());
			pstmt.setString(3, sportsNewsVO.getContent());
			pstmt.setTimestamp(4, sportsNewsVO.getNewsDate());
			pstmt.setString(5, sportsNewsVO.getNewsSource());
			pstmt.setInt(6, sportsNewsVO.getNewsType());
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
	public void update(SportsNewsVO sportsNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, sportsNewsVO.getManagerId());
			pstmt.setString(2, sportsNewsVO.getTitle());
			pstmt.setString(3, sportsNewsVO.getContent());
			pstmt.setTimestamp(4, sportsNewsVO.getNewsDate());
			pstmt.setString(5, sportsNewsVO.getNewsSource());
			pstmt.setInt(6, sportsNewsVO.getNewsType());
			pstmt.setInt(7, sportsNewsVO.getNewsSn());
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
	public void delete(Integer newsSn) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, newsSn);
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
	public SportsNewsVO findByPrimaryKey(Integer newsSn) {

		SportsNewsVO sportsNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, newsSn);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sportsNewsVO = new SportsNewsVO();
				sportsNewsVO.setNewsSn(newsSn);
				sportsNewsVO.setManagerId(rs.getInt("managerId"));
				sportsNewsVO.setTitle(rs.getString("title"));
				sportsNewsVO.setContent(rs.getString("content"));
				sportsNewsVO.setNewsDate(rs.getTimestamp("newsDate"));
				sportsNewsVO.setNewsSource(rs.getString("newsSource"));
				sportsNewsVO.setNewsType(rs.getInt("newsType"));
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
		return sportsNewsVO;
	}

	@Override
	public List<SportsNewsVO> getAll() {
		List<SportsNewsVO> list = new ArrayList<SportsNewsVO>();
		SportsNewsVO sportsNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// SportsNewsVO 也稱為 Domain objects
				sportsNewsVO = new SportsNewsVO();
				sportsNewsVO.setNewsSn(rs.getInt("newsSn"));
				sportsNewsVO.setManagerId(rs.getInt("managerId"));
				sportsNewsVO.setTitle(rs.getString("title"));
				sportsNewsVO.setContent(rs.getString("content"));
				sportsNewsVO.setNewsDate(rs.getTimestamp("newsDate"));
				sportsNewsVO.setNewsSource(rs.getString("newsSource"));
				sportsNewsVO.setNewsType(rs.getInt("newsType"));
				list.add(sportsNewsVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
