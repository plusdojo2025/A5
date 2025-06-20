package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDao;
import dao.ShiftDAO;
import dto.CalEvent;
import dto.CalShift;

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
//            String shiftQuery = "SELECT shift_date, COUNT(shift_id) AS shift_count FROM shift GROUP BY shift_date";
//            //データベースにクエリを送るためのStatementを作成し、shiftQueryを実行、結果はResultSetに返ってくる
//            try (Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(shiftQuery)) {
//            	//ResultSetに次の行がある限りループ
//                while (rs.next()) {
//                	//Map<String, Integer> 型のshiftDataに保存
//                    shiftData.put(rs.getString("shift_date"), rs.getInt("shift_count"));
//                }
//            }
            ShiftDAO sdao = new ShiftDAO();
            List<CalShift> slist = sdao.getShift();
            //jspが見えるところにセット
            request.setAttribute("calShift", slist);

//            //イベント件数を日付ごとに取得
//            String eventQuery = "SELECT event_date, COUNT(event_id) AS event_count FROM event GROUP BY event_date";
//            try (Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(eventQuery)) {
//                while (rs.next()) {
//                    eventData.put(rs.getString("event_date"), rs.getInt("event_count"));
//                }
//            }
            EventDao edao = new EventDao();
            List<CalEvent> elist = edao.getEvent();
            //jspが見えるところにセット
            request.setAttribute("calEvent", elist);

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
