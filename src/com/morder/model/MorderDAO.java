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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_list.model.Order_listVO;


public class MorderDAO implements MorderDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://mysql5257.chickenkiller.com:3306/TFA102_G4?rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
//	public static final String THEURL = "jdbc:mysql://localhost:3306/TFA102_G4?rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	public static final String ADD_MORDER = "INSERT INTO MORDER (userId, couponId, purchaseDate, totalPrice, orderPayment, orderCard, orderCardYear, orderCardMonth, orderCompleteDate, orderDeliveyTypeId, receiver, receiverPhone, receiverAddress, storeId, storeName, storeAddress, shippingDate, deliveryDate, deliveryStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_MORDER = "DELETE FROM MORDER WHERE orderSN = ?";
	public static final String UPDATE_MORDER = "UPDATE MORDER SET userId = ?, couponId = ?, purchaseDate = ?, totalPrice = ?, orderPayment = ?, orderCard = ?, orderCardYear = ?, orderCardMonth = ?, orderCompleteDate = ?, orderDeliveyTypeId = ?, receiver = ?, receiverPhone = ?, receiverAddress = ?, storeId = ?, storeName = ?, storeAddress = ?, shippingDate = ?, deliveryDate = ?, deliveryStatus = ? where orderSN = ?";
	public static final String QUERY_MORDER = "SELECT * FROM MORDER WHERE orderSN = ?";
	public static final String QUERY_MORDER_USER = "SELECT * FROM MORDER WHERE userId = ? order by purchaseDate DESC";
	public static final String QUERY_ALLMORDER = "SELECT * FROM MORDER";
	public static final String ADD_ORDERLIST = "INSERT INTO ORDER_LIST (productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback) VALUES (?, ?, ?, ?, ?, ?)";
	
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
	public void addMorder(MorderVO morder) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
			
			if(morder.getStoreId() == null) {
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
	public void deleteMorder(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_MORDER);
			pstmt.setInt(1, orderSN);
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
	public void updateMorder(MorderVO morder) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
	public MorderVO getMorder(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MorderVO morder = null;
		
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
		return morder;
	}

	@Override
	public List<MorderVO> getAllMorder() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MorderVO> list = new ArrayList<MorderVO>();
		
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
	public List<MorderVO> getMorderByUser(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MorderVO> morderList = new ArrayList<MorderVO>();
		
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
				MorderVO morder = new MorderVO(orderSN, userId, couponId, purchaseDate, totalPrice,
						orderPayment, orderCard, orderCardYear, orderCardMonth,
						orderCompleteDate, orderDeliveyTypeId, receiver, receiverPhone,
						receiverAddress, storeId, storeName, storeAddress, shippingDate,
						deliveryDate, deliveryStatus);
				morderList.add(morder);
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
		return morderList;
	}
	
	// 新增訂單連同訂單明細一起新增，回傳訂單PK鍵
	@Override
	public Integer addMorderWithList(MorderVO morder, List<Order_listVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String cols[] = { "orderSN" };
		Integer newOrderSN = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(ADD_MORDER, cols);
			// 新增訂單
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
			
			if(morder.getStoreId() == null) {
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
			// 拿到PK鍵的值
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				newOrderSN = rs.getInt(1);
				System.out.println("自增主鍵值 = " + newOrderSN + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			// 新增訂單明細
			addWithOrder(con, newOrderSN, list);
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			try {
				
				con.rollback();				
			
			} catch(SQLException se) {
			
				throw new RuntimeException("Fail to add order." + se.getMessage());
				
			}
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
		} return newOrderSN;
	}
	
	private static void addWithOrder(Connection con, Integer newOrderSN, List<Order_listVO> list) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(ADD_ORDERLIST);
			for(Order_listVO orderList: list) {
				
				pstmt.setInt(1, orderList.getProductSpecId());
				pstmt.setInt(2, newOrderSN);
				pstmt.setInt(3, orderList.getOrderCost());
				pstmt.setInt(4, orderList.getPurchaseQuantity());
				if(orderList.getProductRate() == null) {
					pstmt.setNull(5, Types.NULL);
				} else {
					pstmt.setInt(5, orderList.getProductRate());					
				}
				pstmt.setString(6, orderList.getProductFeedback());
				pstmt.addBatch(); 
			}
			
			pstmt.executeBatch();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
