package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Manual;

public class ManualDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<Manual> select(Manual card) {
		Connection conn = null;
		List<Manual> cardList = new ArrayList<Manual>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する (~~ LIKE ? AND で検索項目を追加できる
			String sql = "SELECT manual_file, importance, date_up, file_id "
					+ "FROM Manual WHERE manual_file LIKE ?, AND importance LIKE ?, AND date_up LIKE ?, AND file_id LIKE ? "
					+ "ORDER BY file_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる (上で追加した分はここでも対応する分を作る必要性がある
			if (card.getManualFile() != null) {
				pStmt.setString(1, "%" + card.getManualFile() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (card.getImportance() >=0 ) {
				pStmt.setInt(2,card.getImportance());
			} else {
//				pStmt.setInt(2);←INTであるため？elseの中は要らないっぽい。整数が不確定要素ではないので？
			if (card.getDate() != null) {
				pStmt.setDate(3,card.getDate());
			} else {
				pStmt.setString(3, "%");
			}
			if (card.getFileId() != null) {
				pStmt.setString(4, "%" + card.getFileId() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする (ここも上で追加した分は対応する分を追加で作成する必要がある
			while (rs.next()) {
				Manual manual = new Manual(rs.getString("manual_file"),rs.getInt("importance"),rs.getDate("date_up"),rs.getString("file_id"));
				cardList.add(manual);
			}
//いったん保留↑
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
	public boolean insert(Manual card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する (上で追加した分だけ？マークを追加）
			String sql = "INSERT INTO Bc VALUES (?, ?, ?, ?)"; 
//										Stringは?で良さそうだが、他のデータ型の場合はどうなるんだろう…
//										回答→入れたいものがあるときは絶対"?"で、オートインクリメントは"0"でよいらしい。
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる (ここでは4個のデータを受けるため4個分の文を書く必要性がある
			if (card.getManualFile() != null) {
				pStmt.setString(1, "%" + card.getManualFile() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (card.getImportance() >=0 ) {
				pStmt.setInt(2,card.getImportance());
			} else {
//				pStmt.setInt(2);←INTであるため？elseの中は要らないっぽい。整数が不確定要素ではないので？
			}
			if (card.getDate() != null) {
				pStmt.setDate(3,card.getDate());
			} else {
				pStmt.setString(3, "%");
			}
			if (card.getFileId() != null) {
				pStmt.setString(4, "%" + card.getFileId() + "%");
			} else {
				pStmt.setString(4, "%");
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
	public boolean update(Bc card) {
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
			String sql = "UPDATE Bc SET zipcode=?, company=?, address=?, department=?, tel=?, position=?,"
					+ "fax=?, email=?, MailDomain=?, PersonName=?, memo=? WHERE RegisterNumber=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			/*if (card.getNumber() != null) {
				pStmt.setString(1, card.getNumber());
			} else {
				pStmt.setString(1, "");
			}*/
			if (card.getZipcode() != null) {
				pStmt.setString(1, card.getZipcode());
			} else {
				pStmt.setString(1, "");
			}
			if (card.getCompany() != null) {
				pStmt.setString(2, card.getCompany());
			} else {
				pStmt.setString(2, "");
			}
			if (card.getAddress() != null) {
				pStmt.setString(3, card.getAddress());
			} else {
				pStmt.setString(3, "");
			}
			if (card.getDepartment() != null) {
				pStmt.setString(4, card.getDepartment());
			} else {
				pStmt.setString(4, "");
			}
			if (card.getTel() != null) {
				pStmt.setString(5, card.getTel());
			} else {
				pStmt.setString(5, "");
			}
			if (card.getPosition() != null) {
				pStmt.setString(6, card.getPosition());
			} else {
				pStmt.setString(6, "");
			}
			if (card.getFax() != null) {
				pStmt.setString(7, card.getFax());
			} else {
				pStmt.setString(7, "");
			}
			if (card.getEmail() != null) {
				pStmt.setString(8, card.getEmail());
			} else {
				pStmt.setString(8, "");
			}
			if (card.getMailDomain() != null) {
				pStmt.setString(9, card.getMailDomain());
			} else {
				pStmt.setString(9, "");
			}
			if (card.getPersonName() != null) {
				pStmt.setString(10, card.getPersonName());
			} else {
				pStmt.setString(10, "");
			}
			if (card.getMemo() != null) {
				pStmt.setString(11, card.getMemo());
			} else {
				pStmt.setString(11, "");
			}
			pStmt.setInt(12, card.getNumber());

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
			String sql = "DELETE FROM Bc WHERE RegisterNumber=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getNumber());

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
