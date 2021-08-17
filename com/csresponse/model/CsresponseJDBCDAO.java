package com.csresponse.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CsresponseJDBCDAO implements CsresponseDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "Ricky";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO emp2 (managerId,userId,csDescription,csResponse,submittedDate,closingDate,responseStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT responseSN,managerId,userId,csDescription,csResponse,submittedDate,closingDate,responseStatus FROM CSRESPONSE order by responseSN";
	private static final String GET_ONE_STMT = 
			"SELECT responseSN,managerId,userId,csDescription,csResponse,submittedDate,closingDate,responseStatus FROM CSRESPONSE where responseSN = ?";
	private static final String DELETE = 
			"DELETE FROM CSRESPONSE where responseSN = ?";
	private static final String UPDATE = 
			"UPDATE CSRESPONSE set managerId=?, userId=?, csDescription=?, csResponse=?, submittedDate=?, closingDate=?,  responseStatus=? where responseSN = ?";

	@Override
	public void insert(CsresponseVO csresponseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, csresponseVO.getManagerId());
			pstmt.setInt(2, csresponseVO.getUserId());
			pstmt.setString(3, csresponseVO.getCsDescription());
			pstmt.setString(4, csresponseVO.getCsResponse());
			pstmt.setDate(5, csresponseVO.getSubmittedDate());
			pstmt.setDate(6, csresponseVO.getClosingDate());
			pstmt.setInt(7, csresponseVO.getResponseStatus());
			
			pstmt.executeUpdate();	
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
	public void update(CsresponseVO csresponseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, csresponseVO.getManagerId());
			pstmt.setInt(2, csresponseVO.getUserId());
			pstmt.setString(3, csresponseVO.getCsDescription());
			pstmt.setString(4, csresponseVO.getCsResponse());
			pstmt.setDate(5, csresponseVO.getSubmittedDate());
			pstmt.setDate(6, csresponseVO.getClosingDate());
			pstmt.setInt(7, csresponseVO.getResponseStatus());
			pstmt.setInt(8, csresponseVO.getResponseSN());
			
			pstmt.executeUpdate();
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
	public void delete(Integer responseSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, responseSN);
			
			pstmt.executeUpdate();
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
	public CsresponseVO findByPrimaryKey(Integer responseSN) {
		CsresponseVO csresponseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, responseSN);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				csresponseVO = new CsresponseVO();
				csresponseVO.setResponseSN(rs.getInt("ResponseSN"));
				csresponseVO.setManagerId(rs.getInt("ManagerId"));
				csresponseVO.setUserId(rs.getInt("UserId"));
				csresponseVO.setCsDescription(rs.getString("CsDescription"));
				csresponseVO.setCsResponse(rs.getString("CsResponse"));
				csresponseVO.setSubmittedDate(rs.getDate("SubmittedDate"));
				csresponseVO.setClosingDate(rs.getDate("ClosingDate"));
				csresponseVO.setResponseStatus(rs.getInt("ResponseStatus"));
				
				
			}
		}catch (ClassNotFoundException e) {
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
		return csresponseVO;
	}

	@Override
	public List<CsresponseVO> getAll() {
		List<CsresponseVO> list = new ArrayList<CsresponseVO>();
		CsresponseVO csresponseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				csresponseVO = new CsresponseVO();
				csresponseVO.setResponseSN(rs.getInt("ResponseSN"));
				csresponseVO.setManagerId(rs.getInt("ManagerId"));
				csresponseVO.setUserId(rs.getInt("UserId"));
				csresponseVO.setCsDescription(rs.getString("CsDescription"));
				csresponseVO.setCsResponse(rs.getString("CsResponse"));
				csresponseVO.setSubmittedDate(rs.getDate("SubmittedDate"));
				csresponseVO.setClosingDate(rs.getDate("ClosingDate"));
				csresponseVO.setResponseStatus(rs.getInt("ResponseStatus"));
			}
			
		}catch (ClassNotFoundException e) {
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
		return list;
	}

}
