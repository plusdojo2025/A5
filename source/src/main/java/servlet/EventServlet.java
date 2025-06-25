package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDao;
import dao.EventTypeDao;
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
		// もしもログインしていなかったらログインページにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("name") == null) {
			response.sendRedirect(request.getContextPath() + "/LogoutServlet");
			return;
		}
		
		// 現在日時を取得し、データベースから当日以降のイベントの情報を持ってくる
		Date date = new Date(new java.util.Date().getTime());
		EventDao eDao= new EventDao();
		List<EventType> eList = eDao.select7(date);
		
		// イベントの内容を持ってくる
		EventTypeDao eTypeDao = new EventTypeDao();
		List<String> tList = eTypeDao.selectAll();
		 
		// 持ってきた情報をリクエストスコープに入れる
		request.setAttribute("eventList", eList);
		request.setAttribute("eventTypeList", tList);
		
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
		request.setCharacterEncoding("UTF-8");
		// もしもログインしていなかったらログインページにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("name") == null) {
			response.sendRedirect(request.getContextPath() + "/LogoutServlet");
			return;
		}
		
		if (request.getParameter("submit").equals("登録")) {
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
		} else if (request.getParameter("submit").equals("追加")) {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String newEvent = request.getParameter("newEvent");
			
			// 登録するデータを受け取り、データベースに登録する
			EventTypeDao etDao = new EventTypeDao();
			if (etDao.insert(newEvent)) {
				String result = "追加に成功しました。";
				request.setAttribute("result", result);
			} else {
				String result = "追加に失敗しました。";
				request.setAttribute("result", result);
			}
		} else if (request.getParameter("submit").equals("削除")) {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String delEvent = request.getParameter("delEvent");
			
			// 登録するデータを受け取り、データベースに登録する
			EventTypeDao etDao = new EventTypeDao();
			if (etDao.delete(delEvent)) {
				String result = "削除に成功しました。";
				request.setAttribute("result", result);
			} else {
				String result = "削除に失敗しました。";
				request.setAttribute("result", result);
			}
		} else if (request.getParameter("submit").equals("イベント削除")) {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String delEventDate = request.getParameter("delEventDate");
			String delEventStart = request.getParameter("delEventStart");
			String delEventEnd = request.getParameter("delEventEnd");
			String delEventType = request.getParameter("delEventType");
			
			EventType eType = new EventType();
			eType.setEventDate(delEventDate);
			eType.setEventStart(delEventStart);
			eType.setEventEnd(delEventEnd);
			eType.setEventType(delEventType);
			
			// 登録するデータを受け取り、データベースに登録する
			EventDao eDao = new EventDao();
			if (eDao.delete(eType)) {
				String result = "削除に成功しました。";
				request.setAttribute("result", result);
			} else {
				String result = "削除に失敗しました。";
				request.setAttribute("result", result);
			}
		}
		response.sendRedirect(request.getContextPath() + "/EventServlet");
	}
}
