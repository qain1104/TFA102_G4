package com.venue_watchlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.venue.model.VenueVO;

public class VenueWatchListJNDIDAO implements VenueWatchListDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String password = "123456";

	private static final String INSERT_STMT = "INSERT INTO VENUE_WATCHLIST(venueSN,userId) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM VENUE_WATCHLIST ORDER BY venueWatchListSN";
	private static final String GET_ONE_STMT = "SELECT * FROM VENUE_WATCHLIST WHERE venueWatchListSN = ?";
	private static final String UPDATE = "UPDATE VENUE_WATCHLIST set venueSN=?, userId=? where venueWatchListSN = ?";
	private static final String DELETE = "DELETE FROM VENUE_WATCHLIST WHERE venueWatchListSN=?";
	public static final String QUERY_LIST_USER = "SELECT * FROM VENUE_WATCHLIST WHERE userId = ?";
	
	
	// jndi
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Sportify");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
	
	
	
	@Override
	public void insert(VenueWatchListVO venueWatchListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, venueWatchListVO.getVenueSN());
			pstmt.setInt(2, venueWatchListVO.getUserId());

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(VenueWatchListVO venueWatchListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, venueWatchListVO.getVenueSN());
			pstmt.setInt(2, venueWatchListVO.getUserId());
			pstmt.setInt(3, venueWatchListVO.getVenueWatchListSN());

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public VenueWatchListVO findByPrimaryKey(Integer venueWatchListSN) {

		VenueWatchListVO venueWatchListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, venueWatchListSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueWatchListSN(rs.getInt("venueWatchListSN"));
				venueWatchListVO.setVenueSN(rs.getInt("VenueSN"));
				venueWatchListVO.setUserId(rs.getInt("UserId"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return venueWatchListVO;
	}

	@Override
	public List<VenueWatchListVO> getAll() {

		List<VenueWatchListVO> list = new ArrayList<VenueWatchListVO>();
		VenueWatchListVO venueWatchListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueWatchListSN(rs.getInt("VenueWatchListSN"));
				venueWatchListVO.setVenueSN(rs.getInt("VenueSN"));
				venueWatchListVO.setUserId(rs.getInt("UserId"));
				list.add(venueWatchListVO);
			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer venueWatchListSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, venueWatchListSN);

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<VenueWatchListVO> getWatchListByUser(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VenueWatchListVO> trackingList = new ArrayList<VenueWatchListVO>();
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(QUERY_LIST_USER);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer venueWatchListSN = rs.getInt("venueWatchListSN");
				Integer venueSN = rs.getInt("venueSN");
				VenueWatchListVO watchList = new VenueWatchListVO(venueWatchListSN, venueSN, userId);
				trackingList.add(watchList);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return trackingList;
	}
}
