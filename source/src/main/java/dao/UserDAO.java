package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<User> select(User reg) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT *  FROM user_name WHERE password LIKE ? AND tencho_flag LIKE ?  " ;
			
			//String sql = "SELECT *  FROM user_name WHERE password LIKE ? AND tencho_flag LIKE ?  " ;
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (reg.getUser_name() != null) {
				pStmt.setString(1,  "%" + reg.getUser_name() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			
			
			if (reg.getPassword() != null) {
				pStmt.setString(2,  "%" + reg.getPassword() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			
			
			if (reg.getTencho_flag() != null) {
				pStmt.setString(3,  "%" + reg.getTencho_flag() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			
			
			
			
			
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {				
				User user = new User();
				User.setuser_Name(rs.getString("user_name"));
				User.setPassword(rs.getString("password"));
				User.setTencho_flag(rs.getString("Tencho_flag"));
				
/*				Bc bc = new Bc(rs.getInt("number"),
						       rs.getString("company"),
						       rs.getString("department"),
						       rs.getString("position"),
						       rs.getString("name"),
						       rs.getString("phone"),
						       rs.getString("email"),
						       rs.getString("zipcode"),
						       rs.getString("address"));*/
				userList.add(User);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			UserList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			UserList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(User reg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO Bc VALUES (0, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (reg.getUser_name() != null) {
				pStmt.setString(1, reg.getUser_name());
			} else {
				pStmt.setString(1, "");
			}
			if (reg.getPassword() != null) {
				pStmt.setString(2, reg.getPassword());
			} else {
				pStmt.setString(2, "");
			}
			if (reg.getTencho_flag() != null) {
				pStmt.setString(3, reg.getTencho_flag());
			} else {
				pStmt.setString(3, "");
			}
			
			
			

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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

		// 結果を返す
		return result;
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(User reg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE Bc SET company=? ,department=? ,position=? ,name=? , phone=? ,email=? ,zipcode=? ,address=? WHERE number=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			
			// SQL文を完成させる
			if (reg.getUser_name() != null) {
				pStmt.setString(1, reg.getUser_name());
			} else {
				pStmt.setString(1, "");
			}
			if (reg.getPassword() != null) {
				pStmt.setString(2, reg.getPassword());
			} else {
				pStmt.setString(2, "");
			}
			if (reg.getTencho_flag() != null) {
				pStmt.setString(3, reg.getTencho_flag());
			} else {
				pStmt.setString(3, "");
			}
			
						
						
			
			pStmt.setInt(4, reg.getNumber());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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

		// 結果を返す
		return result;
	}

	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(User card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "DELETE FROM User WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, reg.getID());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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

		// 結果を返す
		return result;
	}
}
