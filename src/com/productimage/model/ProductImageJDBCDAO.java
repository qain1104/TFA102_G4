package com.productimage.model;

import java.util.*;
import java.sql.*;

public class ProductImageJDBCDAO implements ProductImageDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://mysql5257.chickenkiller.com:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO PRODUCTIMAGE (productSN,productImage) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT productImageSN,productSN,productImage FROM PRODUCTIMAGE order by productImageSN";
	private static final String GET_ONE_STMT = 
		"SELECT productImageSN,productSN,productImage FROM PRODUCTIMAGE where productImageSN = ?";
	private static final String GET_ONE_STMT_BY_PRODUCT = 
			"SELECT * FROM PRODUCTIMAGE where productSN = ?";
	private static final String DELETE = 
		"DELETE FROM PRODUCTIMAGE where productImageSN = ?";
	private static final String UPDATE = 
		"UPDATE PRODUCTIMAGE set productImageSN=? ,productSN=? ,productImage=? where productImageSN = ?";

	@Override
	public void insert(ProductImageVO productImageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productImageVO.getProductSN());
			pstmt.setBytes(2, productImageVO.getProductImage());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(ProductImageVO productImageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productImageVO.getProductSN());
			pstmt.setBytes(2, productImageVO.getProductImage());
			pstmt.setInt(3, productImageVO.getProductImageSN());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer productImageSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productImageSN);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public ProductImageVO findByPrimaryKey(Integer productImageSN) {

		ProductImageVO productImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productImageSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				productImageVO = new ProductImageVO();
				productImageVO.setProductSN(rs.getInt("productSN"));
				productImageVO.setProductImage(rs.getBytes("productImage"));
				productImageVO.setProductImageSN(rs.getInt("productImageSN"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return productImageVO;
	}

	@Override
	public List<ProductImageVO> getAll() {
		List<ProductImageVO> list = new ArrayList<ProductImageVO>();
		ProductImageVO productImageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productImageVO = new ProductImageVO();
				productImageVO.setProductSN(rs.getInt("productSN"));
				productImageVO.setProductImage(rs.getBytes("productImage"));
				productImageVO.setProductImageSN(rs.getInt("productImageSN"));
				list.add(productImageVO); 
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	@Override
	public List<ProductImageVO> findByProduct(Integer productSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductImageVO> list = new ArrayList<ProductImageVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_PRODUCT);
			pstmt.setInt(1, productSN);
			rs = pstmt.executeQuery();

			while (rs.next()) {	
				byte[] productImage = rs.getBytes("productImage");
				Integer productImageSN = rs.getInt("productImageSN");
				ProductImageVO productImageVO = new ProductImageVO(productImageSN, productSN, productImage);
				list.add(productImageVO);
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		ProductImageJDBCDAO dao = new ProductImageJDBCDAO();

		// 新增
//		ProductImageVO productImageVO1 = new ProductImageVO();
//		productImageVO1 = new ProductImageVO();
//		productImageVO1.setProductSN(11001);
//		productImageVO1.setProductImage("ccnkanfib");
//		dao.insert(productImageVO1);
		
		
//		
//
//		// 修改
//		ProductImageVO productImageVO2 = new ProductImageVO();
//		productImageVO2 = new ProductImageVO();
//		productImageVO2.setProductSN(11001);
//		productImageVO2.setProductImage("");
//		productImageVO2.setProductImageSN(13002);
//		dao.update(productImageVO2);
//
//		// 刪除
//		dao.delete(13001);
//
//		// 查詢
//		ProductImageVO productImageVO3 = dao.findByPrimaryKey(13002);
//		System.out.print(productImageVO3.getProductImageSN() + ",");
//		System.out.print(productImageVO3.getProductSN() + ",");
//		System.out.println(productImageVO3.getProductImage());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ProductImageVO> list = dao.getAll();
//		for (ProductImageVO aPI : list) {
//		System.out.print(aPI.getProductImageSN() + ",");
//		System.out.print(aPI.getProductSN() + ",");
//		System.out.print(aPI.getProductImage());
//		System.out.println();
//		}
		
		// List<ProductImageVO> findByProduct(Integer productSN)
		ProductImageService service = new ProductImageService();
		List<ProductImageVO> list = service.findByProduct(new Integer(11001));
		System.out.println(list.size());
	}
}