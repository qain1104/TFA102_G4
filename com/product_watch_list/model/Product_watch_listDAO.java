package com.product_watch_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_watch_listDAO implements Product_watch_listDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String THEURL = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String ADD_LIST = "INSERT INTO PRODUCT_WATCH_LIST (productSN, userId) VALUES (?, ?)";
	public static final String DELETE_LIST = "DELETE FROM PRODUCT_WATCH_LIST WHERE pwlSN = ?";
	public static final String UPDATE_LIST = "UPDATE PRODUCT_WATCH_LIST SET productSN = ?, userId = ? where pwlSN = ?";
	public static final String QUERY_LIST = "SELECT * FROM PRODUCT_WATCH_LIST WHERE pwlSN = ?";
	public static final String QUERY_LIST_USER = "SELECT * FROM PRODUCT_WATCH_LIST WHERE userId = ?";
	public static final String QUERY_ALLLIST = "SELECT * FROM PRODUCT_WATCH_LIST";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addWatchList(Product_watch_listVO watchList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(ADD_LIST);
			pstmt.setInt(1, watchList.getProductSN());
			pstmt.setInt(2, watchList.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void deleteWatchList(Integer pwlSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_LIST);
			pstmt.setInt(1, pwlSN);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void updateWatchList(Product_watch_listVO watchList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_LIST);
			pstmt.setInt(1, watchList.getProductSN());
			pstmt.setInt(2, watchList.getUserId());
			pstmt.setInt(3, watchList.getPwlSN());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public Product_watch_listVO getWatchList(Integer pwlSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product_watch_listVO watchList = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_LIST);
			pstmt.setInt(1, pwlSN);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer productSN = rs.getInt("productSN");
				Integer userId = rs.getInt("userId");
				watchList = new Product_watch_listVO(pwlSN, productSN, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return watchList;
	}

	@Override
	public List<Product_watch_listVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product_watch_listVO> list = new ArrayList<Product_watch_listVO>();
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_ALLLIST);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer pwlSN = rs.getInt("pwlSN");
				Integer productSN = rs.getInt("productSN");
				Integer userId = rs.getInt("userId");
				Product_watch_listVO watchList = new Product_watch_listVO(pwlSN, productSN, userId);
				list.add(watchList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
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
	public Product_watch_listVO getWatchListByUser(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product_watch_listVO watchList = null;
		
		try {
			con = DriverManager.getConnection(THEURL, USER, PASSWORD);
			pstmt = con.prepareStatement(QUERY_LIST_USER);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer pwlSN = rs.getInt("pwlSN");
				Integer productSN = rs.getInt("productSN");
				watchList = new Product_watch_listVO(pwlSN, productSN, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return watchList;
	}

}
