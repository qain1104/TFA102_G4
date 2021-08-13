package com.report.model;

import java.util.*;

import javax.sql.rowset.serial.SerialException;

import java.sql.*;

public class REPORTJDBCDAO implements REPORTDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO REPORT (articleSN,replySN,userId,reportClass,reportContent,reportDate,reportStatus,managerId,reportAuditDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT reportSN,articleSN,replySN,userId,reportClass,reportContent,reportDate,reportStatus,managerId,reportAuditDate FROM REPORT order by reportSN";
	private static final String GET_ONE_STMT = "SELECT reportSN,articleSN,replySN,userId,reportClass,reportContent,reportDate,reportStatus,managerId,reportAuditDate FROM REPORT where reportSN = ?";
	private static final String DELETE = "DELETE FROM REPORT where reportSN = ?";
	private static final String UPDATE = "UPDATE REPORT set articleSN=? ,replySN=? ,userId=? ,reportClass=? ,reportContent=? ,reportDate=? ,reportStatus=? ,managerId=? ,reportAuditDate=?  where reportSN = ?";

	@Override
	public int insert(REPORTVO reportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, reportVO.getArticleSN());
			
			if(reportVO.getReplySN()==null) {
				pstmt.setNull(2, java.sql.Types.INTEGER);
			}else {
				pstmt.setInt(2, reportVO.getReplySN());
				
			}
			
			pstmt.setInt(3, reportVO.getUserId());
			pstmt.setInt(4, reportVO.getReportClass());
			
			if(reportVO.getReportContent()==null) {
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}else {
				pstmt.setString(5, reportVO.getReportContent());
			}
			
			
			pstmt.setTimestamp(6, reportVO.getReportDate());
			pstmt.setInt(7, reportVO.getReportStatus());
			
			if(reportVO.getManagerId()==null) {
				pstmt.setNull(8, java.sql.Types.INTEGER);
			}else {
				pstmt.setInt(8, reportVO.getManagerId());	
			}
			
			if(reportVO.getReportAuditDate()==null) {
				pstmt.setNull(9, java.sql.Types.INTEGER);
			}else {
				pstmt.setTimestamp(9, reportVO.getReportAuditDate());
			}
			

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return updateCount;
	}

	@Override
	public int update(REPORTVO reportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(10, reportVO.getReportSN());
			pstmt.setInt(1, reportVO.getArticleSN());
			if(reportVO.getReplySN()==null) {
				pstmt.setNull(2, java.sql.Types.INTEGER);
			}else {
				pstmt.setInt(2, reportVO.getReplySN());
				
			}
			pstmt.setInt(3, reportVO.getUserId());
			pstmt.setInt(4, reportVO.getReportClass());
			if(reportVO.getReportContent()==null) {
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}else {
				pstmt.setString(5, reportVO.getReportContent());
			}
			pstmt.setTimestamp(6, reportVO.getReportDate());
			pstmt.setInt(7, reportVO.getReportStatus());
			
			if(reportVO.getManagerId()==null) {
				pstmt.setNull(8, java.sql.Types.INTEGER);
			}else {
				pstmt.setInt(8, reportVO.getManagerId());	
			}
			
			if(reportVO.getReportAuditDate()==null) {
				pstmt.setNull(9, java.sql.Types.INTEGER);
			}else {
				pstmt.setTimestamp(9, reportVO.getReportAuditDate());
			}

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return updateCount;
	}

	@Override
	public int delete(Integer REPORTSN) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, REPORTSN);

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return updateCount;
	}

	@Override
	public REPORTVO findByPrimaryKey(Integer reportSN) {
		REPORTVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reportVO = new REPORTVO();
				reportVO.setReportSN(rs.getInt("reportSN"));
				reportVO.setArticleSN(rs.getInt("articleSN"));
				reportVO.setReplySN(rs.getInt("replySN"));
				reportVO.setUserId(rs.getInt("userId"));
				reportVO.setReportClass(rs.getInt("reportClass"));
				reportVO.setReportContent(rs.getString("reportContent"));
				reportVO.setReportDate(rs.getTimestamp("reportDate"));
				reportVO.setReportStatus(rs.getInt("reportStatus"));
				reportVO.setManagerId(rs.getInt("managerId"));
				reportVO.setReportAuditDate(rs.getTimestamp("reportAuditDate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return reportVO;
	}

	@Override
	public List<REPORTVO> getAll() {
		List<REPORTVO> list = new ArrayList<REPORTVO>();
		REPORTVO reportVO = null;

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
				reportVO = new REPORTVO();
				reportVO.setReportSN(rs.getInt("reportSN"));
				reportVO.setArticleSN(rs.getInt("articleSN"));
				reportVO.setReplySN(rs.getInt("replySN"));
				reportVO.setUserId(rs.getInt("userId"));
				reportVO.setReportClass(rs.getInt("reportClass"));
				reportVO.setReportContent(rs.getString("reportContent"));
				reportVO.setReportDate(rs.getTimestamp("reportDate"));
				reportVO.setReportStatus(rs.getInt("reportStatus"));
				reportVO.setManagerId(rs.getInt("managerId"));
				reportVO.setReportAuditDate(rs.getTimestamp("reportAuditDate"));
				list.add(reportVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return list;
	}

	public static void main(String[] args) throws SerialException, SQLException {

		REPORTJDBCDAO dao = new REPORTJDBCDAO();

		// 新增
//		REPORTVO aVO1 = new REPORTVO();
//		aVO1.setArticleSN(4006);
//		aVO1.setReplySN(6002);
//		aVO1.setUserId(1005);
//		aVO1.setReportClass(0);
//		aVO1.setReportContent("12345");
//		aVO1.setReportDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//		aVO1.setReportStatus(0);
//		aVO1.setManagerId(null);
//		aVO1.setReportAuditDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//		int updateCount_insert = dao.insert(aVO1);
//		System.out.println(updateCount_insert);

		// 修改
//		REPORTVO aVO2 = new REPORTVO();
//		aVO2.setReportSN(8014);
//		aVO2.setArticleSN(4001);
//		aVO2.setReplySN(null);
//		aVO2.setUserId(1005);
//		aVO2.setReportClass(0);
//		aVO2.setReportContent("1234567");
//		aVO2.setReportDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//		aVO2.setReportStatus(0);
//		aVO2.setManagerId(null);
//		aVO2.setReportAuditDate(null);
//		int updateCount_update = dao.update(aVO2);
//		 System.out.println(updateCount_update);

		// 刪除
//		 int updateCount_delete = dao.delete(8013);
//		 System.out.println(updateCount_delete);

		// 查詢
//		REPORTVO aVO3 = dao.findByPrimaryKey(8003);
//		System.out.println(aVO3.toString());
//		System.out.println("---------------------");
//		// 查詢
//		List<REPORTVO> list = dao.getAll();
//		for (REPORTVO aEmp : list) {
//			System.out.println(aEmp.toString());
//		}

	}

}
