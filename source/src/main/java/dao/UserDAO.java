package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;

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
			String sql = "SELECT *  FROM User WHERE pw LIKE ? AND name LIKE ? AND flag LIKE ? ORDER BY id" ;
			
			//String sql = "SELECT *  FROM user_name WHERE password LIKE ? AND tencho_flag LIKE ?  " ;
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			
			
			if (reg.getPw() != null) {
				pStmt.setString(1,  "%" + reg.getPw() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			
			
			if (reg.getName() != null) {
				pStmt.setString(2,  "%" + reg.getName() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			

			if (reg.getFlag() != null) {
				pStmt.setInteger(3,  "%" + reg.getFlag() + "%");
			} else {
				pStmt.setInteger(3, "%");
			}
			
			
			
			
			
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {				
				User user = new User();
				User.Id(rs.getString("ID"));
				User.Pw(rs.getString("pw"));
				User.Name(rs.getString("name"));
				User.Flag(rs.getInteger(("flag"));
				
				User user = new user(rs.getInt("id"),
						       rs.getString("pw"),
						       rs.getString("name"),
						       rs.getInteger("flag"),
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
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
			if (reg.getPw() != null) {
				pStmt.setString(1, reg.getPw());
			} else {
				pStmt.setString(1, "");
			}
			if (reg.getName() != null) {
				pStmt.setString(2, reg.getName());
			} else {
				pStmt.setString(2, "");
			}
			if (reg.getFlag() != null) {
				pStmt.setInteger(4, reg.getFlag());
			} else {
				pStmt.setString(4, "");
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
			if (reg.getPw() != null) {
				pStmt.setString(1, reg.getPw());
			} else {
				pStmt.setString(1, "");
			}
			if (reg.getName() != null) {
				pStmt.setString(2, reg.getName());
			} else {
				pStmt.setString(2, "");
			}
			if (reg.getFlag() != null) {
				pStmt.setInteger(3, reg.getFlag());
			} else {
				pStmt.setString(3, "");
			}
			
			
			
						
						
			
			pStmt.setInt(4, reg.getId());

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
