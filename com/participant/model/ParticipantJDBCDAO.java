package com.participant.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class ParticipantJDBCDAO implements ParticipantDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "Ricky";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO participant (sportsGroupSN,userId) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT participantID,sportsGroupSN,userId FROM participant order by ParticipantID";
	private static final String GET_ONE_STMT = "SELECT participantID,sportsGroupSN,userId FROM participant where participantID = ?";
	private static final String DELETE = "DELETE FROM participant where participantID = ?";
	private static final String UPDATE = "UPDATE participant set sportsGroupSN=?, userId=? where participantID = ?";

	@Override
	public void insert(ParticipantVO participantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, participantVO.getSportsGroupSN());
			pstmt.setInt(2, participantVO.getUserId());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public void update(ParticipantVO participantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, participantVO.getSportsGroupSN());
			pstmt.setInt(2, participantVO.getUserId());
			pstmt.setInt(3, participantVO.getParticipantID());
			pstmt.executeUpdate();
			
			
		}catch (ClassNotFoundException e) {
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
	public void delete(Integer participantID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, participantID);
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
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
	public ParticipantVO findByPrimaryKey(Integer participantID) {
		ParticipantVO participantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, participantID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				participantVO = new ParticipantVO();
				participantVO.setParticipantID(rs.getInt("ParticipantID"));
				participantVO.setSportsGroupSN(rs.getInt("SportsGroupSN"));
				participantVO.setUserId(rs.getInt("UserId"));
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return participantVO;
	}

	@Override
	public List<ParticipantVO> getAll() {
		List<ParticipantVO> list = new ArrayList<ParticipantVO>();
		ParticipantVO participantVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				participantVO = new ParticipantVO();
				participantVO.setParticipantID(rs.getInt("ParticipantID"));
				participantVO.setSportsGroupSN(rs.getInt("SportsGroupSN"));
				participantVO.setUserId(rs.getInt("UserId"));
				list.add(participantVO);
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
	
	public static void main(String[] args) {
		ParticipantJDBCDAO dao = new ParticipantJDBCDAO();
		ParticipantVO participantVO = dao.findByPrimaryKey(10001);
//		System.out.print(participantVO.getParticipantID() + ",");
//		System.out.print(participantVO.getSportsGroupSN() + ",");
//		System.out.print(participantVO.getUserId());
		System.out.print(participantVO);
		
		List<ParticipantVO> list = dao.getAll();
		for (ParticipantVO XX : list) {
			System.out.print(XX.getParticipantID() + ",");
			System.out.print(XX.getSportsGroupSN() + ",");
			System.out.print(XX.getUserId() + ",");
			System.out.println();
		}
//		DeptVO deptVO1 = new DeptVO();
//		deptVO1.setDname("製造部");
//		deptVO1.setLoc("中國江西");
//		dao.insert(deptVO1);
//		ParticipantVO participantVO1 = new ParticipantVO();
//		participantVO1.setSportsGroupSN(9003);
//		participantVO1.setUserId(1002);
//		dao.insert(participantVO1);
	}
}
