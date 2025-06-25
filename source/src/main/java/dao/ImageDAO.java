package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageDAO {

	//画像データをDBに登録するメソッド---------------------------------
		public int saveImage(byte[] imageBytes) {
			Connection conn = null;
			int result = 0;
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

				// SQL文を準備する
				String sql = "INSERT INTO images (image_data) VALUES (?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				pStmt.setBytes(1, imageBytes);

				result = pStmt.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}

		//画像データのバイト文字列をDBから取得するメソッド
		public ArrayList<byte[]> getImageData() {
		    Connection conn = null;
		    ArrayList<byte[]> imageDataList = new ArrayList<byte[]>();

		    try {
		        // JDBCドライバを読み込む
		        Class.forName("org.h2.Driver");

		        // データベースに接続する
		        conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

		        // SQL文を準備する
		        String sql = "SELECT image_data FROM images";
		        PreparedStatement pstmt = conn.prepareStatement(sql);

		        // SQL文を実行し、結果セットを取得する
		        ResultSet rs = pstmt.executeQuery();

		        // 結果セットから画像データを取得する
		        while(rs.next()) {
		        	imageDataList.add(rs.getBytes("image_data"));
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } finally {
		        // データベースを切断
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }

		    return imageDataList;
		}


}
