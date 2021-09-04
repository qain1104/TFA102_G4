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

public class VenueWatchListJDBCDAO implements VenueWatchListDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "David";
	String password = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, venueWatchListVO.getVenueSN());
			pstmt.setInt(2, venueWatchListVO.getUserId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, venueWatchListVO.getVenueSN());
			pstmt.setInt(2, venueWatchListVO.getUserId());
			pstmt.setInt(3, venueWatchListVO.getVenueWatchListSN());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, venueWatchListSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueWatchListSN(rs.getInt("venueWatchListSN"));
				venueWatchListVO.setVenueSN(rs.getInt("VenueSN"));
				venueWatchListVO.setUserId(rs.getInt("UserId"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueWatchListSN(rs.getInt("VenueWatchListSN"));
				venueWatchListVO.setVenueSN(rs.getInt("VenueSN"));
				venueWatchListVO.setUserId(rs.getInt("UserId"));
				list.add(venueWatchListVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}

	@Override
	public void delete(Integer venueWatchListSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, venueWatchListSN);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	public static void main(String[]  args) {

		VenueWatchListJDBCDAO dao = new VenueWatchListJDBCDAO();

//		新增
//		VenueWatchListVO venueWatchListVO1 = new VenueWatchListVO();
//		venueWatchListVO1.setVenueSN(19004);
//		venueWatchListVO1.setUserId(1003);
//		dao.insert(venueWatchListVO1);
		
//		修改
		VenueWatchListVO VenueWatchListVO2 = new VenueWatchListVO();
		VenueWatchListVO2.setVenueWatchListSN(21003);
		VenueWatchListVO2.setVenueSN(19001);
		VenueWatchListVO2.setUserId(1001);
		dao.update(VenueWatchListVO2);
		
//		刪除
//		dao.delete(21005);
		

//		查單筆
		VenueWatchListVO VenueWatchListVO3 = dao.findByPrimaryKey(21003);
		System.out.print(VenueWatchListVO3.getVenueWatchListSN() + ",");
		System.out.print(VenueWatchListVO3.getVenueSN() + ",");
		System.out.println(VenueWatchListVO3.getUserId());
		System.out.println("---------------------");

//		查全部
		List<VenueWatchListVO> list = dao.getAll();
		for (VenueWatchListVO venueWatchListVO : list) {
			System.out.print(venueWatchListVO.getVenueWatchListSN() + ",");
			System.out.print(venueWatchListVO.getVenueSN() + ",");
			System.out.print(venueWatchListVO.getUserId());
			System.out.println();
		}
	}
		public List<VenueWatchListVO> getWatchListByUser(Integer userId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<VenueWatchListVO> trackingList = new ArrayList<VenueWatchListVO>();
			try {
//				con = DriverManager.getConnection(THEURL, USER, PASSWORD);
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
