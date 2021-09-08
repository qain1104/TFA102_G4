package com.venue.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.naming.java.javaURLContextFactory;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

public class VenueJDBCDAO implements VenueDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO VENUE(corpUserId,venueStatus,venueName,venueClass,venueInfo,venuePrice,venueAddress,venuePic,venueAccommodate,venuePhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM VENUE ORDER BY venueSN";
	private static final String GET_ONE_STMT = "SELECT * FROM VENUE WHERE venueSN = ?";
	private static final String UPDATE = "UPDATE VENUE set corpUserId=?, venueStatus=?, venueName=?, venueClass=?, venueInfo=?, venuePrice=?, venueAddress=?, venuePic=?, venueAccommodate=?, venuePhone=? where venueSN = ?";
	private static final String DELETE = "DELETE FROM VENUE WHERE venueSN=?";

	@Override
	public void insert(VenueVO venueVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, venueVO.getCorpUserId());
			pstmt.setInt(2, venueVO.getVenueStatus());
			pstmt.setString(3, venueVO.getVenueName());
			pstmt.setInt(4, venueVO.getVenueClass());
			pstmt.setString(5, venueVO.getVenueInfo());
			pstmt.setInt(6, venueVO.getVenuePrice());
			pstmt.setString(7, venueVO.getVenueAddress());
			pstmt.setBytes(8, venueVO.getVenuePic());
			pstmt.setInt(9, venueVO.getVenueAccommodate());
			pstmt.setString(10, venueVO.getVenuePhone());

			pstmt.executeUpdate();

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
	public void update(VenueVO venueVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, venueVO.getCorpUserId());
			pstmt.setInt(2, venueVO.getVenueStatus());
			pstmt.setString(3, venueVO.getVenueName());
			pstmt.setInt(4, venueVO.getVenueClass());
			pstmt.setString(5, venueVO.getVenueInfo());
			pstmt.setInt(6, venueVO.getVenuePrice());
			pstmt.setString(7, venueVO.getVenueAddress());
			pstmt.setBytes(8, venueVO.getVenuePic());
			pstmt.setInt(9, venueVO.getVenueAccommodate());
			pstmt.setString(10, venueVO.getVenuePhone());
			pstmt.setInt(11, venueVO.getVenueSN());

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
	public VenueVO findByPrimaryKey(Integer venueSN) {

		VenueVO venueVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, venueSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueVO = new VenueVO();
				venueVO.setVenueSN(rs.getInt("venuesn"));
				venueVO.setCorpUserId(rs.getInt("CorpUserId"));
				venueVO.setVenueStatus(rs.getInt("venuestatus"));
				venueVO.setVenueName(rs.getString("venuename"));
				venueVO.setVenueClass(rs.getInt("venueclass"));
				venueVO.setVenueInfo(rs.getString("venueinfo"));
				venueVO.setVenuePrice(rs.getInt("venueprice"));
				venueVO.setVenueAddress(rs.getString("venueaddress"));
				venueVO.setVenuePic(rs.getBytes("venuepic"));
				venueVO.setVenueAccommodate(rs.getInt("venueaccommodate"));
				venueVO.setVenuePhone(rs.getString("venuephone"));
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
		return venueVO;
	}

	@Override
	public List<VenueVO> getAll() {

		List<VenueVO> list = new ArrayList<VenueVO>();
		VenueVO venueVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				venueVO = new VenueVO();
				venueVO.setVenueSN(rs.getInt("venuesn"));
				venueVO.setCorpUserId(rs.getInt("CorpUserId"));
				venueVO.setVenueStatus(rs.getInt("venuestatus"));
				venueVO.setVenueName(rs.getString("venuename"));
				venueVO.setVenueClass(rs.getInt("venueClass"));
				venueVO.setVenueInfo(rs.getString("venueinfo"));
				venueVO.setVenuePrice(rs.getInt("venueprice"));
				venueVO.setVenueAddress(rs.getString("venueaddress"));
				venueVO.setVenuePic(rs.getBytes("venuepic"));
				venueVO.setVenueAccommodate(rs.getInt("venueaccommodate"));
				venueVO.setVenuePhone(rs.getString("venuephone"));
				list.add(venueVO);
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
		return list;
	}

	@Override
	public void delete(Integer venueSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, venueSN);

			pstmt.executeUpdate();

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
