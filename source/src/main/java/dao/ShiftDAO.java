package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CalShift;
import dto.UserShift;

public class ShiftDAO {
	// ユーザーのシフトをすべて持ってくる
	public List<UserShift> selectAll() {
		Connection conn = null;
		List<UserShift> shiftList = new ArrayList<UserShift>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文(SELECT文で日付・開始時間・終了時間・ユーザーネームを持ってくる)
			String sql = "SELECT shift_date, shift_start, shift_end, user_name "
						+ "FROM shift "
						+ "JOIN user ON shift.user_id = user.id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserShift userShift = new UserShift(
					rs.getString("shift_date"), 
					rs.getString("shift_start"),
					rs.getString("shift_end"),
					rs.getString("user_name")
				);
				shiftList.add(userShift);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			shiftList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			shiftList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					shiftList = null;
				}
			}
		}
		
		// 結果を返す
		return shiftList;
	}
	
	// 指定した任意のシフトをもってくる
	public List<UserShift> select(UserShift userShift) {
		Connection conn = null;
		List<UserShift> shiftList = new ArrayList<UserShift>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文(SELECT文で日付・開始時間・終了時間・ユーザーネームを持ってくる)
			String sql = "SELECT shift_date, shift_start, shift_end, user_name "
						+ "FROM shift "
						+ "JOIN user ON shift.user_id = user.id "
						+ "WHERE shift_date LIKE ? " // 一旦保留
						+ "AND shift_start LIKE ? "
						+ "AND shift_end LIKE ? "
						+ "AND user.user_name LIKE ? "
						+ "ORDER BY shift_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, userShift.getShiftDate());
			pStmt.setString(2, userShift.getShiftStart());
			pStmt.setString(3, userShift.getShiftEnd());
			pStmt.setString(4, userShift.getUserName());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserShift shift = new UserShift(
					rs.getDate("shift_date"), 
					rs.getString("shift_start"),
					rs.getString("shift_end"),
					rs.getString("user_name")
				);
				shiftList.add(shift);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			shiftList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			shiftList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					shiftList = null;
				}
			}
		}
		
		// 結果を返す
		return shiftList;
	}
	
	// シフトを登録し、成功したらtrueを返す
	public boolean insert(UserShift userShift) {
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
			String sql = "INSERT INTO shift VALUES (0, ?, ?, ?, (SELECT id FROM user WHERE user_name LIKE ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setDate(1, userShift.getShiftDate());
				pStmt.setString(2, userShift.getShiftStart());
				pStmt.setString(3, userShift.getShiftEnd());
				pStmt.setString(4, userShift.getUserName());
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
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
	
	// シフトを更新し、成功したらtrueを返す
	public boolean update(UserShift userShift) {
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
			String sql = "UPDATE shift SET "
					+ "shift_date LIKE ?, "
					+ "shift_start LIKE ?, "
					+ "shift_end LIKE ?, "
					+ "user_id = (SELECT id FROM user WHERE user_name LIKE ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setDate(1, userShift.getShiftDate());
				pStmt.setString(2, userShift.getShiftStart());
				pStmt.setString(3, userShift.getShiftEnd());
				pStmt.setString(4, userShift.getUserName());
				
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
	
	// 日付とユーザーネームでシフトを抽出し、削除する
	public boolean delete(UserShift userShift) {
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
			String sql = "DELETE FROM shift WHERE shift_date LIKE ? AND user_id = (SELECT id FROM user WHERE user_name LIKE ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, userShift.getShiftDate());
			pStmt.setString(2, userShift.getUserName());
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
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
	
	//イベントの日付と、その日付に付随するイベントの件数を取得するメソッド
	public List<CalShift> getShift() {
		Connection conn = null;
		List<CalShift> calShiftList = new ArrayList<>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			String sql = "SELECT shift_date, COUNT(shift_id) AS shift_count FROM shift GROUP BY shift_date";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				CalShift shift = new CalShift();
				shift.setShiftData(rs.getDate("shift_date"));
				shift.setCount(rs.getInt("shift_count"));
				calShiftList.add(shift);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			calShiftList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			calShiftList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					calShiftList = null;
				}
			}
		}
		
		// 結果を返す
		return calShiftList;
	}

}
