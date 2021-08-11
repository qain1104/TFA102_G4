package com.productspec.model;

import java.util.*;
import java.sql.*;

public class ProductSpecJDBCDAO implements ProductSpecDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO productspec (productSN,productStock,productPrice,productSpec) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT productSpecId,productSN,productStock,productPrice,productSpec FROM productspec order by productSpecId";
	private static final String GET_ONE_STMT = 
		"SELECT productSpecId,productSN,productStock,productPrice,productSpec FROM productspec where productSpecId = ?";
	private static final String DELETE = 
		"DELETE FROM productspec where productSpecId = ?";
	private static final String UPDATE = 
		"UPDATE productspec set productSN=? ,productStock=? ,productPrice=? ,productSpec=? where productSpecId = ?";

	@Override
	public void insert(ProductSpecVO productSpecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productSpecVO.getProductSN());
			pstmt.setInt(2, productSpecVO.getProductStock());
			pstmt.setInt(3, productSpecVO.getProductPrice());
			pstmt.setString(4, productSpecVO.getProductSpec());
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
	public void update(ProductSpecVO productSpecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productSpecVO.getProductSN());
			pstmt.setInt(2, productSpecVO.getProductStock());
			pstmt.setInt(3, productSpecVO.getProductPrice());
			pstmt.setString(4, productSpecVO.getProductSpec());
			pstmt.setInt(5, productSpecVO.getProductSpecId());
			
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
	public void delete(Integer productSpecId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productSpecId);

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
	public ProductSpecVO findByPrimaryKey(Integer productSpecId) {

		ProductSpecVO productSpecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productSpecId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				productSpecVO = new ProductSpecVO();
				productSpecVO.setProductSpecId(rs.getInt("productSpecId"));
				productSpecVO.setProductSN(rs.getInt("productSN"));
				productSpecVO.setProductStock(rs.getInt("productStock"));
				productSpecVO.setProductPrice(rs.getInt("productPrice"));
				productSpecVO.setProductSpec(rs.getString("productSpec"));
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
		return productSpecVO;
	}

	@Override
	public List<ProductSpecVO> getAll() {
		List<ProductSpecVO> list = new ArrayList<ProductSpecVO>();
		ProductSpecVO productSpecVO = null;

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
				productSpecVO = new ProductSpecVO();
				productSpecVO.setProductSpecId(rs.getInt("productSpecId"));
				productSpecVO.setProductSN(rs.getInt("productSN"));
				productSpecVO.setProductStock(rs.getInt("productStock"));
				productSpecVO.setProductPrice(rs.getInt("productPrice"));
				productSpecVO.setProductSpec(rs.getString("productSpec"));
				list.add(productSpecVO); // Store the row in the list
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

		ProductSpecJDBCDAO dao = new ProductSpecJDBCDAO();

		// 新增
//		ProductSpecVO productSpecVO1 = new ProductSpecVO();
//		productSpecVO1 = new ProductSpecVO();
//		productSpecVO1.setProductSN(11001);
//		productSpecVO1.setProductStock(200);
//		productSpecVO1.setProductPrice(4900);
//		productSpecVO1.setProductSpec("3XL");
//		dao.insert(productSpecVO1);
		
		
//		
//
//		// 修改
//		ProductSpecVO productSpecVO2 = new ProductSpecVO();
//		productSpecVO2 = new ProductSpecVO();
//		productSpecVO2.setProductSN(11001);
//		productSpecVO2.setProductStock(50);
//		productSpecVO2.setProductPrice(590);
//		productSpecVO2.setProductSpec("2XL");
//		productSpecVO2.setProductSpecId(12021);
//		dao.update(productSpecVO2);
//
//		// 刪除
//		dao.delete(12021);
//
//		// 查詢
//		ProductSpecVO productSpecVO3 = dao.findByPrimaryKey(12001);
//		System.out.print(productSpecVO3.getProductSpecId() + ",");
//		System.out.print(productSpecVO3.getProductSN() + ",");
//		System.out.print(productSpecVO3.getProductStock() + ",");
//		System.out.print(productSpecVO3.getProductPrice() + ",");
//		System.out.println(productSpecVO3.getProductSpec());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ProductSpecVO> list = dao.getAll();
//		for (ProductSpecVO aPS : list) {
//		System.out.print(aPS.getProductSpecId() + ",");
//		System.out.print(aPS.getProductSN() + ",");
//		System.out.print(aPS.getProductStock() + ",");
//		System.out.print(aPS.getProductPrice() + ",");
//		System.out.print(aPS.getProductSpec());
//		System.out.println();
//		}
	}
}