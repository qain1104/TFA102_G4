package com.productimage.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestReadBlob {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER ="Eason";
	public static final String PASSWORD = "123456";
	
	private static final String SQL = "SELECT productimage FROM productimage where productImageSN = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. getBlob
//			pstmt.setInt(1, 13003);
//			rs1 = pstmt.executeQuery();
//			rs1.next();
//			Blob blob = rs1.getBlob(1);
//			readPicture(blob);

//			// 2. getBytes
			pstmt.setInt(1, 13005);
			rs2 = pstmt.executeQuery();
			rs2.next();
			byte[] pic = rs2.getBytes(1);
			readPicture(pic);
//
//			// 3. getBinaryStream
//			pstmt.setInt(1, 3);
//			rs3 = pstmt.executeQuery();
//			rs3.next();
//			InputStream is = rs3.getBinaryStream(1);
//			readPicture(is);

			System.out.println("�閰Ｘ���");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 靘遣蝡������� (頞�遣蝡�����)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// Handle with blob data
//	public static void readPicture(Blob blob) throws IOException, SQLException {
//		InputStream is = blob.getBinaryStream();
//		FileOutputStream fos = new FileOutputStream("Output/1.png");
//		int i;
//		while ((i = is.read()) != -1) {
//			fos.write(i);
//			fos.flush();
//		}
//		fos.close();
//		is.close();
//	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("Output/2.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
//
//	// Handle with stream data
//	public static void readPicture(InputStream is) throws IOException {
//		FileOutputStream fos = new FileOutputStream("Output/3.png");
//		int i;
//		while ((i = is.read()) != -1) {
//			fos.write(i);
//			fos.flush();
//		}
//		fos.close();
//		is.close();
//	}
}
