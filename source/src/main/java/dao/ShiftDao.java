package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CalShift;
import dto.UserShift;

public class ShiftDao {
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
	
	public int selectId(UserShift userShift) {
		Connection conn = null;
		int iD = 0;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文(SELECT文で日付・開始時間・終了時間・ユーザーネームを持ってくる)
			String sql = "SELECT shift_id "
						+ "FROM shift "
						+ "JOIN user ON shift.user_id = user.id "
						+ "WHERE shift_date LIKE ? " // 一旦保留
						+ "AND user.user_name LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, userShift.getShiftDate());
			pStmt.setString(2, userShift.getUserName());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				iD = rs.getInt("shift_id");
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
		return iD;
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
			String sql = "INSERT INTO shift VALUES (NULL, ?, ?, ?, ?)";
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
	public boolean insert2(UserShift userShift) {
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
			String sql = "INSERT INTO shift VALUES (NULL, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setDate(1, userShift.getShiftDate());
				pStmt.setString(2, userShift.getShiftStart());
				pStmt.setString(3, userShift.getShiftEnd());
				UserDao shiftDao = new UserDao();
				System.out.println(shiftDao.selectNameId(userShift.getUserName()));
				pStmt.setInt(4, shiftDao.selectNameId(userShift.getUserName()));
			
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
	public boolean update(UserShift userShift, int id) {
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
					+ "shift_date = ?, "
					+ "shift_start = ?, "
					+ "shift_end = ?, "
					+ "user_id = (SELECT id FROM user WHERE user_name LIKE ?) "
					+ "WHERE shift_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setDate(1, userShift.getShiftDate());
				pStmt.setString(2, userShift.getShiftStart());
				pStmt.setString(3, userShift.getShiftEnd());
				pStmt.setString(4, userShift.getUserName());
				pStmt.setInt(5, id);
				
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
	public int selectDbl(UserShift userShift) {
		Connection conn = null;
		int count = 0;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文(SELECT文で日付・開始時間・終了時間・ユーザーネームを持ってくる)
			String sql = "SELECT COUNT(*) "
						+ "FROM shift "
						+ "JOIN user ON shift.user_id = user.id "
						+ "WHERE shift_date LIKE ? " // 一旦保留
						+ "AND user.user_name LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, userShift.getShiftDate());
			pStmt.setString(2, userShift.getUserName());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			 
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
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
	
	// 指定日から7日間のシフトを取得するメソッド
	public List<CalShift> select7(Date date) {
	    Connection conn = null;
	    List<CalShift> sList = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/a5?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password"
	        );

	        // 指定日から7日間の範囲で取得（shift_dateが[date, date+6日]の範囲）
	        String sql = "SELECT shift_date, shift_start, shift_end, user_id " +
	                     "FROM shift WHERE shift_date >= ? AND shift_date < DATE_ADD(?, INTERVAL 7 DAY) " +
	                     "ORDER BY shift_date, shift_start";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setDate(1, date);
	        pStmt.setDate(2, date);

	        ResultSet rs = pStmt.executeQuery();

	        while (rs.next()) {
	            CalShift shift = new CalShift();
	            shift.setShiftData(rs.getDate("shift_date"));
	            shift.setShiftStart(rs.getString("shift_start"));
	            shift.setShiftEnd(rs.getString("shift_end"));
	            shift.setUserId(rs.getInt("user_id"));
	            sList.add(shift);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        sList = null;
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return sList;
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
