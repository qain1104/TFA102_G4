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
	public void insert(SportsGroupVO sportsGroupSNVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, sportsGroupSNVO.getUserId());
			pstmt.setString(2, sportsGroupSNVO.getSportsType());
			pstmt.setString(3, sportsGroupSNVO.getSportsLocation());
			pstmt.setTimestamp(4, sportsGroupSNVO.getExerciseTime());
			pstmt.setInt(5, sportsGroupSNVO.getNumberUpLimit());
			pstmt.setInt(6, sportsGroupSNVO.getNumberLowLimit());
			pstmt.setTimestamp(7, sportsGroupSNVO.getRegistTime());
			pstmt.setTimestamp(8, sportsGroupSNVO.getRegistTimeEnd());
			pstmt.setTimestamp(9, sportsGroupSNVO.getIssueDATE());
			pstmt.setInt(10, sportsGroupSNVO.getParticipantNumber());
			pstmt.setInt(11, sportsGroupSNVO.getSuccess());
			pstmt.setString(12, sportsGroupSNVO.getRemarks());

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
	public void update(SportsGroupVO sportsGroupSNVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, sportsGroupSNVO.getUserId());
			pstmt.setString(2, sportsGroupSNVO.getSportsType());
			pstmt.setString(3, sportsGroupSNVO.getSportsLocation());
			pstmt.setTimestamp(4, sportsGroupSNVO.getExerciseTime());
			pstmt.setInt(5, sportsGroupSNVO.getNumberUpLimit());
			pstmt.setInt(6, sportsGroupSNVO.getNumberLowLimit());
			pstmt.setTimestamp(7, sportsGroupSNVO.getRegistTime());
			pstmt.setTimestamp(8, sportsGroupSNVO.getRegistTimeEnd());
			pstmt.setTimestamp(9, sportsGroupSNVO.getIssueDATE());
			pstmt.setInt(10, sportsGroupSNVO.getParticipantNumber());
			pstmt.setInt(11, sportsGroupSNVO.getSuccess());
			pstmt.setString(12, sportsGroupSNVO.getRemarks());
			pstmt.setInt(13, sportsGroupSNVO.getSportsGroupSN());
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
		SportsGroupVO sportsGroupSNVO = null;
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
				// empVo ¤]ºÙ¬° Domain objects
				sportsGroupSNVO = new SportsGroupVO();
				sportsGroupSNVO.setSportsGroupSN(rs.getInt("sportsGroupSN"));
				sportsGroupSNVO.setUserId(rs.getInt("userId"));
				sportsGroupSNVO.setSportsType(rs.getString("sportsType"));
				sportsGroupSNVO.setSportsLocation(rs.getString("sportsLocation"));
				sportsGroupSNVO.setExerciseTime(rs.getTimestamp("exerciseTime"));
				sportsGroupSNVO.setNumberUpLimit(rs.getInt("numberUpLimit"));
				sportsGroupSNVO.setNumberLowLimit(rs.getInt("numberLowLimit"));
				sportsGroupSNVO.setRegistTime(rs.getTimestamp("registTime"));
				sportsGroupSNVO.setRegistTimeEnd(rs.getTimestamp("registTimeEnd"));
				sportsGroupSNVO.setIssueDATE(rs.getTimestamp("issueDATE"));
				sportsGroupSNVO.setParticipantNumber(rs.getInt("participantNumber"));
				sportsGroupSNVO.setSuccess(rs.getInt("success"));
				sportsGroupSNVO.setRemarks(rs.getString("remarks"));
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
		return sportsGroupSNVO;
	}

	@Override
	public List<SportsGroupVO> getAll() {
		List<SportsGroupVO> list = new ArrayList<SportsGroupVO>();
		SportsGroupVO sportsGroupSNVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sportsGroupSNVO = new SportsGroupVO();
				sportsGroupSNVO.setSportsGroupSN(rs.getInt("sportsGroupSN"));
				sportsGroupSNVO.setUserId(rs.getInt("userId"));
				sportsGroupSNVO.setSportsType(rs.getString("sportsType"));
				sportsGroupSNVO.setSportsLocation(rs.getString("sportsLocation"));
				sportsGroupSNVO.setExerciseTime(rs.getTimestamp("exerciseTime"));
				sportsGroupSNVO.setNumberUpLimit(rs.getInt("numberUpLimit"));
				sportsGroupSNVO.setNumberLowLimit(rs.getInt("numberLowLimit"));
				sportsGroupSNVO.setRegistTime(rs.getTimestamp("registTime"));
				sportsGroupSNVO.setRegistTimeEnd(rs.getTimestamp("registTimeEnd"));
				sportsGroupSNVO.setIssueDATE(rs.getTimestamp("issueDATE"));
				sportsGroupSNVO.setParticipantNumber(rs.getInt("participantNumber"));
				sportsGroupSNVO.setSuccess(rs.getInt("success"));
				sportsGroupSNVO.setRemarks(rs.getString("remarks"));
				list.add(sportsGroupSNVO); // Store the row in the list
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
		SportsGroupVO sportsGroupSNVO = dao.findByPrimaryKey(9004);
		System.out.print(sportsGroupSNVO);
	}

}
