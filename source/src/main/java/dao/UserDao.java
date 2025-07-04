package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;


public class UserDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<User> selectAll() {
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
			String sql = "SELECT *  FROM user  ORDER BY id" ;
			
			//String sql = "SELECT *  FROM User WHERE  ORDER BY id" ;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {				
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setPw(rs.getString("password"));
				user.setName(rs.getString("user_name"));
				user.setFlag(rs.getInt("tenchou_flag"));
				
				 userList.add(user); //一覧表示
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
	
	
	public int selectNameId(String name) {
		Connection conn = null;
		
		int nm=0;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			String sql = "SELECT  id FROM user where user_name = ?" ;
			
			//String sql = "SELECT *  FROM User WHERE  ORDER BY id" ;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, name);
			
			
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {				
				
				nm = rs.getInt("id");
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
		return nm;
	}
	
	// ログイン用
	public List<User> login(User user) {
		Connection conn = null;
		List<User> loginUser = new ArrayList<User>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// ユーザーネームとパスワードを参照し正しければユーザーネームと店長フラグを持ってくる
			String sql = "SELECT id, user_name, tenchou_flag FROM user WHERE user_name LIKE ? AND password LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPw());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				User logUser = new User();
				logUser.setId(rs.getInt("id"));
				logUser.setName(rs.getString("user_name"));
				logUser.setFlag(rs.getInt("tenchou_flag"));
				loginUser.add(logUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			loginUser = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginUser = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					loginUser = null;
				}
			}
		}
		// 結果を返す
		return loginUser;
	}

	// 引数userで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(User reg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, reg.getName());
			pStmt.setString(2, reg.getPw());
			pStmt.setInt(3, reg.getFlag());
			
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

	// 引数userで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(User reg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE user SET user_name=? ,password=?  WHERE id=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			

			// SQL文を完成させる
			pStmt.setString(1, reg.getName());
			pStmt.setString(2, reg.getPw());
			pStmt.setInt(3, reg.getId());
			
			

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
		System.out.println(result);
		// 結果を返す
		return result;
	}
	
	
	
	
	public boolean pwupdate(User reg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE user SET password=?  WHERE password=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			

			// SQL文を完成させる
			pStmt.setString(1, reg.getName());
			pStmt.setString(2, reg.getPw());
			
			
			

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

	// 引数userで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(User reg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "DELETE FROM user WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, reg.getId());

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
