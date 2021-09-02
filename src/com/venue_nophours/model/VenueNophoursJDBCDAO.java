package com.venue_nophours.model;

import java.sql.Connection;
import java.sql.Timestamp;
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

import org.apache.naming.java.javaURLContextFactory;

import com.sun.tracing.dtrace.ArgsAttributes;
import com.venue.model.VenueDAO_interface;
import com.venue.model.VenueVO;

public class VenueNophoursJDBCDAO implements VenueNophoursDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO VENUE_NOPHOURS_TABLE(venueSN,venueDate,venueNophours) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM VENUE_NOPHOURS_TABLE ORDER BY VenueNophoursSN";
	private static final String GET_ONE_STMT = "SELECT * FROM VENUE_NOPHOURS_TABLE WHERE VenueNophoursSN = ?";
	private static final String UPDATE = "UPDATE VENUE_NOPHOURS_TABLE set venueSN=?, venueDate=?, venueNophours=? where VenueNophoursSN = ?";
	private static final String DELETE = "DELETE FROM VENUE_NOPHOURS_TABLE WHERE VenueNophoursSN=?";

	@Override
	public void insert(VenueNophoursVO venueNophoursVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, venueNophoursVO.getVenueSN());
			pstmt.setTimestamp(2,venueNophoursVO.getVenueDate());
//			if(venueNophoursVO.getVenueDate()==null) {
//				pstmt.setNull(2, java.sql.Types.INTEGER);
//			}else {
//			pstmt.setTimestamp(2,venueNophoursVO.getVenueDate());
//			}
			pstmt.setString(3, venueNophoursVO.getVenueNophours());

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
	public void update(VenueNophoursVO venueNophoursVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, venueNophoursVO.getVenueSN());
			pstmt.setTimestamp(2, venueNophoursVO.getVenueDate());
			pstmt.setString(3, venueNophoursVO.getVenueNophours());
			pstmt.setInt(4, venueNophoursVO.getVenueNophoursSN());

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
	public VenueNophoursVO findByPrimaryKey(Integer venueNophoursSN) {

		VenueNophoursVO venueNophoursVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, venueNophoursSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueNophoursVO = new VenueNophoursVO();
				venueNophoursVO.setVenueNophoursSN(rs.getInt("venueNophoursSN"));
				venueNophoursVO.setVenueSN(rs.getInt("venueSN"));
				venueNophoursVO.setVenueDate(rs.getTimestamp("venueDate"));
				venueNophoursVO.setVenueNophours(rs.getString("venueNophours"));
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
		return venueNophoursVO;
	}

	@Override
	public List<VenueNophoursVO> getAll() {

		List<VenueNophoursVO> list = new ArrayList<VenueNophoursVO>();
		VenueNophoursVO venueNophoursVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueNophoursVO = new VenueNophoursVO();
				venueNophoursVO.setVenueNophoursSN(rs.getInt("venueNophoursSN"));
				venueNophoursVO.setVenueSN(rs.getInt("venueSN"));
				venueNophoursVO.setVenueDate(rs.getTimestamp("venueDate"));
				venueNophoursVO.setVenueNophours(rs.getString("venueNophours"));
				list.add(venueNophoursVO);
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
	public void delete(Integer venueNophoursSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, venueNophoursSN);

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

	
}
