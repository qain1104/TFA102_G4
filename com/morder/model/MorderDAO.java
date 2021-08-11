package com.morder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class MorderDAO implements MorderDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String ADD_MORDER = "INSERT INTO Morder (userId, couponId, purchaseDate, totalPrice, orderPayment, orderCard, orderCardYear, orderCardMonth, orderCompleteDate, orderDeliveyTypeId, receiver, receiverPhone, receiverAddress, storeId, storeName, storeAddress, shippingDate, deliveryDate, deliveryStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_MORDER = "DELETE FROM Morder WHERE orderSN = ?";
	public static final String UPDATE_MORDER = "UPDATE Morder SET userId = ?, couponId = ?, purchaseDate = ?, totalPrice = ?, orderPayment = ?, orderCard = ?, orderCardYear = ?, orderCardMonth = ?, orderCompleteDate = ?, orderDeliveyTypeId = ?, receiver = ?, receiverPhone = ?, receiverAddress = ?, storeId = ?, storeName = ?, storeAddress = ?, shippingDate = ?, deliveryDate = ?, deliveryStatus = ? where orderSN = ?";
	public static final String QUERY_MORDER = "SELECT * FROM Morder WHERE orderSN = ?";
	public static final String QUERY_MORDER_USER = "SELECT * FROM Morder WHERE userId = ?";
	public static final String QUERY_ALLMORDER = "SELECT * FROM Morder";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addMorder(MorderVO morder) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(ADD_MORDER);
			pstmt.setInt(1, morder.getUserId());
			
			if(null == morder.getCouponId()) {
				pstmt.setNull(2, Types.NULL);
			} else {
				pstmt.setInt(2, morder.getCouponId());					
			}
			
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4, morder.getTotalPrice());
			pstmt.setInt(5, morder.getOrderPayment());
			pstmt.setString(6, morder.getOrderCard());					
			pstmt.setString(7, morder.getOrderCardYear());					
			pstmt.setString(8, morder.getOrderCardMonth());					
			pstmt.setTimestamp(9, morder.getOrderCompleteDate());					
			pstmt.setInt(10, morder.getOrderDeliveyTypeId());	
			pstmt.setString(11, morder.getReceiver());
			pstmt.setString(12, morder.getReceiverPhone());
			pstmt.setString(13, morder.getReceiverAddress());
			
			if(null == morder.getStoreId()) {
				pstmt.setNull(14, Types.NULL);
			} else {
				pstmt.setInt(14, morder.getStoreId());					
			}
			
			pstmt.setString(15, morder.getStoreName());					
			pstmt.setString(16, morder.getStoreAddress());					
			pstmt.setTimestamp(17, morder.getShippingDate());					
			pstmt.setTimestamp(18, morder.getDeliveryDate());					
			pstmt.setInt(19, 0);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void deleteMorder(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_MORDER);
			pstmt.setInt(1, orderSN);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void updateMorder(MorderVO morder) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_MORDER);
			pstmt.setInt(1, morder.getUserId());
			pstmt.setInt(2, morder.getCouponId());				
			pstmt.setTimestamp(3, morder.getPurchaseDate());
			pstmt.setInt(4, morder.getTotalPrice());
			pstmt.setInt(5, morder.getOrderPayment());
			pstmt.setString(6, morder.getOrderCard());				
			pstmt.setString(7, morder.getOrderCardYear());
			pstmt.setString(8, morder.getOrderCardMonth());	
			pstmt.setTimestamp(9, morder.getOrderCompleteDate());	
			pstmt.setInt(10, morder.getOrderDeliveyTypeId());	
			pstmt.setString(11, morder.getReceiver());
			pstmt.setString(12, morder.getReceiverPhone());
			pstmt.setString(13, morder.getReceiverAddress());
			pstmt.setInt(14, morder.getStoreId());
			pstmt.setString(15, morder.getStoreName());
			pstmt.setString(16, morder.getStoreAddress());
			pstmt.setTimestamp(17, morder.getShippingDate());
			pstmt.setTimestamp(18, morder.getDeliveryDate());
			pstmt.setInt(19, 0);
			pstmt.setInt(20, morder.getOrderSN());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public MorderVO getMorder(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MorderVO morder = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_MORDER);
			pstmt.setInt(1, orderSN);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer userId = rs.getInt("userId");
				Integer couponId = rs.getInt("couponId");
				Timestamp purchaseDate = rs.getTimestamp("purchaseDate");
				Integer totalPrice = rs.getInt("totalPrice");
				Integer orderPayment = rs.getInt("orderPayment");
				String orderCard = rs.getString("orderCard");
				String orderCardYear = rs.getString("orderCardYear");
				String orderCardMonth = rs.getString("orderCardMonth");
				Timestamp orderCompleteDate = rs.getTimestamp("orderCompleteDate");
				Integer orderDeliveyTypeId = rs.getInt("orderDeliveyTypeId");
				String receiver = rs.getString("receiver");
				String receiverPhone = rs.getString("receiverPhone");
				String receiverAddress = rs.getString("receiverAddress");
				Integer storeId = rs.getInt("storeId");
				String storeName = rs.getString("storeName");
				String storeAddress = rs.getString("storeAddress");
				Timestamp shippingDate = rs.getTimestamp("shippingDate");
				Timestamp deliveryDate = rs.getTimestamp("deliveryDate");
				Integer deliveryStatus = rs.getInt("deliveryStatus");
				morder = new MorderVO(orderSN, userId, couponId, purchaseDate, totalPrice,
						orderPayment, orderCard, orderCardYear, orderCardMonth,
						orderCompleteDate, orderDeliveyTypeId, receiver, receiverPhone,
						receiverAddress, storeId, storeName, storeAddress, shippingDate,
						deliveryDate, deliveryStatus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		return morder;
	}

	@Override
	public List<MorderVO> getAllMorder() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MorderVO> list = new ArrayList<MorderVO>();
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_ALLMORDER);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer orderSN = rs.getInt("orderSN");
				Integer userId = rs.getInt("userId");
				Integer couponId = rs.getInt("couponId");
				Timestamp purchaseDate = rs.getTimestamp("purchaseDate");
				Integer totalPrice = rs.getInt("totalPrice");
				Integer orderPayment = rs.getInt("orderPayment");
				String orderCard = rs.getString("orderCard");
				String orderCardYear = rs.getString("orderCardYear");
				String orderCardMonth = rs.getString("orderCardMonth");
				Timestamp orderCompleteDate = rs.getTimestamp("orderCompleteDate");
				Integer orderDeliveyTypeId = rs.getInt("orderDeliveyTypeId");
				String receiver = rs.getString("receiver");
				String receiverPhone = rs.getString("receiverPhone");
				String receiverAddress = rs.getString("receiverAddress");
				Integer storeId = rs.getInt("storeId");
				String storeName = rs.getString("storeName");
				String storeAddress = rs.getString("storeAddress");
				Timestamp shippingDate = rs.getTimestamp("shippingDate");
				Timestamp deliveryDate = rs.getTimestamp("deliveryDate");
				Integer deliveryStatus = rs.getInt("deliveryStatus");
				MorderVO morder = new MorderVO(orderSN, userId, couponId, purchaseDate, totalPrice,
						orderPayment, orderCard, orderCardYear, orderCardMonth,
						orderCompleteDate, orderDeliveyTypeId, receiver, receiverPhone,
						receiverAddress, storeId, storeName, storeAddress, shippingDate,
						deliveryDate, deliveryStatus);
				list.add(morder);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public MorderVO getMorderByUser(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MorderVO morder = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_MORDER_USER);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {;
				Integer orderSN = rs.getInt("orderSN");
				Integer couponId = rs.getInt("couponId");
				Timestamp purchaseDate = rs.getTimestamp("purchaseDate");
				Integer totalPrice = rs.getInt("totalPrice");
				Integer orderPayment = rs.getInt("orderPayment");
				String orderCard = rs.getString("orderCard");
				String orderCardYear = rs.getString("orderCardYear");
				String orderCardMonth = rs.getString("orderCardMonth");
				Timestamp orderCompleteDate = rs.getTimestamp("orderCompleteDate");
				Integer orderDeliveyTypeId = rs.getInt("orderDeliveyTypeId");
				String receiver = rs.getString("receiver");
				String receiverPhone = rs.getString("receiverPhone");
				String receiverAddress = rs.getString("receiverAddress");
				Integer storeId = rs.getInt("storeId");
				String storeName = rs.getString("storeName");
				String storeAddress = rs.getString("storeAddress");
				Timestamp shippingDate = rs.getTimestamp("shippingDate");
				Timestamp deliveryDate = rs.getTimestamp("deliveryDate");
				Integer deliveryStatus = rs.getInt("deliveryStatus");
				morder = new MorderVO(orderSN, userId, couponId, purchaseDate, totalPrice,
						orderPayment, orderCard, orderCardYear, orderCardMonth,
						orderCompleteDate, orderDeliveyTypeId, receiver, receiverPhone,
						receiverAddress, storeId, storeName, storeAddress, shippingDate,
						deliveryDate, deliveryStatus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		return morder;
	}

}
