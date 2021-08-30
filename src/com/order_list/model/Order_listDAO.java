package com.order_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.morder.model.MorderService;
import com.morder.model.MorderVO;
import com.order_delivery_type.model.Order_delivery_typeVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;

public class Order_listDAO implements Order_listDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://mysql5257.chickenkiller.com:3306/TFA102_G4?rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
//	public static final String THEURL = "jdbc:mysql://localhost:3306/TFA102_G4?rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	public static final String ADD_ORDERLIST = "INSERT INTO ORDER_LIST (productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_ORDERLIST = "UPDATE ORDER_LIST SET productSpecId = ?, orderSN = ?, orderCost = ?, purchaseQuantity = ?, productRate = ?, productFeedback = ? where orderListSN = ?";
	public static final String QUERY_ORDERLIST = "SELECT * FROM ORDER_LIST WHERE orderListSN = ?";
	public static final String QUERY_ORDERLIST_BYMORDER = "SELECT * FROM ORDER_LIST WHERE orderSN = ?";
	public static final String QUERY_ALLORDERLIST = "SELECT * FROM ORDER_LIST";
	public static final String QUERY_ORDERLIST_BYSPECID = "SELECT * FROM ORDER_LIST WHERE productSpecId = ?";
	
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
	public void addOrderList(Order_listVO orderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_ORDERLIST);
			pstmt.setInt(1, orderList.getProductSpecId());
			pstmt.setInt(2, orderList.getOrderSN());
			pstmt.setInt(3, orderList.getOrderCost());
			pstmt.setInt(4, orderList.getPurchaseQuantity());
			if(orderList.getProductRate() == null) {
				pstmt.setNull(5, Types.NULL);
			} else {
				pstmt.setInt(5, orderList.getProductRate());					
			}
			pstmt.setString(6, orderList.getProductFeedback());
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
	public void updateOrderList(Order_listVO orderList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
	public Order_listVO getOneOrderList(Integer orderListSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order_listVO orderList = null;
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
		return orderList;
	}

	@Override
	public List<Order_listVO> getAllOrderList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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

	@Override
	public List<Order_listVO> getOrderListByOrder(Integer orderSN){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
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
				Order_listVO orderList = new Order_listVO(orderListSN, productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback);
				list.add(orderList);
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

	@Override
	public List<ProductSpecVO> orderListGetInfo(List<Order_listVO> orderList) {
		List<ProductSpecVO> list = new ArrayList<ProductSpecVO>();
		ProductSpecService productSpecService = new ProductSpecService();
		try {
			for(Order_listVO orderListVO : orderList) {
				ProductSpecVO productSpec = 
						productSpecService.getOneProduct(orderListVO.getProductSpecId());
				list.add(productSpec);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("訂單明細轉換產品規格失敗" + e.getMessage());
		} 
		return list;
	}

	@Override
	public List<Order_listVO> specGetOrderlist(Integer productSpecId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		try {
//			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(QUERY_ORDERLIST_BYSPECID);
			pstmt.setInt(1, productSpecId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer orderListSN = rs.getInt("orderListSN");
				Integer orderSN = rs.getInt("orderSN");
				Integer orderCost = rs.getInt("orderCost");
				Integer purchaseQuantity = rs.getInt("purchaseQuantity");
				Integer productRate = rs.getInt("productRate");
				String productFeedback = rs.getString("productFeedback");
				Order_listVO orderList = new Order_listVO(orderListSN, productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback);
				list.add(orderList);
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
