package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

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

	// 引数の日付よりも後のイベントを7件取り出す。
	public List<EventType> select7(Date date) {
		Connection conn = null;
		List<EventType> eList = new ArrayList<EventType>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			String sql = "SELECT event_date, event_start, event_end, type_name FROM event JOIN event_type ON event.type_id = event_type.type_id"
					+ " WHERE event_date >= ? ORDER BY event_date LIMIT 7";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
				pStmt.setDate(1, date);
				
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				EventType event = new EventType();
				event.setEventDate(rs.getDate("event_date"));
				event.setEventStart(rs.getString("event_start"));
				event.setEventEnd(rs.getString("event_end"));
				event.setEventType(rs.getString("type_name"));
				eList.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			eList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			eList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					eList = null;
				}
			}
		}
		
		// 結果を返す
		return eList;
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
	
	// イベント登録用
	public boolean insert(EventType eventType) {
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
			String sql = "INSERT INTO event VALUES (?, ?, ?, 0, (SELECT type_id FROM event_type WHERE type_name LIKE ?))";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, eventType.getEventDate());
			pStmt.setString(2, eventType.getEventStart());
			pStmt.setString(3, eventType.getEventEnd());
			pStmt.setString(4, eventType.getEventType());
			
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
	public boolean delete(EventType eventType) {
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
			String sql = "DELETE FROM event WHERE event_date = ? AND event_start = ? AND event_end = ? AND type_id = (SELECT type_id FROM event_type WHERE type_name = ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setDate(1, eventType.getEventDate());
			pStmt.setString(2, eventType.getEventStart());
			pStmt.setString(3, eventType.getEventEnd());
			pStmt.setString(4, eventType.getEventType());
			
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
			String sql = "SELECT event_date, COUNT(event_id) AS event_count FROM event GROUP BY event_date";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			//コラムの確認
			ResultSet rs1 = pStmt.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs1.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
			    System.out.println("Column " + i + ": " + rsmd.getColumnName(i));
			}
			
			// 結果表をコレクションにコピーする
			while (rs1.next()) {
				CalEvent event = new CalEvent();
				event.setEventData(rs1.getDate("event_date"));
				event.setCount(rs1.getInt("event_count"));
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
