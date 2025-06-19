package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Shift;
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
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
					rs.getString(""), 
					rs.getString(""),
					rs.getString(""),
					rs.getString("")
				);
				shiftList.add(userShift);
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
	
	// 指定した任意のシフトをもってくる
	public List<UserShift> select(UserShift userShift) {
		Connection conn = null;
		List<UserShift> shiftList = new ArrayList<UserShift>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文(SELECT文で日付(範囲指定）・開始時間・終了時間・ユーザーネームを持ってくる)
			String sql = "SELECT shift_date, shift_start, shift_end, user_name "
						+ "FROM shift "
						+ "JOIN user ON shift.user_id = user.id "
						+ "WHERE shift_date BETWEEN ? AND ? "
						+ "AND shift_start LIKE ? "
						+ "AND shift_end LIKE ? "
						+ "AND user.user_name LIKE ? "
						+ "ORDER BY shift_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, "%" + userShift.getShiftDate() + "%");
			pStmt.setString(2, "%" + card.getDep() + "%");
			pStmt.setString(3, "%" + card.getPos() + "%");
			pStmt.setString(4, "%" + card.getCompany() + "%");
			pStmt.setString(5, "%" + card.getPostCode() + "%");
			pStmt.setString(6, "%" + card.getAddress() + "%");
			pStmt.setString(7, "%" + card.getPhone() + "%");
			pStmt.setString(8, "%" + card.getFax() + "%");
			pStmt.setString(9, "%" + card.getEmail() + "%");
			pStmt.setString(10, "%" + card.getRemarks() + "%");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserShift shift = new UserShift(
					rs.getString(""), 
					rs.getString(""),
					rs.getString(""),
					rs.getString("")
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
	
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Shift shift) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			String sql = "INSERT INTO shift VALUES (0, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setString(1, shift.getShiftDate());
				pStmt.setString(2, shift.getShiftStart());
				pStmt.setString(3, shift.getShiftEnd());
				pStmt.setInt(4, shift.getUserId());
			
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
	
	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Shift shift) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			String sql = "UPDATE shift SET "
					+ "name = ?, "
					+ "dep_id = (SELECT id FROM department WHERE dep = ?), "
					+ "pos_id = (SELECT id FROM post WHERE pos = ?), "
					+ "company = ?, "
					+ "post_code = ?, "
					+ "address = ?, "
					+ "phone = ?, "
					+ "fax = ?, "
					+ "email = ?, "
					+ "remarks = ? "
					+ "WHERE no = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setString(1, card.getName());
				pStmt.setString(2, card.getDep());
				pStmt.setString(3, card.getPos());
				pStmt.setString(4, card.getCompany());
				pStmt.setString(5, card.getPostCode());
				pStmt.setString(6, card.getAddress());
				pStmt.setString(7, card.getPhone());
				pStmt.setString(8, card.getFax());
				pStmt.setString(9, card.getEmail());
				pStmt.setString(10, card.getRemarks());
				pStmt.setInt(11, card.getNo());
				
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
	public boolean delete(Bc card) {
		// cardからnumber
		
		// Bc trueCard = new Bc();
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			String sql = "DELETE FROM business_card WHERE no=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, card.getNo());
			
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
	
	public List<Contact> send(Contact message) {
		List<Contact> contactList = new ArrayList<>();
		contactList.add(message);
		return contactList;
	}
}
