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
			pstmt.setInt(1, 11002);
			InputStream is = getPictureStream("items/logo.png");
			pstmt.setBlob(2, is);
			pstmt.executeUpdate();
			is.close();

//			// 2. setBytes
//			pstmt.setInt(1, 2);
//			pstmt.setString(2, "巴塞隆納");
//			byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
//			pstmt.setBytes(3, pic);
//			pstmt.executeUpdate();
//
//			// 3. setBinaryStream
//			pstmt.setInt(1, 3);
//			pstmt.setString(2, "皇家馬德里");
//			InputStream is2 = getPictureStream("items/FC_Real_Madrid.png");
//			pstmt.setBinaryStream(3, is2);
//			pstmt.executeUpdate();
//			is2.close();

			System.out.println("新增成功");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
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

	// 使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
