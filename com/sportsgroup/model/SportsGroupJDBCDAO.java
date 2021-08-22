package com.sportsgroup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.participant.model.ParticipantJDBCDAO;
import com.participant.model.ParticipantVO;


public class SportsGroupJDBCDAO implements SportsGroupDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "Ricky";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO SPORTS_GROUP(userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks) VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,?)";
	private static final String GET_ALL_STMT = "SELECT sportsGroupSN,userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks FROM SPORTS_GROUP order by sportsGroupSN";
	private static final String GET_ONE_STMT = "SELECT sportsGroupSN,userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks FROM SPORTS_GROUP where sportsGroupSN = ?";
	private static final String DELETE = "DELETE FROM SPORTS_GROUP where sportsGroupSN = ?";
	private static final String UPDATE = "UPDATE SPORTS_GROUP set userId=?, sportsType=?, sportsLocation=?, exerciseTime=?, numberUpLimit=?, numberLowLimit=?, registTime=?, registTimeEnd=?, issueDATE=?, participantNumber=?, success=?, remarks=? where sportsGroupSN = ?";

	@Override
	public void insert(SportsGroupVO sportsGroupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, sportsGroupVO.getUserId());
			pstmt.setString(2, sportsGroupVO.getSportsType());
			pstmt.setString(3, sportsGroupVO.getSportsLocation());
			pstmt.setTimestamp(4, sportsGroupVO.getExerciseTime());
			pstmt.setInt(5, sportsGroupVO.getNumberUpLimit());
			pstmt.setInt(6, sportsGroupVO.getNumberLowLimit());
			pstmt.setTimestamp(7, sportsGroupVO.getRegistTime());
			pstmt.setTimestamp(8, sportsGroupVO.getRegistTimeEnd());
			pstmt.setTimestamp(9, sportsGroupVO.getIssueDATE());
			pstmt.setInt(10, sportsGroupVO.getParticipantNumber());
			pstmt.setInt(11, sportsGroupVO.getSuccess());
			pstmt.setString(12, sportsGroupVO.getRemarks());

			pstmt.executeUpdate();
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
	}

	@Override
	public void update(SportsGroupVO sportsGroupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, sportsGroupVO.getUserId());
			pstmt.setString(2, sportsGroupVO.getSportsType());
			pstmt.setString(3, sportsGroupVO.getSportsLocation());
			pstmt.setTimestamp(4, sportsGroupVO.getExerciseTime());
			pstmt.setInt(5, sportsGroupVO.getNumberUpLimit());
			pstmt.setInt(6, sportsGroupVO.getNumberLowLimit());
			pstmt.setTimestamp(7, sportsGroupVO.getRegistTime());
			pstmt.setTimestamp(8, sportsGroupVO.getRegistTimeEnd());
			pstmt.setTimestamp(9, sportsGroupVO.getIssueDATE());
			pstmt.setInt(10, sportsGroupVO.getParticipantNumber());
			pstmt.setInt(11, sportsGroupVO.getSuccess());
			pstmt.setString(12, sportsGroupVO.getRemarks());
			pstmt.setInt(13, sportsGroupVO.getSportsGroupSN());
			pstmt.executeUpdate();
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

	}

	@Override
	public void delete(Integer sportsGroupSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, sportsGroupSN);

			pstmt.executeUpdate();
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

	}

	@Override
	public SportsGroupVO findByPrimaryKey(Integer sportsGroupSN) {
		SportsGroupVO sportsGroupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sportsGroupSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				sportsGroupVO = new SportsGroupVO();
				sportsGroupVO.setSportsGroupSN(rs.getInt("sportsGroupSN"));
				sportsGroupVO.setUserId(rs.getInt("userId"));
				sportsGroupVO.setSportsType(rs.getString("sportsType"));
				sportsGroupVO.setSportsLocation(rs.getString("sportsLocation"));
				sportsGroupVO.setExerciseTime(rs.getTimestamp("exerciseTime"));
				sportsGroupVO.setNumberUpLimit(rs.getInt("numberUpLimit"));
				sportsGroupVO.setNumberLowLimit(rs.getInt("numberLowLimit"));
				sportsGroupVO.setRegistTime(rs.getTimestamp("registTime"));
				sportsGroupVO.setRegistTimeEnd(rs.getTimestamp("registTimeEnd"));
				sportsGroupVO.setIssueDATE(rs.getTimestamp("issueDATE"));
				sportsGroupVO.setParticipantNumber(rs.getInt("participantNumber"));
				sportsGroupVO.setSuccess(rs.getInt("success"));
				sportsGroupVO.setRemarks(rs.getString("remarks"));
			}
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
		return sportsGroupVO;
	}

	@Override
	public List<SportsGroupVO> getAll() {
		List<SportsGroupVO> list = new ArrayList<SportsGroupVO>();
		SportsGroupVO sportsGroupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sportsGroupVO = new SportsGroupVO();
				sportsGroupVO.setSportsGroupSN(rs.getInt("sportsGroupSN"));
				sportsGroupVO.setUserId(rs.getInt("userId"));
				sportsGroupVO.setSportsType(rs.getString("sportsType"));
				sportsGroupVO.setSportsLocation(rs.getString("sportsLocation"));
				sportsGroupVO.setExerciseTime(rs.getTimestamp("exerciseTime"));
				sportsGroupVO.setNumberUpLimit(rs.getInt("numberUpLimit"));
				sportsGroupVO.setNumberLowLimit(rs.getInt("numberLowLimit"));
				sportsGroupVO.setRegistTime(rs.getTimestamp("registTime"));
				sportsGroupVO.setRegistTimeEnd(rs.getTimestamp("registTimeEnd"));
				sportsGroupVO.setIssueDATE(rs.getTimestamp("issueDATE"));
				sportsGroupVO.setParticipantNumber(rs.getInt("participantNumber"));
				sportsGroupVO.setSuccess(rs.getInt("success"));
				sportsGroupVO.setRemarks(rs.getString("remarks"));
				list.add(sportsGroupVO); // Store the row in the list
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
	public static void main(String[] args) {
		SportsGroupJDBCDAO dao = new SportsGroupJDBCDAO();
		SportsGroupVO sportsGroupVO = new SportsGroupVO();
		sportsGroupVO.setUserId(1003);
		sportsGroupVO.setSportsType("aaaa");
		sportsGroupVO.setSportsLocation("urhome");
		sportsGroupVO.setExerciseTime(java.sql.Timestamp.valueOf("2021-08-09 01:51:00"));
		sportsGroupVO.setNumberUpLimit(5);
		sportsGroupVO.setNumberLowLimit(2);
		sportsGroupVO.setRegistTime(java.sql.Timestamp.valueOf("2021-08-08 01:51:00"));
		sportsGroupVO.setRegistTimeEnd(java.sql.Timestamp.valueOf("2021-08-09 01:51:00"));
		sportsGroupVO.setIssueDATE(java.sql.Timestamp.valueOf("2021-08-16 01:51:00"));
		sportsGroupVO.setParticipantNumber(1);
		sportsGroupVO.setSuccess(0);
		sportsGroupVO.setRemarks("java好難喔喔喔");
		dao.insert(sportsGroupVO);
		
		System.out.print("輸入成功");
	}

}
