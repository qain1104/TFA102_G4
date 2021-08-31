package com.sportsgroup.model;

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

import com.participant.model.ParticipantJDBCDAO;
import com.participant.model.ParticipantVO;

public class SportsGroupJDBCDAO implements SportsGroupDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO SPORTS_GROUP(userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks) VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,?)";
	private static final String GET_ALL_STMT = "SELECT sportsGroupSN,userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks FROM SPORTS_GROUP order by sportsGroupSN";
	private static final String GET_ONE_STMT = "SELECT sportsGroupSN,userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks FROM SPORTS_GROUP where sportsGroupSN = ?";
	private static final String DELETE = "DELETE FROM SPORTS_GROUP where sportsGroupSN = ?";
	private static final String UPDATE = "UPDATE SPORTS_GROUP set userId=?, sportsType=?, sportsLocation=?, exerciseTime=?, numberUpLimit=?, numberLowLimit=?, registTime=?, registTimeEnd=?, issueDATE=?, participantNumber=?, success=?, remarks=? where sportsGroupSN = ?";
	Integer key = null;
	@Override
	
	public void insert(SportsGroupVO sportsGroupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			
			String[] cols = { "sportsGroupSN" };
			pstmt = con.prepareStatement(INSERT_STMT,cols);

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
			System.out.println("AAAAAAQQQQQ");
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				 key = rs.getInt(1); // 只支援欄位索引值取得自增主鍵值
				System.out.println("自增主鍵值 = " + key + "(剛新增成功的揪團編號)");
				
				
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}

			rs.close();
			
		}  catch (SQLException se) {
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
			con = ds.getConnection();
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
		}  catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, sportsGroupSN);

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
			con = ds.getConnection();
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
		}  catch (SQLException se) {
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
			con = ds.getConnection();
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



}
