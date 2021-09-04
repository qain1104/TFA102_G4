package com.rental_list.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class RentalListJNDIDAO implements RentalListDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Sportify");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			};
	private static final String INSERT_STMT = "INSERT INTO RENTAL_LIST(venueSN,userId,returnStatus,rentalDate,venueReview,beforeUse,afterUse,rentalTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RENTAL_LIST ORDER BY rentalListSN";
	private static final String GET_ONE_STMT = "SELECT * FROM RENTAL_LIST WHERE rentalListSN = ?";
	private static final String UPDATE = "UPDATE RENTAL_LIST set venueSN=?, userId=?, returnStatus=?, rentalDate=?, venueReview=?, beforeUse=?, afterUse=?, rentalTime=? where rentalListSN = ?";
	private static final String DELETE = "DELETE FROM RENTAL_LIST WHERE rentalListSN = ?";
	
	@Override
	public void insert(RentalListVO rentalListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rentalListVO.getVenueSN());
			pstmt.setInt(2, rentalListVO.getUserId());
			pstmt.setInt(3, rentalListVO.getReturnStatus());
			pstmt.setTimestamp(4, rentalListVO.getRentalDate());
			pstmt.setString(5, rentalListVO.getVenueReview());
			pstmt.setBytes(6, rentalListVO.getBeforeUse());
			pstmt.setBytes(7, rentalListVO.getAfterUse());
			pstmt.setString(8, rentalListVO.getRentalTime());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	}

	@Override
	public void update(RentalListVO rentalListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rentalListVO.getVenueSN());
			pstmt.setInt(2, rentalListVO.getUserId());
			pstmt.setInt(3, rentalListVO.getReturnStatus());
			pstmt.setTimestamp(4, rentalListVO.getRentalDate());
			pstmt.setString(5, rentalListVO.getVenueReview());
			pstmt.setBytes(6, rentalListVO.getBeforeUse());
			pstmt.setBytes(7, rentalListVO.getAfterUse());
			pstmt.setString(8, rentalListVO.getRentalTime());
			pstmt.setInt(9, rentalListVO.getRentalListSN());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer rentalListSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rentalListSN);

			pstmt.executeUpdate();

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

	}
	
	@Override
	public RentalListVO findByPrimaryKey(Integer rentalListSN) {

		RentalListVO rentalListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rentalListSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalListVO = new RentalListVO();
				rentalListVO.setRentalListSN(rs.getInt("rentalListSN"));
				rentalListVO.setVenueSN(rs.getInt("VenueSN"));
				rentalListVO.setUserId(rs.getInt("UserId"));
				rentalListVO.setReturnStatus(rs.getInt("ReturnStatus"));
				rentalListVO.setRentalDate(rs.getTimestamp("RentalDate"));
				rentalListVO.setVenueReview(rs.getString("VenueReview"));
				rentalListVO.setBeforeUse(rs.getBytes("BeforeUse"));
				rentalListVO.setAfterUse(rs.getBytes("AfterUse"));
				rentalListVO.setRentalTime(rs.getString("RentalTime"));
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return rentalListVO;
	}

	@Override
	public List<RentalListVO> getAll() {

		List<RentalListVO> list = new ArrayList<RentalListVO>();
		RentalListVO rentalListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentalListVO = new RentalListVO();
				rentalListVO.setRentalListSN(rs.getInt("rentalListSN"));
				rentalListVO.setVenueSN(rs.getInt("VenueSN"));
				rentalListVO.setUserId(rs.getInt("UserId"));
				rentalListVO.setReturnStatus(rs.getInt("ReturnStatus"));
				rentalListVO.setRentalDate(rs.getTimestamp("RentalDate"));
				rentalListVO.setVenueReview(rs.getString("VenueReview"));
				rentalListVO.setBeforeUse(rs.getBytes("BeforeUse"));
				rentalListVO.setAfterUse(rs.getBytes("AfterUse"));
				rentalListVO.setRentalTime(rs.getString("RentalTime"));
				list.add(rentalListVO);
			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
