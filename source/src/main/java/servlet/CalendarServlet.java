package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/CalenderServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	             Statement stmt = conn.createStatement()) {
			String shiftQuery = "SELECT date, COUNT(shift_id) AS shift_count FROM shift GROUP BY date";
            ResultSet shiftResult = stmt.executeQuery(shiftQuery);
            int shiftCount = 0;
            if (shiftResult.next()) {
                shiftCount = shiftResult.getInt("shift_count");
            }
            String eventQuery = "SELECT event_date, COUNT(event_id) AS event_count FROM event GROUP BY event_date";
            ResultSet eventResult = stmt.executeQuery(eventQuery);
            int eventCount = 0;
            if (eventResult.next()) {
                eventCount = eventResult.getInt("event_count");
            }
            request.setAttribute("shiftCount", shiftCount);
            request.setAttribute("eventCount", eventCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_calendar.jsp");
		dispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			}
	}
