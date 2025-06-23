package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
		// 店長ページにフォワードする
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
			response.sendRedirect("/webapp/LoginServlet");
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
		EventType event = new EventType();
		for (int i = 0; i < eventDates.length; i++) {
			if (eDao.insert(new EventType(eventDates[i], eventStarts[i], eventEnds[i], 0, events[i]))) {
				
			}
		}
		
		
		if (loginUser != null) { // ログイン成功
			// セッションスコープにユーザーネーム・店長フラグを格納する
			HttpSession session = request.getSession();
			String loginUserName = (loginUser.get(0)).getName();
			int loginUserFlag = (loginUser.get(0)).getFlag();
			session.setAttribute("user", loginUserName);
			session.setAttribute("flag", loginUserFlag);
			
			// 日時を取得しセッションスコープに格納する
			Date now = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formatDate = date.format(now);
			session.setAttribute("date", formatDate);
			
			
			response.sendRedirect("/webapp/Servlet");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			// request.setAttribute("result", new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/webapp/LoginServlet"));
			
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}
	}
}
