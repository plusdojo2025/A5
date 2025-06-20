package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CalEvent;
import dto.Event;
import dto.EventType;

public class EventDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
		public List<Event> select(EventType card) {
			Connection conn = null;
			List<Event> cardList = new ArrayList<Event>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT event_date, event_start, event_end, event_id, type_name FROM event JOIN event_type ON event.type_id = event_type.type_id"
						+ " WHERE event_date LIKE ? AND event_start LIKE ? AND event_end LIKE ? AND type_name = ? ORDER BY event_id";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getEventDate() != null) {
					pStmt.setString(1, "%" + card.getEventDate() + "%");
				} else {
					pStmt.setString(1, "%");
				}
				if (card.getEventStart() != null) {
					pStmt.setString(2, "%" + card.getEventStart() + "%");
				} else {
					pStmt.setString(2, "%");
				}
				if (card.getEventEnd() != null) {
					pStmt.setString(3, "%" + card.getEventEnd() + "%");
				} else {
					pStmt.setString(3, "%");
				}
					pStmt.setString(4, card.getEventType());
				
				


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Event event = new Event(
									rs.getString("event_date"),
									rs.getString("event_start"),
									rs.getString("event_end"),
									rs.getInt("event_id"),
									rs.getInt("type_id"));
					cardList.add(event);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				cardList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						cardList = null;
					}
				}
			}

			// 結果を返す
			return cardList;
		}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Event card) {
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
			String sql = "INSERT INTO event VALUES (?, ?, ?, 0, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getEventDate() != null) {
				pStmt.setString(1, "" + card.getEventDate() + "");
			} else {
				pStmt.setString(1, "");
			}
			if (card.getEventStart() != null) {
				pStmt.setString(2, "" + card.getEventStart() + "");
			} else {
				pStmt.setString(2, "");
			}
			if (card.getEventEnd() != null) {
				pStmt.setString(3, "" + card.getEventEnd() + "");
			} else {
				pStmt.setString(3, "");
			}
				pStmt.setInt(4, card.getTypeId());
			

			
			
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
	public boolean update(Event card) {
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
			String sql = "UPDATE event SET event_date=?, event_start=?, event_end=?,event_id=?, type_id=? WHERE event_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			
				pStmt.setDate(1, card.getEventDate());
			
				pStmt.setString(2, card.getEventStart());
			
				pStmt.setString(3, card.getEventEnd());
				
				pStmt.setInt(4, card.getTypeId());
			
	
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
	public boolean delete(Event card) {
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
			String sql = "DELETE FROM event WHERE event_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getEventId());

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
	
	//イベントの日付と、その日付に付随するイベントの件数を取得するメソッド
	public List<CalEvent> getEvent() {
		Connection conn = null;
		List<CalEvent> cardList = new ArrayList<>();

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
				CalEvent event = new CalEvent();
				event.setEventData(rs.getDate("shift_date"));
				event.setCount(rs.getInt("shift_count"));
				cardList.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}
}
