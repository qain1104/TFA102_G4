package com.order_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order_delivery_type.model.Order_delivery_typeVO;

public class Order_listDAO implements Order_listDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String ADD_ORDERLIST = "INSERT INTO ORDER_LIST (productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String DELETE_ORDERLIST = "DELETE FROM ORDER_LIST WHERE orderListSN = ?";
	public static final String UPDATE_ORDERLIST = "UPDATE ORDER_LIST SET productSpecId = ?, orderSN = ?, orderCost = ?, purchaseQuantity = ?, productRate = ?, productFeedback = ? where orderListSN = ?";
	public static final String QUERY_ORDERLIST = "SELECT * FROM ORDER_LIST WHERE orderListSN = ?";
	public static final String QUERY_ORDERLIST_BYMORDER = "SELECT * FROM ORDER_LIST WHERE orderSN = ?";
	public static final String QUERY_ALLORDERLIST = "SELECT * FROM ORDER_LIST";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addOrderList(Order_listVO orderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(ADD_ORDERLIST);
			pstmt.setInt(1, orderList.getProductSpecId());
			pstmt.setInt(2, orderList.getOrderSN());
			pstmt.setInt(3, orderList.getOrderCost());
			pstmt.setInt(4, orderList.getPurchaseQuantity());
			pstmt.setInt(5, orderList.getProductRate());
			pstmt.setString(6, orderList.getProductFeedback());
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteOrderList(Integer orderListSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_ORDERLIST);
			pstmt.setInt(1, orderListSN);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		
	}

	@Override
	public void updateOrderList(Order_listVO orderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ORDERLIST);
			pstmt.setInt(1, orderList.getProductSpecId());
			pstmt.setInt(2, orderList.getOrderSN());
			pstmt.setInt(3, orderList.getOrderCost());
			pstmt.setInt(4, orderList.getPurchaseQuantity());
			pstmt.setInt(5, orderList.getProductRate());
			pstmt.setString(6, orderList.getProductFeedback());
			pstmt.setInt(7, orderList.getOrderListSN());
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public Order_listVO getOneOrderList(Integer orderListSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order_listVO orderList = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_ORDERLIST);
			pstmt.setInt(1, orderListSN);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer productSpecId = rs.getInt("productSpecId");
				Integer orderSN = rs.getInt("orderSN");
				Integer orderCost = rs.getInt("orderCost");
				Integer purchaseQuantity = rs.getInt("purchaseQuantity");
				Integer productRate = rs.getInt("productRate");
				String productFeedback = rs.getString("productFeedback");
				orderList = new Order_listVO(orderListSN, productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderList;
	}

	@Override
	public List<Order_listVO> getAllOrderList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_ALLORDERLIST);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer orderListSN = rs.getInt("orderListSN");
				Integer productSpecId = rs.getInt("productSpecId");
				Integer orderSN = rs.getInt("orderSN");
				Integer orderCost = rs.getInt("orderCost");
				Integer purchaseQuantity = rs.getInt("purchaseQuantity");
				Integer productRate = rs.getInt("productRate");
				String productFeedback = rs.getString("productFeedback");
				Order_listVO orderList = new Order_listVO(orderListSN, productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback);
				list.add(orderList);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public Order_listVO getOneOrderListByOrder(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order_listVO orderList = null;
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_ORDERLIST_BYMORDER);
			pstmt.setInt(1, orderSN);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer orderListSN = rs.getInt("orderListSN");
				Integer productSpecId = rs.getInt("productSpecId");
				Integer orderCost = rs.getInt("orderCost");
				Integer purchaseQuantity = rs.getInt("purchaseQuantity");
				Integer productRate = rs.getInt("productRate");
				String productFeedback = rs.getString("productFeedback");
				orderList = new Order_listVO(orderListSN, productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderList;
	}

}
