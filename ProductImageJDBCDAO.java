package com.productimage.model;

import java.util.*;
import java.sql.*;

public class ProductImageJDBCDAO implements ProductImageDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO productimage (productSN,productImage) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT productImageSN,productSN,productImage FROM productimage order by productImageSN";
	private static final String GET_ONE_STMT = 
		"SELECT productImageSN,productSN,productImage FROM productimage where productImageSN = ?";
	private static final String DELETE = 
		"DELETE FROM productimage where productImageSN = ?";
	private static final String UPDATE = 
		"UPDATE productimage set productImageSN=? ,productSN=? ,productImage=? where productImageSN = ?";

	@Override
	public void insert(ProductImageVO productImageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productImageVO.getProductSN());
			pstmt.setBlob(2, productImageVO.getProductImage());
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
			pstmt.setBlob(2, productImageVO.getProductImage());
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
				// empVo ?]???? Domain objects
				productImageVO = new ProductImageVO();
				productImageVO.setProductSN(rs.getInt("productSN"));
				productImageVO.setProductImage(rs.getBlob("productImage"));
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
				// empVO ?]???? Domain objects
				productImageVO = new ProductImageVO();
				productImageVO.setProductSN(rs.getInt("productSN"));
				productImageVO.setProductImage(rs.getBlob("productImage"));
				productImageVO.setProductImageSN(rs.getInt("productImageSN"));
				list.add(productImageVO); // Store the row in the list
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

	public static void main(String[] args) {

		ProductImageJDBCDAO dao = new ProductImageJDBCDAO();

		// ?s?W
		ProductImageVO productImageVO1 = new ProductImageVO();
		productImageVO1 = new ProductImageVO();
		productImageVO1.setProductSN(11001);
//		productImageVO1.setProductImage("ccnkanfib");
		dao.insert(productImageVO1);
		
		
//		
//
//		// ????
//		ProductImageVO productImageVO2 = new ProductImageVO();
//		productImageVO2 = new ProductImageVO();
//		productImageVO2.setProductSN(11001);
//		productImageVO2.setProductImage("");
//		productImageVO2.setProductImageSN(13002);
//		dao.update(productImageVO2);
//
//		// ?R??
//		dao.delete(13001);
//
//		// ?d??
//		ProductImageVO productImageVO3 = dao.findByPrimaryKey(13002);
//		System.out.print(productImageVO3.getProductImageSN() + ",");
//		System.out.print(productImageVO3.getProductSN() + ",");
//		System.out.println(productImageVO3.getProductImage());
//		System.out.println("---------------------");
//
//		// ?d??
//		List<ProductImageVO> list = dao.getAll();
//		for (ProductImageVO aPI : list) {
//		System.out.print(aPI.getProductImageSN() + ",");
//		System.out.print(aPI.getProductSN() + ",");
//		System.out.print(aPI.getProductImage());
//		System.out.println();
//		}
	}
}