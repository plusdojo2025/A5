package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/a5";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Integer> shiftData = new LinkedHashMap<>();
        Map<String, Integer> eventData = new LinkedHashMap<>();

        //データベース接続
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	//シフト件数を日付ごとに取得
            String shiftQuery = "SELECT shift_date, COUNT(shift_id) AS shift_count FROM shift GROUP BY shift_date";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(shiftQuery)) {
                while (rs.next()) {
                    shiftData.put(rs.getString("shift_date"), rs.getInt("shift_count"));
                }
            }
            //イベント件数を日付ごとに取得
            String eventQuery = "SELECT event_date, COUNT(event_id) AS event_count FROM event GROUP BY event_date";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(eventQuery)) {
                while (rs.next()) {
                    eventData.put(rs.getString("event_date"), rs.getInt("event_count"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //取得したデータをjspに渡す
        request.setAttribute("shiftData", shiftData);
        request.setAttribute("eventData", eventData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_calendar.jsp");
        dispatcher.forward(request, response);
    }
}
