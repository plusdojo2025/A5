package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDao;
import dto.EventType;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// イベントページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_event.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインページにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String[] eventDates = request.getParameterValues("date");
		String[] eventStarts = request.getParameterValues("start");
		String[] eventEnds = request.getParameterValues("end");
		String[] events = request.getParameterValues("event");

		
		// 登録するデータを受け取り、データベースに登録する
		EventDao eDao = new EventDao();
		for (int i = 0; i < eventDates.length; i++) {
			if (eDao.insert(new EventType(eventDates[i], eventStarts[i], eventEnds[i], 0, events[i]))) {
				String result = "登録に成功しました。";
				request.setAttribute("result", result);
			} else {
				String result = "登録に失敗しました。";
				request.setAttribute("result", result);
				break;
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_event.jsp");
		dispatcher.forward(request, response);
	}
}
