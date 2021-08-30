package com.order_delivery_type.model;

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

public class Order_delivery_typeDAO implements Order_delivery_typeDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://mysql5257.chickenkiller.com:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	public static final String ADD_TYPE = "INSERT INTO ORDER_DELIVERY_TYPE (deliveryType, deliveryFee) VALUES (?, ?)";
	public static final String DELETE_TYPE = "DELETE FROM ORDER_DELIVERY_TYPE WHERE orderDeliveryTypeId = ?";
	public static final String UPDATE_TYPE = "UPDATE ORDER_DELIVERY_TYPE SET deliveryType = ?, deliveryFee = ? where orderDeliveryTypeId = ?";
	public static final String QUERY_TYPE = "SELECT * FROM ORDER_DELIVERY_TYPE WHERE orderDeliveryTypeId = ?";
	public static final String QUERY_ALLTYPE = "SELECT * FROM ORDER_DELIVERY_TYPE";
	
	// jndi
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Sportify");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// jdbc
//	static {
//		try {
//			Class.forName(DRIVER);
//		} catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void addOrderDeliveryType(Order_delivery_typeVO type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_TYPE);
			pstmt.setString(1, type.getDeliveryType());
			pstmt.setInt(2, type.getDeliveryFee());
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	@Override
	public void deleteOrderDeliveryType(Integer orderDeliveryTypeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_TYPE);
			pstmt.setInt(1, orderDeliveryTypeId);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	@Override
	public void updateOrderDeliveryType(Order_delivery_typeVO type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_TYPE);
			pstmt.setString(1, type.getDeliveryType());
			pstmt.setInt(2, type.getDeliveryFee());
			pstmt.setInt(3, type.getOrderDeliveryTypeId());
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	@Override
	public Order_delivery_typeVO getOneType(Integer orderDeliveryTypeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order_delivery_typeVO odtVO = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(QUERY_TYPE);
			pstmt.setInt(1, orderDeliveryTypeId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String deliveryType = rs.getString("deliveryType");
				Integer deliveryFee = rs.getInt("deliveryFee");
				odtVO = new Order_delivery_typeVO(orderDeliveryTypeId, deliveryType, deliveryFee);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return odtVO;
	}
	@Override
	public List<Order_delivery_typeVO> getAllType() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order_delivery_typeVO> list = new ArrayList<Order_delivery_typeVO>();
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(QUERY_ALLTYPE);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer orderDeliveryTypeId = rs.getInt("orderDeliveryTypeId");
				String deliveryType = rs.getString("deliveryType");
				Integer deliveryFee = rs.getInt("deliveryFee");
				Order_delivery_typeVO odtVO = new Order_delivery_typeVO(orderDeliveryTypeId, deliveryType, deliveryFee);
				list.add(odtVO);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	

}
