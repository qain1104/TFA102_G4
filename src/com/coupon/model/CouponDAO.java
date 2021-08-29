package com.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO implements CouponDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://mysql5257.chickenkiller.com:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	public static final String ADD_COUPON = "INSERT INTO COUPON (couponInfo, couponName, couponStarting, couponEnding, couponSN, couponDiscount) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String DELETE_COUPON = "DELETE FROM COUPON WHERE couponId = ?";
	public static final String DELETE_COUPON_SN = "DELETE FROM COUPON WHERE couponSN = ?";
	public static final String UPDATE_COUPON = "UPDATE COUPON SET couponInfo = ?, couponName = ?, couponStarting = ?, couponEnding = ?, couponSN = ?, couponDiscount = ? where couponId = ?";
	public static final String QUERY_COUPON = "SELECT * FROM COUPON WHERE couponId = ?";
	public static final String QUERY_COUPON_SN = "SELECT * FROM COUPON WHERE couponSN = ?";
	public static final String QUERY_ALLCOUPON = "SELECT * FROM COUPON";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addCoupon(CouponVO coupon) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(ADD_COUPON);
			pstmt.setString(1, coupon.getCouponInfo());
			pstmt.setString(2, coupon.getCouponName());
			pstmt.setTimestamp(3, coupon.getCouponStarting());
			pstmt.setTimestamp(4, coupon.getCouponEnding());
			pstmt.setString(5, coupon.getCouponSN());
			pstmt.setInt(6, coupon.getCouponDiscount());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void deleteCoupon(Integer couponId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_COUPON);
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateCoupon(CouponVO coupon) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_COUPON);
			pstmt.setString(1, coupon.getCouponInfo());
			pstmt.setString(2, coupon.getCouponName());
			pstmt.setTimestamp(3, coupon.getCouponStarting());
			pstmt.setTimestamp(4, coupon.getCouponEnding());
			pstmt.setString(5, coupon.getCouponSN());
			pstmt.setInt(6, coupon.getCouponDiscount());
			pstmt.setInt(7, coupon.getCouponId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public CouponVO getCoupon(Integer couponId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CouponVO coupon = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_COUPON);
			pstmt.setInt(1, couponId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String couponInfo = rs.getString("couponInfo");
				String couponName = rs.getString("couponName");
				Timestamp couponStarting = rs.getTimestamp("couponStarting");
				Timestamp couponEnding = rs.getTimestamp("couponEnding");
				String couponSN = rs.getString("couponSN");
				Integer couponDiscount = rs.getInt("couponDiscount");
				coupon = new CouponVO(couponId, couponInfo, couponName, couponStarting, couponEnding, couponSN, couponDiscount);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return coupon;
	}

	@Override
	public List<CouponVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CouponVO> list = new ArrayList<CouponVO>();
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_ALLCOUPON);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer couponId = rs.getInt("couponId");
				String couponInfo = rs.getString("couponInfo");
				String couponName = rs.getString("couponName");
				Timestamp couponStarting = rs.getTimestamp("couponStarting");
				Timestamp couponEnding = rs.getTimestamp("couponEnding");
				String couponSN = rs.getString("couponSN");
				Integer couponDiscount = rs.getInt("couponDiscount");
				CouponVO coupon = new CouponVO(couponId, couponInfo, couponName, couponStarting, couponEnding, couponSN, couponDiscount);
				list.add(coupon);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}


	@Override
	public CouponVO getCouponBySn(String couponSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CouponVO coupon = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_COUPON_SN);
			pstmt.setString(1, couponSN);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer couponId = rs.getInt("couponId");
				String couponInfo = rs.getString("couponInfo");
				String couponName = rs.getString("couponName");
				Timestamp couponStarting = rs.getTimestamp("couponStarting");
				Timestamp couponEnding = rs.getTimestamp("couponEnding");
				Integer couponDiscount = rs.getInt("couponDiscount");
				coupon = new CouponVO(couponId, couponInfo, couponName, couponStarting, couponEnding, couponSN, couponDiscount);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return coupon;
	}

}
