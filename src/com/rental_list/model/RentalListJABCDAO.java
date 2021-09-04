package com.rental_list.model;

import java.util.*;
import java.sql.*;

public class RentalListJABCDAO implements RentalListDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "David";
	String password = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

	}

	@Override
	public void update(RentalListVO rentalListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer rentalListSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rentalListSN);

			pstmt.executeUpdate();

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

	}
	
	@Override
	public RentalListVO findByPrimaryKey(Integer rentalListSN) {

		RentalListVO rentalListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	public static void main(String[] args) {

		RentalListJABCDAO dao = new RentalListJABCDAO();

		
		// 新增
//		RentalListVO rentalListVO1 = new RentalListVO();
//		rentalListVO1.setVenueSN(19005);
//		rentalListVO1.setUserId(1005);
//		rentalListVO1.setReturnStatus(1);
//		rentalListVO1.setRentalDate(java.sql.Timestamp.valueOf("2020-01-01 11:11:11"));
//		rentalListVO1.setVenueReview("燈光明亮");
//		rentalListVO1.setRentalTime("100");
//		dao.insert(rentalListVO1);
		
		// 修改
		RentalListVO rentalListVO2 = new RentalListVO();
		rentalListVO2.setRentalListSN(22001);
		rentalListVO2.setVenueSN(19001);
		rentalListVO2.setUserId(1001);
		rentalListVO2.setReturnStatus(1);
		rentalListVO2.setRentalDate(java.sql.Timestamp.valueOf("2020-01-01 10:10:10"));
		rentalListVO2.setVenueReview("環境乾淨");
		rentalListVO2.setBeforeUse(null);
		rentalListVO2.setAfterUse(null);
		rentalListVO2.setRentalTime("100");
		dao.update(rentalListVO2);
		
		// 刪除
//		dao.delete(22011);
		
		
//		查單一
		RentalListVO rentalListVO3 = dao.findByPrimaryKey(22002);
		System.out.print(rentalListVO3.getRentalListSN() + ",");
		System.out.print(rentalListVO3.getVenueSN() + ",");
		System.out.print(rentalListVO3.getUserId() + ",");
		System.out.print(rentalListVO3.getReturnStatus() + ",");
		System.out.print(rentalListVO3.getRentalDate() + ",");
		System.out.print(rentalListVO3.getVenueReview() + ",");
		System.out.print(rentalListVO3.getBeforeUse() + ",");
		System.out.print(rentalListVO3.getAfterUse() + ",");
		System.out.println(rentalListVO3.getRentalTime());
		System.out.println("---------------------");
		
		//查全部
		List<RentalListVO> list = dao.getAll();
		for (RentalListVO rentalListVO : list) {
			System.out.print(rentalListVO.getRentalListSN() + ",");
			System.out.print(rentalListVO.getVenueSN() + ",");
			System.out.print(rentalListVO.getUserId() + ",");
			System.out.print(rentalListVO.getReturnStatus() + ",");
			System.out.print(rentalListVO.getRentalDate() + ",");
			System.out.print(rentalListVO.getVenueReview() + ",");
			System.out.print(rentalListVO.getBeforeUse() + ",");
			System.out.print(rentalListVO.getAfterUse() + ",");
			System.out.print(rentalListVO.getRentalTime());
			System.out.println();
		}
	}
}
