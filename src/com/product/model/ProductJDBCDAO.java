package com.product.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://mysql5257.chickenkiller.com:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO PRODUCT (corpUserId,productName,productClass,productDetail,productBrand,productOnsale,productStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT productSN,corpUserId,productName,productClass,productDetail,productBrand,productOnsale,productStatus FROM PRODUCT order by productSN";
	private static final String GET_ONE_STMT = 
		"SELECT productSN,corpUserId,productName,productClass,productDetail,productBrand,productOnsale,productStatus FROM PRODUCT where productSN = ?";
	private static final String DELETE = 
		"DELETE FROM PRODUCT where productSN = ?";
	private static final String UPDATE = 
		"UPDATE PRODUCT set corpUserId=? ,productName=? ,productClass=? ,productDetail=? ,productBrand=? ,productOnsale=? ,productStatus=? where productSN = ?";
	private static final String SELECT_FIND_CORPUSERID = 
			"SELECT * FROM PRODUCT WHERE corpUserId = ?";
		
	
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
	
	Integer key = null;
	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			String[] cols = { "productSN" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, productVO.getCorpUserId());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getProductClass());
			pstmt.setString(4, productVO.getProductDetail());
			pstmt.setString(5, productVO.getProductBrand());
			pstmt.setTimestamp(6, productVO.getProductOnsale());
			pstmt.setInt(7, productVO.getProductStatus());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1); // �u�䴩�����ޭȨ��o�ۼW�D���
				System.out.println("�ۼW�D��� = " + key + "(��s�W���\�����u�s��)");
			} else{
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();

			// Handle any driver errors
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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productVO.getCorpUserId());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getProductClass());
			pstmt.setString(4, productVO.getProductDetail());
			pstmt.setString(5, productVO.getProductBrand());
			pstmt.setTimestamp(6, productVO.getProductOnsale());
			pstmt.setInt(7, productVO.getProductStatus());
			pstmt.setInt(8, productVO.getProductSN());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer productSN) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productSN);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ProductVO findByPrimaryKey(Integer productSN) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, productSN);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setCorpUserId(rs.getInt("corpUserId"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductClass(rs.getInt("productClass"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductBrand(rs.getString("productBrand"));
				productVO.setProductOnsale(rs.getTimestamp("productOnsale"));
				productVO.setProductStatus(rs.getInt("productStatus"));
			}

			// Handle any driver errors
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
		return productVO;
	}
	
	@Override
	public ProductVO findByProductName(String productName) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, productName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setCorpUserId(rs.getInt("corpUserId"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductClass(rs.getInt("productClass"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductBrand(rs.getString("productBrand"));
				productVO.setProductOnsale(rs.getTimestamp("productOnsale"));
				productVO.setProductStatus(rs.getInt("productStatus"));
			}

			// Handle any driver errors
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
		return productVO;
	}


	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setCorpUserId(rs.getInt("corpUserId"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductClass(rs.getInt("productClass"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductBrand(rs.getString("productBrand"));
				productVO.setProductOnsale(rs.getTimestamp("productOnsale"));
				productVO.setProductStatus(rs.getInt("productStatus"));
				list.add(productVO); // Store the row in the list
			}

	
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
	public List<ProductVO> selectCorpUserId(Integer corpUserId) {
		List<ProductVO> list = new ArrayList<ProductVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_FIND_CORPUSERID);
			pstmt.setInt(1, corpUserId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ProductVO productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setCorpUserId(rs.getInt("corpUserId"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductClass(rs.getInt("productClass"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductBrand(rs.getString("productBrand"));
				productVO.setProductOnsale(rs.getTimestamp("productOnsale"));
				productVO.setProductStatus(rs.getInt("productStatus"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<ProductVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();

		// �s�W
//		ProductVO productVO1 = new ProductVO();
//		productVO1 = new ProductVO();
//		productVO1.setCorpUserId(2001);
//		productVO1.setProductName("HG Armour�uT-Shirt");
//		productVO1.setProductClass(0);
//		productVO1.setProductDetail("�ŤU�M��I�t���������^���A���U��z��q��\r\n" + 
//				"�H��u�ǳ]�p�A���U��ֱ��_�P���i�l�ϰ쪺��Ĳ�A�ô��ɭ@�Ω�\r\n" + 
//				"�����s�ӳS�]�p�A�W�[�B���F���ʩM�ξA��\r\n" + 
//				"���V�B���x���d���ҡA�i�H�g�W�m�W���r���Τ�����X\r\n" + 
//				"�P�P��窫�N�����~\r\n" + 
//				"���n�ɶȨϥεL��}�վ�\r\n" + 
//				"�C�źu������\r\n" + 
//				"���i���S\r\n" + 
//				"���i�ϥγn�ƾ�\r\n" + 
//				"���i���~");
//		productVO1.setProductBrand("underarmour");
//		productVO1.setProductOnsale(java.sql.Timestamp.valueOf("2021-08-04"));
//		productVO1.setProductStatus(0);
//		dao.insert(productVO1);
		
		
//		
//
//		// �ק�
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setProductSN(11005);
//		productVO2.setCorpUserId(2001);
//		productVO2.setProductName("HG �uT-Shirt");
//		productVO2.setProductClass(0);
//		productVO2.setProductDetail("�ŤU�M��I�t���������^���A���U��z��q���F" + 
//				"�H��u�ǳ]�p�A���U��ֱ��_�P���i�l�ϰ쪺��Ĳ�A�ô��ɭ@�ΩʡF" + 
//				"�����s�ӳS�]�p�A�W�[�B���F���ʩM�ξA�סF" + 
//				"���V�B���x���d���ҡA�i�H�g�W�m�W���r���Τ�����X�F" + 
//				"�P�P��窫�N�����~�C");
//		productVO2.setProductBrand("UnderArmour");
//		productVO2.setProductOnsale(java.sql.Timestamp.valueOf("2021-08-05"));
//		productVO2.setProductStatus(0);
//		dao.update(productVO2);
//
//		// �R��
//		dao.delete(11004);
//
//		// �d��
		ProductService service = new ProductService();
		List<ProductVO> list = service.getProductCategory(2);
		System.out.println(list.size());
//		System.out.print(productVO3.getProductSN() + ",");
//		System.out.print(productVO3.getCorpUserId() + ",");
//		System.out.print(productVO3.getProductName() + ",");
//		System.out.print(productVO3.getProductClass() + ",");
//		System.out.print(productVO3.getProductDetail() + ",");
//		System.out.print(productVO3.getProductBrand() + ",");
//		System.out.print(productVO3.getProductOnsale() + ",");
//		System.out.println(productVO3.getProductStatus());
		System.out.println("---------------------");
//
//		// �d��
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProduct : list) {
//		System.out.print(aProduct.getProductSN() + ",");
//		System.out.print(aProduct.getCorpUserId() + ",");
//		System.out.print(aProduct.getProductName() + ",");
//		System.out.print(aProduct.getProductClass() + ",");
//		System.out.print(aProduct.getProductDetail() + ",");
//		System.out.print(aProduct.getProductBrand() + ",");
//		System.out.print(aProduct.getProductOnsale() + ",");
//		System.out.print(aProduct.getProductStatus());
//		System.out.println();
//		}
	}
}