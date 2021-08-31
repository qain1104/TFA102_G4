package com.participant.model;

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

import com.sportsgroup.model.SportsGroupJDBCDAO;
import com.sportsgroup.model.SportsGroupVO;




public class ParticipantJDBCDAO implements ParticipantDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO PARTICIPANT (sportsGroupSN,userId) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT participantID,sportsGroupSN,userId FROM PARTICIPANT order by ParticipantID";
	private static final String GET_ONE_STMT = "SELECT participantID,sportsGroupSN,userId FROM PARTICIPANT where participantID = ?";
	private static final String DELETE = "DELETE FROM PARTICIPANT where participantID = ?";
	private static final String UPDATE = "UPDATE PARTICIPANT set sportsGroupSN=?, userId=? where participantID = ?";

	@Override
	public void insert(ParticipantVO participantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, participantVO.getSportsGroupSN());
			pstmt.setInt(2, participantVO.getUserId());
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, participantVO.getSportsGroupSN());
			pstmt.setInt(2, participantVO.getUserId());
			pstmt.setInt(3, participantVO.getParticipantID());
			pstmt.executeUpdate();
			
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, participantID);
			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, participantID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				participantVO = new ParticipantVO();
				participantVO.setParticipantID(rs.getInt("ParticipantID"));
				participantVO.setSportsGroupSN(rs.getInt("SportsGroupSN"));
				participantVO.setUserId(rs.getInt("UserId"));
			}
			
		}catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				participantVO = new ParticipantVO();
				participantVO.setParticipantID(rs.getInt("ParticipantID"));
				participantVO.setSportsGroupSN(rs.getInt("SportsGroupSN"));
				participantVO.setUserId(rs.getInt("UserId"));
				list.add(participantVO);
			}
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
