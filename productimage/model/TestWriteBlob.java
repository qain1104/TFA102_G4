package com.productimage.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TestWriteBlob {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/TFA102_G4?serverTimezone=Asia/Taipei";
	public static final String USER ="Eason";
	public static final String PASSWORD = "123456";
	
	private static final String SQL = "INSERT INTO productimage(productSN,productImage) VALUES (?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. setBlob (JDBC 4.0)
//			pstmt.setInt(1, 11002);
//			InputStream is = getPictureStream("items/logo.png");
//			pstmt.setBlob(2, is);
//			pstmt.executeUpdate();
//			is.close();

//			// 2. setBytes
			pstmt.setInt(1, 11002);
			pstmt.setString(2, "撌游����");
			byte[] pic = getPictureByteArray("items/logo.png");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();

//			// 3. setBinaryStream
//			pstmt.setInt(1, 3);
//			pstmt.setString(2, "��振擐砍噸���");
//			InputStream is2 = getPictureStream("items/FC_Real_Madrid.png");
//			pstmt.setBinaryStream(3, is2);
//			pstmt.executeUpdate();
//			is2.close();

			System.out.println("�憓���");

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

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// 雿輻InputStream鞈��撘�
//	public static InputStream getPictureStream(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		return fis;
//	}

	// 雿輻byte[]�撘�
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
